package afluentes.core.article.benchmark;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicReference;

import afluentes.core.api.ICallback;

class CallbackLoader implements ILoader {
	AfluentesDao dao;

	CallbackLoader(AfluentesDao dao) {
		this.dao = dao;
	}

	@Override
	public synchronized void loadMessages(List<Message> messages) {
		final AtomicReference<Throwable> tReference = new AtomicReference<>();
		final CountDownLatch latch = new CountDownLatch(2 * messages.size());		
		for (final Message message : messages) {
			final CallbackSenderProxy senderProxy = (CallbackSenderProxy) message.sender;
			final CallbackRecipientsProxy recipientsProxy = (CallbackRecipientsProxy) message.recipients;
			dao.getUserFn.y(senderProxy.senderId, new ICallback<IUser>() {
				@Override
				public void y(IUser sender) {
					senderProxy.sender = sender;
					latch.countDown();					
				}

				@Override
				public void t(Throwable t) {
					tReference.compareAndSet(null, t);
					latch.countDown();
				}
			});
			
			dao.getMessageRecipientListFn.y(recipientsProxy.messageId, new ICallback<List<IUser>>() {
				@Override
				public void y(List<IUser> recipients) {
					recipientsProxy.recipients = recipients;
					latch.countDown();
				}

				@Override
				public void t(Throwable t) {
					tReference.compareAndSet(null, t);
					latch.countDown();
				}
			});
		}
		try {
			latch.await();
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
		Throwable t = tReference.get();
		if (t != null) {
			throw new RuntimeException(t);
		}
	}		
}