package healthcare;

import java.awt.Choice;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.InputMethodListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.InputMethodEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;

public class FoodList {
	DBConnect db = new DBConnect();
	Calendar cal = Calendar.getInstance();

	JFrame frame;
	private String user_id;

	// jtable
	Object ob[][] = new Object[0][3];
	Object ob2[][] = new Object[0][2];
	DefaultTableModel model;
	DefaultTableModel morningmodel;
	DefaultTableModel lunchmodel;
	DefaultTableModel dinnermodel;
	DefaultTableModel dessertmodel;
	String str[] = { "��ȣ", "����", "Į�θ�" };
	String str2[] = { "��ȣ", "����", "Į�θ�", "����" };
	String str3[] = { "����", "Į�θ�", "����" };
	String selectedDate;

	/**
	 * @wbp.parser.constructor
	 */

	// public static void main(String[] args) {
	// EventQueue.invokeLater(new Runnable() {
	// public void run() {
	// try {
	// FoodList f = new FoodList();
	// f.frame.setVisible(true);
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// }
	// });
	// new FoodList();
	//
	// }

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
		contentPane.setBounds(0, 0, 794, 561);
		frame.getContentPane().add(contentPane);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		contentPane.setVisible(true);

		// ==============================Choice==============================

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

		for (int i = 2021; i < 2023; i++) {
			for (int j = 1; j <= 12; j++) {
				for (int k = 1; k <= 31; k++) {
					choiceDate.add(String.valueOf(i + "-" + j + "-" + k));
				}
			}
		}

		choiceDate.select(today_year + "-" + today_month + "-" + today_day);

		Choice choiceTime = new Choice();
		choiceTime.setBounds(135, 110, 264, 21);
		contentPane.add(choiceTime);

		choiceTime.add("��ħ");
		choiceTime.add("����");
		choiceTime.add("����");
		choiceTime.add("����");
		// ==============================JButton==============================
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

		JButton btnExer = new JButton("�");
		btnExer.setBounds(145, 10, 121, 67);
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

