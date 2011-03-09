package org.javaside.cms.web.blog;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.javaside.cms.core.SpringSecurityUtils;
import org.javaside.cms.core.Struts2Utils;
import org.javaside.cms.entity.Member;
import org.javaside.cms.entity.MemberEnjoy;
import org.javaside.cms.entity.MemberGroupUser;
import org.javaside.cms.entity.MemberInfo;
import org.javaside.cms.service.MemberEnjoyManager;
import org.javaside.cms.service.MemberGroupUserManager;
import org.javaside.cms.service.MemberManager;
import org.javaside.cms.service.ReceiveMessageManager;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

//个人档案
@SuppressWarnings("serial")
@Results( { @Result(name = "home", location = "/home.action", type = "redirect"),
		@Result(name = "archives", location = "/blog/archives.action?uid=${uid}", type = "redirect") })
public class ArchivesAction extends ActionSupport implements Preparable {

	@Autowired
	private MemberManager memberManager;
	@Autowired
	private MemberEnjoyManager memberEnjoyManager;
	@Autowired
	private MemberGroupUserManager memberGroupUserManager;
	@Autowired
	private ReceiveMessageManager receiveMessageManager;
	

	private List friendGroupList; //读取好友分类
	private MemberInfo entity;
	private Member tomember;//读取其它会员信息
	private Member member;//读取自己的信息
	private Long uid;
	private Long groupType;
	private Integer unRead; //邮箱未读信息数量
	private String action;
	private String messageError = "0"; //前端信息
	//用户名
	private String userName = SpringSecurityUtils.getCurrentUserName();

	@Action("/blog/archives")
	public String archives() {
		tomember = memberManager.getMemberId(tomember.getId());
		member = memberManager.getMemberByLoginName(SpringSecurityUtils.getCurrentUserName());
		if (member != null) {
			friendGroupList = memberGroupUserManager.getFriendGroupList(member.getId());
			
			if (memberGroupUserManager.isFriendRelation(member.getId(), tomember.getId()) //判断是否是好友
					|| member.getId() == tomember.getId()){
				messageError = "1"; //前端字符串判断，如果等于1，则为朋友，才有权限查看个人信息。
			}
		}
		return this.SUCCESS;
	}

	/**
	 * 欣赏
	 * 
	 * @return
	 */
	@Action("/blog/enjoy")
	public String enjoy() {
		String username = SpringSecurityUtils.getCurrentUserName();
		if (username == null || "roleAnonymous".equals(username)) {
			Struts2Utils.renderText("nologin");
			return null;
		}
		if (member.getId() == tomember.getId()) {//不能欣赏自己
			Struts2Utils.renderText("youself");
			return null;
		}
		if (memberGroupUserManager.isFriendRelation(member.getId(), tomember.getId())) {
			Struts2Utils.renderText("twofriend");
			addActionMessage("对方已经是您的好友");
			return null;
		}
		if (memberGroupUserManager.isFriendRelation(tomember.getId(), member.getId())) {
			Struts2Utils.renderText("friend");
			addActionMessage("对方已经添加您为好友");
			return null;
		}

		if (memberEnjoyManager.isEnjoy(tomember.getId(), member.getId())) {
			Struts2Utils.renderText("isenter");
			addActionMessage("您已经欣赏过这位好友了");
			return null;
		}
		if (memberEnjoyManager.isTwoEnjoy(tomember.getId(), member.getId())) {
			Struts2Utils.renderText("twoenjoy"); //双方都欣赏，则成为好友
			List member_tomember = memberGroupUserManager.getByMemberGroupUser(member.getId(), tomember.getId());
			List tomember_member = memberGroupUserManager.getByMemberGroupUser(tomember.getId(), member.getId());
			//把双方都加为自己的好友，并默认分组在1.
			if (tomember_member.size() > 0) {//如果对方正在申请我加为好友，则把对方的状态改为1.
				Object[] object = (Object[]) tomember_member.get(0);
				String id = object[0].toString();
				MemberGroupUser memberGroupUser = memberGroupUserManager.get(Long.valueOf(id));
				memberGroupUser.setFriendState(1);
				memberGroupUserManager.save(memberGroupUser);
			} else {
				MemberGroupUser memberGroupUser = new MemberGroupUser();
				memberGroupUser.setFriendState(1);
				memberGroupUser.setGroupType(1l);
				memberGroupUser.setTid(member.getId());
				memberGroupUser.setUid(tomember.getId());
				memberGroupUserManager.save(memberGroupUser);
			}
			if (member_tomember.size() > 0) {//如果我自己正在申请对方加为好友，则把我自己申请对方的状态改为1
				Object[] object = (Object[]) member_tomember.get(0);
				String id = object[0].toString();
				MemberGroupUser memberGroupUser = memberGroupUserManager.get(Long.valueOf(id));
				memberGroupUser.setFriendState(1);
				memberGroupUserManager.save(memberGroupUser);
			} else {
				MemberGroupUser memberGroupUserTo = new MemberGroupUser();
				memberGroupUserTo.setFriendState(1);
				memberGroupUserTo.setGroupType(1l);
				memberGroupUserTo.setTid(tomember.getId());
				memberGroupUserTo.setUid(member.getId());
				memberGroupUserTo.setBlogIndex(0);
				memberGroupUserManager.save(memberGroupUserTo);
			}
			addActionMessage("您们俩互相欣赏已经成为好友");

			//得到欣赏记录删除,已经成为好友，不需要欣赏。
			List list = memberEnjoyManager.getByMemberEnjoy(member.getId(), tomember.getId());
			Object[] object = (Object[]) list.get(0);
			String id = object[0].toString();
			memberEnjoyManager.delete(Long.valueOf(id));

			return null;
		} else {//如果对方没有欣赏你，则我欣赏他。
			MemberEnjoy memberEnjoy = new MemberEnjoy();
			memberEnjoy.setEnjoyState(1);
			memberEnjoy.setTid(member.getId());
			memberEnjoy.setUid(tomember.getId());
			memberEnjoyManager.save(memberEnjoy);
		}
		return null;
	}

