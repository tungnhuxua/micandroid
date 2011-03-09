 /*
 * Light Portal
 *
 * Copyright (c) 2009, Light Portal, Inc or third-party contributors as
 * indicated by the @author tags or express copyright attribution
 * statements applied by the authors.  All third-party contributions are
 * distributed under license by Light Portal, Inc.
 *
 * This copyrighted material is made available to anyone wishing to use, modify,
 * copy, or redistribute it subject to the terms and conditions of the GNU
 * Lesser General Public License, as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Lesser General Public License
 * for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this distribution; if not, write to:
 * Free Software Foundation, Inc.
 * 51 Franklin Street, Fifth Floor
 * Boston, MA  02110-1301  USA
 *
 */

package org.light.portal.search.model;

import org.light.portal.util.ImageUtil;

/**
 * 
 * @author Jianmin Liu
 **/
public class SearchResultItem {
	private String id;
	private String name;
	private String uri;
	private String photoUrl;
	private String photoWidth;
	private String photoHeight;
	private String date;
	private int number;
	private String subject;
	private String summary;
	private String content;
	private String link;
	private String signature;
	private String type;
	private String listPrice;
	private String price;
	private String discount;
	private String expireDate;
	private boolean showDetail;
	private boolean userData = true;
	
	public SearchResultItem(){
		
	}
	
	public SearchResultItem(int number, String id, String name, String uri, String photoUrl, String date){
		this.number = number;
		this.id = id;
		this.name = name;
		this.uri = uri;
		this.photoUrl = photoUrl;
		this.date = date;
	}
	public SearchResultItem(int number, String id, String name, String uri, String photoUrl, String date, String content, String link, String type){
		this(number,id,name,uri,photoUrl,date);
		this.content = content;
		this.link = link;
		this.type = type;
		this.showDetail = true;
	}
	public SearchResultItem(int number, String userId, String name, String uri, String photoUrl, String width, String height, String date, String content, String link, String signature, String listPrice,String price, String discount,String expireDate, String type, boolean userData){
		this(number,userId,name,uri,photoUrl,date,content,link,type);	
		this.photoWidth = width;
		this.photoHeight = height;
		this.signature = signature;
		this.listPrice = listPrice;
		this.price = price;
		this.discount = discount;
		this.expireDate = expireDate;
		this.userData = userData;
	}
	public SearchResultItem(int number, String userId, String name, String uri, String photoUrl, String date, String subject, String summary, String content, String link, String type){
		this(number,userId,name,uri,photoUrl,date);
		this.subject = subject;
		this.summary = summary;
		this.content = content;
		this.link = link;
		this.type = type;
		this.showDetail = true;
	}
	
	public String getShortName(){
		return (this.name.length() <= 60) ? this.name : this.name.substring(0,60)+"...";
	}
	
	public String getDetail(){
		String desc = (subject == null) ? "" : subject;
		desc+= (summary == null) ? "" : "<br/>"+summary;
		desc+= (content == null) ? "" : "<br/>"+content;
		if(desc.length() > 200) desc=desc.substring(0,200)+"...";
		return desc;
	}
	
	public String getImage(){
		return this.photoUrl;
	}
	
	public int getImageWidth(){
		int width=0;
		try{
			width = Integer.parseInt(photoWidth);
		}catch(Exception e){}
		return width;
	}
	
	public int getImageHeight(){
		int height=0;
		try{
			height = Integer.parseInt(photoHeight);
		}catch(Exception e){}
		return height;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isHttpPhotoUrl(){
		return photoUrl.toLowerCase().startsWith("http");
	}
	public String getPhotoUrl(){
		return ImageUtil.getPhotoUrl(this.photoUrl);
	}
		
	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}
	
	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public boolean isShowDetail() {
		return showDetail;
	}
	public void setShowDetail(boolean showDetail) {
		this.showDetail = showDetail;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public boolean isUserData() {
		return userData;
	}
	public String getPhotoHeight() {
		return photoHeight;
	}
	public void setPhotoHeight(String photoHeight) {
		this.photoHeight = photoHeight;
	}
	public String getPhotoWidth() {
		return photoWidth;
	}
	public void setPhotoWidth(String photoWidth) {
		this.photoWidth = photoWidth;
	}
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
	public String getDiscount() {
		return discount;
	}
	public void setDiscount(String discount) {
		this.discount = discount;
	}
	public String getListPrice() {
		return listPrice;
	}
	public void setListPrice(String listPrice) {
		this.listPrice = listPrice;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getExpireDate() {
		return expireDate;
	}
	public void setExpireDate(String expireDate) {
		this.expireDate = expireDate;
	}
	
}