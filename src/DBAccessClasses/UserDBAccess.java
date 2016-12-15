package DBAccessClasses;
/**
 * these are import statements that may be needed and are subject to change
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
/**
 * this class will handle the interaction between User objects and the UserAcount table
 * it will contain all methods related to the user object like creating new accounts, making changes, deleting them. 
 * it will be accessed by the Main Classes and access the BookDBAccess class when editing posts. 
 * takes care of log in and maybe log out
 * @author gchar158
 *
 */

	public class UserDBAccess {

	    private static Connection conn;
	    
	    public void updateInfo(String firstName, String lastName, String address, String billingAddress, String creditNum, String email, String cvc) throws SQLException
	    {
	        conn=DBConnection.getConnection();
	        PreparedStatement stmt= conn.prepareStatement("update user_accounts set First Name=?, Last Name=?, Address=?, Billing Address=?, "
	                + "Credit Card Number=? where email=?");
	        stmt.setString(1, firstName);
	        stmt.setString(2, lastName);
	        stmt.setString(3, address);
	        stmt.setString(4, address);;
	        stmt.setString(5,creditNum);
	        stmt.setString(6, email);
	        
	        stmt.close();
	    }
	    
		    public void addInfo
		    
		    		(	String firstName, 
		    			String lastName, 
		    			String email, 
		    			String address, 
		    			String username,
		    			String password, 
		    			String cardNumber, 
		    			String expirationDate,
		    			String cvcCodeField,	
		    			String NameOnCard, 	
		    		 	String billingAddress, 	    		 	
		    		 	String securityQuest,  
		    		 	String securityQuestAns)  throws SQLException
		    		
		    		
		    {
		         
		        
		    	conn=DBConnection.getConnection();
		        
		        
		        PreparedStatement stmt= conn.prepareStatement
		        		
		        ("insert INTO user_accounts values ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, null, ?)");
		        
		        stmt.setString	(1, firstName);
		        stmt.setString	(2, lastName);
		        stmt.setString	(3, address);		        
		        stmt.setString	(4, email);
		        stmt.setString	(5, billingAddress);
		        stmt.setString	(6, cardNumber);
		        stmt.setString	(7, NameOnCard);
		        stmt.setString	(8, expirationDate);
			    stmt.setString	(9, cvcCodeField);	 
			    stmt.setString	(10,password);	
		        stmt.setString	(11, securityQuest);
		        stmt.setString	(12, securityQuestAns);
		        stmt.setString	(13, username);
        
		        stmt.execute();		        
		        stmt.close();
		
	    }
}
