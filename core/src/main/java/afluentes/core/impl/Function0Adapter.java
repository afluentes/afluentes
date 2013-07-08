package afluentes.core.impl;

import afluentes.core.api.IAsynchronousFunction0;
import afluentes.core.api.IAsynchronousFunction1;
import afluentes.core.api.ICallback;
import afluentes.core.api.ISynchronousFunction0;
import afluentes.core.api.ISynchronousFunction1;

class IAsynchronousFunction0Adapter<X1> implements IAsynchronousFunction1<X1, Void> {
	private final IAsynchronousFunction0<Void> f;

	IAsynchronousFunction0Adapter(final IAsynchronousFunction0<Void> f) {
		this.f = f;
	}

	@Override
	public void y(final X1 x1, final ICallback<Void> callback) {
		f.y(callback);
	}
}

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
}