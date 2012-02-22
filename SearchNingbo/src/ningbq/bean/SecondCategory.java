package ningbq.bean;

import java.io.Serializable;

public class SecondCategory implements Serializable {

	private static final long serialVersionUID = 6039935081986846846L;

	private int id;

	private String secondId;

	private String firstId;

	private String name_en;

	private String name_cn;

	public int getId() {
		return id;
	}

	public void setId(int id) {
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

	public String getSecondId() {
		return secondId;
	}

	public void setSecondId(String secondId) {
		this.secondId = secondId;
	}

	public String getFirstId() {
		return firstId;
	}

	public void setFirstId(String firstId) {
		this.firstId = firstId;
	}

}
