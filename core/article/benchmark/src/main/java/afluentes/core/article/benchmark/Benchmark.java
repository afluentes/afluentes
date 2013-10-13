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

import afluentes.core.api.IEvaluation;
import afluentes.loader.api.ILoader;
import afluentes.loader.impl.LoaderImpl;

import com.google.common.base.Joiner;
import com.jolbox.bonecp.BoneCPDataSource;

class Benchmark {
	void execute() throws SQLException, FileNotFoundException {
		BoneCPDataSource ds = null;
		ExecutorService executor = null;
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
		 	
		 	int maximumMessageId = getMaximumMessageId(ds);
		 	
		 	executor = Executors.newCachedThreadPool();
		 	
		 	Dao dao = new Dao(ds, 0, executor);
		 	
		 	dao.putEntityDescription(MediaTypeImpl.class, new EntityDescription<MediaTypeImpl>() {
				@Override
				MediaTypeImpl getEntity(ResultSet rs) throws SQLException {
					MediaTypeImpl m = new MediaTypeImpl();
					m.id = rs.getInt("ID");
					m.type = rs.getString("TYPE");
					m.subtype = rs.getString("SUBTYPE");
					return m;
				}

				@Override
				Integer getKey(MediaTypeImpl entity) {
					return entity.getId();
				}
			});
		 	
		 	dao.putEntityDescription(FileImpl.class, 
		 		new EntityDescription<FileImpl>() {
					@Override
					FileImpl getEntity(ResultSet rs) throws SQLException {
						FileImpl f = new FileImpl();
						f.id = rs.getInt("ID");
						f.name = rs.getString("NAME");
						return f;
					}
					
					@Override
					Integer getKey(FileImpl entity) {
						return entity.getId();
					}				
		 		}.putRelationshipDescription("mediaType",
					new OneRelationshipDescription<FileImpl, MediaTypeImpl>("FILE_ID", MediaTypeImpl.class) {
						@Override
						void setProxy(FileImpl entity, IEvaluation<MediaTypeImpl> evaluation) {
							MediaTypeProxy proxy = new MediaTypeProxy();
							proxy.evaluation = evaluation;
							entity.mediaType = proxy;
						}

						@Override
						String getQuery(List<Integer> keys) {
							return "select FILE.ID as FILE_ID, MEDIA_TYPE.* from FILE, MEDIA_TYPE where FILE.MEDIA_TYPE_ID = MEDIA_TYPE.ID and FILE.ID in (" + Joiner.on(", ").join(keys) + ")";
						}
					}
				)
			);
		 	
		 	dao.putEntityDescription(UserImpl.class, 
		 		new EntityDescription<UserImpl>() {
					@Override
					UserImpl getEntity(ResultSet rs) throws SQLException {
						UserImpl u = new UserImpl();
						u.id = rs.getInt("ID");
						u.name = rs.getString("NAME");
						return u;
					}
					
					@Override
					Integer getKey(UserImpl entity) {
						return entity.getId();
					}				
			 	}.putRelationshipDescription("picture", 
					new OneRelationshipDescription<UserImpl, FileImpl>("USER_ID", FileImpl.class) {
			 			@Override
						void setProxy(UserImpl entity, IEvaluation<FileImpl> evaluation) {
							FileProxy proxy = new FileProxy();
							proxy.evaluation = evaluation;
							entity.picture = proxy;
						}

						@Override
						String getQuery(List<Integer> keys) {
							return "select USER.ID as USER_ID, FILE.* from USER, FILE where USER.PICTURE_ID = FILE.ID and USER.ID in (" + Joiner.on(", ").join(keys) + ")";							
						}
					}
				)
		 	);
		 	
		 	dao.putEntityDescription(MessageImpl.class, 
		 		new EntityDescription<MessageImpl>() {
			 		@Override
			 		MessageImpl getEntity(ResultSet rs) throws SQLException {
						MessageImpl m = new MessageImpl();
						m.id = rs.getInt("ID");
						m.subject = rs.getString("SUBJECT");
						m.body = rs.getString("BODY");
						return m;
					}
	
					@Override
					Integer getKey(MessageImpl entity) {
						return entity.getId();
					}		 		
				}.putRelationshipDescription("sender", 
					new OneRelationshipDescription<MessageImpl, UserImpl>("MESSAGE_ID", UserImpl.class) {
						@Override
						void setProxy(MessageImpl entity, IEvaluation<UserImpl> evaluation) {
							UserProxy proxy = new UserProxy();
							proxy.evaluation = evaluation;
							entity.sender = proxy;							
						}

						@Override
						String getQuery(List<Integer> keys) {
							return "select MESSAGE.ID as MESSAGE_ID, USER.* from MESSAGE, USER where MESSAGE.SENDER_ID = USER.ID and MESSAGE.ID in (" + Joiner.on(", ").join(keys) + ")";
						}
					}
				).putRelationshipDescription("recipients", 
					new ManyRelationshipDescription<MessageImpl, UserImpl>("MESSAGE_ID", UserImpl.class) {
						@Override
						void setProxy(MessageImpl entity, IEvaluation<List<UserImpl>> evaluation) {
							ListProxy<UserImpl> proxy = new ListProxy<>();
							proxy.evaluation = evaluation;
							entity.recipients = proxy;
						}

						@Override
						String getQuery(List<Integer> keys) {
							return "select MESSAGE_RECIPIENT.MESSAGE_ID as MESSAGE_ID, USER.* from MESSAGE_RECIPIENT, USER where MESSAGE_RECIPIENT.RECIPIENT_ID = USER.ID and MESSAGE_RECIPIENT.MESSAGE_ID in (" + Joiner.on(", ").join(keys) + ")";
						}
					}
				).putRelationshipDescription("files", 
					new ManyRelationshipDescription<MessageImpl, FileImpl>("MESSAGE_ID", FileImpl.class) {
						@Override
						void setProxy(MessageImpl entity, IEvaluation<List<FileImpl>> evaluation) {
							ListProxy<FileImpl> proxy = new ListProxy<>();
							proxy.evaluation = evaluation;
							entity.files = proxy;							
						}

						@Override
						String getQuery(List<Integer> keys) {
							return "select MESSAGE_FILE.MESSAGE_ID as MESSAGE_ID, FILE.* from MESSAGE_FILE, FILE where MESSAGE_FILE.FILE_ID = FILE.ID and MESSAGE_FILE.MESSAGE_ID in (" + Joiner.on(", ").join(keys) + ")";
						}
					}
				)
		 	);
		 	
		 	execute(maximumMessageId, dao);		 	
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

	void execute(int maximumMessageId, Dao dao) {
		ILoader<List<MessageImpl>> loader = new LoaderImpl<List<MessageImpl>>(".{sender.picture.mediaType, recipients.picture.mediaType, files.mediaType}") {};
//		ILoader<List<MessageImpl>> loader = new LoaderImpl<List<MessageImpl>>(".{sender.picture.mediaType, recipients, files.mediaType}") {};
//		ILoader<List<MessageImpl>> loader = new LoaderImpl<List<MessageImpl>>(".{sender.picture.mediaType, recipients}") {};
//		ILoader<List<MessageImpl>> loader = new LoaderImpl<List<MessageImpl>>(".{sender, recipients}") {};
		
		Marshaller marshaller = new Marshaller();
		
		int outerIterationCount = 10;
		int innerIterationCount = 1000;
		int messageCount = 50;
		for (int i = 0; i < outerIterationCount; ++i) {
			long totalTime = 0;
			Random messageIdRandom = new Random(0);
			for (int j = 0; j < innerIterationCount; ++j) {
				List<Integer> messageIds = new ArrayList<>();
				for (int k = 0; k < messageCount; ++k) {
					messageIds.add(messageIdRandom.nextInt(maximumMessageId) + 1);
				}
				String query = "select * from MESSAGE where ID in (" + Joiner.on(", ").join(messageIds) + ")";
				
				long time = System.nanoTime();
			 	List<MessageImpl> messages = dao.getEntityList(MessageImpl.class, query);			 	
			 	loader.load(messages);
			 	//System.out.println(marshaller.marshallMessages(messages));
				
		 		time = System.nanoTime() - time;
		 		totalTime += time;
			}
			double mean = totalTime;
			mean /= innerIterationCount;
			mean /= 1000000;
			System.out.println(mean);
		}		
	}
		
	public static void main(String args[]) throws FileNotFoundException, SQLException {
		new Benchmark().execute();
	}
}