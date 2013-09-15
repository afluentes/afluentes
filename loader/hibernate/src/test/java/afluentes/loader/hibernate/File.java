package afluentes.loader.hibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="FILE")
public class File {
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
	private MediaType mediaType;
	
	public void setMediaType(MediaType mediaType) {
		this.mediaType = mediaType;
	}

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="MEDIA_TYPE_ID")
	public MediaType getMediaType() {
		return mediaType;
	}
/*---------------------------------------------------------------------------*/	
}