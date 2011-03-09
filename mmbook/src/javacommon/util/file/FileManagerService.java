package javacommon.util.file;


import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javacommon.util.RandomNum;
import javax.servlet.jsp.PageContext;

import org.apache.commons.lang.StringUtils;

/**
 * 文件处理
 * javacommon.util.file.FileManagerService 
 * @version 1.0
 */

public class FileManagerService {

	public FileManagerService() {

	}
	
	public static void main(String[] args){
		FileManagerService server = new FileManagerService();
		try{
		byte[] xx=server.readFileToByte("C:/Program Files/Apache Software Foundation/tomcat-5.5.25/webapps/ROOT/upfile/type/productId/20100821131611200.txt");
		System.out.println(new String(xx));
		
		System.out.println("******************");
		String content = server.readerFile("C:/Program Files/Apache Software Foundation/tomcat-5.5.25/webapps/ROOT/upfile/type/productId/20100821131611200.txt");
		System.out.println(content);
		}catch(Exception exe){
			
		}
	}
	
	protected String  getFileName(String filePath) {
		String fileName=StringUtils.substringAfterLast(filePath, "/");
		 if(fileName.equals("")||fileName==null){
			 fileName=StringUtils.substringAfterLast(filePath, "\\");
		 }
		 return fileName;
	}
	/**
	 * 按日期生成文件名称
	 * @return
	 */
	public static String createFileName(){
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat formatter = new SimpleDateFormat("yyMMddHHmm");
		String mDateTime = formatter.format(cal.getTime());
		String newfilename = mDateTime + RandomNum.getRandom();
		return newfilename;
	}

	/**
	 * 创建文件
	 * 
	 * @param fileName
	 *            String 文件名，包含文件路径
	 * @param buf
	 *            byte[] 文件内容
	 * @throws java.lang.Exception
	 *             创建文件失败，抛出异常
	 */
	public static void createFile(String fileName, byte[] buf) throws Exception {
		String sDir = fileName.substring(0, fileName.lastIndexOf("/"));
		// System.out.println("dir == " + sDir);
		// 创建目录
		File dir = new File(sDir);
		if (!dir.exists()) {
			dir.mkdirs();
		}

		// 创建文件
		File file = new File(fileName);
		// if(!file.exists()) {
		// System.out.println("文件长度 == " + buf.length);
		if (buf != null && buf.length > 0) {
			FileOutputStream fOut = new FileOutputStream(fileName);
			fOut.write(buf, 0, buf.length);
			fOut.close();
		}
		// }
	}

	/**
	 * 删除文件
	 * 
	 * @param fileName
	 *            String 文件名，包含文件路径
	 * @return int 成功 >0；失败 -1
	 */
	public static int deleteFile(String fileName) {
		int results = 0;
		File file = new File(fileName);
		if (file.exists()) {
			if (file.delete()) {
				results = results + 1;
			}
		}
		return results;
	}

	/**
	 * 删除文件夹
	 * 
	 * @param dirName
	 *            String 文件夹名称，包含文件夹路径
	 * @return int 成功 >0；失败 -1
	 */
	public static int deleteDir(String dirName) {
		int results = 0;
		File dir = new File(dirName);
		File[] files = dir.listFiles();
		for (int i = 0; i < files.length; i++) { // 删除目录下的所有文件
			if (files[i].exists()) {
				files[i].delete();
			}
		}

		if (dir.exists()) { // 删除目录
			if (dir.delete()) {
				results = results + 1;
			}
		}
		return results;
	}

	/**
	 * 批量删除物理文件
	 * 
	 * @param arrfile
	 *            String[] //被删除文件数组
	 * @throws Exception
	 * @return int 成功返回被删除文件个数，失败返回0
	 */
	public static int deletePhysicsFile(String[] arrfile) throws Exception {
		int results = 0;
		for (int i = 0; i < arrfile.length; i++) {
			File fl = new File(arrfile[i]);
			if (fl.exists()) {
				if (fl.delete()) {
					results = results + 1;
				}
			}
		}
		return results;
	}

