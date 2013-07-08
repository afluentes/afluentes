package afluentes.core.impl;

import afluentes.core.api.IAsynchronousFunction0;
import afluentes.core.api.IAsynchronousRunnable;
import afluentes.core.api.ICallback;
import afluentes.core.api.ISynchronousFunction0;

class IAsynchronousRunnableAdapter implements IAsynchronousFunction0<Void> {
	private final IAsynchronousRunnable runnable;

	IAsynchronousRunnableAdapter(final IAsynchronousRunnable runnable) {
		this.runnable = runnable;
	}

	@Override
	public void y(ICallback<Void> callback) {
		runnable.run(callback);
	}
}

class RunnableAdapter implements ISynchronousFunction0<Void> {
	private final Runnable runnable;
	
	RunnableAdapter(final Runnable runnable) {
		this.runnable = runnable;
	}

	@Override
	public Void y() {
		runnable.run();
		return null;
	}
}