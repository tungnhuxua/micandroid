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

import static org.light.portal.util.Constants._COUNTRY_LIST;
import static org.light.portal.util.Constants._TIMEZONE_LIST;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * 
 * @author Jianmin Liu
 **/
public class LocaleUtil {
	
	public static List<Language> getSupportedLanguages() {
		return languages;
	}
	
	public static List<Region> getSupportedRegions() {
		return regions;
	}
	
	public static List<Region> getSupportedCountry(Locale locale) {	
		if(countries.get(locale) == null){
			synchronized(countries){
				List<Region> countryList = new ArrayList<Region>();
				String[] countryArray = PropUtil.getStringArray(_COUNTRY_LIST);
				for(int i=0;i<countryArray.length;i++){
					countryList.add(new Region(MessageUtil.getMessage(countryArray[i],locale),countryArray[i]));					
				}
				countries.put(locale,countryList);
			}
		}
		return countries.get(locale);
	}
	
	public static List<LabelBean> getSupportedTimeZone(Locale locale) {
		if(timeZones.get(locale) == null){
			synchronized(timeZones){
				List<LabelBean> timezoneList = new ArrayList<LabelBean>();
				String[] timezoneArray = PropUtil.getStringArray(_TIMEZONE_LIST);
				for(int i=0;i<timezoneArray.length;i++){
					timezoneList.add(new LabelBean(MessageUtil.getMessage(timezoneArray[i],locale),timezoneArray[i]));
				}
				timeZones.put(locale,timezoneList);
			}
		}
		return timeZones.get(locale);
	}
	public static List<Region> getSupportedProvince() {
		return provinces;
	}
	private static List<Language> languages;
	private static List<Region> regions;
	private static Map<Locale,List<Region>> countries;
	private static List<Region> provinces;
	private static Map<Locale,List<LabelBean>> timeZones;
	
