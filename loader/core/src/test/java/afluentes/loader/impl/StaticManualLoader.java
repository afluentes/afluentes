package afluentes.loader.impl;

import java.util.ArrayList;
import java.util.List;

import afluentes.core.api.IEvaluation;
import afluentes.core.api.IEvaluator1;
import afluentes.core.api.IEvaluator4;
import afluentes.core.api.IReduce;
import afluentes.core.api.ISynchronousFunction1;
import afluentes.core.api.ISynchronousFunction4;
import afluentes.core.impl.Constant;
import afluentes.core.impl.ReduceEvaluator;
import afluentes.core.impl.SynchronousEvaluator1;
import afluentes.core.impl.SynchronousEvaluator4;
import afluentes.loader.api.IEvaluationHolder;
import afluentes.loader.api.ILoader;

class StaticManualLoader implements ILoader<List<IMessage>> {
	@Override
	public void load(IEvaluation<List<IMessage>> root) {
		reduceMessages.y(mapMessages.y(root)).y().y();
	}
								
	IEvaluator1<List<IEvaluation<? extends IMessage>>, IEvaluation<List<IMessage>>> reduceMessages = 
		new SynchronousEvaluator1<>(new ReduceEvaluator<>(new IReduce<IMessage, List<IMessage>>() {
			@Override
			public List<IMessage> y(List<IMessage> messages) {
				return messages;
			}
		})); 

	IEvaluator1<List<IMessage>, List<IEvaluation<? extends IMessage>>> mapMessages = 
		new SynchronousEvaluator1<>(new ISynchronousFunction1<List<IMessage>, List<IEvaluation<? extends IMessage>>>() {
			@Override
			public List<IEvaluation<? extends IMessage>> y(List<IMessage> messages) {
				List<IEvaluation<? extends IMessage>> evaluations = new ArrayList<>();
				for (IMessage message : messages) {
					MessageImpl messageImpl = (MessageImpl) message;
					IEvaluationHolder<IUser> sender = (IEvaluationHolder<IUser>) messageImpl.getSender();					
					IEvaluationHolder<List<IUser>> recipients = (IEvaluationHolder<List<IUser>>) messageImpl.getRecipients();					
					IEvaluationHolder<List<IFile>> files = (IEvaluationHolder<List<IFile>>) messageImpl.getFiles();
					
					IEvaluation<IMessage> evaluation = loadMessage.y(
						new Constant<IMessage>(message),
						sender.getEvaluation(),
						recipients.getEvaluation(),
						files.getEvaluation()
					);
					evaluations.add(evaluation);
				}
				return evaluations;
			}
		});

	IEvaluator4<IMessage, IUser, List<IUser>, List<IFile>, IMessage> loadMessage =
		new SynchronousEvaluator4<>(new ISynchronousFunction4<IMessage, IUser, List<IUser>, List<IFile>, IMessage>() {
			@Override
			public IMessage y(IMessage x1, IUser x2, List<IUser> x3, List<IFile> x4) {
				return x1;
			}
		});

	@Override
	public void load(List<IMessage> root) {
		load(new Constant<>(root));
	}
}