package hmj;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JList;
import java.awt.Choice;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ExerciseList extends JFrame {

	private JPanel contentPane;
	private JTextField fieldSearch;
	private JTextField fieldCount;
	private JTextField fieldTC;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ExerciseList frame = new ExerciseList();
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
	public ExerciseList() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
	
		//====================================JList====================================
		JList listExercise = new JList();
		listExercise.setBounds(12, 110, 387, 391);
		contentPane.add(listExercise);
		
		JList listMyexer = new JList();
		listMyexer.setBounds(412, 110, 347, 391);
		contentPane.add(listMyexer);
		
		//====================================Choice====================================
		Choice choiceDate = new Choice();
		choiceDate.setBounds(135, 83, 264, 21);
		contentPane.add(choiceDate);
		
		//====================================JButton====================================
		JButton btnMain = new JButton("¸ÞÀÎ");
		btnMain.setBounds(12, 10, 121, 67);
		btnMain.setFont(new Font("±¼¸²", Font.BOLD, 20));
		contentPane.add(btnMain);
		
		JButton btnFoodlist = new JButton("½Ä´Ü");
		btnFoodlist.setBounds(145, 10, 121, 67);
		btnFoodlist.setFont(new Font("±¼¸²", Font.BOLD, 20));
		contentPane.add(btnFoodlist);
		
		JButton btnRept = new JButton("¸®Æ÷Æ®");
		btnRept.setBounds(278, 10, 121, 67);
		btnRept.setFont(new Font("±¼¸²", Font.BOLD, 20));
		contentPane.add(btnRept);
		
		JButton btnSearch = new JButton("°Ë»ö");
		btnSearch.setBounds(714, 10, 58, 40);
		contentPane.add(btnSearch);
		
		JButton btnPlus = new JButton("+");
		btnPlus.setBounds(135, 511, 80, 40);
		btnPlus.setFont(new Font("±¼¸²", Font.BOLD, 20));
		contentPane.add(btnPlus);
		
		JButton btnMinus = new JButton("-");
		btnMinus.setBounds(227, 511, 80, 40);
		btnMinus.setFont(new Font("±¼¸²", Font.BOLD, 20));
		contentPane.add(btnMinus);
		
		JButton btnSave = new JButton("OK");
		btnSave.setBounds(319, 511, 80, 40);
		btnSave.setFont(new Font("±¼¸²", Font.BOLD, 20));
		contentPane.add(btnSave);
		
		//====================================TextField====================================
		fieldSearch = new JTextField();
		fieldSearch.setBounds(412, 10, 290, 40);
		fieldSearch.setColumns(10);
		contentPane.add(fieldSearch);
		
		fieldCount = new JTextField();
		fieldCount.setBounds(70, 512, 58, 39);
		fieldCount.setColumns(10);
		contentPane.add(fieldCount);
		
		fieldTC = new JTextField();
		fieldTC.setBounds(541, 511, 231, 33);
		fieldTC.setColumns(10);
		contentPane.add(fieldTC);
		
		//====================================JLabel====================================
		
		JLabel lblTC = new JLabel("ÃÑ Ä®·Î¸®");
		lblTC.setBounds(412, 511, 139, 40);
		lblTC.setHorizontalAlignment(SwingConstants.CENTER);
		lblTC.setFont(new Font("±¼¸²", Font.BOLD, 20));
		contentPane.add(lblTC);
		
		
		
		JLabel lblTime = new JLabel("½Ã°£");
		lblTime.setHorizontalAlignment(SwingConstants.CENTER);
		lblTime.setFont(new Font("±¼¸²", Font.BOLD, 16));
		lblTime.setBounds(12, 511, 46, 40);
		contentPane.add(lblTime);
		
		JLabel lblChoiceDate = new JLabel("³¯Â¥ ¼±ÅÃ");
		lblChoiceDate.setHorizontalAlignment(SwingConstants.CENTER);
		lblChoiceDate.setBounds(12, 83, 121, 21);
		contentPane.add(lblChoiceDate);
		
		JLabel lblMyexer = new JLabel("¼±ÅÃÇÑ ¿îµ¿");
		lblMyexer.setFont(new Font("±¼¸²", Font.BOLD, 25));
		lblMyexer.setHorizontalAlignment(SwingConstants.CENTER);
		lblMyexer.setBounds(411, 60, 348, 44);
		contentPane.add(lblMyexer);
	}
}
