package org.javaside.cms.web.blog;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.javaside.cms.core.SpringSecurityUtils;
import org.javaside.cms.core.Struts2Utils;
import org.javaside.cms.entity.Member;
import org.javaside.cms.entity.MemberInfo;
import org.javaside.cms.service.MemberFootmarkManager;
import org.javaside.cms.service.MemberInfoManager;
import org.javaside.cms.service.MemberManager;
import org.javaside.cms.service.ReceiveMessageManager;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

@SuppressWarnings("serial")
@Results( { @Result(name = "home", location = "/home.action", type = "redirect"),
		@Result(name = "member-cover", location = "/blog/member-cover.action", type = "redirect") })
public class MemberCoverAction extends ActionSupport implements Preparable {

	@Autowired
	private MemberManager memberManager;
	@Autowired
	private ReceiveMessageManager receiveMessageManager;
	@Autowired
	private MemberInfoManager infoManager;
	
	private Long id;
	private Member member;
	private MemberInfo entity;
	private String action;
	//用户名
	private String userName = SpringSecurityUtils.getCurrentUserName();
	private Integer unRead; //邮箱未读信息数量
	
	
	//获取用户足迹列表
	@Action("/blog/member-cover")
	public String memberCover() throws Exception {
		if (member == null) return "home";
		
		entity = member.getInfo();
		return this.SUCCESS;
	}

	public String memberCoverSave()throws Exception{
		if (member == null) return "home";
		
//		entity = infoManager.get(entity.getId());
//		entity.setCoverImg(entity.getCoverImg());
//		entity.setCoverSetup(entity.getCoverSetup());
		infoManager.save(entity);
		return "member-cover";
	}
	
	
	public void prepare() throws Exception {
		if (id != null) {
			entity = infoManager.get(id);
		} else {
			entity = new MemberInfo();
		}
		member = memberManager.getMemberByLoginName(SpringSecurityUtils.getCurrentUserName());
		unRead = receiveMessageManager.getReceiveUnReadCount(member);
		secure();
	}
	
	/**
	 * 防止未登录用户
	 */
	public void secure() {
		action = Struts2Utils.getRequest().getServletPath();
		// 去掉 .action 后缀
		if (action.indexOf(".") > 0) {
			action = action.substring(0, action.indexOf(".")).replace(".", "");
		}

	}
	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public Integer getUnRead() {
		return unRead;
	}

	public void setUnRead(Integer unRead) {
		this.unRead = unRead;
	}

	public MemberInfo getEntity() {
		return entity;
	}

	public void setEntity(MemberInfo entity) {
		this.entity = entity;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	
	
}
