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

public class FoodList {
	DBConnect db = new DBConnect();
	TableRowSorter<TableModel> tableRowSorter2; // 필터 정렬할때 출력되는 테이블의 값을 저장하는 변수
	float totalCal = (float) 0;
	LocalDate today = LocalDate.now();
	JFrame frame;
	private String user_id;
	String selectedDate;

	// jtable
	Object ob[][] = new Object[0][3];
	Object ob2[][] = new Object[0][2];
	DefaultTableModel model;
	DefaultTableModel morningmodel;
	DefaultTableModel lunchmodel;
	DefaultTableModel dinnermodel;
	DefaultTableModel dessertmodel;
	String str[] = { "번호", "음식", "칼로리(1회 제공량)" };
	String str2[] = { "번호", "음식", "칼로리", "수량" };

	/**
	 * @wbp.parser.constructor
	 */

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

	public FoodList() {
		initialize();
	}

	public FoodList(String User_ID) {
		user_id = User_ID;
		System.out.println("user id : " + user_id);
		initialize();

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
		contentPane.setBackground(Color.WHITE);
		contentPane.setBounds(0, 0, 794, 561);
		frame.getContentPane().add(contentPane);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setVisible(true);
		contentPane.setLayout(null);

		// ==============================Choice==============================
		Calendar calender = Calendar.getInstance();

		int today_year = calender.get(Calendar.YEAR);
		int today_month = calender.get(Calendar.MONTH) + 1;
		int today_day = calender.get(Calendar.DAY_OF_MONTH);

		String m = String.format("%02d", today_month);
		String d = String.format("%02d", today_day);

		selectedDate = today_year + "-" + m + "-" + d;

		Choice choiceDate = new Choice();
		choiceDate.setBounds(133, 60, 264, 21);
		contentPane.add(choiceDate);

		for (int i = 2021; i < 2023; i++) {
			for (int j = 1; j <= 12; j++) {
				for (int k = 1; k <= 31; k++) {
					choiceDate.add(String.valueOf(i + "-" + j + "-" + k));
				}
			}
		}

		choiceDate.select(today_year + "-" + today_month + "-" + today_day);

		Choice choiceTime = new Choice();
		choiceTime.setBounds(133, 87, 264, 21);
		contentPane.add(choiceTime);

		choiceTime.add("아침");
		choiceTime.add("점심");
		choiceTime.add("저녁");
		choiceTime.add("간식");
		// ==============================JButton==============================
		// 메인 버튼 이미지
		ImageIcon food_main = new ImageIcon("C:\\Users\\kitri\\Desktop\\미니프로젝트\\icon\\food_main.png");
		Image food_main1 = food_main.getImage();
		Image food_main2 = food_main1.getScaledInstance(121, 40, Image.SCALE_SMOOTH);
		ImageIcon food_main3 = new ImageIcon(food_main2);
		// 메인버튼
		JButton btnMain = new JButton(food_main3);
		btnMain.setBounds(12, 10, 121, 40);
		btnMain.setFont(new Font("굴림", Font.BOLD, 20));
		contentPane.add(btnMain);
		btnMain.setBorderPainted(false); // 버튼테두리 없애

		btnMain.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Main m = new Main(user_id);
				m.frame.setVisible(true);
				frame.dispose();

			}
		});
		// 식단 버튼 이미지
		ImageIcon food_food = new ImageIcon("C:\\Users\\kitri\\Desktop\\미니프로젝트\\icon\\food_food.png");
		Image food_food1 = food_food.getImage();
		Image food_food2 = food_food1.getScaledInstance(121, 40, Image.SCALE_SMOOTH);
		ImageIcon food_food3 = new ImageIcon(food_food2);
		// 식단버튼
		JButton btnFoodlist = new JButton(food_food3);
		btnFoodlist.setFont(new Font("굴림", Font.BOLD, 20));
		btnFoodlist.setBounds(144, 10, 121, 40);
		contentPane.add(btnFoodlist);
		btnFoodlist.setBorderPainted(false); // 버튼테두리 없애

		// 운동 버튼 이미지
		ImageIcon food_ex = new ImageIcon("C:\\Users\\kitri\\Desktop\\미니프로젝트\\icon\\food_ex.png");
		Image food_ex1 = food_ex.getImage();
		Image food_ex2 = food_ex1.getScaledInstance(121, 40, Image.SCALE_SMOOTH);
		ImageIcon food_ex3 = new ImageIcon(food_ex2);
		JButton btnExer = new JButton(food_ex3);
		btnExer.setBounds(277, 10, 121, 40);
		btnExer.setFont(new Font("굴림", Font.BOLD, 20));
		contentPane.add(btnExer);
		btnExer.setBorderPainted(false); // 버튼테두리 없애

		btnExer.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ExerciseList ex = new ExerciseList(user_id);
				ex.frame.setVisible(true);
				frame.dispose();

			}
		});

		// 리포트 버튼 이미지
		ImageIcon food_report = new ImageIcon("C:\\Users\\kitri\\Desktop\\미니프로젝트\\icon\\food_report.png");
		Image food_report1 = food_report.getImage();
		Image food_report2 = food_report1.getScaledInstance(121, 40, Image.SCALE_SMOOTH);
		ImageIcon food_report3 = new ImageIcon(food_report2);
		// 리포트 버튼
		JButton btnRept = new JButton(food_report3);
		btnRept.setBounds(410, 10, 121, 40);
		btnRept.setFont(new Font("굴림", Font.BOLD, 20));
		contentPane.add(btnRept);
		btnRept.setBorderPainted(false); // 버튼테두리 없애

		btnRept.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Report rp = new Report(user_id);
				rp.frame.setVisible(true);
				frame.dispose();
			}
		});

		// 플러스 버튼 이미지
		ImageIcon food_plus = new ImageIcon("C:\\Users\\kitri\\Desktop\\미니프로젝트\\icon\\food_plus.png");
		Image food_plus1 = food_plus.getImage();
		Image food_plus2 = food_plus1.getScaledInstance(80, 40, Image.SCALE_SMOOTH);
		ImageIcon food_plus3 = new ImageIcon(food_plus2);
		// 플러스버튼
		JButton btnPlus = new JButton(food_plus3);
		btnPlus.setBounds(135, 511, 80, 40);
		btnPlus.setFont(new Font("굴림", Font.BOLD, 20));
		contentPane.add(btnPlus);
		btnPlus.setBorderPainted(false); // 버튼테두리 없애

		// 마이너스 버튼 이미지
		ImageIcon food_minus = new ImageIcon("C:\\Users\\kitri\\Desktop\\미니프로젝트\\icon\\food_minus.png");
		Image food_minus1 = food_minus.getImage();
		Image food_minus2 = food_minus1.getScaledInstance(80, 40, Image.SCALE_SMOOTH);
		ImageIcon food_minus3 = new ImageIcon(food_minus2);
		// 마이너스 버튼
		JButton btnMinus = new JButton(food_minus3);
		btnMinus.setBounds(227, 511, 80, 40);
		btnMinus.setFont(new Font("굴림", Font.BOLD, 20));
		contentPane.add(btnMinus);
		btnMinus.setBorderPainted(false); // 버튼테두리 없애

		// ok 버튼 이미지
		ImageIcon food_ok = new ImageIcon("C:\\Users\\kitri\\Desktop\\미니프로젝트\\icon\\food_ok.png");
		Image food_ok1 = food_ok.getImage();
		Image food_ok2 = food_ok1.getScaledInstance(80, 40, Image.SCALE_SMOOTH);
		ImageIcon food_ok3 = new ImageIcon(food_ok2);
		// ok버튼
		JButton btnSave = new JButton(food_ok3);
		btnSave.setBounds(319, 511, 80, 40);
		btnSave.setFont(new Font("굴림", Font.BOLD, 20));
		contentPane.add(btnSave);
		btnSave.setBorderPainted(false); // 버튼테두리 없애

		// ==============================JTextField==============================
		JTextField fieldSearch = new JTextField();
		fieldSearch.setBounds(133, 120, 264, 21);
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

		model = new DefaultTableModel(ob, str);
		morningmodel = new DefaultTableModel(ob2, str2);
		lunchmodel = new DefaultTableModel(ob2, str2);
		dinnermodel = new DefaultTableModel(ob2, str2);
		dessertmodel = new DefaultTableModel(ob2, str2);

		try {
			ResultSet rs = db.getInfo("SELECT * FROM nutrient;");
			while (rs.next()) {
				String Food_no = rs.getString("Food_no");
				String Food_Name = rs.getString("Food_Name");
				Float Food_Cal = rs.getFloat("Food_Cal");
				Object data[] = { Food_no, Food_Name, Food_Cal };
				model.addRow(data);

			}
		} catch (Exception e) {
			System.out.println("select() 실행 오류 : " + e.getMessage());
		}

		JTable table = new JTable(model) {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int i, int c) {
				return false;
			}
		};

		JTable morningTable = new JTable(morningmodel) {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int i, int c) {
				return false;
			}
		};

		JTable lunchTable = new JTable(lunchmodel) {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int i, int c) {
				return false;
			}
		};

		JTable dinnerTable = new JTable(dinnermodel) {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int i, int c) {
				return false;
			}
		};

		JTable dessertTable = new JTable(dessertmodel) {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int i, int c) {
				return false;
			}
		};
		tableRowSorter2 = new TableRowSorter<>(table.getModel()); // 시작할때, 기존 음식테이블 저장

		// Food_Code 컬럼 숨기기
		table.setModel(model);
		table.removeColumn(table.getColumnModel().getColumn(0));

		morningTable.setModel(morningmodel);
		morningTable.removeColumn(morningTable.getColumnModel().getColumn(0));

		lunchTable.setModel(lunchmodel);
		lunchTable.removeColumn(lunchTable.getColumnModel().getColumn(0));

		dinnerTable.setModel(dinnermodel);
		dinnerTable.removeColumn(dinnerTable.getColumnModel().getColumn(0));

		dessertTable.setModel(dessertmodel);
		dessertTable.removeColumn(dessertTable.getColumnModel().getColumn(0));

		JScrollPane js = new JScrollPane(table);
		js.setBounds(12, 151, 383, 350);
		contentPane.add(js);
		js.getViewport().setBackground(Color.WHITE);
		

		JScrollPane morningjs = new JScrollPane(morningTable);
		morningjs.setBounds(456, 83, 316, 95);
		contentPane.add(morningjs);
		morningjs.getViewport().setBackground(Color.WHITE);

		JScrollPane lunchjs = new JScrollPane(lunchTable);
		lunchjs.setBounds(456, 188, 316, 95);
		contentPane.add(lunchjs);
		lunchjs.getViewport().setBackground(Color.WHITE);

		JScrollPane dinnerjs = new JScrollPane(dinnerTable);
		dinnerjs.setBounds(456, 293, 316, 95);
		contentPane.add(dinnerjs);
		dinnerjs.getViewport().setBackground(Color.WHITE);

		JScrollPane dessertjs = new JScrollPane(dessertTable);
		dessertjs.setBounds(456, 398, 316, 95);
		contentPane.add(dessertjs);
		dessertjs.getViewport().setBackground(Color.WHITE);

		//테이블 폰트바꾸기
		table.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		table.getTableHeader().setFont(new Font("HY헤드라인M", Font.PLAIN, 12));
		morningTable.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		morningTable.getTableHeader().setFont(new Font("HY헤드라인M", Font.PLAIN, 12));
		lunchTable.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		lunchTable.getTableHeader().setFont(new Font("HY헤드라인M", Font.PLAIN, 12));
		dinnerTable.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		dinnerTable.getTableHeader().setFont(new Font("HY헤드라인M", Font.PLAIN, 12));
		dessertTable.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		dessertTable.getTableHeader().setFont(new Font("HY헤드라인M", Font.PLAIN, 12));

		// 디폴트로 오늘 데이터, 총칼로리 출력
		fieldTC.setText(showUserFoodlist(today.toString()) + "");
		// 날짜 클릭시 DB에 데이터 가져와서 아점저간 테이블에 뿌리기
		choiceDate.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				selectedDate = (String) e.getItem(); // 바뀐 날짜
				// 오른쪽 테이블 초기화
				morningmodel.setRowCount(0);
				lunchmodel.setRowCount(0);
				dinnermodel.setRowCount(0);
				dessertmodel.setRowCount(0);
				// UserFoodlist 출력
				fieldTC.setText(showUserFoodlist(selectedDate) + "");
			}
		});

		String choice_date = choiceDate.getItem(choiceDate.getSelectedIndex());
		System.out.println(choice_date);

		// +버튼 동작
		btnPlus.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();

				if (row == -1) {
					JOptionPane.showMessageDialog(null, "음식을 선택해주세요");
				}
				// 수량 입력안할시 에러메시지
				else if (fieldCount.getText().trim().isEmpty()) {
					JOptionPane.showMessageDialog(null, "수량을 입력해주세요");
				} else {
					Object sdb[] = { tableRowSorter2.getModel().getValueAt(table.convertRowIndexToModel(row), 0),
							tableRowSorter2.getModel().getValueAt(table.convertRowIndexToModel(row), 1),
							tableRowSorter2.getModel().getValueAt(table.convertRowIndexToModel(row), 2),
							fieldCount.getText() }; // 음식리스트.음식명, 음식리스트.칼로리 , 수량

					if (choiceTime.getItem(choiceTime.getSelectedIndex()) == "아침") {
						sdb[2] = Float.valueOf(sdb[2].toString()) * Float.valueOf(sdb[3].toString());
						totalCal += (float) sdb[2];
						fieldTC.setText(totalCal + "");
						morningmodel.addRow(sdb);
					} else if (choiceTime.getItem(choiceTime.getSelectedIndex()) == "점심") {
						sdb[2] = Float.valueOf(sdb[2].toString()) * Float.valueOf(sdb[3].toString());
						totalCal += (float) sdb[2];
						fieldTC.setText(totalCal + "");
						lunchmodel.addRow(sdb);
					} else if (choiceTime.getItem(choiceTime.getSelectedIndex()) == "저녁") {
						sdb[2] = Float.valueOf(sdb[2].toString()) * Float.valueOf(sdb[3].toString());
						totalCal += (float) sdb[2];
						fieldTC.setText(totalCal + "");
						dinnermodel.addRow(sdb);
					} else if (choiceTime.getItem(choiceTime.getSelectedIndex()) == "간식") {
						sdb[2] = Float.valueOf(sdb[2].toString()) * Float.valueOf(sdb[3].toString());
						totalCal += (float) sdb[2];
						fieldTC.setText(totalCal + "");
						dessertmodel.addRow(sdb);

					}
				}

			}
		});

		// -버튼 동작

		btnMinus.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int mdel = morningTable.getSelectedRow();
				int ldel = lunchTable.getSelectedRow();
				int ddel = dinnerTable.getSelectedRow();
				int dsdel = dessertTable.getSelectedRow();
				if (mdel != -1) {
					System.out.println("mdel" + mdel);
					totalCal -= Float.valueOf(morningmodel.getValueAt(mdel, 2).toString());
					fieldTC.setText(totalCal + "");
					morningmodel.removeRow(mdel);
				}
				if (ldel != -1) {
					System.out.println("ldel" + ldel);
					totalCal -= Float.valueOf(lunchmodel.getValueAt(ldel, 2).toString());
					fieldTC.setText(totalCal + "");
					lunchmodel.removeRow(ldel);
				}
				if (ddel != -1) {
					System.out.println("ddel" + ddel);
					totalCal -= Float.valueOf(dinnermodel.getValueAt(ddel, 2).toString());
					fieldTC.setText(totalCal + "");
					dinnermodel.removeRow(ddel);
				}
				if (dsdel != -1) {
					System.out.println("dsdel" + dsdel);
					totalCal -= Float.valueOf(dessertmodel.getValueAt(dsdel, 2).toString());
					fieldTC.setText(totalCal + "");
					dessertmodel.removeRow(dsdel);

				}

			}
		});

		// save버튼 동작
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String choice_date = choiceDate.getItem(choiceDate.getSelectedIndex()); // 선택된 날짜
				System.out.println(choice_date);
				String choice_time = choiceTime.getItem(choiceTime.getSelectedIndex()); // 선택된 시간대(아점저간)

				db.delete(choice_date, user_id);
				// 저장 버튼을 누르면 시간대별로 코드,수량만 저장하기
				// db를 전부 지워 -> rowcount==0이면 저장안하고 rowcount >= 1이면 db에 저장
				// DB지우는 함수 필요
				if (morningmodel.getRowCount() > 0) { // 테이블에 값이 있으면, DB에 저장
					for (int i = 0; i < morningmodel.getRowCount(); i++) {
						db.user_eat(user_id, choice_date, "아침", morningmodel.getValueAt(i, 0).toString(),
								morningmodel.getValueAt(i, 2).toString(), morningmodel.getValueAt(i, 3).toString());
					}
				}
				if (lunchmodel.getRowCount() > 0) {
					for (int i = 0; i < lunchmodel.getRowCount(); i++) {
						db.user_eat(user_id, choice_date, "점심", lunchmodel.getValueAt(i, 0).toString(),
								lunchmodel.getValueAt(i, 2).toString(), lunchmodel.getValueAt(i, 3).toString());
					}
				}
				if (dinnermodel.getRowCount() > 0) {
					for (int i = 0; i < dinnerTable.getRowCount(); i++) {
						db.user_eat(user_id, choice_date, "저녁", dinnermodel.getValueAt(i, 0).toString(),
								dinnermodel.getValueAt(i, 2).toString(), dinnermodel.getValueAt(i, 3).toString());
					}
				}
				if (dessertmodel.getRowCount() > 0) {
					for (int i = 0; i < dessertmodel.getRowCount(); i++) {
						db.user_eat(user_id, choice_date, "간식", dessertmodel.getValueAt(i, 0).toString(),
								dessertmodel.getValueAt(i, 2).toString(), dessertmodel.getValueAt(i, 3).toString());
					}
				}

				db.food_report(user_id, choice_date, fieldTC.getText());

				JOptionPane.showMessageDialog(null, "음식데이터를 저장했습니다");

			}
		});

		// 검색으로 테이블 조회
		fieldSearch.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				String val = fieldSearch.getText();// fieldsearch에서 텍스트가져오기
				TableRowSorter<TableModel> tableRowSorter = new TableRowSorter<>(table.getModel());
				table.setRowSorter(tableRowSorter); // table row정렬
				tableRowSorter.setRowFilter(RowFilter.regexFilter(val)); // 필터링 하기

				tableRowSorter2 = tableRowSorter;

			}
		});

		// ==============================Label==============================
		JLabel lblTC = new JLabel("총 칼로리");
		lblTC.setForeground(Color.DARK_GRAY);
		lblTC.setBounds(412, 511, 139, 33);
		lblTC.setHorizontalAlignment(SwingConstants.CENTER);
		lblTC.setFont(new Font("HY헤드라인M", Font.PLAIN, 16));
		contentPane.add(lblTC);

		JLabel lblMorning = new JLabel("아침");
		lblMorning.setForeground(Color.DARK_GRAY);
		lblMorning.setBounds(405, 83, 51, 95);
		lblMorning.setHorizontalAlignment(SwingConstants.CENTER);
		lblMorning.setFont(new Font("HY헤드라인M", Font.PLAIN, 16));
		contentPane.add(lblMorning);

		JLabel lblLunch = new JLabel("점심");
		lblLunch.setForeground(Color.DARK_GRAY);
		lblLunch.setBounds(405, 188, 51, 95);
		lblLunch.setHorizontalAlignment(SwingConstants.CENTER);
		lblLunch.setFont(new Font("HY헤드라인M", Font.PLAIN, 16));
		contentPane.add(lblLunch);

		JLabel lblDinner = new JLabel("저녁");
		lblDinner.setForeground(Color.DARK_GRAY);
		lblDinner.setBounds(405, 293, 51, 95);
		lblDinner.setHorizontalAlignment(SwingConstants.CENTER);
		lblDinner.setFont(new Font("HY헤드라인M", Font.PLAIN, 16));
		contentPane.add(lblDinner);

		JLabel lblDessert = new JLabel("간식");
		lblDessert.setForeground(Color.DARK_GRAY);
		lblDessert.setBounds(405, 398, 51, 95);
		lblDessert.setHorizontalAlignment(SwingConstants.CENTER);
		lblDessert.setFont(new Font("HY헤드라인M", Font.PLAIN, 16));
		contentPane.add(lblDessert);

		JLabel lblCount = new JLabel("수량");
		lblCount.setForeground(Color.DARK_GRAY);
		lblCount.setBounds(12, 511, 58, 40);
		lblCount.setHorizontalAlignment(SwingConstants.CENTER);
		lblCount.setFont(new Font("HY헤드라인M", Font.PLAIN, 16));
		contentPane.add(lblCount);

		JLabel lblChoiceDate = new JLabel("날짜 선택");
		lblChoiceDate.setFont(new Font("HY헤드라인M", Font.PLAIN, 12));
		lblChoiceDate.setForeground(Color.DARK_GRAY);
		lblChoiceDate.setBounds(12, 60, 121, 21);
		lblChoiceDate.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblChoiceDate);

		JLabel lblChoiceTime = new JLabel("시간 선택");
		lblChoiceTime.setFont(new Font("HY헤드라인M", Font.PLAIN, 12));
		lblChoiceTime.setForeground(Color.DARK_GRAY);
		lblChoiceTime.setBounds(12, 87, 121, 21);
		lblChoiceTime.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblChoiceTime);

		JLabel lblChoiceTime_1 = new JLabel("검색");
		lblChoiceTime_1.setFont(new Font("HY헤드라인M", Font.PLAIN, 12));
		lblChoiceTime_1.setForeground(Color.DARK_GRAY);
		lblChoiceTime_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblChoiceTime_1.setBounds(12, 120, 121, 21);
		contentPane.add(lblChoiceTime_1);

	}

	// 오른쪽 UserFoodList 출력 함수
	public float showUserFoodlist(String date) {
		selectedDate = date;
		totalCal = 0;

		System.out.println();
		try {
			ResultSet rs_morning = db.getInfo(
					"select nutrient.Food_no, nutrient.Food_Name, user_eat.Food_cal, user_eat.Food_Count from user_eat left outer join nutrient on user_eat.Food_no = nutrient.Food_no where User_ID = '"
							+ user_id + "' and Eat_Time='아침' and Eat_date=" + "'" + selectedDate + "'");
			ResultSet rs_lunch = db.getInfo(
					"select nutrient.Food_no, nutrient.Food_Name, user_eat.Food_cal, user_eat.Food_Count from user_eat left outer join nutrient on user_eat.Food_no = nutrient.Food_no where User_ID = '"
							+ user_id + "' and Eat_Time='점심' and Eat_date=" + "'" + selectedDate + "'");
			ResultSet rs_dinner = db.getInfo(
					"select nutrient.Food_no, nutrient.Food_Name, user_eat.Food_cal, user_eat.Food_Count from user_eat left outer join nutrient on user_eat.Food_no = nutrient.Food_no where User_ID = '"
							+ user_id + "' and Eat_Time='저녁' and Eat_date=" + "'" + selectedDate + "'");
			ResultSet rs_dessert = db.getInfo(
					"select nutrient.Food_no, nutrient.Food_Name, user_eat.Food_cal, user_eat.Food_Count from user_eat left outer join nutrient on user_eat.Food_no = nutrient.Food_no where User_ID = '"
							+ user_id + "' and Eat_Time='간식' and Eat_date=" + "'" + selectedDate + "'");
			while (rs_morning.next()) {
				String Food_no = rs_morning.getString("Food_no");
				String Food_Name = rs_morning.getString("Food_Name");
				String Food_cal = rs_morning.getString("Food_cal");
				totalCal += Float.parseFloat(Food_cal);
				Float Food_Count = rs_morning.getFloat("Food_Count");
				Object data_morning[] = { Food_no, Food_Name, Food_cal, Food_Count };
				System.out.println(Food_no + Food_Name);
				morningmodel.addRow(data_morning);

			}
			while (rs_lunch.next()) {
				String Food_no = rs_lunch.getString("Food_no");
				String Food_Name = rs_lunch.getString("Food_Name");
				String Food_cal = rs_lunch.getString("Food_cal");
				totalCal += Float.parseFloat(Food_cal);
				Float Food_Count = rs_lunch.getFloat("Food_Count");
				Object data_lunch[] = { Food_no, Food_Name, Food_cal, Food_Count };
				lunchmodel.addRow(data_lunch);
			}
			while (rs_dinner.next()) {
				String Food_no = rs_dinner.getString("Food_no");
				String Food_Name = rs_dinner.getString("Food_Name");
				String Food_cal = rs_dinner.getString("Food_cal");
				totalCal += Float.parseFloat(Food_cal);
				Float Food_Count = rs_dinner.getFloat("Food_Count");
				Object data_dinner[] = { Food_no, Food_Name, Food_cal, Food_Count };
				dinnermodel.addRow(data_dinner);
			}
			while (rs_dessert.next()) {
				String Food_no = rs_dessert.getString("Food_no");
				String Food_Name = rs_dessert.getString("Food_Name");
				String Food_cal = rs_dessert.getString("Food_cal");
				totalCal += Float.parseFloat(Food_cal);
				Float Food_Count = rs_dessert.getFloat("Food_Count");
				Object data_dessert[] = { Food_no, Food_Name, Food_cal, Food_Count };
				dessertmodel.addRow(data_dessert);
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return totalCal;

	}
}
