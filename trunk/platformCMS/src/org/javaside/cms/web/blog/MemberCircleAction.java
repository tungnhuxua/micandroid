package org.javaside.cms.web.blog;

import java.util.Date;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.javaside.cms.core.Page;
import org.javaside.cms.core.SpringSecurityUtils;
import org.javaside.cms.core.Struts2Utils;
import org.javaside.cms.entity.Member;
import org.javaside.cms.entity.MemberCircle;
import org.javaside.cms.entity.MemberCircleUser;
import org.javaside.cms.service.MemberCircleManager;
import org.javaside.cms.service.MemberCircleUserManager;
import org.javaside.cms.service.MemberManager;
import org.javaside.cms.service.ReceiveMessageManager;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

@SuppressWarnings("serial")
@Results( {
		@Result(name = "home", location = "/home.action", type = "redirect"),
		@Result(name = "membercircle", location = "/blog/member-circle.action", type = "redirect"),
		@Result(name = "memberaddcircle", location = "/blog/member-addcircle.action", type = "redirect"),
		@Result(name = "membermodicircle", location = "/blog/member-modicircle.action", type = "redirect"),
		@Result(name = "membercirclemanager", location = "/blog/member-circle-manager.action?id=${id}", type = "redirect"),
		@Result(name = "membercircleunmanager", location = "/blog/member-circle-unmanager.action?id=${id}", type = "redirect"),
		@Result(name = "membermycircle", location = "/blog/member-mycircle.action", type = "redirect") })
public class MemberCircleAction extends ActionSupport implements Preparable {

	@Autowired
	private MemberCircleManager memberCircleManager;
	@Autowired
	private MemberCircleUserManager memberCircleUserManager;
	@Autowired
	private MemberManager memberManager;
	@Autowired
	private ReceiveMessageManager receiveMessageManager;

	//基本属性
	private List circleTypeList;
	private List list;
	private Page page = new Page(20);
	private Member member;
	private MemberCircle entity;
	private String action;
	private Long id;
	private Long mcuid;
	private Integer circleIndex; //放置个人首页
	private String messageError; //错误信息
	private Integer unRead; //邮箱未读信息数量
	//用户名
	private String userName = SpringSecurityUtils.getCurrentUserName();

	//进入申请圈子
	@Actions( { @Action("/blog/member-addcircle") })
	public String enterCircle() {
		secure();
		if (member == null)
			return "home";
		int circleManagerSize = memberCircleManager.getCircleMemberManagerSize(member.getId());
		if (circleManagerSize < 1) {
			circleTypeList = memberCircleManager.getCircleTypeList(); //获取圈子类型
		} else {
			messageError = "1";
		}
		return this.SUCCESS;
	}

	//*****************************************我管理的圈子********************************
	@Actions( { @Action("/blog/member-circle") })
	public String circle() {
		secure();
		if (member == null)
			return "home";

		list = memberCircleManager.getManagerCircle(page, member.getId());

		return this.SUCCESS;
	}

	//查看我的管理圈子信息
	@Actions( { @Action("/blog/member-modicircle") })
	public String enteCircle() {
		secure();
		if (member == null)
			return "home";

		if (memberCircleManager.isSecureManagerCircle(id, member.getId())) {
			circleTypeList = memberCircleManager.getCircleTypeList(); //获取圈子类型
			entity = memberCircleManager.get(id);
		} else
			return "home"; //防止无权限用户

		return this.SUCCESS;
	}

	/**
	 * 修改我的管理圈子信息
	 * 
	 * @return
	 */
	public String modiCircle() {
		secure();
		if (member == null)
			return "home";

		MemberCircle memberCircle = new MemberCircle();
		memberCircle = memberCircleManager.get(entity.getId());
		memberCircle.setUpdateDate(new Date());
		memberCircle.setCircleImage(entity.getCircleImage());
		memberCircle.setCircleName(entity.getCircleName());
		memberCircle.setCircleType(entity.getCircleType());
		memberCircle.setDes(entity.getDes());
		memberCircle.setProvince(entity.getProvince());
		memberCircle.setCity(entity.getCity());
		memberCircle.setCounty(entity.getCounty());
		memberCircleManager.save(memberCircle);
		return "membercircle";

	}

	/**
	 * 删除我的管理圈子
	 * 
	 * @return
	 */
	public String delCircle() {
		secure();
		if (member == null)
			return "home";

		if (memberCircleManager.isSecureManagerCircle(id, member.getId())) {
			List list = memberCircleUserManager.getRelatingMemberCircleUser(id);
			for (int i = 0; i < list.size(); i++) {
				Object[] object = (Object[]) list.get(i);
				Long mcuid = (Long) object[0];
				memberCircleUserManager.delete(mcuid);
			}
			memberCircleManager.delete(id);
		} else
			return "home";
		return "membercircle";
	}

