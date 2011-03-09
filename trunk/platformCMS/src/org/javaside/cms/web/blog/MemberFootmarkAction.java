package org.javaside.cms.web.blog;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.javaside.cms.core.Page;
import org.javaside.cms.core.SpringSecurityUtils;
import org.javaside.cms.core.Struts2Utils;
import org.javaside.cms.entity.Member;
import org.javaside.cms.entity.MemberCollection;
import org.javaside.cms.service.MemberFootmarkManager;
import org.javaside.cms.service.MemberManager;
import org.javaside.cms.service.ReceiveMessageManager;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

@SuppressWarnings("serial")
@Results( { @Result(name = "home", location = "/home.action", type = "redirect"),
		@Result(name = "member-footmark", location = "/blog/member-footmark.action", type = "redirect") })
public class MemberFootmarkAction extends ActionSupport implements Preparable {

	@Autowired
	private MemberManager memberManager;
	@Autowired
	private MemberFootmarkManager memberFootmarkManager;
	@Autowired
	private ReceiveMessageManager receiveMessageManager;

	//基本属性
	private Page page = new Page(20);
	private MemberCollection entity;
	private Member member;
	private String action;
	private List list;
	private Long id;
	private Integer unRead; //邮箱未读信息数量
	//用户名
	private String userName = SpringSecurityUtils.getCurrentUserName();

	//获取用户足迹列表
	@Action("/blog/member-footmark")
	public String collectionList() throws Exception {
		secure();
		if (member == null)
			return "home";

		list = memberFootmarkManager.getMemberFootmarkList(page, member.getId());
		return this.SUCCESS;
	}

	//删除足迹记录
	public String deleteFootmark() throws Exception {
		secure();
		if (member == null)
			return "home";

		if (memberFootmarkManager.isFootmarkSecure(id, member.getId())) {
			memberFootmarkManager.delete(id);
		} else
			return "home";

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

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public MemberCollection getEntity() {
		return entity;
	}

	public void setEntity(MemberCollection entity) {
		this.entity = entity;
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
