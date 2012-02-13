package ningbq.util;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import android.util.Log;

public class AndroidFileUtil {
	private static final String TAG = "AndroidFileUtil" ;

	private AndroidFileUtil() {
	}

	public static String getNameByDate(String pathName) {
		StringBuffer buffer = new StringBuffer();

		SimpleDateFormat simple = new SimpleDateFormat("yyMMddHHmmss");
		String diff = "_" + simple.format(new Date());

		int idx = pathName.lastIndexOf(".");
		if (idx != -1) {
			buffer.append(pathName.substring(0, idx));
			buffer.append(diff);
			buffer.append(pathName.substring(idx));
		} else {
			buffer.append(pathName);
			buffer.append(diff);
		}

		return buffer.toString();
	}

	public static String defaultSystemFilePath() {
		return File.separator + "soningbo" + File.separator + "fileUpload"
				+ File.separator + "header" + File.separator;
	}
	
	
	public static boolean createDir(String path){
		File dir = new File(path) ;
		if(dir.exists()){
			Log.i(TAG, "目录已经存在,不需要创建") ;
			return false ;
		}else{
			return dir.mkdirs() ;
		}
	}
}
