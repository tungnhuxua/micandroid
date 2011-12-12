package ningbo.media.bean;

import java.util.Date;

public class SystemUserTest {

	public static String uJson() {
		SystemUser u = new SystemUser();
		// u.setId(1);
		u.setEmail("leyxan.nb@gmail.com");
		// u.setName_cn("宁烛坪");
		// u.setName_en("David.Ning");
		u.setPassword("123456");
		u.setUsername("Davidning");
		// u.setDate_time(new Date());
		// u.setPhoto_path("/data/data/image/head.png");
		// u.setIsManager(false);
		return u.toJson();
	}

	public static void main(String[] args) {
		SystemUser su;
		su = SystemUser.fromJson(uJson());
		System.out.println("id:" + su.getId() +" username:" + su.getUsername()) ;
	}
}
