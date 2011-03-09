package cn.mmconpay.util;

import javacommon.util.file.FileManagerService;

/**
 * 菜单控制类
 * @author xxj
 *
 */
public class SecurityNemu {
	
	public static String menuStr;
	
	public SecurityNemu(){
		if (menuStr == null) {
			menuStr = FileManagerService.getContent("res/nemu.json");
			System.out.println(menuStr);
			menuStr = FileManagerService.trimSpace(menuStr);
		}
	}
	
	
	
}
