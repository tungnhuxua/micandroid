package org.javaside.cms.web.blog;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.javaside.cms.core.Page;
import org.javaside.cms.core.SpringSecurityUtils;
import org.javaside.cms.core.Struts2Utils;
import org.javaside.cms.entity.Member;
import org.javaside.cms.entity.MemberDynamics;
import org.javaside.cms.entity.MemberGroup;
import org.javaside.cms.entity.MemberGroupUser;
import org.javaside.cms.service.MemberDynamicsManager;
import org.javaside.cms.service.MemberEnjoyManager;
import org.javaside.cms.service.MemberGroupManager;
import org.javaside.cms.service.MemberGroupUserManager;
import org.javaside.cms.service.MemberManager;
import org.javaside.cms.service.ReceiveMessageManager;
import org.javaside.cms.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

@SuppressWarnings("serial")
@Results( {
		@Result(name = "home", location = "/home.action", type = "redirect"),
		@Result(name = "memberfriend", location = "/blog/member-friend.action", type = "redirect"),
		@Result(name = "memberfans", location = "/blog/member-fans.action", type = "redirect"),
		@Result(name = "memberfriendsearch", location = "/blog/member-friend-search.action", type = "redirect"),
		@Result(name = "memberfriendrequestlist", location = "/blog/member-friend-requestlist.action", type = "redirect") })
public class MemberFriendAction extends ActionSupport implements Preparable {

	@Autowired
	private MemberGroupUserManager memberGroupUserManager;
	@Autowired
	private MemberGroupManager memberGroupManager;
	@Autowired
	private MemberManager memberManager;
	@Autowired
	private MemberEnjoyManager memberEnjoyManager;
	@Autowired
	private MemberDynamicsManager memberDynamicsManager;
	@Autowired
	private ReceiveMessageManager receiveMessageManager;

	//基本属性
	private MemberGroupUser memberGroupUser;
	private MemberGroup memberGroup;
	private Page page = new Page(20);
	private List friendGroupList; //读取分类
	private List list;//读取好友
	private int state = 0; //0表示查询所有好友，1表示查询分类好友

	private Member member;
	private String action;
	private Long id;
	private Long tid; //朋友id
	private Long groupType; //分类类型
	private String groupName; //分类名
	private Integer blogIndex; //放置个人首页
	private String name; //搜索用户名
	private Long countRequestFriend; //获取申请好友总人数
	private Integer unRead; //邮箱未读信息数量
	//用户名
	private String userName = SpringSecurityUtils.getCurrentUserName();

	//好友管理
	@Actions( { @Action("/blog/member-friend") })
	public String FriendList() throws Exception {
		secure();
		if (member == null)
			return "home";

		if (state == 0) {
			list = memberGroupUserManager.getFriendList(page, member.getId());
		} else {
			list = memberGroupUserManager.getFriendGroupQuery(page, member.getId(), groupType);
		}
		friendGroupList = memberGroupUserManager.getFriendGroupList(member.getId());
		countRequestFriend = memberGroupUserManager.getCountRequestFriend(member.getId());
		return this.SUCCESS;
	}

	//粉丝团管理
	@Actions( { @Action("/blog/member-fans") })
	public String FansList() throws Exception {
		secure();
		if (member == null)
			return "home";

		list = memberGroupUserManager.getFansList(page, member.getId());
		friendGroupList = memberGroupUserManager.getFriendGroupList(member.getId());
		return this.SUCCESS;
	}

	/**
	 * 好友搜索
	 * 
	 * @return
	 * @throws Exception
	 */
	@Actions( { @Action("/blog/member-friend-search") })
	public String searcheFriend() throws Exception {
		secure();
		if (member == null)
			return "home";

		list = memberGroupUserManager.searchFriend(page, member.getId(), name);
		friendGroupList = memberGroupUserManager.getFriendGroupList(member.getId());
		return this.SUCCESS;
	}

	/**
	 * 添加分类
	 * 
	 * @return
	 * @throws Exception
	 */
	public String addGroup() throws Exception {
		secure();
		if (member == null)return "home";
		
		if(groupName !="" && groupName != null){
			MemberGroup group = new MemberGroup();
			group.setUid(member.getId());
			group.setGroupName(groupName);
			group.setGroupType(memberGroupUserManager.getMaxGroupType(member.getId()) + 1);
			memberGroupManager.save(group);
		}
		return "memberfriend";
	}

	/**
	 * 欣赏朋友加为好友
	 * 
	 * @return
	 * @throws Exception
	 */

	public String addfriend() throws Exception {
		secure();
		if (member == null)
			return "home";

		if (member.getId() == id)
			return null;
		//如果是好友则不允许加入 
		if (memberGroupUserManager.isFriendRelation(member.getId(), id)) {
			addActionMessage("您已经是好友了，请不要在重新加为好友");
		} else {
			MemberGroupUser mgu = new MemberGroupUser();
			mgu.setTid(id);
			mgu.setUid(member.getId());
			mgu.setFriendState(1);
			mgu.setGroupType(groupType);
			mgu.setBlogIndex(0);
			memberGroupUserManager.save(mgu);

			delEnjoyTwo(member.getId(), id);
			delEnjoyTwo(id, member.getId());//若加为好友，则删除欣赏记录
		}
		return "memberfans";
	}

	/**
	 * 删除好友
	 * 
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		secure();
		if (member == null)
			return "home";
		if (memberGroupUserManager.isFriend(member.getId(), tid)) {
			memberGroupUserManager.delete(id);
		}
		return "memberfriend";
	}

	/**
	 * 删除好友请求
	 * 
	 * @return
	 * @throws Exception
	 */
	public String deleteRequestFriend() throws Exception {
		secure();
		if (member == null)
			return "home";
		if (memberGroupUserManager.isFriend(member.getId(), tid)) {
			memberGroupUserManager.delete(id);
		}
		return "memberfriendrequestlist";
	}

