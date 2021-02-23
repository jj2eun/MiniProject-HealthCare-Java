package healthcare;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
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

import com.toedter.calendar.JDateChooser;
import java.awt.Color;

public class Report {

	JFrame frame;
	JPanel chartPanel;
	private String user_id;
	private String sql;
	LocalDate today = LocalDate.now();
	SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");

	/**
	 * @wbp.parser.constructor
	 */

	public Report() {
		initialize();
	}

	public Report(String User_ID) {
		user_id = User_ID;
		System.out.println("user id : " + user_id);
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setTitle("FoodList");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 800, 600);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);

		// Content Panel 생성
		JPanel contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBounds(0, 0, 794, 124);
		frame.getContentPane().add(contentPane);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		contentPane.setVisible(true);

		// 몸무게 CharPanel 생성
		chartPanel = createChartPanel(1);
		chartPanel.setBounds(44, 138, 700, 240);
		frame.getContentPane().add(chartPanel);
		chartPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		chartPanel.setLayout(null);
		chartPanel.setVisible(true);

		// 탄단지 ChartPanel 생성
		JPanel chartPanel2 = new JPanel();
		chartPanel2 = createChartPanel(2);
		chartPanel2.setBounds(44, 373, 700, 172);
		frame.getContentPane().add(chartPanel2);
		chartPanel2.setBorder(new EmptyBorder(5, 5, 5, 5));
		chartPanel2.setLayout(null);
		chartPanel2.setVisible(true);
		// ====================================JButton====================================
		// 메인 버튼 이미지
		ImageIcon report_main = new ImageIcon("C:\\Users\\kitri\\Desktop\\미니프로젝트\\icon\\report_main.png");
		Image report_main1 = report_main.getImage();
		Image report_main2 = report_main1.getScaledInstance(167, 50, Image.SCALE_SMOOTH);
		ImageIcon report_main3 = new ImageIcon(report_main2);
		// 메인 버튼
		JButton btnMain = new JButton(report_main3);
		btnMain.setFont(new Font("굴림", Font.BOLD, 20));
		btnMain.setBounds(40, 20, 167, 50);
		contentPane.add(btnMain);
		btnMain.setBorderPainted(false); //버튼테두리 없애

		btnMain.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Main m = new Main(user_id);
				m.frame.setVisible(true);
				frame.dispose();
			}
		}); // Main버튼 클릭 액션
		// 식단 버튼 이미지
		ImageIcon report_food = new ImageIcon("C:\\Users\\kitri\\Desktop\\미니프로젝트\\icon\\report_food.png");
		Image report_food1 = report_food.getImage();
		Image report_food2 = report_food1.getScaledInstance(167, 50, Image.SCALE_SMOOTH);
		ImageIcon report_food3 = new ImageIcon(report_food2);
		// 식단 버튼
		JButton btnFoodlist = new JButton(report_food3);
		btnFoodlist.setFont(new Font("굴림", Font.BOLD, 20));
		btnFoodlist.setBounds(219, 20, 167, 50);
		contentPane.add(btnFoodlist);
		btnFoodlist.setBorderPainted(false); //버튼테두리 없애

		btnFoodlist.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FoodList fl = new FoodList(user_id);
				fl.frame.setVisible(true);
				frame.dispose();
			}
		}); // 식단버튼 클릭 액션
		// 운동 버튼 이미지
		ImageIcon report_ex = new ImageIcon("C:\\Users\\kitri\\Desktop\\미니프로젝트\\icon\\report_ex.png");
		Image report_ex1 = report_ex.getImage();
		Image report_ex2 = report_ex1.getScaledInstance(167, 50, Image.SCALE_SMOOTH);
		ImageIcon report_ex3 = new ImageIcon(report_ex2);
		// 운동버튼
		JButton btnExer = new JButton(report_ex3);
		btnExer.setFont(new Font("굴림", Font.BOLD, 20));
		btnExer.setBounds(398, 20, 167, 50);
		contentPane.add(btnExer);
		btnExer.setBorderPainted(false); //버튼테두리 없애


		btnExer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ExerciseList ex = new ExerciseList(user_id);
				ex.frame.setVisible(true);
				frame.dispose();
			}
		}); // 운동버튼 클릭 액션

		// 리포트 버튼 이미지
		ImageIcon report_report = new ImageIcon("C:\\Users\\kitri\\Desktop\\미니프로젝트\\icon\\report_report.png");
		Image report_report1 = report_report.getImage();
		Image report_report2 = report_report1.getScaledInstance(167, 50, Image.SCALE_SMOOTH);
		ImageIcon report_report3 = new ImageIcon(report_report2);
		// 리포트버튼
		JButton btnRept = new JButton(report_report3);
		btnRept.setFont(new Font("굴림", Font.BOLD, 20));
		btnRept.setBounds(577, 20, 167, 50);
		contentPane.add(btnRept);
		btnRept.setBorderPainted(false); //버튼테두리 없애


		// ok 버튼 이미지
		ImageIcon report_ok = new ImageIcon("C:\\Users\\kitri\\Desktop\\미니프로젝트\\icon\\report_ok.png");
		Image report_ok1 = report_ok.getImage();
		Image report_ok2 = report_ok1.getScaledInstance(97, 29, Image.SCALE_SMOOTH);
		ImageIcon report_ok3 = new ImageIcon(report_ok2);
		JButton btnWeightSave = new JButton(report_ok3);
		btnWeightSave.setFont(new Font("굴림", Font.BOLD, 20));
		btnWeightSave.setBounds(639, 95, 97, 29);
		contentPane.add(btnWeightSave);
		btnWeightSave.setBorderPainted(false); //버튼테두리 없애

		// =================================== textField=========================================
		
		JTextField txtWeight = new JTextField();
		txtWeight.setBounds(253, 95, 374, 29);
		contentPane.add(txtWeight);
		txtWeight.setColumns(10);
		// =================================== JLabel=========================================
		JLabel lblWeight = new JLabel("체중");
		lblWeight.setForeground(Color.DARK_GRAY);
		lblWeight.setFont(new Font("HY헤드라인M", Font.PLAIN, 16));
		lblWeight.setHorizontalAlignment(SwingConstants.CENTER);
		lblWeight.setBounds(40, 95, 105, 29);
		contentPane.add(lblWeight);
		
		// =================================== dataChooser=========================================	
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(144, 95, 97, 29);
		dateChooser.setCalendar(Calendar.getInstance());
		contentPane.add(dateChooser);

		// =============================== Action Handlerr=========================================	
		
		// 몸무게 입력 Button Click 액션
		btnWeightSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String weight = txtWeight.getText();
				if (!checkRightWeight(weight)) {
					JOptionPane.showMessageDialog(null, "체중을 정확히 입력해 주세요.");
					return;
				} // 몸무게 입력 체크
				ResultSet rs = null;
				DBConnect dbconn = new DBConnect();
				Float curWeight = Float.parseFloat(txtWeight.getText()); // 입력받은 몸무게 0이하거나 입력안할경우 예외처리
				String chosenDate = transFormat.format(dateChooser.getDate()); // dataChooser에서 날짜 받아오기
				sql = "SELECT User_Day_Weight FROM report WHERE Report_Date = '" + chosenDate + "' and User_ID = '"
						+ user_id + "';";
				rs = dbconn.getInfo(sql);

				// report table에 정보가 없을 때 - insert
				try {
					if (!rs.next())
						sql = "insert into report(User_ID, Report_Date, User_Day_Weight) values ('" + user_id + "','"
								+ chosenDate + "', " + curWeight + ");";
					else {
						// report table에 정보가 있을 때 - update
						sql = "UPDATE report SET User_Day_Weight = " + curWeight + " where User_ID = '" + user_id
								+ "' and Report_Date = '" + chosenDate + "';";
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
		switch (code) {
		case 1: // 몸무게 차트
			CreateChart ch = new CreateChart(user_id, "체중 변화 그래프", "Day", "Weight(kg)");
			dataset = ch.createDataset(1, dbconn);
			chart = ChartFactory.createLineChart(ch.chartTitle, ch.categoryAxisLabel, ch.valueAxisLabel, dataset);

			plot = (CategoryPlot) chart.getPlot();
			ValueAxis vaxis = (ValueAxis) plot.getRangeAxis();
			vaxis.setRange(30, 120); // y축 범위 (몸무게최저 - 10 , 몸무게최고 + 10)
			lineRenderer = (LineAndShapeRenderer) plot.getRenderer();
			lineRenderer.setDefaultShapesVisible(true);
			lineRenderer.setDefaultItemLabelGenerator(new StandardCategoryItemLabelGenerator("{2}", decimalformat));
			lineRenderer.setDefaultItemLabelsVisible(true);
			lineRenderer.setDefaultSeriesVisible(true);
			break;
		case 2: // 탄단지 차트
			CreateChart ch2 = new CreateChart(user_id, "오늘의 탄단지", "", "total(g)");
			dataset = ch2.createDataset(2, dbconn);
			chart = ChartFactory.createStackedBarChart(ch2.chartTitle, ch2.categoryAxisLabel, ch2.valueAxisLabel,
					dataset, PlotOrientation.HORIZONTAL, true, true, false);

			plot = (CategoryPlot) chart.getPlot();
			stackedBarRenderer = (StackedBarRenderer) plot.getRenderer();
			stackedBarRenderer
					.setDefaultItemLabelGenerator(new StandardCategoryItemLabelGenerator("{2}", decimalformat));
			stackedBarRenderer.setDefaultItemLabelsVisible(true);
			stackedBarRenderer.setDefaultSeriesVisible(true);
			break;
		}
		chart.getTitle().setFont(new Font("맑은 고딕", Font.BOLD, 18));
		chart.getLegend().setItemFont(new Font("맑은 고딕", Font.BOLD, 15));
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
			if (weight.isEmpty() || Float.parseFloat(weight) <= 0) {
				return false;
			} else
				return true;
		} catch (Exception ex) {
			return false;
		}
	}
}