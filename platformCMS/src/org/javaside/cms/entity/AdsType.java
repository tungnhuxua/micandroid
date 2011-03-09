package org.javaside.cms.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * 广告类别，如自定义广告，GOOGLE广告等。
 * 
 * @author HP
 */
@Entity
@Table(name = "ADSTYPE")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AdsType {
	private Long id;
	private String name;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
