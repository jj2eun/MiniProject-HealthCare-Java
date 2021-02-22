package healthcare;
//z
import java.sql.*;

import javax.swing.JOptionPane;

public class DBConnect {

	public ResultSet getInfo(String query) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		String sql;
		conn = getConnection();
		
		//this.sql = "SELECT * FROM exercise limit 5";
		sql = query;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return rs;
	} // ���� ��ȸ
	
	
	// =================== �α��� DB Connect ==============================
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

	} // �α��� ���� ����

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
	} // ȸ������ ���� ����
	
	public static void idCheck(String User_ID) {

		try {

			Connection conn = getConnection();
			PreparedStatement stmt = conn.prepareStatement("select User_ID from user_personal WHERE User_ID = ?");
			stmt.setString(1, User_ID);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				JOptionPane.showMessageDialog(null, "�ߺ��Դϴ�.");
			} else {
				JOptionPane.showMessageDialog(null, "��밡���� ID �Դϴ�.");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	} // ID �ߺ�üũ
	
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

	} // Get Connection
	
	public static void closeConnection() {
		
	}
	// Close Connection

	public static void user_eat(String user_id, String choice_date, String choice_time, String choice_food,
	         String choice_food_count) {
	      try {
	         Connection conn = getConnection();
	         PreparedStatement stmt = conn.prepareStatement("INSERT INTO user_eat"
	               + "(User_ID, Eat_Date, Eat_Time,Food_no,Food_Count)" + "VALUE" + "('" + user_id + "','"
	               + choice_date + "','" + choice_time + "','" + choice_food + "','" + choice_food_count + "')");
	         
	         
	         stmt.executeUpdate();
	         
	         
	         System.out.println("The data has been saved! 1");
	      } catch (Exception e) {
	         e.printStackTrace();
	      }

	   }// ���ĵ��� ���� :��ħ
	  
}
