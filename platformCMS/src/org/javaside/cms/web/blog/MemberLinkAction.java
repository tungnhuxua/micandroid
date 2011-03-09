package org.javaside.cms.web.blog;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.javaside.cms.core.Page;
import org.javaside.cms.core.SpringSecurityUtils;
import org.javaside.cms.core.Struts2Utils;
import org.javaside.cms.entity.Member;
import org.javaside.cms.entity.MemberLink;
import org.javaside.cms.service.MemberLinkManager;
import org.javaside.cms.service.MemberManager;
import org.javaside.cms.service.ReceiveMessageManager;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

@Results( { @Result(name = "home", location = "/home.action", type = "redirect"),
		@Result(name = "member-link", location = "/blog/member-link.action", type = "redirect") })
public class MemberLinkAction extends ActionSupport implements Preparable {

	@Autowired
	private MemberManager memberManager;
	@Autowired
	private MemberLinkManager memberLinkManager;
	@Autowired
	private ReceiveMessageManager receiveMessageManager;

	private Page page = new Page(20);
	private Member member;
	private String action;
	private MemberLink entity;
	private List list;
	private String move;
	private Long id;
	private String linkNameModi;
	private String linkUriModi;
	private Integer unRead; //邮箱未读信息数量
	//用户名
	private String userName = SpringSecurityUtils.getCurrentUserName();

	//添加链接 
	public String addLink() throws Exception {
		secure();
		if (member == null)
			return "home";
		if (memberLinkManager.getMemberLinkAllRows(member.getId()) < 20) {
			entity.setClickNumer(0l);
			entity.setUid(member.getId());
			entity.setLinkOrder(memberLinkManager.getMaxMemberLinkOrder(member.getId()) + 1);
			memberLinkManager.save(entity);
		} else {
			Struts2Utils.renderText("notTwentyRows");
			return null;
		}
		return "member-link";
	}

	@Action("/blog/member-link")
	public String linkList() throws Exception {
		secure();
		if (member == null)
			return "home";

		list = memberLinkManager.getLinkList(page, member.getId());
		return this.SUCCESS;
	}

	//链接删除
	public String deleteLink() throws Exception {
		secure();
		if (member == null)
			return "home";

		if (memberLinkManager.isMemberLinkManager(member.getId(), id)) {
			memberLinkManager.delete(id);
		} else
			return "home";

		return "member-link";
	}

	//链接修改
	public String linkModi() throws Exception {
		secure();
		if (member == null)
			return "home";

		if (memberLinkManager.isMemberLinkManager(member.getId(), entity.getId())) {
			MemberLink memberLink = memberLinkManager.get(entity.getId());
			memberLink.setLinkName(entity.getLinkName());
			memberLink.setLinkUri(entity.getLinkUri());
			memberLinkManager.save(memberLink);
		} else
			return "home";
		return "member-link";
	}

	//用户链接上下移动
	public String linkMove() throws Exception {
		secure();
		if (member == null)
			return "home";

		list = memberLinkManager.getLinkList(page, member.getId());

		for (int i = 0; i < list.size(); i++) {
			Object[] object = (Object[]) list.get(i);
			if (object[0].toString().equals(id.toString())) {
				if (move.equals("up")) {
					try {
						//当前条操作
						Object[] object2 = (Object[]) list.get(i - 1);
						Long musicId_currently = (Long) object[0];
						MemberLink currentlyRow = memberLinkManager.get(musicId_currently);
						Long order = (Long) object[3];
						currentlyRow.setLinkOrder(order - 1);
						memberLinkManager.save(currentlyRow);

						//上一条操作
						Long musicId_up = (Long) object2[0];
						MemberLink upRow = memberLinkManager.get(musicId_up);
						upRow.setLinkOrder(order + 1);
						memberLinkManager.save(upRow);
					} catch (Exception x) {
						return "member-music";
					}
				} else {
					//下移
					try {
						//当前条操作
						Object[] object2 = (Object[]) list.get(i + 1);
						Long musicId_currently = (Long) object[0];
						MemberLink currentlyRow = memberLinkManager.get(musicId_currently);
						Long order = (Long) object[3];
						currentlyRow.setLinkOrder(order + 1);
						memberLinkManager.save(currentlyRow);

						//上一条操作
						Long musicId_up = (Long) object2[0];
						MemberLink upRow = memberLinkManager.get(musicId_up);
						upRow.setLinkOrder(order - 1);
						memberLinkManager.save(upRow);
					} catch (Exception x) {
						return "member-music";
					}

				}
			}
		}

		return "member-link";
	}

	public void prepare() throws Exception {
		member = memberManager.getMemberByLoginName(SpringSecurityUtils.getCurrentUserName());
		unRead = receiveMessageManager.getReceiveUnReadCount(member);
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

	public MemberLink getEntity() {
		return entity;
	}

	public void setEntity(MemberLink entity) {
		this.entity = entity;
	}

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

	public String getMove() {
		return move;
	}

	public void setMove(String move) {
		this.move = move;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLinkNameModi() {
		return linkNameModi;
	}

	public void setLinkNameModi(String linkNameModi) {
		this.linkNameModi = linkNameModi;
	}

	public String getLinkUriModi() {
		return linkUriModi;
	}

	public void setLinkUriModi(String linkUriModi) {
		this.linkUriModi = linkUriModi;
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

}
