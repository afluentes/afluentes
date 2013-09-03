package afluentes.loader.impl;

import afluentes.core.api.IEvaluation;
import afluentes.loader.api.IEvaluationHolder;

class UserProxy implements IEvaluationHolder<IUser>, IUser {
	IEvaluation<IUser> evaluation;

	@Override
	public IEvaluation<IUser> getEvaluation() {
		return evaluation;
	}

	@Override
	public Integer getIdentifier() {
		return evaluation.y().getIdentifier();
	}

	@Override
	public IPicture getPicture() {
		return evaluation.y().getPicture();
	}
}