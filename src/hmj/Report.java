package hmj;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class Report extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Report frame = new Report();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Report() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//====================================JButton====================================
		JButton btnMain = new JButton("¸ÞÀÎ");
		btnMain.setFont(new Font("±¼¸²", Font.BOLD, 20));
		btnMain.setBounds(40, 20, 167, 50);
		contentPane.add(btnMain);
		
		JButton btnFoodlist = new JButton("½Ä´Ü");
		btnFoodlist.setFont(new Font("±¼¸²", Font.BOLD, 20));
		btnFoodlist.setBounds(304, 20, 167, 50);
		contentPane.add(btnFoodlist);
		
		JButton btnExer = new JButton("¿îµ¿");
		btnExer.setFont(new Font("±¼¸²", Font.BOLD, 20));
		btnExer.setBounds(564, 20, 167, 50);
		contentPane.add(btnExer);
		
		JButton btnWeightSave = new JButton("OK");
		btnWeightSave.setFont(new Font("±¼¸²", Font.BOLD, 20));
		btnWeightSave.setBounds(675, 99, 97, 29);
		contentPane.add(btnWeightSave);
		//====================================textField====================================
		textField = new JTextField();
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
