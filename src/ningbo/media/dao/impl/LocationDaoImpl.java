package ningbo.media.dao.impl;

import ningbo.media.bean.Location;
import ningbo.media.core.dao.impl.BaseDaoImpl;
import ningbo.media.dao.LocationDao;

import org.springframework.stereotype.Repository;

@Repository("locationDao")
public class LocationDaoImpl extends BaseDaoImpl<Location, Integer> implements
		LocationDao {

	public LocationDaoImpl() {
		super(Location.class);
	}
}
