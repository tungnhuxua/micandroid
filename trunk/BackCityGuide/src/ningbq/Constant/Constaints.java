package ningbq.Constant;
/**
 * 
 * @author Saurabh Solanki
 * @description : class used for common values used in project 
 *
 */
public class Constaints 
{
	public static  double    	LATITUDE  = 0;
	public static  double   	LONGITUDE = 0;
	public static  String[][]   CITY      = {{"Beijing","12 miles"},{"Shanghai","1.5 miles"},{"Guangzhou","66 miles"},{"Shenzhen","57 miles"},{"Hangzhou","4.5 miles"},{"Chengdu","5.6 miles"},{"Xi'an","42 miles"},{"Suzhou","59 miles"},{"Guilin","90 miles"},{"Chongqing","60 miles"}};
	
	/**
	 * 
	 * @param latitude1 : latitude in double 
	 * @param longitude1: longitude in double
	 * @return Distance in Double 
	 * @description find the distance of given place form current loaction 
	 */
	public static double distance(double latitude1,double longitude1)
	{
		return( 3959 * Math.acos( Math.cos( Math.toRadians(LATITUDE) ) * Math.cos( Math.toRadians( latitude1 ) ) * 
				 Math.cos( Math.toRadians( longitude1 ) - Math.toRadians(LONGITUDE) ) + Math.sin( Math.toRadians(LATITUDE) ) 
					* Math.sin( Math.toRadians( latitude1 ) ) ) );
	}
}
