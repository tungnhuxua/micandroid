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

package org.light.portal.search;

import static org.light.portal.util.Constants._FILE_PATH;
import static org.light.portal.util.Constants._INDEX_PATH;
import static org.light.portal.util.Constants._LUCENE_ANALYZER;
import static org.light.portal.util.Constants._LUCENE_RESULT_TOP;
import static org.light.portal.util.Constants._LUCENE_HIGHLIGHTER_PREFIX;
import static org.light.portal.util.Constants._LUCENE_HIGHLIGHTER_SUFFIX;

import java.lang.reflect.Constructor;
import java.util.regex.Pattern;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.util.Version;
import org.light.portal.model.Organization;
import org.light.portal.util.OrganizationThreadLocal;
import org.light.portal.util.PropUtil;

/**
 * 
 * @author Jianmin Liu
 **/
public class SearcherUtil {
	private static final String LUCENE_ESCAPE_CHARS = "[\\\\!\\(\\)\\:\\^\\]\\{\\}\\~\\*\\?\\'\",=]|\\. |\\.$";//"[\\\\+\\-\\!\\(\\)\\:\\^\\]\\{\\}\\~\\*\\?\\'\",.=]";
	private static final Pattern escapePattern = Pattern.compile(LUCENE_ESCAPE_CHARS);
	private static final String REPLACEMENT_STRING = " ";
	
	public static String escape(String keyword){
		if(keyword == null) return null;
		return escapePattern.matcher(keyword).replaceAll(REPLACEMENT_STRING).trim();			
	}
	
	public static String getKeyword(String keyword){
		if(keyword == null) return null;	
		keyword = keyword.trim();
		if(isQuoted(keyword)) return keyword;
		if(keyword.length() > 0){
			keyword += allowWildcard(keyword) ? "*" : "";
			if(keyword.indexOf(" ") > 0){				
				String[] words = keyword.split(" ");
				StringBuilder buffer = new StringBuilder();
				for(String word : words){
					if("".equals(word)) continue;
					if(!word.equalsIgnoreCase("AND") || !word.equalsIgnoreCase("OR") || !word.equalsIgnoreCase("NOT")){
						if(word.startsWith("+")) word=word.substring(1);
						if(word.startsWith("-")){ 
							word=word.substring(1);
							buffer.append(" AND NOT ");
						}else if(buffer.length() > 0){							
							 buffer.append(" AND ");
						}
						buffer.append(word);
					}else if(word.equalsIgnoreCase("AND")){
						if(buffer.length() > 0) buffer.append(" AND ");
					}else if(word.equalsIgnoreCase("OR")){
						if(buffer.length() > 0) buffer.append(" OR ");
					}else if(word.equalsIgnoreCase("NOT")){
						if(buffer.length() > 0) buffer.append(" NOT ");
					}
				}
				return buffer.toString();
			}
		}
		return keyword;
	}
	
	private static boolean allowWildcard(String keyword){
		return 	!keyword.endsWith("*") 
		 && !keyword.endsWith("~") 
		 && (keyword.indexOf("~")<0 
				 || (keyword.indexOf("~")>0
			         && !Character.isDigit(keyword.charAt(keyword.indexOf("~")+1))
				     )
			)
		 ;
	}
	
	private static boolean isQuoted(String keyword){
		return keyword.startsWith("\"") && keyword.endsWith("\"");
	}
	
	public static Analyzer getAnalyzer(){
		Analyzer analyzer = null;
		try{
			Constructor c = Class.forName(PropUtil.getString(_LUCENE_ANALYZER)).getConstructor(new Class[]{Version.class});
			analyzer = (Analyzer)c.newInstance(new Object[]{Version.LUCENE_30});
		}catch(Exception e){
			throw new RuntimeException(e);
		}
		return analyzer;
	}
	
	public static String getPath(Organization org){
		long orgId = 1;
		if(org == null) org = OrganizationThreadLocal.getOrg();
		if(org != null) orgId = org.getId();
		return getPath(orgId);
	}
	public static String getPath(long orgId){
		return _FILE_PATH+orgId+System.getProperty("file.separator")+_INDEX_PATH;
	}
	
	public static int top(){
		int top = 1024;
		try{
			top =PropUtil.getInt(_LUCENE_RESULT_TOP); 
		}catch(Exception e){}
		return top;
	}
	
	public static String getDateFormat(){
		return "yyyy/MM/dd HH:mm:ss:SSS";
	}
	
	public static String getHighlighterPrefix(){
		return PropUtil.getString(_LUCENE_HIGHLIGHTER_PREFIX);
	}
	
	public static String getHighlighterSuffix(){
		return PropUtil.getString(_LUCENE_HIGHLIGHTER_SUFFIX);
	}
}
