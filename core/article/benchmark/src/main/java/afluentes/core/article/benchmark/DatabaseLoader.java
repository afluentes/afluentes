package afluentes.core.article.benchmark;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

class DatabaseLoader {
	static final int MAXIMUM_BATCH_SIZE = 1000;
	static final String BODY = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum lacus nunc, imperdiet vitae sed.";
	
	void loadDatabase(int userCount, int messageCount) throws SQLException {
		try (Connection c = DriverManager.getConnection("jdbc:mysql://localhost/afluentes", "afluentes", "afluentes");
				Statement s = c.createStatement()) {
			c.setAutoCommit(true);
			dropTables(c);
			createTables(c);
			
			c.setAutoCommit(false);
			loadUsers(c, userCount);
			loadMessages(c, userCount, messageCount);
			loadMessagesRecipients(c, userCount, messageCount);
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

	void loadUsers(Connection c, int userCount) throws SQLException {		
		long t = System.currentTimeMillis();
		System.out.println("Loading users");
		try (PreparedStatement s = c.prepareStatement("insert into USER (NAME) values (?)")) {
			int batchSize = 0;
			for (int i = 1; i <= userCount; ++i) {
				String name = "User " + i;
				s.setString(1, name);
				s.addBatch();
				if (batchSize == MAXIMUM_BATCH_SIZE) {
					s.executeBatch();
					c.commit();
					batchSize = 0;
				} else {
					++batchSize;
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
	
	void loadMessages(Connection c, int userCount, int messageCount) throws SQLException {
		long t = System.currentTimeMillis();
		System.out.println("Loading messages");
		Random r = new Random();
		try (PreparedStatement s = c.prepareStatement("insert into MESSAGE (SUBJECT, BODY, SENDER_ID) values (?, ?, ?)")) {
			int batchSize = 0;
			for (int i = 1; i <= messageCount; ++i) {
				String subject = "Message " + i;
				String body = BODY;
				int senderId = r.nextInt(userCount) + 1;
				s.setString(1, subject);
				s.setString(2, body);
				s.setInt(3, senderId);
				s.addBatch();
				if (batchSize == MAXIMUM_BATCH_SIZE) {
					s.executeBatch();
					c.commit();
					batchSize = 0;
				} else {
					++batchSize;
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

	void loadMessagesRecipients(Connection c, int userCount, int messageCount) throws SQLException {
		long t = System.currentTimeMillis();
		System.out.println("Loading messages recipients");
		Random r = new Random();
		try (PreparedStatement s = c.prepareStatement("insert into MESSAGE_RECIPIENT(MESSAGE_ID, RECIPIENT_ID) values (?, ?)")) {
			int batchSize = 0;
			for (int i = 1; i <= messageCount; ++i) {
				int messageId = i;
				int recipientId = r.nextInt(userCount) + 1;
				s.setInt(1, messageId);
				s.setInt(2, recipientId);
				s.addBatch();
				if (batchSize == MAXIMUM_BATCH_SIZE) {
					s.executeBatch();
					c.commit();
					batchSize = 0;
				} else {
					++batchSize;
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
		new DatabaseLoader().loadDatabase(10 * 1000, 100 * 1000);
	}	
}