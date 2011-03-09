/*
 * $Id: FileUploadAction.java 476710 2006-11-19 05:05:14Z mrdon $
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.javaside.cms.web.blog;

import java.awt.Rectangle;
import java.io.File;

import org.apache.commons.io.FileUtils;
import org.javaside.cms.core.SpringSecurityUtils;
import org.javaside.cms.core.Struts2Utils;
import org.javaside.cms.entity.Article;
import org.javaside.cms.entity.Member;
import org.javaside.cms.entity.MemberInfo;
import org.javaside.cms.service.MemberInfoManager;
import org.javaside.cms.service.MemberManager;
import org.javaside.cms.util.FileRule;
import org.javaside.cms.util.ImageHepler;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

/**
 * Show case File Upload example's action. <code>FileUploadAction</code>
 */
public class UploadAction extends ActionSupport implements Preparable{

	private static final long serialVersionUID = 5156288255337069381L;

	private MemberInfo entity;
	private Article article;
	private Member member;
	@Autowired
	private MemberInfoManager infoManager;
	@Autowired
	private MemberManager memberManager;

	private String contentType;
	private File upload;
	private String fileName;
	private String caption;
	private String fileGroup = "other"; //文件分组，article 表示文章相关文件，ads表示广告相关文件
	private String fileFormat; //文件格式
	private Long fileSize; //文件大小

	private String userCapDir; //用户头像目录
	private String txt_left; //图片x坐标
	private String txt_top; //图片y坐标
	private String txt_width; //图片宽度
	private String txt_height;//图片高度
	private String txt_DropWidth;//剪裁宽度
	private String txt_DropHeight;//剪裁高度
	private String memberid; //临时存储变量
	private Long id; //临时存储变量

	private String action = "memberpicture"; //标识action

	// since we are using <s:file name="upload" .../> the file name will be
	// obtained through getter/setter of <file-tag-name>FileName
	public String getUploadFileName() {
		return fileName;
	}

	public void setUploadFileName(String fileName) {
		this.fileName = fileName;
	}

	// since we are using <s:file name="upload" ... /> the content type will be
	// obtained through getter/setter of <file-tag-name>ContentType
	public String getUploadContentType() {
		return contentType;
	}

	public void setUploadContentType(String contentType) {
		this.contentType = contentType;
	}

	// since we are using <s:file name="upload" ... /> the File itself will be
	// obtained through getter/setter of <file-tag-name>
	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	public String getFileGroup() {
		return fileGroup;
	}

	public void setFileGroup(String fileGroup) {
		this.fileGroup = fileGroup;
	}

	public String getFileFormat() {
		return fileFormat;
	}

	public void setFileFormat(String fileFormat) {
		this.fileFormat = fileFormat;
	}

