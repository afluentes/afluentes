IAsynchronousRunnableAdapter() ::= 

<<package afluentes.core.impl;

import afluentes.core.api.IAsynchronousFunction0;
import afluentes.core.api.IAsynchronousRunnable;
import afluentes.core.api.ICallback;

class IAsynchronousRunnableAdapter implements IAsynchronousFunction0<Void> {
	private final IAsynchronousRunnable runnable;

	IAsynchronousRunnableAdapter(final IAsynchronousRunnable runnable) {
		this.runnable = runnable;
	}

	@Override
	public void y(ICallback<Void> callback) {
		runnable.run(callback);
	}
}>>