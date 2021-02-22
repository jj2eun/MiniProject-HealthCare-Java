package healthcare;

import java.sql.*;

import javax.swing.JOptionPane;

public class DBConnect {

	public static void report(String user_id, String report_date, String user_day_weight, String day_Cal, String day_c,
			String day_p, String day_f, String day_use_cal) {
		try {
			Connection conn = getConnection();
			PreparedStatement stmt = conn.prepareStatement("INSERT INTO report"
					+ "(User_ID, Report_Date, User_Day_Weight, Day_Cal, Day_C, Day_P, Day_F, Day_Use_Cal)" + "VALUE"
					+ "('" + user_id + "','" + report_date + "','" + user_day_weight + "','" + day_Cal + "','" + day_c
					+ "','" + day_p + "','" + day_f + "','" + day_use_cal + "')");

			stmt.executeUpdate();

			System.out.println("The data has been saved! 2");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void delete(String choice_date, String user_id) {
		try {
			Connection conn = getConnection();
			PreparedStatement stmt = conn.prepareStatement("DELETE FROM user_eat" + " WHERE Eat_Date =" + "'"
					+ choice_date + "' " + "  AND User_ID =" + "'" + user_id + "'");

			stmt.executeUpdate();
			System.out.println("The data has been deleted");
		} catch (SQLException ee) {
			System.out.println(ee.getMessage());
		}

	}

	public void updateInfo(String query) {
		Connection conn = null;

		String sql;
		conn = getConnection();
		sql = query;
		try {
			conn.createStatement().executeQuery(sql);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

	public ResultSet getInfo(String query) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		String sql;
		conn = getConnection();

		// this.sql = "SELECT * FROM exercise limit 5";
		sql = query;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return rs;
	} // 정보 조회

	// =================== 로그인 DB Connect ==============================
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

	} // 로그인 정보 저장

	public static void createJoin(String User_ID, String User_Password, String User_Name, String User_Gender,
			String User_Height, String User_Weight, String Use_Act_Index, String Day_Recommend_Cal) {
		try {
			Connection conn = getConnection();
			PreparedStatement stmt = conn.prepareStatement("INSERT INTO user_personal"
					+ "(User_ID, User_Password, User_Name,User_Gender,User_Height,User_Weight,Use_Act_Index,Day_Recommend_Cal)"
					+ "VALUE" + "('" + User_ID + "','" + User_Password + "','" + User_Name + "','" + User_Gender + "','"
					+ User_Height + "','" + User_Weight + "','" + Use_Act_Index + "','" + Day_Recommend_Cal + "')");
			stmt.executeUpdate();
			System.out.println("The data has been saved!");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	} // 회원가입 정보 저장

	public static boolean idCheck(String User_ID) {

		try {

			Connection conn = getConnection();
			PreparedStatement stmt = conn.prepareStatement("select User_ID from user_personal WHERE User_ID = ?");
			stmt.setString(1, User_ID);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				JOptionPane.showMessageDialog(null, "중복입니다.");
				return true;
			} else {
				JOptionPane.showMessageDialog(null, "사용가능한 ID 입니다.");
				return false;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;

	} // ID 중복체크

	public static void user_eat(String user_id, String choice_date, String choice_time, String choice_food,
			String choice_cal, String choice_food_count) {
		try {
			Connection conn = getConnection();
			PreparedStatement stmt = conn.prepareStatement(
					"INSERT INTO user_eat" + "(User_ID, Eat_Date, Eat_Time,Food_no,Food_cal,Food_Count)" + "VALUE"
							+ "('" + user_id + "','" + choice_date + "','" + choice_time + "','" + choice_food + "','"
							+ choice_cal + "','" + choice_food_count + "')");

			stmt.executeUpdate();

			System.out.println("The data has been saved! 1");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}// 음식데이터 저장

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

	}// Close Connection

	// ======================= 운동정보 조회 ================================
	public static void exercise(String Exercise_Name, String Exercise_Cal) {

		try {

			Connection conn = getConnection();
			PreparedStatement stmt = conn.prepareStatement("select * from exercise");

			ResultSet rs = stmt.executeQuery();

			System.out.println(rs);

			while (rs.next()) {
				System.out.println(rs.getString("Exercise_Name") + (": ") + rs.getInt("Exercise_Cal") + ("kcal"));

			}

		} catch (Exception e) {

		}

	}

}