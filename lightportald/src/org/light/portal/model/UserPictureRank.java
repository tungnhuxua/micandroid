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

import java.util.Date;

/**
 * 
 * @author Jianmin Liu
 **/
public class UserPictureRank extends Entity{

	private long userId;
	private long pictureId;
	private int rankScore;
	private long rankById;
	
	public UserPictureRank(){
		super();
	}
	
	public UserPictureRank(long userId,long pictureId,int rankScore,long rankById){
		this();
		this.userId = userId;
		this.pictureId = pictureId;
		this.rankScore = rankScore;
		this.rankById= rankById;
	}

	public long getPictureId() {
		return pictureId;
	}
	

	public void setPictureId(long pictureId) {
		this.pictureId = pictureId;
	}
	

	public long getRankById() {
		return rankById;
	}
	

	public void setRankById(long rankById) {
		this.rankById = rankById;
	}
	

	public int getRankScore() {
		return rankScore;
	}
	

	public void setRankScore(int rankScore) {
		this.rankScore = rankScore;
	}
	

	public long getUserId() {
		return userId;
	}
	

	public void setUserId(long userId) {
		this.userId = userId;
	}

}
