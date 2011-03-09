package org.javaside.cms.web.admin;

import java.util.List;

import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.javaside.cms.core.CRUDActionSupport;
import org.javaside.cms.core.Page;
import org.javaside.cms.entity.Category;
import org.javaside.cms.service.CategoryManager;
import org.springframework.beans.factory.annotation.Autowired;

@SuppressWarnings("serial")
@Results( { @Result(name = CRUDActionSupport.RELOAD, location = "category.action?page.pageRequest=${page.pageRequest}&id=${entity.parent.id}", type = "redirect") })
public class CategoryAction extends CRUDActionSupport<Category> {
	@Autowired
	private CategoryManager categoryManager;

	private Category entity; //栏目实体
	private Long id; //栏目ID
	private Long[] ids; //栏目ID数组
	private Integer[] sorts; //栏目排序值
	private Long[] sortids; //栏目排序ID数组		
	private Page<Category> page = new Page<Category>(10); //每页10条记录
	private List<Category> tree; //栏目菜单树
	private String action = "category"; //点击栏目数时要转发的ACTION
	private String actionParam = "id"; //点击栏目数时要转发的ACTION的参数

	public String sort() throws Exception {
		categoryManager.updateSort(sortids, sorts);
		return RELOAD;
	}

	/**
	 * 栏目菜单树
	 * 
	 * @return
	 * @throws Exception
	 */
	public String tree() throws Exception {
		tree = categoryManager.getCategoryRoot();
		return "tree";
	}

	@Override
	public String delete() throws Exception {
		categoryManager.delete(id);
		return RELOAD;
	}

	public String deleteBatch() throws Exception {
		categoryManager.deleteBatch(ids);
		return RELOAD;
	}

	@Override
	public String list() throws Exception {
		page.setOrderBy("sort");
		page = categoryManager.getCategoryList(entity, page);
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
	protected void prepareModel() throws Exception {
		if (id != null) {
			entity = categoryManager.get(id);
		} else {
			entity = new Category();
		}
	}

	@Override
	public String input() throws Exception {
		//新增栏目时重新导入父栏目
		if (entity.getId() == null && entity.getParent() != null && entity.getParent().getId() != null) {
			entity.setParent(categoryManager.get(entity.getParent().getId()));
		}
		return INPUT;
	}

	@Override
	public String save() throws Exception {
		if (entity.getParent().getId() == null) {
			entity.setParent(null);
		}
		categoryManager.save(entity);
		return RELOAD;
	}

	public Category getModel() {
		return entity;
	}

	public Category getEntity() {
		return entity;
	}

	public void setEntity(Category entity) {
		this.entity = entity;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Page<Category> getPage() {
		return page;
	}

	public void setPage(Page<Category> page) {
		this.page = page;
	}

	public List<Category> getTree() {
		return tree;
	}

	public void setTree(List<Category> tree) {
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
}
