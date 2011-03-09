package org.javaside.cms.service;

import org.javaside.cms.core.DefaultEntityManager;
import org.javaside.cms.entity.MemberMessage;
import org.javaside.cms.entity.MemberMessageSetup;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MemberMessageSetupManager extends DefaultEntityManager<MemberMessageSetup, Long> {

	
	public MemberMessageSetup getMemberMessageSetupID(Long uid) {
		return this.entityDao.findUniqueByProperty("uid", uid);
	}
}
