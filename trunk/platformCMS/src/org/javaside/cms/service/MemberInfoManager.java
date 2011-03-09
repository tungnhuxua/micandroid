package org.javaside.cms.service;

import org.javaside.cms.core.DefaultEntityManager;
import org.javaside.cms.entity.Member;
import org.javaside.cms.entity.MemberInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MemberInfoManager extends DefaultEntityManager<MemberInfo, Long> {

	public MemberInfo getMemberInfoUid(Member member) {
		return this.entityDao.findUniqueByProperty("member", member);
	}

	@Transactional(readOnly = true)
	public MemberInfo getMemberInfoDomain(String domain) {
		return this.entityDao.findUniqueByProperty("domain", domain);
	}

	@Transactional(readOnly = true)
	public boolean isDomainUnique(String domain, String oldDomain) {
		return entityDao.isPropertyUnique("domain", domain, oldDomain);
	}
}
