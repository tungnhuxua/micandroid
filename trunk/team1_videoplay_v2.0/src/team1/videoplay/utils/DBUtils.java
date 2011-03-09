package team1.videoplay.utils;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class DBUtils {
//	private static DBUtils db=null;
	private static String driverName;
	private static String path;
	private static String host;
	private static String port;
	private static String dbName;
	private static String username;
	private static String password;
	static {
		Properties pro=PropUtils.loadProp(DBUtils.class, "/file.properties");
		    driverName=pro.getProperty("driverName");
			path=pro.getProperty("path");
			host=pro.getProperty("host");
			port=pro.getProperty("port");
			dbName=pro.getProperty("dbName");
			username=pro.getProperty("username");
			password=pro.getProperty("password");
//		db=new DBUtils();
		try {
			Class.forName(driverName);
		} catch (ClassNotFoundException e) {
			System.out.println("«˝∂Øº”‘ÿ ß∞‹£°");
		}
	}
	public static Connection getConn() {
		Connection conn=null;
		try {
			conn=DriverManager.getConnection(path+host+port+dbName,username,password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
	public static PreparedStatement getPstmt(Connection conn,String sql){
		PreparedStatement pstmt=null;
		try {
			pstmt=conn.prepareStatement(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pstmt;
	}
	public static ResultSet getRs(PreparedStatement pstmt){
		ResultSet rs=null;
		try {
			rs=pstmt.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
	public static void close(ResultSet rs){
		if(rs != null) {
		   try {
			rs.close();
			rs=null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	}
	public static void close(PreparedStatement pstmt){
		if(pstmt != null) {
		   try {
			   pstmt.close();
			   pstmt=null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	}
	public static void close(Connection conn){
		if(conn != null) {
		   try {
			conn.close();
			conn=null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	}
	public static int getExecuteUpdate(PreparedStatement pstmt)
	{
		int i=-1;
		try {
			i=pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i;
	}
}
