package com.retro.shop;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class JdbcMain {

	public static Map<Integer,Map<String,Double>> getMenu() throws Exception{		
		Class.forName("oracle.jdbc.driver.OracleDriver"); //("com.mysql.jdbc.Driver");
		
		String url="jdbc:oracle:thin:@localhost:1521:xe"; //"jdbc:mysql://localhost:3306/Retroshop";
		String uname="hr";
		String pass="roke";
		Connection con = null;
	    PreparedStatement pstmt = null;
	    ResultSet rst = null;
	    
	    Map<Integer,Map<String,Double>> items = new HashMap<>();
	    
	    String query="select * from retro_shop";
		try {
	         con = DriverManager.getConnection(url, uname, pass);
	         pstmt = con.prepareStatement(query);
	         rst = pstmt.executeQuery();
	         while(rst.next()) {
	        	 
	        	 Map<String,Double> innerMap=new HashMap<>();
	        	 innerMap.put(rst.getString(2), rst.getDouble(3));
	        	 
	        	 items.put(rst.getInt(1),innerMap);
	        	 
	         }
	         pstmt.close();
	         con.close();
	      } catch(Exception exec) {
	         exec.printStackTrace();
	      }
		
		return items;
	}
	
	public static boolean addOrder(String customerName,String phno,String orderItemsCodeList,float totalCost) throws Exception{		
		Class.forName("oracle.jdbc.driver.OracleDriver"); //("com.mysql.jdbc.Driver");
		
		String url="jdbc:oracle:thin:@localhost:1521:xe"; //"jdbc:mysql://localhost:3306/Retroshop";
		String uname="hr";
		String pass="roke";
		Connection con = null;
	    PreparedStatement pstmt = null;
	    ResultSet rst = null;
	    int flag=0;
	    
	    String query="insert into retro_shop_orders"
	    		+ " values(seq1.nextval, ?, ?, ?, ?)";
		try {
	         con = DriverManager.getConnection(url, uname, pass);	 	    
	         pstmt = con.prepareStatement(query);
	         pstmt.setString(1,customerName);
		     pstmt.setString(2,phno);
		     pstmt.setString(3,orderItemsCodeList);
		     pstmt.setFloat(4,totalCost);
	         //pstmt = con.prepareStatement(query);
	         flag=pstmt.executeUpdate();
	         pstmt.close();
	         con.close();
	      } catch(Exception exec) {
	         exec.printStackTrace();
	      }
		
		return flag>0 ? true:false;
	}

}
