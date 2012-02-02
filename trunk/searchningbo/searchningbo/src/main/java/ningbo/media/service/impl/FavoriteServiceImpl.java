package ningbo.media.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import ningbo.media.bean.Favorite;
import ningbo.media.core.service.impl.BaseServiceImpl;
import ningbo.media.dao.FavoriteDao;
import ningbo.media.service.FavoriteService;

@Service("favoriteService")
public class FavoriteServiceImpl extends BaseServiceImpl<Favorite, Integer>
		implements FavoriteService {

	private FavoriteDao favoriteDao ;
	
	@Autowired
	public FavoriteServiceImpl(@Qualifier("favoriteDao")FavoriteDao favoriteDao) {
		super(favoriteDao);
		this.favoriteDao = favoriteDao ;
	}

	public Favorite findFavoriteById(String userId, String locationId) {
		return favoriteDao.findFavoriteById(userId, locationId);
	}

	public boolean isExistsFavorite(String userId, String locationId) {
		boolean flag = false ;
		final String hql = "from Favorite model where model.userId = ? and model.locationId = ? ";
		Favorite favorite = null;
		if ("".equals(userId) || "".equals(locationId) || userId == null
				|| locationId == null) {
			return flag;
		}
		try {
			favorite = (Favorite) findUnique(hql, Integer.valueOf(userId),
					Integer.valueOf(locationId));
			if(favorite != null){
				flag = true ;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return flag;
		}
		return flag ;
	}

}
