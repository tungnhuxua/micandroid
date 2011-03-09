package cn.mmbook.platform.facade;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import cn.mmbook.platform.model.manage.*;
import cn.mmbook.platform.model.site.*;
import cn.mmbook.platform.service.manage.*;
import cn.mmbook.platform.service.site.SiteArticleManager;
import cn.mmbook.platform.service.site.SiteContentManager;
import cn.mmbook.platform.service.site.SiteMusicManager;
import cn.mmbook.platform.service.site.SiteNovelNoteManager;
import cn.mmbook.platform.service.site.SitePicNoteManager;
import cn.mmbook.platform.service.site.*;
import cn.org.rapid_framework.page.Page;



/**
 * 标签门面类
 * @author arye"灬爪哇'  luyi_arye@qq.com(527746395)
 *
 */

@Component("tagService")
@Transactional
public class TagService {

	
	private SiteChannelsManager siteChannelsManager;
	private SitePartManager sitePartManager;
	private SiteContentSortManager siteContentSortManager;
	private SitePicNoteManager sitePicNoteManager;
	private SiteNovelNoteManager siteNovelNoteManager;
	private SiteMusicManager siteMusicManager;
	private SiteArticleManager siteArticleManager;
	private SiteContentManager siteContentManager;
	
	/**增加setXXXX()方法,spring就可以通过autowire自动设置对象属性*/
	public void setSitePicNoteManager(SitePicNoteManager sitePicNoteManager) {
		this.sitePicNoteManager = sitePicNoteManager;
	}
	public void setSiteChannelsManager(SiteChannelsManager manager) {
		this.siteChannelsManager = manager;
	}
	public void setSitePartManager(SitePartManager manager) {
		this.sitePartManager = manager;
	}
	public void setSiteContentSortManager(SiteContentSortManager manager) {
		this.siteContentSortManager = manager;
	}	
	public void setSiteNovelNoteManager(SiteNovelNoteManager siteNovelNoteManager) {
		this.siteNovelNoteManager = siteNovelNoteManager;
	}
	public void setSiteArticleManager(SiteArticleManager siteArticleManager) {
		this.siteArticleManager = siteArticleManager;
	}
	public void setSiteContentManager(SiteContentManager siteContentManager) {
		this.siteContentManager = siteContentManager;
	}
	public void setSiteMusicManager(SiteMusicManager siteMusicManager) {
		this.siteMusicManager = siteMusicManager;
	}
	
	/*********** 频道 处理************/
	/**
	 * 按网站ID 查询 频道列表数据
	 * @param map
	 *   String sort 1: 按ID排序 2,按排序值(sortValue),3 随机
	 *   String siteId 网站ID
	 *   String start 当前页码
	 *   String count 返回记录数 (0 不分页取所有记录)
	 * @return Map 
	 *    String size 符合条件记录总数
	 *    List<SiteChannels> 结果集
	 */
	public Page getSiteChannelList(Map map){
		return siteChannelsManager.getSiteChannelList(map);
	}
	/**
	 * 查询单个 网站频道数据
	 * @param siteChannelId 频道ID
	 * @return SiteChannels 频道数据对象
	 */
	public SiteChannels getSiteChannelInfo(Integer siteChannelId){
		return siteChannelsManager.getById(siteChannelId);
	}


	/*********** 栏目 处理************/
	/**
	 * 查询 网站对应的 所有频道及 频道对应的一级栏目
	 * @param map
	 *   String siteId 网站ID
	 * @return Map 
	 *    String size 符合条件记录总数
	 *    List<SiteChannels> 结果集
	 */
	public List getAllChannelPart(Map map){
		return siteChannelsManager.getAllChannelPart(map);
	}
	
	/**
	 * 按频道ID查询 一级栏目列表数据
	 * @param map
	 *   String sort 1: 按ID排序 2,按排序值(sortValue),3 随机
	 *   String siteChannelId 频道ID
	 *   String page 1 分页 2 不分页
	 *   String start 当前页码
	 *   String count 返回记录数
	 * @return Map 
	 *    String size 符合条件记录总数
	 *    List<SitePart> 结果集
	 */
	public Page getSitePartList(Map map){
		return sitePartManager.getSitePartList(map);
	}
	/**
	 * 按栏目ID查询 下级栏目
	 * @param map
	 *   String sort 排序字符串
	 *   String sitePartId 栏目ID
	 *   String start 当前页码
	 *   String count 返回记录数 (0 不分页取所有记录)
	 * @return Page 
	 *    List<SitePart> 结果集
	 */
	public List getSitePartListById(Map map){
		return sitePartManager.getSitePartListById(map);
	}
	/**
	 * 按栏目ID查询栏目
	 * @param sitePartId 栏目ID
	 * @return
	 */
	public SitePart getSitePartById(String sitePartId){
		return sitePartManager.getSitePartById(Integer.parseInt(sitePartId));
	}
		
