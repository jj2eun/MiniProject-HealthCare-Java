package healthcare;
import java.awt.Choice;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Date;
import java.sql.ResultSet;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class ExerciseList  {
	DBConnect db = new DBConnect();
	JFrame frame;
	private String user_id;
	DefaultTableModel model1;
	DefaultTableModel model2;
	Object ob1[] [] =new Object[0][3];
	Object ob2[] [] =new Object[0][7];
	String str1[] = {"운동코드","운동이름","소모칼로리"};
	String str2[] = {"PK", "사용자ID", "날짜", "운동코드", "운동이름", "소모칼로리", "운동시간"};
	String selectedDate;
	public ExerciseList() {initialize() ;}
	public ExerciseList(String User_ID) {
		user_id = User_ID;
		System.out.println("user id : "+user_id);
		initialize();
	}
	
	private void  initialize() {
		frame = new JFrame();
		frame.setTitle("ExerciseList");
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
		
	
		
		//====================================Choice====================================
	      Calendar calender = Calendar.getInstance();
	       
	       int today_year = calender.get(Calendar.YEAR);   
	       int today_month = calender.get(Calendar.MONTH)+1;
	       int today_day = calender.get(Calendar.DAY_OF_MONTH);
	       
	       String m = String.format("%02d", today_month); 
	       String d = String.format("%02d", today_day); 
	       
	       selectedDate = today_year + "-" + m + "-" + d;
	       
	       System.out.println(selectedDate);

//			JComboBox<String> combo = new JComboBox<String>();
//			combo.setBounds(135, 83, 264, 21);
//			contentPane.add(combo);
//			for (int i = 2021; i < 2023; i++) {
//				for (int j = 1; j <= 12; j++) {
//					for (int k = 1; k <= 31; k++) {
//						combo.addItem(String.valueOf(i + "년" + j + "월" + k + "일"));
//					}
//				}
//			}
//
//			combo.setSelectedItem(today_year + "년" + today_month + "월" + today_day + "일");
//
//			combo.addItemListener(new ItemListener() {
//				public void itemStateChanged(ItemEvent e) {
//
//					System.out.println(e.getItem());
//					selectedDate = (String) e.getItem();
//					System.out.println(selectedDate);
//
//				}
//			});
	    
		
		Choice choiceDate = new Choice();
		choiceDate.setBounds(135, 83, 264, 21);
		contentPane.add(choiceDate);
		for (int i = 2021 ; i < 2023 ; i++) {
			for(int j =1 ; j <=12 ; j++) {
				for(int k=1 ; k <=31 ; k++) {
					choiceDate.add(String.valueOf(i+"년"+j+"월"+k+"일"));
				}
			}
		}
		
		choiceDate.select(today_year + "년" + today_month + "월" + today_day + "일");
		
		choiceDate.addItemListener(new ItemListener() {
	           public void itemStateChanged(ItemEvent e) {
	        	   
	              System.out.println(e.getItem());
	              selectedDate = (String) e.getItem();
	              System.out.println(selectedDate);
	              
	           }
	        });
		//====================================JButton====================================
		JButton btnMain = new JButton("메인");
		btnMain.setBounds(12, 10, 121, 67);
		btnMain.setFont(new Font("굴림", Font.BOLD, 20));
		contentPane.add(btnMain);
		
		JButton btnFoodlist = new JButton("식단");
		btnFoodlist.setBounds(145, 10, 121, 67);
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
		
		JButton btnRept = new JButton("리포트");
		btnRept.setBounds(278, 10, 121, 67);
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
		
		
		JButton btnPlus = new JButton("+");
		btnPlus.setBounds(135, 511, 80, 40);
		btnPlus.setFont(new Font("굴림", Font.BOLD, 20));
		contentPane.add(btnPlus);
		
		JButton btnMinus = new JButton("-");
		btnMinus.setBounds(227, 511, 80, 40);
		btnMinus.setFont(new Font("굴림", Font.BOLD, 20));
		contentPane.add(btnMinus);
		
		JButton btnSave = new JButton("OK");
		btnSave.setBounds(319, 511, 80, 40);
		btnSave.setFont(new Font("굴림", Font.BOLD, 20));
		contentPane.add(btnSave);
		
		//====================================TextField====================================
		JTextField fieldSearch = new JTextField();
		fieldSearch.setBounds(432, 10, 320, 40);
		fieldSearch.setColumns(10);
		contentPane.add(fieldSearch);
		
		JTextField fieldCount = new JTextField();
		fieldCount.setBounds(70, 512, 58, 39);
		fieldCount.setColumns(10);
		contentPane.add(fieldCount);
		
		JTextField fieldTC = new JTextField();
		fieldTC.setBounds(541, 511, 231, 33);
		fieldTC.setColumns(10);
		contentPane.add(fieldTC);
		
		model1 = new DefaultTableModel(ob1,str1);
		
		try {
			 ResultSet rs = db.getInfo("SELECT * FROM exercise;");
			 while(rs.next()) {
				 String Exercise_no = rs.getString("Exercise_no");
				 String Exercise_Name = rs.getString("Exercise_Name");
				 Float Exercise_Cal = rs.getFloat("Exercise_Cal");
				 Object data[] = {Exercise_no, Exercise_Name, Exercise_Cal};
				 model1.addRow(data);
				
			 }
			 }catch(Exception e) {
				 System.out.println("select() 실행 오류 : " + e.getMessage());
			 }
		
		
		JTable table1 = new JTable(model1);
		
		JScrollPane js1 = new JScrollPane(table1);
		js1.setLocation(12,136);
		js1.setSize(383, 365);
		contentPane.add(js1);
		
		fieldSearch.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				String val = fieldSearch.getText();//fieldsearch에서 텍스트가져오기
				TableRowSorter<TableModel> trs = new TableRowSorter<>(table1.getModel());
				table1.setRowSorter(trs);		//table row정렬 
				trs.setRowFilter(RowFilter.regexFilter(val));
			
			}
		});
		
		
		
		model2 = new DefaultTableModel(ob2,str2);
		
		try {
			 ResultSet rs = db.getInfo("SELECT Ex_no, User_ID, Exercise_Date, u.Exercise_no, Exercise_Name, Exercise_Cal, Exercise_Time "
			 		+ "FROM user_exercise u LEFT OUTER JOIN exercise e "
			 		+ "USING(Exercise_no) "
			 		+ "WHERE (User_ID = \"" + user_id + "\") && (Exercise_Date ='" + selectedDate + "');");
			 while(rs.next()) {
				 int Ex_no = rs.getInt("Ex_no");
				 String User_ID = rs.getString("User_ID");
				 Date Exercise_Date = rs.getDate("Exercise_Date");
				 String Exercise_no = rs.getString("Exercise_no");
				 String Exercise_Name = rs.getString("Exercise_Name");
				 Float Exercise_Cal = rs.getFloat("Exercise_Cal");
				 Float Exercise_Time = rs.getFloat("Exercise_Time");
				 Object data[] = {Ex_no, User_ID, Exercise_Date, Exercise_no, Exercise_Name, Exercise_Cal, Exercise_Time};
				 model2.addRow(data);
				
			 }
			 }catch(Exception e) {
				 System.out.println("select() 실행 오류 : " + e.getMessage());
			 }
		
		
		JTable table2 = new JTable(model2);
		
		JScrollPane js2 = new JScrollPane(table2);
		js2.setLocation(412,136);
		js2.setSize(348, 365);
		contentPane.add(js2);
		
		
		//====================================JLabel====================================
		
		JLabel lblTC = new JLabel("총 칼로리");
		lblTC.setBounds(412, 511, 139, 40);
		lblTC.setHorizontalAlignment(SwingConstants.CENTER);
		lblTC.setFont(new Font("굴림", Font.BOLD, 20));
		contentPane.add(lblTC);
		
		
		
		JLabel lblTime = new JLabel("시간");
		lblTime.setHorizontalAlignment(SwingConstants.CENTER);
		lblTime.setFont(new Font("굴림", Font.BOLD, 16));
		lblTime.setBounds(12, 511, 46, 40);
		contentPane.add(lblTime);
		
		JLabel lblChoiceDate = new JLabel("날짜 선택");
		lblChoiceDate.setHorizontalAlignment(SwingConstants.CENTER);
		lblChoiceDate.setBounds(12, 83, 121, 21);
		contentPane.add(lblChoiceDate);
		
		JLabel lblMyexer = new JLabel("선택한 운동");
		lblMyexer.setFont(new Font("굴림", Font.BOLD, 25));
		lblMyexer.setHorizontalAlignment(SwingConstants.CENTER);
		lblMyexer.setBounds(411, 60, 348, 44);
		contentPane.add(lblMyexer);
	}
}
