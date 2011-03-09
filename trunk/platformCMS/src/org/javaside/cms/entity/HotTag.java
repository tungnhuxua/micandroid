package org.javaside.cms.entity;

/**
 * 非持久类，用与存放热门标签信息
 * 
 * @author zhouxinghua
 */
public class HotTag {
	private Tag tag; //标签
	private Integer count; //关联此标签的文章数量
	private Float fontSize;

	public Tag getTag() {
		return tag;
	}

	public void setTag(Tag tag) {
		this.tag = tag;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Float getFontSize() {
		return fontSize;
	}

	public void setFontSize(Float fontSize) {
		this.fontSize = fontSize;
	}
}
