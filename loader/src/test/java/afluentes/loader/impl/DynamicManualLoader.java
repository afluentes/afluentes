package afluentes.loader.impl;

import java.util.ArrayList;
import java.util.List;

import afluentes.core.api.IEvaluation;
import afluentes.core.api.IEvaluator1;
import afluentes.core.api.IReduce;
import afluentes.core.api.IReduceFlatEvaluator;
import afluentes.core.api.ISynchronousFunction1;
import afluentes.core.impl.Constant;
import afluentes.core.impl.ReduceFlatEvaluator;
import afluentes.core.impl.SynchronousEvaluator1;
import afluentes.loader.api.IEvaluationHolder;
import afluentes.loader.api.ILoader;

class DymanicManualLoader implements ILoader<List<IMessage>> {
	@Override
	public void load(IEvaluation<List<IMessage>> root) {
		reduceMessages.y(mapMessages.y(root)).y();
	}

/* ------------------------------------------------------------------------- */	
	IReduceFlatEvaluator<IMessage, List<IMessage>> reduceMessages = reduceParents();

	IEvaluator1<List<IMessage>, List<IEvaluation<? extends IMessage>>> mapMessages = 
		new SynchronousEvaluator1<>(new ISynchronousFunction1<List<IMessage>, List<IEvaluation<? extends IMessage>>>() {
			@Override
			public List<IEvaluation<? extends IMessage>> y(List<IMessage> messages) {
				List<IEvaluation<? extends IMessage>> evaluations = new ArrayList<>();
				for (IMessage message : messages) {
					evaluations.add(reduceMessage.y(new Constant<>(getMessageEvaluations.y(message))));
				}
				return evaluations;
			}
		});

	ISynchronousFunction1<IMessage, List<IEvaluation<?>>> getMessageEvaluations = 
		new ISynchronousFunction1<IMessage, List<IEvaluation<?>>>() {
			@Override
			public List<IEvaluation<?>> y(IMessage x1) {
				List<IEvaluation<?>> evaluations = new ArrayList<>();

				MessageImpl message = (MessageImpl) x1;
				evaluations.add(new Constant<>(message));
				
				IEvaluationHolder<IUser> sender = (IEvaluationHolder<IUser>) message.getSender();
				IEvaluation<List<IEvaluation<?>>> evals = new SynchronousEvaluator1<>(getUserEvaluations).y(sender.getEvaluation());
				reduceUser.y(evals);
				evaluations.add(sender.getEvaluation());
				
				IEvaluationHolder<List<IUser>> recipients = (IEvaluationHolder<List<IUser>>) message.getRecipients();
				evaluations.add(reduceUsers.y(mapUsers.y(recipients.getEvaluation())));

				IEvaluationHolder<List<IFile>> files = (IEvaluationHolder<List<IFile>>) message.getFiles();				
				evaluations.add(reduceFiles.y(mapFiles.y(files.getEvaluation())));

				return evaluations;
			}		
		};
		
	IReduceFlatEvaluator<Object, IMessage> reduceMessage = reduceChild();
/* ------------------------------------------------------------------------- */
		
		
		
/* ------------------------------------------------------------------------- */				
	IReduceFlatEvaluator<IUser, List<IUser>> reduceUsers = reduceParents();

	IEvaluator1<List<IUser>, List<IEvaluation<? extends IUser>>> mapUsers = 
		new SynchronousEvaluator1<>(new ISynchronousFunction1<List<IUser>, List<IEvaluation<? extends IUser>>>() {
			@Override
			public List<IEvaluation<? extends IUser>> y(List<IUser> users) {
				List<IEvaluation<? extends IUser>> evaluations = new ArrayList<>();
				for (IUser user : users) {
					evaluations.add(reduceUser.y(new Constant<>(getUserEvaluations.y(user))));
				}
				return evaluations;
			}
		});

	ISynchronousFunction1<IUser, List<IEvaluation<?>>> getUserEvaluations = 
		new ISynchronousFunction1<IUser, List<IEvaluation<?>>>() {
			@Override
			public List<IEvaluation<?>> y(IUser x1) {
				List<IEvaluation<?>> evaluations = new ArrayList<>();

				UserImpl userImpl = (UserImpl) x1;
				evaluations.add(new Constant<>(x1));

				IEvaluationHolder<IPicture> picture = (IEvaluationHolder<IPicture>) userImpl.getPicture();
				evaluations.add(picture.getEvaluation());

				return evaluations;
			}		
		};

	IReduceFlatEvaluator<Object, IUser> reduceUser = reduceChild();
/* ------------------------------------------------------------------------- */		
		

		
/* ------------------------------------------------------------------------- */		
	IReduceFlatEvaluator<IFile, List<IFile>> reduceFiles = reduceParents();
		
	IEvaluator1<List<IFile>, List<IEvaluation<? extends IFile>>> mapFiles = 
		new SynchronousEvaluator1<>(new ISynchronousFunction1<List<IFile>, List<IEvaluation<? extends IFile>>>() {
			@Override
			public List<IEvaluation<? extends IFile>> y(List<IFile> files) {
				List<IEvaluation<? extends IFile>> evaluations = new ArrayList<>();
				for (IFile file : files) {
					evaluations.add(reduceFile.y(new Constant<>(getFileEvaluations.y(file))));
				}
				return evaluations;
			}
		});
		
	ISynchronousFunction1<IFile, List<IEvaluation<?>>> getFileEvaluations = 
		new ISynchronousFunction1<IFile, List<IEvaluation<?>>>() {
			@Override
			public List<IEvaluation<?>> y(IFile x1) {
				List<IEvaluation<?>> evaluations = new ArrayList<>();

				FileImpl fileImpl = (FileImpl) x1;
				evaluations.add(new Constant<>(fileImpl));
				
				IEvaluationHolder<IMediaType> mediaType = (IEvaluationHolder<IMediaType>) fileImpl.getMediaType();					
				evaluations.add(mediaType.getEvaluation());

				return evaluations;
			}		
		};		

	IReduceFlatEvaluator<Object, IFile> reduceFile = reduceChild();		
/* ------------------------------------------------------------------------- */

	private <Parent> IReduceFlatEvaluator<Parent, List<Parent>> reduceParents() {
		return new ReduceFlatEvaluator<>(new IReduce<Parent, List<Parent>>() {
			@Override
			public List<Parent> y(List<Parent> parents) {
				return parents;
			}
		});
	}
			
	private <Child> IReduceFlatEvaluator<Object, Child> reduceChild() {
		return new ReduceFlatEvaluator<>(new IReduce<Object, Child>() {
			@Override
			public Child y(List<Object> x1) {
				return (Child) x1.get(0);
			}
		});		
	}			
}