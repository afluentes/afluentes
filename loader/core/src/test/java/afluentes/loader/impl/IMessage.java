package afluentes.loader.impl;

import java.util.List;

interface IMessage {
	Integer getIdentifier();
	IUser getSender();
	List<IUser> getRecipients();
	List<IFile> getFiles();
}