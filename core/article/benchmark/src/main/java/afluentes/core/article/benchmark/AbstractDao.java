package afluentes.core.article.benchmark;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;

import javax.sql.DataSource;

import afluentes.core.api.IAsynchronousFunction1;
import afluentes.core.api.ICallback;
import afluentes.core.api.IEvaluation;
import afluentes.core.api.IEvaluator1;
import afluentes.core.api.IEvaluator2;
import afluentes.core.api.ISynchronousFunction1;
import afluentes.core.api.ISynchronousFunction2;
import afluentes.core.impl.AsynchronousEvaluator1;
import afluentes.core.impl.Constant;
import afluentes.core.impl.SynchronousEvaluator1;
import afluentes.core.impl.SynchronousEvaluator2;

import com.google.common.base.Joiner;

abstract class AbstractDao {
	static final boolean DEBUG = false;
	static final boolean DEBUG_SQL = true;
	static final boolean DEBUG_SLEEP = false;

	static void debug(String sql) {
		if (DEBUG) {
			if (DEBUG_SQL) {
				System.out.println(sql);
			}
			if (DEBUG_SLEEP) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {}
			}
		}
	}

	DataSource ds;

	AbstractDao(final DataSource ds) {
		this.ds = ds;
	}
	
	List<Message> getMessageList(List<Integer> messageIds) {
		String predicate;
		if (messageIds.size() == 1) {
			predicate = "ID = " + messageIds.get(0);
		} else {
			predicate = "ID in (" + Joiner.on(", ").join(messageIds) + ")";
		}
		String query = getSelectMessageQuery(predicate);
		long t = System.nanoTime();
		try (Connection c = ds.getConnection();
				Statement s = c.createStatement();
				ResultSet rs = s.executeQuery(query)) {
			return getMessageList(rs);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			t = System.nanoTime() - t;
			debug(t + ";" + query);
		}
	}	
	
	String getSelectMessageQuery(String predicate) {
		return "select ID, SUBJECT, BODY, SENDER_ID from MESSAGE where " + predicate;
	}	

	List<Message> getMessageList(ResultSet rs) throws SQLException {
		List<Message> messageList = new ArrayList<>();
		while (rs.next()) {
			Message message = getMessage(rs); 
			messageList.add(message);
		}
		setSenderProxies(messageList);
		setRecipientsProxies(messageList);
		return messageList;
	}

	Message getMessage(ResultSet rs) throws SQLException {
		UserImpl sender = new UserImpl();
		sender.id = rs.getInt("SENDER_ID");		
		
		Message message = new Message();
		message.id = rs.getInt("ID");
		message.subject = rs.getString("SUBJECT");
		message.body = rs.getString("BODY");
		message.sender = sender;
		return message;
	}

	abstract void setSenderProxies(List<Message> messageList);

	abstract void setRecipientsProxies(List<Message> messageList);

	IUser getUser(int userId) {
		String query = getSelectUserQuery("ID = " + userId);		
		long t = System.nanoTime();
		try (Connection c = ds.getConnection();
				Statement s = c.createStatement();
				ResultSet rs = s.executeQuery(query)) {
			if (rs.next()) {
				return getUser(rs);
			} else {
				return null;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			t = System.nanoTime() - t;
			debug(t + ";" + query);
		}
	}
	
	String getSelectUserQuery(String predicate) {
		return "select * from USER where " + predicate; 
	}
		
	IUser getUser(ResultSet rs) throws SQLException {
		UserImpl user = new UserImpl();
		user.id = rs.getInt("ID");
		user.name = rs.getString("NAME");		
		return user;
	}	
	
	Map<Integer, IUser> getUserMap(List<Integer> userIdList) {
		String predicate;
		if (userIdList.size() == 1) {
			predicate = "ID = " + userIdList.get(0);
		} else {
			predicate = "ID in (" + Joiner.on(", ").join(userIdList) + ")";
		}
		String query = getSelectUserQuery(predicate);
		long t = System.nanoTime();		
		try (Connection c = ds.getConnection();
				Statement s = c.createStatement();
				ResultSet rs = s.executeQuery(query)) {
			return getUserMap(rs);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			t = System.nanoTime() - t;
			debug(t + ";" + query);
		}
	}
		
	Map<Integer, IUser> getUserMap(ResultSet rs) throws SQLException {
		Map<Integer, IUser> userMap = new HashMap<>();
		while (rs.next()) {
			IUser user = getUser(rs);
			userMap.put(user.getId(), user);
		}
		return userMap;
	}	
	
	List<IUser> getMessageRecipientList(int messageId) {
		String query = getSelectMessageRecipientQuery("MESSAGE_RECIPIENT.MESSAGE_ID = " + messageId);
		long t = System.nanoTime();
		try (Connection c = ds.getConnection();
				Statement s = c.createStatement();
				ResultSet rs = s.executeQuery(query)) {
			return getUserList(rs);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			t = System.nanoTime() - t;
			debug(t + ";" + query);
		}
	}

	String getSelectMessageRecipientQuery(String predicate) {
		return "select USER.*, MESSAGE_RECIPIENT.MESSAGE_ID from USER, MESSAGE_RECIPIENT where USER.ID = MESSAGE_RECIPIENT.RECIPIENT_ID and " + predicate;
	}
	
	List<IUser> getUserList(ResultSet rs) throws SQLException {
		List<IUser> userList = new ArrayList<>();
		while (rs.next()) {
			IUser user = getUser(rs); 
			userList.add(user);
		}
		return userList;
	}		
	
	Map<Integer, List<IUser>> getMessageRecipientListMap(List<Integer> messageIdList) {
		String predicate;
		if (messageIdList.size() == 1) {
			predicate = "MESSAGE_RECIPIENT.MESSAGE_ID = " + messageIdList.get(0);
		} else {
			predicate = "MESSAGE_RECIPIENT.MESSAGE_ID in (" + Joiner.on(", ").join(messageIdList) + ")";
		}
		String query = getSelectMessageRecipientQuery(predicate); 		
		long t = System.nanoTime();
		try (Connection c = ds.getConnection();
				Statement s = c.createStatement();
				ResultSet rs = s.executeQuery(query)) {
			return getMessageRecipientListMap(rs);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			t = System.nanoTime() - t;
			debug(t + ";" + query);
		}
	}

	Map<Integer, List<IUser>> getMessageRecipientListMap(ResultSet rs) throws SQLException {
		Map<Integer, List<IUser>> messageRecipientListMap = new HashMap<>();
		while (rs.next()) {
			Integer messageId = rs.getInt("MESSAGE_ID");
			List<IUser> messageRecipientList = messageRecipientListMap.get(messageId);
			if (messageRecipientList == null) {
				messageRecipientList = new ArrayList<>();
				messageRecipientListMap.put(messageId, messageRecipientList);
			}
			IUser messageRecipient = getUser(rs);
			messageRecipientList.add(messageRecipient);
		}
		return messageRecipientListMap;
	}
}

