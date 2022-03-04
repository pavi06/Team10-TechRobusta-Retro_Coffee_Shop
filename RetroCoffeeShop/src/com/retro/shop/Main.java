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
			System.out.format("%8s%15s%10s","ItemNo","Name","Price");
			System.out.println();
			for(Map.Entry<Integer,Map<String,Double>> i : items.entrySet()) {
				System.out.format("%8d",i.getKey());
				for(Map.Entry<String,Double> j:i.getValue().entrySet()) {
					System.out.format("%15s%10s",j.getKey(),j.getValue().toString());
					System.out.println();				
				}
			}
		} catch (Exception e) {
			System.out.println("Error in loading Menu!!");
		}
	}  

	public static void main(String[] args) {
		
		System.out.println("Welcome to Retro Coffee Shop");
		System.out.println("----MainMenu----\n1.Show Menu\n2.Order Entry\n3.DailySalesReport\n4.Exit");
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
//					JdbcMain.reports();
				} catch (Exception e) {
					System.out.println("Error in displaying the reports!");
					e.printStackTrace();
				}
				break;
			default:
				System.out.println("Enter a valid choice!");
				break;
			}
		}while(ch!='3');
		System.out.println("Thank you for visiting RetroCoffee Shop!");
	}

}
