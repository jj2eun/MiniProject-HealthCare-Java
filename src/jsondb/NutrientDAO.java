package jsondb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class NutrientDAO {
	static final String JDBC_DRIVER = "org.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mariadb://localhost:3306/test";
	
	//  Database credentials
	static final String USER = "root";
	static final String PASS = "1234";
	
	Connection conn = null;
	Statement stmt = null;
	
	public void connectDB() {
		System.out.println("Nutrient Database 접속...");
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			if(conn != null) System.out.println("conn 성공");
			else System.out.println("conn 실패");
			stmt = conn.createStatement();
	
		} catch (Exception e) {
			e.printStackTrace();
		}
	} // ConncectDB
	
	public void closeDB() {
		System.out.println("Nutrient Database Close...");
		try {
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	// Nutrient 객체 받아서 DB안에 넣기
//	public void insertNutrient(Nutrient n) {
//		
//		
//		try {
//			Class.forName("org.mariadb.jdbc.Driver");
//			System.out.println("Connecting to database...");
//			conn = DriverManager.getConnection(DB_URL, USER, PASS);
//			
//			if(conn != null) System.out.println("conn 성공");
//			else System.out.println("conn 실패");
//			
//			System.out.println(sql); // 실행될 쿼리문 출력
//			
//			stmt = conn.createStatement();
//			stmt.executeUpdate(sql);
//			
//			stmt.close();
//			conn.close();
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			 try{
//		         if(stmt!=null)
//		            stmt.close();
//		      }catch(SQLException se2){
//		      }// nothing we can do
//		      try{
//		         if(conn!=null)
//		            conn.close();
//		      }catch(SQLException se){
//		         se.printStackTrace();
//		      }//end finally try
//		 } // end try
//	
//		
//	}
}