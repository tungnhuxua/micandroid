package javacommon.util.io;

/**
 * 读配置文件类
 * javacommon.util.io.ReadProperty.getInstance().getValue(key);
 * @author bbz
 *
 */
import java.io.InputStream;
import java.util.Properties;
import java.util.ResourceBundle;

public class ReadProperty {

	
    private static ResourceBundle resourceBundle = null;

    private ReadProperty(){}    
    //在自己内部定义自己一个实例   
     //注意这是private 只供内部调用    
     private static ReadProperty instance = new ReadProperty();    
    //这里提供了一个供外部访问本class的静态方法，可以直接访问    
     public static ReadProperty getInstance() {    
        return instance;    
   } 
    
    /**
     * 获得关键字的值
     * @param key String 关键字
     * @return String 值
     */
    public static String getValue(String key) {
        if(resourceBundle==null) {
            instantiation();
        }
        return resourceBundle.getString(key);
    }

    /**
     * 实例化配置文件
     */
    public static void instantiation() {
        resourceBundle = ResourceBundle.getBundle("javacommon.util.properties.systemParameters");
    }
    public static void main(String[] args){
    	ReadProperty obj = new ReadProperty();
    	System.out.println( obj.getValue("canSendSectionManager"));
    }

}
