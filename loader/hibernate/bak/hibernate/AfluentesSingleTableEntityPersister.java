package afluentes.loader.hibernate;

import org.hibernate.HibernateException;
import org.hibernate.cache.spi.access.EntityRegionAccessStrategy;
import org.hibernate.cache.spi.access.NaturalIdRegionAccessStrategy;
import org.hibernate.engine.spi.Mapping;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.mapping.PersistentClass;
import org.hibernate.metamodel.binding.EntityBinding;
import org.hibernate.persister.entity.SingleTableEntityPersister;
import org.hibernate.type.BagType;
import org.hibernate.type.Type;

public class AfluentesSingleTableEntityPersister extends SingleTableEntityPersister {
	public AfluentesSingleTableEntityPersister(PersistentClass persistentClass,
											   EntityRegionAccessStrategy cacheAccessStrategy,
											   NaturalIdRegionAccessStrategy naturalIdRegionAccessStrategy,
											   SessionFactoryImplementor factory, 
											   Mapping mapping) {
		super(persistentClass, cacheAccessStrategy, naturalIdRegionAccessStrategy, factory, mapping);
	}
	
	public AfluentesSingleTableEntityPersister(EntityBinding entityBinding,		
											   EntityRegionAccessStrategy cacheAccessStrategy,
											   NaturalIdRegionAccessStrategy naturalIdRegionAccessStrategy,
											   SessionFactoryImplementor factory,	
											   Mapping mapping) throws HibernateException {
		super(entityBinding, cacheAccessStrategy, naturalIdRegionAccessStrategy, factory, mapping);
	}	

	@Override
	public Type[] getPropertyTypes() {
		Type[] types = super.getPropertyTypes();
		for (int i = 0; i < types.length; ++i) {
			if (types[i] instanceof BagType) {
				BagType bagType = (BagType) types[i];
				types[i] = new AfluentesBagType(bagType);
			}
		}
		return types; 
	}
}