package team1.videoplay.playrecord.fatory;

import team1.videoplay.playrecord.dao.PlayRecordDao;

public class PlayRecordDaoFactory {
	public static PlayRecordDao getInstance(String name){
		PlayRecordDao prd=null;
		try {
			prd = (PlayRecordDao)Class.forName(name).newInstance();

		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return prd;
	}
}
