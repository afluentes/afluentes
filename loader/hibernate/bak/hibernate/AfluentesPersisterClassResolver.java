package afluentes.loader.hibernate;

import org.hibernate.mapping.Collection;
import org.hibernate.mapping.PersistentClass;
import org.hibernate.metamodel.binding.EntityBinding;
import org.hibernate.metamodel.binding.PluralAttributeBinding;
import org.hibernate.persister.collection.CollectionPersister;
import org.hibernate.persister.entity.EntityPersister;
import org.hibernate.persister.entity.SingleTableEntityPersister;
import org.hibernate.persister.internal.StandardPersisterClassResolver;
import org.hibernate.persister.spi.PersisterClassResolver;

public class AfluentesPersisterClassResolver implements PersisterClassResolver {
	private static final long serialVersionUID = -8406684376008098095L;
	
	private PersisterClassResolver resolver;
	
	public AfluentesPersisterClassResolver() {
		resolver = new StandardPersisterClassResolver();
	}
	
	@Override
	public Class<? extends EntityPersister> getEntityPersisterClass(PersistentClass metadata) {
		Class<? extends EntityPersister> persister = resolver.getEntityPersisterClass(metadata);
		System.out.println("getEntityPersisterClass(" + metadata + ") = " + persister);
		if (SingleTableEntityPersister.class.equals(persister)) {
			return AfluentesSingleTableEntityPersister.class;	
		} else {
			return persister;
		}
	}

	@Override
	public Class<? extends EntityPersister> getEntityPersisterClass(EntityBinding metadata) {
		Class<? extends EntityPersister> persister = resolver.getEntityPersisterClass(metadata);
		System.out.println("getEntityPersisterClass(" + metadata + ") = " + persister);
		if (SingleTableEntityPersister.class.equals(persister)) {
			return AfluentesSingleTableEntityPersister.class;	
		} else {
			return persister;
		}
	}

	@Override
	public Class<? extends CollectionPersister> getCollectionPersisterClass(Collection metadata) {
		Class<? extends CollectionPersister> persister = resolver.getCollectionPersisterClass(metadata);
		System.out.println("getCollectionPersisterClass(" + metadata + ") = " + persister);		
		return persister;
	}

	@Override
	public Class<? extends CollectionPersister> getCollectionPersisterClass(PluralAttributeBinding metadata) {
		Class<? extends CollectionPersister> persister = resolver.getCollectionPersisterClass(metadata);
		System.out.println("getCollectionPersisterClass(" + metadata + ") = " + persister);		
		return persister;
	}
}