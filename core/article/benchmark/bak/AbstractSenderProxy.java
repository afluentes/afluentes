package afluentes.core.article.benchmark;

import afluentes.core.api.IEvaluation;

abstract class AbstractSenderProxy implements IUser {	
	@Override
	public int getId() {
		return getSender().getId();
	}

	@Override
	public String getName() {
		return getSender().getName();
	}
	
	@Override
	public IFile getPicture() {
		return getSender().getPicture();
	}
	
	abstract IUser getSender();	
}

class StandardSenderProxy extends AbstractSenderProxy {
	AbstractDao dao;
	int senderId;
	IUser sender;

	StandardSenderProxy(AbstractDao dao, int senderId) {
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

class AfluentesSenderProxy extends AbstractSenderProxy {
	IEvaluation<IUser> evaluation;

	AfluentesSenderProxy(IEvaluation<IUser> evaluation) {
		this.evaluation = evaluation;
	}

	@Override
	IUser getSender() {
		return evaluation.y();
	}
}

class CallbackSenderProxy extends AbstractSenderProxy {
	int senderId;
	volatile IUser sender;
	
	CallbackSenderProxy(int senderId) {
		this.senderId = senderId;
	}

	@Override
	IUser getSender() {
		if (sender == null) {
			throw new IllegalStateException("sender == null");
		}
		return sender;
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

  @Override
  public IFile getPicture() {
	return getSender().getPicture();
  }
}