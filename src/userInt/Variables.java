package userInt;

import java.util.ArrayList;

import ObjectClasses.Book;

/**
 * this class holds all the global variables that checks to see if the user is logged in or not
 *
 */
public class Variables {
	public static Boolean isLoggedIn = false;
	public static String  userName = "";
	public static String userAccount = "";
	
	
	public static String transactionNum;
	public static String Seller_Name; 
	public static String Seller_Acc_Num; 
	public static String Buyer_Name;
	public static String Buyer_Acc_Num;
	public static String Buyer_Address; 
	public static int Transaction_Date; 
	public static String Book_Title;
	public static String Author_Firstname; 
	public static String Author_Lastname;
	public static String ISBN;
	public static Double Price; 
	public static String Credit_Card_Number; 
	public static Double Percent_Received; 
	public static Double total;
	public static String received; 
	public static String shipped;

    public static double sum=0.0;
	public static String numberAsStriing= String.valueOf(sum);
	
	public static ArrayList<Book> cart = new ArrayList<Book> ();
	
	public static String cartInfo; 
	
}
