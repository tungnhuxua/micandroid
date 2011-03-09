package org.javaside.cms.service;

import org.javaside.cms.core.DefaultEntityManager;
import org.javaside.cms.core.ServiceException;
import org.javaside.cms.core.SpringSecurityUtils;
import org.javaside.cms.entity.Role;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RoleManager extends DefaultEntityManager<Role, Long> {
	/**
	 * 重载delte函数,演示异常处理及用户行为日志.
	 */
	@Override
	public void delete(Long id) {
		if (id.intValue() == 1) {
			logger.warn("操作员{}尝试删除超级管理员用户角色", SpringSecurityUtils.getCurrentUserName());
			throw new ServiceException("不能删除超级管理员角色");
		}

		entityDao.delete(id);
	}

	/**
	 * 根据ID集合批量删除角色
	 * 
	 * @param ids
	 */
	public void deleteBatch(Long[] ids) {
		for (Long id : ids) {
			entityDao.delete(id);
		}
	}
}
