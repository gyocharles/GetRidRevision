package DBAccessClasses;
/**
 * these are import statements that may be needed and are subject to change
 */
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.sql.*;
import java.text.SimpleDateFormat;

import userInt.Variables;

/**
 * this class will handle the interaction between the program and the transaction table
 * this includes all transaction related actions and methods to fill the table
 * it will most likely be accessed by the Main Classes when an order is completed to create a record
 * A summary of the transaction will be sent to the customer as a receipt once a customer makes a transaction.
 * @param transactionNum This represents the transaction number
 * @param Seller_Name This represents the name of the seller
 * @param Seller_Acc_Num This represents the sellers account number
 * @param Buyer_Name This represents the name of the buyer
 * @param Buyer_Acc_Num This represents the buyers account number
 * @param Buyer_Address This represents the buyers address
 * @param Transaction_Date This represents the date on which a transaction was made
 * @param Book_Title This represents the title of a book 
 * @param Author_Firstname This represents the first name of an author from a book
 * @param Author_Lastname This represents the last name of an author 
 * @param ISBN This represents the ISBN of a book
 * @param Price This represents the price of a book
 * @param Credit_Card_Number This represents a users credit card number
 * @param Percent_Received This represents the percent that we take from every transaction
 * @param total This represents the total amount of a transaction
 * 
 * 
 *
 */


public class TransactionDBAccess {
	
	
	private Connection conn;
	int transactionNum;
	String Seller_Name; 
	String Seller_Acc_Num; 
	String Buyer_Name;
	String Buyer_Acc_Num;
	String Buyer_Address; 
	int Transaction_Date; 
	String Book_Title;
	String Author_Firstname; 
	String Author_Lastname;
	String ISBN;
	Double Price; 
	String Credit_Card_Number; 
	Double Percent_Received; 
	Double total;
	String received; 
	String shipped;

	
	/**
	 * The following method is in charge of creating a transaction once a user makes a purchase. 
	 * More specifically this method updates the fields of the transaction table with the appropriate inputs.
	 * @throws SQLException
	 */
	
	public void createTransaction(String transactionNum, String Seller_Name, String Seller_Acc_Num, String Buyer_Name, String Buyer_Acc_Num,
			String Buyer_Address, int Transaction_Date, String Book_Title, String Author_Firstname, 
			String Author_Lastname, String ISBN, Double Price, String Credit_Card_Number, Double Percent_Received, 
			Double total, String received, String shipped ) throws SQLException 
		
	{
		conn=DBConnection.getConnection();
		
		PreparedStatement stmt=conn.prepareStatement("INSERT into Product values(?, ?, ?, ?, ?,?,?,?,?,?,?,?,?,?, ?, ?, ?)");  	
		String transactionD = new SimpleDateFormat("MM.dd.yyyy.HH.mm.ss").format(new Timestamp(Transaction_Date));
				
		stmt.setString(1,transactionNum);  
		stmt.setString(2,Seller_Name); 
		stmt.setString(3,Seller_Acc_Num);
		stmt.setString(4,Buyer_Name);
		stmt.setString(5, Buyer_Acc_Num);
		stmt.setString(6, Buyer_Address);
		stmt.setString(7, transactionD);
		stmt.setString(8,Book_Title );
		stmt.setString(9, Author_Firstname);
		stmt.setString(10, Author_Lastname);
		stmt.setString(11, ISBN);
		stmt.setDouble(12,Price);
		stmt.setString(13, Credit_Card_Number);
		stmt.setDouble(14, Percent_Received);
		stmt.setDouble(15, total);
		stmt.setString(16,received);
		stmt.setString(17, shipped);
		stmt.executeUpdate(); 
		conn.close();
	}
	
	
	

	/**
	 * The following method is in charge of initiating the createTransaction method. This method will be called in the 
	 * actionListener class for the Checkout and pay button.
	 */
	
	public void addTransaction()
	{
		try {
			this.createTransaction(Variables.transactionNum,  Variables.Seller_Name,  
					Variables.Seller_Acc_Num,  Variables.Buyer_Name,  Variables.Buyer_Acc_Num,
					Variables.Buyer_Address, 
					 Variables.Transaction_Date,  Variables.Book_Title,  Variables.Author_Firstname, 
					 Variables.Author_Lastname,  Variables.ISBN,  Variables.Price,  
					 Variables.Credit_Card_Number,  Variables.Percent_Received, 
					 Variables.total,  Variables.received,  Variables.shipped);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}
	
	
	
	/**
	 * The following method is in charge of retrieving the transaction. That will then be sent to the user asa  receipt once they
	 * make a transaction. This method will get used in the user notification method. 
	 * @throws SQLException
	 */
	public void retrieveTransaction( ) throws SQLException{ 
		
		conn=DBConnection.getConnection();
		PreparedStatement stmt=conn.prepareStatement("select * from transaction");  
		ResultSet rs= stmt.executeQuery();
		while(rs.next()){
			System.out.println(rs.getInt(1)+"/n"+rs.getString(2)+"/n"+rs.getString(4)+"/n"+rs.getInt(5)+"/n"+rs.getString(6)+"/n"
		+rs.getString(7)+"/n"+rs.getString(8)+"/n"+rs.getString(9)+"/n"+rs.getString(10)+"/n"+rs.getInt(11)+"/n"+rs.getDouble(15));
		}
		

		
	}
}
	





