package team1.videoplay.utils;



import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropUtils {
	public static Properties loadProp(Class clazz,String name){
		Properties prop=new Properties();
		InputStream is=clazz.getResourceAsStream(name);
		try {
			prop.load(is);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return prop;
	}
}
