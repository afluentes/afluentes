package afluentes.middleware.ws.rs.client;

import javax.ws.rs.client.InvocationCallback;

import afluentes.core.api.ICallback;

public class ICallbackAdapter<Y> implements InvocationCallback<Y> {
	private final ICallback<Y> callback;

	public ICallbackAdapter(final ICallback<Y> callback) {
		if (callback == null) {
			throw new IllegalArgumentException("callback == null");
		}
		this.callback = callback;
	}

	@Override
	public void completed(Y response) {
		callback.y(response);
	}

	@Override
	public void failed(Throwable throwable) {
		callback.t(throwable);
	}
}