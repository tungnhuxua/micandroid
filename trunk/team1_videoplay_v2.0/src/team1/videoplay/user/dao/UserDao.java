package team1.videoplay.user.dao;

import java.util.ArrayList;

import team1.videoplay.user.model.User;






public interface UserDao {
	public int addUser(User user);
	public int deleteUser(int user_id);
	public int updateUser(User user_id);
	public int updateUserStatus(User user);
	public ArrayList<User> getAllUser( int page,int pageSize);
	public User getUser(int user_id);
	public int getUserCount();
	public User getUserByUserName(String username);
	public int userMoneyModify(float money,int user_id);
	public int userScoreModify(long user_point,int user_id);
	public User getUserByEmail(String email);
}
