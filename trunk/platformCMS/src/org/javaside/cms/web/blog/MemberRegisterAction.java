package org.javaside.cms.web.blog;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.javaside.cms.core.Struts2Utils;
import org.javaside.cms.entity.MailActivation;
import org.javaside.cms.entity.Member;
import org.javaside.cms.entity.MemberGroup;
import org.javaside.cms.entity.MemberInfo;
import org.javaside.cms.entity.MemberMessageSetup;
import org.javaside.cms.service.MailActivtionManager;
import org.javaside.cms.service.MemberGroupManager;
import org.javaside.cms.service.MemberGroupUserManager;
import org.javaside.cms.service.MemberInfoManager;
import org.javaside.cms.service.MemberManager;
import org.javaside.cms.service.MemberMessageSetupManager;
import org.javaside.cms.util.SendMail;
import org.javaside.cms.util.StrUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

@SuppressWarnings("serial")
public class MemberRegisterAction extends ActionSupport implements ModelDriven<Member>, Preparable {

	@Autowired
	private MailActivtionManager mailActivtionManager;
	@Autowired
	private MemberManager memberManager;
	@Autowired
	private MemberGroupUserManager memberGroupUserManager;
	@Autowired
	private MemberGroupManager memberGroupManager;
	@Autowired
	private MemberMessageSetupManager memberMessageSetupManager;
	@Autowired
	private MemberInfoManager infoManager;

	private Member member;
	private MemberInfo memberinfo;
	private MailActivation mailActivtion;
	private Boolean isUidUsercode; //是否注册成功
	private Boolean isMidMailcode; //是否邮件激活成功
	private Long mid;
	private Integer unRead; //邮箱未读信息数量

	/**
	 * 会员发送邀请链接给游客做验证
	 * 
	 * @return
	 * @throws Exception
	 */
	@Actions( { @Action("/member-register") })
	public String register() throws Exception {
		Long uid = member.getId();
		String usercode = member.getUsercode();
		isUidUsercode = memberManager.isUidUsercode(uid, usercode);
		if (isUidUsercode) {
			member = memberManager.get(member.getId());
			memberinfo = member.getInfo();
		}
		return SUCCESS;
	}

	/**
	 * 支持使用Jquery.validate Ajax检验用户名是否重复.
	 */
	@Actions( { @Action("/checkloginname") })
	public String checkLoginName() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String loginName = request.getParameter("loginName");
		String orgLoginName = request.getParameter("orgLoginName");

