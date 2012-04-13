package ningbo.media.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import ningbo.media.core.service.impl.BaseServiceImpl;
import ningbo.media.entity.SystemUser;
import ningbo.media.system.dao.SystemUserDao;
import ningbo.media.system.service.SystemUserService;

@Service("systemUserService")
public class SystemUserServiceImpl extends BaseServiceImpl<SystemUser, Long>
		implements SystemUserService {

	@Autowired
	public SystemUserServiceImpl(@Qualifier("systemUserDao")
	SystemUserDao systemUserDao) {
		super(systemUserDao);
	}
}