class StandardDao extends AbstractDao {
	StandardDao(DataSource ds) {
		super(ds);
	}

	@Override
	void setSenderProxies(List<Message> messageList) {
		for (Message message : messageList) {
			message.sender = new StandardSenderProxy(this, message.sender.getId());
		}
	}

	@Override
	void setRecipientsProxies(List<Message> messageList) {
		for (Message message : messageList) {
			message.recipients = new StandardRecipientsProxy(this, message.id);
		}		
	}
}

class AfluentesDao extends AbstractDao {
	IAsynchronousFunction1<Integer, IUser> getUserFn;
	IEvaluator1<Integer, IUser> getUser;
	
	IAsynchronousFunction1<Integer, List<IUser>> getMessageRecipientListFn;	
	IEvaluator1<Integer, List<IUser>> getMessageRecipientList;

	AfluentesDao(final DataSource ds, final ExecutorService executor) {
		super(ds);
		
		getUserFn = new IAsynchronousFunction1<Integer, IUser>() {
			@Override
			public void y(final Integer userId, final ICallback<IUser> callback) {
				executor.execute(new Runnable() {
					@Override
					public void run() {
						IUser user = null;
						try {
							user = getUser(userId);
						} catch (Throwable t) {
							callback.t(t);
						}
						callback.y(user);
					}
				});				
			}
		};		
		getUser = new AsynchronousEvaluator1<>(getUserFn);
		
		getMessageRecipientListFn = new IAsynchronousFunction1<Integer, List<IUser>>() {
			@Override
			public void y(final Integer messageId, final ICallback<List<IUser>> callback) {
				executor.execute(new Runnable() {
					@Override
					public void run() {
						List<IUser> messageRecipientList = null;
						try {
							messageRecipientList = getMessageRecipientList(messageId);							
						} catch (Throwable t) {
							callback.t(t);
						}
						callback.y(messageRecipientList);
					}					
				});
			}
		};
		getMessageRecipientList = new AsynchronousEvaluator1<>(getMessageRecipientListFn);
	}
	
