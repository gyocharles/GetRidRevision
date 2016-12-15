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
	
	public void updateInfo(String firstName, String lastName, String address, String billingAddress, String creditNum, String email, String cvc) throws SQLException{
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
	
}
