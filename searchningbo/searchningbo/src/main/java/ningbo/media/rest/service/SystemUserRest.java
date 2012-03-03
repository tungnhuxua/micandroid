package ningbo.media.rest.service;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import ningbo.media.bean.SystemUser;
import ningbo.media.rest.util.Constant;
import ningbo.media.rest.util.FileUpload;
import ningbo.media.service.SystemUserService;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.sun.jersey.multipart.FormDataBodyPart;
import com.sun.jersey.multipart.FormDataMultiPart;

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
	public SystemUser getSystemUserById(@PathParam("id") Integer id) {
		SystemUser u = systemUserService.get(id);
		return u;
	}

	@Path("/showAll")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<SystemUser> getAll() {
		return systemUserService.getAll();
	}

	/**
	 * This method is judge whether the username and email is exists or not If
	 * exists returns true, and the register can't save the information
	 * 
	 * @param form
	 * @param request
	 * @return
	 * @throws JSONException 
	 */
	@Path("/register")
	@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_JSON)
	public String addSystemUser(FormDataMultiPart form,
			@Context HttpServletRequest request) throws JSONException {
		String username = form.getField("username").getValue();
		String password = form.getField("password").getValue();
		String email = form.getField("email").getValue();
		String key = form.getField("key").getValue();
		FormDataBodyPart part = form.getField("head_photo");
		String fileName = part.getContentDisposition().getFileName();
		JSONObject json = new JSONObject();
		boolean b = false;
		// the key is wrong
		if ("".equals(key) || key == null) {
			json.put("error", "Key Is NUll!");
			return json.toString();

		} else if (!Constant.KEY.equals(key)) {
			json.put("error", "Key Is Invalid!");
			return json.toString();
		}
		// return true if the username is exist
		final boolean bool_username = systemUserService.isExist("username",
				username);
		try {
			if (bool_username)
				return json.put("username", b).toString();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		// return true if the email is exists
		final boolean bool_email = systemUserService.isExist("email", email);
		try {
			if (bool_email)
				return json.put("email", b).toString();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		// judge whether the username and email is exists or not
		SystemUser u = new SystemUser();
		u.setEmail(email);
		u.setPassword(password);
		u.setUsername(username);
		u.setDate_time(new Date());
		u.setIsManager(false);
		try {
			u.setPhoto_path(FileUpload.upload(part, fileName, request));
			systemUserService.save(u);
			return json.put("success", !b).toString();
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	/**
	 * This method is judge whether the username and email is exists or not If
	 * exists returns true, and the register can't save the information
	 * 
	 * @param form
	 * @param request
	 * @return
	 */
	@Path("/edit")
	@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_JSON)
	public String editSystemUser(FormDataMultiPart form,
			@Context HttpServletRequest request) throws JSONException{
		Integer id = Integer.parseInt(form.getField("id").getValue());
		String username = form.getField("username").getValue();
		String password = form.getField("password").getValue();
		String email = form.getField("email").getValue();
		String key = form.getField("key").getValue();
		FormDataBodyPart part = form.getField("head_photo");
		String fileName = part.getContentDisposition().getFileName();
		JSONObject json = new JSONObject();
		boolean b = false;
		// the key is wrong
		if ("".equals(key) || key == null) {
			json.put("error", "Key Is NUll!");
			return json.toString();

		} else if (!Constant.KEY.equals(key)) {
			json.put("error", "Key Is Invalid!");
			return json.toString();
		}
		// return true if the username is exist
		final boolean bool_username = systemUserService.isExist("username",
				username);
		if (bool_username) return json.put("username", b).toString();
		// return true if the email is exists
		final boolean bool_email = systemUserService.isExist("email", email);
		if (bool_email) return json.put("email", b).toString();
		// judge whether the username and email is exists or not
		SystemUser u = new SystemUser();
		u.setId(id);
		u.setEmail(email);
		u.setPassword(password);
		u.setUsername(username);
		u.setDate_time(new Date());
		u.setIsManager(false);
		try {
			u.setPhoto_path(FileUpload.upload(part, fileName, request));
			systemUserService.update(u);
			return json.put("success", !b).toString();
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	@Path("/verification")
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public String verificationUser(@FormParam("username") String username,
			@FormParam("password") String password,
			@FormParam("key") String key, @FormParam("deviceId") String deviceId)
			throws JSONException {
		JSONObject json = new JSONObject();
		if ("".equals(key) || key == null) {
			json.put("error", "Key Is NUll!");
			return json.toString();

		} else if (!Constant.KEY.equals(key)) {
			json.put("error", "Key Is Invalid!");
			return json.toString();
		} else {
			return null;
		}

		// SystemUser u = systemUserService.verificationUser(email, password);
		// if(u == null){
		// return new SystemUser() ;
		// }
		// return u ;
	}

	@Path("/check/{property}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String isExist(@PathParam("property") String property,
			@QueryParam("value") String value) {
		Boolean flag = systemUserService.isExist(property, value);
		return flag.toString();
	}

	@Path("/login")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public String login(@FormParam("username") String username,
			@FormParam("password") String password, @FormParam("key") String key) throws JSONException {
		Boolean flag = false;
		JSONObject json = new JSONObject();
		if ("".equals(key) || key == null) {
			json.put("error", "Key Is NUll!");
			return json.toString();
		} else if (!Constant.KEY.equals(key)) {
			json.put("error", "Key Is Invalid!");
			return json.toString();
		}
		flag = systemUserService.login(username, password);
		return flag.toString();
	}

}
