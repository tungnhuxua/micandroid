package ningbo.media.bean;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;

@Entity
@Table(name = "category2")
@XmlRootElement
public class SecondCategory implements Serializable {

	private static final long serialVersionUID = 1831733122782637063L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Expose
	private Integer id;

	@Expose
	private String name_en;

	@Expose
	private String name_cn;

	@Expose
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "category1_id")
	private FirstCategory firstCategory;
	
	@OneToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "secondCategory")
	private List<Location> locations ;

	public SecondCategory() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName_en() {
		return name_en;
	}

	public void setName_en(String name_en) {
		this.name_en = name_en;
	}

	public String getName_cn() {
		return name_cn;
	}

	public void setName_cn(String name_cn) {
		this.name_cn = name_cn;
	}

	@XmlTransient
	public FirstCategory getFirstCategory() {
		return firstCategory ;
	}

	public void setFirstCategory(FirstCategory firstCategory) {
		this.firstCategory = firstCategory;
	}
	
	@XmlTransient
	public List<Location> getLocations() {
		return locations;
	}

	public void setLocations(List<Location> locations) {
		this.locations = locations;
	}

	public String toJson() {
		Gson gson = new Gson();
		String json = gson.toJson(this, SecondCategory.class);
		return json;
	}

	public static SecondCategory fromJson(String jsonCategory) {
		Gson gson = new Gson();
		SecondCategory fCategory = gson.fromJson(jsonCategory,
				SecondCategory.class);
		return fCategory;
	}

}
