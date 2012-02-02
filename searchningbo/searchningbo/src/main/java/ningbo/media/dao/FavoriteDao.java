package ningbo.media.dao;

import ningbo.media.bean.Favorite;
import ningbo.media.core.dao.BaseDao;

public interface FavoriteDao extends BaseDao<Favorite, Integer> {

	public Favorite findFavoriteById(String userId,String locationId) ;
}
