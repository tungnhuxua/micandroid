package ningbo.media.rest.service;

import java.util.List;

import javax.annotation.Resource;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
//import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
//import javax.ws.rs.core.UriInfo;

import ningbo.media.bean.SystemUser;
import ningbo.media.service.SystemUserService;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Path("/user")
@Component
@Scope("request")
public class SystemUserRest{
	
	@Resource
	private SystemUserService systemUserService ;
	
	//@Context
	//private UriInfo info ;

	@Path("/show/{id : \\d+}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public SystemUser getSystemUserByName(@PathParam("id")Integer id) {
			SystemUser u = systemUserService.get(id) ;
			System.out.println(u.toJson()) ;
			return u ;
	}
	
	@Path("/showAll")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<SystemUser> getAll(){
		return systemUserService.getAll() ;
	}

	@Path("/add")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public String addSystemUser(String userJson) {
		return null ;
	}
	

}
