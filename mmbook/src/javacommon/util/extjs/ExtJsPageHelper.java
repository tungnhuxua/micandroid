package javacommon.util.extjs;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.util.WebUtils;

import cn.org.rapid_framework.page.PageRequest;

public class ExtJsPageHelper {
	static int DEFAULT_PAGE_SIZE = 10;
	
	public static PageRequest<Map> createPageRequestForExtJs(HttpServletRequest request,String defaultOrderBy) {
		PageRequest<Map> result = new PageRequest(new HashMap());
		return bindPageRequestParameters(result, request, defaultOrderBy);
	}

	public static PageRequest<Map> bindPageRequestParameters(PageRequest<Map> pageRequest, HttpServletRequest request,String defaultOrderBy) {
		int start = getIntParameter(request,"start",0);
		int limit = getIntParameter(request, "limit", DEFAULT_PAGE_SIZE);
		String field_type = request.getParameter("field_type");
		String query = request.getParameter("query"); //SearchField.js 里设置此参数名
		String sort = request.getParameter("sort");// 指定排序的列
		 
	 
		String dir = request.getParameter("dir");// 顺序倒序
		String orderBy = defaultOrderBy; //默认正向排序列 
	    System.out.println("sort = "+sort+" , dir="+dir);	
		if (sort != null && dir != null){
			
			/*
			 * 因为有些对象会关联其它的对象，这样我们取关联对象的相关属性值的时候就会出现
			 * object.property这样的格式，而index页面排序的时候由于直接传参数到后台，
			 * 导致sql语句会出现ORDER BY object.property这样的情况，这在oracle是不
			 * 允许的，所以如果排序参数是如上格式，则将.换成_,这样oracle就支持了
			 * qiongguo 2009-5-28
			 */
			if (sort.indexOf(".") != -1){
				//sort = sort.substring(sort.indexOf(".")+1, sort.length());
					//将排序参数中的.换成下划线。
					sort = sort.replaceAll("\\.", "_");
				}
			orderBy = sort + " " + dir;
			}else{
				orderBy = " id_ desc ";
			}
			
			
		
		//如果没有按照指定字段搜索,则按全条件查询	
		if(field_type!=null){
			Map map = new HashMap();
			map.put(field_type, query);
			pageRequest.setFilters(map);
		}
		pageRequest.getFilters().putAll(WebUtils.getParametersStartingWith(request, "s_"));
		
		pageRequest.setPageNumber(start / limit + 1);
		pageRequest.setPageSize(limit);
		pageRequest.setSortColumns(orderBy);
		return pageRequest;
	}
	
	private static int getIntParameter(HttpServletRequest request,String name,int defaultValue) {
		String value = request.getParameter(name);
		if (StringUtils.isNotBlank(value)) {
			return Integer.valueOf(value);
		}
		return defaultValue;
	}
}
