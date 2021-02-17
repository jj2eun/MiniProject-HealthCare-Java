package hmj;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JSeparator;

public class Main extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
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
	public Main() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
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
