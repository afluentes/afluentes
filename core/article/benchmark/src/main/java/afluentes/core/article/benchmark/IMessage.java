package afluentes.core.article.benchmark;

import java.util.List;

interface IMessage {
	int getId();
	String getSubject();
	String getBody();
	IUser getSender();
	List<? extends IUser> getRecipients();
	List<? extends IFile> getFiles();
}