package com.retro.shop;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Map;

public class Bill {
	
	public static void BillRecipt(String customerName, List<Map<String,Double>> orderedItems,float totalCost) {
		System.out.println("       RetroCoffee Shop Menu       ");
		System.out.println("       contact : 9876543210        ");
		
		System.out.println("-----------------------------------");
		System.out.println("ItemNo\tName\t\t Price");
			for(Map.Entry<String,Double> j:orderedItems.entrySet()) {
				System.out.println(j.getKey()+"\t"+j.getValue());				
			}
		}
	System.out.println("Total Amount:"\t\t+j.getValue());
	System.out.println("-----------------------------------");
	System.out.println("         THANK YOU          ");
	System.out.println("         VISIT AGAIN         ");
	}
public static void addRewards(String customerName,String phno,float totalCost)throws Exception{
	Class.forName("oracle.jdbc.driver.OracleDriver"); //("com.mysql.jdbc.Driver");
	
	String url="jdbc:oracle:thin:@localhost:1521:xe";//"jdbc:mysql://localhost:3306/Retroshop";
	String uname="hr"; 
	String pass="roke";
	Connection con=null;
	PreparedStatement pstmt=null;
	ResultSet rst = null;
	int flag=0;
	
	String query="insert into retro_shop_customer"
	+"values(seq1.nextval,?,?,?,?)";
	try {
		con=DriverManager.getConnection(url,uname,pass); 
		pstmt=con.prepareStatement(query);
		pstmt.setString(1, customerName);
		pstmt.setString(2, phno); 
		float pts=(totalCost/100)*10;
		pstmt.setFloat(3, pts); 
		//pstmt=con.preparedStatement(query);
		flag=pstmt.executeUpdate();
		pstmt.close();
		con.close();
		
	}catch(Exception exec) {
		exec.printStackTrace();
	}
	
}
}
	
//public static void main(String[] args) {
//        
//        Scanner input = new Scanner(System.in);
//	String[] s = new String[]        {"Americano    ","Latte    ","Cappuccino    ","Espresso     ","Mochachino","Garlic Bread","Nachos     ","Chilli cheese toast  "};
//	double[] rate = new double[]{124.70,115.50,110.50,144.50,139.78,86.50,86.50,105.50};
//			
//	int[] qt = new int[10];
//        int sum=0;
//	boolean quit=true;
//         
//        
//        do{
//            System.out.println("ITEM"+"\t\t\tPrice");
//		for(int i=0;i<10;i++)
//            System.out.println((i+1)+"."+s[i]+"\t\t"+rate[i]);
//            
//            
//            int ch=input.nextInt();
//	    if(ch>0 && ch<10)
//	    {
//		System.out.println("enter the no of quantites of "+s[ch-1]);
//                 int q=input.nextInt();
//	         qt[ch-1]+=q;
//		
//	    }
//	    else
//	    {
//               quit=false;
//              
//            }
//        }while(quit);
//    	
// 	   
//        System.out.println("Your Orders are:\n");
//	    for(int i=0;i<10;i++)
//	    {
//             if(qt[i]!=0)
//	     {
//	      sum+=qt[i]*rate[i];
//              System.out.println(s[i]+"*   "+qt[i]+"=="+qt[i]*rate[i]+"rs");
//	     }
//	    }
//  
//        
//        System.out.println("Your total bill="+sum);
//        
//         System.out.println("Thank you");
//    
//    
//    }
//	
		    
	


}
