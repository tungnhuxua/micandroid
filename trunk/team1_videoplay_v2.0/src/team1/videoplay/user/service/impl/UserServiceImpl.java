package team1.videoplay.user.service.impl;
import java.util.Properties;

import team1.videoplay.exception.PasswordIsErrorException;
import team1.videoplay.exception.UserNotFoundException;
import team1.videoplay.user.dao.UserDao;
import team1.videoplay.user.factory.UserDaoFactory;
import team1.videoplay.user.model.User;
import team1.videoplay.user.service.UserService;
import team1.videoplay.utils.FenYe;
import team1.videoplay.utils.PropUtils;

public class UserServiceImpl implements UserService {
	//单例实现（根据下列写每个service）
	private static UserDao ud;
	private UserServiceImpl(){};
	private static UserService us=new UserServiceImpl();
	public static UserService getInstance(){
		return us;
	}
	static {
		Properties prop = PropUtils.loadProp(UserService.class, "/file.properties");//读取文件
		String name = prop.getProperty("userdaoname");				//得到文件里的appealdaoname
		ud = UserDaoFactory.getInstance(name);						//通过工厂得到一个AppealDao对象
	}
	
	public int addUser(User user) {
		return ud.addUser(user);
	}

	public int deleteUser(int userId) {
		return ud.deleteUser(userId);
	}
	/**
	 * 一定要注意，只要是分页的查询方法，写法都如下：utils包下有一个FenYe包装类，里面有如下属性： pageSize; page;pagecount;list;
	 * pageSize制定每页显示的记录数，page是当前页，pagecount是总页数，list是查找出的所有结果集，如何应用，看下面一个方法
	 */
	public FenYe getAllUser(int page) {
		FenYe fenye = new FenYe();
		fenye.setPage(page);
		int userCount = ud.getUserCount();//dao里面新加入查找所有记录数的方法
		int pageSize = FenYe.pageSize;//pageSize是在FenYe类里定义的常量
		fenye.setPagecount((int)Math.ceil((double)userCount/pageSize));//得到总页数
		fenye.setList(ud.getAllUser(page, pageSize));
		return fenye;
	}

	public User getUser(int userId) {
		return ud.getUser(userId);
	}
	public int getUserCount(){
		return ud.getUserCount();
	}

	public User getUserByUserName(String username) {
		return ud.getUserByUserName(username);
	}

	public int updateUser(User userId) {
		return ud.updateUser(userId);
	}

	public int updateUserStatus(User user) {
		return ud.updateUserStatus(user);
	}

	public int userMoneyModify(float money, int userId) {
		return ud.userMoneyModify(money, userId);
	}
	
	 public User Login(String username,String password){
    	 User user=ud.getUserByUserName(username);
    	 if(user==null){
    		 throw new UserNotFoundException();//若会员名为空，抛出异常 UserNotFoundException()
    	 }else{
    		 if(!password.equals(user.getUser_password())){
    			 throw new PasswordIsErrorException();//若密码不正确，抛出异常 PasswordIsErrorException()
    		 }
    	 }
    	 return user;
     }
	 public int userScoreModify(long userPoint, int userId){
		 return ud.userScoreModify(userPoint, userId);
	 }
	public User getUserByEmail(String email) {
		return ud.getUserByEmail(email);
	}
}
