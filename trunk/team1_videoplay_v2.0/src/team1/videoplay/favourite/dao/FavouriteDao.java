package team1.videoplay.favourite.dao;



import java.util.ArrayList;

import team1.videoplay.favourite.model.Favourite;


public interface FavouriteDao {
	public int addFavourite(Favourite favourite);
	public int deleteFavourite(int favourateID);
	public ArrayList<Favourite> getAllFavourite(int page,int pageSize);
	public int getFavouriteCount();
	//¡¡
	public ArrayList<Favourite> getAllFavouriteByVideoID(int videoID);
	
	//¡¡
	public ArrayList<Favourite> getAllFavouriteByUserID(int userID);
}
