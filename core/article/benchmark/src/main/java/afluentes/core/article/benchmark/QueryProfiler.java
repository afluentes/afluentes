package afluentes.core.article.benchmark;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.jolbox.bonecp.BoneCPDataSource;

public class QueryProfiler {
	public static void main(String[] args) throws SQLException {
		String[] queryList = new String[] {
"select * from MESSAGE where ID in (41361, 5949, 48030, 16448, 43516, 81054, 54492, 69762, 18720, 32855, 81078, 32678, 87474, 84263, 31096, 88845, 30085, 67876, 17242, 7321, 59144, 24889, 4525, 21948, 24653, 51261, 3504, 77883, 14693, 10224, 19746, 25246, 92038, 62388, 91603, 75863, 78126, 55654, 17939, 42636, 60961, 72376, 24556, 25731, 41199, 63692, 32075, 39337, 32113, 2063)",
"select MESSAGE_RECIPIENT.MESSAGE_ID as MESSAGE_ID, USER.* from MESSAGE_RECIPIENT, USER where MESSAGE_RECIPIENT.RECIPIENT_ID = USER.ID and MESSAGE_RECIPIENT.MESSAGE_ID in (2063, 3504, 4525, 5949, 7321, 10224, 14693, 16448, 17242, 17939, 18720, 19746, 21948, 24556, 24653, 24889, 25246, 25731, 30085, 31096, 32075, 32113, 32678, 32855, 39337, 41199, 41361, 42636, 43516, 48030, 51261, 54492, 55654, 59144, 60961, 62388, 63692, 67876, 69762, 72376, 75863, 77883, 78126, 81054, 81078, 84263, 87474, 88845, 91603, 92038)",
"select MESSAGE.ID as MESSAGE_ID, USER.* from MESSAGE, USER where MESSAGE.SENDER_ID = USER.ID and MESSAGE.ID in (2063, 3504, 4525, 5949, 7321, 10224, 14693, 16448, 17242, 17939, 18720, 19746, 21948, 24556, 24653, 24889, 25246, 25731, 30085, 31096, 32075, 32113, 32678, 32855, 39337, 41199, 41361, 42636, 43516, 48030, 51261, 54492, 55654, 59144, 60961, 62388, 63692, 67876, 69762, 72376, 75863, 77883, 78126, 81054, 81078, 84263, 87474, 88845, 91603, 92038)",
		};
		BoneCPDataSource ds = null;
		try {
			ds = new BoneCPDataSource();			
			ds.setDriverClass("org.mariadb.jdbc.Driver");
			ds.setDriverClass("com.mysql.jdbc.Driver");
		 	ds.setJdbcUrl("jdbc:mysql://localhost/afluentes");
		 	ds.setUsername("afluentes");
		 	ds.setPassword("afluentes");
		 	ds.setMaxConnectionsPerPartition(100);
//		 	ds.setDefaultReadOnly(true);		 	
//		 	ds.setDefaultTransactionIsolation("READ_UNCOMMITTED");
		 	ds.sanitize();
		 	
			try (Connection c = ds.getConnection();
					Statement s = c.createStatement()) {
				for (int i = 0; i < queryList.length; ++i) {
					String query = queryList[i];
					long t = System.nanoTime();
					int j = 1;
					while (j <= 10000) {
						ResultSet rs = s.executeQuery(query);
						while (rs.next()) {}
						rs.close();
						++j;
					}
					t = System.nanoTime() - t;
					double mean = t; 
					mean /= j;
					mean /= 1000000;
					System.out.println(mean + ";" + query);
				}
			}
		} finally {
			if (ds != null) {
				try {
					ds.close();
				} catch (Exception e) {}
			}
		}		
	}
}