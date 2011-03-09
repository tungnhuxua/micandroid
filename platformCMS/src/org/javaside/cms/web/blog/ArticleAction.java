package org.javaside.cms.web.blog;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.javaside.cms.core.CRUDActionSupport;
import org.javaside.cms.core.Page;
import org.javaside.cms.core.SpringSecurityUtils;
import org.javaside.cms.core.Struts2Utils;
import org.javaside.cms.entity.Article;
import org.javaside.cms.entity.BlogArticleAccessMember;
import org.javaside.cms.entity.Category;
import org.javaside.cms.entity.Comment;
import org.javaside.cms.entity.HotTag;
import org.javaside.cms.entity.LogCategory;
import org.javaside.cms.entity.Member;
import org.javaside.cms.entity.MemberDynamics;
import org.javaside.cms.entity.MemberFootmark;
import org.javaside.cms.entity.MemberInfo;
import org.javaside.cms.entity.MemberLateGuest;
import org.javaside.cms.entity.ShowCategory;
import org.javaside.cms.entity.Tag;
import org.javaside.cms.service.ArticleManager;
import org.javaside.cms.service.BlogArticleAccessMemberManager;
import org.javaside.cms.service.CategoryManager;
import org.javaside.cms.service.CommentManager;
import org.javaside.cms.service.LogCategoryManager;
import org.javaside.cms.service.MemberDynamicsManager;
import org.javaside.cms.service.MemberFootmarkManager;
import org.javaside.cms.service.MemberGroupUserManager;
import org.javaside.cms.service.MemberInfoManager;
import org.javaside.cms.service.MemberLateGuestManager;
import org.javaside.cms.service.MemberLinkManager;
import org.javaside.cms.service.MemberManager;
import org.javaside.cms.service.ReceiveMessageManager;
import org.javaside.cms.service.ShowCategoryManager;
import org.javaside.cms.service.TagManager;
import org.javaside.cms.util.DateUtil;
import org.javaside.cms.util.StrUtils;
import org.springframework.beans.factory.annotation.Autowired;

@SuppressWarnings("serial")
@Results( {
		@Result(name = "log", location = "article-log.action?cateId=${cateId}&logId=${logId}&page.pageRequest=${page.pageRequest}", type = "redirect"),
		@Result(name = "show", location = "article-show.action?cateId=${cateId}&logId=${logId}&page.pageRequest=${page.pageRequest}", type = "redirect"),
		@Result(name = "home", location = "/home.action", type = "redirect") })
public class ArticleAction extends CRUDActionSupport<Article> {
	@Autowired
	private ArticleManager articleManager;
	@Autowired
	private CategoryManager categoryManager;
	@Autowired
	private MemberManager memberManager;
	@Autowired
	private MemberInfoManager memberInfoManager;
	@Autowired
	private TagManager tagManager;
	@Autowired
	private LogCategoryManager logManager;
	@Autowired
	private ShowCategoryManager showManager;
	@Autowired
	CommentManager commentManager;
	@Autowired
	private MemberFootmarkManager memberFootmarkManager;
	@Autowired
	private MemberLateGuestManager memberLateGuestManager;
	@Autowired
	private MemberLinkManager memberLinkManager;
	@Autowired
	private MemberGroupUserManager memberGroupUserManager;
	@Autowired
	private MemberDynamicsManager memberDynamicsManager;
	@Autowired
	private ReceiveMessageManager receiveMessageManager;

	@Autowired
	private BlogArticleAccessMemberManager blogArticleAccessManager;

	private Member member; //自己的信息

	private Member tomember; // 对方的信息

	// 基本属性
	private Article entity;
	private Long id;
	private Page<Article> page = new Page<Article>(20);//每页20条记录
	private Category categoryTmp;
	private Set<Category> categoryList;
	private Long cateId = 54L; //日志，秀场的分类ID

	private String artRandom = ""; //文章随机数
	private String tagStr; //标签

	private List<LogCategory> logCate; //日志自定义分类
	private List<ShowCategory> showCate; //秀场自定义分类

	private LogCategory logCategoryT;
	private ShowCategory showCategoryT;

	private Long logId = 0l; //日志，秀场自定义分类的ID ,0表示所有分类
	private Character ahiden;
	private Character aup;
	private Character upblog;

	private Article preArticle; // 上一篇文章
	private Article nextArticle; // 下一篇文章
	private Article article; //文章

	private Page<Comment> commentPage = new Page<Comment>(10); //文章评论列表
	//用户名
	private String userName = SpringSecurityUtils.getCurrentUserName();

