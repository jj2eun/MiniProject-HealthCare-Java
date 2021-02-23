package healthcare;

import java.awt.Choice;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Calendar;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
import java.awt.Color;

public class ExerciseList {
	DBConnect db = new DBConnect();
	TableRowSorter<TableModel> tableRowSorter2; // 필터 정렬할때 출력되는 테이블의 값을 저장하는 변수
	float totalCal = (float) 0;
	LocalDate today = LocalDate.now();
	JFrame frame;
	private String user_id;
	String selectedDate;
	Object ob1[][] = new Object[0][3];
	Object ob2[][] = new Object[0][2];
	DefaultTableModel model1;
	DefaultTableModel model2;

	String str1[] = { "운동코드", "운동", "소모칼로리(1시간당)" };
	String str2[] = { "운동코드", "운동", "소모칼로리", "운동시간" };

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ExerciseList e = new ExerciseList();
					e.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		new ExerciseList();

	}

	public ExerciseList() {
		initialize();
	}

	public ExerciseList(String User_ID) {
		user_id = User_ID;
		System.out.println("user id : " + user_id);
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setTitle("ExerciseList");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 800, 600);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);

		JPanel contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBounds(0, 0, 794, 571);
		frame.getContentPane().add(contentPane);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		contentPane.setVisible(true);

		// ====================================Choice====================================
		Calendar calender = Calendar.getInstance();

		int today_year = calender.get(Calendar.YEAR);
		int today_month = calender.get(Calendar.MONTH) + 1;
		int today_day = calender.get(Calendar.DAY_OF_MONTH);

		String m = String.format("%02d", today_month);
		String d = String.format("%02d", today_day);

		selectedDate = today_year + "-" + m + "-" + d;

		Choice choiceDate = new Choice();
		choiceDate.setBounds(135, 60, 264, 21);
		contentPane.add(choiceDate);

		Calendar calender2 = Calendar.getInstance();
		for (int i = 2021; i < 2023; i++) {
			for (int j = 1; j <= 12; j++) {
				calender2.set(i, j - 1, 1);
				for (int k = 1; k <= calender2.getActualMaximum(Calendar.DAY_OF_MONTH); k++) {
					choiceDate.add(String.valueOf(i + "-" + j + "-" + k));
				}
			}
		}

		choiceDate.select(today_year + "-" + today_month + "-" + today_day);

		// ====================================JButton====================================
		// 메인 버튼 이미지
		ImageIcon ex_main = new ImageIcon("C:\\Users\\kitri\\Desktop\\미니프로젝트\\icon\\ex_main.png");
		Image ex_main1 = ex_main.getImage();
		Image ex_main2 = ex_main1.getScaledInstance(121, 40, Image.SCALE_SMOOTH);
		ImageIcon ex_main3 = new ImageIcon(ex_main2);
		// 메인버튼
		JButton btnMain = new JButton(ex_main3);
		btnMain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main m = new Main(user_id);
				m.frame.setVisible(true);
				frame.dispose();
				
			}
		});
		btnMain.setBounds(12, 10, 121, 40);
		btnMain.setFont(new Font("굴림", Font.BOLD, 20));
		contentPane.add(btnMain);
		btnMain.setBorderPainted(false); //버튼테두리 없애

		// 식단 버튼 이미지
		ImageIcon ex_food = new ImageIcon("C:\\Users\\kitri\\Desktop\\미니프로젝트\\icon\\ex_food.png");
		Image ex_food1 = ex_food.getImage();
		Image ex_food2 = ex_food1.getScaledInstance(121, 40, Image.SCALE_SMOOTH);
		ImageIcon ex_food3 = new ImageIcon(ex_food2);
		// 식단버튼
		JButton btnFoodlist = new JButton(ex_food3);
		btnFoodlist.setBounds(145, 10, 121, 40);
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
		ImageIcon ex_ex = new ImageIcon("C:\\Users\\kitri\\Desktop\\미니프로젝트\\icon\\ex_ex.png");
		Image ex_ex1 = ex_ex.getImage();
		Image ex_ex2 = ex_ex1.getScaledInstance(121, 40, Image.SCALE_SMOOTH);
		ImageIcon ex_ex3 = new ImageIcon(ex_ex2);
		// 운동버튼
		JButton btnExer = new JButton(ex_ex3);
		btnExer.setFont(new Font("굴림", Font.BOLD, 20));
		btnExer.setBounds(278, 10, 121, 40);
		contentPane.add(btnExer);
		btnExer.setBorderPainted(false); //버튼테두리 없애


		// 리포트 버튼 이미지
		ImageIcon ex_report = new ImageIcon("C:\\Users\\kitri\\Desktop\\미니프로젝트\\icon\\ex_report.png");
		Image ex_report1 = ex_report.getImage();
		Image ex_report2 = ex_report1.getScaledInstance(121, 40, Image.SCALE_SMOOTH);
		ImageIcon ex_report3 = new ImageIcon(ex_report2);
		// 리포트 버튼
		JButton btnRept = new JButton(ex_report3);
		btnRept.setBounds(411, 10, 121, 40);
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

		// 플러스 버튼 이미지
		ImageIcon ex_plus = new ImageIcon("C:\\Users\\kitri\\Desktop\\미니프로젝트\\icon\\ex_plus.png");
		Image ex_plus1 = ex_plus.getImage();
		Image ex_plus2 = ex_plus1.getScaledInstance(80, 40, Image.SCALE_SMOOTH);
		ImageIcon ex_plus3 = new ImageIcon(ex_plus2);
		// 플러스 버튼
		JButton btnPlus = new JButton(ex_plus3);
		btnPlus.setBounds(135, 511, 80, 40);
		btnPlus.setFont(new Font("굴림", Font.BOLD, 20));
		contentPane.add(btnPlus);
		btnPlus.setBorderPainted(false); //버튼테두리 없애


		// 마이너스 버튼 이미지
		ImageIcon ex_minus = new ImageIcon("C:\\Users\\kitri\\Desktop\\미니프로젝트\\icon\\ex_minus.png");
		Image ex_minus1 = ex_minus.getImage();
		Image ex_minus2 = ex_minus1.getScaledInstance(80, 40, Image.SCALE_SMOOTH);
		ImageIcon ex_minus3 = new ImageIcon(ex_minus2);
		// 마이너스
		JButton btnMinus = new JButton(ex_minus3);
		btnMinus.setBounds(227, 511, 80, 40);
		btnMinus.setFont(new Font("굴림", Font.BOLD, 20));
		contentPane.add(btnMinus);
		btnMinus.setBorderPainted(false); //버튼테두리 없애


		// ok 버튼 이미지
		ImageIcon ex_ok = new ImageIcon("C:\\Users\\kitri\\Desktop\\미니프로젝트\\icon\\ex_ok.png");
		Image ex_ok1 = ex_ok.getImage();
		Image ex_ok2 = ex_ok1.getScaledInstance(80, 40, Image.SCALE_SMOOTH);
		ImageIcon ex_ok3 = new ImageIcon(ex_ok2);
		// ok버튼
		JButton btnSave = new JButton(ex_ok3);
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnSave.setBounds(319, 511, 80, 40);
		btnSave.setFont(new Font("굴림", Font.BOLD, 20));
		contentPane.add(btnSave);
		btnSave.setBorderPainted(false); //버튼테두리 없애


		//

		// ====================================TextField====================================
		JTextField fieldSearch = new JTextField();
		fieldSearch.setBounds(135, 87, 264, 21);
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

		model1 = new DefaultTableModel(ob1, str1) {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int i, int c) {
				return false;
			}
		};

		model2 = new DefaultTableModel(ob2, str2) {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int i, int c) {
				return false;
			}
		};

		try {
			ResultSet rs = db.getInfo("SELECT * FROM exercise;");
			while (rs.next()) {
				String Exercise_no = rs.getString("Exercise_no");
				String Exercise_Name = rs.getString("Exercise_Name");
				Float Exercise_Cal = rs.getFloat("Exercise_Cal");
				Object data[] = { Exercise_no, Exercise_Name, Exercise_Cal };
				model1.addRow(data);

			}
		} catch (Exception e) {
			System.out.println("select() 실행 오류 : " + e.getMessage());
		}

		JTable table1 = new JTable(model1);
		JTable table2 = new JTable(model2);
		tableRowSorter2 = new TableRowSorter<>(table1.getModel()); // 시작할때, 기존 음식테이블 저장
		// Exercis_no 컬럼 숨기기
		table1.setModel(model1);
		table1.removeColumn(table1.getColumnModel().getColumn(0));

		table2.setModel(model2);
		table2.removeColumn(table2.getColumnModel().getColumn(0));

		JScrollPane js1 = new JScrollPane(table1);
		js1.setBounds(12, 118, 383, 383);
		contentPane.add(js1);
		js1.getViewport().setBackground(Color.WHITE);

		JScrollPane js2 = new JScrollPane(table2);
		js2.setBounds(432, 118, 340, 383);
		contentPane.add(js2);
		js2.getViewport().setBackground(Color.WHITE);
		
		//테이블 폰트바꾸기
		table1.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		table1.getTableHeader().setFont(new Font("HY헤드라인M", Font.PLAIN, 12));
		table2.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		table2.getTableHeader().setFont(new Font("HY헤드라인M", Font.PLAIN, 12));

		fieldTC.setText(showUserExerciseList(today.toString()) + "");
		// 날짜 클릭시 DB에 데이터 가져와서 테이블에 뿌리기
		choiceDate.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				System.out.println(e.getItem());
				selectedDate = (String) e.getItem(); // 선택한 날짜 얻기
				model2.setRowCount(0); // 오른쪽 테이블 초기화
				fieldTC.setText(showUserExerciseList(selectedDate) + ""); // 오른쪽테이블 출력 + 총칼로리 출력
			}
		});

		String choice_date = choiceDate.getItem(choiceDate.getSelectedIndex());
		System.out.println(choice_date);

		fieldSearch.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				String val = fieldSearch.getText();// fieldsearch에서 텍스트가져오기
				TableRowSorter<TableModel> tableRowSorter = new TableRowSorter<>(table1.getModel());
				table1.setRowSorter(tableRowSorter); // table row정렬
				tableRowSorter.setRowFilter(RowFilter.regexFilter(val)); // 필터링하기

				tableRowSorter2 = tableRowSorter;
			}
		});

		// +버튼 동작
		btnPlus.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				int row = table1.getSelectedRow();
				if (row == -1) {
					JOptionPane.showMessageDialog(null, "음식을 선택해주세요");
				}
				// 수량 입력안할시 에러메시지
				else if (fieldCount.getText().trim().isEmpty()) {
					JOptionPane.showMessageDialog(null, "수량을 입력해주세요");
				} else {
					Object sdb[] = { tableRowSorter2.getModel().getValueAt(table1.convertRowIndexToModel(row), 0),
							tableRowSorter2.getModel().getValueAt(table1.convertRowIndexToModel(row), 1),
							tableRowSorter2.getModel().getValueAt(table1.convertRowIndexToModel(row), 2),
							fieldCount.getText() }; // 음식리스트.음식명, 음식리스트.칼로리 얻기, 수량
					// ->
					// sdb[]에 담기

					sdb[2] = Float.valueOf(sdb[2].toString()) * Float.valueOf(sdb[3].toString());
					totalCal += (float) sdb[2];
					fieldTC.setText(totalCal + "");
					model2.addRow(sdb);
					// System.out.println("cal= " + sdb[2]);

				}

			}
		});

		// -버튼 동작

		btnMinus.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int mdel = table2.getSelectedRow();

				if (mdel != -1) {
					System.out.println("mdel" + mdel);
					totalCal -= Float.valueOf(model2.getValueAt(mdel, 2).toString());
					fieldTC.setText(totalCal + "");
					model2.removeRow(mdel);
				}

			}
		});

		// save버튼 동작
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String choice_date = choiceDate.getItem(choiceDate.getSelectedIndex()); // 선택된 날짜
				System.out.println(choice_date);

				db.delete_exercise(choice_date, user_id);
				// 저장 버튼을 누르면 시간대별로 코드,수량만 저장하기
				// db를 전부 지워 -> rowcount==0이면 저장안하고 rowcount >= 1이면 db에 저장
				// DB지우는 함수 필요
				if (model2.getRowCount() > 0) { // 테이블에 값이 있으면, DB에 저장
					for (int i = 0; i < model2.getRowCount(); i++) {
						db.user_exercise(user_id, choice_date, model2.getValueAt(i, 0).toString(),
								model2.getValueAt(i, 2).toString(), model2.getValueAt(i, 3).toString());
					}
				}

				db.exer_report(user_id, choice_date, fieldTC.getText());

				JOptionPane.showMessageDialog(null, "운동데이터를 저장했습니다");

			}
		});
		// ====================================JLabel====================================

		JLabel lblChoiceDate_1 = new JLabel("검색");
		lblChoiceDate_1.setFont(new Font("HY헤드라인M", Font.PLAIN, 12));
		lblChoiceDate_1.setForeground(Color.DARK_GRAY);
		lblChoiceDate_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblChoiceDate_1.setBounds(12, 87, 121, 21);
		contentPane.add(lblChoiceDate_1);
		
		JLabel lblTC = new JLabel("총 칼로리");
		lblTC.setForeground(Color.DARK_GRAY);
		lblTC.setBounds(411, 511, 132, 33);
		lblTC.setHorizontalAlignment(SwingConstants.CENTER);
		lblTC.setFont(new Font("HY헤드라인M", Font.PLAIN, 16));
		contentPane.add(lblTC);

		JLabel lblTime = new JLabel("시간");
		lblTime.setForeground(Color.DARK_GRAY);
		lblTime.setHorizontalAlignment(SwingConstants.CENTER);
		lblTime.setFont(new Font("HY헤드라인M", Font.PLAIN, 16));
		lblTime.setBounds(0, 511, 70, 40);
		contentPane.add(lblTime);

		JLabel lblChoiceDate = new JLabel("날짜 선택");
		lblChoiceDate.setFont(new Font("HY헤드라인M", Font.PLAIN, 12));
		lblChoiceDate.setForeground(Color.DARK_GRAY);
		lblChoiceDate.setHorizontalAlignment(SwingConstants.CENTER);
		lblChoiceDate.setBounds(12, 60, 121, 21);
		contentPane.add(lblChoiceDate);

		JLabel lblMyexer = new JLabel("선택한 운동");
		lblMyexer.setForeground(Color.DARK_GRAY);
		lblMyexer.setBackground(Color.WHITE);
		lblMyexer.setFont(new Font("HY헤드라인M", Font.PLAIN, 18));
		lblMyexer.setHorizontalAlignment(SwingConstants.CENTER);
		lblMyexer.setBounds(432, 60, 340, 58);
		contentPane.add(lblMyexer);
	}

	public float showUserExerciseList(String date) {
		totalCal = 0;

		System.out.println();
		try {
			ResultSet rs_exercise = db.getInfo(
					"SELECT Ex_no, User_ID, Exercise_Date, u.Exercise_no, Exercise_Name, e.Exercise_Cal, Exercise_Time "
							+ "FROM user_exercise u LEFT OUTER JOIN exercise e " + "USING(Exercise_no) "
							+ "WHERE (User_ID = '" + user_id + "') AND (Exercise_Date ='" + selectedDate + "');");

			while (rs_exercise.next()) {
				String Exercise_no = rs_exercise.getString("Exercise_no");
				String Exercise_Name = rs_exercise.getString("Exercise_Name");
				String Exercise_Cal = rs_exercise.getString("Exercise_Cal");
				totalCal += Float.parseFloat(Exercise_Cal);
				Float Exercise_Time = rs_exercise.getFloat("Exercise_Time");
				Object data_exercise[] = { Exercise_no, Exercise_Name, Exercise_Cal, Exercise_Time };

				model2.addRow(data_exercise);

			}

		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		return totalCal;

	}
}