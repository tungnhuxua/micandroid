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

package org.light.portal.model;

/**
 * 
 * @author Jianmin Liu
 **/
public class UserProfile  extends Entity{
	private long userId;
	private String firstName;
	private String middleName;
	private String lastName;
	private String occupation;
	private int ethnicity;
	private int bodyType;
	private int height;
	private int registerPurpose;
	//background & lifestyle
	private int maritalStatus;
	private int sexualOrientation;
	private String religion;
	private String hometown;
	private int smoker;
	private int drinker;
	private int childrenStatus;
	private int education;
	private String income;
	
	//interests and personality
	private String headline;
	private String aboutMe;
	private String likeToMeet;
	private String interests;
	private String music;
	private String movies;
	private String television;
	private String books;
	private String heroes;

	public UserProfile(){
	super();
	}

	public UserProfile(long userId){
	this();
	this.userId = userId;
	}

	public String getName(){
		StringBuilder name= new StringBuilder();
		if(this.firstName != null) name.append(this.firstName);
		if(this.middleName!= null) name.append(" ").append(this.middleName);
		if(this.lastName != null) name.append(" ").append(this.lastName);
		return name.toString();
	}
	
	public String getAboutMe() {
		return aboutMe;
	}
	

	public void setAboutMe(String aboutMe) {
		this.aboutMe = aboutMe;
	}
	

	public int getBodyType() {
		return bodyType;
	}
	

	public void setBodyType(int bodyType) {
		this.bodyType = bodyType;
	}
	

	public String getBooks() {
		return books;
	}
	

	public void setBooks(String books) {
		this.books = books;
	}
	

	public int getChildrenStatus() {
		return childrenStatus;
	}
	

	public void setChildrenStatus(int childrenStatus) {
		this.childrenStatus = childrenStatus;
	}
	

	public int getDrinker() {
		return drinker;
	}
	

	public void setDrinker(int drinker) {
		this.drinker = drinker;
	}
	

	public int getEducation() {
		return education;
	}
	

	public void setEducation(int education) {
		this.education = education;
	}
	

	public int getEthnicity() {
		return ethnicity;
	}
	

	public void setEthnicity(int ethnicity) {
		this.ethnicity = ethnicity;
	}
	

	public String getFirstName() {
		return firstName;
	}
	

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	

	public String getHeadline() {
		return headline;
	}
	

	public void setHeadline(String headline) {
		this.headline = headline;
	}
	

	public int getHeight() {
		return height;
	}
	

	public void setHeight(int height) {
		this.height = height;
	}
	

	public String getHeroes() {
		return heroes;
	}
	

	public void setHeroes(String heroes) {
		this.heroes = heroes;
	}
	

	public String getHometown() {
		return hometown;
	}
	

	public void setHometown(String hometown) {
		this.hometown = hometown;
	}
	

	public String getIncome() {
		return income;
	}
	

	public void setIncome(String income) {
		this.income = income;
	}
	

	public String getInterests() {
		return interests;
	}
	

	public void setInterests(String interests) {
		this.interests = interests;
	}
	

	public String getLastName() {
		return lastName;
	}
	

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	

	public String getLikeToMeet() {
		return likeToMeet;
	}
	

	public void setLikeToMeet(String likeToMeet) {
		this.likeToMeet = likeToMeet;
	}
	

	public int getMaritalStatus() {
		return maritalStatus;
	}
	

	public void setMaritalStatus(int maritalStatus) {
		this.maritalStatus = maritalStatus;
	}
	

	public String getMiddleName() {
		return middleName;
	}
	

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	

	public String getMovies() {
		return movies;
	}
	

	public void setMovies(String movies) {
		this.movies = movies;
	}
	

	public String getMusic() {
		return music;
	}
	

	public void setMusic(String music) {
		this.music = music;
	}
	

	public String getOccupation() {
		return occupation;
	}
	

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}
	

	public int getRegisterPurpose() {
		return registerPurpose;
	}
	

	public void setRegisterPurpose(int registerPurpose) {
		this.registerPurpose = registerPurpose;
	}
	

	public String getReligion() {
		return religion;
	}
	

	public void setReligion(String religion) {
		this.religion = religion;
	}
	

	public int getSexualOrientation() {
		return sexualOrientation;
	}
	

	public void setSexualOrientation(int sexualOrientation) {
		this.sexualOrientation = sexualOrientation;
	}
	

	public int getSmoker() {
		return smoker;
	}
	

	public void setSmoker(int smoker) {
		this.smoker = smoker;
	}
	

	public String getTelevision() {
		return television;
	}
	

	public void setTelevision(String television) {
		this.television = television;
	}
	

	public long getUserId() {
		return userId;
	}
	

	public void setUserId(long userId) {
		this.userId = userId;
	}

}
