package com.retro.shop;

import java.util.*;
import java.sql.*;

public class Order {
	
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		Map<Integer,Map<String,Double>> items = JdbcMain.getMenu();
		
		System.out.println("Enter your name: ");
		String name = sc.nextLine();
		
		System.out.println("Enter your Mobilenumber: ");
		String phno = sc.nextLine();
		
		System.out.println("       RetroCoffee Shop Menu       ");
		System.out.println("-----------------------------------");
		System.out.println("ItemNo\tName\t\tPrice");
		for(Map.Entry<Integer,Map<String,Double>> i : items.entrySet()) {
			System.out.print(i.getKey()+"\t");
			for(Map.Entry<String,Double> j:i.getValue().entrySet()) {
				System.out.println(j.getKey()+"\t"+j.getValue());				
			}
		}
		
		char c;
		ArrayList<Integer> iList = new ArrayList<Integer>();
		
		do {
			System.out.println("Enter the Item what you what(specify the number): ");
			int ch=sc.nextInt();
			iList.add(ch);
			 
			System.out.println("Would you like to order more(Y/N) : ");
			c = sc.next().charAt(0);
		}while(c =='Y');
		
		sc.close();
		
		//iterating through selected items to find totalCost
		float totalCost=0.0f;
		List<Map<String,Double>> orderedItems=new ArrayList<>();
		for(Integer i: iList) {
			for(Map.Entry<String,Double> j:items.get(i).entrySet()) {
				totalCost+=j.getValue();
				Map<String,Double> item=new HashMap<>();
				item.put(j.getKey(), j.getValue());
				orderedItems.add(item);
			}
		}
				
		boolean flag=JdbcMain.addOrder(name, phno,iList.toString(),totalCost);
		
		if(flag) {
       	 System.out.println("Order placed successfully!");
       	 Bill.BillRecipt(name,orderedItems,totalCost);
        }else {
       	 System.out.println("Error in placing order!!Try again.");
        }
		
	}

}
