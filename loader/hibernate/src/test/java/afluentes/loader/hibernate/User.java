package afluentes.loader.hibernate;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class User {
/*---------------------------------------------------------------------------*/	
	private Integer id;
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}
/*---------------------------------------------------------------------------*/
	
/*---------------------------------------------------------------------------*/
	private Picture picture;
	
	public void setPicture(Picture picture) {
		this.picture = picture;
	}
	
	@OneToOne(fetch=FetchType.LAZY)
	public Picture getPicture() {
		return picture;
	}
/*---------------------------------------------------------------------------*/	
}