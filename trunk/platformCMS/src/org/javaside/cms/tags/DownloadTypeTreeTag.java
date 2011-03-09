package org.javaside.cms.tags;

import java.io.IOException;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.javaside.cms.entity.DownloadType;

/**
 * 生成栏目菜单树
 * 
 * @author zhouxinghua
 */
@SuppressWarnings("serial")
public class DownloadTypeTreeTag extends TagSupport {

	private List<DownloadType> value; //栏目树根节点集合
	private String action; //点击栏目时发送的ACTION
	private String param; //url参数

	@Override
	public int doEndTag() throws JspException {
		try {
			for (DownloadType type : value) {
				printContext(type);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return SKIP_PAGE;
		}
		return EVAL_PAGE;
	}

	private void printContext(DownloadType type) throws IOException {
		pageContext.getOut().write("<li>");
		pageContext.getOut().write(
				"<a target=\"rightFrame\" href=\"" + action + "?" + param + "=" + type.getId() + "\">");
		if (type.getChild() != null && type.getChild().size() > 0) {
			pageContext.getOut().write("<span class=\"folder\">");
		} else {
			pageContext.getOut().write("<span class=\"file\">");
		}
		pageContext.getOut().write(type.getName());
		pageContext.getOut().write("</span>");
		pageContext.getOut().write("</a>");
		if (type.getChild() != null && type.getChild().size() > 0) {
			pageContext.getOut().write("<ul>");
			for (DownloadType tmp : type.getChild()) {
				this.printContext(tmp);
			}
			pageContext.getOut().write("</ul>");
		}
		pageContext.getOut().write("</li>");
	}

	@Override
	public int doStartTag() throws JspException {
		return SKIP_BODY;
	}

	public void setValue(List<DownloadType> value) {
		this.value = value;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public void setParam(String param) {
		this.param = param;
	}
}
