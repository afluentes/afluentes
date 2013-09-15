package afluentes.loader.api;

import afluentes.core.api.IEvaluation;

public interface IEvaluationHolder<Y> {
	IEvaluation<Y> getEvaluation();
}