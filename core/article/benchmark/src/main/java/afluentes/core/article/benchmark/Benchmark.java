package afluentes.core.article.benchmark;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.sql.DataSource;

import com.jolbox.bonecp.BoneCPDataSource;

class Benchmark {
	void execute() throws SQLException, FileNotFoundException {
		BoneCPDataSource ds = null;
		ExecutorService executor = null;
		try {
			ds = new BoneCPDataSource();
			ds.setDriverClass("com.mysql.jdbc.Driver");
//		 	ds.setDriverClass("org.mariadb.jdbc.Driver");			
		 	ds.setJdbcUrl("jdbc:mysql://192.168.1.3/afluentes_100");
		 	ds.setUsername("afluentes");
		 	ds.setPassword("afluentes");
		 	ds.setMaxConnectionsPerPartition(100);
		 	ds.setDefaultReadOnly(true);		 	
		 	ds.setDefaultTransactionIsolation("READ_UNCOMMITTED");
		 	ds.sanitize();
		 	
		 	int maximumUserId = getMaximumUserId(ds);

		 	executor = Executors.newCachedThreadPool();

		 	Marshaller marshaller = new Marshaller();
		 	
/*		 	
		 	AfluentesDao dao = new CallbackDao(ds, executor);
		 	execute("callback", maximumUserId, dao, new CallbackLoader(dao), marshaller);
		 	execute("afluentes", maximumUserId, new AfluentesDao(ds, executor), new AfluentesLoader(), marshaller);
		 	execute("standard", maximumUserId, new StandardDao(ds), new StandardLoader(), marshaller);

		 	execute("syncBatch1", maximumUserId, new SyncBatchDao(ds, executor, 1), new AfluentesLoader(), marshaller);
		 	execute("asyncBatch1", maximumUserId, new AsyncBatchDao(ds, executor, 1), new AfluentesLoader(), marshaller);
		 	
		 	for (int i = 1; i <= 25; i += 1) {
		 		execute("syncBatch" + i, maximumUserId, new SyncBatchDao(ds, executor, i), new AfluentesLoader(), marshaller);
			 	execute("asyncBatch" + i, maximumUserId, new AsyncBatchDao(ds, executor, i), new AfluentesLoader(), marshaller);		 		
		 	}		 	
*/

/*		 	
		 	AfluentesDao dao = new CallbackDao(ds, executor);
			execute("standard", maximumUserId, new StandardDao(ds), new StandardLoader(), marshaller);
			execute("callback", maximumUserId, dao, new CallbackLoader(dao), marshaller);
			execute("afluentes", maximumUserId, new AfluentesDao(ds, executor), new AfluentesLoader(), marshaller);
*/

			execute("asyncBatch", maximumUserId, new AsyncBatchDao(ds, executor, 100), new AfluentesLoader(), marshaller);
			execute("syncBatch", maximumUserId, new SyncBatchDao(ds, executor, 100), new AfluentesLoader(), marshaller);
		} finally {
			if (executor != null) {
				try {
					executor.shutdown();
				} catch (Exception e) {}				
			}
			if (ds != null) {
				try {
					ds.close();
				} catch (Exception e) {}
			}
		}
	}
	
	int getMaximumUserId(DataSource ds) throws SQLException {
		try (Connection c = ds.getConnection();
				Statement s = c.createStatement();
				ResultSet rs = s.executeQuery("select max(ID) from USER")) {
			rs.next();
			return rs.getInt(1);
		}
	}

	void execute(String path, int maximumUserId, AbstractDao dao, ILoader loader, Marshaller marshaller) throws FileNotFoundException {
		for (int i = 0; i < 3; ++i) {
			long t1 = System.currentTimeMillis();
			
			long[] ts = new long[1000];
			long[] ns = new long[1000];

			for (int userId = 0; userId < UserIdLists.USER_ID_LIST_100_MESSAGES.length; ++userId) {
//			for (int userId = 1; userId <= maximumUserId; ++userId) {
		 		long t2 = System.nanoTime();
		 		List<Message> messages = dao.getSentMessageList(UserIdLists.USER_ID_LIST_100_MESSAGES[userId]);
		 		loader.loadMessages(messages);
		 		marshaller.marshallMessages(messages);		 		
		 		t2 = System.nanoTime() - t2;

		 		int index = messages.size(); 
		 		ts[index] += t2;
		 		++ns[index];
		 	}
			
			t1 = System.currentTimeMillis() - t1;
			System.out.println(path + ": " + (t1 / 1000.0) + "s");
			
			try (PrintWriter writer = new PrintWriter(path + "." + i + ".csv")) {
				for (int index = 0; index < ts.length; ++index) {
					if (ns[index] > 0) { 
						writer.println(index + "\t" + ts[index] + "\t" + ns[index] + "\t" + (ts[index] / ns[index]));
					}
				}			
			}			
	 	}
	}
		
	public static void main(String args[]) throws FileNotFoundException, SQLException {
		new Benchmark().execute();
	}
}