package org.javaside.cms.web.blog;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.javaside.cms.core.CmsActionSet;
import org.javaside.cms.core.SpringSecurityUtils;
import org.javaside.cms.core.Struts2Utils;
import org.javaside.cms.entity.Member;
import org.javaside.cms.entity.MemberInfo;
import org.javaside.cms.service.MemberInfoManager;
import org.javaside.cms.service.MemberManager;
import org.javaside.cms.service.ReceiveMessageManager;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

@SuppressWarnings("serial")
@Results( { @Result(name = "home", location = "/home.action", type = "redirect"),
		@Result(name = "memmberInfo", location = "/blog/member-info.action", type = "redirect"),
		@Result(name = "memmberloginInfo", location = "/blog/memberlogin-info.action", type = "redirect"),
		@Result(name = "membernoticeinfo", location = "/blog/member-notice.action", type = "redirect"),
		@Result(name = "memberpicture", location = "/blog/member-picture.action", type = "redirect"),
		@Result(name = "memberwork", location = "/blog/member-work.action", type = "redirect"),
		@Result(name = "memberrequest", location = "/blog/member-request.action", type = "redirect"), })
public class MemberInfoAction extends ActionSupport implements Preparable {

	private Long id;
	private MemberInfo entity;
	private Member member;
	private String action;
	private String password;
	private String workId;
	private String workName;
	
	private Integer unRead; //邮箱未读信息数量
	//用户名
	private String userName = SpringSecurityUtils.getCurrentUserName();

	@Autowired
	private MemberInfoManager infoManager;
	@Autowired
	private MemberManager memberManager;
	@Autowired
	private ReceiveMessageManager receiveMessageManager;

	/**
	 * 初始化登录会员的个人档案信息
	 */
	@Override
	@Actions( { @Action("/blog/memberlogin-info"), @Action("/blog/member-info"), @Action("/blog/member-notice"),
			@Action("/blog/member-picture"), @Action("/blog/member-request"), @Action("/blog/member-mark"),
			@Action("/blog/member-domain"),@Action("/blog/member-work")})
	public String input() throws Exception {
		if (member == null)
			return "home";
		action = Struts2Utils.getRequest().getServletPath();
		// 去掉 .action 后缀
		if (action.indexOf(".") > 0) {
			action = action.substring(0, action.indexOf(".")).replace(".", "");
		}

		if (member == null)
			return "home";
		entity = member.getInfo();
		return this.SUCCESS;
	}

	/**
	 * 保存个人信息档案
	 */

	public String save() throws Exception {
		if (member == null)
			return "home";
		infoManager.save(entity);
		if (entity != null && entity.getMember() != null && entity.getMember().getName() != null) {
			String name = entity.getMember().getName();
			member = memberManager.get(member.getId());
			member.setName(name);
			memberManager.save(member);
		}
		//转向登录信息页面
		if ("memberlogininfo".equals(action))
			return "memmberloginInfo";
		else if ("membernoticeinfo".equals(action))
			return "membernoticeinfo";

		//个人档案页面
		return "memmberInfo";
	}

	/**
	 * 更新个性域名
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action("/blog/member-update-domain")
	public String updateDomain() throws Exception {
		if (member == null)
			return "home";
		String tact = "/" + entity.getDomain();
		for (String act : CmsActionSet.set) {
			if (tact == null || "".equals(tact) || tact.matches(act)) {

				return "member-domain";
			}
		}
		infoManager.save(entity);
		return "member-domain";
	}

	@Action("/blog/member-update")
	public String updatePassword() throws Exception {

		if (member == null)
			return "home";

		if (password != null && !"".equals(password)) {
			member.setPassword(password);
			memberManager.save(member);
		}
		return "member-password";
	}
	
	@Action("/blog/member-works-update")
	public String updateWorks()throws Exception{
		
		if (member == null)
			return "home";
		
		MemberInfo info = infoManager.get(entity.getId());
        info.setWorkId(workId);
        info.setWorkName(workName);
		infoManager.save(info);
		return "memberwork";
		
	}
	
	
	@Action("/blog/member-password")
	public String updatePasswordInput() throws Exception {
		if (member == null)
			return "home";
		return this.SUCCESS;
	}

	@Action("/checkoldpassword")
	public String checkPassword() throws Exception {
		if (member == null)
			return "home";
		String oldpassword = ServletActionContext.getRequest().getParameter("oldpassword");
		if (member == null || !oldpassword.equals(member.getPassword())) {
			Struts2Utils.renderText("false");
		} else if (oldpassword.equals(member.getPassword())) {
			Struts2Utils.renderText("true");
		}
		return null;
	}

	@Action("/checkdomain")
	public String checkdomain() throws Exception {
		String tact = "/" + entity.getDomain();
		for (String act : CmsActionSet.set) {
			if (tact == null || "".equals(tact) || tact.matches(act)) {
				Struts2Utils.renderText("false");
				return null;
			}
		}
		HttpServletRequest request = ServletActionContext.getRequest();
		String domain = request.getParameter("entity.domain");
		String oldDomain = request.getParameter("oldDomain");

		if (infoManager.isDomainUnique(domain, oldDomain)) {
			Struts2Utils.renderText("true");
			return null;
		}
		Struts2Utils.renderText("false");
		return null;
	}

	/**
	 * 绑定已登录的会员个人档案信息
	 */
	public void prepare() throws Exception {
		if (id != null) {
			entity = infoManager.get(id);
		} else {
			entity = new MemberInfo();
		}
		member = memberManager.getMemberByLoginName(SpringSecurityUtils.getCurrentUserName());
		unRead = receiveMessageManager.getReceiveUnReadCount(member);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public MemberInfo getEntity() {
		return entity;
	}

	public void setEntity(MemberInfo entity) {
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public String getWorkId() {
		return workId;
	}

	public void setWorkId(String workId) {
		this.workId = workId;
	}

	public String getWorkName() {
		return workName;
	}

	public void setWorkName(String workName) {
		this.workName = workName;
	}

}
