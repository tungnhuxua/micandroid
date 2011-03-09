package cn.mmbook.platform.action.tag;

import static javacommon.util.extjs.Struts2JsonHelper.outJson;
import java.io.IOException;
import java.util.*;
import javacommon.base.BaseStruts2Action;
import javacommon.util.SpringContextUtil;

/**
 * 模板发布 
 * @author Felix  945166129
 *
 */

public class ReleaseAction extends BaseStruts2Action {

	private String versionId;

	public String getVersionId() {
		return versionId;
	}

	public void setVersionId(String versionId) {
		this.versionId = versionId;
	}

 

	public void redeploy() throws IOException {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			result.put("failure", true);
			result.put("msg", "发布失败!");

		} catch (Exception e) {
			result.put("failure", true);
			result.put("msg", "发布失败!");
			e.printStackTrace();
		}
		outJson(result);
	}

	//生成所有页面全局变量
	public void updatePage() throws IOException {
		Map<String, Object> result = new HashMap<String, Object>();
		try {

		} catch (Exception e) {
			result.put("failure", true);
			result.put("msg", "更新失败!");
			e.printStackTrace();
		}
		outJson(result);
	}

	//生成所有模板页面
	public void updateCmsTemplet() throws IOException {
		Map<String, Object> result = new HashMap<String, Object>();
		try {

			result.put("success", true);
			result.put("msg", "更新成功!");
		} catch (Exception e) {
			result.put("failure", true);
			result.put("msg", "更新失败!");
			e.printStackTrace();
		}
		outJson(result);
	}

	//生成所有栏目标签
	public void updateCategory() throws IOException {
		Map<String, Object> result = new HashMap<String, Object>();
		try {

			result.put("success", true);
			result.put("msg", "更新成功!");
		} catch (Exception e) {
			result.put("failure", true);
			result.put("msg", "更新失败!");
			e.printStackTrace();
		}
		outJson(result);
	}

	//生成所有内容标签
	public void updateCmsTagContent() throws IOException {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			result.put("success", true);
			result.put("msg", "更新成功!");
		} catch (Exception e) {
			result.put("failure", true);
			result.put("msg", "更新失败!");
			e.printStackTrace();
		}
		outJson(result);
	}

	//生成所有内容推荐标签
	public void updateCmsTagRmdation() throws IOException {
		Map<String, Object> result = new HashMap<String, Object>();
		try {

			result.put("success", true);
			result.put("msg", "更新成功!");
		} catch (Exception e) {
			result.put("failure", true);
			result.put("msg", "更新失败!");
			e.printStackTrace();
		}
		outJson(result);
	}

}