	static{
		countries= new HashMap<Locale,List<Region>>();
		timeZones= new HashMap<Locale,List<LabelBean>>();
		
		languages= new ArrayList<Language>();		
		languages.add(new Language("ar","Arabic"));
		languages.add(new Language("da","Danish"));
		languages.add(new Language("de_DE","German"));
		languages.add(new Language("el","Greek"));
		languages.add(new Language("en","English",true));
		languages.add(new Language("es","Spanish"));
		languages.add(new Language("fi","Finnish"));
		languages.add(new Language("fr","French"));
		languages.add(new Language("hu","Hungarian"));
		languages.add(new Language("is","Icelandic"));
		languages.add(new Language("it_IT","Italian"));
		languages.add(new Language("iw_IL","Hebrew"));
		languages.add(new Language("ja_JP","Japanese"));
		languages.add(new Language("ko_KR","Korean"));
		languages.add(new Language("nl_NL ","Dutch"));
		languages.add(new Language("no","Norwegian"));
		languages.add(new Language("pl","Polish"));
		languages.add(new Language("pt","Portuguese"));
		languages.add(new Language("ru","Russian"));
		languages.add(new Language("sv","Swedish"));
		languages.add(new Language("th","Thai"));
		languages.add(new Language("tr","Turkish"));
		languages.add(new Language("zh_CN","\u4e2d\u6587(\u7b80\u4f53)",true));
		languages.add(new Language("zh_TW","Traditional Chinese"));
		
		regions= new ArrayList<Region>();
		regions.add(new Region("English","en"));
		regions.add(new Region("Arabic","ar"));		
		regions.add(new Region("Danish","da"));
		regions.add(new Region("German","de_DE"));
		regions.add(new Region("Greek","el"));		
		regions.add(new Region("Canada(English)","en_CA"));
		regions.add(new Region("Canada(French)","fr_CA"));
		regions.add(new Region("United States","en_US"));		
		regions.add(new Region("United Kingdom","en_GB"));		
		regions.add(new Region("Spanish","es"));
		regions.add(new Region("Finnish","fi"));
		regions.add(new Region("French","fr"));
		regions.add(new Region("Hungarian","hu"));
		regions.add(new Region("Icelandic","is"));
		regions.add(new Region("Italian","it_IT"));
		regions.add(new Region("Hebrew","iw_IL"));
		regions.add(new Region("Japanese","ja_JP"));
		regions.add(new Region("Korean","ko_KR"));
		regions.add(new Region("Dutch","nl_NL "));
		regions.add(new Region("Norwegian","no"));
		regions.add(new Region("Polish","pl"));
		regions.add(new Region("Portuguese","pt"));
		regions.add(new Region("Russian","ru"));
		regions.add(new Region("Swedish","sv"));
		regions.add(new Region("Thai","th"));
		regions.add(new Region("Turkish","tr"));				
		regions.add(new Region("\u4e2d\u56fd","zh_CN"));
		regions.add(new Region("HongKong ","zh_HK"));
		regions.add(new Region("TaiWan","zh_TW"));	
		
//		countries= new ArrayList<Region>();
//		countries.add(new Region("Afghanistan","AF"));
//		countries.add(new Region("Albania", "AL"));	
//		countries.add(new Region("Algeria", "DZ"));
//		countries.add(new Region("American Samoa","AS"));
//		countries.add(new Region("Andorra","AD"));
//		countries.add(new Region("Angola","AO"));
//		countries.add(new Region("Anguilla","AI"));
//		countries.add(new Region("Antarctica","AQ"));
//		countries.add(new Region("Antigua and Barbuda","AG"));
//		countries.add(new Region("Argentina","AR"));
//		countries.add(new Region("Armenia","AM"));
//		countries.add(new Region("Aruba","AW"));
//		countries.add(new Region("Australia","AU"));
//		countries.add(new Region("Austria","AT"));
//		countries.add(new Region("Azerbaijan","AZ"));
//		countries.add(new Region("Bahamas","BS"));
//		countries.add(new Region("Bahrain","BH"));
//		countries.add(new Region("Bangladesh","BD"));
//		countries.add(new Region("Barbados","BB"));
//		countries.add(new Region("Belarus","BY"));
//		countries.add(new Region("Belgium","BE"));
//		countries.add(new Region("Belize","BZ"));
//		countries.add(new Region("Benin","BJ"));
//		countries.add(new Region("Bermuda","BM"));
//		countries.add(new Region("Bhutan","BT"));
//		countries.add(new Region("Bolivia","BO"));
//		countries.add(new Region("Bosnia and Herzegowina","BA"));
//		countries.add(new Region("Botswana","BW"));
//		countries.add(new Region("Bouvet Island","BV"));
//		countries.add(new Region("Brazil","BR"));
//		countries.add(new Region("British Indian Ocean Territory","IO"));
//		countries.add(new Region("Brunei Darussalam","BN"));
//		countries.add(new Region("Bulgaria","BG"));
//		countries.add(new Region("Burkina Faso","BF"));
//		countries.add(new Region("Burundi","BI"));
//		countries.add(new Region("Cambodia","KH"));
//		countries.add(new Region("Cameroon","CM"));
//		countries.add(new Region("Canada","CA"));
//		countries.add(new Region("Cape Verde","CV"));
//		countries.add(new Region("Cayman Islands","KY"));
//		countries.add(new Region("Central African Republic","CF"));
//		countries.add(new Region("Chad","TD"));
//		countries.add(new Region("Chile","CL"));
//		countries.add(new Region("\u4e2d\u56fd","CN"));
//		countries.add(new Region("Christmas Island","CX"));
//		countries.add(new Region("Cocoa (Keeling) Islands","CC"));
//		countries.add(new Region("Colombia","CO"));
//		countries.add(new Region("Comoros","KM"));
//		countries.add(new Region("Congo","CG"));
//		countries.add(new Region("Cook Islands","CK"));
//		countries.add(new Region("Costa Rica","CR"));
//		countries.add(new Region("Cote Divoire","CI"));
//		countries.add(new Region("Croatia (Hrvatska)","CT"));
//		countries.add(new Region("Cuba","CU"));
//		countries.add(new Region("Cyprus","CY"));
//		countries.add(new Region("Czech Republic","CZ"));
//		countries.add(new Region("Denmark","DK"));
//		countries.add(new Region("Djibouti","DJ"));
//		countries.add(new Region("Dominica","DM"));
//		countries.add(new Region("Dominican Republic","DO"));
//		countries.add(new Region("East Timor","TP"));
//		countries.add(new Region("Ecuador","EC"));
//		countries.add(new Region("Egypt","EG"));
//		countries.add(new Region("El Salvador","SV"));
//		countries.add(new Region("Equatorial Guinea","GQ"));
//		countries.add(new Region("Eritrea","ER"));
//		countries.add(new Region("Estonia","EE"));
//		countries.add(new Region("Ethiopia","ET"));
//		countries.add(new Region("Falkland Islands (Malvinas)","FK"));
//		countries.add(new Region("Faroe Islands","FO"));
//		countries.add(new Region("Fiji","FJ"));
//		countries.add(new Region("Finland","FI"));
//		countries.add(new Region("France","FR"));
//		countries.add(new Region("Gabon","GA"));
//		countries.add(new Region("Gambia","GM"));
//		countries.add(new Region("Georgia","GE"));
//		countries.add(new Region("Germany","DE"));
//		countries.add(new Region("Ghana","GH"));
//		countries.add(new Region("Gibraltar","GI"));
//		countries.add(new Region("Greece","GR"));
//		countries.add(new Region("Greenland","GL"));
//		countries.add(new Region("Grenada","GD"));
//		countries.add(new Region("Guam","GU"));
//		countries.add(new Region("Guatemala","GT"));
//		countries.add(new Region("Guinea","GN"));
//		countries.add(new Region("Guinea-Bissau","GW"));
//		countries.add(new Region("Guyana","GY"));
//		countries.add(new Region("Haiti","HT"));
//		countries.add(new Region("Heard and Mc Donald Islands","HM"));
//		countries.add(new Region("Honduras","HN"));
//		countries.add(new Region("Hong Kong","HK"));
//		countries.add(new Region("Hungary","HU"));
//		countries.add(new Region("Iceland","IS"));
//		countries.add(new Region("India","IN"));
//		countries.add(new Region("Indonesia","ID"));
//		countries.add(new Region("Iran (Islamic Republic of)","IR"));
//		countries.add(new Region("Iraq","IQ"));
//		countries.add(new Region("Ireland","IE"));
//		countries.add(new Region("Israel","IL"));
//		countries.add(new Region("Italy","IT"));
//		countries.add(new Region("Jamaica","JM"));
//		countries.add(new Region("Japan","JP"));
//		countries.add(new Region("Jordan","JO"));
//		countries.add(new Region("Kazakhstan","KZ"));
//		countries.add(new Region("Kenya","KE"));
//		countries.add(new Region("Kiribati","KI"));
//		countries.add(new Region("Korea,  Democratic Peoples Republic of","KP"));
//		countries.add(new Region("Korea,  Republic of","KR"));
//		countries.add(new Region("Kuwait","KW"));
//		countries.add(new Region("Kyrgyzstan","KG"));
//		countries.add(new Region("Lao Peoples Democratic Republic","LA"));
//		countries.add(new Region("Latvia","LV"));
//		countries.add(new Region("Lebanon","LB"));
//		countries.add(new Region("Lesotho","LS"));
//		countries.add(new Region("Liberia","LR"));
//		countries.add(new Region("Libyan Arab Jamahiriya","LY"));
//		countries.add(new Region("Liechtenstein","LI"));
//		countries.add(new Region("Lithuania","LT"));
//		countries.add(new Region("Luxembourg","LU"));
//		countries.add(new Region("Macau","MO"));
//		countries.add(new Region("Macedonia, The Former Yugoslav Republic of","MK"));
//		countries.add(new Region("Madagascar","MG"));
//		countries.add(new Region("Malawi","MW"));
//		countries.add(new Region("Malaysia","MY"));
//		countries.add(new Region("Maldives","MV"));
//		countries.add(new Region("Mali","ML"));
//		countries.add(new Region("Malta","MT"));
//		countries.add(new Region("Marshall Islands","MH"));
//		countries.add(new Region("Mauritania","MR"));
//		countries.add(new Region("Mauritius","MU"));
//		countries.add(new Region("Mexico","MX"));
//		countries.add(new Region("Micronesia, Federated States of","FM"));
//		countries.add(new Region("Moldova,  Republic of","MD"));
//		countries.add(new Region("Monaco","MC"));
//		countries.add(new Region("Mongolia","MN"));
//		countries.add(new Region("Montserrat","MS"));
//		countries.add(new Region("Morocco","MA"));
//		countries.add(new Region("Mozambique","MZ"));
//		countries.add(new Region("Myanmar","MM"));
//		countries.add(new Region("Namibia","NA"));
//		countries.add(new Region("Nauru","NR"));
//		countries.add(new Region("Nepal","NP"));
//		countries.add(new Region("Netherlands","NL"));
//		countries.add(new Region("Netherlands Antilles","AN"));
//		countries.add(new Region("New Zealand","NZ"));
//		countries.add(new Region("Nicaragua","NI"));
//		countries.add(new Region("Niger","NE"));
//		countries.add(new Region("Nigeria","NG"));
//		countries.add(new Region("Niue","NU"));
//		countries.add(new Region("Norfolk Island","NF"));
//		countries.add(new Region("Northern Mariana Islands","MP"));
//		countries.add(new Region("Norway","NO"));
//		countries.add(new Region("Oman","OM"));
//		countries.add(new Region("Pakistan","PK"));
//		countries.add(new Region("Palau","PW"));
//		countries.add(new Region("Panama","PA"));
//		countries.add(new Region("Papua New Guinea","PG"));
//		countries.add(new Region("Paraguay","PY"));
//		countries.add(new Region("Peru","PE"));
//		countries.add(new Region("Philippines","PH"));
//		countries.add(new Region("Pitcairn","PN"));
//		countries.add(new Region("Poland","PL"));
//		countries.add(new Region("Portugal","PT"));
//		countries.add(new Region("Puerto Rico","PR"));
//		countries.add(new Region("Qatar","QA"));
//		countries.add(new Region("Romania","RO"));
//		countries.add(new Region("Russian Federation","RU"));
//		countries.add(new Region("Rwanda","RW"));
//		countries.add(new Region("Saint Kitts and Nevis","KN"));
//		countries.add(new Region("Saint Lucia","LC"));
//		countries.add(new Region("Saint Vincent and the Grenadines","VC"));
//		countries.add(new Region("Samoa","WS"));
//		countries.add(new Region("San Marino","SM"));
//		countries.add(new Region("Sao Tome and Principe","ST"));
//		countries.add(new Region("Saudi Arabia","SA"));
//		countries.add(new Region("Senegal","SN"));
//		countries.add(new Region("Seychelles","SC"));
//		countries.add(new Region("Sierra Leone","SL"));
//		countries.add(new Region("Singapore","SG"));
//		countries.add(new Region("Slovakia (Slovak Republic)","SK"));
//		countries.add(new Region("Slovenia","SI"));
//		countries.add(new Region("Solomon Islands","Sb"));
//		countries.add(new Region("Somalia","SO"));
//		countries.add(new Region("South Africa","ZA"));
//		countries.add(new Region("South Georgia and the South Sandwich Islands","GS"));
//		countries.add(new Region("Spain","ES"));
//		countries.add(new Region("Sri Lanka","LK"));
//		countries.add(new Region("St. Helena","SH"));
//		countries.add(new Region("Sudan","SD"));
//		countries.add(new Region("Suriname","SR"));
//		countries.add(new Region("Svalbard and Jan Mayen Islands","SJ"));
//		countries.add(new Region("Swaziland","SZ"));
//		countries.add(new Region("Sweden","SE"));
//		countries.add(new Region("Switzerland","CH"));
//		countries.add(new Region("Syrian ArabRepublic","SY"));
//		countries.add(new Region("Taiwan","TW"));
//		countries.add(new Region("Tajikistan","TJ"));
//		countries.add(new Region("Tanzania,  United Republic of","TZ"));
//		countries.add(new Region("Thailand","TH"));
//		countries.add(new Region("Togo","TG"));
//		countries.add(new Region("Tokelau","TK"));
//		countries.add(new Region("Tonga","TO"));
//		countries.add(new Region("Trinidad and Tobago","TT"));
//		countries.add(new Region("Tunisia","TN"));
//		countries.add(new Region("Turkey","TR"));
//		countries.add(new Region("Turkmenistan","TM"));
//		countries.add(new Region("Turks and Caicos Islands","TC"));
//		countries.add(new Region("Tuvalu","TV"));
//		countries.add(new Region("Uganda","UG"));
//		countries.add(new Region("Ukraine","UA"));
//		countries.add(new Region("United ArabEmirates","AE"));
//		countries.add(new Region("United Kingdom","UK"));
//		countries.add(new Region("United States","US"));
//		countries.add(new Region("United States Minor Outlying Islands","UM"));
//		countries.add(new Region("Uruguay","UY"));
//		countries.add(new Region("Uzbekistan","UZ"));
//		countries.add(new Region("Vanuatu","VU"));
//		countries.add(new Region("Vatican City State(Holy See)","VA"));
//		countries.add(new Region("Venezuela","VE"));
//		countries.add(new Region("Viet Nam","VN"));
//		countries.add(new Region("Virgin Islands (British)","VG"));
//		countries.add(new Region("Virgin Islands (U.S.)","VI"));
//		countries.add(new Region("Western Sahara","EH"));
//		countries.add(new Region("Yeman","YE"));
//		countries.add(new Region("Yugoslavia","YU"));
//		countries.add(new Region("Zaire","ZR"));
//		countries.add(new Region("Zambia","ZM"));
//		countries.add(new Region("Zimbabwe","ZW"));
		
		provinces= new ArrayList<Region>();
		
//		timeZones= new ArrayList<LabelBean>();
//		timeZones.add(new LabelBean("GMT", "Greenwich Mean Time (GMT)"));
//		timeZones.add(new LabelBean("UTC", "Universal Coordinated Time (GMT)"));
//		timeZones.add(new LabelBean("ECT", "European Central Time (GMT+1:00)"));
//		timeZones.add(new LabelBean("EET", "Eastern European Time (GMT+2:00)"));
//		timeZones.add(new LabelBean("ART", "(Arabic) Egypt Standard Time (GMT+2:00)"));
//		timeZones.add(new LabelBean("EAT", "Eastern African Time (GMT+3:00)"));
//		timeZones.add(new LabelBean("MET", "Middle East Time (GMT+3:30)"));
//		timeZones.add(new LabelBean("NET", "Near East Time (GMT+4:00)"));
//		timeZones.add(new LabelBean("PLT", "Pakistan Lahore Time (GMT+5:00)"));
//		timeZones.add(new LabelBean("IST", "India Standard Time (GMT+5:30)"));
//		timeZones.add(new LabelBean("BST", "Bangladesh Standard Time (GMT+6:00)"));
//		timeZones.add(new LabelBean("VST", "Vietnam Standard Time (GMT+7:00)"));
//		timeZones.add(new LabelBean("CTT", "China Taiwan Time (GMT+8:00)"));
//		timeZones.add(new LabelBean("JST", "Japan Standard Time (GMT+9:00)"));
//		timeZones.add(new LabelBean("ACT", "Australia Central Time (GMT+9:30)"));
//		timeZones.add(new LabelBean("AET", "Australia Eastern Time (GMT+10:00)"));
//		timeZones.add(new LabelBean("SST", "Solomon Standard Time (GMT+11:00)"));
//		timeZones.add(new LabelBean("NST", "New Zealand Standard Time (GMT+12:00)"));
//		timeZones.add(new LabelBean("MIT", "Midway Islands Time (GMT-11:00)"));
//		timeZones.add(new LabelBean("HST", "Hawaii Standard Time (GMT-10:00)"));
//		timeZones.add(new LabelBean("AST", "Alaska Standard Time (GMT-9:00)"));
//		timeZones.add(new LabelBean("PST", "Pacific Standard Time (GMT-8:00)"));
//		timeZones.add(new LabelBean("PNT", "Phoenix Standard Time (GMT-7:00)"));
//		timeZones.add(new LabelBean("MST", "Mountain Standard Time (GMT-7:00)"));
//		timeZones.add(new LabelBean("CST", "Central Standard Time (GMT-6:00)"));
//		timeZones.add(new LabelBean("EST", "Eastern Standard Time (GMT-5:00)"));
//		timeZones.add(new LabelBean("IET", "Indiana Eastern Standard Time (GMT-5:00)"));
//		timeZones.add(new LabelBean("PRT", "Puerto Rico and US Virgin Islands Time (GMT-4:00)"));
//		timeZones.add(new LabelBean("CNT", "Canada Newfoundland Time (GMT-3:30)"));
//		timeZones.add(new LabelBean("AGT", "Argentina Standard Time (GMT-3:00)"));
//		timeZones.add(new LabelBean("BET", "Brazil Eastern Time (GMT-3:00)"));
//		timeZones.add(new LabelBean("CAT","Central African Time (GMT-1:00)"));
	}
}
