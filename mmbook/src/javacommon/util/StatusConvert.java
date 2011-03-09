package javacommon.util;

/**
 * 状态中文名与状态码之间的转换
 * 
 * @author pch 2010.6.11
 * 
 */
public class StatusConvert {

	/**
	 * 把数字状态码转为中文
	 * 
	 * @author pch 2010.6.11
	 * 
	 * @param statusNumber
	 *            数字状态码
	 * @return 数字状态码对应的中文
	 */
	public static String numberToString(String statusNumber) {
		String status = null;
		if (statusNumber != null) {
			switch (Integer.parseInt(statusNumber)) {
			case 00:
				status = "初始";
				break;
			case 01:
				status = "初始提交";
				break;
			case 10:
				status = "变更";
				break;
			case 11:
				status = "变更提交";
				break;
			case 20:
				status = "修改";
				break;
			case 21:
				status = "修改提交";
				break;
			case 22:
				status = "一级审核通过";
				break;
			case 25:
				status = "商用";
				break;
			case 32:
				status = "一审审核被拒";
				break;
			case 35:
				status = "二审审核被拒";
				break;
			case 50:
				status = "暂停";
				break;
			case 98:
				status = "终止";
				break;
			case 99:
				status = "历史";
				break;
			default:
				status = "";
				break;
			}
		}
		return status;
	}

	/**
	 * 把中文状态转为数字状态码
	 * 
	 * @author pch 2010.6.11
	 * 
	 * @param status
	 *            中文状态
	 * @return 数字状态码
	 */
	public static String stringToNumber(String status) {
		String statusNumber = null;
		if (status != null) {
			if (status.equals("初始")) {
				statusNumber = "00";
			} else if (status.equals("初始提交")) {
				statusNumber = "01";
			} else if (status.equals("变更")) {
				statusNumber = "10";
			} else if (status.equals("变更提交")) {
				statusNumber = "11";
			} else if (status.equals("一级审核通过")) {
				statusNumber = "22";
			} else if (status.equals("商用")) {
				statusNumber = "25";
			} else if (status.equals("一审审核被拒")) {
				statusNumber = "32";
			} else if (status.equals("二审审核被拒")) {
				statusNumber = "35";
			} else if (status.equals("暂停")) {
				statusNumber = "50";
			} else if (status.equals("终止")) {
				statusNumber = "98";
			} else if (status.equals("历史")) {
				statusNumber = "99";
			}
		}
		return statusNumber;
	}

}
