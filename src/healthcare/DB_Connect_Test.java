//package healthcare;
//
//   import java.sql.*;
//
//   public class DB_Connect_Test {

//      static final String JDBC_DRIVER = "org.mariadb.jdbc.Driver";
//      static final String DB_URL = "jdbc:mariadb://192.168.0.41:3306/project1";
//
//      static final String USER = "project1";
//      static final String PASS = "kitri1950!@";
//
//      public static void main(String[] args) {
//
//         Connection conn = null;
//         Statement stmt = null;
//
//         try {
//             Class.forName("org.mariadb.jdbc.Driver");
//
//            conn = DriverManager.getConnection(DB_URL, USER, PASS);
//            
//            stmt = conn.createStatement();
//            String sql;
//            sql = "SELECT * FROM exercise";
//            ResultSet rs = stmt.executeQuery(sql);
//
//            while (rs.next()) {
//               String Exercise_no = rs.getString("Exercise_no");
//               String Exercise_Name = rs.getString("Exercise_Name");
//               float Exercise_Cal = rs.getFloat("Exercise_Cal");
//
//               System.out.println(Exercise_no + ", " + Exercise_Name + ", " + Exercise_Cal);
//            }
//            rs.close();
//            stmt.close();
//            conn.close();
//
//         } catch (Exception e) {
//            e.printStackTrace();
//
//         } finally {
//            try {
//               if (stmt != null)
//                  stmt.close();
//            } catch (SQLException se2) {
//            } // nothing we can do
//            try {
//               if (conn != null)
//                  conn.close();
//            } catch (SQLException se) {
//               se.printStackTrace();
//            } // end finally try
//         } // end try
//         System.out.println("Goodbye!");
//      }// end main
//   }// end FirstExample
//   }