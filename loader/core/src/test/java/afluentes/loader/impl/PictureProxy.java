package afluentes.loader.impl;

import afluentes.core.api.IEvaluation;
import afluentes.loader.api.IEvaluationHolder;

class PictureProxy implements IEvaluationHolder<IPicture>, IPicture {
	IEvaluation<IPicture> evaluation;
	
	@Override
	public IEvaluation<IPicture> getEvaluation() {
		return evaluation;
	}

	@Override
	public Integer getIdentifier() {
		return evaluation.y().getIdentifier();
	}
}