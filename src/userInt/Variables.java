package userInt;

import java.util.ArrayList;

import ObjectClasses.Book;

/*
 * this class holds all the global variables that checks to see if the user is logged in or not
 *
 */
public class Variables {
	public static Boolean isLoggedIn = false;
	public static String  userName = "";
	public static String userAccount = "";
	
	public static ArrayList<Book> cart = new ArrayList<Book> ();
	
}
