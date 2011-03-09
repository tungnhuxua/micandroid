package javacommon.util;

/**
 * 对字符串操作工具类
 * 暂时只加了字符串转LIST和转数组 
 * javacommon.util.StringTool
 */

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
 

public class StringTool {
	/**
	 * 把一个以逗号分隔的String转成ArrayList
	 */
	public static ArrayList stringToArray(String liststr) throws Exception {
		return stringToArray(liststr, ",");
	}

	/**
	 * 把一个以compart分隔的String转成ArrayList
	 */
	public static ArrayList stringToArray(String liststr, String compart)
			throws Exception {
		try {
			ArrayList temparray = new ArrayList();
			int pos = 0;
			String tempstr = liststr;
			if (tempstr.equals(""))
				return null;
			String temp = null;
			while (tempstr.length() > 0) {
				pos = tempstr.indexOf(compart);
				if (pos < 0) {
					temparray.add(tempstr);
					break;
				}
				temp = tempstr.substring(0, pos);
				if (temp != null)
					temparray.add(temp);
				tempstr = tempstr.substring(pos + 1);
			}
			return temparray;
		} catch (Exception e) {
			// e.printStackTrace();
			throw e;
		}
	}

	// ==========================================================================//
	// Function strCut
	// 分割字串为数组
	// ==========================================================================//
	public static Integer[] strCut(String as_str, String as_split) {
		Integer[] ls_ret = null, ls_arytmp = null;
		String ls_str, ls_split, ls_tmp = "";
		ls_str = as_str;
		ls_split = as_split;
		int li_pos;
		int li_group = 0;
		int li_orgpos = 0;
		// 计算数组大小
		if (as_str == null)
			return ls_ret;
		if (as_str.equals("")) {
			return ls_ret;
		}

		li_group = 0;
		while (ls_str.length() > 0) {
			li_group++;
			if (ls_ret != null) {
				ls_arytmp = new Integer[ls_ret.length];
				ls_arytmp = ls_ret;
				ls_ret = new Integer[li_group];
				for (int i = 0; i < ls_arytmp.length; i++) {
					ls_ret[i] = ls_arytmp[i];
				}
			} else {
				ls_ret = new Integer[li_group];
			}
			li_pos = ls_str.indexOf(ls_split);
			if (li_pos > 0) {
				ls_ret[li_group - 1] = Integer.valueOf(
						ls_str.substring(0, li_pos)).intValue();
				ls_str = ls_str.substring(li_pos + ls_split.length());
			} else {
				if (!ls_str.equals("")) {
					ls_ret[li_group - 1] = Integer.valueOf(ls_str.trim())
							.intValue();
					ls_str = "";
				}
			}
		}
		return ls_ret;
	}

 
 

	/**
	 * 格式化<img />标签 tls
	 */
	public static String formatPath(String text) {
		String regex = "<img\\s\\S+(\\.\\w{3,4})\"?>";
		Pattern p = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(text);
		StringBuffer sb = new StringBuffer();
		while (m.find()) {
			String src = m.group();
			src = src.substring(0, src.length() - 1);
			src += " />";
			m.appendReplacement(sb, src);
		}
		m.appendTail(sb);
		return sb.toString();
	}

