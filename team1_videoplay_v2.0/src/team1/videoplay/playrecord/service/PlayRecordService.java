package team1.videoplay.playrecord.service;

import java.util.ArrayList;

import team1.videoplay.playrecord.model.PlayRecord;
import team1.videoplay.utils.FenYe;

public interface PlayRecordService {
	public int addPlayRecord(PlayRecord playRecord);
	public int deletePlayRecord(int playrecordID);
	public FenYe getAllPlayRecord(int page);
	public PlayRecord getPlayRecord(int playrecordID);
	public int getPlayRecordCount();
	public ArrayList<PlayRecord> getPlayRecordByUserID(int userID);
	//¡¡
	public ArrayList<PlayRecord> getAllPlayRecordByVideoID(int videoID);
	//¡¡
	public ArrayList<PlayRecord> getAllPlayRecordByUserID(int userID) ;
}
