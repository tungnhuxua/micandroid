package net.nbol.template;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import net.nbol.cms.model.News;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import freemarker.template.Template;
import freemarker.template.TemplateException;



/**
 * 功能描述：根据模板生成静态文件
 * 创建时间：2011-1-4 上午05:17:05
 * 版权信息：宁波在线
 * @author zoopnin
 * @version 1.0
 */

public class FreemarkerGenerator {
	private  Logger logger = LoggerFactory.getLogger(getClass()) ;
	
	/*** freemarker配置. */
    private FreeMarkerConfigurer freemarkerConfig = null;
    
    
    /**
     * 生成静态页面.
     *
     * @param news 新闻
     * @param page 开始页
     * @param pageSize 页面大小
     * @param root 根目录
     * @param ctx contextPath(上下文路径)
     * @param templateName 模板名称
     */
    public void createNews(News news,int page,int pageSize,String root,String ctx,String templateName){
    	logger.info("静态页面生成开始...") ;
    	
    	Date date = news.getUpdateDate() ;
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
    	// root是用getRealPath("/")获得的根目录，将html保存在网站根目录下，通过tomcat直接访问
        String fileName = null;
        if(news.getNewsCategory() == null){
        	fileName = root + "/html/0/" + sdf.format(date) + "/" + news.getId() +".html" ;
        }else{
        	fileName = root + "/html/" + news.getNewsCategory().getId() + "/" +sdf.format(date) + "/" + news.getId() + ".html" ;
        }
        System.out.println(fileName) ;
        Map<String,Object> model = new HashMap<String, Object>() ;
        model.put("news", news);
        model.put("ctx", ctx) ;
    }
	
    /**
     * 功能：根据模板文件生成静态文件
     * @param templateName 模板文件名
     * @param fileName 静态文件文件名（同时保存路径信息）
     * @param map 封装生成静态文件需要的参数
     * 
     */
	public void template2File(String templateName,String fileName,Map<String,Object> map){
		try{
			Template t = freemarkerConfig.createConfiguration().getTemplate(templateName) ;
			
			String result = FreeMarkerTemplateUtils.processTemplateIntoString(t, map) ;
			
			File file = new File(fileName) ;
			if(!file.getParentFile().exists()){
				file.getParentFile().mkdirs() ;
			}
			
			PrintWriter out = new PrintWriter(new OutputStreamWriter(
                    new FileOutputStream(fileName), "UTF-8"));
			out.println(result);
            out.flush();
            out.close();
		}catch (IOException ex) {
            ex.printStackTrace();
            logger.error("生成静态文件目录异常！发生时间："+ new Date()) ;
        } catch (TemplateException ex) {
            ex.printStackTrace();
            logger.error("模板文件异常！发生时间："+ new Date()) ;
        }
	}

}
