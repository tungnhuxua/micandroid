package cn.mmbook.freemark.constant;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import freemarker.cache.TemplateLoader;



/** 
 * 自定定义一个TemplateLoader
 * 用String来构造Template的方法
 * @author Keven Chen  2008-07-01
 * @version $Revision 1.0 $
 *  
 */


public class StringTemplateLoader implements TemplateLoader {

	
	
	private String template;

	public StringTemplateLoader(String template) {
		this.template = template;
		if (template == null) {
			this.template = "";
		}
	}

	public void closeTemplateSource(Object templateSource) throws IOException {
		((StringReader) templateSource).close();
	}

	public Object findTemplateSource(String name) throws IOException {
		return new StringReader(template);
	}

	public long getLastModified(Object templateSource) {
		return 0;
	}

	public Reader getReader(Object templateSource, String encoding)
			throws IOException {
		return (Reader) templateSource;
	}

}
