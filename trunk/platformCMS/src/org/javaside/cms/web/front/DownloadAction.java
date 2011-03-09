package org.javaside.cms.web.front;

import java.io.InputStream;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Result;
import org.javaside.cms.core.Page;
import org.javaside.cms.core.SpringSecurityUtils;
import org.javaside.cms.core.Struts2Utils;
import org.javaside.cms.entity.Ads;
import org.javaside.cms.entity.DownloadResource;
import org.javaside.cms.entity.DownloadType;
import org.javaside.cms.entity.Member;
import org.javaside.cms.service.AdsLocationManager;
import org.javaside.cms.service.DownloadResourceManager;
import org.javaside.cms.service.DownloadTypeManager;
import org.javaside.cms.service.MemberManager;
import org.javaside.cms.util.FileRule;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

public class DownloadAction extends ActionSupport  implements Preparable {
	@Autowired
	private DownloadResourceManager resourceManager;
	@Autowired
	private DownloadTypeManager typeManager;
	@Autowired
	AdsLocationManager locationManager;
	@Autowired
	private MemberManager memberManager;

	private String action; //
	private DownloadResource resource; //下载资源
	private DownloadType type;
	private Page<DownloadResource> page = new Page<DownloadResource>(12);
	private Integer recommend = 0; //是否推荐 
	private String downFileName; // 下载文件名
	private InputStream inputStream; //文件下载流
	private String letter; //排序字母
	private List<DownloadResource> recommendpic; //精选图片
	private List<DownloadResource> HomogeneousResource; //同类资源
	private List<DownloadResource> correlation; //相关资源
	private String searchResourceName; //下载搜索

	private Ads[] adsArray; //广告位
	//用户名
	private String userName = SpringSecurityUtils.getCurrentUserName();
	private Member member; //用户信息

	/**
	 * 下载文件
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "/downfile", results = { @Result(name = "success", type = "stream", params = { "contentType",
			"application/octet-stream;charset=ISO8859-1", "inputName", "inputStream", "contentDisposition",
			"attachment;filename=${downFileName}", "bufferSize", "4096" }) })
	public String downfile() throws Exception {
		if (resource == null || resource.getId() == null)
			return null;

		resource = resourceManager.get(resource.getId());
		if (resource == null || resource.getUri() == null || "".equals(resource.getUri()))
			return null;

		downFileName = resource.getUri().substring(resource.getUri().lastIndexOf("/")).replace("/", "");
		inputStream = Struts2Utils.getSession().getServletContext().getResourceAsStream(
				resource.getUri().replace("/", FileRule.SPT));
		if (inputStream == null)
			return null;
		Integer count = resource.getDowncount() == null ? 0 : resource.getDowncount();
		resource.setDowncount(count + 1);
		resourceManager.save(resource);
		return SUCCESS;
	}

	@Actions( { @Action("/download") })
	public String download() throws Exception {
		action = "/download";
		if (recommend == 1) { //获取拦目
			page = resourceManager.getRecommendResource(page);
		}
		if (recommend == 0) {
			page = resourceManager.getResourceOfType(type, page);
		}
		type = typeManager.get(3L); //获取拦目
		if (recommend == 2) {//最新发布
			page = resourceManager.getTopHotResource(type, "createDate", page);
		}
		if (recommend == 3) {//人气，就是下载次数
			page = resourceManager.getTopHotResource(type, "downcount", page);
		}
		if (recommend == 4) {//首字母索引
			page = resourceManager.getResourceByLetter(letter, page);
		}
		//广告
		adsArray = locationManager.getAdsLocation(104L);

		return SUCCESS;
	}

	@Action("/downloadsearch")
	public String downloadsearch() throws Exception {
		type = typeManager.get(3L); //获取拦目
		page = resourceManager.getResourceSearch(page, searchResourceName);
		//广告
		adsArray = locationManager.getAdsLocation(104L);

		return SUCCESS;
	}

	@Action("/downloadcontent")
	public String content() throws Exception {
		action = "/downloadcontent";
		//广告
		adsArray = locationManager.getAdsLocation(105L);
		if (resource != null && resource.getId() != null) {
			resource = resourceManager.get(resource.getId());
		}
		getRecommendPic(); //精选图片
		getHomogeneousRes(); //同类资源
		getCorrelationPic(); //相关资源
		return SUCCESS;
	}

	/**
	 * 精选图片
	 */
	public void getRecommendPic() {
		DownloadType type = typeManager.get(3L); // 获取拦目
		recommendpic = resourceManager.getRecommendPic(type, "createDate", 10);
	}

	/**
	 * 同类资源
	 */
	public void getHomogeneousRes() {
		HomogeneousResource = resourceManager.getHomogeneousResource(type, "createDate", 18);
	}

	/**
	 * 相关资源
	 */
	public void getCorrelationPic() {
		correlation = resourceManager.getCorrelationPic(type, resource.getTag(), 8);
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public DownloadType getType() {
		return type;
	}

	public void setType(DownloadType type) {
		this.type = type;
	}

	public Page<DownloadResource> getPage() {
		return page;
	}

	public void setPage(Page<DownloadResource> page) {
		this.page = page;
	}

	public Integer getRecommend() {
		return recommend;
	}

	public void setRecommend(Integer recommend) {
		this.recommend = recommend;
	}

	public DownloadResource getResource() {
		return resource;
	}

	public void setResource(DownloadResource resource) {
		this.resource = resource;
	}

	public String getDownFileName() {
		return downFileName;
	}

	public void setDownFileName(String downFileName) {
		this.downFileName = downFileName;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public String getLetter() {
		return letter;
	}

	public void setLetter(String letter) {
		this.letter = letter;
	}

	public Ads[] getAdsArray() {
		return adsArray;
	}

	public void setAdsArray(Ads[] adsArray) {
		this.adsArray = adsArray;
	}

	public String getUserName() {
		return userName;
	}

	public List<DownloadResource> getRecommendpic() {
		return recommendpic;
	}

	public void setRecommendpic(List<DownloadResource> recommendpic) {
		this.recommendpic = recommendpic;
	}

	public void setHomogeneousResource(List<DownloadResource> homogeneousResource) {
		HomogeneousResource = homogeneousResource;
	}

	public List<DownloadResource> getHomogeneousResource() {
		return HomogeneousResource;
	}

	public List<DownloadResource> getCorrelation() {
		return correlation;
	}

	public void setCorrelation(List<DownloadResource> correlation) {
		this.correlation = correlation;
	}

	public String getSearchResourceName() {
		return searchResourceName;
	}

	public void setSearchResourceName(String searchResourceName) {
		this.searchResourceName = searchResourceName;
	}
	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}
	public void prepare() throws Exception {
		if(!userName.equals("roleAnonymous") && userName !=null){
			member = memberManager.getMemberByLoginName(SpringSecurityUtils.getCurrentUserName()); //获取已登录用户个人信息
		}
			
	}
}
