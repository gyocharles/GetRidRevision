package DBAccessClasses;
/**
 * this class grants access to the database via the getConnection method which is called by the other
 * classes in the package.
 */
import java.sql.*;
 

public class DBConnection {
	private static Connection conn; 

	public static void initial() throws ClassNotFoundException{
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			System.out.println("good");
		} catch (InstantiationException e) {
			// 
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// 
			e.printStackTrace();
		}
		catch (ClassNotFoundException ex) {
			   System.out.println("Error: unable to load driver class!");
			   System.exit(1);
		}
 
	}
	public static Connection getConnection()throws SQLException{
		if(conn==null)
		conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/book?autoReconnect=true&useSSL=false", "root", "ganam");
		return conn;
	}

	public static void close(){
		if(conn!=null)
			try{
				conn.close();
			}catch(SQLException ex){
				ex.printStackTrace();
			}
	}
	
	public static  boolean checkLogin(String uname,String pwd) throws ClassNotFoundException, SQLException
	{
		//conn = DBConnection.getConnection();
				
		PreparedStatement pst = conn.prepareStatement	 ("SELECT * FROM user_accounts WHERE username=? AND Password=?");;
	    ResultSet rs;
	    
		try 
		{
			pst.setString(1, uname); //this replaces the 1st  "?" in the query for username
			pst.setString(2, pwd);    //this replaces the 2st  "?" in the query for password
			//executes the prepared statement
			rs=pst.executeQuery();
			if(rs.next())
			{
				//TRUE iff the query founds any corresponding data
				return true;
			}
			else
			{
				return false;
			}
		} 
		
		catch (Exception e) 
		{
			// TODO Auto-generated catch block
			System.out.println("error while validating"+e);
			return false;
		}
	}
	

}