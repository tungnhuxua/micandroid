package org.javaside.cms.core;

import java.util.HashSet;
import java.util.Set;

/**
 * 存放CMS的所有action,以正则表达式形式存放
 * 
 * @author zhouxh
 */
public class CmsActionSet {
	public static Set<String> set = new HashSet<String>();
	static {
		set.add("/admin.*");
		set.add("/blog.*");
		set.add("/circle.*");
		set.add("/comment.*");
		set.add("/download.*");
		set.add("/get-username.*");
		set.add("/home.*");
		set.add("/login.*");
		set.add("/member.*");
		set.add("/news.*");
		set.add("/person.*");
		set.add("/search.*");
		set.add("/show.*");
		set.add("/special.*");
		set.add("/tag.*");
		set.add("/world.*");
		set.add("/article.*");
		set.add("/common.*");
		set.add("/css.*");
		set.add("/fckeditor.*");
		set.add("/images.*");
		set.add("/js.*");
		set.add("/index.*");
		set.add("/ding.*");
		set.add("/del.*");
		set.add("/downfile.*");
		set.add("/enterCircle.*");
		set.add("/check.*");
		set.add("/WEB-INF.*");
		set.add("/upload.*");
		set.add("/UploadImages.*");
		set.add("/userfiles.*");
	}
}
