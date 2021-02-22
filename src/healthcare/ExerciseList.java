package healthcare;

import java.awt.Choice;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

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

public class ExerciseList {
	DBConnect db = new DBConnect();
	float totalCal = (float) 0;
	JFrame frame;
	private String user_id;
	String selectedDate;
	Object ob1[][] = new Object[0][3];
	Object ob2[][] = new Object[0][2];
	DefaultTableModel model1;
	DefaultTableModel model2;
	String str1[] = { "��ڵ�", "�", "�Ҹ�Į�θ�" };
	String str2[] = { "��ڵ�", "�", "�Ҹ�Į�θ�", "��ð�" };


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
		contentPane.setBounds(0, 0, 794, 561);
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
		choiceDate.setBounds(135, 83, 264, 21);
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
		JButton btnMain = new JButton("����");
		btnMain.setBounds(12, 10, 121, 67);
		btnMain.setFont(new Font("����", Font.BOLD, 20));
		contentPane.add(btnMain);

		btnMain.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Main m = new Main(user_id);
				m.frame.setVisible(true);
				frame.dispose();

			}
		});

		JButton btnFoodlist = new JButton("�Ĵ�");
		btnFoodlist.setBounds(145, 10, 121, 67);
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

		JButton btnRept = new JButton("����Ʈ");
		btnRept.setBounds(278, 10, 121, 67);
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

		JButton btnPlus = new JButton("+");
		btnPlus.setBounds(135, 511, 80, 40);
		btnPlus.setFont(new Font("����", Font.BOLD, 20));
		contentPane.add(btnPlus);

		JButton btnMinus = new JButton("-");
		btnMinus.setBounds(227, 511, 80, 40);
		btnMinus.setFont(new Font("����", Font.BOLD, 20));
		contentPane.add(btnMinus);

		JButton btnSave = new JButton("OK");
		btnSave.setBounds(319, 511, 80, 40);
		btnSave.setFont(new Font("����", Font.BOLD, 20));
		contentPane.add(btnSave);

		// ====================================TextField====================================
		JTextField fieldSearch = new JTextField();
		fieldSearch.setBounds(432, 10, 320, 40);
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

		model1 = new DefaultTableModel(ob1, str1);
		model2 = new DefaultTableModel(ob2, str2);

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
			System.out.println("select() ���� ���� : " + e.getMessage());
		}

		JTable table1 = new JTable(model1);
		JTable table2 = new JTable(model2);

		// Exercis_no �÷� �����
		table1.setModel(model1);
		table1.removeColumn(table1.getColumnModel().getColumn(0));

		table2.setModel(model2);
		table2.removeColumn(table2.getColumnModel().getColumn(0));

		JScrollPane js1 = new JScrollPane(table1);
		js1.setBounds(12, 136, 383, 365);
		contentPane.add(js1);

		JScrollPane js2 = new JScrollPane(table2);
		js2.setBounds(432, 136, 317, 365);
		contentPane.add(js2);

		// ��¥ Ŭ���� DB�� ������ �����ͼ� �������� ���̺� �Ѹ���
		choiceDate.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				System.out.println(e.getItem());
				selectedDate = (String) e.getItem();
				model2.setRowCount(0);
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

					fieldTC.setText(totalCal + "");

				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});

		String choice_date = choiceDate.getItem(choiceDate.getSelectedIndex());
		System.out.println(choice_date);

		fieldSearch.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				String val = fieldSearch.getText();// fieldsearch���� �ؽ�Ʈ��������
				TableRowSorter<TableModel> trs = new TableRowSorter<>(table1.getModel());
				table1.setRowSorter(trs); // table row����
				trs.setRowFilter(RowFilter.regexFilter(val));

			}
		});

		// +��ư ����
		btnPlus.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				int row = table1.getSelectedRow();
				if (row == -1) {
					JOptionPane.showMessageDialog(null, "������ �������ּ���");
				}
				// ���� �Է¾��ҽ� �����޽���
				else if (fieldCount.getText().trim().isEmpty()) {
					JOptionPane.showMessageDialog(null, "������ �Է����ּ���");
				} else {
					Object sdb[] = { table1.getModel().getValueAt(row, 0), table1.getModel().getValueAt(row, 1),
							table1.getModel().getValueAt(row, 2), fieldCount.getText() }; // ���ĸ���Ʈ.���ĸ�, ���ĸ���Ʈ.Į�θ� ���, ����
					// ->
					// sdb[]�� ���

					sdb[2] = Float.valueOf(sdb[2].toString()) * Float.valueOf(sdb[3].toString());
					totalCal += (float) sdb[2];
					fieldTC.setText(totalCal + "");
					model2.addRow(sdb);
					// System.out.println("cal= " + sdb[2]);

				}

			}
		});

		// -��ư ����

		btnMinus.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int mdel = table2.getSelectedRow();

				if (mdel != -1) {
					System.out.println("mdel" + mdel);
					totalCal -= Float.parseFloat((String) (model2.getValueAt(mdel, 2)));
					fieldTC.setText(totalCal + "");
					model2.removeRow(mdel);
				}


			}
		});
		
		
//		// save��ư ����
//				btnSave.addActionListener(new ActionListener() {
//					public void actionPerformed(ActionEvent e) {
//
//						String choice_date = choiceDate.getItem(choiceDate.getSelectedIndex()); // ���õ� ��¥
//						System.out.println(choice_date);
//
//						db.delete(choice_date, user_id);
//						// ���� ��ư�� ������ �ð��뺰�� �ڵ�,������ �����ϱ�
//						// db�� ���� ���� -> rowcount==0�̸� ������ϰ� rowcount >= 1�̸� db�� ����
//						// DB����� �Լ� �ʿ�
//						if (model2.getRowCount() > 0) { // ���̺� ���� ������, DB�� ����
//							for (int i = 0; i < model2.getRowCount(); i++) {
//								db.user_eat(user_id, choice_date,model2.getValueAt(i, 0).toString(),
//										model2.getValueAt(i, 2).toString(), model2.getValueAt(i, 3).toString());
//							}
//						}
//
//						db.report(user_id, choice_date, "0.0", fieldTC.getText(), "0.0", "0.0", "0.0", "0.0");
//
//						JOptionPane.showMessageDialog(null, "������͸� �����߽��ϴ�");
//
//					}
//				});
		// ====================================JLabel====================================

		JLabel lblTC = new JLabel("�� Į�θ�");
		lblTC.setBounds(412, 511, 139, 40);
		lblTC.setHorizontalAlignment(SwingConstants.CENTER);
		lblTC.setFont(new Font("����", Font.BOLD, 20));
		contentPane.add(lblTC);

		JLabel lblTime = new JLabel("�ð�");
		lblTime.setHorizontalAlignment(SwingConstants.CENTER);
		lblTime.setFont(new Font("����", Font.BOLD, 16));
		lblTime.setBounds(12, 511, 46, 40);
		contentPane.add(lblTime);

		JLabel lblChoiceDate = new JLabel("��¥ ����");
		lblChoiceDate.setHorizontalAlignment(SwingConstants.CENTER);
		lblChoiceDate.setBounds(12, 83, 121, 21);
		contentPane.add(lblChoiceDate);

		JLabel lblMyexer = new JLabel("������ �");
		lblMyexer.setFont(new Font("����", Font.BOLD, 25));
		lblMyexer.setHorizontalAlignment(SwingConstants.CENTER);
		lblMyexer.setBounds(411, 60, 348, 44);
		contentPane.add(lblMyexer);
	}
}