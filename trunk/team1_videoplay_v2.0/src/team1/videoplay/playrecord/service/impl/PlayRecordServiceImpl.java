package team1.videoplay.playrecord.service.impl;

import java.util.ArrayList;
import java.util.Properties;

import team1.videoplay.playrecord.dao.PlayRecordDao;
import team1.videoplay.playrecord.fatory.PlayRecordDaoFactory;
import team1.videoplay.playrecord.model.PlayRecord;
import team1.videoplay.playrecord.service.PlayRecordService;
import team1.videoplay.utils.FenYe;
import team1.videoplay.utils.PropUtils;

public class PlayRecordServiceImpl implements PlayRecordService {
	private static PlayRecordDao prd;
	private PlayRecordServiceImpl(){};
	private static PlayRecordService prs=new PlayRecordServiceImpl();
	public static PlayRecordService getInstance(){
		return prs;
	}
	static {
		Properties prop = PropUtils.loadProp(PlayRecordService.class, "/file.properties");//读取文件
		String name = prop.getProperty("playrecorddaoname");				//得到文件里的PayUserdaoname
		prd = PlayRecordDaoFactory.getInstance(name);						//通过工厂得到一个AppealDao对象
	}
	public int addPlayRecord(PlayRecord playRecord) {
		return prd.addPlayRecord(playRecord);
	}

	public int deletePlayRecord(int playrecordID) {
		return prd.deletePlayRecord(playrecordID);
	}

	public FenYe getAllPlayRecord(int page) {
		FenYe fenye = new FenYe();
		fenye.setPage(page);
		int playrecordCount = prd.getPlayRecordCount();//dao里面新加入查找所有记录数的方法
		int pageSize = FenYe.pageSize;//pageSize是在FenYe类里定义的常量
		fenye.setPagecount((int)Math.ceil((double)playrecordCount/pageSize));//得到总页数
		fenye.setList(prd.getAllPlayRecord(page, pageSize));
		return fenye;
	}

	public PlayRecord getPlayRecord(int playrecordID) {
		return prd.getPlayRecord(playrecordID);
	}

	public int getPlayRecordCount() {
		// TODO Auto-generated method stub
		return prd.getPlayRecordCount();
	}
	public ArrayList<PlayRecord> getPlayRecordByUserID(int userID){
		return prd.getPlayRecordByUserID(userID);
	}
	//亮
	public ArrayList<PlayRecord> getAllPlayRecordByVideoID(int videoID){
		return prd.getAllPlayRecordByVideoID(videoID);
	}
	//亮
	public ArrayList<PlayRecord> getAllPlayRecordByUserID(int userID) {
		return prd.getAllPlayRecordByUserID(userID);
	}

}
