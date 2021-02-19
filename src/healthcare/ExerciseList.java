package healthcare;
import java.awt.Choice;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
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

public class ExerciseList {
	DBConnect db = new DBConnect();
	
	
	 JFrame frame;
	 private String user_id;
	 
		Object ob[] [] =new Object[0][3];
		DefaultTableModel model;
		String str[] = {"Exercise_no","Exercise_Name","Exercise_Cal"};
		String str2[]={"Food_Name","Food_Cal"};
		
	public static void main(String[] args) {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						FoodList f = new FoodList();
						f.frame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
			new FoodList();
			
		}
	
	
	 public ExerciseList() {initialize() ;}
	 public ExerciseList(String User_ID) {
		user_id = User_ID;
		System.out.println("user id : "+user_id);
		initialize() ;
		
		
		
	}
	 private void initialize() {
		 
		 
		 
		frame = new JFrame();
		frame.setTitle("FoodList");
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
		
	
		//==============================Choice==============================
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
		
		
		Choice choiceTime = new Choice();
		choiceTime.setBounds(135, 110, 264, 21);
		contentPane.add(choiceTime);
		
		choiceTime.add("아침");
		choiceTime.add("점심");
		choiceTime.add("저녁");
		choiceTime.add("간식");
		//==============================JButton==============================
		JButton btnMain = new JButton("메인");
		btnMain.setBounds(12, 10, 121, 67);
		btnMain.setFont(new Font("굴림", Font.BOLD, 20));
		contentPane.add(btnMain);
		
		btnMain.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Main m = new Main(user_id);
				m.frame.setVisible(true);
				frame.dispose();
				
			}
		});
		
		
		JButton btnExer = new JButton("운동");
		btnExer.setBounds(145, 10, 121, 67);
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
		
		
		JButton btnSearch = new JButton("검색");
		btnSearch.setBounds(706, 10, 66, 40);
		contentPane.add(btnSearch);
		
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
		
	
		
		//==============================JTextField==============================
		JTextField fieldSearch = new JTextField();
		fieldSearch.setBounds(463, 10, 231, 40);
		contentPane.add(fieldSearch);
		fieldSearch.setColumns(10);
		
		
		
		JTextField fieldCount = new JTextField();
		fieldCount.setBounds(70, 512, 58, 39);
		contentPane.add(fieldCount);
		fieldCount.setColumns(10);
		
		JTextField fieldTC = new JTextField();
		fieldTC.setBounds(541, 511, 231, 33);
		contentPane.add(fieldTC);
		fieldTC.setColumns(10);
		
	
		//==================== JList==============================
		
		
		
		
		model = new DefaultTableModel(ob,str);

		
		try {
			 ResultSet rs = db.getInfo("SELECT * FROM exercise;");
			 while(rs.next()) {
				 String Exercise_no = rs.getString("Exercise_no");
				 String Exercise_Name = rs.getString("Exercise_Name");
				 Float Exercise_Cal = rs.getFloat("Exercise_Cal");
				 Object data[] = {Exercise_no,Exercise_Name,Exercise_Cal};
				 model.addRow(data);
				
			 }
			 }catch(Exception e) {
				 System.out.println("select() 실행 오류 : " + e.getMessage());
			 }
		
		
		JTable table = new JTable(model);


		JScrollPane js = new JScrollPane(table);
		js.setLocation(12,136);
		js.setSize(383, 365);
		contentPane.add(js);


		fieldSearch.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				String val = fieldSearch.getText();//fieldsearch에서 텍스트가져오기
				TableRowSorter<TableModel> trs = new TableRowSorter<>(table.getModel());
				table.setRowSorter(trs);		//table row정렬 
				trs.setRowFilter(RowFilter.regexFilter(val));
			
			}
		});
		
		//==============================Label==============================
		JLabel lblTC = new JLabel("총 칼로리");
		lblTC.setBounds(412, 511, 139, 40);
		lblTC.setHorizontalAlignment(SwingConstants.CENTER);
		lblTC.setFont(new Font("굴림", Font.BOLD, 20));
		contentPane.add(lblTC);
		

		
		JLabel lblCount = new JLabel("수량");
		lblCount.setHorizontalAlignment(SwingConstants.CENTER);
		lblCount.setFont(new Font("굴림", Font.BOLD, 16));
		lblCount.setBounds(12, 511, 46, 40);
		contentPane.add(lblCount);
		
		JLabel lblChoiceDate = new JLabel("날짜 선택");
		lblChoiceDate.setHorizontalAlignment(SwingConstants.CENTER);
		lblChoiceDate.setBounds(12, 83, 121, 21);
		contentPane.add(lblChoiceDate);
		
		JLabel lblChoiceTime = new JLabel("시간 선택");
		lblChoiceTime.setHorizontalAlignment(SwingConstants.CENTER);
		lblChoiceTime.setBounds(12, 110, 121, 21);
		contentPane.add(lblChoiceTime);
	}
	 
}
