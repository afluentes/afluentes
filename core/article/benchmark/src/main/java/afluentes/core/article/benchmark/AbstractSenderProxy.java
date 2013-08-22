package afluentes.core.article.benchmark;

import afluentes.core.api.IEvaluation;

abstract class AbstractSenderProxy implements IUser {
	abstract IUser getSender();

	@Override
	public int getId() {
		return getSender().getId();
	}

	@Override
	public String getName() {
		return getSender().getName();
	}	
}

class ImperativeSenderProxy extends AbstractSenderProxy {
	AbstractDao dao;
	int senderId;
	IUser sender;

	ImperativeSenderProxy(AbstractDao dao, int senderId) {
		this.dao = dao;
		this.senderId = senderId;
	}

	@Override
	IUser getSender() {
		if (sender == null) {
			sender = dao.getUser(senderId);
		}
		return sender;
	}
}

class FunctionalSenderProxy extends AbstractSenderProxy {
	IEvaluation<IUser> evaluation;

	FunctionalSenderProxy(IEvaluation<IUser> evaluation) {
		this.evaluation = evaluation;
	}

	@Override
	IUser getSender() {
		return evaluation.y();
	}
}

class SenderProxy implements IUser {
  IEvaluation<IUser> evaluation;
	
  @Override
  public int getId() {
    return getSender().getId();
  }

  @Override
  public String getName() {
    return getSender().getName();
  }
	
  IUser getSender() {
    return evaluation.y();
  }
}