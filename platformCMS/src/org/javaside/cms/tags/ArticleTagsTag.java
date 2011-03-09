package org.javaside.cms.tags;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * 文章标签
 * 
 * @author huage
 */
public class ArticleTagsTag extends TagSupport {

	private String href;
	private String value;

	@Override
	public int doEndTag() throws JspException {
		if (value != null) {
			value = value.replace("，", ",");
			String[] tags = value.split(",");
			try {
				for (String tag : tags) {
					pageContext.getOut().write("<a href=\"" + href + "\">" + tag + "</a>" + "&nbsp;&nbsp;");
				}
			} catch (Exception e) {
				e.printStackTrace();
				return SKIP_PAGE;
			}
		}
		return super.doEndTag();
	}

	public void setValue(String value) {
		this.value = value;
	}

	public void setHref(String href) {
		this.href = href;
	}
}
