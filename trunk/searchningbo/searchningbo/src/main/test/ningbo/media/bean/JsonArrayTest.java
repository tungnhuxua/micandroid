package ningbo.media.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.junit.Test;

public class JsonArrayTest {

	private List<String> getStringList(){
		List<String> values = new ArrayList<String>() ;
		values.add("zoopnin") ;
		values.add("leyxan") ;
		values.add("devon") ;
		
		return values; 
	}
	
	@Test
	public void testArray(){
		
		String[] strs = this.getStringList().toArray(new String[0]) ;
		
		for(String str : strs){
			System.out.println(str) ;
		}
		
		System.out.println(Locale.getDefault().getCountry()) ;
	}
}
