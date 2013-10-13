package afluentes.core.article.benchmark;

public class MediaTypeImpl implements IMediaType {
	int id;
	String type;
	String subtype;

	@Override
	public int getId() {
		return id;
	}

	@Override
	public String getType() {
		return type;
	}

	@Override
	public String getSubtype() {
		return subtype;
	}

	@Override
	public String toString() {
		return "MediaTypeImpl [id=" + id + ", type=" + type + ", subtype=" + subtype + "]";
	}
}