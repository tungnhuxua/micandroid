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

import ningbo.media.bean.Favorite;
import ningbo.media.bean.SystemUser;
import ningbo.media.mailUtil.SendMail;
import ningbo.media.rest.util.CodeConstant;
import ningbo.media.rest.util.Constant;
import ningbo.media.rest.util.FileUpload;
import ningbo.media.rest.util.MD5;
import ningbo.media.service.FavoriteService;
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
	
	@Resource
	private FavoriteService favoriteService;

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
//		FormDataBodyPart part = form.getField("head_photo");
//		String fileName = part.getContentDisposition().getFileName();
		JSONObject json = new JSONObject();
		// the key is wrong
		if (key.isEmpty()) {
			json.put(CodeConstant.CODE, CodeConstant.CODE_NULL);
			return json.toString();

		} else if (!Constant.KEY.equals(key)) {
			json.put(CodeConstant.CODE, CodeConstant.CODE_INVALID);
			return json.toString();
		}

		if ("".equals(username) || username == null) {
			json.put(CodeConstant.CODE, CodeConstant.CODE_NULL);
			return json.toString();
		}
		if ("".equals(email) || email == null) {
			json.put(CodeConstant.CODE, CodeConstant.CODE_NULL);
			return json.toString();
		}
		if ("".equals(password) || password == null) {
			json.put(CodeConstant.CODE, CodeConstant.CODE_NULL);
			return json.toString();
		}

		SystemUser u = new SystemUser();
		u.setEmail(email);
		u.setPassword(MD5.hash(password));
		u.setUsername(username);
		u.setDatetime(new Date());
		u.setIsManager(false);
		u.setStatus(false);
		try {
//			u.setPhoto_path(FileUpload.upload(part, fileName, "user_head",request));
			Integer id = systemUserService.save(u);
			SendMail.SendMailTo(SendMail.SEND_HTML, email, id, key);
			return json.put(CodeConstant.CODE, CodeConstant.CODE_SUCCESS).toString();
		} catch (Exception ex) {
			ex.printStackTrace();
			json.put(CodeConstant.CODE, CodeConstant.CODE_EXCEPTION);
			return json.toString();
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
			@Context HttpServletRequest request) throws JSONException {
		Integer id = Integer.parseInt(form.getField("id").getValue());
		String username = form.getField("username").getValue();
		String password = form.getField("password").getValue();
		String email = form.getField("email").getValue();
		String key = form.getField("key").getValue();
		FormDataBodyPart part = form.getField("head_photo");
		String fileName = part.getContentDisposition().getFileName();
		JSONObject json = new JSONObject();
		// the key is wrong
		if (key.isEmpty()) {
			json.put(CodeConstant.CODE, CodeConstant.CODE_NULL);
			return json.toString();

		} else if (!Constant.KEY.equals(key)) {
			json.put(CodeConstant.CODE, CodeConstant.CODE_INVALID);
			return json.toString();
		}
		SystemUser u = new SystemUser();
		u.setId(id);
		u.setEmail(email);
		u.setPassword(password);
		u.setUsername(username);
		u.setDatetime(new Date());
		u.setIsManager(false);
		try {
			u.setPhoto_path(FileUpload.upload(part, fileName, "user_haed",
					request));
			systemUserService.update(u);
			return json.put(CodeConstant.CODE, CodeConstant.CODE_SUCCESS).toString();
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	/**
	 * @param form
	 * @param request
	 * @return
	 */
	@Path("/verifystatus/{id : \\d+}/{key : \\w+}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String verifystatus(@PathParam("id") Integer id,
			@PathParam("key") String key) throws JSONException {
		JSONObject json = new JSONObject();
		// the key is wrong
		if (key.isEmpty()) {
			json.put(CodeConstant.CODE, CodeConstant.CODE_NULL);
			return json.toString();

		} else if (!Constant.KEY.equals(key)) {
			json.put(CodeConstant.CODE, CodeConstant.CODE_INVALID);
			return json.toString();
		}
		SystemUser u = systemUserService.get(id);
		u.setStatus(true);
		try {
			systemUserService.update(u);
			return json.put(CodeConstant.CODE, CodeConstant.CODE_SUCCESS).toString();
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
		if (key.isEmpty()) {
			json.put(CodeConstant.CODE, CodeConstant.CODE_NULL);
			return json.toString();

		} else if (!Constant.KEY.equals(key)) {
			json.put(CodeConstant.CODE, CodeConstant.CODE_INVALID);
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

	/**
	 * 判断是否已经存在
	 * @param property
	 * @param value
	 * @return
	 */
	@Path("/check/{property}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String isExist(@PathParam("property") String property,
			@PathParam("value") String value) {
		Boolean flag = systemUserService.isExist(property, value);
		return flag.toString();
	}

	@Path("/login")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public String login(@FormParam("username") String username,
			@FormParam("password") String password,
			@FormParam("key") String key,
			@FormParam("device_id") String device_id) throws JSONException {
		JSONObject json = new JSONObject();
		if (key.isEmpty()) {
			json.put(CodeConstant.CODE, CodeConstant.CODE_NULL);
			return json.toString();
		} else if (!Constant.KEY.equals(key)) {
			json.put(CodeConstant.CODE, CodeConstant.CODE_INVALID);
			return json.toString();
		}
		if (username.isEmpty()) {
			return json.put(CodeConstant.CODE, CodeConstant.CODE_NULL).toString();
		}
		if (password.isEmpty()) {
			return json.put(CodeConstant.CODE, CodeConstant.CODE_NULL).toString();
		}

		Integer user_id = systemUserService.login(username, password);
		if (0 != user_id) {
			json.put(CodeConstant.CODE, CodeConstant.CODE_SUCCESS);
		} else {
			json.put(CodeConstant.CODE, CodeConstant.CODE_FAILURE);
		}
		
		//得到所有为userid为null的favorite
		List<Favorite> listFav = favoriteService.findFavoriteByDeviceAndUser(device_id);
		if(null != listFav && listFav.size() > 0){
			for (int i = 0; i < listFav.size(); i++) {
				Favorite entity = listFav.get(i);
				entity.setId(listFav.get(i).getId());
				entity.setUserId(user_id);
				favoriteService.update(entity);
			}
		}
		return json.toString();
	}

}
