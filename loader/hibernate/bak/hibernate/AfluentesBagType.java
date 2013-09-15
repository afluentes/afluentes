package afluentes.loader.hibernate;

import java.io.Serializable;
import java.util.Collection;

import org.hibernate.collection.spi.PersistentCollection;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.persister.collection.CollectionPersister;
import org.hibernate.type.BagType;

class AfluentesBagType extends BagType {
	private static final long serialVersionUID = 1909015446785742242L;
	
	AfluentesBagType(BagType type) {
		super(type.getTypeScope(), type.getRole(), type.getLHSPropertyName());
	}
	
	@Override
	public PersistentCollection instantiate(SessionImplementor session, CollectionPersister persister, Serializable key) {
		return new AfluentesPersistentBag(session);
	}

	@Override
	public PersistentCollection wrap(SessionImplementor session, Object collection) {
		return new AfluentesPersistentBag(session, (Collection) collection);
	}	
}