package healthcare;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.renderer.category.StackedBarRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;

import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JMonthChooser;

public class Report  {

	JFrame frame;
	JPanel chartPanel;
	private String user_id;
	private String sql;
	LocalDate today = LocalDate.now();
	SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
	/**
	 * @wbp.parser.constructor
	 */
	public Report() {initialize() ;}
	public Report(String User_ID) {
		user_id = User_ID;
		System.out.println("user id : "+user_id);
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
		
		// Content Panel ����
		JPanel contentPane = new JPanel();
		contentPane.setBounds(0,0,794,134);
		frame.getContentPane().add(contentPane);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		contentPane.setVisible(true);
		
		// ������ CharPanel ����
		chartPanel = createChartPanel(1);
		chartPanel.setBounds(50, 140, 700, 240);
		frame.getContentPane().add(chartPanel);
		chartPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		chartPanel.setLayout(null);
		chartPanel.setVisible(true);
		
		// ź���� ChartPanel ����
		JPanel chartPanel2 = new JPanel();
		chartPanel2 = createChartPanel(2);
		chartPanel2.setBounds(50, 394, 700, 153);
		frame.getContentPane().add(chartPanel2);
		chartPanel2.setBorder(new EmptyBorder(5, 5, 5, 5));
		chartPanel2.setLayout(null);
		chartPanel2.setVisible(true);
		//====================================JButton====================================
		// ���ι�ư
		JButton btnMain = new JButton("����");
		btnMain.setFont(new Font("����", Font.BOLD, 20));
		btnMain.setBounds(40, 20, 167, 50);
		contentPane.add(btnMain);
		
		btnMain.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Main m = new Main(user_id);
				m.frame.setVisible(true);
				frame.dispose();
			}
		}); // Main��ư Ŭ�� �׼�
		// �Ĵܹ�ư
		JButton btnFoodlist = new JButton("�Ĵ�");
		btnFoodlist.setFont(new Font("����", Font.BOLD, 20));
		btnFoodlist.setBounds(304, 20, 167, 50);
		contentPane.add(btnFoodlist);
		
		btnFoodlist.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FoodList fl = new FoodList(user_id);
				fl.frame.setVisible(true);
				frame.dispose();
			}
		}); // �Ĵܹ�ư Ŭ�� �׼�
		// ���ư
		JButton btnExer = new JButton("�");
		btnExer.setFont(new Font("����", Font.BOLD, 20));
		btnExer.setBounds(564, 20, 167, 50);
		contentPane.add(btnExer);
		
		btnExer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ExerciseList ex = new ExerciseList(user_id);
				ex.frame.setVisible(true);
				frame.dispose();
			}
		}); //���ư Ŭ�� �׼�
		
		JButton btnWeightSave = new JButton("OK");
		btnWeightSave.setFont(new Font("����", Font.BOLD, 20));
		btnWeightSave.setBounds(675, 99, 97, 29);
		contentPane.add(btnWeightSave);
		//=================================== textField ===================================
		JTextField txtWeight = new JTextField();
		txtWeight.setBounds(253, 99, 405, 29);
		contentPane.add(txtWeight);
		txtWeight.setColumns(10);
		//=================================== JLabel ======================================
		JLabel lblWeight = new JLabel("ü��");
		lblWeight.setFont(new Font("����", Font.BOLD, 20));
		lblWeight.setHorizontalAlignment(SwingConstants.CENTER);
		lblWeight.setBounds(40, 99, 113, 29);
		contentPane.add(lblWeight);
		
		//=================================== dataChooser ==================================
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(144, 99, 97, 29);
		dateChooser.setCalendar(Calendar.getInstance());
		contentPane.add(dateChooser);
		
		//=============================== Action Handler ===================================
		// ������ �Է� Button Click �׼�
		btnWeightSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String weight = txtWeight.getText();
				if(!checkRightWeight(weight)) {
					JOptionPane.showMessageDialog(null, "ü���� ��Ȯ�� �Է��� �ּ���.");
					return ;
				} // ������ �Է� üũ
				ResultSet rs = null;
				DBConnect dbconn = new DBConnect();
				Float curWeight = Float.parseFloat(txtWeight.getText()); // �Է¹��� ������ 0���ϰų� �Է¾��Ұ�� ����ó��
				String chosenDate = transFormat.format(dateChooser.getDate()); // dataChooser���� ��¥ �޾ƿ���
				sql = "SELECT User_Day_Weight FROM report WHERE Report_Date = '" + chosenDate + "' and User_ID = '" + user_id +"';";
				rs = dbconn.getInfo(sql);
				
				// report table�� ������ ���� �� - insert
				try {
					if(!rs.next()) sql = "insert into report(User_ID, Report_Date, User_Day_Weight) values ('" + user_id + "','" +chosenDate + "', " + curWeight +");"; 
					else {
						// report table�� ������ ���� �� - update
						sql = "UPDATE report SET User_Day_Weight = " + curWeight +" where User_ID = '" + user_id + "' and Report_Date = '" + chosenDate + "';";
					}
					dbconn.updateInfo(sql);
					rs.close();
					updatePanel(chartPanel, 1); // �г� ������Ʈ
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
	}


	// ������ ChartPanel ����
	private JPanel createChartPanel(int code) {
		JFreeChart chart = null;
		CategoryDataset dataset = null;
		DBConnect dbconn = new DBConnect();
		CategoryPlot plot = null;
		LineAndShapeRenderer lineRenderer = null;
		StackedBarRenderer stackedBarRenderer = null;
		DecimalFormat decimalformat = new DecimalFormat("##");
		switch(code) {
		case 1: // ������ ��Ʈ
			CreateChart ch = new CreateChart(user_id,"ü�� ��ȭ �׷���","Day","Weight(kg)");
			dataset = ch.createDataset(1, dbconn);
			chart = ChartFactory.createLineChart(ch.chartTitle, ch.categoryAxisLabel, ch.valueAxisLabel, dataset);
			
			plot = (CategoryPlot) chart.getPlot();
            ValueAxis vaxis = (ValueAxis) plot.getRangeAxis();
            vaxis.setRange(30, 70); // y�� ���� (���������� - 10 , �������ְ� + 10)
			lineRenderer = (LineAndShapeRenderer) plot.getRenderer();
			lineRenderer.setDefaultShapesVisible(true);
			lineRenderer.setDefaultItemLabelGenerator(new StandardCategoryItemLabelGenerator("{2}", decimalformat));
			lineRenderer.setDefaultItemLabelsVisible(true);
			lineRenderer.setDefaultSeriesVisible(true);
			break;
		case 2: // ź���� ��Ʈ
			CreateChart ch2 = new CreateChart(user_id,"������ ź����","","total(g)");
			dataset = ch2.createDataset(2, dbconn);
			chart = ChartFactory.createStackedBarChart(ch2.chartTitle, ch2.categoryAxisLabel, ch2.valueAxisLabel, dataset, PlotOrientation.HORIZONTAL, true, true, false);
			
			plot = (CategoryPlot) chart.getPlot();
			stackedBarRenderer = (StackedBarRenderer) plot.getRenderer();
			stackedBarRenderer.setDefaultItemLabelGenerator(new StandardCategoryItemLabelGenerator("{2}", decimalformat));
			stackedBarRenderer.setDefaultItemLabelsVisible(true);
			stackedBarRenderer.setDefaultSeriesVisible(true);
			break;
		}
		chart.getTitle().setFont(new Font("���� ���",Font.BOLD, 18));
		chart.getLegend().setItemFont(new Font("���� ���",Font.BOLD, 15));
		return new ChartPanel(chart);
	}
	 
	// �г� ������Ʈ
	private void updatePanel(JPanel panel, int code) {
		frame.remove(panel);
		this.chartPanel = createChartPanel(code);
		this.chartPanel.setBounds(50, 140, 700, 240);
		this.chartPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		frame.getContentPane().add(this.chartPanel);
		
		frame.revalidate();
		frame.repaint();
		
		this.chartPanel.setLayout(null);
		this.chartPanel.setVisible(true);
	}
	
	// ������ �ùٸ��� �Է��ߴ��� Ȯ��
	private boolean checkRightWeight(String weight) {
		try {
			if(weight.isEmpty() || Float.parseFloat(weight) <= 0 ) {
				return false;
			}
			else return true;
		} catch(Exception ex) {
			return false;
		}
	}
}