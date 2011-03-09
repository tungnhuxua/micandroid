package team1.videoplay.playrecord.dao;

import java.util.ArrayList;

import team1.videoplay.playrecord.model.PlayRecord;




public interface PlayRecordDao {
	public int addPlayRecord(PlayRecord playRecord);
	public int deletePlayRecord(int PlayRecordID);
	public ArrayList<PlayRecord> getAllPlayRecord(int page,int pageSize);
	public PlayRecord getPlayRecord(int PlayRecordID);
	public int getPlayRecordCount();
	public ArrayList<PlayRecord> getPlayRecordByUserID(int userID);
	//¡¡
	public ArrayList<PlayRecord> getAllPlayRecordByVideoID(int videoID);
	//¡¡
	public ArrayList<PlayRecord> getAllPlayRecordByUserID(int userID) ;
}
