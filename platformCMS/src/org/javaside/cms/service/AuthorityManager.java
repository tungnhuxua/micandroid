package org.javaside.cms.service;

import org.javaside.cms.core.DefaultEntityManager;
import org.javaside.cms.entity.Authority;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AuthorityManager extends DefaultEntityManager<Authority, Long> {

}
