package cn.mmbook.platform.model.base;

import javacommon.base.BaseEntity;
import javacommon.util.file.FileManagerService;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * <p> BaseAccessories 数据类 <br> 
 * <p>   <br>
 * @author admin,
 * @version 1.0. 2010-07-08
 *
 */

public class BaseAccessories extends BaseEntity {
	
	//date formats
	
	//columns START
	/**附件ID*/
	private java.lang.String id;
	/**网站附件分类ID*/
	private java.lang.Integer accessoriesTypeId;
	/**附属ID*/
	private java.lang.String subordinationId;
	/**内容ID*/
	private java.lang.String contentId;
	/**分类ID*/
	private java.lang.Integer sortId;
	/**附件URL*/
	private java.lang.String contentUrl;
	/**附件格式*/
	private java.lang.String format;
	/**附件文件大小*/
	private java.lang.Integer fileSize;
	/**附件说明*/
	private java.lang.String notes;
	/**文件后缀*/
	private java.lang.String fileSurfix;
	//columns END 
	/**文件保存时间*/
	private java.lang.String insertTime;

	/**附件内容字符串*/
	private java.lang.String content;
	
	public BaseAccessories(){
	}

	public BaseAccessories(
		java.lang.String id
	){
		this.id = id;
	}

	/**
	 * 文件附件URL读取文件内容
	 * @return
	 */
	public java.lang.String getContent() {
		String text = "";
		try{
			//System.out.println("getContent");
			if(null!=this.contentUrl&&contentUrl.length()>0){
				String fileurl = javacommon.util.io.ReadProperty.getInstance().getValue("FILEURL")+contentUrl;
				//System.out.println("^^^^"+fileurl);
				byte[] byte1 = FileManagerService.readFileToByte(fileurl);
				text = new String( byte1);
				//text = FileManagerService.readerFile(fileurl);
				//System.out.println("%%%"+text);
			}
			this.content = text;
			
			return content;
		}catch(Exception exe){
			System.out.println("读取附件TXT内容错误");
			exe.printStackTrace();
			return "";
		}
	}

	public void setContent(java.lang.String content) {
		this.content = content;
	}
	
	public void setId(java.lang.String value) {
		this.id = value;
	}
	
	public java.lang.String getId() {
		return this.id;
	}
	public void setAccessoriesTypeId(java.lang.Integer value) {
		this.accessoriesTypeId = value;
	}
	
	public java.lang.Integer getAccessoriesTypeId() {
		return this.accessoriesTypeId;
	}
	public void setSubordinationId(java.lang.String value) {
		this.subordinationId = value;
	}
	
	public java.lang.String getSubordinationId() {
		return this.subordinationId;
	}
	public void setContentId(java.lang.String value) {
		this.contentId = value;
	}
	
	public java.lang.String getContentId() {
		return this.contentId;
	}
	public void setSortId(java.lang.Integer value) {
		this.sortId = value;
	}
	
	public java.lang.Integer getSortId() {
		return this.sortId;
	}
	public void setContentUrl(java.lang.String value) {
		this.contentUrl = value;
	}
	
	public java.lang.String getContentUrl() {
		return this.contentUrl;
	}
	public void setFormat(java.lang.String value) {
		this.format = value;
	}
	
	public java.lang.String getFormat() {
		return this.format;
	}
	public void setFileSize(java.lang.Integer value) {
		this.fileSize = value;
	}
	
	public java.lang.Integer getFileSize() {
		return this.fileSize;
	}
	public void setNotes(java.lang.String value) {
		this.notes = value;
	}
	
	public java.lang.String getNotes() {
		return this.notes;
	}
	public void setFileSurfix(java.lang.String value) {
		this.fileSurfix = value;
	}
	
	public java.lang.String getFileSurfix() {
		return this.fileSurfix;
	}
	
	private BaseAccessoriesAtegory baseAccessoriesAtegory;
	
	public void setBaseAccessoriesAtegory(BaseAccessoriesAtegory baseAccessoriesAtegory){
		this.baseAccessoriesAtegory = baseAccessoriesAtegory;
	}
	
	public BaseAccessoriesAtegory getBaseAccessoriesAtegory() {
		return baseAccessoriesAtegory;
	}
	
	
	/** 数据类映射要在以下三个方法加入GET方法 toString() hashCode() equals(Object obj) */

	public String toString() {
		return new ToStringBuilder(this)
			.append("Id",getId())
			.append("AccessoriesTypeId",getAccessoriesTypeId())
			.append("SubordinationId",getSubordinationId())
			.append("ContentId",getContentId())
			.append("SortId",getSortId())
			.append("ContentUrl",getContentUrl())
			.append("Format",getFormat())
			.append("FileSize",getFileSize())
			.append("Notes",getNotes())
			.append("FileSurfix",getFileSurfix())
			.append("Content",getContent())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.append(getAccessoriesTypeId())
			.append(getSubordinationId())
			.append(getContentId())
			.append(getSortId())
			.append(getContentUrl())
			.append(getFormat())
			.append(getFileSize())
			.append(getNotes())
			.append(getFileSurfix())
			.append(getContent())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof BaseAccessories == false) return false;
		if(this == obj) return true;
		BaseAccessories other = (BaseAccessories)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.append(getAccessoriesTypeId(),other.getAccessoriesTypeId())
			.append(getSubordinationId(),other.getSubordinationId())
			.append(getContentId(),other.getContentId())
			.append(getSortId(),other.getSortId())
			.append(getContentUrl(),other.getContentUrl())
			.append(getFormat(),other.getFormat())
			.append(getFileSize(),other.getFileSize())
			.append(getNotes(),other.getNotes())
			.append(getFileSurfix(),other.getFileSurfix())
			.append(getContent(),other.getContent())
			.isEquals();
	}
}

