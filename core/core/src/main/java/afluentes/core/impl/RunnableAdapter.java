package afluentes.core.impl;

import afluentes.core.api.ISynchronousFunction0;

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