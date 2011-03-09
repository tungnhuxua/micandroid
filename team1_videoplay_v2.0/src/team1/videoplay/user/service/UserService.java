package team1.videoplay.user.service;
import team1.videoplay.user.model.User;
import team1.videoplay.utils.FenYe;



public interface UserService {
	public int addUser(User user);
	public int updateUser(User user_id);
	public int updateUserStatus(User user);
	public int deleteUser(int user_id);
	public FenYe getAllUser(int page);
	public User getUser(int user_id);
	public int getUserCount();
	public User getUserByUserName(String username);
	public int userMoneyModify(float money,int user_id);
	public User Login(String username,String password);
	public int userScoreModify(long userPoint, int userId);
	public User getUserByEmail(String email);
}