	@Override
	void setSenderProxies(List<Message> messageList) {
		for (Message message : messageList) {
			message.sender = new AfluentesSenderProxy(getUser.y(new Constant<>(message.sender.getId()))); 
		}
	}

	@Override
	void setRecipientsProxies(List<Message> messageList) {
		for (Message message : messageList) {
			message.recipients = new AfluentesRecipientsProxy(getMessageRecipientList.y(new Constant<>(message.id)));
		}
	}	
}

class CallbackDao extends AfluentesDao {
	CallbackDao(DataSource ds, ExecutorService executor) {
		super(ds, executor);
	}
	
	@Override
	void setSenderProxies(List<Message> messageList) {
		for (Message message : messageList) {
			message.sender = new CallbackSenderProxy(message.sender.getId()); 
		}
	}

	@Override
	void setRecipientsProxies(List<Message> messageList) {
		for (Message message : messageList) {
			message.recipients = new CallbackRecipientsProxy(message.id);
		}
	}
}

abstract class BatchDao extends AbstractDao {
	int batchSize;
	
	ISynchronousFunction1<List<Integer>, Map<Integer, IUser>> getUserMapSyncFn;
	IAsynchronousFunction1<List<Integer>, Map<Integer, IUser>> getUserMapAsyncFn;
	IEvaluator1<List<Integer>, Map<Integer, IUser>> getUserMap;

	ISynchronousFunction2<Map<Integer, IUser>, Integer, IUser> getUserFn;
	IEvaluator2<Map<Integer, IUser>, Integer, IUser> getUser;
	
	ISynchronousFunction1<List<Integer>, Map<Integer, List<IUser>>> getMessageRecipientListMapSyncFn;
	IAsynchronousFunction1<List<Integer>, Map<Integer, List<IUser>>> getMessageRecipientListMapAsyncFn;
	IEvaluator1<List<Integer>, Map<Integer, List<IUser>>> getMessageRecipientListMap;
	
	ISynchronousFunction2<Map<Integer, List<IUser>>, Integer, List<IUser>> getMessageRecipientListFn;	
	IEvaluator2<Map<Integer, List<IUser>>, Integer, List<IUser>> getMessageRecipientList;

	BatchDao(final DataSource ds, final ExecutorService executor, int batchSize) {
		super(ds);
		
		this.batchSize = batchSize;
		
		this.getUserMapSyncFn = new ISynchronousFunction1<List<Integer>, Map<Integer, IUser>>() {
			@Override
			public Map<Integer, IUser> y(List<Integer> userIdList) {
				return getUserMap(userIdList);
			}
		};
		this.getUserMapAsyncFn = new IAsynchronousFunction1<List<Integer>, Map<Integer, IUser>>() {
			@Override
			public void y(final List<Integer> userIdList, final ICallback<Map<Integer, IUser>> callback) {
				executor.execute(new Runnable() {					
					@Override
					public void run() {
						Map<Integer, IUser> userMap = null;
						try {
							userMap = getUserMap(userIdList);
						} catch (Throwable t) {
							callback.t(t);
						}
						callback.y(userMap);
					}					
				});					
			}
		};
		
		this.getUserFn = new ISynchronousFunction2<Map<Integer, IUser>, Integer, IUser>() {
			@Override
			public IUser y(Map<Integer, IUser> userMap, Integer userId) {
				return userMap.get(userId);
			}
		};
		this.getUser = new SynchronousEvaluator2<>(getUserFn);
		
		this.getMessageRecipientListMapSyncFn = new ISynchronousFunction1<List<Integer>, Map<Integer, List<IUser>>>() {
			@Override
			public Map<Integer, List<IUser>> y(List<Integer> messageIdList) {
				return getMessageRecipientListMap(messageIdList);
			}			
		};
		this.getMessageRecipientListMapAsyncFn = new IAsynchronousFunction1<List<Integer>, Map<Integer, List<IUser>>>() {
			@Override
			public void y(final List<Integer> messageIdList, final ICallback<Map<Integer, List<IUser>>> callback) {
				executor.execute(new Runnable() {
					@Override
					public void run() {				
						Map<Integer, List<IUser>> messageRecipientListMap = null;
						try {
							messageRecipientListMap = getMessageRecipientListMap(messageIdList); 					
						} catch (Throwable t) {
							callback.t(t);
						}
						callback.y(messageRecipientListMap);
					}
				});
			}
		};				
		
		this.getMessageRecipientListFn = new ISynchronousFunction2<Map<Integer, List<IUser>>, Integer, List<IUser>>() {
			@Override
			public List<IUser> y(Map<Integer, List<IUser>> messageRecipientListMap, Integer messageId) {
				return messageRecipientListMap.get(messageId);
			}
		};	
		this.getMessageRecipientList = new SynchronousEvaluator2<>(getMessageRecipientListFn);			
	}

