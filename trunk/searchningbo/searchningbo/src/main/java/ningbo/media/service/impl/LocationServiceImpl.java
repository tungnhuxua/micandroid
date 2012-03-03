package ningbo.media.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ningbo.media.bean.Location;
import ningbo.media.core.service.impl.BaseServiceImpl;
import ningbo.media.dao.LocationDao;
import ningbo.media.service.LocationService;

@Service("locationService")
public class LocationServiceImpl extends BaseServiceImpl<Location, Integer>
		implements LocationService {

	@Autowired
	public LocationServiceImpl(@Qualifier("locationDao")LocationDao  locationDao) {
		super(locationDao);
	}

}
