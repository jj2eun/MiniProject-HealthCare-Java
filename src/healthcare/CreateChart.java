package healthcare;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

public class CreateChart {
   String user_id;
   String chartTitle;
   String categoryAxisLabel;
   String valueAxisLabel;
   LocalDate today;

   CreateChart() {
   }

   CreateChart(String user_id, String chartTitle, String categoryAxisLabel, String valueAxisLabel) {
      this.user_id = user_id;
      this.chartTitle = chartTitle;
      this.categoryAxisLabel = categoryAxisLabel;
      this.valueAxisLabel = valueAxisLabel;
      this.today = LocalDate.now();
   }

   // Dataset 생성 함수
   public CategoryDataset createDataset(int code, DBConnect dbconn) {
      ResultSet rs = null;
      String date;
      DefaultCategoryDataset dataset = new DefaultCategoryDataset();
      switch (code) {
      case 1:
         String series1 = "체중";
         Float weight = (float) 0;
         LocalDate firstDay = today.with(TemporalAdjusters.firstDayOfMonth()); // 2021-02-01
         LocalDate lastDay = today.with(TemporalAdjusters.lastDayOfMonth()); // 2021-02-28
         String first = null, last = null;
         Float[] arr = new Float[32];
         String sql = "select * from report where User_ID = '" + user_id + "' and Report_Date between '" + firstDay
               + "' and '" + lastDay + "' order by Report_Date;"; // 해달월의 날짜 모두 가져오기
         rs = dbconn.getInfo(sql);

         try {
            while (rs.next()) { // 2020-09-02
               if (rs.isFirst())
                  first = rs.getString("Report_Date");
               if (rs.isLast())
                  last = rs.getString("Report_Date");
               weight = rs.getFloat("User_Day_Weight");
               date = rs.getString("Report_Date");
               int i = Integer.parseInt(date.substring(8, 10));
               arr[i] = weight;
            }

            // 값이 들어온 이번 달 첫 번째 날짜 ~ 값이 들어있는 이번 달 마지막 날짜
            int first1 = Integer.parseInt(first.substring(8, 10));
            int last1 = Integer.parseInt(last.substring(8, 10));
            System.out.println("DeBug first1: " + first1);
            System.out.println("DeBug last1: " + last1);
            float flag = (float) 0;
            for (int i = first1; i <= last1; i++) {
               if (flag == 0) {
                  if (arr[i] == null)
                     dataset.addValue(0, series1, Integer.toString(i)); // 날짜에 값이없으면
                  else {
                     dataset.addValue(arr[i], series1, Integer.toString(i));
                     flag = arr[i];
                  }
               } else {
                  if (arr[i] == null)
                     dataset.addValue(flag, series1, Integer.toString(i)); // 날짜에 값이없으면
                  else {
                     dataset.addValue(arr[i], series1, Integer.toString(i));
                     flag = arr[i];
                  }
               }
            }
         } catch (Exception e) {
            // e.printStackTrace();
            System.out.println("데이터가 존재하지 않아 차트 출력 불가");
            break;
         }
         break;
      case 2: // 탄단지 차트
         float calo = (float) 0, prot  = (float) 0, fat = (float) 0;
         sql = "select * from report where User_ID = '" + user_id + "' and Report_Date = '" + today + "';"; // 오늘날짜
                                                                                    // 정보
                                                                                    // 가져오기
         rs = dbconn.getInfo(sql);
         try {
            while (rs.next()) {
               calo += rs.getFloat("Day_C");
               prot += rs.getFloat("Day_P");
               fat += rs.getFloat("Day_F");
               dataset.addValue(calo, "탄수화물", "");
               dataset.addValue(prot, "단백질", "");
               dataset.addValue(fat, "지방", "");

               System.out.println("Calo, prot, fat" + calo + " " + prot + " " + fat);
            }

         } catch (SQLException e) {
            e.printStackTrace();
         }

         break;
      }
      try {
         rs.close();
      } catch (SQLException e) {
         e.printStackTrace();
      }
      return dataset;

   }

}