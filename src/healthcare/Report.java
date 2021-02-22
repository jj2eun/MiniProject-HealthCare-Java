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
		
		// Content Panel 생성
		JPanel contentPane = new JPanel();
		contentPane.setBounds(0,0,794,134);
		frame.getContentPane().add(contentPane);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		contentPane.setVisible(true);
		
		// 몸무게 CharPanel 생성
		chartPanel = createChartPanel(1);
		chartPanel.setBounds(50, 140, 700, 240);
		frame.getContentPane().add(chartPanel);
		chartPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		chartPanel.setLayout(null);
		chartPanel.setVisible(true);
		
		// 탄단지 ChartPanel 생성
		JPanel chartPanel2 = new JPanel();
		chartPanel2 = createChartPanel(2);
		chartPanel2.setBounds(50, 394, 700, 153);
		frame.getContentPane().add(chartPanel2);
		chartPanel2.setBorder(new EmptyBorder(5, 5, 5, 5));
		chartPanel2.setLayout(null);
		chartPanel2.setVisible(true);
		//====================================JButton====================================
		// 메인버튼
		JButton btnMain = new JButton("메인");
		btnMain.setFont(new Font("굴림", Font.BOLD, 20));
		btnMain.setBounds(40, 20, 167, 50);
		contentPane.add(btnMain);
		
		btnMain.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Main m = new Main(user_id);
				m.frame.setVisible(true);
				frame.dispose();
			}
		}); // Main버튼 클릭 액션
		// 식단버튼
		JButton btnFoodlist = new JButton("식단");
		btnFoodlist.setFont(new Font("굴림", Font.BOLD, 20));
		btnFoodlist.setBounds(304, 20, 167, 50);
		contentPane.add(btnFoodlist);
		
		btnFoodlist.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FoodList fl = new FoodList(user_id);
				fl.frame.setVisible(true);
				frame.dispose();
			}
		}); // 식단버튼 클릭 액션
		// 운동버튼
		JButton btnExer = new JButton("운동");
		btnExer.setFont(new Font("굴림", Font.BOLD, 20));
		btnExer.setBounds(564, 20, 167, 50);
		contentPane.add(btnExer);
		
		btnExer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ExerciseList ex = new ExerciseList(user_id);
				ex.frame.setVisible(true);
				frame.dispose();
			}
		}); //운동버튼 클릭 액션
		
		JButton btnWeightSave = new JButton("OK");
		btnWeightSave.setFont(new Font("굴림", Font.BOLD, 20));
		btnWeightSave.setBounds(675, 99, 97, 29);
		contentPane.add(btnWeightSave);
		//=================================== textField ===================================
		JTextField txtWeight = new JTextField();
		txtWeight.setBounds(253, 99, 405, 29);
		contentPane.add(txtWeight);
		txtWeight.setColumns(10);
		//=================================== JLabel ======================================
		JLabel lblWeight = new JLabel("체중");
		lblWeight.setFont(new Font("굴림", Font.BOLD, 20));
		lblWeight.setHorizontalAlignment(SwingConstants.CENTER);
		lblWeight.setBounds(40, 99, 113, 29);
		contentPane.add(lblWeight);
		
		//=================================== dataChooser ==================================
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(144, 99, 97, 29);
		dateChooser.setCalendar(Calendar.getInstance());
		contentPane.add(dateChooser);
		
		//=============================== Action Handler ===================================
		// 몸무게 입력 Button Click 액션
		btnWeightSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String weight = txtWeight.getText();
				if(!checkRightWeight(weight)) {
					JOptionPane.showMessageDialog(null, "체중을 정확히 입력해 주세요.");
					return ;
				} // 몸무게 입력 체크
				ResultSet rs = null;
				DBConnect dbconn = new DBConnect();
				Float curWeight = Float.parseFloat(txtWeight.getText()); // 입력받은 몸무게 0이하거나 입력안할경우 예외처리
				String chosenDate = transFormat.format(dateChooser.getDate()); // dataChooser에서 날짜 받아오기
				sql = "SELECT User_Day_Weight FROM report WHERE Report_Date = '" + chosenDate + "' and User_ID = '" + user_id +"';";
				rs = dbconn.getInfo(sql);
				
				// report table에 정보가 없을 때 - insert
				try {
					if(!rs.next()) sql = "insert into report(User_ID, Report_Date, User_Day_Weight) values ('" + user_id + "','" +chosenDate + "', " + curWeight +");"; 
					else {
						// report table에 정보가 있을 때 - update
						sql = "UPDATE report SET User_Day_Weight = " + curWeight +" where User_ID = '" + user_id + "' and Report_Date = '" + chosenDate + "';";
					}
					dbconn.updateInfo(sql);
					rs.close();
					updatePanel(chartPanel, 1); // 패널 업데이트
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
	}


	// 몸무게 ChartPanel 생성
	private JPanel createChartPanel(int code) {
		JFreeChart chart = null;
		CategoryDataset dataset = null;
		DBConnect dbconn = new DBConnect();
		CategoryPlot plot = null;
		LineAndShapeRenderer lineRenderer = null;
		StackedBarRenderer stackedBarRenderer = null;
		DecimalFormat decimalformat = new DecimalFormat("##");
		switch(code) {
		case 1: // 몸무게 차트
			CreateChart ch = new CreateChart(user_id,"체중 변화 그래프","Day","Weight(kg)");
			dataset = ch.createDataset(1, dbconn);
			chart = ChartFactory.createLineChart(ch.chartTitle, ch.categoryAxisLabel, ch.valueAxisLabel, dataset);
			
			plot = (CategoryPlot) chart.getPlot();
            ValueAxis vaxis = (ValueAxis) plot.getRangeAxis();
            vaxis.setRange(30, 70); // y축 범위 (몸무게최저 - 10 , 몸무게최고 + 10)
			lineRenderer = (LineAndShapeRenderer) plot.getRenderer();
			lineRenderer.setDefaultShapesVisible(true);
			lineRenderer.setDefaultItemLabelGenerator(new StandardCategoryItemLabelGenerator("{2}", decimalformat));
			lineRenderer.setDefaultItemLabelsVisible(true);
			lineRenderer.setDefaultSeriesVisible(true);
			break;
		case 2: // 탄단지 차트
			CreateChart ch2 = new CreateChart(user_id,"오늘의 탄단지","","total(g)");
			dataset = ch2.createDataset(2, dbconn);
			chart = ChartFactory.createStackedBarChart(ch2.chartTitle, ch2.categoryAxisLabel, ch2.valueAxisLabel, dataset, PlotOrientation.HORIZONTAL, true, true, false);
			
			plot = (CategoryPlot) chart.getPlot();
			stackedBarRenderer = (StackedBarRenderer) plot.getRenderer();
			stackedBarRenderer.setDefaultItemLabelGenerator(new StandardCategoryItemLabelGenerator("{2}", decimalformat));
			stackedBarRenderer.setDefaultItemLabelsVisible(true);
			stackedBarRenderer.setDefaultSeriesVisible(true);
			break;
		}
		chart.getTitle().setFont(new Font("맑은 고딕",Font.BOLD, 18));
		chart.getLegend().setItemFont(new Font("맑은 고딕",Font.BOLD, 15));
		return new ChartPanel(chart);
	}
	 
	// 패널 업데이트
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
	
	// 몸무게 올바르게 입력했는지 확인
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