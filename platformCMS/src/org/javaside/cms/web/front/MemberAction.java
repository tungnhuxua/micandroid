package org.javaside.cms.web.front;

import org.apache.struts2.convention.annotation.Action;
import org.javaside.cms.core.Page;
import org.javaside.cms.core.SpringSecurityUtils;
import org.javaside.cms.entity.Member;
import org.javaside.cms.entity.MemberType;
import org.javaside.cms.service.MemberManager;
import org.javaside.cms.service.MemberTypeManager;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

/**
 * 江湖
 * 
 * @author
 */
public class MemberAction extends ActionSupport implements Preparable {

	// Spring 自动实例化这个类
	@Autowired
	private MemberManager memberManager;
	@Autowired
	private MemberTypeManager typeManager;

	//江湖
	private MemberType type;

	private Page<Member> seGirPage = new Page<Member>(14);
	private Page<Member> visualPage = new Page<Member>(14);
	private Page<Member> artPage = new Page<Member>(14);
	private Page<Member> mediaPage = new Page<Member>(14);
	private Page<Member> photographyPage = new Page<Member>(14);
	private Page<Member> planPage = new Page<Member>(14);
	private Page<Member> spealPage = new Page<Member>(14);

	private Page<Member> page = new Page<Member>(35);

	//用户名
	private String userName = SpringSecurityUtils.getCurrentUserName();
	private Member member; //用户信息

	@Action("/circle-heroes")
	public String heros() throws Exception {
		seGirPage = memberManager.getMemberByType(seGirPage, typeManager.get(3L));
		visualPage = memberManager.getMemberByType(visualPage, typeManager.get(4L));
		artPage = memberManager.getMemberByType(artPage, typeManager.get(5L));
		mediaPage = memberManager.getMemberByType(mediaPage, typeManager.get(6L));
		photographyPage = memberManager.getMemberByType(photographyPage, typeManager.get(7L));
		planPage = memberManager.getMemberByType(planPage, typeManager.get(8L));
		spealPage = memberManager.getMemberByType(spealPage, typeManager.get(9L));
		return this.SUCCESS;
	}

	@Action("/circle-hero-type")
	public String memberType() throws Exception {
		if (type != null && type.getId() != null) {
			type = typeManager.get(type.getId());
			if (type != null)
				page = memberManager.getMemberByType(page, type);
		}

		return this.SUCCESS;
	}

	@Action("/circle-member")
	public String allMember() throws Exception {
		page = memberManager.getAllMember(page);
		return this.SUCCESS;
	}

	@Action("/circle-member-new")
	public String newMember() throws Exception {
		page = memberManager.getNewMember(page);
		return this.SUCCESS;
	}

	@Action("/circle-member-hot")
	public String hotMember() throws Exception {
		page = memberManager.getHotMember(page);
		return this.SUCCESS;
	}

	@Action("/circle-member-columns")
	public String columnsMember() throws Exception {
		page = memberManager.getColumnsMember(artPage);
		return this.SUCCESS;
	}

	public Page<Member> getSeGirPage() {
		return seGirPage;
	}

	public void setSeGirPage(Page<Member> seGirPage) {
		this.seGirPage = seGirPage;
	}

	public Page<Member> getVisualPage() {
		return visualPage;
	}

	public void setVisualPage(Page<Member> visualPage) {
		this.visualPage = visualPage;
	}

	public Page<Member> getArtPage() {
		return artPage;
	}

	public void setArtPage(Page<Member> artPage) {
		this.artPage = artPage;
	}

	public Page<Member> getMediaPage() {
		return mediaPage;
	}

	public void setMediaPage(Page<Member> mediaPage) {
		this.mediaPage = mediaPage;
	}

	public Page<Member> getPhotographyPage() {
		return photographyPage;
	}

	public void setPhotographyPage(Page<Member> photographyPage) {
		this.photographyPage = photographyPage;
	}

	public Page<Member> getPlanPage() {
		return planPage;
	}

	public void setPlanPage(Page<Member> planPage) {
		this.planPage = planPage;
	}

	public Page<Member> getSpealPage() {
		return spealPage;
	}

	public void setSpealPage(Page<Member> spealPage) {
		this.spealPage = spealPage;
	}

	public MemberType getType() {
		return type;
	}

	public void setType(MemberType type) {
		this.type = type;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Page<Member> getPage() {
		return page;
	}

	public void setPage(Page<Member> page) {
		this.page = page;
	}
	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}
	public void prepare() throws Exception {
		if(!userName.equals("roleAnonymous") && userName !=null){
			member = memberManager.getMemberByLoginName(SpringSecurityUtils.getCurrentUserName()); //获取已登录用户个人信息
		}
			
	}
}
