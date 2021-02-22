package healthcare;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
//z
public class Health_Login extends JFrame {
	DBConnect DBConnect = new DBConnect();

	private JPanel Login_panel;
	private JTextField loginID;
	private JButton loginBtn, joinBtn;
	private JLabel lblJoin;
	private JButton exitBtn;
	private JPasswordField loginPWD;

	
	// 프레임 생성
	public Health_Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 418, 295);
		Login_panel = new JPanel();
		Login_panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(Login_panel);
		Login_panel.setLayout(null);
		Login_panel.setLayout(null);

		lblJoin = new JLabel("로그인");
		Font f1 = new Font("돋움", Font.BOLD, 20);// 돋움체
		Login_panel.setLayout(null);
		lblJoin.setFont(new Font("HY헤드라인M", Font.BOLD, 20));
		lblJoin.setBounds(156, 10, 73, 32);
		Login_panel.add(lblJoin);

		// 로그인 라벨
		JLabel lblLogin = new JLabel("ID");
		lblLogin.setFont(new Font("HY헤드라인M", Font.PLAIN, 15));
		lblLogin.setBounds(45, 71, 29, 35);
		Login_panel.add(lblLogin);

		// 패스워드 라벨
		JLabel lblPassword = new JLabel("PWD");
		lblPassword.setFont(new Font("HY헤드라인M", Font.PLAIN, 15));
		lblPassword.setBounds(37, 128, 36, 35);
		Login_panel.add(lblPassword);

		// 로그인 텍스트필드
		loginID = new JTextField();
		loginID.setFont(new Font("HY헤드라인M", Font.PLAIN, 12));
		loginID.setBounds(86, 71, 176, 35);
		Login_panel.add(loginID);
		loginID.setColumns(10);

		// 회원가입 버튼
		joinBtn = new JButton("회원가입");
		joinBtn.setFont(new Font("HY헤드라인M", Font.PLAIN, 12));
		joinBtn.setBackground(Color.WHITE);
		joinBtn.setBounds(71, 202, 104, 29);
		Login_panel.add(joinBtn);

		// 종료 버튼
		exitBtn = new JButton("종료");
		exitBtn.setFont(new Font("HY헤드라인M", Font.PLAIN, 12));
		exitBtn.setBackground(Color.WHITE);
		exitBtn.setBounds(232, 202, 104, 29);
		Login_panel.add(exitBtn);

		// 로그인 버튼
		loginBtn = new JButton("로그인");

		loginBtn.setFont(new Font("HY헤드라인M", Font.PLAIN, 15));
		loginBtn.setBackground(Color.WHITE);
		loginBtn.setBounds(281, 71, 78, 92);
		Login_panel.add(loginBtn);

		loginPWD = new JPasswordField();
		loginPWD.setFont(new Font("Arial", Font.PLAIN, 15));
		loginPWD.setBounds(85, 128, 176, 32);
		Login_panel.add(loginPWD);

		setVisible(true);

		// 회원가입 액션
		joinBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Health_Join frame = new Health_Join();
				dispose();

			}
		});

		// 종료 액션
		exitBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		// 로그인 액션
		loginBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				String User_ID = loginID.getText();
				char[] PSW = loginPWD.getPassword();
				String User_Password = String.valueOf(PSW);

				// 로그인 후 넘어가기
				int login = healthcare.DBConnect.login(User_ID, User_Password);
				if (login >= 1) {
					Main main = new Main(User_ID);
					dispose();
					main.frame.setVisible(true);
				}
				else {
					JOptionPane.showMessageDialog(null, "로그인 실패");
				}
			}
		});

	}
}
