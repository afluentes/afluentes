package afluentes.core.article.benchmark;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;

import javax.sql.DataSource;

import afluentes.core.api.IAsynchronousFunction1;
import afluentes.core.api.ICallback;
import afluentes.core.api.IEvaluator1;
import afluentes.core.impl.AsynchronousEvaluator1;
import afluentes.core.impl.Constant;

abstract class AbstractDao {
	static final boolean DEBUG = false;
	
	static void debug(String sql) {
		if (DEBUG) {
			System.out.println(sql);
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {}
		}
	}
	
	DataSource ds;

	AbstractDao(final DataSource ds) {
		this.ds = ds;		
	}

	List<Message> getMessages(int senderId) {
		String sql = "select ID, SUBJECT, BODY, SENDER_ID from MESSAGE where SENDER_ID = " + senderId;
		try (Connection c = ds.getConnection();
				Statement s = c.createStatement();
				ResultSet rs = s.executeQuery(sql)) {
			return getMessages(rs);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}		
	}
	
	List<Message> getMessages(ResultSet rs) throws SQLException {
		List<Message> messages = new ArrayList<>();
		while (rs.next()) {
			messages.add(getMessage(rs));
		}
		return messages;
	}

	Message getMessage(ResultSet rs) throws SQLException {
		Message message = new Message();
		message.id = rs.getInt("ID");
		message.subject = rs.getString("SUBJECT");
		message.body = rs.getString("BODY");
		message.sender = getSenderProxy(rs.getInt("SENDER_ID"));
		message.recipients = getRecipientsProxy(message.id);
		return message;
	}

	abstract IUser getSenderProxy(int senderId);
	
	abstract List<IUser> getRecipientsProxy(int messageId);

	IUser getUser(int userId) {
		String sql = "select * from USER where ID = " + userId;
		debug(sql);
		try (Connection c = ds.getConnection();
				Statement s = c.createStatement();
				ResultSet rs = s.executeQuery(sql)) {
			if (rs.next()) {
				return getUser(rs);
			} else {
				return null;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	IUser getUser(ResultSet rs) throws SQLException {
		UserImpl user = new UserImpl();
		user.id = rs.getInt("ID");
		user.name = rs.getString("NAME");		
		return user;
	}
	
	List<IUser> getRecipients(int messageId) {
		String sql = "select USER.* from USER, MESSAGE_RECIPIENT where USER.ID = MESSAGE_RECIPIENT.RECIPIENT_ID and MESSAGE_RECIPIENT.MESSAGE_ID = " + messageId;
		debug(sql);
		try (Connection c = ds.getConnection();
				Statement s = c.createStatement();
				ResultSet rs = s.executeQuery(sql)) {
			return getUsers(rs);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}		
	}
	
	List<IUser> getUsers(ResultSet rs) throws SQLException {
		List<IUser> users = new ArrayList<>();
		while (rs.next()) {
			users.add(getUser(rs));
		}
		return users;
	}
}

class StandardDao extends AbstractDao {
	StandardDao(DataSource ds) {
		super(ds);
	}

	@Override
	IUser getSenderProxy(int senderId) {
		return getUser(senderId);
	}

	@Override
	List<IUser> getRecipientsProxy(int messageId) {
		return getRecipients(messageId);
	}
}

class AfluentesDao extends AbstractDao {
	IAsynchronousFunction1<Integer, IUser> getUserFn;
	IEvaluator1<Integer, IUser> getUser;
	
	IAsynchronousFunction1<Integer, List<IUser>> getRecipientsFn;	
	IEvaluator1<Integer, List<IUser>> getRecipients;

	AfluentesDao(final DataSource ds, final ExecutorService executor) {
		super(ds);
		
		getUserFn = new IAsynchronousFunction1<Integer, IUser>() {
			@Override
			public void y(final Integer userId, final ICallback<IUser> callback) {
				executor.execute(new Runnable() {
					@Override
					public void run() {
						try {
							callback.y(getUser(userId));
						} catch (Throwable t) {
							callback.t(t);
						}
					}					
				});				
			}
		};		
		getUser = new AsynchronousEvaluator1<>(getUserFn);
		
		getRecipientsFn = new IAsynchronousFunction1<Integer, List<IUser>>() {
			@Override
			public void y(final Integer userId, final ICallback<List<IUser>> callback) {
				executor.execute(new Runnable() {
					@Override
					public void run() {
						try {							
							callback.y(getRecipients(userId));
						} catch (Throwable t) {
							callback.t(t);
						}
					}					
				});
			}
		};
		getRecipients = new AsynchronousEvaluator1<>(getRecipientsFn);
	}

	@Override
	IUser getSenderProxy(int senderId) {
		return new AfluentesSenderProxy(getUser.y(new Constant<>(senderId)));
	}

	@Override
	List<IUser> getRecipientsProxy(int messageId) {
		return new AfluentesRecipientsProxy(getRecipients.y(new Constant<>(messageId)));
	}
}

class CallbackDao extends AfluentesDao {
	CallbackDao(DataSource ds, ExecutorService executor) {
		super(ds, executor);
	}

	@Override
	IUser getSenderProxy(int senderId) {
		return new CallbackSenderProxy(senderId);
	}

	@Override
	List<IUser> getRecipientsProxy(int messageId) {
		return new CallbackRecipientsProxy(messageId);
	}
}