package afluentes.loader.impl;

import java.util.List;

import afluentes.core.api.IEvaluation;
import afluentes.loader.api.IEvaluationHolder;

class MessageProxy implements IEvaluationHolder<IMessage>, IMessage {
	IEvaluation<IMessage> evaluation;	

	@Override
	public IEvaluation<IMessage> getEvaluation() {
		return evaluation;
	}
	
	@Override
	public Integer getIdentifier() {
		return evaluation.y().getIdentifier();
	}

	@Override
	public IUser getSender() {
		return evaluation.y().getSender();
	}

	@Override
	public List<IUser> getRecipients() {
		return evaluation.y().getRecipients();
	}

	@Override
	public List<IFile> getFiles() {
		return evaluation.y().getFiles();
	}
}