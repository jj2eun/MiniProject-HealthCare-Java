package hmj;
import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class Report  {

	JFrame frame;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					Report rp = new Report();
//					rp.frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public Report() {
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
		
		//====================================JButton====================================
		JButton btnMain = new JButton("¸ÞÀÎ");
		btnMain.setFont(new Font("±¼¸²", Font.BOLD, 20));
		btnMain.setBounds(40, 20, 167, 50);
		contentPane.add(btnMain);
		
		btnMain.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Main m = new Main();
				m.frame.setVisible(true);
				frame.dispose();
				
			}
		});
		
		JButton btnFoodlist = new JButton("½Ä´Ü");
		btnFoodlist.setFont(new Font("±¼¸²", Font.BOLD, 20));
		btnFoodlist.setBounds(304, 20, 167, 50);
		contentPane.add(btnFoodlist);
		
		btnFoodlist.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				FoodList fl = new FoodList();
				fl.frame.setVisible(true);
				frame.dispose();
				
			}
		});
		
		JButton btnExer = new JButton("¿îµ¿");
		btnExer.setFont(new Font("±¼¸²", Font.BOLD, 20));
		btnExer.setBounds(564, 20, 167, 50);
		contentPane.add(btnExer);
		
		btnExer.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ExerciseList ex = new ExerciseList();
				ex.frame.setVisible(true);
				frame.dispose();
				
			}
		});
		
		JButton btnWeightSave = new JButton("OK");
		btnWeightSave.setFont(new Font("±¼¸²", Font.BOLD, 20));
		btnWeightSave.setBounds(675, 99, 97, 29);
		contentPane.add(btnWeightSave);
		//====================================textField====================================
		JTextField textField = new JTextField();
		textField.setBounds(253, 99, 405, 29);
		contentPane.add(textField);
		textField.setColumns(10);
		
		//====================================JLabel====================================
		
		
		JLabel lblWeight = new JLabel("¸ö¹«°Ô");
		lblWeight.setFont(new Font("±¼¸²", Font.BOLD, 20));
		lblWeight.setHorizontalAlignment(SwingConstants.CENTER);
		lblWeight.setBounds(40, 99, 201, 29);
		contentPane.add(lblWeight);
		
		JLabel lblGraph1 = new JLabel("Ã¼Áß ±×·¡ÇÁ");
		lblGraph1.setFont(new Font("±¼¸²", Font.BOLD, 35));
		lblGraph1.setHorizontalAlignment(SwingConstants.CENTER);
		lblGraph1.setBounds(253, 138, 276, 42);
		contentPane.add(lblGraph1);
		
		JLabel lblGraph2 = new JLabel("³ªÀÇ Åº´ÜÁö!!");
		lblGraph2.setHorizontalAlignment(SwingConstants.CENTER);
		lblGraph2.setFont(new Font("±¼¸²", Font.BOLD, 35));
		lblGraph2.setBounds(253, 429, 276, 42);
		contentPane.add(lblGraph2);
	}

}
