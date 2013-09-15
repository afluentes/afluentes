package afluentes.loader.hibernate;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.persister.spi.PersisterClassResolver;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.junit.Test;

public class HibernateLoaderTest {
	@Test
	public void testJpa() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("afluentes");
		try {
			EntityManager manager = factory.createEntityManager();
			try {
				Message message = manager.find(Message.class, 1);
				System.out.println(message.getClass());
				System.out.println(message);
				List<User> recipients = message.getRecipients();
				System.out.println(recipients.getClass());
				for (User recipient : recipients) {
					System.out.println(recipient.getClass());
					System.out.println(recipient);
					System.out.println(recipient.getPicture().getClass());
					System.out.println(recipient.getPicture());
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
	public void testHibernate() {
		Configuration configuration = new Configuration();
		configuration.configure();
		
		ServiceRegistry registry = new ServiceRegistryBuilder()
			.applySettings(configuration.getProperties())
			.addService(PersisterClassResolver.class, new AfluentesPersisterClassResolver())
			.buildServiceRegistry();

		SessionFactory factory = configuration.buildSessionFactory(registry);
		try {
			Session session = factory.openSession();
			try {
				Message message = (Message) session.load(Message.class, 1);
				System.out.println(message.getClass());
				System.out.println(message);
				List<User> recipients = message.getRecipients();
				System.out.println(recipients.getClass());
				for (User recipient : recipients) {
					System.out.println(recipient.getClass());
					System.out.println(recipient);
					System.out.println(recipient.getPicture().getClass());
					System.out.println(recipient.getPicture());
				}
			} finally { 
				try {
					session.close();
				} catch (RuntimeException e) {}				
			}
		} finally {
			try {
				factory.close();
			} catch (RuntimeException e) {}
		}
	}
}