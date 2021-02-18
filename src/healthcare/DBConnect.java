package healthcare;

import java.sql.*;

import javax.swing.JOptionPane;

public class DBConnect {

//	public static void main(String[] args) {
//		
//		getConnection();
//		
//
//	}
	public static void Login(String User_ID, String User_Password) {

//		String sql = "(SELECT User_Password FROM user_personal WHERE User_ID ='" + User_ID + "' and User_Password='" + User_Password+"')";
//		try {
//			Connection conn = getConnection();
//			PreparedStatement stmt = conn.prepareStatement(sql);
//			ResultSet rs = stmt.executeQuery();
//			
//			
//
//		} catch (Exception e) {
//			System.out.println(e.getMessage());
//			
//		}
//		return;

		try {

			Connection conn = getConnection();
			PreparedStatement stmt = conn.prepareStatement("(SELECT User_Password FROM user_personal WHERE User_ID ='"
					+ User_ID + "' and User_Password='" + User_Password + "')");
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				String User_ID1 = rs.getString("User_ID");
				String User_Password2 = rs.getString("User_Password");

				System.out.println(User_ID + "," + User_Password);
			}

		} catch (Exception e) {

		}
		return;

	}

	public static void createJoin(String User_ID, String User_Password, String User_Name, String User_Gender,
			String User_Height, String User_Weight, String Use_Act_Index) {
		try {
			Connection conn = getConnection();
			PreparedStatement stmt = conn.prepareStatement("INSERT INTO user_personal"
					+ "(User_ID, User_Password, User_Name,User_Gender,User_Height,User_Weight,Use_Act_Index)" + "VALUE"
					+ "('" + User_ID + "','" + User_Password + "','" + User_Name + "','" + User_Gender + "','"
					+ User_Height + "','" + User_Weight + "','" + Use_Act_Index + "')");
			stmt.executeUpdate();
			System.out.println("The data has been saved!");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public static Connection getConnection() {

		try {

			String JDBC_DRIVER = "org.mariadb.jdbc.Driver";
			String DB_URL = "jdbc:mariadb://kitri.synology.me:3306/project1";
			String USER = "kitri";
			String PASS = "Kitri1950!@";
			Class.forName(JDBC_DRIVER);

			Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);

			System.out.println("The Connection Successful");
			return conn;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}

	}

}
