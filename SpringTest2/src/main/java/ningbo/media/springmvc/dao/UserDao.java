package ningbo.media.springmvc.dao;

import java.util.List;

import ningbo.media.springmvc.domain.User;

public interface UserDao {

	public int insertUser(User user);
	
	public User getUser(int userId);
	
	public List<User> getUsers();
	
	public boolean updateUser(User user);
	
	public boolean deleteUSer(User user);
	
}
