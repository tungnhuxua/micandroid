package ningbo.media.rest.service;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;

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
	public List<SecondCategory> getCategoryByFirst(String value,@Context ServletContext ctx){
		String propertyName = "firstCategoryId" ;
		System.out.println(ctx.getAttribute("value")) ;
		return secondCategoryService.getList(propertyName, 1) ;
	}
}
