package hmj;
import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JList;
import java.awt.Choice;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class FoodList {
	 JFrame frame;
	

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					FoodList f = new FoodList();
//					f.frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */

	public FoodList() {
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
					choiceDate.add(String.valueOf(i+"��"+j+"��"+k+"��"));
				}
			}
		}
		
		
		Choice choiceTime = new Choice();
		choiceTime.setBounds(135, 110, 264, 21);
		contentPane.add(choiceTime);
		
		choiceTime.add("��ħ");
		choiceTime.add("����");
		choiceTime.add("����");
		choiceTime.add("����");
		//==============================JButton==============================
		JButton btnMain = new JButton("����");
		btnMain.setBounds(12, 10, 121, 67);
		btnMain.setFont(new Font("����", Font.BOLD, 20));
		contentPane.add(btnMain);
		
		btnMain.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Main m = new Main();
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
				ExerciseList ex = new ExerciseList();
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
				Report rp = new Report();
				rp.frame.setVisible(true);
				frame.dispose();
			}
		});
		
		
		JButton btnSearch = new JButton("�˻�");
		btnSearch.setBounds(706, 10, 66, 40);
		contentPane.add(btnSearch);
		
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
		JList listFood = new JList();
		listFood.setBounds(12, 137, 387, 364);
		contentPane.add(listFood);
		
		JList listMoring = new JList();
		listMoring.setBounds(463, 83, 317, 95);
		contentPane.add(listMoring);
		
		JList listLunch = new JList();
		listLunch.setBounds(463, 188, 317, 95);
		contentPane.add(listLunch);
		
		JList listDinner = new JList();
		listDinner.setBounds(463, 293, 317, 95);
		contentPane.add(listDinner);

		JList listDessert = new JList();
		listDessert.setBounds(463, 398, 317, 95);
		contentPane.add(listDessert);
		
		//==============================Label==============================
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
	}
}
