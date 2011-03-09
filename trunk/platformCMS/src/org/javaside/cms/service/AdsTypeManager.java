package org.javaside.cms.service;

import org.javaside.cms.core.DefaultEntityManager;
import org.javaside.cms.entity.AdsType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//Spring Service Bean的标识.
@Service
//默认将类中的所有函数纳入事务管理.
@Transactional
public class AdsTypeManager extends DefaultEntityManager<AdsType, Long> {

}
