package healthcare;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JPasswordField;

public class Health_Join extends JFrame {
	DBConnect DBConnect = new DBConnect();

	static final String JDBC_DRIVER = "org.mariadb.jdbc.Driver";
	static final String DB_URL = "jdbc:mariadb://192.168.0.41:3306/project1";
	static final String USER = "project1";
	static final String PASS = "kitri1950!@";

	private JPanel Join_panel;
	private JLabel lblJoin;
	private JTextField id;
	private JTextField psw;
	private JTextField name;
	private JTextField height;
	private JTextField weight;
	private JButton joinCompleteBtn;
	private JButton undoBtn;
	private JButton dcBtn;
	private boolean idcheck = true; // id중복체크 변수 (true이면 중복체크를 누르지 않음)
	private JPasswordField passwordField;

	// 에플리케이션 런치

	// 프레임생성
	public Health_Join() {

	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(700, 250, 383, 547);
		Join_panel = new JPanel();
		Join_panel.setBackground(Color.WHITE);
		Join_panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(Join_panel);
		Join_panel.setLayout(null);

		/* ======================= 라벨 ================================ */

		// 회원가입 라벨,폰트설정
		lblJoin = new JLabel("회원가입");
		lblJoin.setForeground(new Color(255, 255, 255));
		Font f1 = new Font("돋움", Font.BOLD, 20);// 돋움체
		Join_panel.setLayout(null);
		lblJoin.setFont(new Font("HY헤드라인M", Font.PLAIN, 17));
		lblJoin.setBounds(144, 0, 85, 64);
		Join_panel.add(lblJoin);

		// 아이디 라벨
		JLabel Idlabel = new JLabel("ID");
		Idlabel.setFont(new Font("HY헤드라인M", Font.PLAIN, 13));
		Idlabel.setBounds(48, 103, 20, 20);
		Join_panel.add(Idlabel);

		// 패스워드 라벨
		JLabel Pwlabel = new JLabel("PWD");
		Pwlabel.setFont(new Font("HY헤드라인M", Font.PLAIN, 13));
		Pwlabel.setBounds(39, 155, 35, 20);
		Join_panel.add(Pwlabel);

		// 이름 라벨
		JLabel Namelabel = new JLabel("이름");
		Namelabel.setFont(new Font("HY헤드라인M", Font.PLAIN, 13));
		Namelabel.setBounds(39, 205, 35, 20);
		Join_panel.add(Namelabel);

		// 키 라벨
		JLabel Heighlabel = new JLabel("키");
		Heighlabel.setFont(new Font("HY헤드라인M", Font.PLAIN, 13));
		Heighlabel.setBounds(45, 258, 20, 20);
		Join_panel.add(Heighlabel);

		// 몸무게 라벨
		JLabel Weighlabel = new JLabel("몸무게");
		Weighlabel.setFont(new Font("HY헤드라인M", Font.PLAIN, 13));
		Weighlabel.setBounds(32, 311, 46, 20);
		Join_panel.add(Weighlabel);

		// 활동지수 라벨
		JLabel Actlabel = new JLabel("활동지수");
		Actlabel.setFont(new Font("HY헤드라인M", Font.PLAIN, 13));
		Actlabel.setBounds(27, 361, 57, 20);
		Join_panel.add(Actlabel);

		// 성별 라벨
		JLabel Genlabel = new JLabel("성별");
		Genlabel.setFont(new Font("HY헤드라인M", Font.PLAIN, 13));
		Genlabel.setBounds(39, 403, 29, 20);
		Join_panel.add(Genlabel);

		/* ======================= 텍스트필드 ================================ */

		// 아이디 텍스트필드
		id = new JTextField();
		id.setFont(new Font("HY헤드라인M", Font.PLAIN, 12));
		id.setColumns(10);
		id.setBounds(100, 96, 160, 32);
		Join_panel.add(id);

		// 패스워드 텍스트필드				
		psw = new JPasswordField();
		psw.setFont(new Font("굴림", Font.PLAIN, 15));
		psw.setColumns(10);
		psw.setBounds(100, 148, 160, 32);
		Join_panel.add(psw);

		// 이름 텍스트필드
		name = new JTextField();
		name.setFont(new Font("HY헤드라인M", Font.PLAIN, 12));
		name.setColumns(10);
		name.setBounds(100, 200, 160, 32);
		Join_panel.add(name);

		// 키 텍스트필드
		height = new JTextField();
		height.setFont(new Font("HY헤드라인M", Font.PLAIN, 12));
		height.setColumns(10);
		height.setBounds(100, 252, 160, 32);
		Join_panel.add(height);

		// 몸무게 텍스트필드
		weight = new JTextField();
		weight.setFont(new Font("HY헤드라인M", Font.PLAIN, 12));
		weight.setColumns(10);
		weight.setBounds(100, 304, 160, 32);
		Join_panel.add(weight);

		/* ======================= 버튼 ================================ */

		// 회원가입 버튼 이미지
		ImageIcon signup_signup = new ImageIcon("C:\\Users\\kitri\\Desktop\\미니프로젝트\\icon\\signup_signup.png");
		Image signup_signup1 = signup_signup.getImage();
		Image signup_signup2 = signup_signup1.getScaledInstance(101, 29, Image.SCALE_SMOOTH);
		ImageIcon signup_signup3 = new ImageIcon(signup_signup2);
		// 회원가입 버튼
		joinCompleteBtn = new JButton(signup_signup3);
		joinCompleteBtn.setFont(new Font("HY헤드라인M", Font.PLAIN, 12));
		joinCompleteBtn.setBackground(Color.WHITE);
		joinCompleteBtn.setBounds(63, 443, 101, 29);
		Join_panel.add(joinCompleteBtn);

		// 취소 버튼 이미지
		ImageIcon signup_cancel = new ImageIcon("C:\\Users\\kitri\\Desktop\\미니프로젝트\\icon\\signup_cancel.png");
		Image signup_cancel1 = signup_cancel.getImage();
		Image signup_cancel2 = signup_cancel1.getScaledInstance(101, 29, Image.SCALE_SMOOTH);
		ImageIcon signup_cancel3 = new ImageIcon(signup_cancel2);
		// 취소 버튼
		undoBtn = new JButton(signup_cancel3);
		undoBtn.setFont(new Font("HY헤드라인M", Font.PLAIN, 12));
		undoBtn.setBackground(Color.WHITE);
		undoBtn.setForeground(Color.DARK_GRAY);
		undoBtn.setBounds(210, 443, 101, 29);
		Join_panel.add(undoBtn);
		undoBtn.setBorderPainted(false); // 버튼테두리 없애기

		// 중복확인 버튼 이미지
		ImageIcon signup_check = new ImageIcon("C:\\Users\\kitri\\Desktop\\미니프로젝트\\icon\\signup_check.png");
		Image signup_check1 = signup_check.getImage();
		Image signup_check2 = signup_check1.getScaledInstance(64, 52, Image.SCALE_SMOOTH);
		ImageIcon signup_check3 = new ImageIcon(signup_check2);
		// 중복확인 버튼
		dcBtn = new JButton(signup_check3);
		dcBtn.setFont(new Font("HY헤드라인M", Font.PLAIN, 12));
		dcBtn.setBackground(Color.WHITE);
		dcBtn.setForeground(Color.DARK_GRAY);
		dcBtn.setBounds(276, 85, 64, 52);
		Join_panel.add(dcBtn);
		dcBtn.setBorderPainted(false); // 버튼테두리 없애기

		/* ======================= 라디오박스, 버튼 ================================ */

		// 활동지수 라디오박스
		JRadioButton highBtn = new JRadioButton("High");
		highBtn.setBackground(Color.WHITE);
		highBtn.setFont(new Font("HY헤드라인M", Font.PLAIN, 12));
		highBtn.setBounds(92, 351, 57, 41);
		JRadioButton midBtn = new JRadioButton("Mid");
		midBtn.setBackground(Color.WHITE);
		midBtn.setFont(new Font("HY헤드라인M", Font.PLAIN, 12));
		midBtn.setBounds(157, 351, 57, 41);
		JRadioButton lowBtn = new JRadioButton("Low");
		lowBtn.setBackground(Color.WHITE);
		lowBtn.setFont(new Font("HY헤드라인M", Font.PLAIN, 12));
		lowBtn.setBounds(218, 351, 57, 41);

		// 성별 라디오박스
		JRadioButton menBtn = new JRadioButton("남");
		menBtn.setBackground(Color.WHITE);
		menBtn.setFont(new Font("HY헤드라인M", Font.PLAIN, 12));
		menBtn.setBounds(118, 394, 57, 41);
		JRadioButton womBtn = new JRadioButton("여");
		womBtn.setBackground(Color.WHITE);
		womBtn.setFont(new Font("HY헤드라인M", Font.PLAIN, 12));
		womBtn.setBounds(203, 394, 57, 41);

		// 라디오버튼그룹: 활동지수
		ButtonGroup Use_Act_Index = new ButtonGroup();
		Use_Act_Index.add(highBtn);
		Use_Act_Index.add(midBtn);
		Use_Act_Index.add(lowBtn);

		// 라디오버튼그룹: 성별
		ButtonGroup User_Gender = new ButtonGroup();

		User_Gender.add(menBtn);
		User_Gender.add(womBtn);

		// 라디오버튼추가
		Join_panel.add(new JLabel("활동지수"));
		Join_panel.add(highBtn);
		Join_panel.add(midBtn);
		Join_panel.add(lowBtn);

		Join_panel.add(new JLabel("성별"));
		Join_panel.add(menBtn);
		Join_panel.add(womBtn);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 367, 64);
		Join_panel.add(panel);
		panel.setBackground(new Color(112, 139, 151)); // 패널색상바꾸기
		
		