	public Long getFileSize() {
		return fileSize;
	}

	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}

	@Override
	public String input() throws Exception {
		return SUCCESS;
	}

	public String getUserCapDir() {
		return userCapDir;
	}

	public void setUserCapDir(String userCapDir) {
		this.userCapDir = userCapDir;
	}

	public String getMemberid() {
		return memberid;
	}

	public void setMemberid(String memberid) {
		this.memberid = memberid;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public String getTxt_left() {
		return txt_left;
	}

	public void setTxt_left(String txt_left) {
		this.txt_left = txt_left;
	}

	public String getTxt_top() {
		return txt_top;
	}

	public void setTxt_top(String txt_top) {
		this.txt_top = txt_top;
	}

	public String getTxt_width() {
		return txt_width;
	}

	public void setTxt_width(String txt_width) {
		this.txt_width = txt_width;
	}

	public String getTxt_height() {
		return txt_height;
	}

	public void setTxt_height(String txt_height) {
		this.txt_height = txt_height;
	}

	public String getTxt_DropWidth() {
		return txt_DropWidth;
	}

	public void setTxt_DropWidth(String txt_DropWidth) {
		this.txt_DropWidth = txt_DropWidth;
	}

	public String getTxt_DropHeight() {
		return txt_DropHeight;
	}

	public void setTxt_DropHeight(String txt_DropHeight) {
		this.txt_DropHeight = txt_DropHeight;
	}

	public MemberInfo getEntity() {
		return entity;
	}

	public void setEntity(MemberInfo entity) {
		this.entity = entity;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	/**
	 * 用户头像上传
	 * 
	 * @return
	 * @throws Exception
	 */
	public String uploadcap() throws Exception {
		fileName = "\\" + userCapDir + "\\" + FileRule.genFileName()
				+ fileName.substring(fileName.lastIndexOf("."), fileName.length());
		String filePath = Struts2Utils.getSession().getServletContext().getRealPath(FileRule.fileRoot) + FileRule.SPT
				+ fileGroup + fileName;
		File toSave = new File(filePath);
		FileUtils.copyFile(upload, toSave);
		fileSize = upload.length();
		fileName = (FileRule.fileRoot + FileRule.SPT + fileGroup + fileName).replace("\\", "/");
		fileFormat = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
		memberid = userCapDir;
		return "cap";
	}

	/**
	 *用户头像剪裁
	 * 
	 * @return
	 * @throws Exception
	 */
	public String uploadCapCut() throws Exception {
		String path = Struts2Utils.getSession().getServletContext().getRealPath("/");
		String filePath1 = path + entity.getHeadPortraitUri();
		String filepath2 = FileRule.fileRoot
				+ FileRule.SPT
				+ fileGroup
				+ FileRule.SPT
				+ entity.getMember().getId()
				+ FileRule.SPT
				+ FileRule.genFileName()
				+ entity.getHeadPortraitUri().substring(entity.getHeadPortraitUri().lastIndexOf("."),
						entity.getHeadPortraitUri().length());
		File file1 = new File(filePath1); //剪裁的图片地址
		File file2 = new File(path + filepath2);//剪裁后的图片地址
		Rectangle rec = new Rectangle(Integer.valueOf(this.txt_left), Integer.valueOf(this.txt_top), Integer.valueOf(this.txt_DropWidth), Integer
				.valueOf(this.txt_DropHeight));
		ImageHepler.cut(file1, file2, Integer.valueOf(this.txt_width), Integer.valueOf(this.txt_height), rec);//实现剪裁功能
		MemberInfo info = infoManager.get(entity.getId());
		filepath2 = filepath2.replace("\\", "/");
		info.setHeadPortraitUri(filepath2);
		infoManager.save(info);//保存头像对应数据表关联
		entity.setHeadPortraitUri(info.getHeadPortraitUri()); //把剪裁头像地址设在entity值里面
		file1.delete(); //删除模版图片，以节约空间。
		return "picture";
	}

	/**
	 *用户日志图片剪裁
	 * 
	 * @return
	 * @throws Exception
	 */
	public String UserLogImgCut() throws Exception {
		if (member == null) return "home";
		String path = Struts2Utils.getSession().getServletContext().getRealPath("/");
		String filePath1 = path + article.getImageLink();
		String filepath2 = FileRule.fileRoot
				+ FileRule.SPT
				+ fileGroup
				+ FileRule.SPT
				+ member.getId()
				+ FileRule.SPT
				+ FileRule.genFileName()
				+ article.getImageLink().substring(article.getImageLink().lastIndexOf("."),
						article.getImageLink().length());
		File file1 = new File(filePath1); //剪裁的图片地址
		File file2 = new File(path + filepath2);//剪裁后的图片地址
		Rectangle rec = new Rectangle(Integer.valueOf(this.txt_left), Integer.valueOf(this.txt_top), Integer.valueOf(this.txt_DropWidth), Integer
				.valueOf(this.txt_DropHeight));
		ImageHepler.cut(file1, file2, Integer.valueOf(this.txt_width), Integer.valueOf(this.txt_height), rec);//实现剪裁功能
		filepath2 = filepath2.replace("\\", "/");
		article.setImageLink(filepath2); //把剪裁头像地址设在entity值里面
//		file1.delete(); //删除模版图片，以节约空间。
		return "userLogImgCut";
	}
	
	/**
	 * 上传圈子图片
	 * 
	 * @return
	 * @throws Exception
	 */
	public String uploadCircle() throws Exception {
		fileName = FileRule.genFilePath() + FileRule.genFileName()
				+ fileName.substring(fileName.lastIndexOf("."), fileName.length());
		String filePath = Struts2Utils.getSession().getServletContext().getRealPath(FileRule.fileRoot) + FileRule.SPT
				+ fileGroup + fileName;
		File toSave = new File(filePath);
		FileUtils.copyFile(upload, toSave);
		fileSize = upload.length();
		fileName = (FileRule.fileRoot + FileRule.SPT + fileGroup + fileName).replace("\\", "/");
		fileFormat = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
		return "circle";
	}
	
	/**
	 * 用户上传封面图片
	 * 
	 * @return
	 * @throws Exception
	 */
	public String uploadCover() throws Exception {
		fileName = "\\" + userCapDir +"\\images"+ "\\" + FileRule.genFileName()
				+ fileName.substring(fileName.lastIndexOf("."), fileName.length());
		String filePath = Struts2Utils.getSession().getServletContext().getRealPath(FileRule.fileRoot) + FileRule.SPT
				+ fileGroup + fileName;
		File toSave = new File(filePath);
		FileUtils.copyFile(upload, toSave);
		fileSize = upload.length();
		fileName = (FileRule.fileRoot + FileRule.SPT + fileGroup + fileName).replace("\\", "/");
		fileFormat = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
		return "cover";
	}

	/**
	 *用户封面图片剪裁
	 * 
	 * @return
	 * @throws Exception
	 */
	public String UserCoverImgCut() throws Exception {
		if (member == null) return "home";
		String path = Struts2Utils.getSession().getServletContext().getRealPath("/");
		String filePath1 = path + entity.getCoverImg();
		String filepath2 = FileRule.fileRoot
				+ FileRule.SPT
				+ fileGroup
				+ FileRule.SPT
				+ member.getId()
				+ FileRule.SPT
				+"images"
				+ FileRule.SPT
				+ FileRule.genFileName()
				+ entity.getCoverImg().substring(entity.getCoverImg().lastIndexOf("."),
						entity.getCoverImg().length());
		File file1 = new File(filePath1); //剪裁的图片地址
		File file2 = new File(path + filepath2);//剪裁后的图片地址
		Rectangle rec = new Rectangle(Integer.valueOf(this.txt_left), Integer.valueOf(this.txt_top), Integer.valueOf(this.txt_DropWidth), Integer
				.valueOf(this.txt_DropHeight));
		ImageHepler.cut(file1, file2, Integer.valueOf(this.txt_width), Integer.valueOf(this.txt_height), rec);//实现剪裁功能
		filepath2 = filepath2.replace("\\", "/");
		entity.setCoverImg(filepath2); //把剪裁头像地址设在entity值里面
//		file1.delete(); //删除模版图片，以节约空间。
		return "userCoverImgCut";
	}
	public void prepare() throws Exception {
		member = memberManager.getMemberByLoginName(SpringSecurityUtils.getCurrentUserName());
	}
}
