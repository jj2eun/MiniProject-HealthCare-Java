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

public class Health_Login extends JFrame {
	
	
	private JPanel Login_panel;
	private JTextField loginID;
	private JButton loginBtn, joinBtn;
	private JLabel lblJoin;
	private JButton exitBtn;
	private JPasswordField loginPWD;

		//���ø����̼� ��ġ
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Health_Login frame = new Health_Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
		//������ ����
	public Health_Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 418, 295);
		Login_panel = new JPanel();
		Login_panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(Login_panel);
		Login_panel.setLayout(null);
		Login_panel.setLayout(null);
		
		lblJoin = new JLabel("�α���");
		Font f1 = new Font("����", Font.BOLD, 20);// ����ü
		Login_panel.setLayout(null);
		lblJoin.setFont(new Font("HY������M", Font.BOLD, 20));
		lblJoin.setBounds(156, 10, 73, 32);
		Login_panel.add(lblJoin);
		
		//�α��� ��
		JLabel lblLogin = new JLabel("ID");
		lblLogin.setFont(new Font("HY������M", Font.PLAIN, 15));
		lblLogin.setBounds(45, 71, 29, 35);
		Login_panel.add(lblLogin);
		
		//�н����� ��
		JLabel lblPassword = new JLabel("PWD");
		lblPassword.setFont(new Font("HY������M", Font.PLAIN, 15));
		lblPassword.setBounds(37,128,36,35);
		Login_panel.add(lblPassword);
		
		//�α��� �ؽ�Ʈ�ʵ�
		loginID = new JTextField();
		loginID.setFont(new Font("HY������M", Font.PLAIN, 12));
		loginID.setBounds(86, 71, 176, 35);
		Login_panel.add(loginID);
		loginID.setColumns(10);
		
		//ȸ������ ��ư
		joinBtn = new JButton("ȸ������");
		joinBtn.setFont(new Font("HY������M", Font.PLAIN, 12));
		joinBtn.setBackground(Color.WHITE);
		joinBtn.setBounds(71, 202, 104, 29);
		Login_panel.add(joinBtn);
		
		//���� ��ư
		exitBtn = new JButton("����");
		exitBtn.setFont(new Font("HY������M", Font.PLAIN, 12));
		exitBtn.setBackground(Color.WHITE);
		exitBtn.setBounds(232, 202, 104, 29);
		Login_panel.add(exitBtn);
		
		//�α��� ��ư
		loginBtn = new JButton("�α���");
		
		loginBtn.setFont(new Font("HY������M", Font.PLAIN, 15));
		loginBtn.setBackground(Color.WHITE);
		loginBtn.setBounds(281, 71, 78, 92);
		Login_panel.add(loginBtn);
		
		loginPWD = new JPasswordField();
		loginPWD.setFont(new Font("Arial", Font.PLAIN, 15));
		loginPWD.setBounds(85, 128, 176, 32);
		Login_panel.add(loginPWD);
		
		setVisible(true);
		
		//ȸ������ �׼�
		joinBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Health_Join frame = new Health_Join();
				dispose();
				
			}
		});
		
		//���� �׼�
		exitBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		//�α��� �׼�
		loginBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				String User_ID = loginID.getText();
				char[] PSW = loginPWD.getPassword();
				String User_Password = String.valueOf(PSW);
				
				System.out.println(User_ID);
				System.out.println(User_Password);
				System.out.println("loingPWD: "+ loginPWD.getPassword());
			
				DBConnect DBConnect = new DBConnect();
				healthcare.DBConnect.Login(User_ID, User_Password);
			
				
//				if(User_ID1.equals(loginID.getText()) && User_Password2.equals(loginPWD.getPassword())) {
//					JOptionPane.showMessageDialog(null, "�α��μ���");
//				}else {
//					JOptionPane.showMessageDialog(null, "�α��ν���");
//				}
				
				
//				dispose();
				
			}
		});
		
		
	}
}