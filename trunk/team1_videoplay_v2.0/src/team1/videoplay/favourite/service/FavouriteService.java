package team1.videoplay.favourite.service;


import java.util.ArrayList;

import team1.videoplay.favourite.model.Favourite;
import team1.videoplay.utils.FenYe;

public interface FavouriteService {
	public int addFavourite(Favourite favourite);
	public int deleteFavourite(int favourateID);
	public FenYe getAllFavourite(int page);
	public int getFavouriteCount();
	//¡¡
	public ArrayList<Favourite> getAllFavouriteByVideoID(int videoID);

	//¡¡
	public ArrayList<Favourite> getAllFavouriteByUserID(int userID);
}
