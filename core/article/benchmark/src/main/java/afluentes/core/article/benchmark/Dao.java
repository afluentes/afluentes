package afluentes.core.article.benchmark;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.sql.DataSource;

import afluentes.core.api.IAsynchronousFunction2;
import afluentes.core.api.ICallback;
import afluentes.core.api.IEvaluation;
import afluentes.core.api.IEvaluator2;
import afluentes.core.api.ISynchronousFunction2;
import afluentes.core.impl.AsynchronousEvaluator2;
import afluentes.core.impl.Constant;
import afluentes.core.impl.SynchronousEvaluator2;
import afluentes.loader.api.ILoader;
import afluentes.loader.impl.LoaderImpl;

import com.google.common.base.Joiner;
import com.jolbox.bonecp.BoneCPDataSource;

class Dao {
	static final boolean ASYNC = true;
	static final boolean DEBUG = false;	
	
	DataSource ds;
	int batchSize;
	ExecutorService executor;
	Map<Class<?>, EntityDescription<?>> entityDescriptions;

	Dao(DataSource ds, int batchSize, ExecutorService executor) {
		this.ds = ds;
		this.batchSize = batchSize;
		this.executor = executor;
		this.entityDescriptions = new HashMap<>();
	}

	<Entity> void putEntityDescription(Class<Entity> entityClass, EntityDescription<Entity> entityDescription) {
		entityDescriptions.put(entityClass, entityDescription);
	}

	<Entity> List<Entity> getEntityList(Class<Entity> entityClass, String query) {
		if (DEBUG) {
			System.out.println(query);
		}
		List<Entity> entityList = new ArrayList<>();
		
		EntityDescription<Entity> entityDescription = (EntityDescription<Entity>) entityDescriptions.get(entityClass);		
		try (Connection c = ds.getConnection();
				Statement s = c.createStatement();
				ResultSet rs = s.executeQuery(query)) {			
			while (rs.next()) {
				Entity entity = entityDescription.getEntity(rs);
				entityList.add(entity);
			}									
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}		
		setProxies(entityDescription, entityList);

		return entityList;
	}

	private <Entity> void setProxies(EntityDescription<Entity> entityDescription, List<Entity> entities) {
		if (batchSize < 1) {
			List<Integer> keys = new ArrayList<>();
			for (Entity entity : entities) {
				keys.add(entityDescription.getKey(entity));
			}

			for (RelationshipDescription<Entity, ?> relationshipDescription : entityDescription.relationshipDescriptions.values()) {
				relationshipDescription.setProxies(this, entities, keys);
			}			
		} else {
			List<Entity> batch = new ArrayList<>(); 
			List<Integer> keys = new ArrayList<>();		
			for (Entity entity : entities) {
				batch.add(entity); 
				keys.add(entityDescription.getKey(entity));
				if (batch.size() == batchSize) {
					for (RelationshipDescription<Entity, ?> relationshipDescription : entityDescription.relationshipDescriptions.values()) {
						relationshipDescription.setProxies(this, batch, keys);
					}				
					batch = new ArrayList<>();
					keys = new ArrayList<>();
				}
			}
			if (batch.size() > 0) {
				for (RelationshipDescription<Entity, ?> relationshipDescription : entityDescription.relationshipDescriptions.values()) {
					relationshipDescription.setProxies(this, batch, keys);
				}
			}
		}
	}
	
