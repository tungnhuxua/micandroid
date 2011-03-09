package team1.videoplay.payuser.service.impl;

import java.util.ArrayList;
import java.util.Properties;

import team1.videoplay.payuser.dao.PayUserDao;
import team1.videoplay.payuser.factory.PayUserDaoFactory;
import team1.videoplay.payuser.model.PayUser;
import team1.videoplay.payuser.service.PayUserService;
import team1.videoplay.utils.FenYe;
import team1.videoplay.utils.PropUtils;

public class PayUserServiceImpl implements PayUserService {
	private static PayUserDao pd;
	private PayUserServiceImpl(){};
	private static PayUserService ps=new PayUserServiceImpl();
	public static PayUserService getInstance(){
		return ps;
	}
	static {
		Properties prop = PropUtils.loadProp(PayUserService.class, "/file.properties");//读取文件
		String name = prop.getProperty("payuserdaoname");				//得到文件里的PayUserdaoname
		pd = PayUserDaoFactory.getInstance(name);						//通过工厂得到一个AppealDao对象
	}
	
	public int addPayUser(PayUser payuser) {
		return pd.addPayUser(payuser);
	}

	public int deletePayUser(int payuserId) {
		return pd.deletePayUser(payuserId);
	}

	public FenYe getAllPayUser(int page) {
		FenYe fenye = new FenYe();
		fenye.setPage(page);
		int payuserCount = pd.getPayUserCount();//dao里面新加入查找所有记录数的方法
		int pageSize = FenYe.pageSize;//pageSize是在FenYe类里定义的常量
		fenye.setPagecount((int)Math.ceil((double)payuserCount/pageSize));//得到总页数
		fenye.setList(pd.getAllPayUser(page, pageSize));
		return fenye;
	}

	public PayUser getPayUser(int payuserId) {
		return pd.getPayUser(payuserId);
	}

	public int getPayUserCount() {
		return pd.getPayUserCount();
	}

	public int updatePayUser(float supplyMoney, int payuserID) {
		return pd.updatePayUser(supplyMoney,payuserID);
	}
	//亮
	public ArrayList<PayUser> getAllPayUserByUserID(int userID) {
		return pd.getAllPayUserByUserID(userID);
	}
}
