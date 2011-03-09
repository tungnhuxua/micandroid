package org.javaside.cms.service;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.javaside.cms.core.DefaultEntityManager;
import org.javaside.cms.entity.MailActivation;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MailActivtionManager extends DefaultEntityManager<MailActivation, Long> {

	/**
	 * 用户非会员邮件激活验证
	 * 
	 * @param uid
	 * @param usercode
	 * @return
	 */
	public boolean isMidMailcode(Long id, String mailCode) {
		boolean bool = false;
		List list = entityDao.findByCriteria(Restrictions.eq("id", id), Restrictions.eq("mailCode", mailCode));
		if (list.size() > 0) {
			bool = true;
		}
		return bool;
	}
}
