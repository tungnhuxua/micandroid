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

import static org.light.portal.util.Constants._ALLOW_RANDOOM_THEME;
import static org.light.portal.util.Constants._BG_IMAGES;
import static org.light.portal.util.Constants._FONTS;
import static org.light.portal.util.Constants._FONTSIZE_LABEL;
import static org.light.portal.util.Constants._FONTSIZE_VALUE;
import static org.light.portal.util.Constants._FONT_COLOR_LABEL;
import static org.light.portal.util.Constants._FONT_COLOR_VALUE;
import static org.light.portal.util.Constants._HEADER_HEIGHT_LABEL;
import static org.light.portal.util.Constants._HEADER_HEIGHT_VALUE;
import static org.light.portal.util.Constants._HEADER_IMAGES;
import static org.light.portal.util.Constants._MAX_SHOW_TABS_MAX;
import static org.light.portal.util.Constants._MAX_SHOW_TABS_MIN;
import static org.light.portal.util.Constants._TAG_TYPE_ID;
import static org.light.portal.util.Constants._TAG_TYPE_NAME;
import static org.light.portal.util.Constants._THEME;
import static org.light.portal.util.Constants._WINDOW_SKIN;
import static org.light.portal.util.Constants._CLIENTS;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * 
 * @author Jianmin Liu
 **/
public class ConfigurationUtil {
	
	public static List<LabelBean> getSupportedThemes() {
		List<LabelBean>  themeCopy = new LinkedList<LabelBean>();
		for(LabelBean bean : themes){
			themeCopy.add(new LabelBean(bean.getId(),bean.getDesc()));
		}
		return themeCopy;
	}
	
	public static List<LabelBean> getSupportedClients() {
		List<LabelBean>  clientCopy = new LinkedList<LabelBean>();
		for(LabelBean bean : clients){
			clientCopy.add(new LabelBean(bean.getId(),bean.getDesc()));
		}
		return clientCopy;
	}
	
	public static List<LabelBean> getSupportedWindowSkins() {
		List<LabelBean>  windowSkinCopy = new LinkedList<LabelBean>();
		for(LabelBean bean : windowSkins){
			windowSkinCopy.add(new LabelBean(bean.getId(),bean.getDesc()));
		}
		return windowSkinCopy;
	}
	
	public static List<LabelBean> getSupportedBgImages() {
		return bgImages;
	}
	
	public static List<LabelBean> getSupportedHeaderImages() {
		return headerImages;
	}
	
	public static List<String> getSupportedFonts() {
		return fonts;
	}
	public static List<LabelBean> getSupportedFontSizes() {
		return fontSizes;
	}
	public static String getRandomColor(){
		Random r = new Random();
		//Color  c = new Color(r.nextInt(256), r.nextInt(256), r.nextInt(256));
		//c.toString();
		int total = fontColors.size();
		int random = r.nextInt(total);
		return fontColors.get(random).getId();
	}
	public static String getRandomTheme(){
		Random r = new Random();		
		int total = themes.size();
		int random = r.nextInt(total);
		while(random < 1){
			random = r.nextInt(total);
		}
		return themes.get(random).getId();
	}
	public static List<LabelBean> getSupportedFontColors() {
		return fontColors;
	}
	public static List<LabelBean> getSupportedHeaderHeights() {
		return headerHeights;
	}
	public static List<LabelBean> getSupportedTagTypes() {
		return tagTypes;
	}
	public static List<Integer> getMaxShowTabsNumber(){
		List<Integer> list = new LinkedList<Integer>();
		int begin = 1;
		try{
			begin = PropUtil.getInt(_MAX_SHOW_TABS_MIN);
		}catch(Exception e){}
		
		int end = 30;
		try{
			end = PropUtil.getInt(_MAX_SHOW_TABS_MAX);
		}catch(Exception e){}
		for(int i=begin;i<=end;i++){
			list.add(i);
		}
		return list;
	}
	public static boolean allowRandomTheme(){
		return PropUtil.getBoolean(_ALLOW_RANDOOM_THEME);
	}
	private static  List<LabelBean>  themes;
	private static  List<LabelBean>  bgImages;
	private static  List<LabelBean>  headerImages;
	private static  List<LabelBean>  windowSkins;
	private static  List<LabelBean>  clients;
	private static 	List<String> fonts;
	private static  List<LabelBean>  fontSizes;
	private static  List<LabelBean>  fontColors;
	private static  List<LabelBean>  headerHeights;
	private static  List<LabelBean>  tagTypes;
	
