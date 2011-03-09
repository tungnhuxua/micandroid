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

package org.light.portal.util;

import static org.light.portal.util.Constants._DEFAULT_USER_PICTURE_MAX_HEIGHT;
import static org.light.portal.util.Constants._DEFAULT_USER_PICTURE_MAX_WIDTH;
import static org.light.portal.util.Constants._DEFAULT_USER_PORTRAIT_HEIGHT;
import static org.light.portal.util.Constants._DEFAULT_USER_PORTRAIT_WIDTH;
import static org.light.portal.util.Constants._DEFAULT_USER_PORTRAIT_SMALL_HEIGHT;
import static org.light.portal.util.Constants._DEFAULT_USER_PORTRAIT_SMALL_WIDTH;
import static org.light.portal.util.Constants._IMAGE_THUMB_SUFFIX;
import static org.light.portal.util.Constants._IMAGE_SMALL_SUFFIX;
import static org.light.portal.util.Constants._ITEM_IMAGE_DETAIL_MAX_WIDTH;
import static org.light.portal.util.Constants._ITEM_IMAGE_VIEW_MAX_WIDTH;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

/**
 * 
 * @author Jianmin Liu
 **/
public class ImageUtil {

	public static boolean saveThumb(String path){
		try{
			File file = new File(path);
			java.awt.image.BufferedImage image=javax.imageio.ImageIO.read(file);								
			return saveThumb(image,path);
		}catch(Exception e){
			return false;
		}
	}
	
	public static boolean saveThumb(BufferedImage image, String path){
		try{							
			int[] size = getPhotoPortraitSize(image.getWidth(),image.getHeight());
			Image thumb  = image.getScaledInstance(size[0], size[1],Image.SCALE_SMOOTH);
			BufferedImage bi = new BufferedImage(size[0], size[1], BufferedImage.TYPE_INT_RGB);
			Graphics2D g2d = bi.createGraphics();
			g2d.drawImage(thumb, null, null);
			String thumbName = path+_IMAGE_THUMB_SUFFIX;
			File outputfile = new File(thumbName);
	        ImageIO.write(bi, "png", outputfile);
	        
	        size = getPhotoSmallSize(image.getWidth(),image.getHeight());
			Image thumb2  = image.getScaledInstance(size[0], size[1],Image.SCALE_SMOOTH);
			BufferedImage bi2 = new BufferedImage(size[0], size[1], BufferedImage.TYPE_INT_RGB);
			Graphics2D g2d2 = bi2.createGraphics();
			g2d2.drawImage(thumb2, null, null);
			String thumbName2 = path+_IMAGE_SMALL_SUFFIX;
			File outputfile2 = new File(thumbName2);
	        ImageIO.write(bi2, "png", outputfile2);
	        
	        return true;
		}catch(Exception e){
			return false;
		}
	}
	
