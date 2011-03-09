package org.javaside.cms.web.admin;

import java.util.List;

import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.javaside.cms.core.CRUDActionSupport;
import org.javaside.cms.core.Page;
import org.javaside.cms.entity.MemberCircle;
import org.javaside.cms.entity.MemberCircleUser;
import org.javaside.cms.service.MemberCircleManager;
import org.javaside.cms.service.MemberCircleUserManager;
import org.springframework.beans.factory.annotation.Autowired;

@SuppressWarnings("serial")
@Results( { @Result(name = CRUDActionSupport.RELOAD, location = "circlemanager.action", type = "redirect"),
		@Result(name = "notauditing", location = "circle-manager!notAuditing.action", type = "redirect"),
		@Result(name = "circlemanager", location = "circle-manager.action", type = "redirect") })
public class CircleManagerAction extends CRUDActionSupport<MemberCircle> {

	private List circleType; //圈子分类
	private List list; //圈子数据
	private Page page = new Page(10);
	private Integer state = 0; //圈子数据类型  0：审核通过的圈子  1：按类型审核通过的圈子 2：按圈子名称搜索
	private Long circleTypeId; //圈子分类id
	private Long circleId; //圈子id
	private Long uid; //创建人id
	private Long commend; //推荐
	private String circleName; //圈子名称

	@Autowired
	private MemberCircleManager memberCircleManager;
	@Autowired
	private MemberCircleUserManager memberCircleUserManager;

	@Override
	public String delete() throws Exception {
		List list = memberCircleUserManager.getRelatingMemberCircleUser(circleId);
		for (int i = 0; i < list.size(); i++) {
			Object[] object = (Object[]) list.get(i);
			Long mcuid = (Long) object[0];
			memberCircleUserManager.delete(mcuid);
		}
		memberCircleManager.delete(circleId);
		return "circlemanager";
	}

	@Override
	public String list() throws Exception {
		if (state == 0) {
			list = memberCircleManager.getBackCircle(page);
		}
		if (state == 1) {
			list = memberCircleManager.getBackCircleType(page, circleTypeId);
		}
		if (state == 2) {
			list = memberCircleManager.serachCircleName(page, circleName);
		}
		return SUCCESS;
	}

	//查询未审核通过的圈子
	public String notAuditing() throws Exception {
		list = memberCircleManager.getBackAuditingCircle(page);
		return "auditing";
	}

	//审核通过
	public String passAuditing() throws Exception {
		MemberCircle memberCircle = memberCircleManager.get(circleId);
		memberCircle.setVerifyState(1l);
		memberCircleManager.save(memberCircle); //更改状态为1，审核通过

		MemberCircleUser memberCircleUser = new MemberCircleUser();
		memberCircleUser.setCid(circleId);
		memberCircleUser.setState(1l); //1为群内会员 
		memberCircleUser.setStatus(1l);//1为群管理员
		memberCircleUser.setUid(uid);
		memberCircleUserManager.save(memberCircleUser); //把创建人设置成群管理员
		return "notauditing";
	}

	//是否推荐
	public String isCommend() throws Exception {
		MemberCircle memberCircle = memberCircleManager.get(circleId);
		if (commend == 1) {
			memberCircle.setCommend(0l);
		} else {
			memberCircle.setCommend(1l);
		}
		memberCircleManager.save(memberCircle);
		return "circlemanager";
	}

	@Override
	protected void prepareModel() throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public String save() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public String tree() throws Exception {
		circleType = memberCircleManager.getCircleTypeList();
		return "tree";
	}

	public List getCircleType() {
		return circleType;
	}

	public void setCircleType(List circleType) {
		this.circleType = circleType;
	}

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Long getCircleTypeId() {
		return circleTypeId;
	}

	public void setCircleTypeId(Long circleTypeId) {
		this.circleTypeId = circleTypeId;
	}

	public Long getCircleId() {
		return circleId;
	}

	public void setCircleId(Long circleId) {
		this.circleId = circleId;
	}

	public MemberCircle getModel() {
		// TODO Auto-generated method stub
		return null;
	}

	public Long getUid() {
		return uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}

	public Long getCommend() {
		return commend;
	}

	public void setCommend(Long commend) {
		this.commend = commend;
	}

	public String getCircleName() {
		return circleName;
	}

	public void setCircleName(String circleName) {
		this.circleName = circleName;
	}

}