	//申请加为好友
	public String requestAddFriend() {
		String username = SpringSecurityUtils.getCurrentUserName();
		if (username == null || "roleAnonymous".equals(username)) {
			Struts2Utils.renderText("nologin");
			return null;
		}
		if (member.getId() == tomember.getId()) {//不能加自己为好友
			Struts2Utils.renderText("youself");
			return null;
		}
		//如果是好友则不允许加入 
		if (memberGroupUserManager.isFriend(member.getId(), tomember.getId())) {
			Struts2Utils.renderText("isfriend");
			return null;
		} else {
			MemberGroupUser mgu = new MemberGroupUser();
			mgu.setTid(tomember.getId());
			mgu.setUid(member.getId());
			mgu.setFriendState(0);
			mgu.setGroupType(1l);
			memberGroupUserManager.save(mgu);
		}
		return null;
	}

	public void prepare() throws Exception {
		member = memberManager.getMemberByLoginName(SpringSecurityUtils.getCurrentUserName());
		unRead = receiveMessageManager.getReceiveUnReadCount(member);
		getUriActions();//获取action地址
	}
	
	/**
	 * 得到ation地址
	 */
	public void getUriActions() {
		action = Struts2Utils.getRequest().getServletPath();
		// 去掉 .action 后缀
		if (action.indexOf(".") > 0) {
			action = action.substring(0, action.indexOf(".")).replace(".", "");
		}

	}

	public MemberInfo getEntity() {
		return entity;
	}

	public void setEntity(MemberInfo entity) {
		this.entity = entity;
	}

	public Member getTomember() {
		return tomember;
	}

	public void setTomember(Member tomember) {
		this.tomember = tomember;
	}

	public Long getUid() {
		return uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public List getFriendGroupList() {
		return friendGroupList;
	}

	public void setFriendGroupList(List friendGroupList) {
		this.friendGroupList = friendGroupList;
	}

	public Long getGroupType() {
		return groupType;
	}

	public void setGroupType(Long groupType) {
		this.groupType = groupType;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getUnRead() {
		return unRead;
	}

	public void setUnRead(Integer unRead) {
		this.unRead = unRead;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getMessageError() {
		return messageError;
	}

	public void setMessageError(String messageError) {
		this.messageError = messageError;
	}

}
