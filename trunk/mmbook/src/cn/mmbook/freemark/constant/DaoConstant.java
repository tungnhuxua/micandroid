package cn.mmbook.freemark.constant;

/***
 * 模板 基本参数配置文件
 * @author xxj 2008-05-16
 * 本地路径信息，不提交
 *
 */

public class DaoConstant {
	
	public static final String INTERFACE_TEMPLATE="GenTemplate/DaoInterface.java.ftl";
	
	public static final String IMPL_TEMPLATE="GenTemplate/DaoImpl.java.ftl";
	
	public static final String PACKAGE="com.trendcom.dao";
	
	public static final String IMPL_PACKAGE="com.trendcom.dao.impl";
	public static final String IMPL_SITEPART="page.web.default";
	public static final String IMPL_WEB="site";
	public static final String SITEPART_TEMPLATE_INDEX="GenTemplate/ftl/tpxw/index.ftl";
	
	/*****************************/
	/**WEB服务器TOMCAT路径*/
	public static final String TOMCATPATH="C:/Program Files/Apache Software Foundation/tomcat-5.5.25/";
	/**项目部署路径*/
	public static final String WEBPATH="C:/Program Files/Apache Software Foundation/tomcat-5.5.25/webapps/mmbook/";
	/**JSP文件生成绝对路径*/
	public static final String JSPPATH="C:/Program Files/Apache Software Foundation/tomcat-5.5.25/webapps/mmbook/site/page/";
	/**项目目录*/
	public static final String WORKPATH="F:/work/MyEclipse6.0/mmbook/";
	/**模板WEB服务器存放绝对路径*/
	public static final String TEMPLETDIR_ABSOLUTE="C:/Program Files/Apache Software Foundation/tomcat-5.5.25/webapps/mmbook/WEB-INF/classes/GenTemplate/";
	/**模板WEB服务器存放相对路径*/
	public static final String TEMPLETDIR_RELATIVE="/GenTemplate/";
}
