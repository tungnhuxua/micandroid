package ningbo.media.service;

import javax.annotation.Resource;

import ningbo.media.BaseTest;

import org.junit.Test;

public class SystemUserServiceTest extends BaseTest{
	
	@Resource
	private SystemUserService systemUserService ;
	

	public void testVerificationUser(){
		String username = "leyxan.nb@gmail.com" ;
		String password = "123456" ;
		
		System.out.println(systemUserService.verificationUser(username, password)) ;
	}
	
	@Test
	public void testEmailIsUnique(){
		String email = "leyxan@gmail.com" ;
		
		System.out.println(systemUserService.isExist("email", email)) ;
	}

}
