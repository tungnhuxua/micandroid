package net.nbol.core.utils;

/**
 * 功能描述：主要记录页面中简单的搜索过滤条件(Criterion).可按项目扩展其他对比方式如大于、小于及其他数据类型如数字和日期.
 * 创建时间：2011-1-5 上午06:51:18
 * 版权信息：宁波在线
 * @author zoopnin
 * @version 1.0
 */

public class PropertyFilter {
	
	public enum MatchType{
		EQUAL,LIKE ;
	}

	private String propertyName;
	private Object value;
	private MatchType matchType = MatchType.EQUAL;

	public PropertyFilter() {
	}
	
	public PropertyFilter(String propertyName, Object value, MatchType matchType) {
		this.propertyName = propertyName;
		this.value = value;
		this.matchType = matchType;
	}
	
	/**
	 * 获取属性名称,可用'|'分隔多个属性,此时属性间是or的关系.
	 */
	public String getPropertyName() {
		return propertyName;
	}

	/**
	 * 设置属性名称,可用'|'分隔多个属性,此时属性间是or的关系.
	 */
	public void setPropertyName(final String propertyName) {
		this.propertyName = propertyName;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(final Object value) {
		this.value = value;
	}

	public MatchType getMatchType() {
		return matchType;
	}

	public void setMatchType(final MatchType matchType) {
		this.matchType = matchType;
	}
	
}
