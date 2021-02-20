package healthcare;
//z
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.awt.Color;

import java.sql.*;

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

	// 에플리케이션 런치
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Health_Join frame = new Health_Join();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

	// 프레임생성
	public Health_Join() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 383, 547);
		Join_panel = new JPanel();
		Join_panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(Join_panel);
		Join_panel.setLayout(null);

		// 회원가입 라벨,폰트설정
		lblJoin = new JLabel("회원가입");
		Font f1 = new Font("돋움", Font.BOLD, 20);// 돋움체
		Join_panel.setLayout(null);
		lblJoin.setFont(new Font("HY헤드라인M", Font.BOLD, 20));
		lblJoin.setBounds(131, 30, 83, 32);
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

		// 아이디 텍스트필드
		id = new JTextField();
		id.setFont(new Font("HY헤드라인M", Font.PLAIN, 12));
		id.setColumns(10);
		id.setBounds(100, 96, 160, 32);
		Join_panel.add(id);

		// 패스워드 텍스트필드
		psw = new JTextField();
		psw.setFont(new Font("HY헤드라인M", Font.PLAIN, 12));
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

		// 회원가입 버튼
		joinCompleteBtn = new JButton("회원가입");
		joinCompleteBtn.setFont(new Font("HY헤드라인M", Font.PLAIN, 12));
		joinCompleteBtn.setBackground(Color.WHITE);
		joinCompleteBtn.setBounds(66, 443, 101, 29);
		Join_panel.add(joinCompleteBtn);

		// 취소 버튼
		undoBtn = new JButton("취소");
		undoBtn.setFont(new Font("HY헤드라인M", Font.PLAIN, 12));
		undoBtn.setBackground(Color.WHITE);
		undoBtn.setForeground(Color.DARK_GRAY);
		undoBtn.setBounds(201, 443, 101, 29);
		Join_panel.add(undoBtn);

		// 중복확인 버튼
		dcBtn = new JButton("<HTML>중복<br>확인</HTML>");
		dcBtn.setFont(new Font("HY헤드라인M", Font.PLAIN, 12));
		dcBtn.setBackground(Color.WHITE);
		dcBtn.setForeground(Color.DARK_GRAY);
		dcBtn.setBounds(276, 85, 64, 52);
		Join_panel.add(dcBtn);

		// 활동지수 라디오박스
		JRadioButton highBtn = new JRadioButton("High");
		highBtn.setFont(new Font("HY헤드라인M", Font.PLAIN, 12));
		highBtn.setBounds(92, 351, 57, 41);
		JRadioButton midBtn = new JRadioButton("Mid");
		midBtn.setFont(new Font("HY헤드라인M", Font.PLAIN, 12));
		midBtn.setBounds(157, 351, 57, 41);
		JRadioButton lowBtn = new JRadioButton("Low");
		lowBtn.setFont(new Font("HY헤드라인M", Font.PLAIN, 12));
		lowBtn.setBounds(218, 351, 57, 41);

		// 성별 라디오박스
		JRadioButton menBtn = new JRadioButton("남");
		menBtn.setFont(new Font("HY헤드라인M", Font.PLAIN, 12));
		menBtn.setBounds(118, 394, 57, 41);
		JRadioButton womBtn = new JRadioButton("여");
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

		setVisible(true);

		// 회원가입 액션
		joinCompleteBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				String User_ID = id.getText();
				String User_Password = psw.getText();
				String User_Name = name.getText();
				String User_Height = height.getText();
				String User_Weight = weight.getText();

				String Use_Act_Index = null;
				if (highBtn.isSelected()) {
					Use_Act_Index = "60";
				}
				if (midBtn.isSelected()) {
					Use_Act_Index = "40";
				}
				if (lowBtn.isSelected()) {
					Use_Act_Index = "20";
				}

				String User_Gender = null;
				if (menBtn.isSelected()) {
					User_Gender = "M";
				}
				if (womBtn.isSelected()) {
					User_Gender = "W";
				}

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
				} else {
					healthcare.DBConnect.createJoin(User_ID, User_Password, User_Name, User_Gender, User_Height,
							User_Weight, Use_Act_Index);
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

					healthcare.DBConnect.idCheck(User_ID);
				}
			}

		});
	}
}