	private List<Date> times = new ArrayList<Date>(); //会员日志，秀场月份时间列表
	private List<Date> years = new ArrayList<Date>(); //会员日志，秀场的年份时间列表
	private String timeType = "month"; //按月份，还是年份查询
	private String date; //日志秀场日期,按月份.
	private Date startDate;
	private Date endDate;

	private List linkList;//链接
	private List friendList; //朋友
	private List<HotTag> memberTag;
	private List memberLateGuestList; //最近访客
	private Tag tag;
	private Integer unRead; //邮箱未读信息数量
	private Integer logCount;//会员日志发布总数
	private Integer showCount;//会员秀场发布总数

	private List<BlogArticleAccessMember> blogArticleAccessList = new ArrayList();

	@Override
	protected void prepareModel() throws Exception {
		if (id != null) {
			entity = articleManager.get(id);
		} else {
			entity = new Article();
		}
		member = memberManager.getMemberByLoginName(SpringSecurityUtils.getCurrentUserName());
		unRead = receiveMessageManager.getReceiveUnReadCount(member);
	}

	//////////////BLOG 文章管理//////////////////////

	@Override
	public String delete() throws Exception {
		//获取登录会员信息
		member = memberManager.getMemberByLoginName(SpringSecurityUtils.getCurrentUserName());
		if (member == null)
			return "home";
		entity = articleManager.get(id);
		if (entity == null || !artRandom.equals(entity.getRandom())) {
			addActionMessage("权限不够，请与管理员联系！");
			if (cateId == 54l)
				return "log"; //返回日志列表
			else
				return "show"; //返回秀场列表
		}
		articleManager.delete(id);

		if (cateId == 54l)
			return "log"; //返回日志列表
		else
			return "show"; //返回秀场列表
	}

	@SuppressWarnings("unchecked")
	@Override
	@Actions( { @Action("/blog/article-log"), @Action("/blog/article-show") })
	public String list() throws Exception {
		//获取登录会员信息
		member = memberManager.getMemberByLoginName(SpringSecurityUtils.getCurrentUserName());
		if (member == null)
			return "home";
		Character isblog = '1'; //1，表示日志，2表示秀场
		if (cateId == 56l) { //56为秀场ID号
			isblog = '2';
		}
		page = articleManager.getArticleOfMember(member, page, isblog, logId);
		initDefCategory();
		unRead = receiveMessageManager.getReceiveUnReadCount(member);
		return this.SUCCESS;
	}

	@Action("/blog/tag-blog")
	public String tagblog() throws Exception {
		member = memberManager.getMemberByLoginName(SpringSecurityUtils.getCurrentUserName());
		page.setPageSize(20);
		if (tomember != null && tomember.getId() != null) {
			tomember = memberManager.get(tomember.getId());
			if (tag != null && tag.getId() != null)
				page = tagManager.getMemberArticleByTag(tomember, tag, page);
		}
		initTimes();
		unRead = receiveMessageManager.getReceiveUnReadCount(member);
		return this.SUCCESS;
	}

