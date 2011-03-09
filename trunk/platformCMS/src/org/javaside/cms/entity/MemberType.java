package org.javaside.cms.entity;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

/**
 * 会员类型。
 * 
 * @author zhouxinghua
 */
@Entity
@Table(name = "MEMBER_TYPE")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class MemberType {
	private Long id;
	private String name;
	private String nameEn;
	private MemberType parent;
	private Set<MemberType> child = new LinkedHashSet<MemberType>();
	private Set<Member> members = new LinkedHashSet<Member>(); //会员

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

	public String getNameEn() {
		return nameEn;
	}

	public void setNameEn(String nameEn) {
		this.nameEn = nameEn;
	}

	@ManyToOne
	@JoinColumn(name = "parent")
	public MemberType getParent() {
		return parent;
	}

	public void setParent(MemberType parent) {
		this.parent = parent;
	}

	@OneToMany(mappedBy = "parent")
	@LazyCollection(LazyCollectionOption.TRUE)
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	public Set<MemberType> getChild() {
		return child;
	}

	public void setChild(Set<MemberType> child) {
		this.child = child;
	}

	@OneToMany(mappedBy = "memberType")
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	public Set<Member> getMembers() {
		return members;
	}

	public void setMembers(Set<Member> members) {
		this.members = members;
	}
}