		if (memberManager.isLoginNameUnique(loginName, orgLoginName)) {
			Struts2Utils.renderText("true");
		} else {
			Struts2Utils.renderText("false");
		}
		//因为直接输出内容而不经过jsp,因此返回null.
		return null;
	}

	/**
	 * 游客注册
	 * 
	 * @return
	 * @throws Exception
	 */
	@Actions( { @Action("/member-reg-success") })
	public String regUserSave() throws Exception {
		if (memberManager.isLoginName(member.getLoginName())) { //判断是否唯一
			member.setUsercode(RandomStringUtils.random(20, StrUtils.N36_CHARS));
			member.setCreateDate(new Date());
			member.setState(0); //非会员注册状态值为0
			memberManager.save(member);
			addActionMessage("保存用户成功");
			SendMail(); //非注册用户激活邮件

			Member reMember = memberManager.getMemberId(mid);
			MemberInfo info = infoManager.getMemberInfoUid(reMember);
			info.setMark(reMember.getInfo().getMark() + 200l);
			infoManager.save(info); //成功邀请一位会员，将会给邀请人加200积分。
		}
		return SUCCESS;
	}

	/**
	 * 非会员邮件激活
	 * 
	 * @return
	 * @throws Exception
	 */
	@Actions( { @Action("/member-mail-success") })
	public String mailActivation() throws Exception {
		isMidMailcode = mailActivtionManager.isMidMailcode(mailActivtion.getId(), mailActivtion.getMailCode());
		if (isMidMailcode) {
			MailActivation mailactivation = mailActivtionManager.get(mailActivtion.getId());
			Member member = memberManager.get(mailactivation.getMid());
			member.setState(1);
			memberManager.save(member); //邮件激活成功后，把状态改为1.
			mailActivtionManager.delete(mailActivtion.getId());//邮件激活成功后，删除此条激活记录

			MemberGroup group = new MemberGroup();
			group.setUid(member.getId());
			group.setGroupName("朋友");
			group.setGroupType(memberGroupUserManager.getMaxGroupType(member.getId()) + 1);
			memberGroupManager.save(group); //用户激活邮件后，保存用户默认“朋友”分组

			MemberMessageSetup memberMessageSetup = new MemberMessageSetup();
			memberMessageSetup.setUid(member.getId());
			memberMessageSetup.setMessageType(1);
			memberMessageSetupManager.save(memberMessageSetup);//用户激活邮件后，保存留言权限设置，默认为1，对外公开。。
		}
		return SUCCESS;
	}

	/** 
	 * 非会员发送邮件激活链接
	 * 
	 * @throws Exception
	 */
	public void SendMail() throws Exception {
		mailActivtion.setCreateDate(new Date());
		mailActivtion.setMailCode(RandomStringUtils.random(10, StrUtils.N36_CHARS) + "-"
				+ RandomStringUtils.random(5, StrUtils.N36_CHARS) + "-"
				+ RandomStringUtils.random(10, StrUtils.N36_CHARS) + "-"
				+ RandomStringUtils.random(5, StrUtils.N36_CHARS));
		mailActivtion.setMid(member.getId());
		mailActivtionManager.save(mailActivtion);
		SendMail send = new SendMail();
		String url = "http://www.ooowo.com/member-mail-success.action?mailActivtion.id=" + mailActivtion.getId()
				+ "&&mailActivtion.mailCode=" + mailActivtion.getMailCode();
		String content = "<div class='ii gt' id=':gi'>您好！<br/>非常感谢您通过<a target='_blank' href='http://www.ooowo.com'>www.ooowo.com</a>网站提交了注册申<wbr/>请。我们非常期待能为您提供有价值的专业信息服务。<br/>现在请您点击以下链接（如链接无法点击，<wbr/>建议您复制以下链接内容，并粘贴至浏览器地址栏中，点击运行），<wbr/>完成最后一步注册激活工作。<br/><br/><a href='"+ url +"' style='color: red;'>" + url + "</a><br/><br/>成功激活后，即刻开始您在设计圈网站的快乐之旅。<br/><br/>衷心祝福您工作愉快、生活幸福、学业有成！<br/><br/>";
		send.send("113444884@qq.com", member.getLoginName(), "设计圈账户注册激活，欢迎您的加入。", content);

	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public Boolean getIsUidUsercode() {
		return isUidUsercode;
	}

	public void setIsUidUsercode(Boolean isUidUsercode) {
		this.isUidUsercode = isUidUsercode;
	}

	public Boolean getIsMidMailcode() {
		return isMidMailcode;
	}

	public void setIsMidMailcode(Boolean isMidMailcode) {
		this.isMidMailcode = isMidMailcode;
	}

	public MailActivation getMailActivtion() {
		return mailActivtion;
	}

	public void setMailActivtion(MailActivation mailActivtion) {
		this.mailActivtion = mailActivtion;
	}

	public Long getMid() {
		return mid;
	}

	public void setMid(Long mid) {
		this.mid = mid;
	}

	public Member getModel() {
		return member;
	}

	public void prepare() throws Exception {
		member = new Member();
	}

	public MemberInfo getMemberinfo() {
		return memberinfo;
	}

	public void setMemberinfo(MemberInfo memberinfo) {
		this.memberinfo = memberinfo;
	}

	public Integer getUnRead() {
		return unRead;
	}

	public void setUnRead(Integer unRead) {
		this.unRead = unRead;
	}

}
