package afluentes.loader.impl;

import java.util.List;

class MessageImpl implements IMessage {
	Integer identifier;
	IUser sender;
	List<IUser> recipients;
	List<IFile> files;
	
	@Override
	public Integer getIdentifier() {
		return identifier;
	}

	@Override
	public IUser getSender() {
		return sender;
	}

	@Override
	public List<IUser> getRecipients() {
		return recipients;
	}

	@Override
	public List<IFile> getFiles() {
		return files;
	}
}