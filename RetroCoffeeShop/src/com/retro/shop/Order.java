package com.retro.shop;

import java.util.*;

public class Order{
	
	public static void placeOrder() {
		//getting items from database
		Map<Integer, Map<String, Double>> items=new HashMap<>();
		try {
			items = JdbcMain.getMenu();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Scanner sc1 = new Scanner(System.in);
		System.out.println("Enter customer name: ");
		String name = sc1.nextLine();
		System.out.println("Enter customer mobile no: ");
		String phno = sc1.nextLine();
		
		char c;
		List<Integer> iList = new ArrayList<>();
		do {
			System.out.println("Enter the Item what you want(specify the item no):");
			int ch=sc1.nextInt();
			iList.add(ch);
			System.out.println("Would you like to order more(Y/N) :");
			c = sc1.next().charAt(0);
		}while(c =='Y'||c=='y');
		
		
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
		
		boolean flag=false;
		try {
			flag = JdbcMain.addOrder(name, phno,iList.toString(),totalCost);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(flag) {
       	 System.out.println("Order placed successfully!.");
       	 Bill.BillRecipt(name,phno,orderedItems,totalCost);
        }else {
       	 System.out.println("Error in placing order!!Try again.");
        }
	}
}
