package org.javaside.cms.tags;

import java.io.IOException;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.javaside.cms.entity.Category;

/**
 * 生成栏目菜单树
 * 
 * @author zhouxinghua
 */
@SuppressWarnings("serial")
public class CategorySelectTag extends TagSupport {

	private List<Category> list; //栏目树根节点集合
	private String name;
	private Long selected = -1L; //url参数

	@Override
	public int doEndTag() throws JspException {
		try {
			pageContext.getOut().write("<select name=\"" + name + "\">");
			pageContext.getOut().write("<option selected=\"selected\">请选择栏目</option>");
			for (Category category : list) {
				printContext(category, 0);
			}
			pageContext.getOut().write("</select>");
		} catch (Exception e) {
			e.printStackTrace();
			return SKIP_PAGE;
		}
		return EVAL_PAGE;
	}

	private void printContext(Category category, int i) throws IOException {
		pageContext.getOut().write("<option value=\"" + category.getId() + "\" ");
		if (category.getId().intValue() == selected.intValue()) {
			pageContext.getOut().write(" selected=\"selected\"");
		}
		pageContext.getOut().write(" >");
		printBlank(i);
		pageContext.getOut().write(category.getName());
		pageContext.getOut().write("</option>");
		if (category.getChild() != null && category.getChild().size() > 0) {
			i++;
			for (Category tmp : category.getChild()) {
				this.printContext(tmp, i);
			}
		}
	}

	private void printBlank(int i) throws IOException {
		while (i > 0) {
			pageContext.getOut().write("&nbsp;&nbsp;");
			i--;
		}
	}

	@Override
	public int doStartTag() throws JspException {
		return SKIP_BODY;
	}

	public void setList(List<Category> list) {
		this.list = list;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSelected(Long selected) {
		this.selected = selected;
	}
}
