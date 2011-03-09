package org.javaside.cms.web.front;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.javaside.cms.core.Page;
import org.javaside.cms.core.SpringSecurityUtils;
import org.javaside.cms.core.Struts2Utils;
import org.javaside.cms.entity.Member;
import org.javaside.cms.entity.MemberCircleUser;
import org.javaside.cms.service.MemberCircleManager;
import org.javaside.cms.service.MemberCircleUserManager;
import org.javaside.cms.service.MemberManager;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

@SuppressWarnings("serial")
@Results( { @Result(name = "home", location = "/home.action", type = "redirect"),
		@Result(name = "circleweb", location = "/circle-web.action", type = "redirect") })
public class WorldAction extends ActionSupport implements Preparable {

	@Autowired
	private MemberCircleManager memberCircleManager;
	@Autowired
	private MemberCircleUserManager memberCircleUserManager;
	@Autowired
	private MemberManager memberManager;

	private Page page = new Page(20);
	private List list;
	private List circleTypeList;
	private Long circleType;
	private Long circleId;
	private Integer getState = 0; //获取数据状态 0：所有圈子 1：按类型的圈子 2：推荐的圈子 3：最新发布 4：人气排行 
	
	private String userName = SpringSecurityUtils.getCurrentUserName();
	private Member member; //用户信息

	@Actions( { @Action("/circle-web") })
	public String circleWeb() {

		circleTypeList = memberCircleManager.getCircleTypeList();
		if (getState == 0) {
			list = memberCircleManager.getAllCircle(page);
		}
		if (getState == 1) {
			list = memberCircleManager.getCircleType(page, circleType);
		}
		if (getState == 2) {
			list = memberCircleManager.getCommendCircle(page);
		}
		if (getState == 3) {
			list = memberCircleManager.getTimeCircle(page);
		}
		if (getState == 4) {
			list = memberCircleManager.getTopCircle(page);
		}
		return this.SUCCESS;
	}

	//加入群英会
	@Action("/enterCircle")
	public String enterCircle() {
		String username = SpringSecurityUtils.getCurrentUserName();
		if (username == null || "roleAnonymous".equals(username)) {
			Struts2Utils.renderText("nologin");
			return null;
		}
		Member member = memberManager.getMemberByLoginName(SpringSecurityUtils.getCurrentUserName());
		if (memberCircleUserManager.isEnterCircle(member.getId(), circleId)) {
			Struts2Utils.renderText("isenter");
			return null;
		} else {

			MemberCircleUser memberCircleUser = new MemberCircleUser();
			memberCircleUser.setCid(circleId);
			memberCircleUser.setState(0l);
			memberCircleUser.setStatus(0l);
			memberCircleUser.setUid(member.getId());
			memberCircleUserManager.save(memberCircleUser);
		}
		return "circleweb";
	}

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public List getCircleTypeList() {
		return circleTypeList;
	}

	public void setCircleTypeList(List circleTypeList) {
		this.circleTypeList = circleTypeList;
	}

	public Integer getGetState() {
		return getState;
	}

	public void setGetState(Integer getState) {
		this.getState = getState;
	}

	public Long getCircleType() {
		return circleType;
	}

	public void setCircleType(Long circleType) {
		this.circleType = circleType;
	}

	public Long getCircleId() {
		return circleId;
	}

	public void setCircleId(Long circleId) {
		this.circleId = circleId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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
