package org.javaside.cms.web.blog;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.javaside.cms.core.Page;
import org.javaside.cms.core.SpringSecurityUtils;
import org.javaside.cms.core.Struts2Utils;
import org.javaside.cms.entity.Article;
import org.javaside.cms.entity.Member;
import org.javaside.cms.entity.MemberMessageSetup;
import org.javaside.cms.service.MemberCircleManager;
import org.javaside.cms.service.MemberCollectionManager;
import org.javaside.cms.service.MemberGroupUserManager;
import org.javaside.cms.service.MemberManager;
import org.javaside.cms.service.MemberMessageManager;
import org.javaside.cms.service.MemberMessageSetupManager;
import org.javaside.cms.service.ReceiveMessageManager;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

@SuppressWarnings("serial")
@Results( { @Result(name = "home", location = "/home.action", type = "redirect"),
		@Result(name = "archives", location = "/blog/archives.action?uid=${uid}", type = "redirect") })
public class BlogFrontAction extends ActionSupport implements Preparable {

	@Autowired
	private MemberManager memberManager;
	@Autowired
	private MemberGroupUserManager memberGroupUserManager;
	@Autowired
	private MemberCollectionManager memberCollectionManager;
	@Autowired
	private MemberCircleManager memberCircleManager;
	@Autowired
	private MemberMessageManager memberMessageManager;
	@Autowired
	private MemberMessageSetupManager memberMessageSetupManager;
	@Autowired
	private ReceiveMessageManager receiveMessageManager;

	// 基本属性
	private Member tomember;
	private Member member;
	private Page<Article> page = new Page<Article>(20);//每页20条记录
	private Page messagePage = new Page(100);
	
	private List<Date> times = new ArrayList<Date>(); //会员日志，秀场月份时间列表
	private List<Date> years = new ArrayList<Date>(); //会员日志，秀场的年份时间列表
	private String timeType = "month"; //按月份，还是年份查询
	private String date; //日志秀场日期,按月份.
	private Date startDate;
	private Date endDate;
	
	private List list; //存放所有列表数据
	private List listAnswer; //存放留言回复数据
	private String messageError = "0";
	private Integer unRead; //邮箱未读信息数量
	private String action;
	//用户名
	private String userName = SpringSecurityUtils.getCurrentUserName();

	public void prepare() throws Exception {
		member = memberManager.getMemberByLoginName(SpringSecurityUtils.getCurrentUserName());
		unRead = receiveMessageManager.getReceiveUnReadCount(member);
		getUriActions(); //获取action地址
	}

	/**
	 * 获取会员好友列表
	 * 
	 * @return
	 * @throws Exception
	 */
	@Actions( { @Action("/blog/blog-friend"), @Action("/blog/blog-friend-list") })
	public String blogFriend() throws Exception {
		if (tomember != null && tomember.getId() != null) {
			member = memberManager.getMemberByLoginName(SpringSecurityUtils.getCurrentUserName());
			tomember = memberManager.get(tomember.getId());
			list = memberGroupUserManager.getMemberFriend(page, tomember.getId());
			initTimes();
		}

		return this.SUCCESS;
	}

	/**
	 * 获取会员圈子列表
	 * 
	 * @return
	 * @throws Exception
	 */
	@Actions( { @Action("/blog/blog-circle"), @Action("/blog/blog-circle-list") })
	public String blogCircle() throws Exception {
		if (tomember != null && tomember.getId() != null) {
			member = memberManager.getMemberByLoginName(SpringSecurityUtils.getCurrentUserName());
			tomember = memberManager.get(tomember.getId());
			list = memberCircleManager.getBlogCircle(page, tomember.getId());
			initTimes();
		}

		return this.SUCCESS;
	}

	/**
	 * 获取会员圈子列表
	 * 
	 * @return
	 * @throws Exception
	 */
	@Actions( { @Action("/blog/blog-collection"), @Action("/blog/blog-collection-list") })
	public String blogCollection() throws Exception {
		if (tomember != null && tomember.getId() != null) {
			member = memberManager.getMemberByLoginName(SpringSecurityUtils.getCurrentUserName());
			tomember = memberManager.get(tomember.getId());
			list = memberCollectionManager.getMemberCollectionList(page, tomember.getId());
			initTimes();
		}

		return this.SUCCESS;
	}

