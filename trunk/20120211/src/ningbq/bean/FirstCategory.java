package ningbq.bean;

import java.io.Serializable;
import ningbq.http.SearchNingboResponse;

public class FirstCategory extends SearchNingboResponse implements Serializable {

	// private static final String TAG = "FirstCategory" ;
	private static final long serialVersionUID = 107416998329389052L;

	private int id;
	
	private String firstId; //mysql provide.

	private String name_en;

	private String name_cn;

	public FirstCategory() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstId() {
		return firstId;
	}

	public void setFirstId(String firstId) {
		this.firstId = firstId;
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


	@Override
	public String toString() {
		return "FirstCategory [id=" + id + ", name_en=" + name_en
				+ ", name_cn=" + name_cn + "]";
	}

}
