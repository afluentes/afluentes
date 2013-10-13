package afluentes.core.article.benchmark;

import afluentes.core.api.IEvaluation;
import afluentes.loader.api.IEvaluationHolder;

public class UserProxy implements IEvaluationHolder<UserImpl>, IUser {
	IEvaluation<UserImpl> evaluation;
	
	@Override
	public IEvaluation<UserImpl> getEvaluation() {
		return evaluation;
	}
	
	@Override
	public int getId() {
		return evaluation.y().getId();
	}

	@Override
	public String getName() {
		return evaluation.y().getName();
	}

	@Override
	public IFile getPicture() {
		return evaluation.y().getPicture();
	}
	
	@Override
	public String toString() {
		return evaluation.y().toString();
	}	
}
