package afluentes.loader.impl;

import afluentes.core.api.IEvaluation;
import afluentes.loader.api.IEvaluationHolder;

class MediaTypeProxy implements IEvaluationHolder<IMediaType>, IMediaType {
	IEvaluation<IMediaType> evaluation;

	@Override
	public IEvaluation<IMediaType> getEvaluation() {
		return evaluation;
	}

	@Override
	public Integer getIdentifier() {
		return evaluation.y().getIdentifier();
	}
}