package org.javaside.cms.tags;

import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.javaside.cms.util.PageNum;

/**
 * 分页页码标签
 * 
 * @author huage
 */
public class PageNumTag extends TagSupport {
	private Integer totalPages;
	private Integer pageNo;
	private String url;

	@Override
	public int doEndTag() throws JspException {
		try {
			if (totalPages != 0) {
				List<Integer> numList = PageNum.getPageList(totalPages, pageNo);
				for (int i = 0; i < numList.size(); i++) {
					Integer num = numList.get(i);
					if (num.intValue() == -1) {
						pageContext.getOut().print("<SPAN>…</SPAN>");
					} else {
						pageContext.getOut().print("<a href=\"" + url + num + "\">" + num + "</a>&nbsp;");
					}
				}
				pageContext.getOut().print("");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return SKIP_PAGE;
		}
		return super.doEndTag();
	}

	public void setTotalPages(Integer totalPages) {
		this.totalPages = totalPages;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