	/*********** 网站内容分类 处理************/
	
	/**
	 * 按未级栏目ID查询一级网站分类列表(返回网站分类表数据信息)
	 * ### 标准写法 ####
	 * @param map
	 *   String sort 排序字符串
	 *   String sitePartId 频道ID
	 *   String start 当前页码
	 *   String count 返回记录数 (0 不分页取所有记录)
	 * @return Page 
	 *    List<SiteContentSort> 结果集
	 */
	public	Page getSoftByPartId(Map map){
		return siteContentSortManager.getSoftByPartId(map);
	}


	/**
	 * 按网站分类ID查询它所有的子类(下级)信息列表(返回网站分类表数据信息)
	 * @param map
	 *   String sort 排序字符串
	 *   String sortId 网站分类ID
	 *   String start 当前页码
	 *   String count 返回记录数 (0 不分页取所有记录)
	 * @return Page 
	 *    List<SiteContentSort> 结果集
	 */
	public List getSortBySortID(Map map){
		return siteContentSortManager.getSortBySortID(map);
	}
	/**
	 * 按网站内容分类ID 查询 分类信息
	 * @param SortId 网站内容分类ID
	 * @return
	 */
	public SiteContentSort getSiteContentSortById(String sortId){
		return siteContentSortManager.getSiteContentSortById(sortId);
	}
	
	/** 2010-09-08
	 * 搜索内容 (搜索 标题，长标题，简介)
	 * @param map 
	 *   String sreach 搜索内容 
	 *   String start 当前页码
	 *   String count 返回记录数 (0 不分页取所有记录)
	 * @return Page 
	 *    List<SiteContentSort> 结果集
	 */
	public List sreachSiteContent(Map map){
		return siteContentManager.sreachSiteContent(map);
	}
	
	/*********** 内容 处理************/
	/**
	 * 按未级网站分类ID查询  内容列表(返回网站分类表数据信息)
	 * @param map
	 *   String sort 排序字符串
	 *   String SortId 网站分类ID
	 *   String start 当前页码 (0 不分页取所有记录)
	 *   String count 返回记录数
	 * @return Page 
	 *    List<SiteContent> 结果集
	 */
	public Page getSiteContentBySortId(Map map){
		return siteContentManager.getListByMap(map);
	}
	/**
	 * 按分类ID查询它及它下级对应的所有  内容列表(返回网站分类表数据信息)
	 * @param map
	 *   String sort 排序字符串
	 *   String SortId 网站分类ID
	 *   String start 当前页码 (0 不分页取所有记录)
	 *   String count 返回记录数
	 * @return Page 
	 *    List<SiteContent> 结果集
	 */
	public Page getSiteContentAllList(Map map){
		return siteContentManager.getListByMap(map);
	}
	/**
	 * 按内容ID查询内容主表信息
	 * @param contentId
	 * @return
	 */
	public SiteContent getSiteContentById(String contentId){
		return siteContentManager.getSiteContentById(contentId);
	}

	/*********** 内容详细 处理************/
	/**
	 * 资讯内容查询 
	 * @param contentId 内容ID
	 * @return SiteArticle
	 */
	public SiteArticle getSiteArticleInfo(String contentId){
		return siteArticleManager.getSiteArticleInfo(contentId);
	}
	/**
	 * 音乐内容查询 
	 * @param contentId 内容ID
	 * @return SiteMusic
	 */
	public SiteMusic getSiteMusicInfo(String contentId){
		return siteMusicManager.getSiteMusicInfo(contentId);
	}
	/**
	 * 小说及简介查询 
	 * @param contentId 内容ID
	 * @return SiteNovelNote
	 */
	public SiteNovelNote getSiteNovelNoteInfo(String contentId){
		return siteNovelNoteManager.getSiteNovelNoteInfo(contentId);
	}
	/**
	 * 图片查询 
	 * @param contentId 内容ID
	 * @return SitePicNote
	 */
	public SitePicNote getSitePicNoteInfo(String contentId){
		return sitePicNoteManager.getSitePicNoteInfo(contentId);
	}
	//
}
