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
		/* ======== Ķ���� ============== */
		JCalendar calendar = new JCalendar();
		calendar.setBounds(40, 212, 691, 350);
		contentPane.add(calendar);
		JPanel jpanel = calendar.getDayChooser().getDayPanel();
		Component component[] = jpanel.getComponents();
		calTrafficLight(component, today);
		// default�� ����(Date)�� �Ѱ��ش�
		
		calendar.getMonthChooser().addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				//System.out.println(evt.getPropertyName()+ ": " + evt.getNewValue());
				if (evt.getPropertyName().toString() == "month") {
					LocalDate date = today.withMonth(Integer.parseInt(evt.getNewValue().toString())+ 1);
					calTrafficLight(component, date);
				} // ����� property���� month�� ��� �ش� month�� date�� �Ѱ��ش�
				
			}
		}); // Ķ���� month���� �̺�Ʈ �ڵ鷯
		
		//====================================JButton====================================
		/* �Ĵ��Է� ��ư */
		JButton btnFoodlist = new JButton("�Ĵ�");
		btnFoodlist.setBounds(40, 20, 167, 50);
		btnFoodlist.setFont(new Font("����", Font.BOLD, 20));
		contentPane.add(btnFoodlist);
		
		btnFoodlist.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				FoodList fl = new FoodList(user_id);
				fl.frame.setVisible(true);
				frame.dispose();
				
			}
		});
		/* ��Է� ��ư */
		JButton btnExer = new JButton("�");
		btnExer.setBounds(304, 20, 167, 50);
		btnExer.setFont(new Font("����", Font.BOLD, 20));
		contentPane.add(btnExer);
		
		btnExer.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ExerciseList ex = new ExerciseList(user_id);
				ex.frame.setVisible(true);
				frame.dispose();
				
			}
		});
		/* ����Ʈ ��ư */
		JButton btnRept = new JButton("����Ʈ");
		btnRept.setBounds(564, 20, 167, 50);
		btnRept.setFont(new Font("����", Font.BOLD, 20));
		contentPane.add(btnRept);
				
		btnRept.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Report rp = new Report(user_id);
				rp.frame.setVisible(true);
				frame.dispose();
			}
		});
		
		/* ===================== �� ===================  */
		JLabel lblCal1 = new JLabel("0");
		lblCal1.setBounds(248, 111, 138, 50);
		lblCal1.setFont(new Font("���� ���", Font.PLAIN, 50));
		contentPane.add(lblCal1);
		//getCal(lblCal1);
		
		JLabel lblSlash = new JLabel("/");
		lblSlash.setBounds(359, 111, 64, 50);
		lblSlash.setFont(new Font("���� ���", Font.PLAIN, 50));
		contentPane.add(lblSlash);
		
		JLabel lblCal2 = new JLabel("0");
		lblCal2.setBounds(435, 111, 180, 50);
		contentPane.add(lblCal2);
		lblCal2.setFont(new Font("���� ���", Font.PLAIN, 50));
		getCal2(lblCal2);
		
		JLabel lblText = new JLabel("�Ϸ���� Į�θ�");
		lblText.setFont(new Font("���� ���", Font.BOLD, 13));
		lblText.setForeground(Color.GRAY);
		lblText.setBounds(472, 160, 103, 22);
		contentPane.add(lblText);
		
	}
	
	//Į�θ� �� �󺧿� �Ѹ���
	public void getCal(JLabel lbl) {
		// db���� �� �޾ƿ���
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
		//�޾ƿ°� �󺧷� ����
	}
	// ����Į�θ� �� �󺧿� �Ѹ���
	public void getCal2(JLabel lbl) {
		// ���������� db���� �ҷ��ͼ� ����Į�θ� ����ϱ�
		// �Ϸ� ���� Į�θ� = <ǥ��ü��>(�ڽ���Ű - 100)*0.9 * Ȱ������
		// Ȱ������  = HIGH : 60  MID : 40  LOW:20
		DBConnect dao = new DBConnect();
		String sql;
		Float height;
		int active = 0;
		float result = 0;
		sql = "Select * from user_personal where User_ID = '" + user_id + "';";
		// ȸ����ȣ�� �������� ��ȸ
		
		ResultSet rs = dao.getInfo(sql);
		
		try {
			while(rs.next()) {
				height = rs.getFloat("User_Height");
				active = rs.getInt("Use_Act_Index");
				result = (float) ((height - 100) * 0.9 * active);
				// �Ϸ� ���� Į�θ� ���
				System.out.println("height: " + height + "active: " + active);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		lbl.setText(Float.toString(result));
		// �󺧿� �Ѹ��� 
	}
	
	public void calTrafficLight(Component component[], LocalDate changedDate) {
		float[] arr = new float[32];
		// ���� �޷��� �� get
		// �ش���� �Ϻ��� db.report�� ��Į�θ� ���
		
		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");


		LocalDate firstDay = changedDate.with(TemporalAdjusters.firstDayOfMonth()); // 2021-02-01
		LocalDate lastDay = changedDate.with(TemporalAdjusters.lastDayOfMonth()); // 2021-02-28
		float recCal = 0;
		DBConnect dao = new DBConnect();
		String sql;
		sql = "Select * from report where User_ID = '" + user_id + "' and Report_Date between '" + firstDay +"' and '" + lastDay+ "';";
		ResultSet rs = dao.getInfo(sql);
		// today��ü�� ����ִ� ���� ù��~������ ����ƮDB�� ��Į�θ� ���
		try {
			while(rs.next()) {
				// ��¥�� �� Į�θ� �ҷ�����
				float totalCal = rs.getFloat("Day_Cal") - rs.getFloat("Day_Use_Cal");
				recCal = rs.getFloat("Day_Recommend_Cal");
				String date = transFormat.format(rs.getDate("Report_Date"));
				int i = Integer.parseInt(date.substring(8)); // ���ڸ� i�� ���
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
				if(arr[i] >= recCal + 300) component[i+7].setBackground(Color.magenta); // ����Į�θ����� 300Į�θ��̻� ����������
				else if(arr[i] >= recCal + 150) component[i+7].setBackground(Color.yellow); // ����Į�θ����� 150Į�θ��̻� ����������
				else if(arr[i] > recCal - 100) component[i+7].setBackground(Color.GREEN); // ����Į�θ����� -100Į�θ��̻� ����������
				else component[i+7].setBackground(Color.GREEN); // ������
			}
		}
	}
}
