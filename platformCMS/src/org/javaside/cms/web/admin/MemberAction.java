package org.javaside.cms.web.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.javaside.cms.core.CRUDActionSupport;
import org.javaside.cms.core.HibernateWebUtils;
import org.javaside.cms.core.Page;
import org.javaside.cms.core.PropertyFilter;
import org.javaside.cms.core.ServiceException;
import org.javaside.cms.core.Struts2Utils;
import org.javaside.cms.entity.Member;
import org.javaside.cms.entity.MemberType;
import org.javaside.cms.entity.Role;
import org.javaside.cms.service.MemberManager;
import org.javaside.cms.service.MemberTypeManager;
import org.javaside.cms.service.RoleManager;
import org.javaside.cms.util.StrUtils;
import org.springframework.beans.factory.annotation.Autowired;

@SuppressWarnings("serial")
@Results( { @Result(name = CRUDActionSupport.RELOAD, location = "member.action", type = "redirect") })
public class MemberAction extends CRUDActionSupport<Member> {

	@Autowired
	private MemberManager memberManager;
	@Autowired
	private RoleManager roleManager;
	@Autowired
	private MemberTypeManager typeManager;

	// 基本属性
	private Member entity;
	private String loginname; //用户搜索属性
	private String serarchType; //用户搜索类型
	private Long id;
	private Long[] ids;
	private Page<Member> page = new Page<Member>(10);//每页10条记录
	private MemberType typeTmp; //大侠分类
	private Boolean colu; //是否专栏

	// 角色相关属性
	private List<Role> allRoles; //全部可选角色列表
	private List<Long> checkedRoleIds; //页面中钩选的角色id列表

	@Override
	public String delete() throws Exception {
		try {
			memberManager.delete(id);
			addActionMessage("删除用户成功");
		} catch (ServiceException e) {
			logger.error(e.getMessage(), e);
			addActionMessage(e.getMessage());
		}
		return RELOAD;
	}

	public String deleteBatch() throws Exception {
		memberManager.deleteBatch(ids);
		return RELOAD;
	}

	@Override
	public String list() throws Exception {
		HttpServletRequest request = Struts2Utils.getRequest();
		List<PropertyFilter> filters = HibernateWebUtils.buildPropertyFilters(request);

		page = memberManager.search(page, filters);
		return SUCCESS;
	}

	@Override
	protected void prepareModel() throws Exception {
		if (id != null) {
			entity = memberManager.get(id);
		} else {
			entity = new Member();
		}
	}

	@Override
	public String input() throws Exception {
		allRoles = roleManager.getAll();
		checkedRoleIds = entity.getRoleIds();
		return INPUT;
	}

	@Override
	public String save() throws Exception {
		//根据页面上的checkbox 整合User的Roles Set
		HibernateWebUtils.mergeByCheckedIds(entity.getRoles(), checkedRoleIds, Role.class);
		if (typeTmp != null && typeTmp.getId() != null && typeTmp.getId() != 0) {
			entity.setMemberType(typeTmp);
		} else {
			entity.setMemberType(null);
		}
		if (colu) {
			entity.setColPce(1);
		} else {
			entity.setColPce(0);
		}
		entity.setUsercode(RandomStringUtils.random(20, StrUtils.N36_CHARS));
		memberManager.save(entity);
		addActionMessage("保存用户成功");
		return RELOAD;
	}

	/**
	 * 用户搜索
	 * 
	 * @return
	 * @throws Exception
	 */
	public String searchUserName() throws Exception {
		page = memberManager.getSearchUserName(page, loginname, serarchType);
		return SUCCESS;
	}

	/**
	 * 支持使用Jquery.validate Ajax检验用户名是否重复.
	 */
	public String checkLoginName() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String loginName = request.getParameter("loginName");
		String orgLoginName = request.getParameter("orgLoginName");

		if (memberManager.isLoginNameUnique(loginName, orgLoginName)) {
			Struts2Utils.renderText("true");
		} else {
			Struts2Utils.renderText("false");
		}
		//因为直接输出内容而不经过jsp,因此返回null.
		return null;
	}

	public Member getModel() {
		return entity;
	}

	public Member getEntity() {
		return entity;
	}

	public void setEntity(Member entity) {
		this.entity = entity;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Page<Member> getPage() {
		return page;
	}

	public void setPage(Page<Member> page) {
		this.page = page;
	}

	public List<Role> getAllRoles() {
		return allRoles;
	}

	public void setAllRoles(List<Role> allRoles) {
		this.allRoles = allRoles;
	}

	public List<Long> getCheckedRoleIds() {
		return checkedRoleIds;
	}

	public void setCheckedRoleIds(List<Long> checkedRoleIds) {
		this.checkedRoleIds = checkedRoleIds;
	}

	public Long[] getIds() {
		return ids;
	}

	public void setIds(Long[] ids) {
		this.ids = ids;
	}

	public String getLoginname() {
		return loginname;
	}

	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}

	public String getSerarchType() {
		return serarchType;
	}

	public void setSerarchType(String serarchType) {
		this.serarchType = serarchType;
	}

	public MemberType getTypeTmp() {
		return typeTmp;
	}

	public void setTypeTmp(MemberType typeTmp) {
		this.typeTmp = typeTmp;
	}

	public Boolean getColu() {
		return colu;
	}

	public void setColu(Boolean colu) {
		this.colu = colu;
	}

}
