package ningbo.media.service.impl;

import java.util.List;

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

	private LocationDao locationDao;
	private static final double DEFAULT_AREA = 5.0E-4;

	@Autowired
	public LocationServiceImpl(@Qualifier("locationDao") LocationDao locationDao) {
		super(locationDao);
	}

	public List<Location> getNearByLocations(double latitude, double longitude,
			double area) {
		List<Location> list = null;
		if (area < 0.0) {
			area = DEFAULT_AREA;
		}
		if (latitude < 0.0 || longitude < 0.0) {
			return null;
		}
		double frontLat = latitude + area;
		double backLat = latitude - area;
		double frontlon = longitude + area;
		double backlon = longitude - area;

		String hql = "from Location as m where m.longitude > ? and m.longitude < ? and m.latitude > ? and m.latitude < ? ";
		try {
			list = locationDao.findByHql(hql, backlon, frontlon,backLat,frontLat);
		} catch (Exception ex) {
			ex.printStackTrace() ;
		}
		return list;
	}

}
