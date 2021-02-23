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

public class Main {

	JFrame frame;
	float recommendCalory = 0;
	private LocalDate today = LocalDate.now();
	private String user_id;

	/**
	 * @wbp.parser.constructor
	 */
	public Main() {
		initialize();
	}

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
		contentPane.setBackground(Color.WHITE);
		contentPane.setBounds(0, 0, 794, 561);
		frame.getContentPane().add(contentPane);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		contentPane.setVisible(true);
		/* ======== 캘린더 ============== */
		JCalendar calendar = new JCalendar();
		calendar.getDayChooser().getDayPanel().setBackground(Color.WHITE);
		calendar.setBounds(39, 201, 691, 350);
		contentPane.add(calendar);
		JPanel jpanel = calendar.getDayChooser().getDayPanel();
		Component component[] = jpanel.getComponents();
		// default로 오늘(Date)를 넘겨준다

		calendar.getMonthChooser().addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				// System.out.println(evt.getPropertyName()+ ": " + evt.getNewValue());
				if (evt.getPropertyName().toString() == "month") {
					LocalDate date = today.withMonth(Integer.parseInt(evt.getNewValue().toString()) + 1);
					calTrafficLight(component, date);
				} // 변경된 property명이 month일 경우 해당 month의 date를 넘겨준다

			}
		}); // 캘린더 month변경 이벤트 핸들러

		// ====================================JButton====================================

		// 메인 버튼 이미지
		ImageIcon back_main = new ImageIcon("C:\\Users\\kitri\\Desktop\\미니프로젝트\\icon\\back_main.png");
		Image back_main1 = back_main.getImage();
		Image back_main2 = back_main1.getScaledInstance(167, 50, Image.SCALE_SMOOTH);
		ImageIcon back_main3 = new ImageIcon(back_main2);
		/* 메인입력 버튼 */
		JButton btnMain = new JButton(back_main3);
		btnMain.setFont(new Font("굴림", Font.BOLD, 20));
		btnMain.setBounds(39, 20, 167, 50);
		contentPane.add(btnMain);
		btnMain.setBorderPainted(false); //버튼테두리 없애

		// 식단 버튼 이미지
		ImageIcon back_food = new ImageIcon("C:\\Users\\kitri\\Desktop\\미니프로젝트\\icon\\back_food.png");
		Image back_food1 = back_food.getImage();
		Image back_food2 = back_food1.getScaledInstance(167, 50, Image.SCALE_SMOOTH);
		ImageIcon back_food3 = new ImageIcon(back_food2);
		/* 식단입력 버튼 */
		JButton btnFoodlist = new JButton(back_food3);
		btnFoodlist.setBounds(218, 20, 167, 50);
		btnFoodlist.setFont(new Font("굴림", Font.BOLD, 20));
		contentPane.add(btnFoodlist);
		btnFoodlist.setBorderPainted(false); //버튼테두리 없애

		btnFoodlist.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				FoodList fl = new FoodList(user_id);
				fl.frame.setVisible(true);
				frame.dispose();

			}
		});
		// 운동 버튼 이미지
		ImageIcon back_ex = new ImageIcon("C:\\Users\\kitri\\Desktop\\미니프로젝트\\icon\\back_ex.png");
		Image back_ex1 = back_ex.getImage();
		Image back_ex2 = back_ex1.getScaledInstance(167, 50, Image.SCALE_SMOOTH);
		ImageIcon back_ex3 = new ImageIcon(back_ex2);
		/* 운동입력 버튼 */
		JButton btnExer = new JButton(back_ex3);
		btnExer.setBounds(397, 20, 167, 50);
		btnExer.setFont(new Font("굴림", Font.BOLD, 20));
		contentPane.add(btnExer);
		btnExer.setBorderPainted(false); //버튼테두리 없애


		btnExer.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ExerciseList ex = new ExerciseList(user_id);
				ex.frame.setVisible(true);
				frame.dispose();

			}
		});
		// 리포트 버튼 이미지
		ImageIcon back_report = new ImageIcon("C:\\Users\\kitri\\Desktop\\미니프로젝트\\icon\\back_report.png");
		Image back_report1 = back_report.getImage();
		Image back_report2 = back_report1.getScaledInstance(167, 50, Image.SCALE_SMOOTH);
		ImageIcon back_report3 = new ImageIcon(back_report2);
		/* 리포트 버튼 */
		JButton btnRept = new JButton(back_report3);
		btnRept.setBounds(576, 20, 167, 50);
		btnRept.setFont(new Font("굴림", Font.BOLD, 20));
		contentPane.add(btnRept);
		btnRept.setBorderPainted(false); //버튼테두리 없애


		btnRept.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Report rp = new Report(user_id);
				rp.frame.setVisible(true);
				frame.dispose();
			}
		});

		/* ===================== 라벨 =================== */
		JLabel lblCal1 = new JLabel(getCal());
		lblCal1.setForeground(Color.DARK_GRAY);
		lblCal1.setBounds(163, 108, 230, 58);
		lblCal1.setFont(new Font("HY헤드라인M", Font.PLAIN, 50));
		contentPane.add(lblCal1);
