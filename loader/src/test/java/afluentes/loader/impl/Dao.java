package afluentes.loader.impl;

import java.util.ArrayList;
import java.util.List;

import afluentes.core.api.IAsynchronousFunction0;
import afluentes.core.api.IAsynchronousFunction1;
import afluentes.core.api.ICallback;
import afluentes.core.api.IEvaluator0;
import afluentes.core.api.IEvaluator1;
import afluentes.core.impl.AsynchronousEvaluator0;
import afluentes.core.impl.AsynchronousEvaluator1;
import afluentes.core.impl.Constant;

class Dao {
	IEvaluator0<List<IMessage>> getMessages = new AsynchronousEvaluator0<>(new IAsynchronousFunction0<List<IMessage>>() {
		@Override
		public void y(final ICallback<List<IMessage>> callback) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					System.out.println("getMessages()");
					
					UserProxy sender = new UserProxy();
					sender.evaluation = getUser.y(new Constant<>(1));
					
					ListProxy<IUser> recipients = new ListProxy<>();
					recipients.evaluation = getRecipients.y(new Constant<>(1));
					
					ListProxy<IFile> files = new ListProxy<>();
					files.evaluation = getFiles.y(new Constant<>(1));					

					MessageImpl message = new MessageImpl();
					message.identifier = 1;
					message.sender = sender;
					message.recipients = recipients;
					message.files = files;

					List<IMessage> messages = new ArrayList<>();
					messages.add(message);

					callback.y(messages);
				}
			}).start();
		}
	});
	
	IEvaluator1<Integer, IUser> getUser = new AsynchronousEvaluator1<>(new IAsynchronousFunction1<Integer, IUser>() {
		@Override
		public void y(final Integer userId, final ICallback<IUser> callback) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					System.out.println("getUser(" + userId + ")");
					
					UserImpl user = new UserImpl();
					user.identifier = userId;

					callback.y(user);
				}
			}).start();
		}
	});

	IEvaluator1<Integer, List<IUser>> getRecipients = new AsynchronousEvaluator1<>(new IAsynchronousFunction1<Integer, List<IUser>>() {
		@Override
		public void y(final Integer messageId, final ICallback<List<IUser>> callback) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					System.out.println("getRecipients(" + messageId + ")");
					
					PictureProxy picture = new PictureProxy();
					picture.evaluation = getPicture.y(new Constant<Integer>(1));

					UserImpl recipient = new UserImpl();
					recipient.identifier = 1;
					recipient.picture = picture;

					List<IUser> recipients = new ArrayList<>();
					recipients.add(recipient);

					callback.y(recipients);
				}
			}).start();
		}
	});	

	IEvaluator1<Integer, IPicture> getPicture = new AsynchronousEvaluator1<>(new IAsynchronousFunction1<Integer, IPicture>() {
		@Override
		public void y(final Integer pictureId, final ICallback<IPicture> callback) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					System.out.println("getPicture(" + pictureId + ")");
					
					PictureImpl picture = new PictureImpl();
					picture.identifier = pictureId;

					callback.y(picture);
				}
			}).start();
		}
	});

	IEvaluator1<Integer, List<IFile>> getFiles = new AsynchronousEvaluator1<>(new IAsynchronousFunction1<Integer, List<IFile>>() {
		@Override
		public void y(final Integer messageId, final ICallback<List<IFile>> callback) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					System.out.println("getFiles(" + messageId + ")");
					
					MediaTypeProxy mediaType = new MediaTypeProxy();
					mediaType.evaluation = getMediaType.y(new Constant<Integer>(1));

					FileImpl file = new FileImpl();
					file.identifier = 1;
					file.mediaType = mediaType;

					List<IFile> files = new ArrayList<>();
					files.add(file);

					callback.y(files);
				}
			}).start();
		}
	});

	IEvaluator1<Integer, IMediaType> getMediaType = new AsynchronousEvaluator1<>(new IAsynchronousFunction1<Integer, IMediaType>() {
		@Override
		public void y(final Integer mediaTypeId, final ICallback<IMediaType> callback) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					System.out.println("getMediaType(" + mediaTypeId + ")");
					
					MediaTypeImpl mediaType = new MediaTypeImpl();
					mediaType.identifier = mediaTypeId;

					callback.y(mediaType);
				}
			}).start();
		}
	});	
}