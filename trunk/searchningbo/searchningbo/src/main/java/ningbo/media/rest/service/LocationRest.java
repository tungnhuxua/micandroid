package ningbo.media.rest.service;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import ningbo.media.bean.Location;
import ningbo.media.bean.SecondCategory;
import ningbo.media.rest.util.Constant;
import ningbo.media.service.LocationService;
import ningbo.media.service.SecondCategoryService;
import org.json.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Path("/location")
@Component
@Scope("request")
public class LocationRest {

	@Resource
	private LocationService locationService;

	@Resource
	private SecondCategoryService secondCategoryService;

	@Path("/showAll")
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public List<Location> getAllLocations() {
		return locationService.getAll();
	}

	@Path("/show/{id : \\d+}")
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Location getLocationById(@PathParam("id") String id) {
		if (id == null) {
			return null;
		}
		return locationService.get(Integer.valueOf(id));
	}

	@Path("/category/{id : \\d+}")
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public List<Location> getAllLocationsBySecondCategory(
			@PathParam("id") String id) {
		if (id == null) {
			return null;
		}
		SecondCategory secondCategory = secondCategoryService.get(Integer
				.valueOf(id));
		if (secondCategory == null) {
			return new ArrayList<Location>();
		}
		List<Location> listLocation = secondCategory.getLocations();
		int size = listLocation.size();
		if (size == 1) {
			listLocation.add(new Location());
		}
		return listLocation;
	}

	/**
	 * 
	 * @FormParam("key") String key,
	 * @FormParam("name_en") String nameEN,
	 * @FormParam("name_cn") String nameCN,
	 * @FormParam("address_en") String addressEN,
	 * @FormParam("address_cn") String addressCN,
	 * @FormParam("telephone") String telephone,
	 * @FormParam("gps_lat") String gpsLat,
	 * @FormParam("gps_lon") String gpsLon,
	 * @FormParam("category2Id")String category2Id,
	 * 
	 * @FormParam("photoLocation") InputStream inputStream,
	 * @FormParam("photoLocation") FormDataContentDisposition fileDetail
	 * */
	@Path("/add")
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces({ MediaType.APPLICATION_JSON })
	public String addLocation(@FormParam("key") String key,
			@FormParam("name_en") String nameEN,
			@FormParam("name_cn") String nameCN,
			@FormParam("address_en") String addressEN,
			@FormParam("address_cn") String addressCN,
			@FormParam("telephone") String telephone,
			@FormParam("gps_lat") String gpsLat,
			@FormParam("gps_lon") String gpsLon,
			@FormParam("category2Id") String category2Id) {
		JSONObject json = new JSONObject();
		// Map<String,String> maps = fileDetail.getParameters() ;
		// String key = maps.get("key") ;
		try {
			if ("".equals(key) || key == null) {
				json.put("key", "Key Is NULL!");
				return json.toString();
			}
			if (!Constant.KEY.equals(key)) {
				json.put("key", "Key Is Invalid!");
				return json.toString();
			}

		} catch (Exception ex) {
			// NumberFormatException
		}

		return null;
	}

}
