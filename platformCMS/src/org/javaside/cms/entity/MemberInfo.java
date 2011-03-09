package org.javaside.cms.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 个人档案
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "member_info")
public class MemberInfo implements java.io.Serializable {

	private Long id;
	private Member member; //会员
	private String realName; //真实姓名
	private Character gender; //性别,男：1，女：2
	private Date birthday; //出生日期
	private Character birthdayHiden; //是否显示出生日期,1隐藏,2显示
	private String liveProvince; //现居住省份
	private String liveCity; //现居住城市
	private String liveCounty; //现居住县城
	private String hometown; //家乡省份
	private String homeCity; //家乡城市
	private String homeCounty; //家乡县城
	private Character marital; //婚姻状况,已婚:1,未婚:2
	private Character maritalHiden; //是否显示婚姻状况,1隐藏,2显示
	private String contacts; //联系方式
	private Character contactsHiden; //是否显示联系方式,1隐藏,2显示
	private String qq;
	private Character qqHiden; //是否显示QQ,1隐藏,2显示
	private String msn;
	private Character msnHiden; //是否显示msn,1隐藏,2显示
	private String address; //联系地址
	private Character addressHiden; //是否显示联系地址,1隐藏,2显示
	private String postalcode; //邮编
	private String website; //个人网站		
	private String education; //教育背景
	private Character educationHiden; //是否显示教育背景,1隐藏,2显示
	private String workInfo; //工作信息
	private Character workInfoHiden; //是否显示工作信息,1隐藏,2显示
	private String introduction; //个人简介
	private String headPortraitUri; //头像地址
	private String notice; //个人公告
	private String cardcode;
	private String tel;
	private Long mark = 0l;
	private Integer accessing = 0; //用户总访问量
	private String domain; //个性域名
	private String coverImg; //封面图片地址
	private Integer coverSetup; //封面设置  0：无封面  1：居左 2：居中  3：居右
	private String workId; //对应职业的id;
	private String workName;//职业名称

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@OneToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinColumn(name = "member_id")
	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	@Column(name = "real_name", length = 50)
	public String getRealName() {
		return this.realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	@Column(name = "gender", length = 1)
	public Character getGender() {
		return this.gender;
	}

	public void setGender(Character gender) {
		this.gender = gender;
	}

	@Column(name = "live_city", length = 200)
	public String getLiveCity() {
		return this.liveCity;
	}

	public void setLiveCity(String liveCity) {
		this.liveCity = liveCity;
	}

	@Column(name = "hometown", length = 200)
	public String getHometown() {
		return this.hometown;
	}

	public void setHometown(String hometown) {
		this.hometown = hometown;
	}

	@Column(name = "marital", length = 1)
	public Character getMarital() {
		return this.marital;
	}

	public void setMarital(Character marital) {
		this.marital = marital;
	}

	@Column(name = "contacts", length = 200)
	public String getContacts() {
		return this.contacts;
	}

	public void setContacts(String contacts) {
		this.contacts = contacts;
	}

	@Column(name = "qq", length = 15)
	public String getQq() {
		return this.qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	@Column(name = "msn", length = 100)
	public String getMsn() {
		return this.msn;
	}

	public void setMsn(String msn) {
		this.msn = msn;
	}

	@Column(name = "address", length = 200)
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "postalcode", length = 6)
	public String getPostalcode() {
		return this.postalcode;
	}

	public void setPostalcode(String postalcode) {
		this.postalcode = postalcode;
	}

	@Column(name = "website", length = 100)
	public String getWebsite() {
		if (this.website != null && !"".equals(this.website)) {
			String regEx = "http://";
			Pattern p = Pattern.compile(regEx, Pattern.CASE_INSENSITIVE);

			Matcher m = p.matcher(this.website);
			if (!m.find()) {
				this.website = "http://" + this.website;
			}
		}
		return this.website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	@Column(name = "education", length = 50)
	public String getEducation() {
		return this.education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	@Column(name = "work_info", length = 200)
	public String getWorkInfo() {
		return this.workInfo;
	}

	public void setWorkInfo(String workInfo) {
		this.workInfo = workInfo;
	}

	@Column(name = "introduction", length = 1000)
	public String getIntroduction() {
		if (this.introduction != null) {
			this.introduction.replace("\r\n", "<br />");
			this.introduction.replace("\n", "<br />");
		}
		return this.introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	@Column(name = "head_portrait_uri", length = 100)
	public String getHeadPortraitUri() {
		return this.headPortraitUri;
	}

	public void setHeadPortraitUri(String headPortraitUri) {
		this.headPortraitUri = headPortraitUri;
	}

	@Column(name = "notice", length = 500)
	public String getNotice() {
		return this.notice;
	}

	public void setNotice(String notice) {
		this.notice = notice;
	}

	public String getCardcode() {
		return cardcode;
	}

	public void setCardcode(String cardcode) {
		this.cardcode = cardcode;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getLiveProvince() {
		return liveProvince;
	}

	public void setLiveProvince(String liveProvince) {
		this.liveProvince = liveProvince;
	}

	public String getLiveCounty() {
		return liveCounty;
	}

	public void setLiveCounty(String liveCounty) {
		this.liveCounty = liveCounty;
	}

	public String getHomeCity() {
		return homeCity;
	}

	public void setHomeCity(String homeCity) {
		this.homeCity = homeCity;
	}

	public String getHomeCounty() {
		return homeCounty;
	}

	public void setHomeCounty(String homeCounty) {
		this.homeCounty = homeCounty;
	}

	@Temporal(TemporalType.DATE)
	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Character getBirthdayHiden() {
		return birthdayHiden;
	}

	public void setBirthdayHiden(Character birthdayHiden) {
		this.birthdayHiden = birthdayHiden;
	}

	public Character getMaritalHiden() {
		return maritalHiden;
	}

	public void setMaritalHiden(Character maritalHiden) {
		this.maritalHiden = maritalHiden;
	}

	public Character getContactsHiden() {
		return contactsHiden;
	}

	public void setContactsHiden(Character contactsHiden) {
		this.contactsHiden = contactsHiden;
	}

	public Character getQqHiden() {
		return qqHiden;
	}

	public void setQqHiden(Character qqHiden) {
		this.qqHiden = qqHiden;
	}

	public Character getMsnHiden() {
		return msnHiden;
	}

	public void setMsnHiden(Character msnHiden) {
		this.msnHiden = msnHiden;
	}

	public Character getAddressHiden() {
		return addressHiden;
	}

	public void setAddressHiden(Character addressHiden) {
		this.addressHiden = addressHiden;
	}

	public Character getEducationHiden() {
		return educationHiden;
	}

	public void setEducationHiden(Character educationHiden) {
		this.educationHiden = educationHiden;
	}

	public Character getWorkInfoHiden() {
		return workInfoHiden;
	}

	public void setWorkInfoHiden(Character workInfoHiden) {
		this.workInfoHiden = workInfoHiden;
	}

	public Long getMark() {
		return mark;
	}

	public void setMark(Long mark) {
		this.mark = mark;
	}

	public Integer getAccessing() {
		return accessing;
	}

	public void setAccessing(Integer accessing) {
		this.accessing = accessing;
	}

	@Column(unique = true)
	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getCoverImg() {
		return coverImg;
	}

	public void setCoverImg(String coverImg) {
		this.coverImg = coverImg;
	}

	public Integer getCoverSetup() {
		return coverSetup;
	}

	public void setCoverSetup(Integer coverSetup) {
		this.coverSetup = coverSetup;
	}

	public String getWorkId() {
		return workId;
	}

	public void setWorkId(String workId) {
		this.workId = workId;
	}

	public String getWorkName() {
		return workName;
	}

	public void setWorkName(String workName) {
		this.workName = workName;
	}

}
