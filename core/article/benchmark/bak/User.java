package afluentes.core.article.benchmark;

class UserImpl implements IUser {
	int id;
	String name;
	IFile picture;

	@Override
	public int getId() {
		return id;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public IFile getPicture() {
		return picture;
	}
}