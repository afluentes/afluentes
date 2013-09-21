package afluentes.core.article.benchmark;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;

class DatabaseLoader {
	static final int MAXIMUM_BATCH_SIZE = 1000;
	
	static final String BODY_100;
	static final String BODY_500;
	static final String BODY_1000;
	static final String BODY_10000;
	static final String BODY_20000;
	static final String BODY_30000;
	static final String BODY_40000;
	static final String BODY_50000;
	static final String BODY_60000;

	static {
		try {
			BODY_100 = Resources.toString(Resources.getResource(DatabaseLoader.class, "body_100"), Charsets.UTF_8);
			BODY_500 = Resources.toString(Resources.getResource(DatabaseLoader.class, "body_500"), Charsets.UTF_8);
			BODY_1000 = Resources.toString(Resources.getResource(DatabaseLoader.class, "body_1000"), Charsets.UTF_8);
			BODY_10000 = Resources.toString(Resources.getResource(DatabaseLoader.class, "body_10000"), Charsets.UTF_8);
			BODY_20000 = Resources.toString(Resources.getResource(DatabaseLoader.class, "body_20000"), Charsets.UTF_8);
			BODY_30000 = Resources.toString(Resources.getResource(DatabaseLoader.class, "body_30000"), Charsets.UTF_8);
			BODY_40000 = Resources.toString(Resources.getResource(DatabaseLoader.class, "body_40000"), Charsets.UTF_8);
			BODY_50000 = Resources.toString(Resources.getResource(DatabaseLoader.class, "body_50000"), Charsets.UTF_8);
			BODY_60000 = Resources.toString(Resources.getResource(DatabaseLoader.class, "body_60000"), Charsets.UTF_8);
		} catch (IOException e) {
			throw new ExceptionInInitializerError(e);
		}
	}
	
	static final String BODY = BODY_1000;

	void loadDatabase(int userPerMessageCountCount,
					  int maximimMessagesPerUserCount,
					  int recipientsPerMessageCount) throws SQLException {
		try (Connection c = DriverManager.getConnection("jdbc:mysql://localhost/afluentes_1000", "afluentes", "afluentes");
				Statement s = c.createStatement()) {
			c.setAutoCommit(true);
			dropTables(c);
			createTables(c);
			
			c.setAutoCommit(false);
			loadUsers(c, userPerMessageCountCount, maximimMessagesPerUserCount);
			loadMessages(c, userPerMessageCountCount, maximimMessagesPerUserCount);
			loadMessagesRecipients(c, userPerMessageCountCount, maximimMessagesPerUserCount, recipientsPerMessageCount);
		}
	}
	
	void dropTables(Connection c) throws SQLException {
		try (Statement s = c.createStatement()) {
			s.executeUpdate("drop table if exists MESSAGE_RECIPIENT");
			s.executeUpdate("drop table if exists MESSAGE");
			s.executeUpdate("drop table if exists USER");					
		}
	}
	
	void createTables(Connection c) throws SQLException {
		try (Statement s = c.createStatement()) {
			s.executeUpdate("create table USER (ID int auto_increment, NAME varchar(100) not null, primary key(ID))");
			s.executeUpdate("create table MESSAGE (ID int auto_increment, SUBJECT varchar(100), BODY text, SENDER_ID int, primary key(ID), foreign key (SENDER_ID) references USER(ID))");
			s.executeUpdate("create table MESSAGE_RECIPIENT (MESSAGE_ID int, RECIPIENT_ID int, primary key (MESSAGE_ID, RECIPIENT_ID), foreign key (MESSAGE_ID) references MESSAGE(ID), foreign key (RECIPIENT_ID) references USER(ID))");
		}			
	}

	void loadUsers(Connection c, int userPerMessageCountCount, int maximimMessagesPerUserCount) throws SQLException {		
		long t = System.currentTimeMillis();
		System.out.println("Loading users");
		try (PreparedStatement s = c.prepareStatement("insert into USER (NAME) values (?)")) {
			int userId = 0;
			int batchSize = 0;
			for (int i = 0; i < userPerMessageCountCount; ++i) {
				for (int j = 0; j <= maximimMessagesPerUserCount; ++j) {				
					++userId;
					String userName = "User " + userId;
					s.setString(1, userName);
					s.addBatch();
					if (batchSize == MAXIMUM_BATCH_SIZE) {
						s.executeBatch();
						c.commit();
						batchSize = 0;
					} else {
						++batchSize;
					}
				}
			}
			if (batchSize > 0) {
				s.executeBatch();
				c.commit();
			}
		}
		t = System.currentTimeMillis() - t;
		System.out.println("Users loaded in " + t + "ms");
	}
	
	void loadMessages(Connection c, int userPerMessageCountCount, int maximimMessagesPerUserCount) throws SQLException {
		long t = System.currentTimeMillis();
		System.out.println("Loading messages");
		try (PreparedStatement s = c.prepareStatement("insert into MESSAGE (SUBJECT, BODY, SENDER_ID) values (?, ?, ?)")) {
			int messageSenderId = 0;						
			int messageId = 0;
			int batchSize = 0;			
			for (int i = 0; i < userPerMessageCountCount; ++i) {				
				for (int j = 0; j <= maximimMessagesPerUserCount; ++j) {
					++messageSenderId;
					for (int k = 0; k < j; ++k) {
						++messageId;
						String messageSubject = "Message " + messageId;
						String messageBody = BODY;					
						s.setString(1, messageSubject);
						s.setString(2, messageBody);
						s.setInt(3, messageSenderId);
						s.addBatch();
						if (batchSize == MAXIMUM_BATCH_SIZE) {
							s.executeBatch();
							c.commit();
							batchSize = 0;
						} else {
							++batchSize;
						}						
					}
				}
			}
			if (batchSize > 0) {
				s.executeBatch();
				c.commit();				
			}
		}
		t = System.currentTimeMillis() - t;
		System.out.println("Messages loaded in " + t + "ms");
	}

	void loadMessagesRecipients(Connection c, int userPerMessageCountCount, int maximimMessagesPerUserCount, int recipientsPerMessageCount) throws SQLException {
		long t = System.currentTimeMillis();
		System.out.println("Loading messages recipients");
		try (PreparedStatement s = c.prepareStatement("insert into MESSAGE_RECIPIENT(MESSAGE_ID, RECIPIENT_ID) values (?, ?)")) {
			Random messageRecipientIdRandom = new Random();
			int userCount = userPerMessageCountCount * (maximimMessagesPerUserCount + 1);
			int messageId = 0;
			int batchSize = 0;
			for (int i = 0; i < userPerMessageCountCount; ++i) {
				for (int j = 0; j <= maximimMessagesPerUserCount; ++j) {
					for (int k = 0; k < j; ++k) {
						++messageId;
						for (int x = 0; x < recipientsPerMessageCount; ++x) {					
							int messageRecipientId = messageRecipientIdRandom.nextInt(userCount) + 1;
							s.setInt(1, messageId);
							s.setInt(2, messageRecipientId);
							s.addBatch();
							if (batchSize == MAXIMUM_BATCH_SIZE) {
								s.executeBatch();
								c.commit();
								batchSize = 0;
							} else {
								++batchSize;
							}						
						}						
					}
				}
			}
			if (batchSize > 0) {
				s.executeBatch();
				c.commit();
			}
		}
		t = System.currentTimeMillis() - t;
		System.out.println("Messages loaded in " + t + "ms");		
	}	

	public static void main(String args[]) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		new DatabaseLoader().loadDatabase(100, 100, 1);
	}	
}