	/**
	 * 写文件方法。
	 * 
	 * @param filename
	 *            String 文件名
	 * @param arrcontent
	 *            String[] 内容数组
	 * @param isappend
	 *            boolean 是否追尾
	 * @throws Exception
	 */
	public static void WriteFile(String filename, String strcontent, boolean isappend)
			throws Exception {
		File f1 = new File(filename);
		if (!f1.exists()) {
			f1.createNewFile();
		}
		FileWriter fr = new FileWriter(filename, isappend);
		BufferedWriter w1 = new BufferedWriter(fr);
		w1.write(strcontent);
		w1.newLine();
		w1.close();
		fr.close();
	}

	/**
	 * 根据相对路径获得绝对路径
	 * 
	 * @param pagecontext
	 *            PageContext
	 * @param dirPath
	 *            String 相对路径
	 * @return String 绝对路径
	 */
	public static String getPath(PageContext pagecontext, String dirPath) {
		String tempPath = pagecontext.getServletContext().getRealPath("");
		Properties properties = System.getProperties();
		String separator = properties.getProperty("file.separator");
		String path = "";
		if (separator.equals("\\")) {
			path = tempPath.substring(0, tempPath.lastIndexOf("\\")) + dirPath;
			path = path.replaceAll("\\\\", "/");
		} else {
			path = tempPath.substring(0, tempPath.lastIndexOf("/")) + dirPath;
		}
		return path;
	}

	/**
	 * 创建目录
	 * 
	 * @param dirPath
	 *            String 目录
	 */
	public static void mkdir(String dirPath) {
		java.io.File dir = new java.io.File(dirPath);
		if (!dir.exists()) {
			dir.mkdirs();
		}
	}

	/**
	 * 读取文件 
	 * 
	 * @param filePath
	 *            文件相对路径
	 * @return 文件内容
	 */
	public static String getContent(String filePath) {
		String textContent = "";
		try {
			InputStream in = FileManagerService.class.getClassLoader()
					.getResourceAsStream(filePath);
			InputStreamReader inReader = new InputStreamReader(in, "UTF-8");
			BufferedReader input = new BufferedReader(inReader);
			StringBuffer buffer = new StringBuffer();
			String text;
			while ((text = input.readLine()) != null) {
				buffer.append(text);
			}
			input.close();
			textContent = buffer.toString();
		} catch (Exception ex) {
		}
		return textContent;
	}
	
