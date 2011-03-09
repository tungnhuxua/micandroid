package org.javaside.cms.web.blog;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.javaside.cms.core.Page;
import org.javaside.cms.core.SpringSecurityUtils;
import org.javaside.cms.core.Struts2Utils;
import org.javaside.cms.entity.Article;
import org.javaside.cms.entity.Member;
import org.javaside.cms.entity.MemberCollection;
import org.javaside.cms.entity.MemberDynamics;
import org.javaside.cms.service.ArticleManager;
import org.javaside.cms.service.MemberCollectionManager;
import org.javaside.cms.service.MemberDynamicsManager;
import org.javaside.cms.service.MemberManager;
import org.javaside.cms.service.ReceiveMessageManager;
import org.javaside.cms.util.DateUtil;
import org.javaside.cms.util.StrUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

@SuppressWarnings("serial")
@Results( { @Result(name = "home", location = "/home.action", type = "redirect"),
		@Result(name = "member-collection", location = "/blog/member-collection.action", type = "redirect") })
public class MemberCollectionAction extends ActionSupport implements Preparable {

	@Autowired
	private MemberManager memberManager;
	@Autowired
	private MemberCollectionManager memberCollectionManager;
	@Autowired
	private MemberDynamicsManager memberDynamicsManager;
	@Autowired
	private ArticleManager articleManager;
	@Autowired
	private ReceiveMessageManager receiveMessageManager;

	private Page page = new Page(20);
	private MemberCollection entity;
	private Member member;
	private String action;
	private List list;
	private Long id;
	private Integer unRead; //邮箱未读信息数量
	//用户名
	private String userName = SpringSecurityUtils.getCurrentUserName();

	//获取用户收藏列表
	@Action("/blog/member-collection")
	public String collectionList() throws Exception {
		secure();
		if (member == null)
			return "home";

		list = memberCollectionManager.getMemberCollectionList(page, member.getId());
		return this.SUCCESS;
	}

	//删除收藏记录
	public String deleteCollection() throws Exception {
		secure();
		if (member == null)
			return "home";

		if (memberCollectionManager.isCollectionSecure(id, member.getId())) {
			memberCollectionManager.delete(id);
		} else
			return "home";

		return "member-collection";
	}

	//用户后管管理 置顶
	public String maxCollectionOrder() throws Exception {
		secure();
		if (member == null)
			return "home";

		if (memberCollectionManager.isCollectionSecure(id, member.getId())) {
			MemberCollection memberCollection = memberCollectionManager.get(id);
			memberCollection.setOrderid(memberCollectionManager.getMaxMemberCollectionOrder(member.getId()) + 1);
			memberCollection.setCreateDate(new Date());
			memberCollectionManager.save(memberCollection);

		} else
			return "home";
		return "member-collection";
	}

	//前台添加收藏
	public String addCollection() throws Exception {
		secure();
		//是否已登录
		String username = SpringSecurityUtils.getCurrentUserName();
		if (username == null || "roleAnonymous".equals(username)) {
			Struts2Utils.renderText("nologin");
			return null;
		}
		//是否重复收藏记录
		if (memberCollectionManager.isCollection(entity.getArticle().getId(), member.getId())) {
			Struts2Utils.renderText("iscollection");
			return null;
		} else {
			MemberCollection memberCollection = new MemberCollection();
			memberCollection.setArticle(entity.getArticle());
			memberCollection.setCreateDate(new Date());
			memberCollection.setOrderid(memberCollectionManager.getMaxMemberCollectionOrder(member.getId()) + 1);
			memberCollection.setUid(member.getId());
			memberCollectionManager.save(memberCollection);

			Article articleTemp = articleManager.get(entity.getArticle().getId());
			MemberDynamics dynamics = new MemberDynamics();
			dynamics.setUid(member.getId());
			dynamics.setMessageType(4l);
			Map map = new HashMap();
			map.put("memberName", member.getName());
			map.put("memberId", member.getId());
			map.put("articleId", entity.getArticle().getId());
			map.put("articleTitle", StrUtils.txt2htm(articleTemp.getTitle()));
			map.put("date", DateUtil.getTimeFormat());
			map.put("create", entity.getArticle().getMember().getId());
			JSONObject json = JSONObject.fromObject(map);
			dynamics.setMessage(json.toString());
			memberDynamicsManager.save(dynamics);//保存用户动态

			memberDynamicsManager.delMemberDynamics50(member.getId()); //用户动态信息只保存50条，50条以后则删除
		}

		return "member-collection";
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
