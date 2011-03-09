package org.javaside.cms.web.admin;

import java.util.List;

import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.javaside.cms.core.CRUDActionSupport;
import org.javaside.cms.core.Page;
import org.javaside.cms.core.SpringSecurityUtils;
import org.javaside.cms.entity.DownloadResource;
import org.javaside.cms.entity.DownloadType;
import org.javaside.cms.service.DownloadResourceManager;
import org.javaside.cms.service.DownloadTypeManager;
import org.javaside.cms.service.MemberManager;
import org.springframework.beans.factory.annotation.Autowired;

@SuppressWarnings("serial")
@Results( { @Result(name = CRUDActionSupport.RELOAD, location = "download-resource.action?page.pageRequest=${page.pageRequest}&resourceType.id=${resourceType.id}", type = "redirect") })
public class DownloadResourceAction extends CRUDActionSupport<DownloadResource> {

	@Autowired
	private DownloadResourceManager resourceManager;
	@Autowired
	private DownloadTypeManager typeManager;
	@Autowired
	private MemberManager memberManager;

	private Long id; //资源ID
	private DownloadResource entity; //资源
	private DownloadType resourceType; //资源类型
	private Page<DownloadResource> page = new Page<DownloadResource>(10);
	private Long[] ids; //资源ID数组
	private List<DownloadType> typeList; //资源分类
	private Boolean recom; //是否是推荐
	private String resourceName;

	@Override
	public String delete() throws Exception {
		resourceManager.delete(id);
		return RELOAD;
	}

	public String deleteBatch() throws Exception {
		resourceManager.deleteBatch(ids);
		return RELOAD;
	}

	@Override
	public String list() throws Exception {
		//下载管理的当前 位置中显示该资源类型
		if (resourceType != null && resourceType.getId() != null) {
			resourceType = typeManager.get(resourceType.getId());
		}
		page = resourceManager.getResourceOfType(resourceType, page);
		return SUCCESS;
	}

	@Override
	protected void prepareModel() throws Exception {
		if (id != null) {
			entity = resourceManager.get(id);
		} else {
			entity = new DownloadResource();
		}
	}

	@Override
	public String input() throws Exception {
		typeList = typeManager.getDownloadTypeRoot();
		if (entity.getType() != null) {
			resourceType = entity.getType();
		} else if (resourceType != null && resourceType.getId() != null) {
			resourceType = typeManager.get(resourceType.getId());
		}
		return INPUT;
	}

	@Override
	public String save() throws Exception {
		//重新指定资源所属分类，防止出现修改所属分类而出现修改分类ID而报错
		if (resourceType != null && resourceType.getId() != null) {
			entity.setType(resourceType);
		}
		if (entity.getId() == null && entity.getMember() == null) {
			//设置资源录入的会员
			entity.setMember(memberManager.getMemberByLoginName(SpringSecurityUtils.getCurrentUserName()));
		}
		//推荐
		if (recom) {
			entity.setRecommend(1);
		} else {
			entity.setRecommend(0);
		}
		resourceManager.save(entity);
		resourceType = entity.getType();
		return RELOAD;
	}

	/**
	 * 下载搜索
	 * 
	 * @return
	 * @throws Exception
	 */
	public String searchResource() throws Exception {
		page = resourceManager.getResourceSearch(page, resourceName);
		return SUCCESS;
	}

	public DownloadResource getModel() {
		return entity;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public DownloadResource getEntity() {
		return entity;
	}

	public void setEntity(DownloadResource entity) {
		this.entity = entity;
	}

	public DownloadType getResourceType() {
		return resourceType;
	}

	public void setResourceType(DownloadType resourceType) {
		this.resourceType = resourceType;
	}

	public Page<DownloadResource> getPage() {
		return page;
	}

	public void setPage(Page<DownloadResource> page) {
		this.page = page;
	}

	public Long[] getIds() {
		return ids;
	}

	public void setIds(Long[] ids) {
		this.ids = ids;
	}

	public List<DownloadType> getTypeList() {
		return typeList;
	}

	public void setTypeList(List<DownloadType> typeList) {
		this.typeList = typeList;
	}

	public Boolean getRecom() {
		return recom;
	}

	public void setRecom(Boolean recom) {
		this.recom = recom;
	}

	public String getResourceName() {
		return resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

}
