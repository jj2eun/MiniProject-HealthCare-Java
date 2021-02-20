package healthcare;
import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import com.toedter.calendar.JCalendar;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.temporal.TemporalAdjusters;
import java.awt.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
//z
public class Main  {
	JFrame frame;
	private LocalDate today = LocalDate.now();
	private String user_id;
	/**
	 * @wbp.parser.constructor
	 */
	public Main() {initialize();}
	public Main(String User_ID) {
		user_id = User_ID;
		initialize();
		System.out.println(user_id);
	}
	public void mainInit() {
		initialize();
	}
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Main");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 800, 600);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		
		JPanel contentPane = new JPanel();
		contentPane.setBounds(0,0,794,561);
		frame.getContentPane().add(contentPane);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		contentPane.setVisible(true);
		/* ======== 캘린더 ============== */
		JCalendar calendar = new JCalendar();
		calendar.setBounds(40, 212, 691, 350);
		contentPane.add(calendar);
		JPanel jpanel = calendar.getDayChooser().getDayPanel();
		Component component[] = jpanel.getComponents();
		calTrafficLight(component, today);
		// default로 오늘(Date)를 넘겨준다
		
		calendar.getMonthChooser().addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				//System.out.println(evt.getPropertyName()+ ": " + evt.getNewValue());
				if (evt.getPropertyName().toString() == "month") {
					LocalDate date = today.withMonth(Integer.parseInt(evt.getNewValue().toString())+ 1);
					calTrafficLight(component, date);
				} // 변경된 property명이 month일 경우 해당 month의 date를 넘겨준다
				
			}
		}); // 캘린더 month변경 이벤트 핸들러
		
		//====================================JButton====================================
		/* 식단입력 버튼 */
		JButton btnFoodlist = new JButton("식단");
		btnFoodlist.setBounds(40, 20, 167, 50);
		btnFoodlist.setFont(new Font("굴림", Font.BOLD, 20));
		contentPane.add(btnFoodlist);
		
		btnFoodlist.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				FoodList fl = new FoodList(user_id);
				fl.frame.setVisible(true);
				frame.dispose();
				
			}
		});
		/* 운동입력 버튼 */
		JButton btnExer = new JButton("운동");
		btnExer.setBounds(304, 20, 167, 50);
		btnExer.setFont(new Font("굴림", Font.BOLD, 20));
		contentPane.add(btnExer);
		
		btnExer.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ExerciseList ex = new ExerciseList(user_id);
				ex.frame.setVisible(true);
				frame.dispose();
				
			}
		});
		/* 리포트 버튼 */
		JButton btnRept = new JButton("리포트");
		btnRept.setBounds(564, 20, 167, 50);
		btnRept.setFont(new Font("굴림", Font.BOLD, 20));
		contentPane.add(btnRept);
				
		btnRept.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Report rp = new Report(user_id);
				rp.frame.setVisible(true);
				frame.dispose();
			}
		});
		
		/* ===================== 라벨 ===================  */
		JLabel lblCal1 = new JLabel("0");
		lblCal1.setBounds(248, 111, 138, 50);
		lblCal1.setFont(new Font("맑은 고딕", Font.PLAIN, 50));
		contentPane.add(lblCal1);
		//getCal(lblCal1);
		
		JLabel lblSlash = new JLabel("/");
		lblSlash.setBounds(359, 111, 64, 50);
		lblSlash.setFont(new Font("맑은 고딕", Font.PLAIN, 50));
		contentPane.add(lblSlash);
		
		JLabel lblCal2 = new JLabel("0");
		lblCal2.setBounds(435, 111, 180, 50);
		contentPane.add(lblCal2);
		lblCal2.setFont(new Font("맑은 고딕", Font.PLAIN, 50));
		getCal2(lblCal2);
		
		JLabel lblText = new JLabel("하루권장 칼로리");
		lblText.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		lblText.setForeground(Color.GRAY);
		lblText.setBounds(472, 160, 103, 22);
		contentPane.add(lblText);
		
	}
	
	//칼로리 얻어서 라벨에 뿌리기
	public void getCal(JLabel lbl) {
		// db에서 값 받아오기
		float cur_cal = 0;
		float use_cal = 0;
		float result = 0;
		DBConnect dao = new DBConnect();
		String sql;
		sql = "Select Day_Cal, Use_Cal from report where User_ID = '" + user_id + "' and Date = "+ today+";";
		ResultSet rs = dao.getInfo(sql);
		
		try {
			while(rs.next()) {
				cur_cal = rs.getFloat("Day_Cal");
				use_cal = rs.getFloat("Use_Cal");
				result = cur_cal - use_cal;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		lbl.setText(result+"");
		//받아온값 라벨로 리턴
	}
	// 권장칼로리 얻어서 라벨에 뿌리기
	public void getCal2(JLabel lbl) {
		// 유저정보를 db에서 불러와서 권장칼로리 계산하기
		// 하루 권장 칼로리 = <표준체중>(자신의키 - 100)*0.9 * 활동지수
		// 활동지수  = HIGH : 60  MID : 40  LOW:20
		DBConnect dao = new DBConnect();
		String sql;
		Float height;
		int active = 0;
		float result = 0;
		sql = "Select * from user_personal where User_ID = '" + user_id + "';";
		// 회원번호별 유저정보 조회
		
		ResultSet rs = dao.getInfo(sql);
		
		try {
			while(rs.next()) {
				height = rs.getFloat("User_Height");
				active = rs.getInt("Use_Act_Index");
				result = (float) ((height - 100) * 0.9 * active);
				// 하루 권장 칼로리 계산
				System.out.println("height: " + height + "active: " + active);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		lbl.setText(Float.toString(result));
		// 라벨에 뿌리기 
	}
	
	public void calTrafficLight(Component component[], LocalDate changedDate) {
		float[] arr = new float[32];
		// 현재 달력의 월 get
		// 해당월의 일별로 db.report의 총칼로리 계산
		
		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");


		LocalDate firstDay = changedDate.with(TemporalAdjusters.firstDayOfMonth()); // 2021-02-01
		LocalDate lastDay = changedDate.with(TemporalAdjusters.lastDayOfMonth()); // 2021-02-28
		float recCal = 0;
		DBConnect dao = new DBConnect();
		String sql;
		sql = "Select * from report where User_ID = '" + user_id + "' and Report_Date between '" + firstDay +"' and '" + lastDay+ "';";
		ResultSet rs = dao.getInfo(sql);
		// today객체에 들어있는 달의 첫날~끝날의 레포트DB의 총칼로리 계산
		try {
			while(rs.next()) {
				// 날짜별 총 칼로리 불러오기
				float totalCal = rs.getFloat("Day_Cal") - rs.getFloat("Day_Use_Cal");
				recCal = rs.getFloat("Day_Recommend_Cal");
				String date = transFormat.format(rs.getDate("Report_Date"));
				int i = Integer.parseInt(date.substring(8)); // 일자만 i에 담기
				//System.out.println("i : "+i + "date: "+date);
				arr[i] = totalCal;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("firstday: " + firstDay);
		System.out.println("lastday: "+lastDay);
		setCalColor(arr, component, recCal);
		
	}
	
	public void setCalColor(float arr[],Component component[], float recCal) {
		for(int i = 1; i < arr.length; i++) {
			if(arr[i] != 0) {
				System.out.println("i: "+i);
				System.out.println("arr[i] : " + arr[i]);
				if(arr[i] >= recCal + 300) component[i+7].setBackground(Color.magenta); // 권장칼로리보다 300칼로리이상 섭취했을때
				else if(arr[i] >= recCal + 150) component[i+7].setBackground(Color.yellow); // 권장칼로리보다 150칼로리이상 섭취했을때
				else if(arr[i] > recCal - 100) component[i+7].setBackground(Color.GREEN); // 권장칼로리보다 -100칼로리이상 섭취했을때
				else component[i+7].setBackground(Color.GREEN); // 나머지
			}
		}
	}
}
