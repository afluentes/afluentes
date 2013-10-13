package afluentes.core.article.benchmark;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

import com.google.common.base.Joiner;
import com.softabar.lipsum.LoremIpsum4J;

class DatabaseLoader {
	static final int MAXIMUM_BATCH_SIZE = 1000;
	
	String driver;
	String url;
	String user;
	String password;
	int userCount;	
	int messageCount;
	int bodyLength;
	int recipientCount;
	int fileCount;

	void loadDatabase() throws ClassNotFoundException, SQLException {
		Class.forName(driver);
		try (Connection c = DriverManager.getConnection(url, user, password);
				Statement s = c.createStatement()) {
			dropTables(s);
			createTables(s);

			c.setAutoCommit(false);
			loadMediaType(c);			
			loadFile(c);
			loadUser(c);		
			loadMessage(c);
			loadMessageRecipient(c);
			loadMessageFile(c);
		}
	}
	
	void dropTables(Statement s) throws SQLException {
		s.executeUpdate("drop table if exists MESSAGE_RECIPIENT");
		s.executeUpdate("drop table if exists MESSAGE_FILE");
		s.executeUpdate("drop table if exists MESSAGE");
		s.executeUpdate("drop table if exists USER");
		s.executeUpdate("drop table if exists FILE");
		s.executeUpdate("drop table if exists MEDIA_TYPE");
	}

	void createTables(Statement s) throws SQLException {
		s.executeUpdate("create table MEDIA_TYPE (ID int auto_increment, TYPE varchar(100), SUBTYPE varchar(100), primary key(ID))");
		s.executeUpdate("create table FILE (ID int auto_increment, NAME varchar(100), MEDIA_TYPE_ID int, primary key(ID), foreign key (MEDIA_TYPE_ID) references MEDIA_TYPE(ID))");
		s.executeUpdate("create table USER (ID int auto_increment, NAME varchar(100), PICTURE_ID int, primary key(ID), foreign key (PICTURE_ID) references FILE(ID))");
		s.executeUpdate("create table MESSAGE (ID int auto_increment, SUBJECT varchar(100), BODY text, SENDER_ID int, primary key(ID), foreign key (SENDER_ID) references USER(ID))");
		s.executeUpdate("create table MESSAGE_RECIPIENT (MESSAGE_ID int, RECIPIENT_ID int, foreign key (MESSAGE_ID) references MESSAGE(ID), foreign key (RECIPIENT_ID) references USER(ID))");
		s.executeUpdate("create table MESSAGE_FILE (MESSAGE_ID int, FILE_ID int, foreign key (MESSAGE_ID) references MESSAGE(ID), foreign key (FILE_ID) references FILE(ID))");		
	}
	
	void loadMediaType(Connection c) throws SQLException {
		try (PreparedStatement s = c.prepareStatement("insert into MEDIA_TYPE (TYPE, SUBTYPE) values (?, ?)")) {
			s.setString(1, "image");
			s.setString(2, "jpeg");
			s.executeUpdate();
			c.commit();
		}
	}
	
	void loadFile(Connection c) throws SQLException {
		int batchSize = 0;		
		try (PreparedStatement s = c.prepareStatement("insert into FILE (NAME, MEDIA_TYPE_ID) values (?, 1)")) {
			for (int i = 1; i <= userCount; ++i) {
				s.setString(1, "Picture " + i);
				s.addBatch();
				if (batchSize == MAXIMUM_BATCH_SIZE - 1) {
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
			
			for (int i = 1; i <= messageCount; ++i) {
				for (int j = 0; j < fileCount; ++j) {
					s.setString(1, "File " + i);
					s.addBatch();
					if (batchSize == MAXIMUM_BATCH_SIZE - 1) {
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
	}
	
	void loadUser(Connection c) throws SQLException {
		try (PreparedStatement s = c.prepareStatement("insert into USER (NAME, PICTURE_ID) values (?, ?)")) {
			for (int i = 1; i <= userCount; ++i) {
				s.setString(1, "User " + i);
				s.setInt(2, i);
				s.addBatch();
			}
			s.executeBatch();
			c.commit();
		}		
	}
		
	void loadMessage(Connection c) throws SQLException {
		String body = Joiner.on("").join(new LoremIpsum4J().getBytes(bodyLength));
		Random senderIdRandom = new Random();
		try (PreparedStatement s = c.prepareStatement("insert into MESSAGE (SUBJECT, BODY, SENDER_ID) values (?, ?, ?)")) {
			int batchSize = 0;
			for (int i = 1; i <= messageCount; ++i) {
				s.setString(1, "User " + i);
				s.setString(2, body);
				s.setInt(3, senderIdRandom.nextInt(userCount) + 1);
				s.addBatch();
				if (batchSize == MAXIMUM_BATCH_SIZE - 1) {
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
	}
	
	void loadMessageRecipient(Connection c) throws SQLException {
		Random recipientIdRandom = new Random();
		try (PreparedStatement s = c.prepareStatement("insert into MESSAGE_RECIPIENT (MESSAGE_ID, RECIPIENT_ID) values (?, ?)")) {
			int batchSize = 0;
			for (int i = 1; i <= messageCount; ++i) {
				for (int j = 0; j < recipientCount; ++j) {
					s.setInt(1, i);
					s.setInt(2, recipientIdRandom.nextInt(userCount) + 1);
					s.addBatch();
					if (batchSize == MAXIMUM_BATCH_SIZE - 1) {
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
	}
	
	void loadMessageFile(Connection c) throws SQLException {		
		try (PreparedStatement s = c.prepareStatement("insert into MESSAGE_FILE (MESSAGE_ID, FILE_ID) values (?, ?)")) {
			int fileId = userCount;
			int batchSize = 0;
			for (int i = 1; i <= messageCount; ++i) {
				for (int j = 0; j < recipientCount; ++j) {
					s.setInt(1, i);
					s.setInt(2, ++fileId);
					s.addBatch();
					if (batchSize == MAXIMUM_BATCH_SIZE - 1) {
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
	}	
	
	public static void main(String args[]) throws ClassNotFoundException, SQLException {
		DatabaseLoader loader = new DatabaseLoader();
		loader.driver = "org.mariadb.jdbc.Driver";
		loader.driver = "com.mysql.jdbc.Driver";
		loader.url = "jdbc:mysql://localhost/afluentes";
		loader.user = "afluentes";
		loader.password = "afluentes";
		loader.userCount = 100;
		loader.messageCount = 1000 * loader.userCount;
		loader.bodyLength = 100;
		loader.recipientCount = 1;
		loader.fileCount = 1;
		loader.loadDatabase();
	}	
}