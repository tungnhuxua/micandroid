package org.javaside.cms.web.admin;

import java.util.List;

import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.javaside.cms.core.CRUDActionSupport;
import org.javaside.cms.core.Page;
import org.javaside.cms.entity.AdsLocation;
import org.javaside.cms.entity.Category;
import org.javaside.cms.service.AdsLocationManager;
import org.javaside.cms.service.AdsManager;
import org.javaside.cms.service.CategoryManager;
import org.springframework.beans.factory.annotation.Autowired;

@SuppressWarnings("serial")
@Results( { @Result(name = CRUDActionSupport.RELOAD, location = "ads-location.action?page.pageRequest=${page.pageRequest}&id=${entity.parent.id}", type = "redirect") })
public class AdsLocationAction extends CRUDActionSupport<AdsLocation> {

	@Autowired
	private AdsLocationManager adsLocationManager;
	@Autowired
	private AdsManager adsManager;
	@Autowired
	private CategoryManager categoryManager;

	private Long id;
	private AdsLocation entity;
	private Page<AdsLocation> page = new Page<AdsLocation>(10);
	private List<AdsLocation> tree;
	private Long[] ids; //广告位置ID集合
	private Integer[] sorts; //广告位置排序值
	private Long[] sortids; //广告位置排序ID数组	
	private String action = "ads-location"; //点击广告位时要转发的ACTION
	private String actionParam = "id"; //点击广告位时要转发的ACTION的参数
	private List<Category> categoryList; //文章栏目下拉菜单

	/**
	 * 排序
	 * 
	 * @return
	 * @throws Exception
	 */
	public String sort() throws Exception {
		adsLocationManager.updateSort(sortids, sorts);
		return RELOAD;
	}

	/**
	 * 广告位树菜单
	 * 
	 * @return
	 * @throws Exception
	 */
	public String tree() throws Exception {
		tree = adsLocationManager.getLocationRoot();
		return "tree";
	}

	@Override
	public String delete() throws Exception {
		adsLocationManager.delete(id);
		return RELOAD;
	}

	public String deleteBatch() throws Exception {
		adsLocationManager.deleteBatch(ids);
		return RELOAD;
	}

	@Override
	public String list() throws Exception {
		page.setOrderBy("sort");
		page = adsLocationManager.getAdsLocationList(entity, page);
		return SUCCESS;
	}

	/**
	 * 执行ececute方法前进行二次绑定,execute默认执行list方法
	 * 
	 * @throws Exception
	 */
	public void prepareExecute() throws Exception {
		prepareModel();
	}

	@Override
	public String input() throws Exception {
		//新增广告位时重新导入父广告位
		if (entity.getId() == null && entity.getParent() != null && entity.getParent().getId() != null) {
			entity.setParent(adsLocationManager.get(entity.getParent().getId()));
		}
		categoryList = categoryManager.getCategoryRoot();
		return INPUT;
	}

	@Override
	protected void prepareModel() throws Exception {
		if (id != null) {
			entity = adsLocationManager.get(id);
		} else {
			entity = new AdsLocation();
		}
	}

	@Override
	public String save() throws Exception {
		//防止引用已实例但ID为空的父广告位
		if (entity.getParent().getId() == null) {
			entity.setParent(null);
		}
		adsLocationManager.save(entity);
		return RELOAD;
	}

	public AdsLocation getModel() {
		return entity;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public AdsLocation getEntity() {
		return entity;
	}

	public void setEntity(AdsLocation entity) {
		this.entity = entity;
	}

	public Page<AdsLocation> getPage() {
		return page;
	}

	public void setPage(Page<AdsLocation> page) {
		this.page = page;
	}

	public List<AdsLocation> getTree() {
		return tree;
	}

	public void setTree(List<AdsLocation> tree) {
		this.tree = tree;
	}

	public Long[] getIds() {
		return ids;
	}

	public void setIds(Long[] ids) {
		this.ids = ids;
	}

	public Integer[] getSorts() {
		return sorts;
	}

	public void setSorts(Integer[] sorts) {
		this.sorts = sorts;
	}

	public Long[] getSortids() {
		return sortids;
	}

	public void setSortids(Long[] sortids) {
		this.sortids = sortids;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getActionParam() {
		return actionParam;
	}

	public void setActionParam(String actionParam) {
		this.actionParam = actionParam;
	}

	public List<Category> getCategoryList() {
		return categoryList;
	}

	public void setCategoryList(List<Category> categoryList) {
		this.categoryList = categoryList;
	}

}
