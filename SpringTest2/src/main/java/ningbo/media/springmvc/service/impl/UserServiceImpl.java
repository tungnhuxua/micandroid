package ningbo.media.springmvc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ningbo.media.springmvc.dao.UserDao;
import ningbo.media.springmvc.domain.User;
import ningbo.media.springmvc.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	
	@Transactional
	public int insertUser(User user) {
		return userDao.insertUser(user);
	}

	
	@Transactional
	public User getUser(final int userId) {
		return userDao.getUser(userId);
	}

	
	@Transactional
	public List<User> getUsers() {
		return userDao.getUsers();
	}

	
	@Transactional
	public boolean updateUser(User user) {
		return userDao.updateUser(user);
	}

	
	@Transactional
	public boolean deleteUser(User user) {
		return userDao.deleteUSer(user);
	}

}
