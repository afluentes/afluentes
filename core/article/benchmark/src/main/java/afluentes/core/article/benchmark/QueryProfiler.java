package afluentes.core.article.benchmark;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.jolbox.bonecp.BoneCPDataSource;

public class QueryProfiler {
	public static void main(String[] args) throws SQLException {
		String[] queryList = new String[] {
			"select ID, SUBJECT, BODY, SENDER_ID from MESSAGE where SENDER_ID = 101", 
			"select USER.*, MESSAGE_RECIPIENT.MESSAGE_ID from USER, MESSAGE_RECIPIENT where USER.ID = MESSAGE_RECIPIENT.RECIPIENT_ID and MESSAGE_RECIPIENT.MESSAGE_ID in (4951, 4952, 4953, 4954, 4955, 4956, 4957, 4958, 4959, 4960, 4961, 4962, 4963, 4964, 4965, 4966, 4967, 4968, 4969, 4970, 4971, 4972, 4973, 4974, 4975, 4976, 4977, 4978, 4979, 4980, 4981, 4982, 4983, 4984, 4985, 4986, 4987, 4988, 4989, 4990, 4991, 4992, 4993, 4994, 4995, 4996, 4997, 4998, 4999, 5000, 5001, 5002, 5003, 5004, 5005, 5006, 5007, 5008, 5009, 5010, 5011, 5012, 5013, 5014, 5015, 5016, 5017, 5018, 5019, 5020, 5021, 5022, 5023, 5024, 5025, 5026, 5027, 5028, 5029, 5030, 5031, 5032, 5033, 5034, 5035, 5036, 5037, 5038, 5039, 5040, 5041, 5042, 5043, 5044, 5045, 5046, 5047, 5048, 5049, 5050)",
			"select * from USER where ID in (101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101)",
			"select ID, SUBJECT, BODY, SENDER_ID from MESSAGE where SENDER_ID = 101",
			"select * from USER where ID in (101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101)",
			"select USER.*, MESSAGE_RECIPIENT.MESSAGE_ID from USER, MESSAGE_RECIPIENT where USER.ID = MESSAGE_RECIPIENT.RECIPIENT_ID and MESSAGE_RECIPIENT.MESSAGE_ID in (5001, 5002, 5003, 5004, 5005, 5006, 5007, 5008, 5009, 5010, 5011, 5012, 5013, 5014, 5015, 5016, 5017, 5018, 5019, 5020, 5021, 5022, 5023, 5024, 5025, 5026, 5027, 5028, 5029, 5030, 5031, 5032, 5033, 5034, 5035, 5036, 5037, 5038, 5039, 5040, 5041, 5042, 5043, 5044, 5045, 5046, 5047, 5048, 5049, 5050)",
			"select * from USER where ID in (101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101)",
			"select USER.*, MESSAGE_RECIPIENT.MESSAGE_ID from USER, MESSAGE_RECIPIENT where USER.ID = MESSAGE_RECIPIENT.RECIPIENT_ID and MESSAGE_RECIPIENT.MESSAGE_ID in (4951, 4952, 4953, 4954, 4955, 4956, 4957, 4958, 4959, 4960, 4961, 4962, 4963, 4964, 4965, 4966, 4967, 4968, 4969, 4970, 4971, 4972, 4973, 4974, 4975, 4976, 4977, 4978, 4979, 4980, 4981, 4982, 4983, 4984, 4985, 4986, 4987, 4988, 4989, 4990, 4991, 4992, 4993, 4994, 4995, 4996, 4997, 4998, 4999, 5000)"				
		};
		BoneCPDataSource ds = null;
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
		 	
			try (Connection c = ds.getConnection();
					Statement s = c.createStatement()) {
				for (int i = 0; i < queryList.length; ++i) {
					String query = queryList[i];
					long t = System.nanoTime();
					int j = 1;
					while (j <= 1000) {
						ResultSet rs = s.executeQuery(query);
						rs.close();
						++j;
					}
					t = System.nanoTime() - t;
					t /= 1000000;
					System.out.println(t + ";" + query);
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