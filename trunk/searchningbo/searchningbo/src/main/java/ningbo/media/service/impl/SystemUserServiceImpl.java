package ningbo.media.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import ningbo.media.bean.SystemUser;
import ningbo.media.core.service.impl.BaseServiceImpl;
import ningbo.media.dao.SystemUserDao;
import ningbo.media.service.SystemUserService;

@Service("systemUserService")
public class SystemUserServiceImpl extends BaseServiceImpl<SystemUser, Integer> implements
		SystemUserService {
	

	@Autowired(required = false)
	public SystemUserServiceImpl(@Qualifier("systemUserDao")SystemUserDao systemUserDao) {
		super(systemUserDao);
	}
	
	public SystemUser verificationUser(String email,String password){
		final String hql = "from SystemUser as model where model.email = ? and model.password = ? " ;
		SystemUser u = (SystemUser)super.findUnique(hql,email,password) ;
		return u ;
	}

}
