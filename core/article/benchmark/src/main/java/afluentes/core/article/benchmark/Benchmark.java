package afluentes.core.article.benchmark;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
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
		 	ds.setJdbcUrl("jdbc:mysql://localhost/afluentes");
		 	ds.setUsername("afluentes");
		 	ds.setPassword("afluentes");
		 	ds.setMaxConnectionsPerPartition(100);
		 	ds.setDefaultReadOnly(true);		 	
		 	ds.setDefaultTransactionIsolation("READ_UNCOMMITTED");
		 	ds.sanitize();
		 	
		 	int maximumMessageId = getMaximumMessageId(ds);

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

		 	execute("async", maximumMessageId, new AsyncBatchDao(ds, executor, 20), new AfluentesLoader(), marshaller);
		 	execute("sync", maximumMessageId, new SyncBatchDao(ds, executor, 100), new AfluentesLoader(), marshaller);
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

	int getMaximumMessageId(DataSource ds) throws SQLException {
		try (Connection c = ds.getConnection();
				Statement s = c.createStatement();
				ResultSet rs = s.executeQuery("select max(ID) from MESSAGE")) {
			rs.next();
			return rs.getInt(1);
		}
	}

	void execute(String path, int maximumMessageId, AbstractDao dao, ILoader loader, Marshaller marshaller) {
		int outerInteractionCount = 3;
		int innerInteractionCount = 1000;
		int messageCount = 100;
		for (int i = 0; i < outerInteractionCount; ++i) {
			long totalTime = 0;
			Random messageIdRandom = new Random(0);
			for (int j = 0; j < innerInteractionCount; ++j) {
				List<Integer> messageIds = new ArrayList<>();
				for (int k = 0; k < messageCount; ++k) {
					messageIds.add(messageIdRandom.nextInt(maximumMessageId) + 1);
				}
				long time = System.nanoTime();
				List<Message> messages = dao.getMessageList(messageIds);						 		
		 		loader.loadMessages(messages);
		 		marshaller.marshallMessages(messages);		 		
		 		time = System.nanoTime() - time;
		 		totalTime += time;
			}
			double mean = totalTime;
			mean /= innerInteractionCount;
			mean /= 1000000;
			System.out.println(mean);
		}		
	}
		
	public static void main(String args[]) throws FileNotFoundException, SQLException {
		new Benchmark().execute();
	}
}