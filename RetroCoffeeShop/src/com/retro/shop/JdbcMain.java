package com.retro.shop;

import java.sql.*;

public class JdbcMain {

	public static void main(String[] args)  throws Exception{
				
		Class.forName("oracle.jdbc.driver.OracleDriver"); //("com.mysql.jdbc.Driver");
		
		String url="jdbc:oracle:thin:@localhost:1521:xe"; //"jdbc:mysql://localhost:3306/Retroshop";
		String uname="hr";
		String pass="roke";
		Connection con = null;
	    PreparedStatement pstmt = null;
	    ResultSet rst = null;
	    String query="select * from retro_shop";
		try {
	         con = DriverManager.getConnection(url, uname, pass);
	         pstmt = con.prepareStatement(query);
	         rst = pstmt.executeQuery();
	         System.out.println("Id\tName\tPrice\n");
	         while(rst.next()) {
	            System.out.print(rst.getInt(1));
	            System.out.print("\t"+rst.getString(2));
	            System.out.print("\t"+rst.getDouble(3));
	            System.out.println();
	         }
	         pstmt.close();
	         con.close();
	      } catch(Exception exec) {
	         exec.printStackTrace();
	      }
		
	}

}
