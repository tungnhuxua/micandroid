package ningbo.media.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;

@Entity
@Table(name = "category1")
@XmlRootElement
public class FirstCategory implements Serializable{

	private static final long serialVersionUID = -7364749707727750792L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Expose
	private Integer id ;
	
	@Expose
	private String name_en ;
	
	@Expose
	private String name_cn ;
	
	@Expose
	private Integer user_id ;
	
	@Expose
	private Date date_time ;
	
	public FirstCategory(){}

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

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public Date getDate_time() {
		return date_time;
	}

	public void setDate_time(Date date_time) {
		this.date_time = date_time;
	}
	
	public  String toJson(){
		Gson gson = new Gson() ;
		String json =  gson.toJson(this, FirstCategory.class) ;
		return json ;
	}
	
	public static FirstCategory fromJson(String jsonCategory){
		Gson gson = new Gson() ;
		FirstCategory fCategory = gson.fromJson(jsonCategory, FirstCategory.class) ;
		return fCategory ;
	}
	
}
