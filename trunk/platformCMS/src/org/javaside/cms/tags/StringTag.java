package org.javaside.cms.tags;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * 字符串截取
 * 
 * @author huage
 */
public class StringTag extends TagSupport {
	private Integer length;
	private String value;

	@Override
	public int doEndTag() throws JspException {
		try {
			value = this.deleteHtmlTag();
			if (value != null && value.length() > length) {

				pageContext.getOut().write(value.substring(0, length) + "...");

			} else {
				pageContext.getOut().write(value);
			}
		} catch (IOException e) {
			e.printStackTrace();
			return SKIP_PAGE;
		}
		return super.doEndTag();
	}

	/**
	 * 过滤HTML标签
	 * 
	 * @return
	 */
	private String deleteHtmlTag() {
		String regEx = "<.*?>";
		Pattern p = Pattern.compile(regEx, Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(value);
		String tmp = m.replaceAll("");
		tmp = tmp.trim();

		String regEx2 = "[\\r\\n\\s]*";
		p = Pattern.compile(regEx2, Pattern.CASE_INSENSITIVE);
		m = p.matcher(tmp);
		tmp = m.replaceAll("");
		tmp = tmp.toString();
		return tmp;
	}

	public void setLength(Integer length) {
		this.length = length;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
