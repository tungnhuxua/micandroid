package org.javaside.cms.web.blog;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.javaside.cms.core.Page;
import org.javaside.cms.core.SpringSecurityUtils;
import org.javaside.cms.core.Struts2Utils;
import org.javaside.cms.entity.Member;
import org.javaside.cms.service.MemberDynamicsManager;
import org.javaside.cms.service.MemberGroupUserManager;
import org.javaside.cms.service.MemberLateGuestManager;
import org.javaside.cms.service.MemberManager;
import org.javaside.cms.service.MemberMessageManager;
import org.javaside.cms.service.ReceiveMessageManager;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
@Results( { @Result(name = "home", location = "/home.action", type = "redirect"),
		@Result(name = "member-home", location = "/blog/member-home.action", type = "redirect") })
public class MemberHomeAction extends ActionSupport {

	@Autowired
	private MemberManager memberManager;
	@Autowired
	private MemberLateGuestManager memberLateGuestManager;
	@Autowired
	private MemberMessageManager memberMessageManager;
	@Autowired
	private MemberGroupUserManager memberGroupUserManager;
	@Autowired
	private ReceiveMessageManager receiveMessageManager;
	@Autowired
	private MemberDynamicsManager memberDynamicsManager;

	private Member member;
	private String action;
	private List memberLateGuestList; //最近访客
	private List memberDynamicsList; //用户动态
	private Integer messageCount; //留言未查看统计
	private Long requestFriendCount; //申请好友总人数
	private Integer messageAnswerCount; //留言回复未查看统计
	private Page dynamicsFirendsPage = new Page(20);
	private Page dynamicsFirendsMorePage = new Page(50);
	private List onlineFirends; //在线好友

	private Integer unRead; //邮箱未读信息数量
	//用户名
	private String userName = SpringSecurityUtils.getCurrentUserName();

	@Action("/blog/member-home")
	public String memberHome() throws Exception {
		secure();
		if (member == null)
			return "home";

		memberLateGuestList = memberLateGuestManager.getMemberLateGuest(member.getId()); //最近访客
		messageCount = memberMessageManager.notExamineCount(member.getId());//留言未查看总条数
		messageAnswerCount = memberMessageManager.notAnswerCount(member);//留言回复未查看统计总条数
		requestFriendCount = memberGroupUserManager.getCountRequestFriend(member.getId());//申请好友总人数
		unRead = receiveMessageManager.getReceiveUnReadCount(member);
		memberDynamicsList = memberDynamicsManager.getMemberDynamicsFriendList(dynamicsFirendsPage, member.getId());//用户动态
		onlineFirends = memberGroupUserManager.getOnlineFriendList(new Page(15), member.getId());
		return this.SUCCESS;
	}

	@Action("/blog/member-dynamics-more")
	public String memberDynamicsMore() throws Exception {
		secure();
		if (member == null)
			return "home";

		memberDynamicsList = memberDynamicsManager.getMemberDynamicsFriendList(dynamicsFirendsMorePage, member.getId()); //用户动态
		return this.SUCCESS;
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
		member = memberManager.getMemberByLoginName(SpringSecurityUtils.getCurrentUserName());
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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public List getMemberLateGuestList() {
		return memberLateGuestList;
	}

	public void setMemberLateGuestList(List memberLateGuestList) {
		this.memberLateGuestList = memberLateGuestList;
	}

	public Integer getMessageCount() {
		return messageCount;
	}

	public void setMessageCount(Integer messageCount) {
		this.messageCount = messageCount;
	}

	public Long getRequestFriendCount() {
		return requestFriendCount;
	}

	public void setRequestFriendCount(Long requestFriendCount) {
		this.requestFriendCount = requestFriendCount;
	}

	public Integer getUnRead() {
		return unRead;
	}

	public void setUnRead(Integer unRead) {
		this.unRead = unRead;
	}

	public List getMemberDynamicsList() {
		return memberDynamicsList;
	}

	public void setMemberDynamicsList(List memberDynamicsList) {
		this.memberDynamicsList = memberDynamicsList;
	}

	public Page getDynamicsFirendsPage() {
		return dynamicsFirendsPage;
	}

	public void setDynamicsFirendsPage(Page dynamicsFirendsPage) {
		this.dynamicsFirendsPage = dynamicsFirendsPage;
	}

	public Integer getMessageAnswerCount() {
		return messageAnswerCount;
	}

	public void setMessageAnswerCount(Integer messageAnswerCount) {
		this.messageAnswerCount = messageAnswerCount;
	}

	public List getOnlineFirends() {
		return onlineFirends;
	}

	public void setOnlineFirends(List onlineFirends) {
		this.onlineFirends = onlineFirends;
	}

}
