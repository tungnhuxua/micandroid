package cn.mmbook.freemark;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import cn.mmbook.freemark.generator.DaoGenerator;
import cn.mmbook.freemark.generator.Generator;
import cn.mmbook.freemark.generator.impl.DaoGeneratorImpl;
import cn.mmbook.freemark.model.JavaModel;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * 
 * @author HuangYu
 */
public class GenMain {

    private Configuration cfg;
    private String templateDir = "GenTemplate";
    private String templateName = "DaoInterface.java.ftl";
    private String outputDir = "F:/codegen/output";
    
    public GenMain() throws IOException{
        init();
    }

    public void setOutputDir(String outputDir) {
        this.outputDir = outputDir;
    }

    public void setTemplateDir(String templateDir) throws IOException {
        this.templateDir = templateDir;
        cfg.setDirectoryForTemplateLoading(new File(templateDir));
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }
    
    public void init() throws IOException {
        // Initialize the FreeMarker configuration;
        // - Create a configuration instance
        cfg = new Configuration();
        File templateDir = new File(this.templateDir);
        cfg.setDirectoryForTemplateLoading(templateDir);
        cfg.setLocale(Locale.CHINA);
        cfg.setDefaultEncoding("UTF-8");
    }
    
    /**
     * 
     */
    public void generate(String className) throws IOException{
    	JavaModel model=new JavaModel();
    	model.setClassName(className);
        // Build the data-model
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("model", model);
        
        // Get the templat object
        Template template = cfg.getTemplate(templateName);
        
        org.apache.commons.io.FileUtils.forceMkdir(new File(outputDir));
        File output = new File(outputDir, className + "Dao.java");
        Writer writer = new FileWriter(output);
        
        // Merge the data-model and the template
        try {
            template.process(data, writer);
        } catch (TemplateException e) {
            e.printStackTrace();
        }
    }
  
    /**
     *  
     */
    public static void main(String[] args) throws IOException {
        GenMain gen = new GenMain();
        gen.generate("User");
        System.out.println(":"+gen.outputDir);
    	DaoGenerator daoGenerator=new DaoGeneratorImpl();
//    	daoGenerator.genAll("UserFuck");
//    	daoGenerator.genDaoInterface("UserDao.java");
//    	daoGenerator.genDaoImpl("UserDaoImpl.java");
    	System.out.println("���������");
    }

}
