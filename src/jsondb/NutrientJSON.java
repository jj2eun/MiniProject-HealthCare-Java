package jsondb;
import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import java.io.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class NutrientJSON extends NutrientDAO{
	String keyId = "2216666e77d34ea399f9";
	String serviceId = "I2790";
	String dataType = "json";
	int start = 392;
	int last = 1000;
	String urlStr = "http://openapi.foodsafetykorea.go.kr/api/"+keyId+"/"+serviceId+"/"+dataType+"/"+start+"/"+last;
	
	static final String JDBC_DRIVER = "org.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mariadb://192.168.0.41:3306/project1";
	
	static final String USER = "project1";
	static final String PASS = "kitri1950!@";
	Connection conn = null;
	Statement stmt = null;
	public void getAPI() {
		
		try {
			URL url = new URL(urlStr);
			
			BufferedReader bf;
			String line = "";
			String result = "";
			bf = new BufferedReader(new InputStreamReader(url.openStream(),"UTF-8"));
			
			while((line=bf.readLine())!= null) {
				result = result.concat(line);
			}
			JSONParser parser = new JSONParser();
			JSONObject obj = (JSONObject)parser.parse(result);
			
			JSONObject parse_SerName = (JSONObject) obj.get("I2790");
			JSONArray parse_listArr = (JSONArray)parse_SerName.get("row");
			
			
			for(int i = 0; i < parse_listArr.size(); i++) {
				Nutrient nut = new Nutrient();
				
				JSONObject nutrient = (JSONObject) parse_listArr.get(i);
				String foodCode = nutrient.get("FOOD_CD").toString();
				String foodName = nutrient.get("DESC_KOR").toString();
				String servingSize = nutrient.get("SERVING_SIZE").toString();
				String cal = nutrient.get("NUTR_CONT1").toString();
				String calbo = nutrient.get("NUTR_CONT2").toString();
				String protein = nutrient.get("NUTR_CONT3").toString();
				String fat = nutrient.get("NUTR_CONT4").toString();
				String sugar = nutrient.get("NUTR_CONT5").toString();
				
				nut.Food_no = foodCode;
				nut.Food_Name = foodName;
				nut.Food_Cal = "".equals(cal) ? 0 : Float.parseFloat(cal);
				nut.Food_C = "".equals(calbo) ? 0 : Float.parseFloat(calbo);
				nut.Food_P = "".equals(protein) ? 0 : Float.parseFloat(protein);
				nut.Food_F = "".equals(fat) ? 0 : Float.parseFloat(fat);
				System.out.println("foodCode: "+foodCode+" foodName: "+foodName+" Cal: "+cal+"calbo: "+calbo);
				
				insertDB(nut);
			} // 객체로 각 값들 저장해서 그 객체를 DAO클래스로 넘겨주자
			bf.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public void insertDB(Nutrient n) {
		
		String sql = "INSERT INTO project1.nutrient VALUES('" + n.Food_no + "','" + n.Food_Name + "'," + n.Food_Cal + "," + n.Food_C + "," + n.Food_P + "," + n.Food_F + ");";
		
		System.out.println("Nutrient Database 접속...");
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			
			if(conn != null) System.out.println("conn 성공");
			else System.out.println("conn 실패");
			
			System.out.println(sql); // 실행될 쿼리문 출력
			
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
			
			stmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			 try{
		         if(stmt!=null)
		            stmt.close();
		      }catch(SQLException se2){
		      }// nothing we can do
		      try{
		         if(conn!=null)
		            conn.close();
		      }catch(SQLException se){
		         se.printStackTrace();
		      }//end finally try
		 } // end try
	}

}

