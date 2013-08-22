package afluentes.core.article.benchmark;

import java.util.ArrayList;
import java.util.List;

import afluentes.core.api.IEvaluation;
import afluentes.core.api.IEvaluator3;
import afluentes.core.api.IReduce;
import afluentes.core.api.IReduceEvaluator;
import afluentes.core.api.ISyncFn1;
import afluentes.core.api.ISyncFn3;
import afluentes.core.impl.Constant;
import afluentes.core.impl.ReduceEvaluator;
import afluentes.core.impl.SynchronousEvaluator3;

class FunctionalLoader implements ILoader {
  @Override
  public void loadMessages(List<Message> messages) {
    List<IEvaluation<Message>> evaluations = new ArrayList<>(messages.size());
    for (Message message : messages) {
      FunctionalSenderProxy senderProxy = (FunctionalSenderProxy) message.sender;
      FunctionalRecipientsProxy recipientsProxy = (FunctionalRecipientsProxy) message.recipients;
      IEvaluation<Message> evaluation = loadMessage.y(new Constant<>(message), senderProxy.evaluation, recipientsProxy.evaluation); 
      evaluations.add(evaluation);
    }
    loadMessages.y(evaluations).y();
  }
	
  IEvaluator3<Message, IUser, List<IUser>, Message> loadMessage = new SynchronousEvaluator3<>(new ISyncFn3<Message, IUser, List<IUser>, Message>() {
    @Override
    public Message y(Message message, IUser sender, List<IUser> recipients) {
      return message;
    }
  });

  IReduceEvaluator<Message, List<Message>> loadMessages = new ReduceEvaluator<>(new IReduce<Message, List<Message>>() {
    @Override
    public List<Message> y(List<Message> messages) {
      return messages;
    }
  });	
}

class Loader {
  void loadMessages(List<Message> messages) {
    List<IEvaluation<Message>> evaluations = new ArrayList<>(messages.size());
    for (Message message : messages) {
      FunctionalSenderProxy senderProxy = (FunctionalSenderProxy) message.sender;
      FunctionalRecipientsProxy recipientsProxy = (FunctionalRecipientsProxy) message.recipients;
      IEvaluation<Message> evaluation = loadMessageFn.y(new Constant<>(message), senderProxy.evaluation,  
                                                        recipientsProxy.evaluation); 
      evaluations.add(evaluation);
    }
    loadMessagesFn.y(evaluations).y();
  }
	
  IEvaluator3<Message, IUser, List<IUser>, Message> loadMessageFn = 
    new SynchronousEvaluator3<>(new ISyncFn3<Message, IUser, List<IUser>, Message>() {
      @Override
      public Message y(Message message, IUser sender, List<IUser> recipients) {
        return message;
      }
    });

  IReduceEvaluator<Message, List<Message>> loadMessagesFn = 
    new ReduceEvaluator<>(new ISyncFn1<List<Message>, List<Message>>() {
      @Override
      public List<Message> y(List<Message> messages) {
        return messages;
      }
    });	
}