    public static String readerFile(String filePath ) {
    	String content="";
        try {
            FileReader fr = new FileReader(filePath);//创建FileReader对象，用来读取字符流
            BufferedReader br = new BufferedReader(fr);    //缓冲指定文件的输入
             
            String myreadline;    //定义一个String类型的变量,用来每次读取一行
            while (br.ready()) {
                myreadline = br.readLine();//读取一行
                myreadline = myreadline+"\n";
                content = content+myreadline+"<br/>" ; //写入文件
            }
            
            br.close(); 
            br.close();
            fr.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }

	
	/**
	 * 读取源文件内容
	 * 
	 * @param filename
	 *            String 文件路径
	 * @throws IOException
	 * @return byte[] 文件内容
	 */
	public static byte[] readFileToByte(String filename) throws IOException {

		File file = new File(filename);
		if (filename == null || filename.equals("")) {
			throw new NullPointerException("无效的文件路径");
		}
		long len = file.length();
		byte[] bytes = new byte[(int) len];
		BufferedInputStream bufferedInputStream = new BufferedInputStream(
				new FileInputStream(file));
		int r = bufferedInputStream.read(bytes);
		if (r != len)
			throw new IOException("读取文件不正确");
		bufferedInputStream.close();
		return bytes;

	}

	/**
	 * 过滤空格、换行符、制表符的字符串 
	 * 
	 * @param content
	 *            内容字符串
	 * @return 过滤掉空格、换行符、制表符的字符串
	 */
	public static String trimSpace(String content) {
		String after = null;
		if (content != null) {
			Pattern p = Pattern.compile("\\s*|\t|\r|\n");
			Matcher m = p.matcher(content);
			after = m.replaceAll("");
		}
		return after;
	}
	
	
	/**
	 * 
	 * 根据路径判断如果存在 删除所有文件
	 * 如果不存在创建一系列的目录 
	 * 
	 * @param path
	 */
	public static boolean mkDirectory(String path) {
		File file = null;
		try {
			file = new File(path);
			if (!file.exists()) {
				return file.mkdirs();
			}else{
				//deleteDir(path);
			}
		} catch (RuntimeException e) {
			e.printStackTrace();
		} finally {
			file = null;
		}
		return false;
	}
	
	/**
	 * 返回上传的结果，成功与否 
	 * 
	 * @param uploadFileName
	 * @param savePath
	 * @param uploadFile
	 * @return
	 */
	public static boolean upload(String uploadFileName, String savePath, File uploadFile) {
		boolean flag = false;
		try {
			uploadForName(uploadFileName, savePath, uploadFile);
			flag =true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	/**
	 * 上传文件并返回上传后的文件名 
	 * 
	 * @param uploadFileName            被上传的文件名称
	 * @param savePath            文件的保存路径
	 * @param uploadFile            被上传的文件
	 * @return 成功与否
	 * @throws IOException
	 */
	public static String uploadForName(String uploadFileName, String savePath, File uploadFile) throws IOException {
		//String newFileName = checkFileName(uploadFileName, savePath);
		FileOutputStream fos = null;
		FileInputStream fis = null;
		try {
			fos = new FileOutputStream(savePath + uploadFileName);
			fis = new FileInputStream(uploadFile);
			byte[] buffer = new byte[1024];
			int len = 0;
			while ((len = fis.read(buffer)) > 0) {
				fos.write(buffer, 0, len);
			}
		} catch (FileNotFoundException e) {
			throw e;
		} catch (IOException e) {
			throw e;
		} finally {
			try {
				if (fos != null) {
					fos.close();
				}
				if (fis != null) {
					fis.close();
				}
			} catch (IOException e) {
				throw e;
			}
		}
		return uploadFileName;
	}
	
	/**
	 * 判断文件名是否已经存在，如果存在则在后面家(n)的形式返回新的文件名，否则返回原始文件名 例如：已经存在文件名 log4j.htm
	 * 则返回log4j(1).htm 
	 * 
	 * @param fileName 文件名
	 * @param dir 判断的文件路径
	 * @return 判断后的文件名
	 */
	public static String checkFileName(String fileName, String dir) {
		boolean isDirectory = new File(dir + fileName).isDirectory();
		if (isFileExist(fileName, dir)) {
			int index = fileName.lastIndexOf(".");
			StringBuffer newFileName = new StringBuffer();
			String name = isDirectory ? fileName : fileName.substring(0, index);
			String extendName = isDirectory ? "" : fileName.substring(index);
			int nameNum = 1;
			while (true) {
				newFileName.append(name).append("(").append(nameNum).append(")");
				if (!isDirectory) {
					newFileName.append(extendName);
				}
				if (isFileExist(newFileName.toString(), dir)) {
					nameNum++;
					newFileName = new StringBuffer();
					continue;
				}
				return newFileName.toString();
			}
		}
		return fileName;
	}
	
	/**
	 * 判断文件是否存在 
	 * @param fileName
	 * @param dir
	 * @return
	 */
	public static boolean isFileExist(String fileName, String dir) {
		File files = new File(dir + fileName);
		return (files.exists()) ? true : false;
	}
	/** 
	 * 创建多级目录 
	 * 
	 * @param aParentDir String 
	 * @param aSubDir  以 / 开头 
	 * @return boolean 是否成功 
	 */
	public static boolean creatDirs(String aParentDir, String aSubDir) {
		File aFile = new File(aParentDir);
		if (aFile.exists()) {
			File aSubFile = new File(aParentDir + aSubDir);
			if (!aSubFile.exists()) {
				return aSubFile.mkdirs();
			} else {
				return true;
			}
		} else {
			return false;
		}
	}
}
