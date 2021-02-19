package hmj;

public class HealthCare_main {

	public static void main(String[] args) {
		System.out.println("Hello -- 메인화면 입장");
		Main m = new Main();
		m.frame.setVisible(true);
		
		DatabaseDAO dao = new DatabaseDAO();
		//dao.getInfo();
		//dao.setUser(null, null, null, null, null, null, null);
		
		
		
		
	}
}
