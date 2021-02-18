package hmj;
import hmj.Main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;
import java.util.Vector;
import javax.swing.JOptionPane;
public class DatabaseDAO {
	private static final String JDBC_DRIVER = "org.mysql.jdbc.Driver";  
	private static final String DB_URL = "jdbc:mariadb://kitri.synology.me:3306/project1";

	   //  Database credentials
	private static final String USER = "kitri";
	private static final String PASS = "Kitri1950!@";
	
	public DatabaseDAO() {}
	
	// conn 얻기
	public Connection getConn() {
		System.out.println("GET CONNECTION ...");
		Connection conn = null;
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	// 정보 조회
	public ResultSet getInfo(String query) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		String sql;
		conn = getConn();
		
		//this.sql = "SELECT * FROM exercise limit 5";
		sql = query;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return rs;
	}
	
	// 회원가입
	public void setUser(String userId, String userPw, String userName, String gender, String height, String weight, String act) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		String sql;
		conn = getConn();
		
		sql = "INSERT INTO user_personal VALUES('9709aa','qwe123','지','F','170','60','40');";
		System.out.println("회원가입 진행중...");
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("회원가입 완료...");
	}
	
	
	
}
