package hmj;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JSeparator;
import java.awt.*;
public class Main  {
	private JFrame frame;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main m = new Main();
					m.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Main() {
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
		//====================================JButton====================================
		JButton btnFoodlist = new JButton("½Ä´Ü");
		btnFoodlist.setBounds(40, 20, 167, 50);
		btnFoodlist.setFont(new Font("±¼¸²", Font.BOLD, 20));
		contentPane.add(btnFoodlist);
		
		JButton btnExer = new JButton("¿îµ¿");
		btnExer.setBounds(304, 20, 167, 50);
		btnExer.setFont(new Font("±¼¸²", Font.BOLD, 20));
		contentPane.add(btnExer);
		
		JButton btnRept = new JButton("¸®Æ÷Æ®");
		btnRept.setBounds(564, 20, 167, 50);
		btnRept.setFont(new Font("±¼¸²", Font.BOLD, 20));
		contentPane.add(btnRept);
	}

}
