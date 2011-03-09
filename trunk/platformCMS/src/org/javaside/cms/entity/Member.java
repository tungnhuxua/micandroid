package org.javaside.cms.entity;

import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.javaside.cms.core.ReflectionUtils;

/**
 * 会员实体
 * 
 * @author zhouxinghua
 */
@Entity
@Table(name = "MEMBER")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Member {
	private Long id;
	private String loginName;
	private String name;
	private String email;
	private String password;
	private Date createDate;
	private MemberInfo info;
	private MemberType memberType;
	private Integer colPce = 0; //1:专栏
	private String usercode;
	private Integer state; //0:非会员 1：正式会员 
	private Integer online = 0; //0:离线，1:在线
	private Set<Role> roles = new LinkedHashSet<Role>(); //有序的关联对象集合.

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@OneToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "member")
	public MemberInfo getInfo() {
		return info;
	}

	public void setInfo(MemberInfo info) {
		this.info = info;
	}

	//避免定义CascadeType.REMOVE, 否则删除角色时会连带删除拥有它的用户.
	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	//多对多定义.
	@JoinTable(name = "MEMBERS_ROLES", joinColumns = { @JoinColumn(name = "MEMBER_ID") }, inverseJoinColumns = { @JoinColumn(name = "ROLE_ID") })
	//集合按id排序.
	@OrderBy("id")
	//集合中对象的id的缓存.
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	//非持久化属性.
	@Transient
	public String getRoleNames() {
		try {
			return ReflectionUtils.fetchElementPropertyToString(roles, "name", ", ");
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	//非持久化属性.
	@Transient
	@SuppressWarnings("unchecked")
	public List<Long> getRoleIds() {
		try {
			return ReflectionUtils.fetchElementPropertyToList(roles, "id");
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public String getUsercode() {
		return usercode;
	}

	public void setUsercode(String usercode) {
		this.usercode = usercode;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	@ManyToOne
	public MemberType getMemberType() {
		return memberType;
	}

	public void setMemberType(MemberType memberType) {
		this.memberType = memberType;
	}

	public Integer getColPce() {
		return colPce;
	}

	public void setColPce(Integer colPce) {
		this.colPce = colPce;
	}

	public Integer getOnline() {
		return online;
	}

	public void setOnline(Integer online) {
		this.online = online;
	}
}
