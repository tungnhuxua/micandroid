package ningbo.media.util;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class Resources {

	 /** 本地化资源文件。 */
    private static final String RESOURCE_LOCATION = "resource.mail";
    
    /** class 全名的资源ResourceBundle。 */
    private static ResourceBundle rb = ResourceBundle.getBundle(RESOURCE_LOCATION);
    
    public static final String GLOBAL_PATH = "global.path" ;
    
    /**
     * 从此资源包中获取给定键的字符串。如果未找到给定键的对象，则返回空字符串。
     * 
     * @param key 所需字符串的键。
     * @return 给定键的字符串。
     */
    public static String getText(String key) {
        if (key == null || "".equals(key.trim()))
            return "";
        String message = "";
        try {
            message = rb.getString(key);
        } catch (MissingResourceException e) {
            e.printStackTrace();
        }
        return message;
    }
    
    
    public static void main(String args[]){
    	System.out.println(getText("global.path")) ;
    }
}
