package com.retro.shop;

import java.time.LocalDate;
import java.util.Map;
import java.util.Scanner;

public class Main {
	
	public static void displayMenu() {
		Map<Integer, Map<String, Double>> items;
		try {
			items = JdbcMain.getMenu();
			System.out.println("       RetroCoffee Shop Menu       ");
			System.out.println("-----------------------------------");
			System.out.format("%8s%15s%12s","ItemNo","Name","Price(Rs)");
			System.out.println();
			for(Map.Entry<Integer,Map<String,Double>> i : items.entrySet()) {
				System.out.format("%8d",i.getKey());
				for(Map.Entry<String,Double> j:i.getValue().entrySet()) {
					System.out.format("%15s%12s",j.getKey(),j.getValue().toString());
					System.out.println();				
				}
			}
		} catch (Exception e) {
			System.out.println("Error in loading Menu!!");
		}
	}  

	public static void main(String[] args) {
		
		Main m = new Main();
		System.out.println("Welcome to Retro Coffee Shop");
		System.out.println("----MainMenu----\n1.Show Menu\n2.Order Entry\n3.DailySalesReport\n4.DataSetup\n5.Exit");
		char ch,c;
		Scanner sc = new Scanner(System.in);
		do {
			System.out.println("\nEnter your choice:");
			ch=sc.nextLine().charAt(0);
			switch(ch) {
			case '1':
				displayMenu();
				break;
			case '2':
				Order.placeOrder();
				break;
			case '3':
				try {
					JdbcMain.report();
				} catch (Exception e) {
					System.out.println("Error in displaying the reports!");
					e.printStackTrace();
				}
				break;
			case '4':
				System.out.println("--------------DataSetUp--------------\n1.Add item\n2.Add customer\nEnter the Option: ");
				Scanner sc4=new Scanner(System.in);
				int n =sc4.nextInt();
				switch(n) {
					case 1:
						JdbcMain.insertItem();
						break;
						
//					case 2:
//						System.out.println("Enter the gst in percent: ");
//						float gst = sc4.nextFloat();
//						JdbcMain.setGst(gst);
//						break;
					
					case 2:
						System.out.println("Enter the Cutsomer Name: ");
						String cust_name = sc4.next();
						System.out.println("Enter the Cutsomer PhoneNumber: ");
						String cust_phno = sc4.next();
						float totalCost=0.0f;
						JdbcMain.addRewards(cust_name, cust_phno, totalCost);
						break;
				}
				break;
				
			case '5':
				System.out.println("Thank you for visiting RetroCoffee Shop!");
				break;
				
			default:
				System.out.println("Enter a valid choice!");
				break;
			}
		}while(ch!='5');
				
	}

}
