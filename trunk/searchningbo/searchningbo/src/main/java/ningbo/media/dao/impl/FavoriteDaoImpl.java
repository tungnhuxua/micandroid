package ningbo.media.dao.impl;

import org.springframework.stereotype.Repository;

import ningbo.media.bean.Favorite;
import ningbo.media.core.dao.impl.BaseDaoImpl;
import ningbo.media.dao.FavoriteDao;

@Repository("favoriteDao")
public class FavoriteDaoImpl extends BaseDaoImpl<Favorite, Integer> implements
		FavoriteDao {

	public FavoriteDaoImpl() {
		super(Favorite.class);
	}

	public Favorite findFavoriteById(String userId, String locationId) {
		
		final String hql = "from Favorite model where model.userId = ? and model.locationId = ? ";
		Favorite favorite = null;
		if ("".equals(userId) || "".equals(locationId) || userId == null
				|| locationId == null) {
			return null;
		}
		try {
			favorite = (Favorite) findUnique(hql, Integer.valueOf(userId),
					Integer.valueOf(locationId));
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
		return favorite;

	}
}
