package afluentes.core.article.benchmark;

class FileImpl implements IFile {
	int id;
	String name;
	
	@Override
	public int getId() {
		return id;
	}

	@Override
	public String getName() {
		return name;
	}
}