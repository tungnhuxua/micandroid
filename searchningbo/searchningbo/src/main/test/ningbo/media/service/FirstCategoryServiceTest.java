package ningbo.media.service;

import java.util.List;

import javax.annotation.Resource;

import ningbo.media.BaseTest;
import ningbo.media.bean.FirstCategory;

import org.junit.Test;

public class FirstCategoryServiceTest extends BaseTest{
	
	@Resource
	private FirstCategoryService firstCategoryService ;
	
	@Test
	public void getAllFirstCategory(){
		List<FirstCategory> list = firstCategoryService.getAll() ;
		
		for(FirstCategory c : list){
			System.out.println(c.toJson()) ;
		}
	}

}