	/**
	 * 标签
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action("/blog/article-tag")
	public String tag() throws Exception {
		member = memberManager.getMemberByLoginName(SpringSecurityUtils.getCurrentUserName());
		if (tomember != null && tomember.getId() != null) {
			tomember = memberManager.get(tomember.getId());
			memberTag = tagManager.getMemberTag(tomember);
		}
		initTimes();
		unRead = receiveMessageManager.getReceiveUnReadCount(member);
		return this.SUCCESS;
	}

	@Override
	public String save() throws Exception {
		member = memberManager.getMemberByLoginName(SpringSecurityUtils.getCurrentUserName());
		if (member == null)
			return "home";

		if (!artRandom.equals(entity.getRandom())) {
			addActionMessage("非法操作!");
			if (cateId == 54l)
				return "log"; //返回日志列表
			else
				return "show"; //返回秀场列表
		}

		if (tagStr != null && !"".equals(tagStr)) {
			tagStr = tagStr.replace("，", ",");
			String[] tags = tagStr.split(",");
			Set<Tag> set = new LinkedHashSet<Tag>();
			//entity.getTags().clear();
			for (String tag : tags) {
				Tag tmp = tagManager.loadTagByName(tag);
				if (tmp == null) {
					tmp = new Tag();
					tmp.setName(tag);
				}
				set.add(tmp);
			}
			entity.setTags(set);
		}

		//重新指定文章所属栏目，防止出现修改所属栏目是出现修改栏目ID而报错
		if (categoryTmp != null && categoryTmp.getId() != null && categoryTmp.getId() != -1l) {
			entity.setCategory(categoryTmp);
		} else {
			entity.setCategory(null);
		}

		//日志自定义分类
		if (logCategoryT != null && logCategoryT.getId() != null && logCategoryT.getId() != -1l) {
			entity.setLogCategory(logCategoryT);
		} else {
			entity.setLogCategory(null);
		}

		//秀场自定义分类
		if (showCategoryT != null && showCategoryT.getId() != null && showCategoryT.getId() != -1l) {
			entity.setShowCategory(showCategoryT);
		} else {
			entity.setShowCategory(null);
		}

		if (entity.getId() == null && entity.getMember() == null) {
			//设置文章录入的会员
			entity.setMember(memberManager.getMemberByLoginName(SpringSecurityUtils.getCurrentUserName()));
		}
		//entity.setCreateDate(new Date());
		//entity.setAmendDate(new Date());
		if (cateId == 54l) {
			entity.setIsblog('1');
		} else {
			entity.setIsblog('2');
		}
		entity.setRandom(RandomStringUtils.random(20, StrUtils.N36_CHARS));//重设随机数
		articleManager.save(entity);

		if (id == null) {//当用户添加日志
			//保存用户动态
			MemberDynamics dynamics = new MemberDynamics();
			dynamics.setUid(member.getId());
			if (cateId == 54l)
				dynamics.setMessageType(2l);//日志
			else
				dynamics.setMessageType(3l);//作品
			Map map = new HashMap();
			map.put("memberName", member.getName());
			map.put("memberId", member.getId());
			map.put("articleId", entity.getId());
			map.put("articleTitle", StrUtils.txt2htm(entity.getTitle()));
			map.put("date", DateUtil.getTimeFormat());
			JSONObject json = JSONObject.fromObject(map);
			dynamics.setMessage(json.toString());
			memberDynamicsManager.save(dynamics);//保存用户动态
			memberDynamicsManager.delMemberDynamics50(member.getId()); //用户动态信息只保存50条，50条以后则删除
		}
		categoryTmp = entity.getCategory();
		if (cateId == 54l)
			return "log";
		return "show";
	}

	@Override
	public String input() throws Exception {
		if (entity != null && entity.getIsblog() != null) {
			if (entity.getIsblog().equals('2')) {
				cateId = 56l;
			}
		}
		cateId = cateId == null ? 54L : cateId;
		Category cat = categoryManager.get(cateId);
		categoryList = cat.getChild();
		initDefCategory();
		entity.setMember(member);
		unRead = receiveMessageManager.getReceiveUnReadCount(member);
		return this.INPUT;
	}

	/**
	 * 获取日志，或秀场的下级分类
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action("/blog/system-category")
	public String systemCategory() throws Exception {
		cateId = cateId == null ? 54l : cateId;
		Category cat = categoryManager.get(cateId);
		categoryList = cat.getChild();
		unRead = receiveMessageManager.getReceiveUnReadCount(member);
		return this.SUCCESS;
	}

	/**
	 * 日志 隐藏 置顶，首页特别推荐
	 * 
	 * @return
	 */
	@Action("/blog/operate")
	public String operate() {
		if (id != null) {
			entity = articleManager.get(id);
		}
		if (entity != null && entity.getId() != null) {
			if (ahiden != null) {
				entity.setHidden(ahiden);
			}
			if (aup != null) {
				entity.setUp(aup);
			}
			if (upblog != null) {
				entity.setUpspecial(upblog);
			}
			articleManager.save(entity);
			Struts2Utils.renderText("true");
		} else {
			Struts2Utils.renderText("false");
		}

		return null;
	}

	/**
	 * 初始化自定义日志，秀场分类
	 */
	private void initDefCategory() {
		member = memberManager.getMemberByLoginName(SpringSecurityUtils.getCurrentUserName());
		if (member == null)
			return;
		logCate = logManager.getLogCate(member);
		showCate = showManager.getShowCate(member);
	}

