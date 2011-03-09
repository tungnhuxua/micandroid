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
import org.javaside.cms.entity.MemberMusic;
import org.javaside.cms.service.MemberManager;
import org.javaside.cms.service.MemberMusicManager;
import org.javaside.cms.service.ReceiveMessageManager;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

@Results( { @Result(name = "home", location = "/home.action", type = "redirect"),
		@Result(name = "member-music", location = "/blog/member-music.action", type = "redirect"),
		@Result(name = "member-music-add", location = "/blog/member-music-add.action", type = "redirect") })
public class MemberMusicAction extends ActionSupport implements Preparable {

	@Autowired
	private MemberMusicManager memberMusicManager;
	@Autowired
	private MemberManager memberManager;
	@Autowired
	private ReceiveMessageManager receiveMessageManager;

	private Page page = new Page(20);
	private Member member;
	private MemberMusic entity;
	private String action;
	private List list;
	private Long id;
	private String move;
	//用户名
	private String userName = SpringSecurityUtils.getCurrentUserName();
	private Integer unRead; //邮箱未读信息数量

	//用户添加音乐
	public String addMusic() throws Exception {
		secure();
		if (member == null)
			return "home";

		//获取用户音乐总条数，一个用户只能添加十首音乐
		if (memberMusicManager.getMemberMusicAllRows(member.getId()) < 10) {
			entity.setMusicCreateDate(new Date());
			entity.setUid(member.getId());
			entity.setMusicOrder(memberMusicManager.getMaxMemberMusicOrder(member.getId()) + 1l);
			memberMusicManager.save(entity);
			Struts2Utils.renderText("success");
		} else {
			Struts2Utils.renderText("notTenRows");
			return null;
		}
		return "member-music-add";
	}

	@Action("/blog/member-music")
	public String musicList() throws Exception {
		secure();
		if (member == null)
			return "home";

		list = memberMusicManager.getMemberMusicUidList(page, member.getId());
		return this.SUCCESS;
	}

	@Action("/blog/member-music-add")
	public String member_music_add() throws Exception {
		return this.SUCCESS;
	}

	//用户删除音乐
	public String delete() throws Exception {
		secure();
		if (member == null)
			return "home";

		if (memberMusicManager.isMemberMusicManager(member.getId(), id)) {
			memberMusicManager.delete(id);
		} else
			return "home";

		return "member-music";
	}

	//音乐上移和下移
	public String musicMove() throws Exception {
		secure();
		if (member == null)
			return "home";

		list = memberMusicManager.getMemberMusicUidList(page, member.getId());
		if (memberMusicManager.isMemberMusicManager(member.getId(), id)) {
			for (int i = 0; i < list.size(); i++) {
				Object[] object = (Object[]) list.get(i);
				if (object[0].toString().equals(id.toString())) {
					if (move.equals("up")) {
						try {
							//当前条操作
							Object[] object2 = (Object[]) list.get(i - 1);
							Long musicId_currently = (Long) object[0];
							MemberMusic currentlyRow = memberMusicManager.get(musicId_currently);
							Long order = (Long) object2[3];
							currentlyRow.setMusicOrder(order + 1);
							memberMusicManager.save(currentlyRow);

							//上一条操作
							Long musicId_up = (Long) object2[0];
							MemberMusic upRow = memberMusicManager.get(musicId_up);
							upRow.setMusicOrder(order - 1);
							memberMusicManager.save(upRow);
						} catch (Exception x) {
							return "member-music";
						}
					} else {
						//下移
						try {
							//当前条操作
							Object[] object2 = (Object[]) list.get(i + 1);
							Long musicId_currently = (Long) object[0];
							MemberMusic currentlyRow = memberMusicManager.get(musicId_currently);
							Long order = (Long) object2[3];
							currentlyRow.setMusicOrder(order - 1);
							memberMusicManager.save(currentlyRow);

							//上一条操作
							Long musicId_up = (Long) object2[0];
							MemberMusic upRow = memberMusicManager.get(musicId_up);
							upRow.setMusicOrder(order + 1);
							memberMusicManager.save(upRow);
						} catch (Exception x) {
							return "member-music";
						}

					}
				}
			}
		} else
			return "home";

		return "member-music";
	}

	//用户音乐置顶操作
	public String musicOrderTop() {
		secure();
		if (member == null)
			return "home";
		if (memberMusicManager.isMemberMusicManager(member.getId(), id)) {
			MemberMusic memberMusic = memberMusicManager.get(id);
			memberMusic.setMusicOrder(memberMusicManager.getMaxMemberMusicOrder(member.getId()) + 1);
			memberMusicManager.save(memberMusic);
		} else
			return "home";

		return "member-music";
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

	public MemberMusic getEntity() {
		return entity;
	}

	public void setEntity(MemberMusic entity) {
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

	public String getMove() {
		return move;
	}

	public void setMove(String move) {
		this.move = move;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
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
