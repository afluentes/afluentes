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
		 	ds.setJdbcUrl("jdbc:mysql://localhost/afluentes");
		 	ds.setUsername("afluentes");
		 	ds.setPassword("afluentes");
		 	
		 	int maximumUserId = getMaximumUserId(ds);

		 	executor = Executors.newCachedThreadPool();

		 	Marshaller marshaller = new Marshaller();

/*		 	
		 	execute("standard", maximumUserId, new StandardDao(ds), new StandardLoader(), marshaller);
		 	execute("afluentes", maximumUserId, new AfluentesDao(ds, executor), new AfluentesLoader(), marshaller);
*/
		 	
		 	AfluentesDao dao = new CallbackDao(ds, executor);
		 	execute("callback", maximumUserId, dao, new CallbackLoader(dao), marshaller);
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
		for (int i = 0; i < 2; ++i) {
			long[] ts = new long[1000];
			long[] ns = new long[1000];

			for (int userId = 1; userId <= 1; ++userId) {
		 		long t = System.nanoTime();
		 		List<Message> messages = dao.getMessages(userId);
		 		loader.loadMessages(messages);
		 		System.out.println(marshaller.marshallMessages(messages));		 		
		 		t = System.nanoTime() - t;

		 		int index = messages.size(); 
		 		ts[index] += t;
		 		++ns[index];
		 	}
			
			try (PrintWriter writer = new PrintWriter(path + i + ".csv")) {
				for (int index = 0; index < ts.length; ++index) {
					if (ns[index] > 0) { 
						writer.println(index + "\t" + ts[index] + "\t" + ns[index] + "\t" + (ts[index] / ns[index]));
					}
				}			
			}			
	 	}
	}
		
	public static void main(String args[]) throws ClassNotFoundException, SQLException, FileNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
		new Benchmark().execute();
	}
}