//import java.sql.*;
//import java.io.*;
//import org.jfree.chart.ChartFactory;
//import org.jfree.chart.ChartUtilities;
//import org.jfree.chart.JFreeChart;
//import org.jfree.chart.plot.PlotOrientation;
//import org.jfree.data.*;
//import org.jfree.data.jdbc.JDBCCategoryDataset;
//
//public class Report {
//	public static void report() throws Exception {
//
//		String query = "SELECT * from chart";
//	       JDBCCategoryDataset dataset = new JDBCCategoryDataset(
//		"jdbc:oracle:thin:@localhost:1521:xe", 
//          "oracle.jdbc.driver.OracleDriver","hr", "roke");
//	       dataset.executeQuery(query);
//	       JFreeChart chart =  
//          ChartFactory.createBarChart3D("Id", "Name", "Sales",
//	        dataset, PlotOrientation.VERTICAL, true, true, false);
//	       try {
//	       ChartUtilities.saveChartAsJPEG(new File("C:/chart.jpg"),
//          chart,400, 300);
//	       } catch (IOException e) {
//	       System.out.println("Problem in creating chart.");
//           }
//	}
//}
