package afluentes.loader.api;

import afluentes.core.api.IEvaluation;

public interface ILoader<Y> {
	void load(Y root);	
	void load(IEvaluation<Y> root);
}