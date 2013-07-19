package afluentes.core.impl;

import afluentes.core.api.IAsynchronousFunction0;
import afluentes.core.api.IAsynchronousFunction1;
import afluentes.core.api.ICallback;

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