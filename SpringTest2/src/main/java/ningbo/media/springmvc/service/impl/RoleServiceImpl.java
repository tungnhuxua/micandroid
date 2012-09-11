package ningbo.media.springmvc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ningbo.media.springmvc.dao.RoleDao;
import ningbo.media.springmvc.domain.Role;
import ningbo.media.springmvc.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleDao roleDao;
	
	
	@Transactional
	public boolean insertRole(Role role) {
		return roleDao.insertRole(role);
	}

	
	@Transactional
	public Role getRole(int roleId) {
		return roleDao.getRole(roleId);
	}


	@Transactional
	public List<Role> getRoles() {
		return roleDao.getRoles();
	}

	
	@Transactional
	public boolean updateRole(Role role) {
		return roleDao.updateRole(role);
	}

	
	@Transactional
	public boolean deleteRole(Role roleId) {
		return roleDao.deleteRole(roleId);
	}

}