	<Entity> Map<Integer, Entity> getEntityMap(Class<Entity> entityClass, String query, String keyColumn) {
		if (DEBUG) {
			System.out.println(query);
		}
		Map<Integer, Entity> entityMap = new HashMap<>();

		List<Entity> entityList = new ArrayList<>();
		EntityDescription<Entity> entityDescription = (EntityDescription<Entity>) entityDescriptions.get(entityClass);		
		try (Connection c = ds.getConnection();
				Statement s = c.createStatement();
				ResultSet rs = s.executeQuery(query)) {			
			while (rs.next()) {
				Integer key = rs.getInt(keyColumn);
				Entity entity = entityDescription.getEntity(rs);
				entityMap.put(key, entity);
				entityList.add(entity);
			}									
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		setProxies(entityDescription, entityList);

		return entityMap;
	}

	<Entity> Map<Integer, List<Entity>> getEntityListMap(Class<Entity> entityClass, String query, String keyColumn) {
		if (DEBUG) {
			System.out.println(query);
		}
		Map<Integer, List<Entity>> entityListMap = new HashMap<>();

		List<Entity> entityList = new ArrayList<>();
		EntityDescription<Entity> entityDescription = (EntityDescription<Entity>) entityDescriptions.get(entityClass);		
		try (Connection c = ds.getConnection();
				Statement s = c.createStatement();
				ResultSet rs = s.executeQuery(query)) {			
			while (rs.next()) {
				Integer key = rs.getInt(keyColumn);
				List<Entity> value = entityListMap.get(key); 
				if (value == null) {
					value = new ArrayList<>(); 
					entityListMap.put(key, value);					
				}				
				Entity entity = entityDescription.getEntity(rs);
				value.add(entity);
				entityList.add(entity);
			}									
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		setProxies(entityDescription, entityList);

		return entityListMap;
	}		

	public static void main(String[] args) {
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

		 	List<MessageImpl> messages = dao.getEntityList(MessageImpl.class, "select * from MESSAGE where ID in (1, 2, 3, 4, 5, 6, 7, 8, 9)");

		 	System.out.println("Loading");
		 	ILoader<List<MessageImpl>> loader = new LoaderImpl<List<MessageImpl>>(".{sender.picture.mediaType, recipients.picture.mediaType, files.mediaType}") {};
		 	loader.load(messages);
		 	System.out.println("Loaded");

		 	for (MessageImpl message : messages) {
		 		message.toString();
		 		message.getSender().getPicture().toString();
		 		message.getSender().getPicture().getMediaType().toString();
		 		for (IUser recipient : message.getRecipients()) {
			 		recipient.getPicture().toString();
			 		recipient.getPicture().getMediaType().toString();		 			
		 		}
		 		for (IFile file : message.getFiles()) {
			 		file.toString();
			 		file.getMediaType().toString();		 			
		 		}		 		
		 	}
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
}

abstract class EntityDescription<Entity> {
	Map<String, RelationshipDescription<Entity, ?>> relationshipDescriptions;
	
	EntityDescription() {
		relationshipDescriptions = new HashMap<>();
	}

	EntityDescription<Entity> putRelationshipDescription(String property, RelationshipDescription<Entity, ?> relationshipDescription) {
		relationshipDescriptions.put(property, relationshipDescription);
		return this;
	}

	abstract Entity getEntity(ResultSet rs) throws SQLException;
	
	abstract Integer getKey(Entity entity);	
}

abstract class RelationshipDescription<Entity, Target> {
	IEvaluator2<Map<Integer, Target>, Integer, Target> getTarget;
	IEvaluator2<Dao, List<Integer>, Map<Integer, Target>> getTargetMap;	

	RelationshipDescription() {
		getTarget = new SynchronousEvaluator2<>(new ISynchronousFunction2<Map<Integer, Target>, Integer, Target>() {
			@Override
			public Target y(Map<Integer, Target> targetMap, Integer key) {
				return targetMap.get(key);
			}
		});		
	}
		
	abstract String getQuery(List<Integer> keys);

	void setProxies(Dao dao, List<Entity> entities, List<Integer> keys) {
		IEvaluation<Map<Integer, Target>> targetMap = getTargetMap.y(new Constant<>(dao), new Constant<>(keys));
		for (int i = 0; i < entities.size(); ++i) {
			Entity entity = entities.get(i);
			Integer key = keys.get(i);
			IEvaluation<Target> target = getTarget.y(targetMap, new Constant<Integer>(key)); 
			setProxy(entity, target);			
		}
	}

	abstract void setProxy(Entity entity, IEvaluation<Target> evaluation);
}

abstract class OneRelationshipDescription<Entity, TargetEntity> extends RelationshipDescription<Entity, TargetEntity> {
	OneRelationshipDescription(final String keyColumn, final Class<TargetEntity> targetEntityClass) {
		if (Dao.ASYNC) {
			System.out.println("Async");
			getTargetMap = new AsynchronousEvaluator2<>(new IAsynchronousFunction2<Dao, List<Integer>, Map<Integer, TargetEntity>>() {
				@Override
				public void y(final Dao dao, final List<Integer> keys, final ICallback<Map<Integer, TargetEntity>> callback) {
					dao.executor.execute(new Runnable() {
						@Override
						public void run() {
							try {
								callback.y(dao.getEntityMap(targetEntityClass, getQuery(keys), keyColumn));
							} catch (Throwable t) {
								callback.t(t);
							}						
						}					
					});
				}		
			});						
		} else {
			System.out.println("Sync");
			getTargetMap = new SynchronousEvaluator2<>(new ISynchronousFunction2<Dao, List<Integer>, Map<Integer, TargetEntity>>() {
				@Override
				public Map<Integer, TargetEntity> y(Dao dao, List<Integer> keys) {
					return dao.getEntityMap(targetEntityClass, getQuery(keys), keyColumn);
				}		
			});						
		}						
	}
}

abstract class ManyRelationshipDescription<Entity, TargetEntity> extends RelationshipDescription<Entity, List<TargetEntity>> {
	ManyRelationshipDescription(final String keyColumn, final Class<TargetEntity> targetEntityClass) {
		if (Dao.ASYNC) {			
			System.out.println("Async");
			getTargetMap = new AsynchronousEvaluator2<>(new IAsynchronousFunction2<Dao, List<Integer>, Map<Integer, List<TargetEntity>>>() {
				@Override
				public void y(final Dao dao, final List<Integer> keys, final ICallback<Map<Integer, List<TargetEntity>>> callback) {
					dao.executor.execute(new Runnable() {
						@Override
						public void run() {
							try {
								callback.y(dao.getEntityListMap(targetEntityClass, getQuery(keys), keyColumn));
							} catch (Throwable t) {
								callback.t(t);
							}						
						}					
					});
				}		
			});			
		} else {
			System.out.println("Sync");
			getTargetMap = new SynchronousEvaluator2<>(new ISynchronousFunction2<Dao, List<Integer>, Map<Integer, List<TargetEntity>>>() {
				@Override
				public Map<Integer, List<TargetEntity>> y(Dao dao, List<Integer> keys) {
					return dao.getEntityListMap(targetEntityClass, getQuery(keys), keyColumn);
				}		
			});			
		}
	}	
}