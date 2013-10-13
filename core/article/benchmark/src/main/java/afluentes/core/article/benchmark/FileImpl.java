package afluentes.core.article.benchmark;

public class FileImpl implements IFile {
	int id;
	String name;
	IMediaType mediaType;
	
	@Override
	public int getId() {
		return id;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public IMediaType getMediaType() {
		return mediaType;
	}

	@Override
	public String toString() {
		return "FileImpl [id=" + id + ", name=" + name + "]";
	}
}