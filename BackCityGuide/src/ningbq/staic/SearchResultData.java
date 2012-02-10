package ningbq.staic;

import java.util.ArrayList;
import java.util.List;

import ningbq.bean.Location;

public class SearchResultData {
	
	private SearchResultData(){}
	
	public static List<Location> getResultData(){
		Location l = new Location() ;
		List<Location> list = new ArrayList<Location>() ;
		l.setLatitude(29.803144 * 1E6) ;
		l.setLongitude(121.562843 * 1E6) ;
		l.setName_cn("诺丁汉大学") ;
		list.add(l) ;
		
		list = new ArrayList<Location>() ;
		l.setLatitude(29.9133455 * 1E6) ;
		l.setLongitude(121.6328865 * 1E6) ;
		l.setName_cn("宁波大学	") ;
		
		list.add(l) ;
		
		return list ;
		
	}
	


}
