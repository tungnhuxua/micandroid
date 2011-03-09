package cn.mmbook.platform.action.site;
 

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javacommon.base.BaseStruts2Action;
import javacommon.util.RandomNum;
import javacommon.util.file.FileManagerService;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.struts2.ServletActionContext;


/**
 * 文件上传
 * @author qiongguo
 *
 */


public class UploadFileAction extends BaseStruts2Action {

	private File upload; // 上传的文件
	private String uploadFileName; // 文件名
	private String uploadContentType; // 文件类型
	private String fileLength;
	// private String savePath; //接受依赖注入的属性

	private String type; // 上传文件的业务类型 比如 合同 ht 计划书 jhs
	private String filetypename; // 上传文件的类型传格式 比如 |rar|zip|doc| 注意传过来的要小写及格式
	private String cpCode; // CP代码
	private String productId; // 产品ID 盛复康add 2010.6.29
	private String busiId; // CP代码
	private String imgWidth;
	private String imgHeight;

 
	private String flag;

	public String getType() {
		return type;
	}

	public String getFiletypename() {
		return filetypename;
	}

	public void setFiletypename(String filetypename) {
		this.filetypename = filetypename;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCpCode() {
		return cpCode;
	}

	public void setCpCode(String cpCode) {
		this.cpCode = cpCode;
	}

	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public String getUploadContentType() {
		return uploadContentType;
	}

	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	public void outJsonString(String str) {
		getResponse().setContentType("text/javascript;charset=UTF-8");
		outString(str);
	}

	public void outString(String str) {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = null;
		try {
			out = getResponse().getWriter();
			out.write(str);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}

	/**
	 * 上传文件 
	 * @author Administrator
	 * @param type;
	 *            //上传文件的业务类型 比如 合同 ht 计划书 jhs
	 * @param filetypename;
	 *            //上传文件的类型传格式 比如 |rar|zip|doc| 注意传过来的要小写及格式
	 * @param cpCode;
	 *            //CP代码
	 * @return
	 */
	public void uploadFiles() {
		String msg = "";
		try {
			// 首先判断其上传的文件必须的业务参数
			String suf = "";
			if (null == type || "".equals(type)) {
				type="jpg";
			}
			if (null == cpCode || "".equals(cpCode)) {
				cpCode="900876";
			}
			if (null == filetypename || "".equals(filetypename)) {
				filetypename="asda.jpg";
			}

			// 判断其上传的文件类型 大小
			if (null != upload && upload.length() > 0) {
				int index = uploadFileName.lastIndexOf(".");
				suf = uploadFileName.substring(index + 1);
				suf = suf.toLowerCase(); // 转成小写
				if (filetypename.indexOf(suf) == -1) {
					msg = "{success:false,msg:'文件类型错误! 文件类型必须是 " + filetypename
							+ " 格式'}";
					throw new Exception("上传的文件类型错误");
				}
			} else {
				throw new Exception("上传的文件为空!");
			}
			if (null == fileLength || "".equals(fileLength)) {
				if (upload.length() > 3145728) {// 如果文件大于3M
					msg = "{success:false,msg:'上传的文件 大于3M!'}";
					throw new Exception("上传文件的 大于3M!");
				}
			} else if (upload.length() > (Long.valueOf(fileLength)) * 1024) {// 如果文件大于3M
				System.out.println(upload.length());
				msg = "{success:false,msg:'上传的文件 大于"
						+ Double.valueOf(fileLength) / 1024 + "M!'}";
				throw new Exception("上传文件的 大于" + Double.valueOf(fileLength)
						/ 1024 + "M!");
			}

			// 设定将要上传的路径
			String rootPath = getRequest().getSession().getServletContext()
					.getRealPath("");

			int flast = rootPath.lastIndexOf(File.separator);
			if (flast != -1) {
				rootPath = rootPath.substring(0, flast);
			}
			rootPath = rootPath.replace("\\", "//");

			// 获取上传的日期
			Calendar cal = Calendar.getInstance();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
			String mDateTime = formatter.format(cal.getTime());

			// 上传规则 暂时写死 具体获取页面传过来的参数 并根据业务去拼装(lp/upfile/类型/CP代码/时间+4位随机数+扩展名)
			//String uploadpath = SParametersHelper.read().getUploadPath();
			String uploadpath = "/ROOT/lp/upfile/";
			rootPath += uploadpath + "//" + type + "//" + cpCode + "//";
			String newfilename = mDateTime + RandomNum.getRandom() + "." + suf;// 新的文件名

			FileManagerService.mkDirectory(rootPath);// 创建文件夹
			if (FileManagerService.upload(newfilename, rootPath, upload)) {// 上传
				String filepathname = "./download.down?homeid=ExampleDownloadDir&lx="
						+ type + "&cpdm=" + cpCode + "&fn=" + newfilename;
				int x = uploadpath.lastIndexOf(File.separator);
				if (x != -1) {
					uploadpath = uploadpath.substring(x, uploadpath.length());
				}
				String imgurl = uploadpath + "/" + type + "/" + cpCode + "/"
						+ newfilename;

				// 格式化文件大小，单位为K，保留一位小数
				DecimalFormat df1 = new DecimalFormat("#.#");
				//df1.setRoundingMode(RoundingMode.DOWN); // 设置数字格式化的取舍方式
				long kLen = upload.length() / 1024;
				if (kLen == 0) { // 不足1K，按1K计算
					kLen = 1;
				}
				String len = df1.format(kLen) + "K";
				
				msg = "{success:true,msg:'上传成功!',filepathname:'" + filepathname
						+ "',imgurl:'" + imgurl + "',fileLength:'" + len
						+ "',newFileName:'" + newfilename + "',fileExt:'" + suf
						+ "'}";
				System.out.println(msg);
			} else {
				msg = "{success:false,msg:'上传失败!'}";
			}
		} catch (Exception e) {
			if ("".equals(msg)) {
				msg = "{success:false,msg:'上传失败!'}";
			}
			e.printStackTrace();
		}
		this.outJsonString(msg);// 注：必须这样写否则上传后浏览器会把JSON的信息当作文件来下载
	}

 

	// 判断是否为数字
	public static boolean isNumeric(String str) {
		for (int i = str.length(); --i >= 0;) {
			int chr = str.charAt(i);
			if (chr < 48 || chr > 57)
				return false;
		}
		return true;
	}

	 

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getFileLength() {
		return fileLength;
	}

	public void setFileLength(String fileLength) {
		this.fileLength = fileLength;
	}
 

//	public void setSensitiveWordManager(
//			SensitiveWordManager sensitiveWordManager) {
//		this.sensitiveWordManager = sensitiveWordManager;
//	}

	/**
	 * 上传文件 
	 * 
	 * @param type;
	 *            //上传文件的业务类型  
	 * @param filetypename;
	 *            //上传文件的类型传格式  注意传过来的要小写及格式
	 * @param productId;
	 *            // 
	 * @return
	 */
	public void uploadFilesNew() {
		String msg = "";
		try {
			// 首先判断其上传的文件必须的业务参数
			String suf = "";
			if (null == type || "".equals(type)) {
				msg = "{success:false,msg:'上传的文件 业务类型为空!'}";
				throw new Exception("上传的文件 业务类型为空!");
			}
			if (null == productId || "".equals(productId)) {
				msg = "{success:false,msg:'产品ID为空!'}";
				throw new Exception("产品ID为空!");
			}
			if (null == filetypename || "".equals(filetypename)) {
				msg = "{success:false,msg:'上传的文件 类型传格式为空!'}";
				throw new Exception("上传的文件 类型传格式为空!");
			}

			// 判断其上传的文件类型 大小
			if (null != upload && upload.length() > 0) {
				int index = uploadFileName.lastIndexOf(".");
				suf = uploadFileName.substring(index + 1);
				suf = suf.toLowerCase(); // 转成小写
				if (filetypename.indexOf(suf) == -1) {
					msg = "{success:false,msg:'文件类型错误! 文件类型必须是 " + filetypename
							+ " 格式'}";
					throw new Exception("上传的文件类型错误");
				}
			} else {
				throw new Exception("上传的文件为空!");
			}
			if (upload.length() > 3145728) {// 如果文件大于3M
				msg = "{success:false,msg:'上传的文件 大于3M!'}";
				throw new Exception("上传文件的 大于3M!");
			}

			// 设定将要上传的路径
			String rootPath = getRequest().getSession().getServletContext()
					.getRealPath("");

			int flast = rootPath.lastIndexOf(File.separator);
			if (flast != -1) {
				rootPath = rootPath.substring(0, flast);
			}
			rootPath = rootPath.replace("\\", "//");

			// 获取上传的日期
			Calendar cal = Calendar.getInstance();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
			String mDateTime = formatter.format(cal.getTime());

			// 上传规则 暂时写死 具体获取页面传过来的参数 并根据业务去拼装(lp/upfile/类型/CP代码/时间+4位随机数+扩展名)
			//String uploadpath = SParametersHelper.getValue("uploadPath"); 
			String uploadpath = "//ROOT//upfile";
			rootPath += uploadpath + "//" + type + "//" + productId + "//";
			String newfilename = mDateTime + RandomNum.getRandom() + "." + suf;// 新的文件名

			FileManagerService.mkDirectory(rootPath);// 创建文件夹
			if (FileManagerService.upload(newfilename, rootPath, upload)) {// 上传
				String filepathname = "./download.down?homeid=ExampleDownloadDir&lx="
						+ type + "&cpdm=" + productId + "&fn=" + newfilename;
				int x = uploadpath.lastIndexOf(File.separator);
				if (x != -1) {
					uploadpath = uploadpath.substring(x, uploadpath.length());
				}
				String imgurl = "upfile/" + type + "/" + productId + "/"
						+ newfilename;
				// String imgurl
				// =SParametersHelper.read().getSystemUrl()+"/upfile/"+type+"/"+busiId+"/"+newfilename;
				msg = "{success:true,msg:'上传成功!',filepathname:'" + filepathname
						+ "',imgurl:'" + imgurl + "'}";
			} else {
				msg = "{success:false,msg:'上传失败!'}";
			}
		} catch (Exception e) {
			if ("".equals(msg)) {
				msg = "{success:false,msg:'上传失败!'}";
			}
			e.printStackTrace();
		}
		this.outJsonString(msg);// 注：必须这样写否则上传后浏览器会把JSON的信息当作文件来下载
	}
}
