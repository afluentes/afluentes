package afluentes.core.article.benchmark;

class UserImpl implements IUser {
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