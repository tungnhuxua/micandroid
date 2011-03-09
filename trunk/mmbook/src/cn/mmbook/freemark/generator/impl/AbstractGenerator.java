package cn.mmbook.freemark.generator.impl;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import cn.mmbook.freemark.constant.StringTemplateLoader;
import cn.mmbook.freemark.generator.Generator;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public abstract class AbstractGenerator implements Generator {

	 
	
	/**
	 * 生成
	 * @param String templateFileName 模板路径
	 * @param data 要加载到模板的数据集合
	 * @param fileName 生成的文件名称
	 */
	public void generate(String templateFileName, Map data, String fileName) {
		try {
			String templateFileDir=templateFileName.substring(0, templateFileName.lastIndexOf("/"));
			String templateFile=templateFileName.substring(templateFileName.lastIndexOf("/")+1, templateFileName.length());
			  // Get the templat object
			String genFileDir=fileName.substring(0, fileName.lastIndexOf("/"));
			Configuration cfg = new Configuration();
			cfg.setClassForTemplateLoading(this.getClass(), templateFileDir);
			cfg.setEncoding(Locale.getDefault(), "UTF-8"); 

	        System.out.println("===================");
			System.out.println("templateFileDir="+templateFileDir);
			System.out.println("templateFile="+templateFile);
			System.out.println("genFileDir="+genFileDir);
			Template template = cfg.getTemplate(templateFile);

	        org.apache.commons.io.FileUtils.forceMkdir(new File(genFileDir));
	        File output = new File(fileName);
	        Writer writer = new FileWriter(output);
	        template.process(data, writer);
	        writer.close();
		} catch (TemplateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 生成
	 * @param data 要加载到模板的数据集合
	 * @param fileName 生成的文件名称
	 * @param templateContent 模板内容 字符串
	 */
	public void generate(  Map data, String fileName,String templateContent) {
		try {
			Configuration cfg = new Configuration();   
	        cfg.setTemplateLoader(new StringTemplateLoader (templateContent));   
	        cfg.setDefaultEncoding("UTF-8");    
	        Template template = cfg.getTemplate("");   
			
	        String genFileDir=fileName.substring(0, fileName.lastIndexOf("/"));
	        org.apache.commons.io.FileUtils.forceMkdir(new File(genFileDir));
	        File output = new File(fileName);
	        Writer writer = new FileWriter(output);
	        template.process(data, writer);
	        writer.close();
		} catch (TemplateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	protected String package2path(String packageName) {
		return packageName.replace('.', '/');
	}

	protected String  getFileName(String filePath) {
		String fileName=StringUtils.substringAfterLast(filePath, "/");
		 if(fileName.equals("")||fileName==null){
			 fileName=StringUtils.substringAfterLast(filePath, "\\");
		 }
		 return fileName;
	}

	protected String capFirst(String string) {
		String s = String.valueOf(string.charAt(0)).toUpperCase();
		s = s + string.substring(1);
		return s;
	}

	protected String uncapFirst(String string) {
		String s = String.valueOf(string.charAt(0)).toLowerCase();
		s = s + string.substring(1);
		return s;
	}
}
