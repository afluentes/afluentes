package afluentes.loader.impl;

import java.util.ArrayList;
import java.util.List;

import afluentes.core.api.IEvaluation;
import afluentes.core.api.IEvaluator1;
import afluentes.core.api.IReduce;
import afluentes.core.api.IReduceEvaluator;
import afluentes.core.api.IReduceFlatEvaluator;
import afluentes.core.api.ISynchronousFunction1;
import afluentes.core.impl.Constant;
import afluentes.core.impl.ReduceEvaluator;
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
	IReduceFlatEvaluator<IMessage, List<IMessage>> reduceMessages = reduceNodes();
	
	IReduce<Object, IMessage> reduceMessageFunction = reduceNode();
	IReduceEvaluator<Object, IMessage> reduceMessageEvaluator = new ReduceEvaluator<>(reduceMessageFunction);
	IReduceFlatEvaluator<Object, IMessage> reduceMessageFlatEvaluator = new ReduceFlatEvaluator<>(reduceMessageFunction);

	ISynchronousFunction1<IMessage, List<IEvaluation<?>>> getMessageEvaluationsFunction = 
		new ISynchronousFunction1<IMessage, List<IEvaluation<?>>>() {
			@Override
			public List<IEvaluation<?>> y(IMessage x1) {
				List<IEvaluation<?>> evaluations = new ArrayList<>();

				MessageImpl message = (MessageImpl) x1;
				evaluations.add(new Constant<>(message));

				IEvaluationHolder<IUser> sender = (IEvaluationHolder<IUser>) message.getSender();
				evaluations.add(reduceUserFlatEvaluator.y(getUserEvaluationsEvaluator.y(sender.getEvaluation())));
				
				IEvaluationHolder<List<IUser>> recipients = (IEvaluationHolder<List<IUser>>) message.getRecipients();
				evaluations.add(reduceUsers.y(mapUsers.y(recipients.getEvaluation())));

				IEvaluationHolder<List<IFile>> files = (IEvaluationHolder<List<IFile>>) message.getFiles();				
				evaluations.add(reduceFiles.y(mapFiles.y(files.getEvaluation())));

				return evaluations;
			}		
		};	
	IEvaluator1<IMessage, List<IEvaluation<?>>> getMessageEvaluationsEvaluator = new SynchronousEvaluator1<>(getMessageEvaluationsFunction);
		
	IEvaluator1<List<IMessage>, List<IEvaluation<? extends IMessage>>> mapMessages = mapNodes(reduceMessageEvaluator, getMessageEvaluationsFunction);	
/* ------------------------------------------------------------------------- */

		
		
/* ------------------------------------------------------------------------- */				
	IReduceFlatEvaluator<IUser, List<IUser>> reduceUsers = reduceNodes();

	IReduce<Object, IUser> reduceUserFunction = reduceNode();
	IReduceEvaluator<Object, IUser> reduceUserEvaluator = new ReduceEvaluator<>(reduceUserFunction);
	IReduceFlatEvaluator<Object, IUser> reduceUserFlatEvaluator = new ReduceFlatEvaluator<>(reduceUserFunction);	

	ISynchronousFunction1<IUser, List<IEvaluation<?>>> getUserEvaluationsFunction = 
		new ISynchronousFunction1<IUser, List<IEvaluation<?>>>() {
			@Override
			public List<IEvaluation<?>> y(IUser x1) {
				List<IEvaluation<?>> evaluations = new ArrayList<>();

				UserImpl userImpl = (UserImpl) x1;
				evaluations.add(new Constant<>(userImpl));

				IEvaluationHolder<IPicture> picture = (IEvaluationHolder<IPicture>) userImpl.getPicture();
				evaluations.add(picture.getEvaluation());

				return evaluations;
			}		
		};		
	IEvaluator1<IUser, List<IEvaluation<?>>> getUserEvaluationsEvaluator = new SynchronousEvaluator1<>(getUserEvaluationsFunction);
	
	IEvaluator1<List<IUser>, List<IEvaluation<? extends IUser>>> mapUsers = mapNodes(reduceUserEvaluator, getUserEvaluationsFunction);	
/* ------------------------------------------------------------------------- */
		

		
/* ------------------------------------------------------------------------- */		
	IReduceFlatEvaluator<IFile, List<IFile>> reduceFiles = reduceNodes();

	IReduce<Object, IFile> reduceFileFunction = reduceNode();
	IReduceEvaluator<Object, IFile> reduceFileEvaluator = new ReduceEvaluator<>(reduceFileFunction);
	IReduceFlatEvaluator<Object, IFile> reduceFileFlatEvaluator = new ReduceFlatEvaluator<>(reduceFileFunction);	
				
	ISynchronousFunction1<IFile, List<IEvaluation<?>>> getFileEvaluationsFunction = 
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
	IEvaluator1<IFile, List<IEvaluation<?>>> getFileEvaluationsEvaluator = new SynchronousEvaluator1<>(getFileEvaluationsFunction);
	
	IEvaluator1<List<IFile>, List<IEvaluation<? extends IFile>>> mapFiles = mapNodes(reduceFileEvaluator, getFileEvaluationsFunction);	
/* ------------------------------------------------------------------------- */

	private <Node> IEvaluator1<List<Node>, List<IEvaluation<? extends Node>>> mapNodes(
			final IReduceEvaluator<Object, Node> reduceNodeEvaluator, 
			final ISynchronousFunction1<Node, List<IEvaluation<?>>> getNodeEvaluationsFunction) { 
		return new SynchronousEvaluator1<>(new ISynchronousFunction1<List<Node>, List<IEvaluation<? extends Node>>>() {
			@Override
			public List<IEvaluation<? extends Node>> y(List<Node> parents) {
				List<IEvaluation<? extends Node>> evaluations = new ArrayList<>();
				for (Node parent : parents) {
					evaluations.add(reduceNodeEvaluator.y(getNodeEvaluationsFunction.y(parent)));
				}
				return evaluations;
			}
		});
	}

	private <Node> IReduceFlatEvaluator<Node, List<Node>> reduceNodes() {
		return new ReduceFlatEvaluator<>(new IReduce<Node, List<Node>>() {
			@Override
			public List<Node> y(List<Node> nodes) {
				return nodes;
			}
		});
	}
			
	private <Node> IReduce<Object, Node> reduceNode() {
		return new IReduce<Object, Node>() {
			@Override
			public Node y(List<Object> nodes) {
				return (Node) nodes.get(0);
			}
		};
	}

	@Override
	public void load(List<IMessage> root) {
		load(new Constant<>(root));
	}
}