//		 getCal(lblCal1);

		JLabel lblSlash = new JLabel("/");
		lblSlash.setForeground(Color.DARK_GRAY);
		lblSlash.setBounds(398, 112, 64, 50);
		lblSlash.setFont(new Font("HY헤드라인M", Font.PLAIN, 50));
		contentPane.add(lblSlash);

		JLabel lblCal2 = new JLabel(getCal2());
		lblCal2.setForeground(Color.DARK_GRAY);
		lblCal2.setBounds(460, 105, 180, 65);
		contentPane.add(lblCal2);
		lblCal2.setFont(new Font("HY헤드라인M", Font.PLAIN, 50));
//		getCal2(lblCal2);

		JLabel lblText = new JLabel("하루권장 칼로리");
		lblText.setFont(new Font("HY헤드라인M", Font.PLAIN, 13));
		lblText.setForeground(Color.GRAY);
		lblText.setBounds(494, 169, 103, 22);
		contentPane.add(lblText);

		calTrafficLight(component, today); // 신호등 캘린더 출력
	}

	// 칼로리 얻어서 라벨에 뿌리기
	public String getCal() {
		// db에서 값 받아오기
		float day_eat_cal = 0;
		float day_use_cal = 0;
		float result = 0;
		DBConnect dao = new DBConnect();
		String sql;
		sql = "Select ifnull(Day_Use_Cal,0), ifnull(Day_Cal,0) from report where User_ID = '" + user_id
				+ "' and Report_Date = '" + today + "' LIMIT 0, 1;";
		ResultSet rs = dao.getInfo(sql);

		try {
			while (rs.next()) {
				day_use_cal = rs.getFloat("ifnull(Day_Use_Cal,0)");
				day_eat_cal = rs.getFloat("ifnull(Day_Cal,0)");
				result = day_eat_cal - day_use_cal;
				System.out.println(result + "=" + day_use_cal + "-" + day_eat_cal);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return String.valueOf(String.format("%.2f",result));
	}

	// 권장칼로리 얻어서 라벨에 뿌리기
	public String getCal2() {
		// 유저정보를 db에서 불러와서 권장칼로리 계산하기
		// 하루 권장 칼로리 = <표준체중>(자신의키 - 100)*0.9 * 활동지수
		// 활동지수 = HIGH : 60 MID : 40 LOW:20
		DBConnect dao = new DBConnect();
		String sql;

		sql = "Select Day_Recommend_Cal from user_personal where User_ID = '" + user_id + "';";
		// 회원번호별 유저정보 조회

		ResultSet rs = dao.getInfo(sql);

		try {
			while (rs.next()) {
				this.recommendCalory = rs.getFloat("Day_Recommend_Cal");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return String.valueOf(recommendCalory);
	}

	public void calTrafficLight(Component component[], LocalDate changedDate) {
		float[] arr = new float[32];
		// 현재 달력의 월 get
		// 해당월의 일별로 db.report의 총칼로리 계산
		for (int i = 0; i < arr.length; i++)
			arr[i] = 0.0f;
		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");

		LocalDate firstDay = changedDate.with(TemporalAdjusters.firstDayOfMonth()); // 2021-02-01
		LocalDate lastDay = changedDate.with(TemporalAdjusters.lastDayOfMonth()); // 2021-02-28
		DBConnect dao = new DBConnect();
		String sql;
		sql = "Select * from report where User_ID = '" + user_id + "' and Report_Date between '" + firstDay + "' and '"
				+ lastDay + "';";
		ResultSet rs = dao.getInfo(sql);
		// today객체에 들어있는 달의 첫날~끝날의 레포트DB의 총칼로리 계산
		try {
			while (rs.next()) {
				// 날짜별 총 칼로리 불러오기
				float totalCal = rs.getFloat("Day_Cal") - rs.getFloat("Day_Use_Cal");
				String date = transFormat.format(rs.getDate("Report_Date"));
				int i = Integer.parseInt(date.substring(8)); // 일자만 i에 담기
				System.out.println("i : " + i + " date: " + date);
				System.out.println(totalCal);
				arr[i] = totalCal;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		setCalColor(arr, component, recommendCalory);

	}

	public void setCalColor(float arr[], Component component[], float recommendCalory) {
		System.out.println("recommendCalory: " + recommendCalory);
		for (int i = 1; i < arr.length; i++) {
			if (arr[i] != 0) {
				// System.out.println("i: " + i);
				// System.out.println("arr[i] : " + arr[i]);
				if (arr[i] >= recommendCalory * 1.3)
					component[i + 7].setBackground(new Color(254,111,105)); // 권장칼로리보다 2000칼로리이상 섭취했을때
				else if (arr[i] >= recommendCalory*1.1)
					component[i + 7].setBackground(new Color(255,203,91)); // 권장칼로리보다 1000 칼로리이상 섭취했을때
				else 
					component[i + 7].setBackground(new Color(137,216,174)); // 권장칼로리-500 ~ 권장칼로리 + 999 칼로리이상 섭취했을때
				
			}
		}
	}
}
