package com.retro.shop;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

public class Bill {
	
	public static void BillRecipt(String customerName,String phno,List<Map<String,Double>> orderedItems,float totalCost) {		
		
		System.out.println("\n\n---------Bill Receipt-------------");
		System.out.println("       RetroCoffee Shop       ");
		System.out.println("       contact : 9876543210        ");
		System.out.println("-----------------------------------");
		System.out.format("%8s%15s%10s","ItemNo","Name","Price");
		System.out.println();
		int count=0;
		for(Map<String,Double> j:orderedItems) {
			for(Map.Entry<String,Double> i:j.entrySet()) {
				System.out.format("%8d%15s%10s",++count,i.getKey(),i.getValue().toString());
				System.out.println();
				//System.out.println(++count+"\t"+i.getKey()+"\t"i.getValue());
			}
		}

		System.out.println();
		System.out.format("%23s%10s","Total Amount:",Float.toString(totalCost));
		System.out.println();
		addRewards(customerName, phno, totalCost);
		System.out.println("\n-----------------------------------");
		System.out.println("              THANK YOU          ");
		System.out.println("             VISIT AGAIN         ");
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
	
}
