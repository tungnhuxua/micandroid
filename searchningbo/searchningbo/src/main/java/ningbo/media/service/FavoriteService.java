package ningbo.media.service;

import ningbo.media.bean.Favorite;
import ningbo.media.core.service.BaseService;

public interface FavoriteService extends BaseService<Favorite, Integer> {

	public Favorite findFavoriteById(String userId,String locationId) ;
	
	public boolean isExistsFavorite(String userId,String locationId) ;
}
