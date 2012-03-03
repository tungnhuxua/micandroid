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

import ningbo.media.bean.Favorite;
import ningbo.media.bean.Location;
import ningbo.media.bean.SystemUser;
import ningbo.media.bean.TempUser;
import ningbo.media.rest.util.Constant;
import ningbo.media.service.FavoriteService;
import ningbo.media.service.LocationService;
import ningbo.media.service.SystemUserService;
import ningbo.media.service.TempUserService;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Path("/favorite")
@Component
@Scope("request")
public class FavoriteRest {

	@Resource
	private FavoriteService favoriteService;

	@Resource
	private SystemUserService systemUserService;

	@Resource
	private LocationService locationService;

	@Resource
	private TempUserService tempUserService;

	@Path("/location/count/{locationId : \\d+}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getFavoriteCountByLocationId(
			@PathParam("locationId") String locationId) {
		List<Favorite> list = favoriteService.getList("locationId",
				Integer.valueOf(locationId));
		if (list == null) {
			return String.valueOf(0);
		} else {
			return String.valueOf(list.size());
		}
	}

	@Path("/add")
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public String addUserFavorite(@FormParam("userId") String userId,
			@FormParam("locationId") String locationId,
			@FormParam("deviceId") String deviceId, @FormParam("key") String key)
			throws JSONException {
		JSONObject tempJson = new JSONObject();
		try {
			TempUser tempUser = null;
			Favorite fav = null;
			if ("".equals(key) || key == null) {
				tempJson.put("key", "Key is NULL!");
				return tempJson.toString();
			} else if (!Constant.KEY.equals(key)) {
				tempJson.put("key", "Key Invalid！");
				return tempJson.toString();
			}
			if ("".equals(locationId) || locationId == null) {
				tempJson.put("locationId", "LocationId is NULL");
				return tempJson.toString();
			}
			Location loc = locationService.get(Integer.valueOf(locationId));
			if (loc == null) {
				tempJson.put("locationId", "LocationId is not exists.");
				return tempJson.toString();
			}

			if (userId != null && userId.trim().length() > 0) {// User Is
																// Exists.
				SystemUser systemUser = systemUserService.get(Integer
						.valueOf(userId));
				if (systemUser == null) {
					tempJson.put("userId", "UserId is not exists.");
					return tempJson.toString();
				}

				fav = favoriteService.findFavoriteById(userId, locationId);
				if (fav != null) {
					tempJson.put("Repeat", "You have collected！");
					return tempJson.toString();
				}

				fav = new Favorite();
				fav.setUserId(Integer.valueOf(userId));
				fav.setLocationId(Integer.valueOf(locationId));
				favoriteService.save(fav);
			} else {// User Is Not Exists.Use Temp User.
				if ("".equals(deviceId) || deviceId == null) {
					tempJson.put("deviceId-ERROR",
							"Please get the serial number of the current equipment");
					return tempJson.toString();
				} else {
					fav = favoriteService.findFavoriteByDeviceId(deviceId,
							locationId);
					if (fav != null) {
						tempJson.put("Repeat", "You have collected！");
						return tempJson.toString();
					}

					fav = new Favorite();
					fav.setLocationId(Integer.valueOf(locationId));
					fav.setDeviceId(deviceId);
					favoriteService.save(fav);

					boolean temp = tempUserService.isExistsDeviceId(deviceId);
					if (!temp) {
						tempUser = new TempUser();
						tempUser.setDeviceId(deviceId);
						tempUser.setTempKey(Constant.KEY);
						tempUserService.save(tempUser);
					}
				}
			}

			tempJson.put("success", "SUCCESS!");
			return tempJson.toString();
		} catch (NumberFormatException ex) {
			tempJson.put("InputError", "Input Invaid!");
			return tempJson.toString();
		}

	}

	@Path("/location/getAll")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public List<Favorite> getFavoriteListByUserIdOrDeviceId(
			@FormParam("key") String key,
			@FormParam("deviceId") String deviceId,
			@FormParam("userId") String userId) {
		List<Favorite> list = new ArrayList<Favorite>();

		try {
			if ("".equals(key) || key == null) {
				list.add(new Favorite("Key IS NULL!"));
				return list;
			} else if (!Constant.KEY.equals(key)) {
				list.add(new Favorite("Input Invaid!"));
				return list;
			}

			if (("".equals(deviceId) || deviceId == null)
					&& ("".equals(userId) || userId == null)) {
				list.add(new Favorite("No Input!"));
				return list;
			} else if ((deviceId != null && deviceId.trim().length() > 0)
					&& ("".equals(userId) || userId == null)) {
				list = favoriteService.getListFavoriteByDeviceId(deviceId);
				if (list == null || list.size() < 1) {
					list = new ArrayList<Favorite>();
					list.add(new Favorite("DeviceId Is Not Exists!"));
					return list;
				}
			} else {
				list = favoriteService.getListFavoriteByUserId(Integer
						.valueOf(userId));
				if (list == null || list.size() < 1) {
					list = new ArrayList<Favorite>();
					list.add(new Favorite("UserId Is Not Exists!"));
					return list;
				}

			}

		} catch (NumberFormatException ex) {
			list.add(new Favorite("Input Invaid!"));
			return list;
		} catch (NullPointerException ex) {
			list.add(new Favorite("No Exists!"));
			return list;
		}
		// list.add(new Favorite("SUCCESS!"));
		return list;
	}

}
