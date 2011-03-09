package org.javaside.cms.web.blog;

import java.util.Date;

import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.javaside.cms.core.Page;
import org.javaside.cms.core.SpringSecurityUtils;
import org.javaside.cms.entity.Member;
import org.javaside.cms.entity.ReceiveMessage;
import org.javaside.cms.entity.SendMessage;
import org.javaside.cms.service.MemberManager;
import org.javaside.cms.service.ReceiveMessageManager;
import org.javaside.cms.service.SendMessageManager;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

@SuppressWarnings("serial")
@Results( { @Result(name = "home", location = "/home.action", type = "redirect"),
		@Result(name = "toSend", location = "/blog/message!send.action", type = "redirect"),
		@Result(name = "toReceive", location = "/blog/message!receive.action", type = "redirect") })
public class MessageAction extends ActionSupport implements Preparable {

	private Long id;
	private Member member;
	private String ids; //用户ID，多个ID 用 ","隔开
	private Long[] mids; //信息ID
	private String title;
	private String content;
	private SendMessage sendMessage;
	private ReceiveMessage receiveMessage;
	private Page<ReceiveMessage> page = new Page<ReceiveMessage>(10);
	private Integer unRead; //邮箱未读信息数量
	private Integer count;
	private Long reid; //邮箱回复 的ID
	private Long mid; //会员ID，用于发邮件
	//用户名
	private String userName = SpringSecurityUtils.getCurrentUserName();
	@Autowired
	private ReceiveMessageManager receiveMessageManager;
	@Autowired
	private SendMessageManager sendMessageManager;
	@Autowired
	private MemberManager memberManager;

	public void prepare() throws Exception {
		member = memberManager.getMemberByLoginName(SpringSecurityUtils.getCurrentUserName());
		unRead = receiveMessageManager.getReceiveUnReadCount(member);
	}

	public String input() throws Exception {

		if (member == null)
			return "home";
		if (reid != null) {
			receiveMessage = receiveMessageManager.get(reid);
		} else if (mid != null) {
			Member m = memberManager.get(mid);
			receiveMessage = new ReceiveMessage();
			receiveMessage.setFromMember(m);
		}
		count = sendMessageManager.getSendCount(member);
		return INPUT;
	}

	public String senddetail() throws Exception {

		if (member == null)
			return "home";
		sendMessage = sendMessageManager.get(id);
		count = sendMessageManager.getSendCount(member);
		return "senddetail";
	}

	public String receivedetail() throws Exception {

		if (member == null)
			return "home";
		receiveMessage = receiveMessageManager.get(id);
		if (receiveMessage != null) {
			receiveMessage.setIsread("1");
			receiveMessageManager.save(receiveMessage);
		}
		count = receiveMessageManager.getReceiveCount(member);
		return "receivedetail";
	}

	/**
	 * 保存个人信息档案
	 */

	public String save() throws Exception {

		if (member == null)
			return "home";
		if (ids == null || "".equals(ids))
			return this.INPUT;
		ids = ids.replace("，", ",");
		String[] idarry = ids.split(",");
		Member toMember;
		for (String id : idarry) {
			toMember = memberManager.getMemberByLoginName(id);
			if (toMember != null) {
				ReceiveMessage entity = new ReceiveMessage();
				entity.setTitle(title);
				entity.setContent(content);
				entity.setFromMember(member);
				entity.setToMember(toMember);
				entity.setCreateDate(new Date());
				receiveMessageManager.save(entity);

				SendMessage send = new SendMessage();
				send.setTitle(title);
				send.setContent(content);
				send.setFromMember(member);
				send.setToMember(toMember);
				send.setCreateDate(new Date());
				sendMessageManager.save(send);
			}
		}
		count = receiveMessageManager.getReceiveCount(member);
		return this.SUCCESS;
	}

	/**
	 * 删除已发信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String sendDelete() throws Exception {

		if (member == null)
			return "home";
		sendMessageManager.delete(id, member);
		return "toSend";
	}

	/**
	 * 删除收件信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String receiveDelete() throws Exception {

		if (member == null)
			return "home";
		receiveMessageManager.delete(id, member);
		return "toReceive";
	}

	public String deleteSendBatch() throws Exception {

		if (member == null)
			return "home";
		sendMessageManager.deleteBatch(mids, member);
		return "toSend";
	}

	public String deleteReceiveBatch() throws Exception {

		if (member == null)
			return "home";
		receiveMessageManager.deleteBatch(mids, member);
		return "toReceive";
	}

	/**
	 * 收件箱
	 * 
	 * @return
	 * @throws Exception
	 */
	public String receive() throws Exception {

		if (member == null)
			return "home";
		page = receiveMessageManager.getReceiveMessage(page, member);
		return "receive";
	}

	/**
	 * 收件箱
	 * 
	 * @return
	 * @throws Exception
	 */
	public String send() throws Exception {

		if (member == null)
			return "home";
		page = sendMessageManager.getSendMessage(page, member);
		return "send";
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Page<ReceiveMessage> getPage() {
		return page;
	}

	public void setPage(Page<ReceiveMessage> page) {
		this.page = page;
	}

	public Long[] getMids() {
		return mids;
	}

	public void setMids(Long[] mids) {
		this.mids = mids;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public SendMessage getSendMessage() {
		return sendMessage;
	}

	public void setSendMessage(SendMessage sendMessage) {
		this.sendMessage = sendMessage;
	}

	public ReceiveMessage getReceiveMessage() {
		return receiveMessage;
	}

	public void setReceiveMessage(ReceiveMessage receiveMessage) {
		this.receiveMessage = receiveMessage;
	}

	public Integer getUnRead() {
		return unRead;
	}

	public void setUnRead(Integer unRead) {
		this.unRead = unRead;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Long getReid() {
		return reid;
	}

	public void setReid(Long reid) {
		this.reid = reid;
	}

	public Long getMid() {
		return mid;
	}

	public void setMid(Long mid) {
		this.mid = mid;
	}
}
