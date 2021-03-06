package afluentes.core.article.benchmark;

import java.util.Iterator;
import java.util.List;

class Marshaller {
	Marshaller() {}

	String marshallMessages(List<? extends IMessage> messages) {
		StringBuilder builder = new StringBuilder();	
		appendMessages(builder, messages);
		return builder.toString();
	}

	void appendMessages(StringBuilder builder, List<? extends IMessage> messages) {
		builder.append('[');
		for (Iterator<? extends IMessage> i = messages.iterator(); i.hasNext(); ) {
			appendMessage(builder, i.next());
			if (i.hasNext()) {
				builder.append(',');
			}
		}
		builder.append(']');
	}
	
	void appendMessage(StringBuilder builder, IMessage message) {
		builder.append('{');
		
		appendString(builder, "id");
		builder.append(':');
		appendInt(builder, message.getId());
		builder.append(',');

		appendString(builder, "subject");
		builder.append(':');
		appendString(builder, message.getSubject());
		builder.append(',');

		appendString(builder, "body");
		builder.append(':');
		appendString(builder, message.getBody());
		builder.append(',');
		
		appendString(builder, "sender");
		builder.append(':');
		appendUser(builder, message.getSender());
		builder.append(',');
		
		appendString(builder, "recipients");
		builder.append(':');
		appendUsers(builder, message.getRecipients());

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

/*		
		appendString(builder, "picture");
		builder.append(':');
		appendFile(builder, user.getPicture());		
*/
		
		builder.append('}');		
	}
	
	void appendFile(StringBuilder builder, IFile file) {
		builder.append('{');

		appendString(builder, "id");
		builder.append(':');
		appendInt(builder, file.getId());
		builder.append(',');

		appendString(builder, "name");
		builder.append(':');
		appendString(builder, file.getName());
		
		appendString(builder, "mediaType");
		builder.append(':');
		appendMediaType(builder, file.getMediaType());		
				
		builder.append('}');				
	}
	
	void appendMediaType(StringBuilder builder, IMediaType mediaType) {
		builder.append('{');

		appendString(builder, "id");
		builder.append(':');
		appendInt(builder, mediaType.getId());
		builder.append(',');

		appendString(builder, "type");
		builder.append(':');
		appendString(builder, mediaType.getType());
		
		appendString(builder, "subtype");
		builder.append(':');
		appendString(builder, mediaType.getSubtype());		
				
		builder.append('}');				
	}	
	
	void appendUsers(StringBuilder builder, List<? extends IUser> users) {
		builder.append('[');
		for (Iterator<? extends IUser> i = users.iterator(); i.hasNext(); ) {
			appendUser(builder, i.next());
			if (i.hasNext()) {
				builder.append(',');
			}
		}
		builder.append(']');
	}
}