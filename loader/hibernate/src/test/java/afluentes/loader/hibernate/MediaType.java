package afluentes.loader.hibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="MEDIA_TYPE")
public class MediaType {
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
}