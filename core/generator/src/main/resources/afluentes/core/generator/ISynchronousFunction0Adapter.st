ISynchronousFunction0Adapter() ::=

<<package afluentes.core.impl;

import afluentes.core.api.ISynchronousFunction0;
import afluentes.core.api.ISynchronousFunction1;

class ISynchronousFunction0Adapter<X1> implements ISynchronousFunction1<X1, Void> {
	private final ISynchronousFunction0<Void> f;
	
	ISynchronousFunction0Adapter(final ISynchronousFunction0<Void> f) {
		this.f = f;
	}
		
	@Override
	public Void y(final X1 x1) {
		f.y();
		return null;
	}
}>>