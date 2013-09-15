package afluentes.loader.impl;

class FileImpl implements IFile {
	Integer identifier;
	IMediaType mediaType;

	@Override
	public Integer getIdentifier() {
		return identifier;
	}

	@Override
	public IMediaType getMediaType() {
		return mediaType;
	}
}