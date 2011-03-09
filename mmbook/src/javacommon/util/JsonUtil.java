package javacommon.util;

import java.util.Hashtable;
import java.util.Map;

/**
 * 处理菜单JSON串
 * 
 * @author pch 2010.5.26
 * 
 */
public class JsonUtil {

	/**
	 * 处理JSON串的辅助MAP
	 */
	private static Map<String, Object> map;
	/**
	 * 特征串开始符号
	 */
	private static String ID_BEGIN = "{'id'";
	/**
	 * 特征串结束符号
	 */
	private static String ID_END = "}";
	/**
	 * 比较的符号
	 */
	public static String FILTER_URL = "url";

	/**
	 * 组装要删除的串
	 * 
	 * @param allString
	 *            总的菜单字符串
	 * @param begin
	 *            要删除的URL在字符串中的开始index
	 * @param end
	 *            要删除的URL在字符串中的结束index
	 * @return map (其中menu:要删除的URL串)
	 */
	private static Map encURL(String allString, int begin, int end) {
		if (map == null) {
			map = new Hashtable<String, Object>();
		}
		for (int j = (begin - 1); j >= 0; j--) {
			// 截取要删除URL之前的字符串
			String bebinStr = allString.substring(0, begin + 1);
			// 查找该URL对应的ID
			int idBegin = bebinStr.indexOf(ID_BEGIN, j);
			if (idBegin >= 0) {
				int idEnd = allString.indexOf(ID_END, end + 1);
				if (idEnd >= 0) {
					String menu = allString.substring(idBegin, idEnd + 1);
					map.put("menu", menu);
					map.put("begin", idBegin);
					map.put("end", idEnd);
					return map;
				}
			}
		}
		return map;
	}

	/**
	 * 对需删除串的前后进行再次处理（二次组装）
	 * 
	 * @param map(menus:进行过滤的总串,menu:需删除的串,begin:menu所在的开始索引,end:menu所在的结束索引)
	 * @return String 返回要删除的串
	 */
	private static String findParent(Map<String, Object> map) {

		String str = (String) map.get("menus");
		String menu = (String) map.get("menu");
		int begin = (Integer) map.get("begin");
		int end = (Integer) map.get("end");
		// 串的尾部处理
		char sufix = 'c';
		String suf = null;
		if (begin >= 0) {
			sufix = str.charAt(end + 1);
			suf = String.valueOf(sufix);
			if (suf.equals(",")) {
				menu = menu + ",";
			} else if (suf.equals("]")) {
				menu = "," + menu;
			}
		}
		// 串的二次处理
		if (end >= 0) {
			char prefix = str.charAt(begin - 1);
			String pre = String.valueOf(prefix);
			if (pre.equals("[")) {
				if (suf.equals("]")) {
					if ((begin - 2) >= 0) {
						map = encURL(str, begin, end);
						menu = findParent(map);
					}
				}
			}
		}

		return menu;
	}

	/**
	 * 处理完成后的串
	 * 
	 * @param value
	 *            需处理的串
	 * @param equalString(url)
	 *            用来比较的串（利用该串组装要删除的串）
	 * @return String 处理完成后的串
	 */
	public static String endValid(String value, String equalString) {
		if (value.length() >= 0) {
			if (equalString.length() >= 0) {
				for (int i = 0; i < value.length(); i++) {
					int urlBegin = value.indexOf(equalString, 0);
					if (urlBegin >= 0) {
						int urlEnd = value.indexOf("'", (urlBegin + equalString
								.length()));
						if (urlEnd >= 0) {
							Map map = encURL(value, urlBegin, urlEnd + 1);
							map.put("menus", value);
							String delete = findParent(map);
							value = value.replace(delete, "");
							return value;
						}
					}
				}
			}
		}
		return null;
	}

}
