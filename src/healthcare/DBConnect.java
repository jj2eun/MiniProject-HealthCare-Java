package healthcare;
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
	} // 정보 조회
	

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
	} // Update Info
	
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
	

	public static void user_eat(String user_id, String choice_date, String choice_time, String choice_food, String food_cal,
	         String choice_food_count) {
	      try {
	         Connection conn = getConnection();
	         PreparedStatement stmt = conn.prepareStatement("INSERT INTO user_eat"
	               + "(User_ID, Eat_Date, Eat_Time,Food_no,Food_Cal,Food_Count)" + "VALUE" + "('" + user_id + "','"
	               + choice_date + "','" + choice_time + "','" + choice_food + "','" + food_cal + "','" + choice_food_count + "')");
	         
	         
	         stmt.executeUpdate();
	         
	         
	         System.out.println("The data has been saved! 1");
	      } catch (Exception e) {
	         e.printStackTrace();
	      }

	   }// 음식데이 저장 :아침
	
	
	
	public static void food_report(String user_id, String report_date, String day_Cal
			) {
	      try {
	         Connection conn = getConnection();
	         PreparedStatement stmt = conn.prepareStatement("INSERT INTO report"
	               + "(User_ID, Report_Date,Day_C, Day_P, Day_F)" 
	               + " SELECT User_ID, Eat_Date, round(Food_C*Food_count,2) , round(Food_P*Food_count,2),round(Food_F*Food_count,2)"
	               + " FROM report_result"
	               + " WHERE User_ID = " + "'"+user_id+"'"
	               + " AND Eat_Date = "+ "'"+report_date+"'");
	        		 ;
	         
	         PreparedStatement stmt2 = conn.prepareStatement("UPDATE report"
	        		 + " SET Day_Cal = " + day_Cal
	        		 + " WHERE User_ID = " + "'"+user_id+"'"
		             + " AND Report_Date = "+ "'"+report_date+"'" );
	         
	         System.out.println("오늘의칼로리" + stmt2);
	         
	         
	         stmt.executeUpdate();
	         stmt2.executeUpdate();
	         
	         
	         System.out.println("The data has been saved! 2");
	      } catch (Exception e) {
	         e.printStackTrace();
	      }
	}
	
	
	
	public void user_exercise(String user_id, String choice_date, String choice_exercise, String exercise_cal, String choice_exercise_time) {
	      try {
	         Connection conn = getConnection();
	         PreparedStatement stmt = conn.prepareStatement(
	        		 "INSERT INTO user_exercise"
	               + "(User_ID, Exercise_Date, Exercise_no,Exercise_Cal, Exercise_Time)"
	               + "VALUE"
	               + "('" + user_id + "','" + choice_date + "','" + choice_exercise + "','" + exercise_cal + "','" + choice_exercise_time + "')");
	         
	         
	         stmt.executeUpdate();
	         
	         
	         System.out.println("The data has been saved! 1");
	      } catch (Exception e) {
	         e.printStackTrace();
	      }

	   }// 음식데이 저장 :아침
	

	
	
	public void exer_report(String user_id, String report_date , String use_Cal) {
	      try { 
	          //운동리스트가 미리 등록되어 있을 경우가 있으므로 if문 추가필요
	         
	         Connection conn = getConnection();
	         
	         PreparedStatement stmt = conn.prepareStatement("INSERT INTO report"
	                  + " (Day_Use_Cal)" 
	                  + " SELECT round(Exercise_cal*Exercise_Time,2)"
	                  + " FROM user_exercise"
	                  + " WHERE User_ID = " + "'"+user_id+"'"
	                  + " AND Exercise_Date =" + "'" +report_date+ "'") 
	               ;
	                  
	         
	      } catch (Exception eee) {
	         eee.printStackTrace();
	      }
	   }
	

	public static void delete(String choice_date, String user_id) {
		try {
			Connection conn = getConnection();
			PreparedStatement stmt =  conn.prepareStatement("DELETE FROM user_eat"
					+ " WHERE Eat_Date ="+"'"+choice_date+"' "
					+"  AND User_ID ="+ "'"+ user_id+"'");
			
			stmt.executeUpdate();
			System.out.println("The data has been deleted");
		} catch(SQLException ee) {
			System.out.println(ee.getMessage());
		}
	
	}
	
	public void delete_exercise(String choice_date, String user_id) {
		try {
			Connection conn = getConnection();
			PreparedStatement stmt =  conn.prepareStatement("DELETE FROM user_exercise"
					+ " WHERE Exercise_Date ="+"'"+choice_date+"' "
					+"  AND User_ID ="+ "'"+ user_id+"'");
			
			stmt.executeUpdate();
			System.out.println("The data has been deleted");
		} catch(SQLException ee) {
			System.out.println(ee.getMessage());
		}
	
	}
	
	     
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