		setVisible(true);

		/* ======================= 액션리스너 ================================ */

		// 회원가입 액션
		joinCompleteBtn.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			@Override
			public void actionPerformed(ActionEvent e) {

				String User_ID = id.getText();
				String User_Password = psw.getText();
				String User_Name = name.getText();
				String User_Height = height.getText();
				String User_Weight = weight.getText();

				// 활동지수
				String Use_Act_Index = null;
				if (highBtn.isSelected()) {
					Use_Act_Index = "40";
				}
				if (midBtn.isSelected()) {
					Use_Act_Index = "35";
				}
				if (lowBtn.isSelected()) {
					Use_Act_Index = "20";
				}

				// user_personal에 권장칼로리 저장
				// float height = Float.parseFloat(User_Height);
				// int active = Integer.getInteger(Use_Act_Index);
				// Float result = (float) ((height - 100) * 0.9 * active);
				// String Day_Recommend_Cal = Float.toString(result);
				// System.out.println("권장칼로리" +result);

				// 성별
				String User_Gender = null;
				if (menBtn.isSelected()) {
					User_Gender = "M";
				}
				if (womBtn.isSelected()) {
					User_Gender = "W";
				}

				// 회원가입 시 예외처리
				if (User_ID.trim().isEmpty()) {
					JOptionPane.showMessageDialog(null, "아이디를 입력해주세요");
				} else if (User_Password.trim().isEmpty()) {
					JOptionPane.showMessageDialog(null, "비밀번호를 입력해주세요");
				} else if (User_Name.trim().isEmpty()) {
					JOptionPane.showMessageDialog(null, "이름을 입력해주세요");
				} else if (User_Height.trim().isEmpty()) {
					JOptionPane.showMessageDialog(null, "키(cm)를 입력해주세요");
				} else if (User_Weight.trim().isEmpty()) {
					JOptionPane.showMessageDialog(null, "몸무게(kg)를 입력해주세요");
				} else if (Use_Act_Index == null) {
					JOptionPane.showMessageDialog(null, "활동지수를 선택해주세요");
				} else if (User_Gender == null) {
					JOptionPane.showMessageDialog(null, "성별을 선택해주세요");
				} else if (idcheck) {
					JOptionPane.showMessageDialog(null, "중복확인을 해주세요");
				} else {
					Float result;
					float height = Float.parseFloat(User_Height);
					float active = Float.parseFloat(Use_Act_Index);
					if (User_Gender == "M") {
						// 표준체중(남자) = 키(m)*키(m)*22, 표준체중(여자) = 키(m)*키(m)*21
						result = (float) ((height * height * 0.0001) * 22 * active);
					} else {
						// User_Gender == "W"
						result = (float) ((height * height * 0.0001) * 21 * active);
					}

					String Day_Recommend_Cal = Float.toString(result);
					System.out.println("성별: " + User_Gender + "권장칼로리" + result);

					healthcare.DBConnect.createJoin(User_ID, User_Password, User_Name, User_Gender, User_Height,
							User_Weight, Use_Act_Index, Day_Recommend_Cal);

					JOptionPane.showMessageDialog(null, "회원가입이 완료되었습니다");

					new Health_Login();
					dispose();

				}
			}
		});

		// 취소 액션
		undoBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				Health_Login frame = new Health_Login();
			}
		});

		// 중복확인 액션
		dcBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String User_ID = id.getText();

				if (User_ID.trim().isEmpty()) {
					JOptionPane.showMessageDialog(null, "아이디를 입력해주세요");

				} else {

					idcheck = healthcare.DBConnect.idCheck(User_ID);

				}
			}

		});
	}
}