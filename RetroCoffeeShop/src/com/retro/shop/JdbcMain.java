package com.retro.shop;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

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
	    		+ " values(seq1.nextval, ?, ?, ?, ?, ?)";
		try {
	         con = DriverManager.getConnection(url, uname, pass);	 	    
	         pstmt = con.prepareStatement(query);
	         pstmt.setString(1,customerName);
		     pstmt.setString(2,phno);
		     pstmt.setString(3,orderItemsCodeList);
		     pstmt.setFloat(4,totalCost);
		     pstmt.setString(5, LocalDate.now().toString());
	         //pstmt = con.prepareStatement(query);
	         flag=pstmt.executeUpdate();
	         pstmt.close();
	         con.close();
	      } catch(Exception exec) {
	         exec.printStackTrace();
	      }
		
		return flag>0 ? true:false;
	}
	
	public static void addRewards(String cust_name,String cust_phno,float totalCost){
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} //("com.mysql.jdbc.Driver");
		
		String url="jdbc:oracle:thin:@localhost:1521:xe";//"jdbc:mysql://localhost:3306/Retroshop";
		String uname="hr"; 
		String pass="roke";
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rst = null;
		int flag=0;
		int pts=0;
		
		String query1="insert into retro_shop_customer (cust_id,cust_name,cust_phno,cust_pts)"
		+"values(seq2.nextval,?,?,?)";
		try {
			con=DriverManager.getConnection(url,uname,pass); 
			pstmt=con.prepareStatement(query1);
			pstmt.setString(1, cust_name);
			pstmt.setString(2, cust_phno);
			if(totalCost>=100) {
				pts=((int)totalCost/100)*10;
			}
			pstmt.setInt(3, pts); 
			flag=pstmt.executeUpdate();
			pstmt.close();
			con.close();
			
		}catch(Exception exec) {
			exec.printStackTrace();
		}
		
		if(flag>0 && totalCost>=100) {
			System.out.println();
			System.out.println((int)pts+" Reward points awarded to the customer!");
		}
		
		
	}
	
	public static void report(){
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} //("com.mysql.jdbc.Driver");
		
		String url="jdbc:oracle:thin:@localhost:1521:xe";//"jdbc:mysql://localhost:3306/Retroshop";
		String uname="hr"; 
		String pass="roke";
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rst = null;
		
		String query2="Select SUM(Total_cost) from retro_shop_orders where order_date=?";
		try {
			con=DriverManager.getConnection(url,uname,pass); 
			pstmt=con.prepareStatement(query2);
			pstmt.setString(1, LocalDate.now().toString());
			rst=pstmt.executeQuery();
			while(rst.next()) {
				System.out.println("-----TODAY'S REPORT ANALYSIS-----");
				System.out.println("\nTOTAL SALES :- Rs "+rst.getFloat(1));
				itemreport();
				System.out.println("----------------------------------");
				}
			pstmt.close();
			con.close();		
			}catch(Exception exec){
			exec.printStackTrace();
		}	
	}
	
	public static void itemreport() {
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} //("com.mysql.jdbc.Driver");
	
		String url="jdbc:oracle:thin:@localhost:1521:xe"; //"jdbc:mysql://localhost:3306/Retroshop";
		String uname="hr";
		String pass="roke";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rst = null;
    
		String query3="Select Order_Items_code_list from retro_shop_orders";
		String str="";
    
		try {
			con=DriverManager.getConnection(url,uname,pass);
			pstmt=con.prepareStatement(query3);
			rst=pstmt.executeQuery();
			while(rst.next()) { 
				str+=rst.getString(1);				
			}
		
			Map<Character, Integer> map = new HashMap<Character, Integer>();
			for (int i = 0; i < str.length(); i++) {
				char c = str.charAt(i);
				Integer val = map.get(c);
				if((c!='[') && (c!=']') && (c!=',') && (c!=' ')) {
					if (val != null) {
						map.put(c, (val + 1));
					}
					else {
						map.put(c,1);
			    	}
				}  
		    }
			
			Map<Integer, Map<String, Double>> items;
			Map<String, Double> itemDetail;
			try {
				items = JdbcMain.getMenu();
				System.out.println("\n   PRODUCT WISE SALES REPORT   ");
				System.out.println("-----------------------------------");
				System.out.format("%8s%15s%18s%12s","ItemNo","Name","Quantity","Amount(Rs)");
				System.out.println();
				for(Map.Entry<Character, Integer> m: map.entrySet()) {
					Integer key=m.getKey()-48;
					itemDetail=items.get(key);
					System.out.format("%8s",m.getKey());
					for(Map.Entry<String,Double> j:itemDetail.entrySet()) {
						System.out.format("%15s%18s%12s",j.getKey(),m.getValue().toString(),
							Float.toString((float)(j.getValue()*m.getValue())));
						System.out.println();				
					}
				}
			} catch (Exception e) {
				System.out.println("Error in loading Menu!!");
		    }
		
			pstmt.close();
			con.close();
		}catch(Exception exec){
			exec.printStackTrace();
		}
	}
}