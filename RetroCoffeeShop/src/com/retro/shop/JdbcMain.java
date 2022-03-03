package com.retro.shop;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class JdbcMain {

	public static Map<String,Double> getMenu() throws Exception{		
		Class.forName("oracle.jdbc.driver.OracleDriver"); //("com.mysql.jdbc.Driver");
		
		String url="jdbc:oracle:thin:@localhost:1521:xe"; //"jdbc:mysql://localhost:3306/Retroshop";
		String uname="hr";
		String pass="roke";
		Connection con = null;
	    PreparedStatement pstmt = null;
	    ResultSet rst = null;
	    
	    Map<String,Double> items = new HashMap<>();
	    
	    String query="select * from retro_shop";
		try {
	         con = DriverManager.getConnection(url, uname, pass);
	         pstmt = con.prepareStatement(query);
	         rst = pstmt.executeQuery();
	         while(rst.next()) {
	        	 items.put(rst.getString(2), rst.getDouble(3));
	         }
	         pstmt.close();
	         con.close();
	      } catch(Exception exec) {
	         exec.printStackTrace();
	      }
		
		return items;
	}

}