		// ==============================JTextField==============================
		JTextField fieldSearch = new JTextField();
		fieldSearch.setBounds(508, 29, 264, 21);
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
			System.out.println("select() ���� ���� : " + e.getMessage());
		}

		JTable table = new JTable(model);
		JTable morningTable = new JTable(morningmodel);
		JTable lunchTable = new JTable(lunchmodel);
		JTable dinnerTable = new JTable(dinnermodel);
		JTable dessertTable = new JTable(dessertmodel);

		// Food_Code �÷� �����
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
		js.setLocation(12, 136);
		js.setSize(383, 365);
		contentPane.add(js);

		JScrollPane morningjs = new JScrollPane(morningTable);
		morningjs.setLocation(463, 83);
		morningjs.setSize(317, 95);
		contentPane.add(morningjs);

		JScrollPane lunchjs = new JScrollPane(lunchTable);
		lunchjs.setLocation(463, 188);
		lunchjs.setSize(317, 95);
		contentPane.add(lunchjs);

		JScrollPane dinnerjs = new JScrollPane(dinnerTable);
		dinnerjs.setLocation(463, 293);
		dinnerjs.setSize(317, 95);
		contentPane.add(dinnerjs);

		JScrollPane dessertjs = new JScrollPane(dessertTable);
		dessertjs.setLocation(463, 398);
		dessertjs.setSize(317, 95);
		contentPane.add(dessertjs);

		// �÷�����ư �׼�
		btnPlus.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				int row = table.getSelectedRow();

				if (row == -1) {
					JOptionPane.showMessageDialog(null, "������ �������ּ���");
				}
				// ���� �Է¾��ҽ� �����޽���
				else if (fieldCount.getText().trim().isEmpty()) {
					JOptionPane.showMessageDialog(null, "������ �Է����ּ���");
				}
				// ��ħ,����,����,���� ���̺� ������ ���İ� ���������� �ֱ�
				else {
					Object sdb[] = { table.getModel().getValueAt(row, 0), table.getModel().getValueAt(row, 1),
							table.getModel().getValueAt(row, 2), fieldCount.getText() }; // ���ĸ���Ʈ.���ĸ�, ���ĸ���Ʈ.Į�θ� ���, ����
					// ->
					System.out.println("sdb= " + sdb[1]);

					// sdb[]�� ���
					if (choiceTime.getItem(choiceTime.getSelectedIndex()) == "��ħ") {
						sdb[2] = Float.valueOf(sdb[2].toString()) * Float.valueOf(sdb[3].toString());
						morningmodel.addRow(sdb);
						System.out.println("cal= " + sdb[2]);
					} else if (choiceTime.getItem(choiceTime.getSelectedIndex()) == "����") {
						sdb[2] = Float.valueOf(sdb[2].toString()) * Float.valueOf(sdb[3].toString());
						lunchmodel.addRow(sdb);
						System.out.println("cal= " + sdb[2]);
					} else if (choiceTime.getItem(choiceTime.getSelectedIndex()) == "����") {
						sdb[2] = Float.valueOf(sdb[2].toString()) * Float.valueOf(sdb[3].toString());
						dinnermodel.addRow(sdb);
						System.out.println("cal= " + sdb[2]);
					} else if (choiceTime.getItem(choiceTime.getSelectedIndex()) == "����") {
						sdb[2] = Float.valueOf(sdb[2].toString()) * Float.valueOf(sdb[3].toString());
						dessertmodel.addRow(sdb);
						System.out.println("cal= " + sdb[2]);

					}
				}

			}
		});

		// ���̳ʽ���ư �׼�
		btnMinus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int mdel = morningTable.getSelectedRow();
				int ldel = lunchTable.getSelectedRow();
				int ddel = dinnerTable.getSelectedRow();
				int dsdel = dessertTable.getSelectedRow();

				if (mdel != -1) {
					morningmodel.removeRow(mdel);
				}
				if (ldel != -1) {
					lunchmodel.removeRow(ldel);
				}
				if (ddel != -1) {
					dinnermodel.removeRow(ddel);
				}
				if (dsdel != -1)
					dessertmodel.removeRow(dsdel);
			}
		});

		// OK��ư �׼�
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String choice_date = choiceDate.getItem(choiceDate.getSelectedIndex()); // ���õ� ��¥
				System.out.println(choice_date);
				String choice_time = choiceTime.getItem(choiceTime.getSelectedIndex()); // ���õ� �ð���(��������)

				// ���� ��ư�� ������ �ð��뺰�� �ڵ�,������ �����ϱ�
				// db�� ���� ���� -> rowcount==0�̸� ������ϰ� rowcount >= 1�̸� db�� ����
				// DB����� �Լ� �ʿ�
				if (morningmodel.getRowCount() > 0) { // ���̺� ���� ������, DB�� ����
					for (int i = 0; i < morningmodel.getRowCount(); i++) {
						DBConnect.user_eat(user_id, choice_date, "��ħ", morningmodel.getValueAt(i, 0).toString(),
								morningmodel.getValueAt(i, 2).toString(), morningmodel.getValueAt(i, 3).toString());
					}
				}
				if (lunchmodel.getRowCount() > 0) {
					for (int i = 0; i < lunchmodel.getRowCount(); i++) {
						DBConnect.user_eat(user_id, choice_date, "����", lunchmodel.getValueAt(i, 0).toString(),
								lunchmodel.getValueAt(i, 2).toString(), lunchmodel.getValueAt(i, 3).toString());
					}
				}
				if (dinnermodel.getRowCount() > 0) {
					for (int i = 0; i < dinnerTable.getRowCount(); i++) {
						DBConnect.user_eat(user_id, choice_date, "����", dinnermodel.getValueAt(i, 0).toString(),
								dinnermodel.getValueAt(i, 2).toString(), dinnermodel.getValueAt(i, 3).toString());
					}
				}
				if (dessertmodel.getRowCount() > 0) {
					for (int i = 0; i < dessertmodel.getRowCount(); i++) {
						DBConnect.user_eat(user_id, choice_date, "����", dessertmodel.getValueAt(i, 0).toString(),
								dessertmodel.getValueAt(i, 2).toString(), dessertmodel.getValueAt(i, 3).toString());
					}
				}
				JOptionPane.showMessageDialog(null, "���ĵ����͸� �����߽��ϴ�");

			}
		});

		// ��¥ Ŭ���� DB�� ������ �����ͼ� �������� ���̺� �Ѹ���
		choiceDate.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				System.out.println(e.getItem());
				selectedDate = (String) e.getItem();
				morningmodel.setRowCount(0);
				lunchmodel.setRowCount(0);
				dinnermodel.setRowCount(0);
				dessertmodel.setRowCount(0);
				System.out.println();
				try {
					ResultSet rs_morning = db.getInfo(
							"select nutrient.Food_no, nutrient.Food_Name, user_eat.Food_cal, user_eat.Food_Count from user_eat left outer join nutrient on user_eat.Food_no = nutrient.Food_no where User_ID = '"
									+ user_id + "' and Eat_Time='��ħ' and Eat_date=" + "'" + selectedDate + "'");
					ResultSet rs_lunch = db.getInfo(
							"select nutrient.Food_no, nutrient.Food_Name, user_eat.Food_cal, user_eat.Food_Count from user_eat left outer join nutrient on user_eat.Food_no = nutrient.Food_no where User_ID = '"
									+ user_id + "' and Eat_Time='����' and Eat_date=" + "'" + selectedDate + "'");
					ResultSet rs_dinner = db.getInfo(
							"select nutrient.Food_no, nutrient.Food_Name, user_eat.Food_cal, user_eat.Food_Count from user_eat left outer join nutrient on user_eat.Food_no = nutrient.Food_no where User_ID = '"
									+ user_id + "' and Eat_Time='����' and Eat_date=" + "'" + selectedDate + "'");
					ResultSet rs_dessert = db.getInfo(
							"select nutrient.Food_no, nutrient.Food_Name, user_eat.Food_cal, user_eat.Food_Count from user_eat left outer join nutrient on user_eat.Food_no = nutrient.Food_no where User_ID = '"
									+ user_id + "' and Eat_Time='����' and Eat_date=" + "'" + selectedDate + "'");
					while (rs_morning.next()) {
						String Food_no = rs_morning.getString("Food_no");
						String Food_Name = rs_morning.getString("Food_Name");
						String Food_cal = rs_morning.getString("Food_cal");
						Float Food_Count = rs_morning.getFloat("Food_Count");
						Object data_morning[] = { Food_no, Food_Name, Food_cal, Food_Count };
						morningmodel.addRow(data_morning);
					}
					while (rs_lunch.next()) {
						String Food_no = rs_lunch.getString("Food_no");
						String Food_Name = rs_lunch.getString("Food_Name");
						String Food_cal = rs_lunch.getString("Food_cal");
						Float Food_Count = rs_lunch.getFloat("Food_Count");
						Object data_lunch[] = { Food_no, Food_Name, Food_cal, Food_Count };
						lunchmodel.addRow(data_lunch);
					}
					while (rs_dinner.next()) {
						String Food_no = rs_dinner.getString("Food_no");
						String Food_Name = rs_dinner.getString("Food_Name");
						String Food_cal = rs_dinner.getString("Food_cal");
						Float Food_Count = rs_dinner.getFloat("Food_Count");
						Object data_dinner[] = { Food_no, Food_Name, Food_cal, Food_Count };
						dinnermodel.addRow(data_dinner);
					}
					while (rs_dessert.next()) {
						String Food_no = rs_dessert.getString("Food_no");
						String Food_Name = rs_dessert.getString("Food_Name");
						String Food_cal = rs_dessert.getString("Food_cal");
						Float Food_Count = rs_dessert.getFloat("Food_Count");
						Object data_dessert[] = { Food_no, Food_Name, Food_cal, Food_Count };
						dessertmodel.addRow(data_dessert);
					}

				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});

		String choice_date = choiceDate.getItem(choiceDate.getSelectedIndex());
		System.out.println(choice_date);

		// �����Է�
		fieldSearch.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				String val = fieldSearch.getText();// fieldsearch���� �ؽ�Ʈ��������
				TableRowSorter<TableModel> trs = new TableRowSorter<>(table.getModel());
				table.setRowSorter(trs); // table row����
				trs.setRowFilter(RowFilter.regexFilter(val));

			}
		});

		// ==============================Label==============================
		JLabel lblTC = new JLabel("�� Į�θ�");
		lblTC.setBounds(412, 511, 139, 40);
		lblTC.setHorizontalAlignment(SwingConstants.CENTER);
		lblTC.setFont(new Font("����", Font.BOLD, 20));
		contentPane.add(lblTC);

		JLabel lblMorning = new JLabel("��ħ");
		lblMorning.setHorizontalAlignment(SwingConstants.CENTER);
		lblMorning.setFont(new Font("����", Font.BOLD, 20));
		lblMorning.setBounds(405, 83, 57, 95);
		contentPane.add(lblMorning);

		JLabel lblLunch = new JLabel("����");
		lblLunch.setHorizontalAlignment(SwingConstants.CENTER);
		lblLunch.setFont(new Font("����", Font.BOLD, 20));
		lblLunch.setBounds(405, 188, 57, 95);
		contentPane.add(lblLunch);

		JLabel lblDinner = new JLabel("����");
		lblDinner.setHorizontalAlignment(SwingConstants.CENTER);
		lblDinner.setFont(new Font("����", Font.BOLD, 20));
		lblDinner.setBounds(402, 293, 57, 95);
		contentPane.add(lblDinner);

		JLabel lblDessert = new JLabel("����");
		lblDessert.setHorizontalAlignment(SwingConstants.CENTER);
		lblDessert.setFont(new Font("����", Font.BOLD, 20));
		lblDessert.setBounds(402, 398, 57, 95);
		contentPane.add(lblDessert);

		JLabel lblCount = new JLabel("����");
		lblCount.setHorizontalAlignment(SwingConstants.CENTER);
		lblCount.setFont(new Font("����", Font.BOLD, 16));
		lblCount.setBounds(12, 511, 46, 40);
		contentPane.add(lblCount);

		JLabel lblChoiceDate = new JLabel("��¥ ����");
		lblChoiceDate.setHorizontalAlignment(SwingConstants.CENTER);
		lblChoiceDate.setBounds(12, 83, 121, 21);
		contentPane.add(lblChoiceDate);

		JLabel lblChoiceTime = new JLabel("�ð� ����");
		lblChoiceTime.setHorizontalAlignment(SwingConstants.CENTER);
		lblChoiceTime.setBounds(12, 110, 121, 21);
		contentPane.add(lblChoiceTime);

		JLabel selectfood = new JLabel("�˻�");
		selectfood.setFont(new Font("����", Font.BOLD, 16));
		selectfood.setBounds(463, 29, 38, 27);
		contentPane.add(selectfood);

	}
}