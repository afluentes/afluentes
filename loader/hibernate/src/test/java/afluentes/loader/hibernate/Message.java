package afluentes.loader.hibernate;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="MESSAGE")
public class Message {
/*---------------------------------------------------------------------------*/	
	private Integer id;
	
	public void setId(Integer id) {
		this.id = id;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	public Integer getId() {
		return id;
	}
/*---------------------------------------------------------------------------*/

/*---------------------------------------------------------------------------*/
	private User sender;
	
	public void setSender(User sender) {
		this.sender = sender;
	}

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="SENDER_ID")
	public User getSender() {
		return sender;
	}
/*---------------------------------------------------------------------------*/
	
/*---------------------------------------------------------------------------*/
	private List<User> recipients;
	
	public void setRecipients(List<User> recipients) {
		this.recipients = recipients;
	}
	
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(
		name="MESSAGE_RECIPIENT", 
		inverseJoinColumns=@JoinColumn(name="RECIPIENT_ID"), 
		joinColumns=@JoinColumn(name="MESSAGE_ID")
	)	
	public List<User> getRecipients() {
		return recipients;
	}
/*---------------------------------------------------------------------------*/	
	
/*---------------------------------------------------------------------------*/
	private List<File> files;
	
	public void setFiles(List<File> files) {
		this.files = files;
	}

	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(
		name="MESSAGE_FILE", 
		inverseJoinColumns=@JoinColumn(name="FILE_ID"), 
		joinColumns=@JoinColumn(name="MESSAGE_ID")
	)
	public List<File> getFiles() {
		return files;
	}
/*---------------------------------------------------------------------------*/	
}