package org.javaside.cms.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 圈子类型
 * 
 * @author dkun.liu@gmail.com
 */

@SuppressWarnings("serial")
@Entity
@Table(name = "MEMBER_CIRCLE_Type")
public class MemberCircleType {

	private Long id;
	private String circleTypeName;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCircleTypeName() {
		return circleTypeName;
	}

	public void setCircleTypeName(String circleTypeName) {
		this.circleTypeName = circleTypeName;
	}

}
