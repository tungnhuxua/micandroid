package org.javaside.cms.web.admin;

import java.util.List;

import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.javaside.cms.core.CRUDActionSupport;
import org.javaside.cms.core.Page;
import org.javaside.cms.entity.DownloadType;
import org.javaside.cms.service.DownloadTypeManager;
import org.springframework.beans.factory.annotation.Autowired;

@SuppressWarnings("serial")
@Results( { @Result(name = CRUDActionSupport.RELOAD, location = "download-type.action?page.pageRequest=${page.pageRequest}&id=${entity.parent.id}", type = "redirect") })
public class DownloadTypeAction extends CRUDActionSupport<DownloadType> {

	@Autowired
	private DownloadTypeManager typeManager;

	private DownloadType entity; //下载分类实体
	private Long id; //分类ID
	private Long[] ids; //分类ID数组
	private Integer[] sorts; //分类排序值
	private Long[] sortids; //分类排序ID数组		
	private Page<DownloadType> page = new Page<DownloadType>(10); //每页10条记录
	private List<DownloadType> tree; //下载分类菜单树
	private String action = "download-type"; //点击分类时要转发的ACTION
	private String actionParam = "id"; //点击分类时要转发的ACTION的参数

	public String sort() throws Exception {
		typeManager.updateSort(sortids, sorts);
		return RELOAD;
	}

	/**
	 * 栏目菜单树
	 * 
	 * @return
	 * @throws Exception
	 */
	public String tree() throws Exception {
		tree = typeManager.getDownloadTypeRoot();
		return "tree";
	}

	@Override
	public String delete() throws Exception {
		typeManager.delete(id);
		return RELOAD;
	}

	/**
	 * 批量删除
	 * 
	 * @return
	 * @throws Exception
	 */
	public String deleteBatch() throws Exception {
		typeManager.deleteBatch(ids);
		return RELOAD;
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
	public String list() throws Exception {
		page.setOrderBy("sort");
		page = typeManager.getDownloadTypeList(entity, page);
		return SUCCESS;
	}

	@Override
	protected void prepareModel() throws Exception {
		if (id != null) {
			entity = typeManager.get(id);
		} else {
			entity = new DownloadType();
		}
	}

	@Override
	public String input() throws Exception {
		//新增下载分类时重新导入父下载分类
		if (entity.getId() == null && entity.getParent() != null && entity.getParent().getId() != null) {
			entity.setParent(typeManager.get(entity.getParent().getId()));
		}
		return INPUT;
	}

	@Override
	public String save() throws Exception {
		if (entity.getParent().getId() == null) {
			entity.setParent(null);
		}
		typeManager.save(entity);
		return RELOAD;
	}

	public DownloadType getModel() {
		// TODO Auto-generated method stub
		return entity;
	}

	public DownloadType getEntity() {
		return entity;
	}

	public void setEntity(DownloadType entity) {
		this.entity = entity;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Page<DownloadType> getPage() {
		return page;
	}

	public void setPage(Page<DownloadType> page) {
		this.page = page;
	}

	public List<DownloadType> getTree() {
		return tree;
	}

	public void setTree(List<DownloadType> tree) {
		this.tree = tree;
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

}
