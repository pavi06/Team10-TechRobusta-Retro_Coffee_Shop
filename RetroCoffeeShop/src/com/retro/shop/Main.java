package com.retro.shop;

import java.util.*;

public class Main {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Map<String,Double> items = new HashMap<>();
		items.put("Americano",124.70);
		items.put("Latte",115.78);
		items.put("Cappuccino",110.50);
		items.put("Espresso",144.50);
		items.put("Mochachino",139.78);
		items.put("Garlic Bread",86.50);
		items.put("Nachos",86.50);
		items.put("Chilli cheese toast", 105.50);
		System.out.println("-------RetroCoffeeShop-------");
		
		for(Map.Entry pairEntry:items.entrySet()){
			System.out.println(pairEntry.getKey()+"\t"+pairEntry.getValue());
		}
		

	}

}