	/**
	 * 获取会员留言
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action("/blog/blog-message")
	public String blogMessage() throws Exception {
		if (tomember != null && tomember.getId() != null) {
			member = memberManager.getMemberByLoginName(SpringSecurityUtils.getCurrentUserName());
			tomember = memberManager.get(tomember.getId());

			MemberMessageSetup memberMessageSetup = memberMessageSetupManager.getMemberMessageSetupID(tomember.getId());
			if (memberMessageSetup.getMessageType().equals(1)) {//对外开放留言
				list = memberMessageManager.getMemberMessageFront(messagePage, tomember.getId());
				listAnswer = memberMessageManager.getMemberMessageAnswer(tomember.getId());
			}
			if (memberMessageSetup.getMessageType().equals(2) && member != null && member.getId() != null) {//隐藏留言
				if (member.getId() == tomember.getId()) {//自己能够查看留言
					list = memberMessageManager.getMemberMessageFront(messagePage, tomember.getId());
					listAnswer = memberMessageManager.getMemberMessageAnswer(tomember.getId());
				} else {
					messageError = "2";
				}

			}
			if (memberMessageSetup.getMessageType().equals(3) && member != null && member.getId() != null) {//好友才有权限查看留言
				if (memberGroupUserManager.isFriendRelation(member.getId(), tomember.getId())
						|| member.getId() == tomember.getId()) {
					list = memberMessageManager.getMemberMessageFront(messagePage, tomember.getId());
					listAnswer = memberMessageManager.getMemberMessageAnswer(tomember.getId());
				} else {
					messageError = "3";
				}
			}
			initTimes();
		}

		return this.SUCCESS;
	}

	/**
	 * 过去六个月的时间列表
	 */
	private void initTimes() {
		if (tomember == null)
			return;
		Date createDate = tomember.getCreateDate();
		Calendar createCl = Calendar.getInstance();
		if (createCl != null) {
			createCl.setTime(createDate);
		}

		Calendar nowCl = Calendar.getInstance();

		int createYear = createCl.get(Calendar.YEAR);
		int nowYear = nowCl.get(Calendar.YEAR);

		if (createYear < nowYear) {
			for (int start = createYear; start < nowYear; start++) {
				years.add(createCl.getTime());
				createCl.add(Calendar.YEAR, 1);
			}

			Collections.reverse(years);
			if (createDate != null) {
				createCl.setTime(createDate);
			}

			Calendar startCl = Calendar.getInstance();
			startCl.set(Calendar.MONTH, 0);

			for (int start = 0; start <= nowCl.get(Calendar.MONTH); start++) {
				times.add(startCl.getTime());
				startCl.add(Calendar.MONTH, 1);
			}

		} else {
			int startMonth = createCl.get(Calendar.MONTH);
			for (int start = startMonth; start <= nowCl.get(Calendar.MONTH); start++) {
				times.add(createCl.getTime());
				createCl.add(Calendar.MONTH, 1);
			}
		}

		Collections.reverse(times);
	}

	/**
	 * 把date 属性转换成单月的开始时间，和结束时间
	 */
	private void initTime() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
		try {
			if ("year".equals(timeType)) {
				startDate = format.parse(date);
				Calendar cl = Calendar.getInstance();
				cl.setTime(startDate);
				cl.set(Calendar.MONTH, 0);
				startDate = cl.getTime();
				cl.add(Calendar.YEAR, 1);
				endDate = cl.getTime();
			} else {
				startDate = format.parse(date);
				Calendar cl = Calendar.getInstance();
				cl.setTime(startDate);
				cl.add(Calendar.MONTH, 1);
				endDate = cl.getTime();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
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
	public Member getTomember() {
		return tomember;
	}

	public void setTomember(Member tomember) {
		this.tomember = tomember;
	}

	public Page<Article> getPage() {
		return page;
	}

	public void setPage(Page<Article> page) {
		this.page = page;
	}

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

	public List<Date> getTimes() {
		return times;
	}

	public void setTimes(List<Date> times) {
		this.times = times;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public List getListAnswer() {
		return listAnswer;
	}

	public void setListAnswer(List listAnswer) {
		this.listAnswer = listAnswer;
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

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public Page getMessagePage() {
		return messagePage;
	}

	public void setMessagePage(Page messagePage) {
		this.messagePage = messagePage;
	}

	public List<Date> getYears() {
		return years;
	}

	public void setYears(List<Date> years) {
		this.years = years;
	}

	public String getTimeType() {
		return timeType;
	}

	public void setTimeType(String timeType) {
		this.timeType = timeType;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	

}
