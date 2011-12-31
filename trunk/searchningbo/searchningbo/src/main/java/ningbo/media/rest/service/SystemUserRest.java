package ningbo.media.rest.service;

import java.util.Date;
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
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces("application/json")
	public SystemUser addSystemUser(@FormParam("email") String email,
			@FormParam("username") String username,
			@FormParam("password") String password) {

		SystemUser u = new SystemUser() ;
		u.setEmail(email) ;
		u.setPassword(password) ;
		u.setUsername(username) ;
		u.setDate_time(new Date()) ;
		try {
			systemUserService.save(u);
			return u;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	@Path("/verification")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public SystemUser verificationUser(@QueryParam("email") String email,
			@QueryParam("password") String password) {
		SystemUser u = systemUserService.verificationUser(email, password);
		if(u == null){
			return new SystemUser() ;
		}
		return u ;
	}

	@Path("/check/{property}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String isExist(@PathParam("property") String property,
			@QueryParam("value") String value) {
		Boolean flag = systemUserService.isExist(property, value);
		return flag.toString();
	}

}