	public static Object reSize(BufferedImage image, int width, int height, String path) throws Exception{
		int maxWidth= PropUtil.getInt(_DEFAULT_USER_PICTURE_MAX_WIDTH);
		int maxHeight = PropUtil.getInt(_DEFAULT_USER_PICTURE_MAX_HEIGHT);
		int newWidth = width;
		int newHeight = height;
		if(width > maxWidth || height > maxHeight){		
			if(width > maxWidth){
				float temp =  height * maxWidth / width;
				newHeight = Math.round(temp);
				newWidth = maxWidth;
			}
			if(newHeight > maxHeight){
				float temp = width * maxHeight / height;
				newWidth = Math.round(temp);
				newHeight = maxHeight;
			}
										
			Image resized  = image.getScaledInstance(newWidth, newHeight,Image.SCALE_SMOOTH);
			BufferedImage bi = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);
			Graphics2D g2d = bi.createGraphics();
			g2d.drawImage(resized, null, null);
			String extention = path.substring(path.lastIndexOf(".")+1);
			File outputfile = new File(path);
			outputfile.delete();
	        ImageIO.write(bi, extention, outputfile);
	        return new int[] {newWidth,newHeight};
			
		}
		return false;
	}
	
	public static int getMaxWidth(int width, int height){
		int maxWidth= PropUtil.getInt(_DEFAULT_USER_PICTURE_MAX_WIDTH);
		int maxHeight = PropUtil.getInt(_DEFAULT_USER_PICTURE_MAX_HEIGHT);
		int newWidth = width;	
		int newHeight = height;
		if(width > maxWidth){		
			if(width > maxWidth){
				float temp =  height * maxWidth / width;
				newHeight = Math.round(temp);
				newWidth = maxWidth;
			}
			if(newHeight > maxHeight){
				float temp = width * maxHeight / height;
				newWidth = Math.round(temp);
				newHeight = maxHeight;
			}
		}
		return newWidth;
	}
	
	public static int getMaxHeight(int width, int height){
		int maxWidth= PropUtil.getInt(_DEFAULT_USER_PICTURE_MAX_WIDTH);
		int maxHeight = PropUtil.getInt(_DEFAULT_USER_PICTURE_MAX_HEIGHT);
		int newWidth = width;		
		int newHeight = height;
		if(width > maxWidth){		
			if(width > maxWidth){
				float temp =  height * maxWidth / width;
				newHeight = Math.round(temp);
				newWidth = maxWidth;
			}
			if(newHeight > maxHeight){
				float temp = width * maxHeight / height;
				newWidth = Math.round(temp);
				newHeight = maxHeight;
			}
		}
		return newHeight;
	}
	
	public static int getItemViewWidth(int imageWidth, int imageHeight){
		int newWidth = imageWidth;
		int newHeight = imageHeight;
		int rate = 99;
		int _IMAGE_MAX_VIEW_WIDTH = PropUtil.getInt(_ITEM_IMAGE_VIEW_MAX_WIDTH);
		while(newWidth > _IMAGE_MAX_VIEW_WIDTH ||
				newHeight > _IMAGE_MAX_VIEW_WIDTH){
			newWidth = imageWidth * rate / 100;
			newHeight= imageHeight * rate / 100;
			rate--;
		}
		return newWidth;		
	}
	public static int getItemViewHeight(int imageWidth, int imageHeight){
		int newWidth = imageWidth;
		int newHeight = imageHeight;
		int rate = 99;
		int _IMAGE_MAX_VIEW_WIDTH = PropUtil.getInt(_ITEM_IMAGE_VIEW_MAX_WIDTH);
		while(newWidth > _IMAGE_MAX_VIEW_WIDTH ||
				newHeight > _IMAGE_MAX_VIEW_WIDTH){
			newWidth = imageWidth * rate / 100;
			newHeight= imageHeight * rate / 100;
			rate--;
		}
		return newHeight;	
	}
	
	public static int getItemDetailWidth(int imageWidth, int imageHeight){
		int newWidth = imageWidth;
		int newHeight = imageHeight;
		int rate = 99;
		int _IMAGE_MAX_VIEW_WIDTH = PropUtil.getInt(_ITEM_IMAGE_DETAIL_MAX_WIDTH);
		while(newWidth > _IMAGE_MAX_VIEW_WIDTH ||
				newHeight > _IMAGE_MAX_VIEW_WIDTH){
			newWidth = imageWidth * rate / 100;
			newHeight= imageHeight * rate / 100;
			rate--;
		}
		return newWidth;		
	}
	public static int getItemDetailHeight(int imageWidth, int imageHeight){
		int newWidth = imageWidth;
		int newHeight = imageHeight;
		int rate = 99;
		int _IMAGE_MAX_VIEW_WIDTH = PropUtil.getInt(_ITEM_IMAGE_DETAIL_MAX_WIDTH);
		while(newWidth > _IMAGE_MAX_VIEW_WIDTH ||
				newHeight > _IMAGE_MAX_VIEW_WIDTH){
			newWidth = imageWidth * rate / 100;
			newHeight= imageHeight * rate / 100;
			rate--;
		}
		return newHeight;	
	}
	
	public static String getPhotoThumbUrl(String photoUrl) {
		return StringUtil.isEmpty(photoUrl) ? null : photoUrl.toLowerCase().startsWith("http")? photoUrl : photoUrl+_IMAGE_THUMB_SUFFIX;
	}
	
	public static String getPhotoUrl(String photoUrl) {
		if(StringUtil.isEmpty(photoUrl))
			return null;
		else if(photoUrl.toLowerCase().startsWith("http"))
			return photoUrl;
		else{
			String fileName = FileUtil.getPathPrefix(OrganizationThreadLocal.getOrganizationId())+photoUrl+_IMAGE_SMALL_SUFFIX;
			File file = new File(fileName);
			if(file.exists()) return photoUrl+_IMAGE_SMALL_SUFFIX;
			
			String fileName2 = FileUtil.getPathPrefix(OrganizationThreadLocal.getOrganizationId())+photoUrl+_IMAGE_THUMB_SUFFIX;
			File file2 = new File(fileName2);
			if(file2.exists()) return photoUrl+_IMAGE_THUMB_SUFFIX;					
		}
		return photoUrl;
	}
	
	public static int[] getPhotoPortraitSize(int photoWidth,int photoHeight){
		int _DEFAULT_WIDTH= PropUtil.getInt(_DEFAULT_USER_PORTRAIT_WIDTH);
		int _DEFAULT_HEIGHT = PropUtil.getInt(_DEFAULT_USER_PORTRAIT_HEIGHT);		
		int newWidth = photoWidth;
		int newHeight = photoHeight;
		if(newWidth > _DEFAULT_WIDTH){
			float temp =  newHeight * _DEFAULT_WIDTH / newWidth;
			newHeight = Math.round(temp);
			newWidth = _DEFAULT_WIDTH;
		}
		if(newHeight > _DEFAULT_HEIGHT){
			float temp = newWidth * _DEFAULT_HEIGHT / newHeight;
			newWidth = Math.round(temp);
			newHeight = _DEFAULT_HEIGHT;
		}		
		return new int[] {newWidth, newHeight};
	}
	
	public static int[] getPhotoSmallSize(int photoWidth,int photoHeight){
		int _DEFAULT_WIDTH= PropUtil.getInt(_DEFAULT_USER_PORTRAIT_SMALL_WIDTH);
		int _DEFAULT_HEIGHT = PropUtil.getInt(_DEFAULT_USER_PORTRAIT_SMALL_HEIGHT);		
		int newWidth = photoWidth;
		int newHeight = photoHeight;
		if(newWidth > _DEFAULT_WIDTH){
			float temp =  newHeight * _DEFAULT_WIDTH / newWidth;
			newHeight = Math.round(temp);
			newWidth = _DEFAULT_WIDTH;
		}
		if(newHeight > _DEFAULT_HEIGHT){
			float temp = newWidth * _DEFAULT_HEIGHT / newHeight;
			newWidth = Math.round(temp);
			newHeight = _DEFAULT_HEIGHT;
		}		
		return new int[] {newWidth, newHeight};
	}
	
	public static int getPhotoSmallWidth(int photoWidth,int photoHeight){
		int _DEFAULT_WIDTH= PropUtil.getInt(_DEFAULT_USER_PORTRAIT_WIDTH);
		int _DEFAULT_HEIGHT = PropUtil.getInt(_DEFAULT_USER_PORTRAIT_HEIGHT);		
		int newWidth = photoWidth;
		int newHeight = photoHeight;
		if(newWidth > _DEFAULT_WIDTH){
			float temp =  newHeight * _DEFAULT_WIDTH / newWidth;
			newHeight = Math.round(temp);
			newWidth = _DEFAULT_WIDTH;
		}
		if(newHeight > _DEFAULT_HEIGHT){
			float temp = newWidth * _DEFAULT_HEIGHT / newHeight;
			newWidth = Math.round(temp);
			newHeight = _DEFAULT_HEIGHT;
		}	
		return newWidth;
	}
	public static int getPhotoSmallHeight(int photoWidth,int photoHeight){
		int _DEFAULT_WIDTH= PropUtil.getInt(_DEFAULT_USER_PORTRAIT_WIDTH);
		int _DEFAULT_HEIGHT = PropUtil.getInt(_DEFAULT_USER_PORTRAIT_HEIGHT);		
		int newWidth = photoWidth;
		int newHeight = photoHeight;
		if(newWidth > _DEFAULT_WIDTH){
			float temp =  newHeight * _DEFAULT_WIDTH / newWidth;
			newHeight = Math.round(temp);
			newWidth = _DEFAULT_WIDTH;
		}
		if(newHeight > _DEFAULT_HEIGHT){
			float temp = newWidth * _DEFAULT_HEIGHT / newHeight;
			newWidth = Math.round(temp);
			newHeight = _DEFAULT_HEIGHT;
		}	
		return newHeight;
	}
}
