 /*
 * Light Portal
 *
 * Copyright (c) 2009, Light Portal, Inc or third-party contributors as
 * indicated by the @author tags or express copyright attribution
 * statements applied by the authors.  All third-party contributions are
 * distributed under license by Light Portal, Inc.
 *
 * This copyrighted material is made available to anyone wishing to use, modify,
 * copy, or redistribute it subject to the terms and conditions of the GNU
 * Lesser General Public License, as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Lesser General Public License
 * for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this distribution; if not, write to:
 * Free Software Foundation, Inc.
 * 51 Franklin Street, Fifth Floor
 * Boston, MA  02110-1301  USA
 *
 */

package org.light.portal.util;

/**
 * 
 * @author Jianmin Liu
 **/
public class HTMLUtil {

	public static String removeScripts(String content){
		if(StringUtil.isEmpty(content)) return content;
		String reg = "(?:<script.*?>)((\n|\r|.)*?)(?:</script>)";		
		content = content.replaceAll(reg,"");
		reg = "(?:<SCRIPT.*?>)((\n|\r|.)*?)(?:</SCRIPT>)";		
		content = content.replaceAll(reg,"");
		reg = "alert\\(((\n|\r|.)*?)\\)";
		content = content.replaceAll(reg,"");
		return content;
	}
	
	public static String disableScripts(String content){
		if(StringUtil.isEmpty(content)) return content;
		String newContent="";
		String temp = "";
		int index = content.toLowerCase().indexOf("<script");
		while(index >= 0){
			newContent = content.substring(0,index)+"&lt;";
			temp = content.substring(index+1);
			int index2 = temp.indexOf(">");
			if(index2 >= 0) newContent+=temp.substring(0,index2)+"&gt;";
			temp = temp.substring(index2+1);
			index2 = temp.toLowerCase().indexOf("</script>");
			if(index2 >= 0) newContent += temp.substring(0,index2)+"&lt;/script&gt;"+temp.substring(index2+9);
			content = newContent;
			index = content.toLowerCase().indexOf("<script");
		}
		
		String reg = "alert\\(((\n|\r|.)*?)\\)";
		content = content.replaceAll(reg,"");
		return content;
	}
	
	public static String disableHTML(String content){
		if(StringUtil.isEmpty(content)) return content;
		String reg = "<";		
		content = content.replaceAll(reg,"&lt;");
		reg = ">";		
		content = content.replaceAll(reg,"&gt;");		
		reg = "alert\\(((\n|\r|.)*?)\\)";
		content = content.replaceAll(reg,"");
		return content;
	}
	
	public static String removeHTML(String content){
		if(StringUtil.isEmpty(content)) return content;
		content = removeScripts(content);
		String reg = "(?:<a.*?>)((\n|\r|.)*?)(?:</a>)";
		content = content.replaceAll(reg,"");		
		reg = "<.*?>";
		content = content.replaceAll(reg,"");
		reg = "\n+";
		content = content.replaceAll(reg," ");
		return content.trim();
	}	
}
