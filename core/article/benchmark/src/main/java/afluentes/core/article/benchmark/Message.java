package afluentes.core.article.benchmark;

import java.util.List;

class Message {
	int id;
	String subject;
	String body;
	IUser sender;
	List<IUser> recipients;
}