package ningbo.media.service;

import ningbo.media.BaseTest;
import ningbo.media.util.ApplicationContextUtil;

import org.junit.Test;

public class SendMailTest extends BaseTest {

	@Test
	public void testMail() {
		SendManagerService sms = (SendManagerService) ApplicationContextUtil
				.getContext().getBean("sendMail");
		
		try{
			sms.sendMail("leyxan.nb@qq.com") ;
			System.out.println("SUCCESS!") ;
		}catch(Exception ex){
			ex.printStackTrace() ;
			System.out.println("Fail!") ;
		}
		
	}

}
