package afluentes.loader.hibernate;

import java.util.Collection;

import org.hibernate.collection.internal.PersistentBag;
import org.hibernate.engine.spi.SessionImplementor;

public class AfluentesPersistentBag extends PersistentBag {
	private static final long serialVersionUID = 3261952807792984839L;

	public AfluentesPersistentBag(SessionImplementor session) {
		super(session);
	}

	public AfluentesPersistentBag(SessionImplementor session, Collection coll) {
		super(session, coll);
	}
	
	public AfluentesPersistentBag() {}	
}