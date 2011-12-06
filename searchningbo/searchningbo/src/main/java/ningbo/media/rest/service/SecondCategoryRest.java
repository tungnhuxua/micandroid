package ningbo.media.rest.service;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import ningbo.media.bean.SecondCategory;
import ningbo.media.service.SecondCategoryService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Path("/category/second")
@Component
@Scope("request")
public class SecondCategoryRest {

	@Resource
	private SecondCategoryService secondCategoryService ;
	
	@Path("/showAll")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<SecondCategory> getAllSecondCategory(){
		return secondCategoryService.getAll() ;
	}
	
	@Path("/show/subCategory")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<SecondCategory> getCategoryByFirst(String value,@Context HttpServletRequest request){
		String propertyName = "firstCategoryId" ;
		String param = request.getParameter("value") ;
		Integer id = Integer.valueOf(param) ;
		return secondCategoryService.getList(propertyName,id ) ;
	}
	

}
