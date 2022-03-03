package com.retro.shop;

import java.util.*;
import java.sql.*;

public class Order {
	
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		Map<String,Double> items = JdbcMain.getMenu();
		
		System.out.println("Enter your name: ");
		String name=sc.nextLine();
		
		System.out.println("Enter your Mobilenumber: ");
		String ph=sc.nextLine();
		
		System.out.println("       RetroCoffee Shop Menu       ");
		System.out.println("-----------------------------------");
		System.out.println("ItemNo\tName\t\tPrice");
		int j=1;
		for(Map.Entry<String,Double> i : items.entrySet()) {
			System.out.println(j+"\t"+i.getKey()+"\t"+i.getValue());
			j++;
		}
		
		sc.close();
		
//		System.out.println("Enter the Item what you what(specify the number): ");
//		int ch=sc.nextInt();
//		
//		String query = " insert into orders (name,mobile_no,item_count,item_name)"
//		        + " values (?, ?, ?, ?)";
//		PreparedStatement preparedStmt = con.prepareStatement(query);
//	      preparedStmt.setString (1, "Arnika");
//	      preparedStmt.setString (2, "9874653547");
//	      preparedStmt.setInt    (3, 1);
//	      preparedStmt.setString (4, "");
		

	}

}