	/**
	 * BLOG 首页
	 * 
	 * @return
	 */
	@Action("/blog/blog-home")
	public String bloghome() throws Exception {
		if (tomember != null && tomember.getId() != null) {
			tomember = memberManager.get(tomember.getId());
			member = memberManager.getMemberByLoginName(SpringSecurityUtils.getCurrentUserName());
			page = articleManager.getBlogRecommendArticleOfMember(tomember, page);
			Page linkPage = new Page(20);
			Page friendPage = new Page(11);
			linkList = memberLinkManager.getLinkList(linkPage, tomember.getId());
			friendList = memberGroupUserManager.getMemberFriend(friendPage, tomember.getId());
			logCount = articleManager.getArticleCountMember(tomember,page, "1");
			showCount = articleManager.getArticleCountMember(tomember, page,"2");

			if (member != null) {
				unRead = receiveMessageManager.getReceiveUnReadCount(member);
				if (tomember.getId() != member.getId()) {//用户本身不能对于以下操作
					/** 用户总访问量 **/
					MemberInfo info = memberInfoManager.getMemberInfoUid(tomember);
					info.setAccessing(info.getAccessing() + 1); //访问一次加1
					memberInfoManager.save(info);//用户总访问量

					/** 保存最近访客 **/
					MemberLateGuest oneLateGuest = memberLateGuestManager.getOneMemberLateGuest(tomember.getId(),
							member); //得到一条重复访客记录
					List lateGuestSize = memberLateGuestManager.getMemberLateGuest(tomember.getId());//取得用户访客数据
					if (lateGuestSize.size() > 12) {//如果大于12个访客，按时间排序，删除最后一位访客
						MemberLateGuest memberLateGuest = (MemberLateGuest) lateGuestSize.get(12);
						memberLateGuestManager.delete(memberLateGuest.getId());
					}
					if (oneLateGuest != null) {//判断是否重复访客
						oneLateGuest.setCreateDate(new Date());
						memberLateGuestManager.save(oneLateGuest); //修改重复访客的最新时间
					} else {
						MemberLateGuest memberLateGuest = new MemberLateGuest();
						memberLateGuest.setUid(tomember.getId());
						memberLateGuest.setTomember(member);
						memberLateGuestManager.save(memberLateGuest); //保存最近访客
					}

				}
			}
		}
		return this.SUCCESS;
	}

	/**
	 * BLOG 会员日志
	 * 
	 * @return
	 * @throws Exception
	 */
	@Actions( { @Action("/blog/blog-log"), @Action("/blog/blog-log-list") })
	public String bloglog() throws Exception {
		if (tomember != null && tomember.getId() != null) {
			tomember = memberManager.get(tomember.getId());
			member = memberManager.getMemberByLoginName(SpringSecurityUtils.getCurrentUserName());
			page = articleManager.getArticleOfMember(tomember, page, "1");
			logCate = logManager.getLogCate(tomember);
			initTimes();
			unRead = receiveMessageManager.getReceiveUnReadCount(member);
		}
		return this.SUCCESS;
	}

	/**
	 * BLOG 会员分类日志列表
	 * 
	 * @return
	 * @throws Exception
	 */
	@Actions( { @Action("/blog/blog-logcategory"), @Action("/blog/blog-logcategory-list") })
	public String blogLogCategory() throws Exception {
		if (tomember != null && tomember.getId() != null) {
			member = memberManager.getMemberByLoginName(SpringSecurityUtils.getCurrentUserName());
			tomember = memberManager.get(tomember.getId());
			page = articleManager.getLogCategoryArticleOfMember(logCategoryT, tomember, page);
			logCate = logManager.getLogCate(tomember);
			initTimes();

		}
		return this.SUCCESS;
	}

	/**
	 * BLOG 会员秀场
	 * 
	 * @return
	 * @throws Exception
	 */
	@Actions( { @Action("/blog/blog-show"), @Action("/blog/blog-show-list") })
	public String blogshow() throws Exception {
		if (tomember != null && tomember.getId() != null) {
			tomember = memberManager.get(tomember.getId());
			member = memberManager.getMemberByLoginName(SpringSecurityUtils.getCurrentUserName());
			page = articleManager.getArticleOfMember(tomember, page, "2");
			showCate = showManager.getShowCate(tomember);
			unRead = receiveMessageManager.getReceiveUnReadCount(member);
			initTimes();
		}
		return this.SUCCESS;
	}

	/**
	 * BLOG 会员分类日志列表
	 * 
	 * @return
	 * @throws Exception
	 */
	@Actions( { @Action("/blog/blog-showcategory"), @Action("/blog/blog-showcategory-list") })
	public String blogShowCategory() throws Exception {
		if (tomember != null && tomember.getId() != null) {
			tomember = memberManager.get(tomember.getId());
			page = articleManager.getShowCategoryArticleOfMember(showCategoryT, tomember, page);
			showCate = showManager.getShowCate(tomember);
			initTimes();
		}
		return this.SUCCESS;
	}

