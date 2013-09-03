package afluentes.loader.impl;

import java.util.ArrayList;
import java.util.List;

import afluentes.core.api.IEvaluation;
import afluentes.core.api.IEvaluator1;
import afluentes.core.api.IReduce;
import afluentes.core.api.IReduceEvaluator;
import afluentes.core.api.ISynchronousFunction1;
import afluentes.core.impl.Constant;
import afluentes.core.impl.ReduceEvaluator;
import afluentes.core.impl.SynchronousEvaluator1;
import afluentes.loader.api.IEvaluationHolder;
import afluentes.loader.api.ILoader;

class DymanicManualLoader implements ILoader<List<IMessage>> {
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

					List<IEvaluation<?>> childEvaluations = new ArrayList<>();
					childEvaluations.add(new Constant<>(messageImpl));
					childEvaluations.add(sender.getEvaluation());
					childEvaluations.add(recipients.getEvaluation());
					childEvaluations.add(files.getEvaluation());

					IEvaluation<IMessage> childEvaluation = loadMessage.y(childEvaluations);
					evaluations.add(childEvaluation);
				}
				return evaluations;
			}
		});
		
	IReduceEvaluator<Object, IMessage> loadMessage = 
		new ReduceEvaluator<>(new IReduce<Object, IMessage>() {
			@Override
			public IMessage y(List<Object> x1) {
				return (IMessage) x1.get(0);
			}
		});	
}