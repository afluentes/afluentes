package afluentes.core.article.benchmark;

import java.util.List;

import afluentes.core.api.IEvaluation;
import afluentes.loader.api.IEvaluationHolder;

public class MessageProxy implements IEvaluationHolder<MessageImpl>, IMessage {
	IEvaluation<MessageImpl> evaluation;
	
	@Override
	public IEvaluation<MessageImpl> getEvaluation() {
		return evaluation;
	}
	
	@Override
	public int getId() {
		return evaluation.y().getId();
	}

	@Override
	public String getSubject() {
		return evaluation.y().getSubject();
	}

	@Override
	public String getBody() {
		return evaluation.y().getBody();
	}

	@Override
	public IUser getSender() {
		return evaluation.y().getSender();
	}

	@Override
	public List<? extends IUser> getRecipients() {
		return evaluation.y().getRecipients();
	}

	@Override
	public List<? extends IFile> getFiles() {
		return evaluation.y().getFiles();
	}
		
	@Override
	public String toString() {
		return evaluation.y().toString();
	}		
}