	/**
	 * BLOG的文章详细内容
	 * 
	 * @return
	 */
	@Action("/blog/blog-content")
	public String blogcontent() {
		if (id != null) {
			entity = articleManager.get(id);
			if (entity != null) {
				if (articleManager.isMemberArticle(id, tomember)) { //防止用户在URL输入tomember.id，如果tomember.id有这条新闻权限，则返回TRUE
					tomember = memberManager.get(tomember.getId());
					nextArticle = articleManager.getNextArticleByMember(id);
					preArticle = articleManager.getPreArticleByMember(id);
					commentPage = commentManager.getCommentsByArticle(entity, commentPage);
				} else
					return "home";
			}
			memberLateGuestList = memberLateGuestManager.getMemberLateGuest(tomember.getId()); //最近访客

			/********** 足迹 *******/
			HttpServletRequest request = ServletActionContext.getRequest();
			String url = request.getServletPath() + "?" + request.getQueryString();
			member = memberManager.getMemberByLoginName(SpringSecurityUtils.getCurrentUserName());
			if (member != null) {
				if (memberFootmarkManager.isFootmark(getId(), member.getId())) { //是否重复足迹记录
					//如果已有足迹记录，返回true则不操作
				} else {
					MemberFootmark memberFootmark = new MemberFootmark();
					memberFootmark.setArticle(article = articleManager.get(id));
					memberFootmark.setCreateDate(new Date());
					memberFootmark.setOrderid(0l);
					memberFootmark.setUri(url);
					memberFootmark.setUid(member.getId());
					memberFootmarkManager.save(memberFootmark);
					memberFootmarkManager.getMemberFootmarkSize(member.getId()); //只保留20条足迹记录
				}

			}

			if (member != null && entity != null && member.getId().intValue() != entity.getMember().getId().intValue()) {
				/** 保存最近访客 **/
				BlogArticleAccessMember oneLateGuest = blogArticleAccessManager.getOneMemberLateGuest(entity, member); //得到一条重复访客记录
				List lateGuestSize = blogArticleAccessManager.getMemberLateGuest(entity);//取得用户访客数据
				if (lateGuestSize.size() > 12) {//如果大于12个访客，按时间排序，删除最后一位访客
					BlogArticleAccessMember memberLateGuest = (BlogArticleAccessMember) lateGuestSize.get(12);
					blogArticleAccessManager.delete(memberLateGuest.getId());
				}
				if (oneLateGuest != null) {//判断是否重复访客
					oneLateGuest.setCreateDate(new Date());
					blogArticleAccessManager.save(oneLateGuest); //修改重复访客的最新时间
				} else {
					BlogArticleAccessMember memberLateGuest = new BlogArticleAccessMember();
					memberLateGuest.setArticle(entity);
					memberLateGuest.setTomember(member);
					blogArticleAccessManager.save(memberLateGuest); //保存最近访客
				}

			}

			if (entity != null) {
				blogArticleAccessList = blogArticleAccessManager.getMemberLateGuest(entity);
			}

		}
		return this.SUCCESS;
	}

	/**
	 * 获取用户某月份或指定年份发布的秀场
	 * 
	 * @return
	 * @throws Exception
	 */
	@Actions( { @Action("/blog/blog-date-show"), @Action("/blog/blog-date-show-list") })
	public String blogShowDate() throws Exception {
		if (tomember != null && tomember.getId() != null) {
			initTime();
			tomember = memberManager.get(tomember.getId());
			page = articleManager.getShowArticleOfDate(tomember, page, startDate, endDate);
			showCate = showManager.getShowCate(tomember);
			initTimes();
		}
		member = memberManager.getMemberByLoginName(SpringSecurityUtils.getCurrentUserName());
		return this.SUCCESS;
	}

