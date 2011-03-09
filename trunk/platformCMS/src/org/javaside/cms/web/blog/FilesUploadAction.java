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

import java.io.File;

import net.fckeditor.handlers.PropertiesLoader;

import org.apache.commons.io.FileUtils;
import org.javaside.cms.core.SpringSecurityUtils;
import org.javaside.cms.core.Struts2Utils;
import org.javaside.cms.entity.Member;
import org.javaside.cms.service.MemberManager;
import org.javaside.cms.util.FileRule;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

/**
 * Show case File Upload example's action. <code>FileUploadAction</code>
 */
public class FilesUploadAction extends ActionSupport implements Preparable {

	private static final long serialVersionUID = 5156288255337069381L;

	private Member member;

	@Autowired
	private MemberManager memberManager;

	/*******多图片上传*********/
	private File[] uploads;
	private String[] uploadFileNames;
	private String[] uploadContentTypes;
	private String fileNames = "";

	public File[] getUpload() {
		return this.uploads;
	}

	public void setUpload(File[] upload) {
		this.uploads = upload;
	}

	public String[] getUploadFileName() {
		return this.uploadFileNames;
	}

	public void setUploadFileName(String[] uploadFileName) {
		this.uploadFileNames = uploadFileName;
	}

	public String[] getUploadContentType() {
		return this.uploadContentTypes;
	}

	public void setUploadContentType(String[] uploadContentType) {
		this.uploadContentTypes = uploadContentType;
	}

	public String getFileNames() {
		return fileNames;
	}

	public void setFileNames(String fileNames) {
		this.fileNames = fileNames;
	}

	public String upload() throws Exception {
		String userPath = PropertiesLoader.getProperty("connector.userBaseFilePath") + member.getId();

		System.out.println("\n\n upload2");
		System.out.println("files:");
		int index = 0;
		for (File u : uploads) {
			String fileName = uploadFileNames[index];
			fileName = FileRule.genFilePath() + FileRule.genFileName()+fileName;
			String filePath = Struts2Utils.getSession().getServletContext().getRealPath(FileRule.fileRoot)
					+ FileRule.SPT + userPath.replace("/UploadImages/", "") +fileName;
			File toSave = new File(filePath);
			FileUtils.copyFile(u, toSave);
			fileNames = fileNames+userPath+fileName+",";
			index++;
		}
		fileNames = fileNames.replace("\\", "/");
		System.out.println("filenames:");
		for (String n : uploadFileNames) {
			System.out.println("*** " + n);
		}
		System.out.println("content types:");
		for (String c : uploadContentTypes) {
			System.out.println("*** " + c);
		}
		System.out.println("\n\n");
		return SUCCESS;
	}

	public void prepare() throws Exception {
		member = memberManager.getMemberByLoginName(SpringSecurityUtils.getCurrentUserName());
	}
}
