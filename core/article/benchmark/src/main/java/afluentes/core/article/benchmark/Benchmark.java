package afluentes.core.article.benchmark;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.jolbox.bonecp.BoneCPDataSource;

class Benchmark {
	void execute() {
		BoneCPDataSource ds = null;
		ExecutorService executor = null;
		try {
			ds = new BoneCPDataSource();
		 	ds.setJdbcUrl("jdbc:mysql://localhost/afluentes");
		 	ds.setUsername("afluentes");
		 	ds.setPassword("afluentes");

		 	executor = Executors.newCachedThreadPool();

		 	Marshaller marshaller = new Marshaller();

		 	execute(new ImperativeDao(ds), new ImperativeLoader(), marshaller);
		 	execute(new FunctionalDao(ds, executor), new FunctionalLoader(), marshaller);
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

	void execute(AbstractDao dao, ILoader loader, Marshaller marshaller) {
		for (int i = 0; i < 3; ++i) {
		 	long t = System.currentTimeMillis();
		 	for (int j = 0; j < 1000; ++j) {
		 		List<Message> messages = dao.getMessages();
		 		loader.loadMessages(messages);
		 		marshaller.marshallMessages(messages);
		 	}
		 	t = System.currentTimeMillis() - t;
		 	System.out.println("Elapsed time: " + t);		 		
	 	}		
	}
		
	public static void main(String args[]) throws ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
		new Benchmark().execute();
	}
}