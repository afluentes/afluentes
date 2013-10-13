package afluentes.core.article.benchmark;

import afluentes.core.api.IEvaluation;

abstract class AbstractPictureProxy implements IFile {	
	@Override
	public int getId() {
		return getPicture().getId();
	}

	@Override
	public String getName() {
		return getPicture().getName();
	}

	abstract IFile getPicture();	
}

class StandardPictureProxy extends AbstractPictureProxy {
	AbstractDao dao;
	int pictureId;
	IFile picture;

	StandardPictureProxy(AbstractDao dao, int pictureId) {
		this.dao = dao;
		this.pictureId = pictureId;
	}

	@Override
	IFile getPicture() {
		if (picture == null) {
			picture = dao.getFile(pictureId);
		}
		return picture;
	}
}

class AfluentesPictureProxy extends AbstractPictureProxy {
	IEvaluation<IFile> evaluation;

	AfluentesPictureProxy(IEvaluation<IFile> evaluation) {
		this.evaluation = evaluation;
	}

	@Override
	IFile getPicture() {
		return evaluation.y();
	}
}

class CallbackPictureProxy extends AbstractPictureProxy {
	int pictureId;
	volatile IFile picture;
	
	CallbackPictureProxy(int pictureId) {
		this.pictureId = pictureId;
	}

	@Override
	IFile getPicture() {
		if (picture == null) {
			throw new IllegalStateException("picture == null");
		}
		return picture;
	}
}