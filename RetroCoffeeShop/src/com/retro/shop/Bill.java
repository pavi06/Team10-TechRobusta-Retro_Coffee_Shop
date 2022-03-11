package com.retro.shop;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

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
				System.out.format("%8d%15s%10s",++count,i.getKey(),"Rs"+i.getValue().toString());
				System.out.println();
				//System.out.println(++count+"\t"+i.getKey()+"\t"i.getValue());
			}
		}

		float gst_amount=0.0f;
		gst_amount=totalCost*JdbcMain.getGst()/100;
		System.out.println();
		System.out.format("%23s%10s","Gst Amount:","Rs"+Float.toString(gst_amount));
		System.out.println();
		System.out.format("%23s%10s","Total Amount:","Rs"+Float.toString(totalCost));
		System.out.println();
		JdbcMain.addRewards(customerName, phno, totalCost);
		System.out.println("\n-----------------------------------");
		System.out.println("              THANK YOU          ");
		System.out.println("             VISIT AGAIN         ");
	}	
		
}
