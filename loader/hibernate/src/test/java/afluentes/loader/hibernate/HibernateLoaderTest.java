package afluentes.loader.hibernate;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.Test;

import afluentes.core.impl.Constant;
import afluentes.loader.api.IEvaluationHolder;
import afluentes.loader.api.ILoader;
import afluentes.loader.impl.LoaderImpl;

public class HibernateLoaderTest {
	@Test
	public void testJpa() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("afluentes");
		try {
			EntityManager manager = factory.createEntityManager();
			try {
				System.out.println("Message");
				Message message = manager.find(Message.class, 1);
				System.out.println(message.getClass().getSuperclass());
				System.out.println(message.getClass());				
				System.out.println(message);
				System.out.println();

				System.out.println("Sender");
				User sender = message.getSender();
				System.out.println(sender.getClass().getSuperclass());
				System.out.println(sender.getClass());				
				System.out.println(sender);				
				if (sender instanceof IEvaluationHolder) {
					IEvaluationHolder holder = (IEvaluationHolder) sender;
					System.out.println(holder);
					System.out.println(holder.getEvaluation());
					System.out.println(holder.getEvaluation().y());
				}				
				System.out.println();
				
				System.out.println("Recipients");
				List<User> recipients = message.getRecipients();
				System.out.println(recipients.getClass());
				for (User recipient : recipients) {
					System.out.println("Recipient");
					System.out.println(recipient.getClass());
					System.out.println(recipient);
					System.out.println();
					
					System.out.println("Picture");
					System.out.println(recipient.getPicture().getClass());
					System.out.println(recipient.getPicture());
					System.out.println();
				}
			} finally {
				try {
					manager.close();
				} catch (Exception e) {}
			}
		} finally {
			try {
				factory.close();
			} catch (Exception e) {}
		}
	}

	@Test
	public void testLoader() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("afluentes");
		try {
			EntityManager manager = factory.createEntityManager();
			try {
				List<Message> messages = manager.createQuery("select m from Message m ", Message.class).getResultList();
				System.out.println("Loading");
				ILoader<List<Message>> loader = new LoaderImpl<List<Message>>(".{sender.picture, recipients.picture, files.{mediaType}}") {};				
				loader.load(new Constant<>(messages));
				System.out.println("Loaded");
				for (Message message : messages) {
					message.toString();
					message.getSender().toString();
					message.getSender().getPicture().toString();
					for (User recipient : message.getRecipients()) {
						recipient.toString();						
						recipient.getPicture().toString();
					}
					for (File file : message.getFiles()) {
						file.toString();						
						file.getMediaType().toString();
					}										
				}
			} finally {
				try {
					manager.close();
				} catch (Exception e) {}
			}
		} finally {
			try {
				factory.close();
			} catch (Exception e) {}
		}
	}	
}