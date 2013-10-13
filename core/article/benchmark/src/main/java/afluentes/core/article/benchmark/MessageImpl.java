package afluentes.core.article.benchmark;

import java.util.List;

public class MessageImpl implements IMessage {
	int id;
	String subject;
	String body;
	IUser sender;
	List<? extends IUser> recipients;
	List<? extends IFile> files;
	
	@Override
	public int getId() {
		return id;
	}

	@Override
	public String getSubject() {
		return subject;
	}

	@Override
	public String getBody() {
		return body;
	}

	@Override
	public IUser getSender() {
		return sender;
	}

	@Override
	public List<? extends IUser> getRecipients() {
		return recipients;
	}

	@Override
	public List<? extends IFile> getFiles() {
		return files;
	}

	@Override
	public String toString() {
		return "MessageImpl [id=" + id + ", subject=" + subject + ", body="+ body + "]";
	}
}