package cn.mmbook.platform.service.site.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javacommon.base.BaseManager;
import javacommon.base.EntityDao;
import javacommon.util.StringTool;
import javacommon.util.file.FileManagerService;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import cn.mmbook.platform.dao.base.BaseAccessoriesDao;
import cn.mmbook.platform.dao.base.BaseKeycontentRealDao;
import cn.mmbook.platform.dao.manage.SiteContentSortDao;
import cn.mmbook.platform.dao.site.SiteArticleDao;
import cn.mmbook.platform.dao.site.SiteContentDao;
import cn.mmbook.platform.model.base.BaseAccessories;
import cn.mmbook.platform.model.site.SiteArticle;
import cn.mmbook.platform.model.site.SiteContent;
import cn.mmbook.platform.service.site.SiteArticleManager;
import cn.org.rapid_framework.page.Page;
import cn.org.rapid_framework.page.PageRequest;
/**
 * <p> SiteArticle server类,实现功能,事物处理<br>
 * <p>   <br>
 * @author admin,
 * @version 1.0. 2010-07-08
 *
 */

@Component("siteArticleManager")
@Transactional
public class SiteArticleManagerImpl extends BaseManager<SiteArticle,java.lang.String> 
					implements SiteArticleManager {

	private SiteArticleDao siteArticleDao;
	private SiteContentDao siteContentDao;
	private SiteContentSortDao siteContentSortDao;
	private BaseKeycontentRealDao baseKeycontentRealDao;
	private BaseAccessoriesDao baseAccessoriesDao;
	/**增加setXXXX()方法,spring就可以通过autowire自动设置对象属性*/
	
	public void setSiteArticleDao(SiteArticleDao dao) {
		this.siteArticleDao = dao;
	}
	/**获取DAO*/
	public EntityDao getEntityDao() {
		return this.siteArticleDao;
	}
	public void setBaseKeycontentRealDao(BaseKeycontentRealDao dao) {
		this.baseKeycontentRealDao = dao;
	}	
	public void setSiteContentDao(SiteContentDao dao) {
		this.siteContentDao = dao;
	}
	public void setBaseAccessoriesDao(BaseAccessoriesDao dao) {
		this.baseAccessoriesDao = dao;
	}
	public void setSiteContentSortDao(SiteContentSortDao dao) {
		this.siteContentSortDao = dao;
	}	
	
	
	
	/**单表分页查询*/
	@Transactional(readOnly=true)
	public Page findByPageRequest(PageRequest pr) {
		return siteArticleDao.findByPageRequest(pr);
	}
	
	/**
	 * 保存 资讯内容
	 * 1：保存基础数据
	 * 2：保存资讯内容
	 * 3：保存关联字
	 * 4：生成内容文件
	 * 5：保存文件信息
	 */
	public void saveInfo(SiteArticle siteArticle){
		System.out.println("saveInfo");
		SiteContent siteContent = siteArticle.getSiteContent();
		/**转码处理 */
		siteContent.setTitle(StringTool.UtfToGbk(siteContent.getTitle()));
		siteContent.setTitleFull(StringTool.UtfToGbk(siteContent.getTitleFull()));
		siteContent.setSynopsis(StringTool.UtfToGbk(siteContent.getSynopsis()));
		siteContent.setSources(StringTool.UtfToGbk(siteContent.getSources()));
		siteContent.setAuthor(StringTool.UtfToGbk(siteContent.getAuthor()));
		siteArticle.setFileContent(StringTool.UtfToGbk(siteArticle.getFileContent()));
		String id =getMaxId("SiteContent.getMaxId");
		siteContent.setId(id);
		/**模型ID 网站内容展现模型(tb_tag_model_info)*/
		siteContent.setModelId("11");
		/**按内容类别ID查询它的顶级ID*/
		System.out.println("siteContent.getSortId() = "+siteContent.getSortId());
		if(null!=siteContent&&null!=siteContent.getSortId()){
			//SiteContentSort info = (SiteContentSort)siteContentSortDao.getById(siteContent.getSortId());
		     java.lang.Integer topid = siteContentSortDao.getTopId(siteContent.getSortId());
		     System.out.println(topid);
		     siteContent.setOneSortId(topid);   
		}
		siteArticle.setId(id);
		siteArticle.setContentId(id);
		siteContentDao.save(siteContent);
		siteArticleDao.save(siteArticle);
		
		/**关键字关联 处理  (关键字与内容关联表tb_base_keycontent_real)*/
		if(null!=siteContent.getKeywordsValue()){ 
			  baseKeycontentRealDao.saveMoer(siteContent.getKeywordsValue(),id);
		}
		/**生成内容文件*/
		String fileContent = siteArticle.getFileContent();
		String rootPath=javacommon.util.io.ReadProperty.getInstance().getValue("FILEURL");
		String filePath = "upfile/type/productId/";
		String filename = createFile(rootPath,filePath,fileContent);
		
		if(javacommon.util.StringTool.isNull(filename)){
			BaseAccessories baseAccessories = new BaseAccessories();
			
			/**附件ID*/
			baseAccessories.setId(FileManagerService.createFileName());
			/**网站附件分类ID **/
			/**10-图片URL,11-资讯文本,12-音乐文件,13-小说章节内容,14-软件文件,15-资讯的附件,*/
			baseAccessories.setAccessoriesTypeId(11);
			/**附属ID*/
			baseAccessories.setSubordinationId(siteArticle.getId());
			/**内容ID*/
			baseAccessories.setContentId(siteContent.getId());
			/**分类ID*/
			baseAccessories.setSortId(siteContent.getSortId());
			/**附件URL*/
			baseAccessories.setContentUrl(filename);
			/**附件格式*/
			baseAccessories.setFormat("txt");
			/**附件文件大小*/
			baseAccessories.setFileSize(11);
			/**附件说明*/
			baseAccessories.setNotes("资讯内容");
			/**文件后缀*/
			baseAccessories.setFileSurfix("txt");
			baseAccessoriesDao.save(baseAccessories);
		}
		
	}
	/**
	 * 修改 资讯内容
	 * 1：保存基础数据
	 * 2：保存资讯内容
	 * 3：保存关联字
	 * 4：生成内容文件
	 * 5：保存文件信息
	 * 	 
	void updateInfo(SiteArticle siteArticle,SiteContent siteContent,BaseAccessories baseAccessories);
	 */
	public void updateInfo(SiteArticle siteArticle,SiteContent siteContent,BaseAccessories baseAccessories){
		System.out.println("update");
		SiteContent siteContent_ojb = siteArticle.getSiteContent();
		if(null==siteContent_ojb){
			System.out.println(" ###siteContent is null ");
		}else{
			System.out.println(" ###siteContent is OK ");
		} 
		String id = siteArticle.getId();
		String contentId = siteArticle.getContentId();
		/**内容ID处理*/
		if(null!=siteContent_ojb&&javacommon.util.StringTool.isNull(siteContent_ojb.getId())==false){
			siteContent_ojb.setId(contentId);
		}
		System.out.println("#### id="+id+",contentId="+contentId); 
		/**模型ID 网站内容展现模型(tb_tag_model_info)*/
		siteContent_ojb.setModelId("11");
		/**按内容类别ID查询它的顶级ID*/
		System.out.println("siteContent.getSortId() = "+siteContent_ojb.getSortId());
		if(null!=siteContent_ojb&&null!=siteContent_ojb.getSortId()){
			 java.lang.Integer topid = siteContentSortDao.getTopId(siteContent_ojb.getSortId());
		     System.out.println(topid);
		     siteContent_ojb.setOneSortId(topid);
		}
 
		siteContentDao.update(siteContent_ojb);
		//siteArticleDao.save(siteArticle);
		
		/**关键字关联 处理  (关键字与内容关联表tb_base_keycontent_real)*/
		if(null!=siteContent_ojb.getKeywordsValue()){
			Map map  = new HashMap();
			map.put("contentId", contentId);
			int count = baseKeycontentRealDao.deleteByMap(map);
			baseKeycontentRealDao.saveMoer(siteContent_ojb.getKeywordsValue(),id);
		}
		/**删除之前的文件*/
		
		/**生成内容文件*/
		BaseAccessories baseAccessories_obj = (BaseAccessories)siteArticle.getBaseAccessories();
		BaseAccessories info = (BaseAccessories)baseAccessoriesDao.getById(baseAccessories_obj.getId());
		if(null!=baseAccessories_obj&&null!=info){
			String fileContent = baseAccessories_obj.getContent();
			/***/
			if(javacommon.util.StringTool.isNull(fileContent)==false){
				fileContent = baseAccessories.getContent();
			}
			String filename="";
			if(javacommon.util.StringTool.isNull(fileContent)){
				String rootPath=javacommon.util.io.ReadProperty.getInstance().getValue("FILEURL");
				String filePath = "upfile/type/productId/";
				filename = createFile(rootPath,filePath,fileContent);
			}
			if(javacommon.util.StringTool.isNull(filename)){
				/**附件URL*/
				info.setContentUrl(filename); 
				/**分类ID*/
				info.setSortId(siteContent_ojb.getSortId());
				baseAccessoriesDao.update(info);
			}
		}
	}

	/**
	 * 删除 资讯内容
	 * 1：删除基础数据
	 * 2：删除资讯内容
	 * 3：删除关联字
	 * 4：删除内容文件
	 * 5：删除文件信息
	 */
	public void removeById(String id){
		SiteArticle siteArticle = (SiteArticle)siteArticleDao.getById(id);
		String contentId = siteArticle.getContentId();
		/**删除基础数据*/
		siteContentDao.deleteById(contentId);
		/**删除资讯内容*/
		siteArticleDao.deleteById(id);
		/**删除 关键字关联 处理  (关键字与内容关联表tb_base_keycontent_real)*/
		if(null!=contentId){ 
			Map map  = new HashMap();
			map.put("contentId", contentId);
			int count = baseKeycontentRealDao.deleteByMap(map);
		}
		/**删除内容文件*/
		if(null!=contentId){ 
			Map map  = new HashMap();
			map.put("contentId", contentId);
			int count = baseAccessoriesDao.deleteByMap(map);
		}
	}
	
	/**
	 * 创建文件
	 * @param rootPath
	 * @param content
	 * @return filename String 生成的文件名和路径
	 */
	public String createFile(String rootPath,String filePath,String content){
		String filename = FileManagerService.createFileName();
		String fileUrl = rootPath+filePath+filename+".txt";
		System.out.println(fileUrl);
		FileManagerService.mkDirectory(rootPath);// 创建文件夹
		try{
			//System.out.println("content;"+content);
		    FileManagerService.WriteFile(fileUrl, content, true);
		    filename = filePath+filename+".txt";
		    return filename;
		}catch(Exception exe){
			exe.printStackTrace();
			return "";
		}
	}
	public List getList(SiteArticle u){
		return siteArticleDao.getList(u);
	}
	/**多表分页查询*/
	@Transactional(readOnly=true)
	public Page listPageAnyTable(PageRequest pr) {
		return siteArticleDao.listPageAnyTable(pr);
	}
	
	/**
	 * 资讯内容查询 
	 * @param contentId 内容ID
	 * @return SiteArticle
	 */
	public SiteArticle getSiteArticleInfo(String contentId){
		List list = siteArticleDao.getSiteArticleInfo(contentId);
		return list.size()>0?(SiteArticle)list.get(0):new SiteArticle();
	}
}
