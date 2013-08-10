package afluentes.core.article.benchmark;

import java.util.Iterator;
import java.util.List;

class Marshaller {
	Marshaller() {}

	String marshallMessages(List<Message> messages) {
		StringBuilder builder = new StringBuilder();	
		appendMessages(builder, messages);
		return builder.toString();
	}

	void appendMessages(StringBuilder builder, List<Message> messages) {
		builder.append('[');
		for (Iterator<Message> i = messages.iterator(); i.hasNext(); ) {
			appendMessage(builder, i.next());
			if (i.hasNext()) {
				builder.append(',');
			}
		}
		builder.append(']');
	}
	
	void appendMessage(StringBuilder builder, Message message) {
		builder.append('{');
		
		appendString(builder, "id");
		builder.append(':');
		appendInt(builder, message.id);
		builder.append(',');

		appendString(builder, "subject");
		builder.append(':');
		appendString(builder, message.subject);
		builder.append(',');

		appendString(builder, "body");
		builder.append(':');
		appendString(builder, message.body);
		builder.append(',');
		
		appendString(builder, "sender");
		builder.append(':');
		appendUser(builder, message.sender);
		builder.append(',');
		
		appendString(builder, "recipients");
		builder.append(':');
		appendUsers(builder, message.recipients);

		builder.append("}");
	}	
		
	void appendString(StringBuilder builder, String string) {
		builder.append('\"');
		builder.append(string);
		builder.append('\"');
	}
	
	void appendInt(StringBuilder builder, int i) {
		builder.append(i);
	}	

	void appendUser(StringBuilder builder, IUser user) {
		builder.append('{');

		appendString(builder, "id");
		builder.append(':');
		appendInt(builder, user.getId());
		builder.append(',');

		appendString(builder, "name");
		builder.append(':');
		appendString(builder, user.getName());
		
		builder.append('}');		
	}
	
	void appendUsers(StringBuilder builder, List<IUser> users) {
		builder.append('[');
		for (Iterator<IUser> i = users.iterator(); i.hasNext(); ) {
			appendUser(builder, i.next());
			if (i.hasNext()) {
				builder.append(',');
			}
		}
		builder.append(']');
	}
}