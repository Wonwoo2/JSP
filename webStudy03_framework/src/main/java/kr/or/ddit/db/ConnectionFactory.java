package kr.or.ddit.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;

/**
 *  Factory Object[Method] Pattern
 */
public class ConnectionFactory {
	private static String driverClassName;
	private static String url;
	private static String user;
	private static String password;
	
	private static DataSource dataSource;
	static {
		
		ResourceBundle bundle = ResourceBundle.getBundle("kr.or.ddit.db.dbinfo");
		driverClassName = bundle.getString("driverClassName");
		url = bundle.getString("url");
		user = bundle.getString("user");
		password = bundle.getString("password");
		
//		try {
//			Class.forName(driverClassName);
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		}
		BasicDataSource bds = new BasicDataSource();
		dataSource = bds;
		bds.setDriverClassName(driverClassName);
		bds.setUrl(url);
		bds.setUsername(user);
		bds.setPassword(password);
		bds.setInitialSize(5);	// 초기 커넥션 갯수
		bds.setMaxTotal(10);	
		bds.setMaxWaitMillis(2000);	// 커넥션 갯수가 초기 커넥션 갯수를 넘어가면 반환을 기다리는 시간을 설정, 이 시간을 넘어가면 커넥션 수를 최대 커넥션 갯수로 설정
		
	}
	
	public static Connection getConnection() throws SQLException {
//		Connection conn = DriverManager.getConnection(url, user, password);
//		OracleDataSource dataSource = new OracleDataSource();
//		dataSource.setURL(url);
		Connection conn = dataSource.getConnection();
		return conn;
	}
}