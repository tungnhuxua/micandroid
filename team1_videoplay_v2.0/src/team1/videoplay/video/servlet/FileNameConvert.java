package team1.videoplay.video.servlet;



import java.text.SimpleDateFormat;
import java.util.Random;

public class FileNameConvert {
	public static String getFileName(String ip){
		 String timeStamp=new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new java.util.Date());
		 StringBuffer sb = new StringBuffer();
		 if(ip!=null){
			 String[] str=ip.split("\\.");
			 for(int i=0;i<str.length;i++){
				 sb.append(addZero(str[i],3));
			 }
		 }
		 sb.append(timeStamp);
		 Random random = new Random();
		 
		 for(int i=0;i<3;i++){
			 sb.append(random.nextInt(10));
		 }
		 
		 return sb.toString();
	}
	
	private static String addZero(String str ,int len){
		StringBuffer sb = new StringBuffer();
		sb.append(str);
		
		while(sb.length()<len){
			sb.insert(0,"0");			
		}
		
		return sb.toString();
	}
}
