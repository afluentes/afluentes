package afluentes.core.article.example;

import java.util.Collection;

import afluentes.loader.api.ILoader;
import afluentes.loader.impl.LoaderImpl;

class User {
  int id;
  String name;
  File picture;
}

class File {
  int id;
  String name;
  MediaType mediaType;
}

class MediaType {
  int id;
  String type;    // e.g. image
  String subtype; // e.g. jpeg
}

class Message {
  int id;
  String subject;
  String body;
  User sender;
  Collection<User> recipients;	
  Collection<File> files;	
  Message replied; 
  Collection<Message> replies;
}

class Traversals {
	@SuppressWarnings("unused")
	void traversal1() {
Collection<Message> messages = getMessages();
for (Message message : messages) {
  print(message.sender);
}
	}

	void traversal2() {
Collection<Message> messages = getMessages();

String traversal = ".{sender, recipients}";
ILoader<Collection<Message>> loader = 
  new LoaderImpl<Collection<Message>>(traversal) {};
loader.load(messages);

for (Message message : messages) {
  print(message.sender);
  for (User recipient : message.recipients) {
    print(recipient);  
  }
}
	}
	
	Collection<Message> getMessages() {
		return null;
	}
  
	void print(User user) {}

@SuppressWarnings("unused")
void visitMessage(Message message) {
  for (Message reply : message.replies) {
    visitMessage(message);
  }  
}

}