package org.javaside.cms.web.blog;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.javaside.cms.core.SpringSecurityUtils;
import org.javaside.cms.entity.LogCategory;
import org.javaside.cms.entity.Member;
import org.javaside.cms.entity.ShowCategory;
import org.javaside.cms.service.LogCategoryManager;
import org.javaside.cms.service.MemberManager;
import org.javaside.cms.service.ShowCategoryManager;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 自定义的日子，秀场管理
 * 
 * @author xinghua.zhou@gmail.com
 */
@SuppressWarnings("serial")
@Results( { @Result(name = "log", location = "article-log.action?cateId=${cateId}&logId=${logId}", type = "redirect"),
		@Result(name = "show", location = "article-show.action?cateId=${cateId}&logId=${logId}", type = "redirect") })
public class DefCategoryAction extends ActionSupport {
	@Autowired
	LogCategoryManager logManager;
	@Autowired
	ShowCategoryManager showManager;
	@Autowired
	private MemberManager memberManager;

	private LogCategory logCategory;
	private ShowCategory showCategory;
	private Long cateId = 54L; //日志，秀场的分类ID
	private Long logId = 0l; //日志，秀场自定义分类的ID ,0表示所有分类
	private Member member = null;

	/**
	 * 增加日志自定义分类
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action("/blog/addLogCategory")
	public String addLogCategory() throws Exception {
		member = memberManager.getMemberByLoginName(SpringSecurityUtils.getCurrentUserName());
		if (member == null)
			return "home";
		if (logCategory != null && logCategory.getName() != null && !logCategory.getName().equals("")
				&& logManager.uniqueCategory(member, logCategory.getName())) {
			logCategory.setMember(member);
			logManager.save(logCategory);
		}

		return "log";
	}

	/**
	 * 增加秀场自定义分类
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action("/blog/addShowCategory")
	public String addShowCategory() throws Exception {
		member = memberManager.getMemberByLoginName(SpringSecurityUtils.getCurrentUserName());
		if (member == null)
			return "home";

		if (showCategory != null && showCategory.getName() != null && !showCategory.getName().equals("")
				&& showManager.uniqueCategory(member, showCategory.getName())) {
			showCategory.setMember(member);
			showManager.save(showCategory);
		}
		return "show";
	}

	public LogCategory getLogCategory() {
		return logCategory;
	}

	public void setLogCategory(LogCategory logCategory) {
		this.logCategory = logCategory;
	}

	public ShowCategory getShowCategory() {
		return showCategory;
	}

	public void setShowCategory(ShowCategory showCategory) {
		this.showCategory = showCategory;
	}

	public Long getCateId() {
		return cateId;
	}

	public void setCateId(Long cateId) {
		this.cateId = cateId;
	}

	public Long getLogId() {
		return logId;
	}

	public void setLogId(Long logId) {
		this.logId = logId;
	}
}
