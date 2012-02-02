package ningbo.media.rest.service;

import java.util.List;

import javax.annotation.Resource;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import ningbo.media.bean.Favorite;
import ningbo.media.service.FavoriteService;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Path("/favorite")
@Component
@Scope("request")
public class FavoriteRest {

	@Resource
	private FavoriteService favoriteService;

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
	public Favorite addUserFavorite(@FormParam("userId") String userId,
			@FormParam("locationId") String locationId,
			@FormParam("locationName") String locationName) {

		Favorite flag = favoriteService.findFavoriteById(userId, locationId);
		if (flag != null) { // 如果存在则不添加.
			return flag;
		}

		Favorite f = new Favorite();
		f.setUserId(Integer.valueOf(userId));
		f.setLocationId(Integer.valueOf(locationId));
		f.setLocationName(locationName);

		try {
			favoriteService.save(f);
			return f;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	@Path("/check/{userId : \\d+}/{locationId : \\d+}")
	@GET
	public String isExistsFavorite(@PathParam("userId") String userId,
			@PathParam("locationId") String locationId) {
		boolean flag = favoriteService.isExistsFavorite(userId, locationId) ;
		System.out.println(flag) ;
		return String.valueOf(flag) ;
	}

}
