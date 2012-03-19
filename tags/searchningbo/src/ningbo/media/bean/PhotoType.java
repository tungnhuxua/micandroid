package ningbo.media.bean;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;


@Entity
@Table(name = "tb_photo_type")
@XmlRootElement
public class PhotoType {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id ;
	
	private String type ;
	
	@OneToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "photoType")
	private List<ImageFile> imageFiles ;
	
	public PhotoType(){}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public List<ImageFile> getImageFiles() {
		return imageFiles;
	}


	public void setImageFiles(List<ImageFile> imageFiles) {
		this.imageFiles = imageFiles;
	}
	
	
}
