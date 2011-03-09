package org.javaside.cms.web.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.javaside.cms.core.CRUDActionSupport;
import org.javaside.cms.core.HibernateWebUtils;
import org.javaside.cms.core.Page;
import org.javaside.cms.core.PropertyFilter;
import org.javaside.cms.core.ServiceException;
import org.javaside.cms.core.Struts2Utils;
import org.javaside.cms.entity.Authority;
import org.javaside.cms.entity.Role;
import org.javaside.cms.service.AuthorityManager;
import org.javaside.cms.service.RoleManager;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 角色管理Action. 使用Struts2 convention-plugin annotation定义Action参数.
 * 
 * @author calvin
 */
@SuppressWarnings("serial")
@Results( { @Result(name = CRUDActionSupport.RELOAD, location = "role.action", type = "redirect") })
public class RoleAction extends CRUDActionSupport<Role> {

	@Autowired
	private RoleManager roleManager;
	@Autowired
	private AuthorityManager authorityManager;

	// 基本属性
	private Role entity;
	private Long id;
	private Long[] ids;
	private List<Role> allRoles;
	private Page<Role> page = new Page<Role>(10);//每页10条记录

	// 权限相关属性
	private List<Authority> allAuths; //全部可选权限列表
	private List<Long> checkedAuthIds;//页面中钩选的权限id列表

	// 基本属性访问函数 //

	public Role getModel() {
		return entity;
	}

	@Override
	protected void prepareModel() throws Exception {
		if (id != null) {
			entity = roleManager.get(id);
		} else {
			entity = new Role();
		}
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Role> getAllRoles() {
		return allRoles;
	}

	// CRUD Action 函数 //

	@Override
	public String list() throws Exception {
		HttpServletRequest request = Struts2Utils.getRequest();
		List<PropertyFilter> filters = HibernateWebUtils.buildPropertyFilters(request);

		page = roleManager.search(page, filters);
		return SUCCESS;
	}

	@Override
	public String input() throws Exception {
		allAuths = authorityManager.getAll();
		checkedAuthIds = entity.getAuthIds();
		return INPUT;
	}

	@Override
	public String save() throws Exception {
		//根据页面上的checkbox 整合Role的Authorities Set.
		HibernateWebUtils.mergeByCheckedIds(entity.getAuths(), checkedAuthIds, Authority.class);

		roleManager.save(entity);
		addActionMessage("保存角色成功");
		return RELOAD;
	}

	@Override
	public String delete() throws Exception {
		try {
			roleManager.delete(id);
			addActionMessage("删除角色成功");
		} catch (ServiceException e) {
			logger.error(e.getMessage(), e);
			addActionMessage(e.getMessage());
		}
		return RELOAD;
	}

	public String deleteBatch() throws Exception {
		roleManager.deleteBatch(ids);
		return RELOAD;
	}

	// 其他属性访问函数及Action函数 //

	public List<Authority> getAllAuths() {
		return allAuths;
	}

	public List<Long> getCheckedAuthIds() {
		return checkedAuthIds;
	}

	public void setCheckedAuthIds(List<Long> checkedAuthIds) {
		this.checkedAuthIds = checkedAuthIds;
	}

	public Role getEntity() {
		return entity;
	}

	public void setEntity(Role entity) {
		this.entity = entity;
	}

	public Page<Role> getPage() {
		return page;
	}

	public void setPage(Page<Role> page) {
		this.page = page;
	}

	public Long getId() {
		return id;
	}

	public void setAllRoles(List<Role> allRoles) {
		this.allRoles = allRoles;
	}

	public void setAllAuths(List<Authority> allAuths) {
		this.allAuths = allAuths;
	}

	public Long[] getIds() {
		return ids;
	}

	public void setIds(Long[] ids) {
		this.ids = ids;
	}
}