	/**
	 * 添加圈子
	 * 
	 * @return
	 */

	public String addCircle() {
		secure();
		if (member == null)
			return "home";

		int circleManagerSize = memberCircleManager.getCircleMemberManagerSize(member.getId());
		if (circleManagerSize < 1) {
			entity.setCreateDate(new Date());
			entity.setUid(member.getId());
			entity.setUpdateDate(new Date());
			entity.setVerifyState(0L);
			entity.setCommend(0l);
			memberCircleManager.save(entity);
		} else {
			return "home";
		}
		return "membercircle";

	}

	/**
	 * 获取圈子正式会员
	 * 
	 * @return
	 */
	@Actions( { @Action("/blog/member-circle-manager") })
	public String queryCircleMember() {
		secure();
		if (member == null)
			return "home";

		list = memberCircleManager.getCircleMember(page, id, member.getId());

		return this.SUCCESS;
	}

	/**
	 * 获取圈子非正式会员
	 * 
	 * @return
	 */
	@Actions( { @Action("/blog/member-circle-unmanager") })
	public String queryUnCircleMember() {
		secure();
		if (member == null)
			return "home";

		list = memberCircleManager.getUnCircleMember(page, id);
		return this.SUCCESS;
	}

	/**
	 * 会员允许加入圈子
	 * 
	 * @return
	 */
	public String addCircleMember() {
		secure();
		if (member == null)
			return "home";

		MemberCircleUser memberCircleUser = new MemberCircleUser();
		if (memberCircleManager.isSecureManagerCircle(id, member.getId())) {
			memberCircleUser = memberCircleUserManager.get(mcuid);
			memberCircleUser.setState(1l);
			memberCircleUserManager.save(memberCircleUser);
		} else
			return "home";

		return "membercircleunmanager";
	}

	/**
	 * 拒绝会员加入圈子
	 * 
	 * @return
	 */
	public String refuseCircleMember() {
		secure();
		if (member == null)
			return "home";

		if (memberCircleManager.isSecureManagerCircle(id, member.getId())) {
			memberCircleUserManager.delete(mcuid);
		} else
			return "home";

		return "membercircleunmanager";

	}

	/**
	 * 删除圈子会员
	 * 
	 * @return
	 */
	public String delCircleMember() {
		secure();
		if (member == null)
			return "home";

		if (memberCircleManager.isSecureManagerCircle(id, member.getId())) {
			memberCircleUserManager.delete(mcuid);
		} else
			return "home";

		return "membercirclemanager";
	}

	//*************************************************我加入的圈子************************************************
	@Actions( { @Action("/blog/member-mycircle") })
	public String getmyCircle() {
		secure();
		if (member == null)
			return "home";

		list = memberCircleManager.getMyCircle(page, member.getId());
		return this.SUCCESS;
	}

	/**
	 * 会员后台退出圈子
	 * 
	 * @return
	 */
	public String quitCircle() {
		secure();
		if (member == null)
			return "home";

		if (memberCircleManager.isSecureCircleMember(id, member.getId())) {
			memberCircleUserManager.delete(mcuid);
		} else
			return "home";

		return "membermycircle";
	}

	/**
	 * 圈子 放置个人首页
	 * 
	 * @return
	 * @throws Exception
	 */
	@Actions( { @Action("/blog/circleIndexOperate") })
	public String circleOperate() throws Exception {
		secure();
		if (member == null)
			return "home";

		if (circleIndex != null) {
			MemberCircleUser memberCircleUser = memberCircleUserManager.get(id);
			memberCircleUser.setCircleIndex(circleIndex);
			memberCircleUserManager.save(memberCircleUser);
			Struts2Utils.renderText("true");
		} else {
			Struts2Utils.renderText("false");
		}

		return null;
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

	public void prepare() throws Exception {
		member = memberManager.getMemberByLoginName(SpringSecurityUtils.getCurrentUserName());
		unRead = receiveMessageManager.getReceiveUnReadCount(member);
	}

	public List getCircleTypeList() {
		return circleTypeList;
	}

	public void setCircleTypeList(List circleTypeList) {
		this.circleTypeList = circleTypeList;
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

	public MemberCircle getEntity() {
		return entity;
	}

	public void setEntity(MemberCircle entity) {
		this.entity = entity;
	}

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public Long getMcuid() {
		return mcuid;
	}

	public void setMcuid(Long mcuid) {
		this.mcuid = mcuid;
	}

	public Integer getCircleIndex() {
		return circleIndex;
	}

	public void setCircleIndex(Integer circleIndex) {
		this.circleIndex = circleIndex;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getMessageError() {
		return messageError;
	}

	public void setMessageError(String messageError) {
		this.messageError = messageError;
	}

	public Integer getUnRead() {
		return unRead;
	}

	public void setUnRead(Integer unRead) {
		this.unRead = unRead;
	}

}
