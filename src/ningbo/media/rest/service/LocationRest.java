package ningbo.media.rest.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import ningbo.media.bean.Location;
import ningbo.media.bean.SecondCategory;
import ningbo.media.rest.util.Constant;
import ningbo.media.rest.util.FileUpload;
import ningbo.media.service.LocationService;
import ningbo.media.service.SecondCategoryService;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.sun.jersey.multipart.FormDataBodyPart;
import com.sun.jersey.multipart.FormDataMultiPart;

@Path("/location")
@Component
@Scope("request")
public class LocationRest {
	
	@Resource
	private LocationService locationService ;
	
	
	@Resource
	private SecondCategoryService secondCategoryService ;
	
	
	
	@Path("/showAll")
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	public List<Location> getAllLocations(){
		return locationService.getAll() ;
	}
	
	
	@Path("/show/{id : \\d+}")
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	public Location getLocationById(@PathParam("id")String id){
		if(id == null){
			return null ;
		}
		return locationService.get(Integer.valueOf(id)) ;
	}
	
	@Path("/category/{id : \\d+}")
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	public List<Location> getAllLocationsBySecondCategory(@PathParam("id")String id){
		if(id == null){
			return null ;
		}
		SecondCategory secondCategory = secondCategoryService.get(Integer.valueOf(id)) ;
		if(secondCategory == null){
			return new ArrayList<Location>() ;
		}
		List<Location> listLocation = secondCategory.getLocations() ;
		int size = listLocation.size() ;
		if(size == 1){
			listLocation.add(new Location()) ;
		}
		return listLocation ;
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
	@Path("/add")
	@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_JSON)
	public String addLocation(FormDataMultiPart form,
			@Context HttpServletRequest request) throws JSONException {
		String key = form.getField("key").getValue();
		JSONObject json = new JSONObject();
		boolean b = false;
		Location location = new Location();
		// the key is wrong
		if (key.isEmpty()) {
			json.put("code", "1");
			return json.toString();

		} else if (!Constant.KEY.equals(key)) {
			json.put("code", "2");
			return json.toString();
		}
		String name_en = form.getField("name_en").getValue();
		String name_cn = form.getField("name_cn").getValue();
		String address_en = form.getField("address_en").getValue();
		String address_cn = form.getField("address_cn").getValue();
		String telephone = form.getField("telephone").getValue();
		String lon = form.getField("longitude").getValue();
		String lat = form.getField("latitude").getValue();
		String cid = form.getField("category_id").getValue();
		FormDataBodyPart part = form.getField("photo_path");
		String fileName = part.getContentDisposition().getFileName();
		location.setName_cn(name_cn);
		location.setName_en(name_en);
		location.setAddress_cn(address_cn);
		location.setAddress_en(address_en);
		location.setTelephone(telephone);
		if(!lon.isEmpty()) location.setLongitude(Double.parseDouble(lon));
		if(!lat.isEmpty()) location.setLatitude(Double.parseDouble(lat));
		if(cid.isEmpty()){
			return json.put("code", "3").toString();
		}
		
		SecondCategory sc = secondCategoryService.get(Integer.valueOf(cid)) ;
		if(sc == null){
			return json.put("code", "4").toString() ;
		}else{
			location.getSecondCategorys().add(sc) ;
		}
		
		try {
			location.setPhoto_path(FileUpload.upload(part, fileName,"location_head", request));
			locationService.save(location);
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
	 * @throws JSONException 
	 */
	@Path("/edit")
	@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_JSON)
	public String editLocation(FormDataMultiPart form,
			@Context HttpServletRequest request) throws JSONException {
		String key = form.getField("key").getValue();
		JSONObject json = new JSONObject();
		boolean b = false;
		// the key is wrong
		if (key.isEmpty()) {
			json.put("code", "1");
			return json.toString();

		} else if (!Constant.KEY.equals(key)) {
			json.put("code", "2");
			return json.toString();
		}
		String pk = form.getField("id").getValue();
		if(pk.isEmpty()){
			return json.put("id", "null").toString();
		}
		Integer id = Integer.parseInt(pk);
		String name_en = form.getField("name_en").getValue();
		String name_cn = form.getField("name_cn").getValue();
		String address_en = form.getField("address_en").getValue();
		String address_cn = form.getField("address_cn").getValue();
		String telephone = form.getField("telephone").getValue();
		String lon = form.getField("longitude").getValue();
		String lat = form.getField("latitude").getValue();
		String cid = form.getField("category_id").getValue();
		FormDataBodyPart part = form.getField("photo_path");
		String fileName = part.getContentDisposition().getFileName();
		Location location = locationService.get(id);
		location.setName_cn(name_cn);
		location.setName_en(name_en);
		location.setAddress_cn(address_cn);
		location.setAddress_en(address_en);
		location.setTelephone(telephone);
		if(!lon.isEmpty()) location.setLongitude(Double.parseDouble(lon));
		if(!lat.isEmpty()) location.setLatitude(Double.parseDouble(lat));
		if(cid.isEmpty()){
			return json.put("cid", false).toString();
		}
		SecondCategory sc = new SecondCategory();
		sc.setId(Integer.parseInt(cid));
		//location.setSecondCategory(sc);
		try {
			location.setPhoto_path(FileUpload.upload(part, fileName,"location_head", request));
			locationService.save(location);
			return json.put("success", !b).toString();
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

}
