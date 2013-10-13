package afluentes.core.article.benchmark;

import afluentes.core.api.IEvaluation;
import afluentes.loader.api.IEvaluationHolder;

public class FileProxy implements IEvaluationHolder<FileImpl>, IFile {
	IEvaluation<FileImpl> evaluation;
	
	@Override
	public IEvaluation<FileImpl> getEvaluation() {
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
	public IMediaType getMediaType() {
		return evaluation.y().getMediaType();
	}
	
	@Override
	public String toString() {
		return evaluation.y().toString();
	}
}