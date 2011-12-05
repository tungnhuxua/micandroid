package ningbo.media.dao;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import ningbo.media.BaseTest;
import ningbo.media.bean.SystemUser;
import ningbo.media.service.SystemUserService;

import org.junit.Test;

public class SystemUserDaoTest extends BaseTest {
	
	@Resource
	private SystemUserService systemUserService ;
	

	public void testSave(){
		SystemUser u = new SystemUser() ;
		u.setEmail("leyxan.nb@gmail.com");
		u.setName_cn("宁烛坪");
		u.setName_en("Devon.Ning");
		u.setPassword("123456");
		u.setUsername("Davidning");
		u.setDate_time(new Date());
		u.setPhoto_path("/data/data/image/head.png");
		u.setIsManager(false);
		try{
			systemUserService.save(u) ;
		}catch(Exception ex){
			ex.printStackTrace() ;
		}
		
	}
	
	@Test
	public void testGetAll(){
		List<SystemUser> list = systemUserService.getAll() ;
		
		for(SystemUser one : list){
			System.out.println(one.toJson() );
		}
	}

}
