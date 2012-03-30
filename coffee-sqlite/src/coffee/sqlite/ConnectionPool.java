package coffee.sqlite;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Observable;
import java.util.Observer;
import java.util.Vector;
/**
 * 数据库连接池的自定义实现
 * 
 * 由tomcat配置的数据源代替
 * 用到的设计模式【单例模式|观察者模式】
 * @author coffee
 */
public class ConnectionPool implements Observer{
	
	private static ConnectionPool context;
	
	private String jdbcDriver = ""; // 数据库驱动
	private String dbUrl = ""; 		// 数据 URL
	private String dbUsername = ""; // 数据库用户名
	private String dbPassword = ""; // 数据库用户密码

	private int initConn = 10;		// 连接池的初始大小
	private int increConn = 5;		// 连接池自动增加的大小
	private int minConn = 10;		// 连接池最小值
	private int maxConn = 50; 		// 连接池最大的大小
	private Vector<PooledConnection> connVector = null; // 存放连接池中数据库连接的向量 , 初始时为 null
	
	/**
	 * 单例模式
	 */
	private ConnectionPool(){
	}
	/**
	 * 创建连接池 
	 * @param jdbcDriver
	 * @param dbUrl
	 * @param dbUsername
	 * @param dbPassword
	 * @return
	 */
	public static ConnectionPool create(String jdbcDriver, String dbUrl, String dbUsername,
			String dbPassword) {
		if(context == null){
			context = new ConnectionPool();	//创建一个该实例的static引用
			context.jdbcDriver = jdbcDriver;
			context.dbUrl = dbUrl;
			context.dbUsername = dbUsername;
			context.dbPassword = dbPassword;
			try {
				Class.forName(context.jdbcDriver);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			//创建initConn指定大小的连接池
			context.connVector = new Vector<PooledConnection>();
			context.createPooledConn(context.initConn);
		}
		return context;
	}

	/**
	 * 创建由 numConnections 指定数目的数据库连接 , 并把这些连接 放入 connVector 向量中
	 * @param numConnections
	 *            要创建的数据库连接的数目
	 */
	private void createPooledConn(int numConnections) {
		for (int x = 0; x < numConnections; x++) {
			// 如果连接数己经达到最大，即退出。
			if (this.maxConn > 0
					&& this.connVector.size() >= this.maxConn) {
				break;
			}
			connVector.addElement(new PooledConnection());
			System.out.println(" 数据库连接己创建 ......");
		}
	}
	/**
	 * 通过调用 getFreeConnection() 函数返回一个可用的数据库连接 ,
	 * 如果当前没有可用的数据库连接，并且更多的数据库连接不能创
	 * 建（如连接池大小的限制），此函数等待一会再尝试获取。
	 * @return 返回一个可用的数据库连接对象
	 */
	public synchronized Connection getConnection() {
		Connection conn = null;
		// 遍历所有的对象，看是否有可用的连接
		for (PooledConnection pConn : connVector) {
			if (!pConn.isBusy()) {
				pConn.setBusy(true);
				conn = pConn.getConnection();
				break;
			}
		}
		if(conn == null){
			this.createPooledConn(this.increConn);
			conn = getConnection();//递归调用
		}
		//如果没有
		return conn;// 返回找到到的可用连接
	}
	/**
	 * 关闭连接池中所有的连接，并清空连接池。
	 */
	public synchronized void closeConnectionPool() {
		// 确保连接池存在，如果不存在，返回
		for (PooledConnection pConn : connVector) {
			try {
				if (pConn.isBusy()) {
					Thread.sleep(1000 * 5);; // 等 5 秒
				}
				pConn.getConnection().close();
			} catch (Exception e) {
				System.out.println(" 关闭数据库连接出错： " + e.getMessage());
			}
		}
		// 置连接池为空
		connVector = null;
	}

	private class PooledConnection extends Observable{
		private Connection connection = null;	// 数据库连接
		private boolean busy = false; 			// 此连接是否正在使用的标志，默认没有正在使用
		
		public PooledConnection() {
			try {
				this.connection = DriverManager.getConnection(dbUrl, dbUsername,
						dbPassword);
				//添加观察者
				this.addObserver(context);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		//setter getter
		public Connection getConnection() {
			return connection;
		}
		public boolean isBusy() {
			return busy;
		}
		/**
		 * 设置忙碌 
		 */
		public void setBusy(boolean busy) {
			this.busy = busy;
			//通知观察者
			this.setChanged();
			this.notifyObservers();
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		if(o instanceof PooledConnection){
			//如果连接不足，则创建连接
			if(this.connVector.size() < this.minConn){
				context.createPooledConn(context.increConn);
			}
		}
	}
}