	static{
		themes = new LinkedList<LabelBean>();
		String[] themeArray = PropUtil.getStringArray(_THEME);
		for(int i=0;i<themeArray.length;i++){
			themes.add(new LabelBean(themeArray[i],themeArray[i]));
		}
		
		bgImages = new LinkedList<LabelBean>();
		String[] bgImagesArray = PropUtil.getStringArray(_BG_IMAGES);
		for(int i=0;i<bgImagesArray.length;i++){
			bgImages.add(new LabelBean(bgImagesArray[i],bgImagesArray[i]));
		}
		
		headerImages = new LinkedList<LabelBean>();
		String[] headerImagesArray = PropUtil.getStringArray(_HEADER_IMAGES);
		for(int i=0;i<headerImagesArray.length;i++){
			headerImages.add(new LabelBean(headerImagesArray[i],headerImagesArray[i]));
		}
		
		windowSkins = new LinkedList<LabelBean>();
		String[] windowSkinArray = PropUtil.getStringArray(_WINDOW_SKIN);
		for(int i=0;i<windowSkinArray.length;i++){
			windowSkins.add(new LabelBean(windowSkinArray[i],windowSkinArray[i]));
		}
		
		clients = new LinkedList<LabelBean>();
		String[] clientArray = PropUtil.getStringArray(_CLIENTS);
		for(int i=0;i<clientArray.length;i++){
			clients.add(new LabelBean(String.valueOf(i),clientArray[i]));
		}

		String[] listFonts = PropUtil.getStringArray(_FONTS);
		fonts = new LinkedList<String>();
		fonts.add(0,"");
		for(String font : listFonts){
			fonts.add(font);
		}
		String[] fontSizeLabels = PropUtil.getStringArray(_FONTSIZE_LABEL);
		String[] fontSizeValues = PropUtil.getStringArray(_FONTSIZE_VALUE);
		fontSizes = new LinkedList<LabelBean>();
		for(int i=0;i<fontSizeLabels.length;i++){
			fontSizes.add(new LabelBean(fontSizeValues[i],fontSizeLabels[i]));
		}
		
		String[] fontColorLabels = PropUtil.getStringArray(_FONT_COLOR_LABEL);
		String[] fontColorValues = PropUtil.getStringArray(_FONT_COLOR_VALUE);
		fontColors = new LinkedList<LabelBean>();
		fontColors.add(new LabelBean("", ""));
		for(int i=0;i<fontColorLabels.length;i++){
			fontColors.add(new LabelBean(fontColorLabels[i],fontColorValues[i]));
		}

		String[] headerHeightLabels = PropUtil.getStringArray(_HEADER_HEIGHT_LABEL);
		String[] headerHeightValues = PropUtil.getStringArray(_HEADER_HEIGHT_VALUE);
		headerHeights = new LinkedList<LabelBean>();
		for(int i=0;i<headerHeightLabels.length;i++){
			headerHeights.add(new LabelBean(headerHeightValues[i],headerHeightLabels[i]));
		}
				
		String[] typeIds = PropUtil.getStringArray(_TAG_TYPE_ID);
		String[] types = PropUtil.getStringArray(_TAG_TYPE_NAME);
		tagTypes = new LinkedList<LabelBean>();
		for(int i=0;i<typeIds.length;i++){
			tagTypes.add(new LabelBean(typeIds[i],types[i]));
		}
	}	
}