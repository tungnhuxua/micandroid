package team1.videoplay.userPay.service.impl;

import java.util.ArrayList;
import java.util.Properties;

import team1.videoplay.userPay.dao.UserPayDao;
import team1.videoplay.userPay.factory.UserPayDaoFactory;
import team1.videoplay.userPay.model.UserPay;
import team1.videoplay.userPay.service.UserPayService;
import team1.videoplay.utils.FenYe;
import team1.videoplay.utils.PropUtils;

public class UserPayServiceImpl implements UserPayService {
	//单例实现（根据下列写每个service）
	private static UserPayDao upd;
	private UserPayServiceImpl(){};
	private static UserPayService ups=new UserPayServiceImpl();
	public static UserPayService getInstance(){
		return ups;
	}
	static {
		Properties prop = PropUtils.loadProp(UserPayService.class, "/file.properties");//读取文件
		String name = prop.getProperty("userpaydaoname");				//得到文件里的appealdaoname
		upd = UserPayDaoFactory.getInstance(name);						//通过工厂得到一个AppealDao对象
	}
	public int addUserPay(UserPay userpay) {
		return upd.addUserPay(userpay);
	}

	public int deleteUserPay(int userpay_id) {
		return upd.deleteUserPay(userpay_id);
	}

	public FenYe getAllUserPay(int page) {
		FenYe fenye = new FenYe();
		fenye.setPage(page);
		int userpayCount = upd.getUserPayCount();//dao里面新加入查找所有记录数的方法
		int pageSize = FenYe.pageSize;//pageSize是在FenYe类里定义的常量
		fenye.setPagecount((int)Math.ceil((double)userpayCount/pageSize));//得到总页数
		fenye.setList(upd.getAllUserPay(page, pageSize));
		return fenye;
	}

	public UserPay getUserPay(int userpay_id) {
		return upd.getUserPay(userpay_id);
	}

	public int getUserPayCount() {
		return upd.getUserPayCount();
	}
	//亮
	public ArrayList<UserPay> getAllUserPayByUserID(int userID){
		return upd.getAllUserPayByUserID(userID);
	}
}