	/**
	 * 朋友 放置个个首页
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action("/blog/friendIndexOperate")
	public String blogIndexOperate() throws Exception {
		secure();
		if (member == null)
			return "home";

		if (blogIndex != null) {
			memberGroupUser = memberGroupUserManager.get(id);
			memberGroupUser.setBlogIndex(Integer.valueOf(blogIndex));
			Struts2Utils.renderText("true");
		} else {
			Struts2Utils.renderText("false");
		}
		memberGroupUserManager.save(memberGroupUser);

		return null;
	}

	//申请好友列表
	@Actions( { @Action("/blog/member-friend-requestlist") })
	public String listAddFriend() {
		secure();
		if (member == null)
			return "home";

		list = memberGroupUserManager.getRequestFriend(page, member.getId());
		friendGroupList = memberGroupUserManager.getFriendGroupList(member.getId());
		return this.SUCCESS;
	}

	//申请加为朋友
	public String requestAddFriend() {
		secure();
		if (member == null)
			return "home";

		if (member.getId() == id)
			return null;
		//如果该好友在自己的好友列表中，则不允许加入自己的好友列表，只需要状状态改为1. 
		if (memberGroupUserManager.isFriendRelation(member.getId(), tid)) {
			MemberGroupUser otherFriend = memberGroupUserManager.get(id);
			otherFriend.setFriendState(1);
			memberGroupUserManager.save(otherFriend); //申请人改为状态为1 ，成为好友。

			MemberDynamics memberDynamics = new MemberDynamics();
			Member tomember = memberManager.get(tid);
			memberDynamics.setUid(member.getId());
			memberDynamics.setMessageType(1l);
			Map map = new HashMap();
			map.put("memberName", member.getName());
			map.put("memberId", member.getId());
			map.put("tomemberName", tomember.getName());
			map.put("tomemberId", tomember.getId());
			map.put("date", DateUtil.getTimeFormat());
			JSONObject json = JSONObject.fromObject(map);

			memberDynamics.setMessage(json.toString());
			memberDynamicsManager.save(memberDynamics); //保存动态消息
			memberDynamicsManager.delMemberDynamics50(member.getId()); //用户动态信息只保存50条，50条以后则删除

			delEnjoyTwo(member.getId(), tid);
			delEnjoyTwo(tid, member.getId());//若加为好友，则删除欣赏记录
			addActionMessage("您已经是好友了，请不要在重新加为好友");
		} else {
			MemberGroupUser otherFriend = memberGroupUserManager.get(id);
			otherFriend.setFriendState(1);
			memberGroupUserManager.save(otherFriend); //申请人改为状态为1 ，成为好友。

			MemberGroupUser mgu = new MemberGroupUser();
			mgu.setFriendState(1);
			mgu.setTid(tid);
			mgu.setUid(member.getId());
			mgu.setGroupType(groupType);
			mgu.setBlogIndex(0);
			memberGroupUserManager.save(mgu);//自己加对为为好友

			MemberDynamics memberDynamics = new MemberDynamics();
			Member tomember = memberManager.get(tid);
			memberDynamics.setUid(member.getId());
			memberDynamics.setMessageType(1l);
			Map map = new HashMap();
			map.put("memberName", member.getName());
			map.put("memberId", member.getId());
			map.put("tomemberName", tomember.getName());
			map.put("tomemberId", tomember.getId());
			map.put("date", DateUtil.getTimeFormat());
			JSONObject json = JSONObject.fromObject(map);
			memberDynamics.setMessage(json.toString());
			memberDynamicsManager.save(memberDynamics); //保存动态消息
			memberDynamicsManager.delMemberDynamics50(member.getId()); //用户动态信息只保存50条，50条以后则删除

			delEnjoyTwo(member.getId(), tid);
			delEnjoyTwo(tid, member.getId());//若加为好友，则删除欣赏记录

		}
		return "memberfriendrequestlist";
	}

	//若加为好友，则删除欣赏记录
	public void delEnjoyTwo(Long uid, Long tid) {
		List list = memberEnjoyManager.getByMemberEnjoy(uid, tid);
		if (list.size() > 0) {
			Object[] object = (Object[]) list.get(0);
			String id = object[0].toString();
			memberEnjoyManager.delete(Long.valueOf(id));
		}

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

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getTid() {
		return tid;
	}

	public void setTid(Long tid) {
		this.tid = tid;
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

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public MemberGroupUser getMemberGroupUser() {
		return memberGroupUser;
	}

	public void setMemberGroupUser(MemberGroupUser memberGroupUser) {
		this.memberGroupUser = memberGroupUser;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public MemberGroup getMemberGroup() {
		return memberGroup;
	}

	public void setMemberGroup(MemberGroup memberGroup) {
		this.memberGroup = memberGroup;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getCountRequestFriend() {
		return countRequestFriend;
	}

	public void setCountRequestFriend(Long countRequestFriend) {
		this.countRequestFriend = countRequestFriend;
	}

	public Integer getBlogIndex() {
		return blogIndex;
	}

	public void setBlogIndex(Integer blogIndex) {
		this.blogIndex = blogIndex;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void prepare() throws Exception {
		member = memberManager.getMemberByLoginName(SpringSecurityUtils.getCurrentUserName());
		unRead = receiveMessageManager.getReceiveUnReadCount(member);
	}

	public Integer getUnRead() {
		return unRead;
	}

	public void setUnRead(Integer unRead) {
		this.unRead = unRead;
	}

}
