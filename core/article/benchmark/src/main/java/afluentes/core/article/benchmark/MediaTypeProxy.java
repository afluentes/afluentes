package afluentes.core.article.benchmark;

import afluentes.core.api.IEvaluation;
import afluentes.loader.api.IEvaluationHolder;

public class MediaTypeProxy implements IEvaluationHolder<MediaTypeImpl>, IMediaType {
	IEvaluation<MediaTypeImpl> evaluation;
	
	@Override
	public IEvaluation<MediaTypeImpl> getEvaluation() {
		return evaluation;
	}
	
	@Override
	public int getId() {
		return evaluation.y().getId();
	}

	@Override
	public String getType() {
		return evaluation.y().getType();
	}

	@Override
	public String getSubtype() {
		return evaluation.y().getSubtype();
	}
	
	@Override
	public String toString() {
		return evaluation.y().toString();
	}	
}