	/**
	 * 获取用户某月份发布的秀场
	 * 
	 * @return
	 * @throws Exception
	 */
	@Actions( { @Action("/blog/blog-date-log"), @Action("/blog/blog-date-log-list") })
	public String blogLogDate() throws Exception {
		if (tomember != null && tomember.getId() != null) {
			initTime();
			tomember = memberManager.get(tomember.getId());
			page = articleManager.getLogArticleOfDate(tomember, page, startDate, endDate);
			logCate = logManager.getLogCate(tomember);
			initTimes();
		}
		member = memberManager.getMemberByLoginName(SpringSecurityUtils.getCurrentUserName());
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

	public Article getModel() {
		return entity;
	}

	public Article getEntity() {
		return entity;
	}

	public void setEntity(Article entity) {
		this.entity = entity;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Page<Article> getPage() {
		return page;
	}

	public void setPage(Page<Article> page) {
		this.page = page;
	}

	public Category getCategoryTmp() {
		return categoryTmp;
	}

	public void setCategoryTmp(Category categoryTmp) {
		this.categoryTmp = categoryTmp;
	}

	public Set<Category> getCategoryList() {
		return categoryList;
	}

	public void setCategoryList(Set<Category> categoryList) {
		this.categoryList = categoryList;
	}

	public String getArtRandom() {
		return artRandom;
	}

	public void setArtRandom(String artRandom) {
		this.artRandom = artRandom;
	}

	public String getTagStr() {
		return tagStr;
	}

	public void setTagStr(String tagStr) {
		this.tagStr = tagStr;
	}

	public Long getCateId() {
		return cateId;
	}

	public void setCateId(Long cateId) {
		this.cateId = cateId;
	}

	public List<LogCategory> getLogCate() {
		return logCate;
	}

	public void setLogCate(List<LogCategory> logCate) {
		this.logCate = logCate;
	}

	public List<ShowCategory> getShowCate() {
		return showCate;
	}

	public void setShowCate(List<ShowCategory> showCate) {
		this.showCate = showCate;
	}

	public Long getLogId() {
		return logId;
	}

	public void setLogId(Long logId) {
		this.logId = logId;
	}

	public LogCategory getLogCategoryT() {
		return logCategoryT;
	}

	public void setLogCategoryT(LogCategory logCategoryT) {
		this.logCategoryT = logCategoryT;
	}

	public ShowCategory getShowCategoryT() {
		return showCategoryT;
	}

	public void setShowCategoryT(ShowCategory showCategoryT) {
		this.showCategoryT = showCategoryT;
	}

	public Character getAhiden() {
		return ahiden;
	}

	public void setAhiden(Character ahiden) {
		this.ahiden = ahiden;
	}

	public Character getAup() {
		return aup;
	}

	public void setAup(Character aup) {
		this.aup = aup;
	}

	public Character getUpblog() {
		return upblog;
	}

	public void setUpblog(Character upblog) {
		this.upblog = upblog;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public Article getPreArticle() {
		return preArticle;
	}

	public void setPreArticle(Article preArticle) {
		this.preArticle = preArticle;
	}

	public Article getNextArticle() {
		return nextArticle;
	}

	public void setNextArticle(Article nextArticle) {
		this.nextArticle = nextArticle;
	}

	public Page<Comment> getCommentPage() {
		return commentPage;
	}

	public void setCommentPage(Page<Comment> commentPage) {
		this.commentPage = commentPage;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public List<Date> getTimes() {
		return times;
	}

	public void setTimes(List<Date> times) {
		this.times = times;
	}

	public Member getTomember() {
		return tomember;
	}

	public void setTomember(Member tomember) {
		this.tomember = tomember;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	public List getLinkList() {
		return linkList;
	}

	public void setLinkList(List linkList) {
		this.linkList = linkList;
	}

	public List getFriendList() {
		return friendList;
	}

	public void setFriendList(List friendList) {
		this.friendList = friendList;
	}

	public List<HotTag> getMemberTag() {
		return memberTag;
	}

	public void setMemberTag(List<HotTag> memberTag) {
		this.memberTag = memberTag;
	}

	public Tag getTag() {
		return tag;
	}

	public void setTag(Tag tag) {
		this.tag = tag;
	}

	public List getMemberLateGuestList() {
		return memberLateGuestList;
	}

	public void setMemberLateGuestList(List memberLateGuestList) {
		this.memberLateGuestList = memberLateGuestList;
	}

	public Integer getUnRead() {
		return unRead;
	}

	public void setUnRead(Integer unRead) {
		this.unRead = unRead;
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

	public List<BlogArticleAccessMember> getBlogArticleAccessList() {
		return blogArticleAccessList;
	}

	public void setBlogArticleAccessList(List<BlogArticleAccessMember> blogArticleAccessList) {
		this.blogArticleAccessList = blogArticleAccessList;
	}

	public Integer getLogCount() {
		return logCount;
	}

	public void setLogCount(Integer logCount) {
		this.logCount = logCount;
	}

	public Integer getShowCount() {
		return showCount;
	}

	public void setShowCount(Integer showCount) {
		this.showCount = showCount;
	}

}