	@Override
	void setSenderProxies(List<Message> messageList) {
		List<Integer> senderIdList = new ArrayList<>();
		for (int i = 0; i < messageList.size(); ++i) {
			Message message = messageList.get(i);
			Integer senderId = message.sender.getId(); 
			senderIdList.add(senderId);
			if (senderIdList.size() == batchSize) {
				setSenderProxies(messageList, i, senderIdList);
				senderIdList = new ArrayList<>();
			}
		}
		setSenderProxies(messageList, messageList.size() - 1, senderIdList);
	}
	
	void setSenderProxies(List<Message> messageList, int i, List<Integer> senderIdList) {
		IEvaluation<Map<Integer, IUser>> senderMap = getUserMap.y(new Constant<List<Integer>>(senderIdList));
		for (int j = i - senderIdList.size() + 1; j <= i; ++j) {
			Message message = messageList.get(j);
			Integer senderId = message.sender.getId();
			IEvaluation<IUser> sender = getUser.y(senderMap, new Constant<Integer>(senderId));
			message.sender = new AfluentesSenderProxy(sender);
		}
	}
	
	@Override
	void setRecipientsProxies(List<Message> messageList) {
		List<Integer> messageIdList = new ArrayList<>();
		for (int i = 0; i < messageList.size(); ++i) {
			Message message = messageList.get(i);
			Integer messageId = message.id;
			messageIdList.add(messageId);
			if (messageIdList.size() == batchSize) {
				setRecipientsProxies(messageList, i, messageIdList);
				messageIdList = new ArrayList<>();
			}
		}
		setRecipientsProxies(messageList, messageList.size() - 1, messageIdList);
	}
	
	void setRecipientsProxies(List<Message> messageList, int i, List<Integer> messageIdList) {
		IEvaluation<Map<Integer, List<IUser>>> messageRecipientListMap = getMessageRecipientListMap.y(new Constant<List<Integer>>(messageIdList));
		for (int j = i - messageIdList.size() + 1; j <= i; ++j) {
			Message message = messageList.get(j);
			Integer messageId = message.id;					
			IEvaluation<List<IUser>> messageRecipientList = getMessageRecipientList.y(messageRecipientListMap, new Constant<Integer>(messageId));
			message.recipients = new AfluentesRecipientsProxy(messageRecipientList);
		}		
	}
}

class SyncBatchDao extends BatchDao {
	SyncBatchDao(DataSource ds, ExecutorService executor, int batchSize) {
		super(ds, executor, batchSize);

		getUserMap = new SynchronousEvaluator1<>(getUserMapSyncFn);
		getMessageRecipientListMap = new SynchronousEvaluator1<>(getMessageRecipientListMapSyncFn);		
	}
}

class AsyncBatchDao extends BatchDao {
	AsyncBatchDao(DataSource ds, ExecutorService executor, int batchSize) {
		super(ds, executor, batchSize);
		
		getUserMap = new AsynchronousEvaluator1<>(getUserMapAsyncFn);
		getMessageRecipientListMap = new AsynchronousEvaluator1<>(getMessageRecipientListMapAsyncFn);
	}
}