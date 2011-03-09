package org.javaside.cms.tags;

import java.io.IOException;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.javaside.cms.entity.DownloadType;

/**
 * 生成下载类型下拉菜单
 * 
 * @author zhouxinghua
 */
@SuppressWarnings("serial")
public class DownloadTypeSelectTag extends TagSupport {

	private List<DownloadType> list; //栏目树根节点集合
	private String name;
	private Long selected = -1L; //url参数

	@Override
	public int doEndTag() throws JspException {
		try {
			pageContext.getOut().write("<select name=\"" + name + "\">");
			pageContext.getOut().write("<option selected=\"selected\">请选择分类</option>");
			for (DownloadType type : list) {
				printContext(type, 0);
			}
			pageContext.getOut().write("</select>");
		} catch (Exception e) {
			e.printStackTrace();
			return SKIP_PAGE;
		}
		return EVAL_PAGE;
	}

	private void printContext(DownloadType type, int i) throws IOException {
		pageContext.getOut().write("<option value=\"" + type.getId() + "\" ");
		if (type.getId().intValue() == selected.intValue()) {
			pageContext.getOut().write(" selected=\"selected\"");
		}
		pageContext.getOut().write(" >");
		printBlank(i);
		pageContext.getOut().write(type.getName());
		pageContext.getOut().write("</option>");
		if (type.getChild() != null && type.getChild().size() > 0) {
			i++;
			for (DownloadType tmp : type.getChild()) {
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

	public void setList(List<DownloadType> list) {
		this.list = list;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSelected(Long selected) {
		this.selected = selected;
	}
}
