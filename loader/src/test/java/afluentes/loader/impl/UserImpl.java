package afluentes.loader.impl;

class UserImpl implements IUser {
	Integer identifier;
	IPicture picture;
	
	@Override
	public Integer getIdentifier() {
		return identifier;
	}

	@Override
	public IPicture getPicture() {
		return picture;
	}
}