	/**
	 * 格式化<br>
	 * 标签 tls
	 */
	public static String formatBr(String text) {
		Pattern p = Pattern.compile("<br>", Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(text);
		StringBuffer sb = new StringBuffer();
		while (m.find()) {
			m.appendReplacement(sb, "<br />");
		}
		m.appendTail(sb);
		return sb.toString();
	}

 

	/**
	 * 过滤除<a>、<br/>、<img/>之外的HTML标签
	 * 
	 * @param htmlStr
	 * @return
	 * @author HLJ
	 */
	public static String filterHtmlTag(String htmlStr) {
		// 过滤HTML注释
		Pattern p1 = Pattern.compile("<!--.*?-->");
		Matcher m1 = p1.matcher(htmlStr);
		while (m1.find()) {
			htmlStr = htmlStr.replace(m1.group(0), "");
		}
		// 过滤<script>
		Pattern p2 = Pattern.compile("<script[^>]*?>[\\s\\S]*?<\\/script>",
				Pattern.CASE_INSENSITIVE);
		Matcher m2 = p2.matcher(htmlStr);
		while (m2.find()) {
			htmlStr = htmlStr.replace(m2.group(0), "");
		}
		// 过滤<style>
		Pattern p3 = Pattern.compile("<style[^>]*?>[\\s\\S]*?<\\/style>",
				Pattern.CASE_INSENSITIVE);
		Matcher m3 = p3.matcher(htmlStr);
		while (m3.find()) {
			htmlStr = htmlStr.replace(m3.group(0), "");
		}
		// 过滤<object>
		Pattern p4 = Pattern.compile("<object[^>]*?>[\\s\\S]*?<\\/object>",
				Pattern.CASE_INSENSITIVE);
		Matcher m4 = p4.matcher(htmlStr);
		while (m4.find()) {
			htmlStr = htmlStr.replace(m4.group(0), "");
		}

		// 过滤HTML标签 <[^>]*> 或 <(.[^>]*)>
		Pattern p5 = Pattern.compile("<[^>]*>");
		Matcher m5 = p5.matcher(htmlStr);
		while (m5.find()) {
			System.out.println("aa" + m5.group(0));
			// String tagStr = matcher.group(0).toLowerCase();
			if (m5.group(0).matches("<br(.*)/>")) {
				// contentStr = contentStr.replace(matcher.group(0),

			} else if (m5.group(0).matches("<a\\s+href(.*)>")) {

			} else if (m5.group(0).matches("</a>")) {

			} else if (m5.group(0).matches("<img\\s+src(.*)/>")) {

			} else if (m5.group(0).matches("</p>")) {
				htmlStr = htmlStr.replace(m5.group(0), "<br />");
			} else if (m5.group(0).matches("</P>")) {
				htmlStr = htmlStr.replace(m5.group(0), "<br />");
			} else {
				htmlStr = htmlStr.replace(m5.group(0), "");
			}
		}
		return htmlStr;
	}

	/**
	 * 判断字符串是否为空
	 * 
	 * @return
	 */
	public static boolean isNull(String str) {
		if (null == str) {
			return false;
		} else if ("".equals(str)) {
			return false;
		} else if ("null".equals(str)) {
			return false;
		} else if (str.length() <= 0) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * 判断字符串是否为空
	 * 
	 * @return
	 */
	public static String UtfToGbk(String str) {
		 try{
			 if(isNull(str)){
				 str=new String((str).trim().getBytes(),"UTF-8");
				 //str=new String(str.trim().getBytes(),"UTF-8");
				 //str=new String(str.trim().getBytes("ISO-8859-1"), "gb2312");
			 }
		 }catch(Exception exe){
			 exe.printStackTrace();
		 }
		 return str;
	}
	
	public static void main(String[] xxx){
		StringTool xx = new StringTool();
		System.out.println(xx.UtfToGbk("鍚岀敇鍏辫嫤宸ュ湪"));
	}

	public byte[] gbk2utf8(String chenese) {
		char c[] = chenese.toCharArray();
		byte[] fullByte = new byte[3 * c.length];
		for (int i = 0; i < c.length; i++) {
			int m = (int) c[i];
			String word = Integer.toBinaryString(m);
			// System.out.println(word);

			StringBuffer sb = new StringBuffer();
			int len = 16 - word.length();
			// 补零
			for (int j = 0; j < len; j++) {
				sb.append("0");
			}
			sb.append(word);
			sb.insert(0, "1110");
			sb.insert(8, "10");
			sb.insert(16, "10");

			// System.out.println(sb.toString());

			String s1 = sb.substring(0, 8);
			String s2 = sb.substring(8, 16);
			String s3 = sb.substring(16);

			byte b0 = Integer.valueOf(s1, 2).byteValue();
			byte b1 = Integer.valueOf(s2, 2).byteValue();
			byte b2 = Integer.valueOf(s3, 2).byteValue();
			byte[] bf = new byte[3];
			bf[0] = b0;
			fullByte[i * 3] = bf[0];
			bf[1] = b1;
			fullByte[i * 3 + 1] = bf[1];
			bf[2] = b2;
			fullByte[i * 3 + 2] = bf[2];

		}
		return fullByte;
	}

}
