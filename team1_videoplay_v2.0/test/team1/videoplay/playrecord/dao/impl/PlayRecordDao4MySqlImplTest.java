package team1.videoplay.playrecord.dao.impl;



import java.util.ArrayList;

import org.junit.Test;

import team1.videoplay.playrecord.model.PlayRecord;


public class PlayRecordDao4MySqlImplTest {
	@Test
	public void testDeletPlayRecord(){
		new PlayRecordDao4MySqlImpl().deletePlayRecord(1);
	}
	@Test
	public void testGetAllPlayRecord(){
		new PlayRecordDao4MySqlImpl().getAllPlayRecord(2, 3);
	}
	@Test
	public void testGetPlayRecord(){
		new PlayRecordDao4MySqlImpl().getPlayRecord(2);
	}
	
	@Test
	public void testAddPlayRecord(){
		PlayRecord r = new PlayRecord();
		
		new PlayRecordDao4MySqlImpl().addPlayRecord(r);
	}
	@Test
	public void testGetPlayRecordByUserID(){
		
		ArrayList<PlayRecord> list = new PlayRecordDao4MySqlImpl().getPlayRecordByUserID(1);
		for(PlayRecord pr:list){
			System.out.println(pr.getPlay_time());
		}
	}
}
