package ningbo.media.rest.service;

import java.util.List;

import javax.annotation.Resource;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import ningbo.media.bean.SystemUser;
import ningbo.media.service.SystemUserService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Path("/user")
@Component
@Scope("request")
public class SystemUserRest {

	@Resource
	private SystemUserService systemUserService;

	// @Context
	// private UriInfo info ;

	/***
	 * description: access:/user/show/{id} example:/user/show/1 [return json.]
	 * request method:get
	 * 
	 * @param id
	 * @return SystemUser
	 */
	@Path("/show/{id : \\d+}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public SystemUser getSystemUserByName(@PathParam("id") Integer id) {
		SystemUser u = systemUserService.get(id);
		return u;
	}

	@Path("/showAll")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<SystemUser> getAll() {
		return systemUserService.getAll();
	}

	@Path("/register")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addSystemUser(String userJson) {
		SystemUser u = SystemUser.fromJson(userJson);
		try {
			systemUserService.save(u);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return Response.ok().build();
	}

	@Path("/verification")
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public SystemUser verificationUser(@QueryParam("email") String email,
			@QueryParam("password") String password) {
		return systemUserService.verificationUser(email, password);
	}

	@Path("/check/{property}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String isExist(@PathParam("property") String property,@QueryParam("value")String value) {
		Boolean flag = systemUserService.isExist(property, value);
		return flag.toString();
	}

}
