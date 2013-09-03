package afluentes.loader.impl;

import afluentes.core.api.IEvaluation;
import afluentes.loader.api.IEvaluationHolder;

class FileProxy implements IEvaluationHolder<IFile>, IFile {
	IEvaluation<IFile> evaluation;

	@Override
	public IEvaluation<IFile> getEvaluation() {
		return evaluation;
	}
	
	@Override
	public Integer getIdentifier() {
		return evaluation.y().getIdentifier();
	}

	@Override
	public IMediaType getMediaType() {
		return evaluation.y().getMediaType();
	}
}