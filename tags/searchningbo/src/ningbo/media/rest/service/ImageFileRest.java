package ningbo.media.rest.service;

import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import ningbo.media.bean.ImageFile;
import ningbo.media.bean.ImageInformation;
import ningbo.media.bean.LinkUserAndImage;
import ningbo.media.bean.PhotoType;
import ningbo.media.rest.util.Constant;
import ningbo.media.rest.util.FileHashCode;
import ningbo.media.rest.util.JSONCode;
import ningbo.media.rest.util.StringUtils;
import ningbo.media.service.ImageFileService;
import ningbo.media.service.ImageInformationService;
import ningbo.media.service.LinkUserAndImageService;
import ningbo.media.service.PhotoTypeService;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataMultiPart;
import com.sun.jersey.multipart.FormDataParam;

@Path("/image")
@Component
@Scope("request")
public class ImageFileRest {

	@Resource
	private ImageFileService imageFileService;

	@Resource
	private ImageInformationService imageInformationService;

	@Resource
	private LinkUserAndImageService linkUserAndImageService;

	@Resource
	private PhotoTypeService photoTypeService;

	@Context
	private UriInfo uriInfo;

	@Path("/add")
	@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_JSON)
	public String addImageFile(FormDataMultiPart form,
			@FormDataParam("file") InputStream uploadFile,
			@FormDataParam("file") FormDataContentDisposition fileDetail)
			throws JSONException, URISyntaxException {

		JSONObject json = new JSONObject();
		String key = form.getField("key").getValue();
		String userId = form.getField("userId").getValue();
		String deviceId = form.getField("deviceId").getValue();
		String typeId = form.getField("typeId").getValue();
		String locationId = form.getField("locationId").getValue() ;

		if (!StringUtils.hasText(key)) {
			json.put(Constant.CODE, JSONCode.isNull);
			return json.toString();
		} else if (!key.equals(Constant.KEY)) {
			json.put(Constant.CODE, JSONCode.isInvalid);
			return json.toString();
		}
		if (!(StringUtils.hasText(userId))
				&& !(StringUtils.hasText(deviceId))) {
			json.put(Constant.CODE, JSONCode.inputInvalid);
			return json.toString();
		}

		if (!StringUtils.hasText(fileDetail.getFileName())) {
			json.put(Constant.CODE, JSONCode.noUploadFile);
			return json.toString();
		}

		if (!StringUtils.hasText(typeId)) {
			json.put(Constant.CODE, JSONCode.typeInvalid);
			return json.toString();
		}
		
		if(!StringUtils.hasText(locationId)){
			json.put(Constant.CODE, JSONCode.isNull);
			return json.toString();
		}

		PhotoType type = photoTypeService.get(Integer.valueOf(typeId));
		if (type == null) {
			json.put(Constant.CODE, JSONCode.inputInvalid);
			return json.toString();
		}

		// temp saved.
		String fileName = fileDetail.getFileName();
		StringBuffer sb = new StringBuffer();
		String tempPath = FileHashCode.makeTempFileDir();
		sb.append(tempPath).append(fileName);

		// save image information.
		ImageInformation inforImage = new ImageInformation();
		Map<String, Object> m = FileHashCode.writeToFile(uploadFile,
				sb.toString());

		inforImage.setImageWidth(Double.valueOf(m.get(Constant.WIDTH)
				.toString()));
		inforImage.setImageHeight(Double.valueOf(m.get(Constant.HEIGHT)
				.toString()));
		inforImage.setImageSize(Long.valueOf(m.get(Constant.FILESIZE)
				.toString()));

		String uuid = String.valueOf(m.get(Constant.UUID));
		Integer inforId = imageInformationService.save(inforImage);// save image
																	// information.

		ImageFile imageFile = new ImageFile();
		imageFile.setFileName(fileName);
		imageFile.setUploadTime(new Date());
		imageFile.setPhotoType(type);
		imageFile.setImageInfo(inforImage);
		imageFile.setUuid(uuid);
		Integer imageFileId = imageFileService.save(imageFile);// save image
																// file.
		LinkUserAndImage linkTable = new LinkUserAndImage();
		try {
			if ("".equals(userId) || userId == null) {// DeviceId User Upload
														// Image.
				linkTable.setDeviceId(deviceId);
				linkTable.setImageId(imageFileId);
			} else {// Register User Upload Image.
				linkTable.setUserId(Integer.valueOf(userId));
				linkTable.setImageId(imageFileId);
			}

			linkUserAndImageService.save(linkTable); // save link table.
		} catch (Exception ex) {
			ex.printStackTrace();
			imageInformationService.delete(inforId);
			imageFileService.delete(imageFileId);

			json.put(Constant.CODE, JSONCode.throwException);
			return json.toString();
		}

		System.out.println(uriInfo.getAbsolutePath()) ;
		System.out.println(uriInfo.getPath()) ;
		System.out.println(uriInfo.getBaseUri()) ;
		System.out.println(uriInfo.getRequestUri()) ;
		System.out.println(getClass().getClassLoader().getResource("").toURI().getPath()) ; 
		json.put(Constant.CODE, JSONCode.success);
		return json.toString();
	}

	@Path("/get")
	@GET
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_OCTET_STREAM)
	public List<LinkUserAndImage> getImageFile() {
		//
		return null;
	}

	@Path("/delete")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public String deleteImageFile(@FormParam("fileId") String fileId,
			@FormParam("key") String key) throws JSONException {

		JSONObject json = new JSONObject();
		if ("".equals(fileId) || fileId == null) {
			json.put(Constant.CODE, JSONCode.isNull);
			return json.toString();
		}
		
		try {
			ImageFile imageFile = imageFileService.get(Integer.valueOf(fileId));
			// delete ext information.
			imageInformationService.delete(imageFile.getImageInfo());
			String uuid = imageFile.getUuid();
			String delete = FileHashCode.getUuidPath(uuid);
			System.out.println(delete);
			// delete file
			FileHashCode.delFile(delete);

			LinkUserAndImage link = linkUserAndImageService.get("imageId",
					Integer.valueOf(fileId));

			if (link != null) {
				// delete link information
				linkUserAndImageService.delete(link);
			}
			// delete file
			imageFileService.delete(imageFile);
		} catch (Exception ex) {
			ex.printStackTrace();
			json.put(Constant.CODE, JSONCode.fail);
			return json.toString();
		}
		json.put(Constant.CODE, JSONCode.success);
		return json.toString();
	}

}
