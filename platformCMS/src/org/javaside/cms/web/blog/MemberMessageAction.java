package org.javaside.cms.web.blog;

import java.util.Date;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.javaside.cms.core.Page;
import org.javaside.cms.core.SpringSecurityUtils;
import org.javaside.cms.core.Struts2Utils;
import org.javaside.cms.entity.Member;
import org.javaside.cms.entity.MemberMessage;
import org.javaside.cms.entity.MemberMessageSetup;
import org.javaside.cms.service.MemberManager;
import org.javaside.cms.service.MemberMessageManager;
import org.javaside.cms.service.MemberMessageSetupManager;
import org.javaside.cms.service.ReceiveMessageManager;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

@Results( { @Result(name = "home", location = "/home.action", type = "redirect"),
		@Result(name = "member-message", location = "/blog/member-message.action", type = "redirect") })
public class MemberMessageAction extends ActionSupport implements Preparable {

	@Autowired
	private MemberManager memberManager;
	@Autowired
	private MemberMessageManager memberMessageManager;
	@Autowired
	private MemberMessageSetupManager memberMessageSetupManager;
	@Autowired
	private ReceiveMessageManager receiveMessageManager;

	private Page page = new Page(20);
	private Page<MemberMessage> answerPage = new Page(20);
	private Member member;
	private Member tomember;
	private MemberMessage entity;
	private int messageType;
	private List list;
	private List listAnswer;
	private Long id;
	private String action;
	private int quietlyCount; //悄悄话统计总数
	private Integer unRead; //邮箱未读信息数量
	//用户名
	private String userName = SpringSecurityUtils.getCurrentUserName();

	@Action("/blog/member-message")
	public String memberMessage() throws Exception {
		secure();
		if (member == null)
			return "home";

		list = memberMessageManager.getMemberMessage(page, member.getId());
		listAnswer = memberMessageManager.getMemberMessageAnswer(member.getId());
		messageType = memberMessageManager.getMemberMessageSetup(member.getId());
		quietlyCount = memberMessageManager.getQuietlyCount(member.getId());

		memberMessageManager.setupExamine(member.getId());//将未查看的留言设置已查看
		return this.SUCCESS;
	}

	@Action("/blog/member-message-notanswer")
	public String notMessageAnswer() throws Exception {
		secure();
		if (member == null)
			return "home";

		answerPage = memberMessageManager.getnotAnswer(answerPage, member);
		List answerList = answerPage.getResult();
		for (int i = 0; i < answerList.size(); i++) {
			MemberMessage message = (MemberMessage) answerList.get(i);
			Member memberTemp = memberManager.get(message.getUid());
			message.setMemberTemp(memberTemp);
//			list = memberMessageManager.getMemberMyMessage(message.getConnectionId());
//			list.add(list);    //暂时保留
		}
		memberMessageManager.setupNotAnser(member); // 将未查看的留言回复设置已查看
		return this.SUCCESS;
	}

	public String setupMessageType() throws Exception {
		secure();
		if (member == null)
			return "home";

		MemberMessageSetup memberMessageSetup = memberMessageSetupManager.getMemberMessageSetupID(member.getId());
		memberMessageSetup.setMessageType(messageType);
		memberMessageSetupManager.save(memberMessageSetup);
		return null;
	}

	public String delMessage() throws Exception {
		secure();
		if (member == null)
			return "home";

		if (memberMessageManager.isMessageSecure(id, member.getId())) { //判断是否有删除权限 
			List messageAnswerId = memberMessageManager.getMemberMessageAnswerId(id);
			Object[] object = null;
			for (int i = 0; i < messageAnswerId.size(); i++) {
				object = (Object[]) messageAnswerId.get(i);
				memberMessageManager.delete(Long.valueOf(object[0].toString())); //删除关联留言的回复
			}
			memberMessageManager.delete(id); //删除留言
		}
		return "member-message";
	}

	/**
	 * 用户留言 loction : blogFront
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action("/blog/member-message-add")
	public String comment() throws Exception {
		String username = SpringSecurityUtils.getCurrentUserName();
		if (username == null || "roleAnonymous".equals(username)) {
			Struts2Utils.renderText("nologin");
			return null;
		}

		member = memberManager.getMemberByLoginName(SpringSecurityUtils.getCurrentUserName());
		Date initDate = memberMessageManager.timeSeconds(member.getId()); // 取得上次发表留言时间
		Date before = new Date(initDate.getTime() + 1000 * 10); // 取得上次发表留言时间，加10妙钟
		Date date = new Date(); //当前时间
		if (before.getTime() > date.getTime()) {//如果上次发表留言和当前时间发表留言时间大于10妙钟，则为灌水
			Struts2Utils.renderText("seconds");
			return null;
		} else {
			entity.setMember(member);
			entity.setType(1l);
			memberMessageManager.save(entity);
		}

		return this.SUCCESS;
	}

	/**
	 * @return
	 * @throws Exception
	 */
	@Action("/blog/member-message-answer")
	public String commentAnswer() throws Exception {
		secure();
		if (member == null)
			return "home";

		entity.setMember(tomember);
		entity.setUid(member.getId());
		entity.setType(2l);
		entity.setExamine(1);
		memberMessageManager.save(entity);

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

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

	public List getListAnswer() {
		return listAnswer;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setListAnswer(List listAnswer) {
		this.listAnswer = listAnswer;
	}

	public MemberMessage getEntity() {
		return entity;
	}

	public void setEntity(MemberMessage entity) {
		this.entity = entity;
	}

	public int getMessageType() {
		return messageType;
	}

	public void setMessageType(int messageType) {
		this.messageType = messageType;
	}

	public Member getTomember() {
		return tomember;
	}

	public void setTomember(Member tomember) {
		this.tomember = tomember;
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

	public void prepare() throws Exception {
		member = memberManager.getMemberByLoginName(SpringSecurityUtils.getCurrentUserName());
		unRead = receiveMessageManager.getReceiveUnReadCount(member);
	}

	public int getQuietlyCount() {
		return quietlyCount;
	}

	public void setQuietlyCount(int quietlyCount) {
		this.quietlyCount = quietlyCount;
	}

	public Page<MemberMessage> getAnswerPage() {
		return answerPage;
	}

	public void setAnswerPage(Page<MemberMessage> answerPage) {
		this.answerPage = answerPage;
	}

	public Integer getUnRead() {
		return unRead;
	}

	public void setUnRead(Integer unRead) {
		this.unRead = unRead;
	}
}
