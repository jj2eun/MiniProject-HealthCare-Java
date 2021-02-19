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
	public static int login(String User_ID, String User_Password) {

		int success = 0;

		try {

			Connection conn = getConnection();
			PreparedStatement stmt = conn.prepareStatement(
					"select User_Password from user_personal WHERE User_ID = ? AND User_Password = ?");
			stmt.setString(1, User_ID);
			stmt.setString(2, User_Password);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				success++;
				System.out.println("login success");
			}

		} catch (Exception e) {

		}
		return success;

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

	public static void idCheck(String User_ID) {

		try {

			Connection conn = getConnection();
			PreparedStatement stmt = conn.prepareStatement("select User_ID from user_personal WHERE User_ID = ?");
			stmt.setString(1, User_ID);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				JOptionPane.showMessageDialog(null, "중복입니다.");
			} else {
				JOptionPane.showMessageDialog(null, "사용가능한 ID 입니다.");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
