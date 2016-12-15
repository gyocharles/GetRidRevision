package DBAccessClasses;

import java.sql.*;
import java.util.*;

import ObjectClasses.Book;

/**
 * this class will handle the interaction between Book objects and the Product database table
 * it will have all the methods related to Book objects like searching for them, adding them to the database, removing them.
 * it will be accessed by the Main Classes when a user searches or wants to make changes to an existing post 
 * or create a new one along with the UserDBAccess class
 * 
 **/

public class BookDBAccess {

	private static Connection conn;
	
	/**
	 * this method will search for books that match the ISBN passed and return the result to be displayed. 
	 * If no book is found a message is returned. 
	 * @param search the ISBN entered in the browse class for the initial search
	 * @return a list of books or "not found" message
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public String getBookByISBN(String search) throws ClassNotFoundException, SQLException
	{
		conn = DBConnection.getConnection();
		String searchResult="";	
		PreparedStatement stmt= conn.prepareStatement	 ("SELECT * FROM product WHERE ISBN=?");	
		stmt.setString(1,search);			
		ResultSet rs= stmt.executeQuery();		
		if(!rs.next()){
			searchResult = "No Book found, please try again.";
		}
		else
			do {
				int entrynum=rs.getInt("Entry_number");
				String title=rs.getString("Booktitle");	
				String ISBN=rs.getString("ISBN");			
				String condition=rs.getString("Condition");
				String authorFirst=rs.getString("Author_Firstname");
				String authorLast=rs.getString("Author_Lastname");
				String seller=rs.getString("Seller_Name");
				double price=rs.getDouble("Price");		
				searchResult = "";
				searchResult += 
						"\nItem ID #### \t" + entrynum +
						"\nTitle: \t" + title +
						"\nISBN: \t" + ISBN +
						"\nAuthor: \t"+ authorFirst + " " + authorLast +
						"\nSeller: \t" + seller +
						"\nCondition: \t" + condition +
						"\nPrice:\t $" + price +"\n\n";			
			} while(rs.next());			
		stmt.close();		
		return searchResult;
	}
	
	/**
	 * This method retrieves all the info of ALL the books in the database. It will be called when the "Browse All" button 
	 * is pressed in the BrowseFrame class.
	 * @return a list of book objects each containing info about a book.
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public String searchAllBooks() throws ClassNotFoundException, SQLException
	{
		ArrayList<Book>books;
		conn = DBConnection.getConnection();
		String searchResult;
		PreparedStatement stmt= conn.prepareStatement("SELECT * FROM product");
		ResultSet rs= stmt.executeQuery();
		searchResult = "";
		if(!rs.next()){
			searchResult = "No Book found, please try again.";
		}
		else
			do {
				int entrynum=rs.getInt("Entry_number");
				String title=rs.getString("Booktitle");	
				String ISBN=rs.getString("ISBN");
				String condition=rs.getString("Condition");
				String authorFirst=rs.getString("Author_Firstname");
				String authorLast=rs.getString("Author_Lastname");
				String seller=rs.getString("Seller_Name");
				double price=rs.getDouble("Price");
				searchResult += 
						"\nItem ID #### \t" + entrynum +
						"\nTitle: \t" + title +
						"\nISBN: \t" + ISBN +
						"\nAuthor: \t"+ authorFirst + " " + authorLast +
						"\nSeller: \t" + seller +
						"\nCondition: \t" + condition +
						"\nPrice:\t $" + price +"\n\n";		
			} while(rs.next());			
		stmt.close();	
		return searchResult;
	}

	/**
	 * This method creates the ArrayList of books objects with the help of creatBook(rs);
	 * @param rs the result set from ArrayList<Book>allBooks
	 * @return ArrayList of book objects
	 * @throws SQLException
	 */
	private static ArrayList<Book> createResultList(ResultSet rs) throws SQLException 
	{
		ArrayList<Book>books = new ArrayList<Book>();
		
		while(rs.next())
		{
			Book book= createBook(rs);
			books.add(book);
		}
		return books;
	}
	
	/*UNUSED
	 * This method assists ArrayList<Book>allBooks in creating the ArrayList of books by creating the book objects
	 * @param rs the result set
	 * @return book object
	 * @throws SQLException
	 */
	
	private static Book createBook(ResultSet rs)throws SQLException
	{
		int entrynum		= rs.getInt("Entry_number");
		int sellerAcc		= rs.getInt("SellerAccountNum");
		String title		= rs.getString("Booktitle");
		String ISBN			= rs.getString("ISBN");
		String condition	= rs.getString("Condition");
		String authorFirst	= rs.getString("Author_Firstname");
		String authorLast	= rs.getString("Author_Lastname");
		String seller		= rs.getString("Seller_Name");
		double price		= rs.getDouble("Price");
		
		Book book= new Book (entrynum, sellerAcc, seller, title, authorFirst, authorLast, ISBN, price, condition);
		return book;
	}
	
	/**
	 * This method searches for a book by its title
	 * @param title the title of the book being searched for
	 * @return formated string of the result(s)
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public String getBookByTitle(String booktitle) throws ClassNotFoundException, SQLException
	{
		conn=DBConnection.getConnection();
		String searchResult="";
		PreparedStatement stmt= conn.prepareStatement("SELECT * FROM product WHERE Booktitle=?");
		stmt.setString(1, booktitle);
		ResultSet rs= stmt.executeQuery();
		if(!rs.next()){
			searchResult="No Book found, please try again";
		}
		else
			do {
				int entrynum=rs.getInt("Entry_number");
				String title = rs.getString("Booktitle");
				String ISBN 		= rs.getString("ISBN");				
				String condition 	= rs.getString("Condition");
				String authorFirst 	= rs.getString("Author_Firstname");
				String authorLast 	= rs.getString("Author_Lastname");
				String seller		= rs.getString("Seller_Name");
				double price		= rs.getDouble("Price");
				searchResult = "";
				searchResult += 
						"\nItem ID #### \t" + entrynum +
						"\nTitle: \t" + title +
						"\nISBN: \t" + ISBN +
						"\nAuthor: \t"+ authorFirst + " " + authorLast +
						"\nSeller: \t" + seller +
						"\nCondition: \t" + condition +
						"\nPrice:\t $" + price +"\n\n";			
			} while(rs.next());
		stmt.close();
		return searchResult;
	}
	
	/**
	 * This method searches for a book by author's name
	 * @param first author's first name
	 * @param last author's last name
	 * @return formated string of the result(s)
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public String getBookByAuthor( String last, String first ) throws ClassNotFoundException, SQLException
	{
		conn=DBConnection.getConnection();
		String searchResult="";
		PreparedStatement stmt =null;
		
		// checking for optional first name search.
		if ( (first.trim()).isEmpty() )
		{	
			stmt= conn.prepareStatement("SELECT * FROM product WHERE Author_Lastname=?");
			stmt.setString(1,last);
		}
		else {
		    stmt= conn.prepareStatement("SELECT * FROM product WHERE Author_Firstname=? AND Author_Lastname=?");	
			stmt.setString(1,first);
			stmt.setString(2,last);
			}
			
		ResultSet rs= stmt.executeQuery();
		if(!rs.next()){
			searchResult="No Book found, please try again";
		}
		else	
		do {
			int entrynum=rs.getInt("Entry_number");
			String title		=rs.getString("Booktitle");			
			String ISBN			=rs.getString("ISBN");			
			String condition	=rs.getString("Condition");
			String authorFirst	=rs.getString("Author_Firstname");
			String authorLast	=rs.getString("Author_Lastname");
			String seller		=rs.getString("Seller_Name");
			double price		=rs.getDouble("Price");	
			searchResult = "";
			searchResult += 
					"\nItem ID #### \t" + entrynum +
					"\nTitle: \t" + title +
					"\nISBN: \t" + ISBN +
					"\nAuthor: \t"+ authorFirst + " " + authorLast +
					"\nSeller: \t" + seller +
					"\nCondition: \t" + condition +
					"\nPrice:\t $" + price +"\n\n";		
		} while(rs.next());				
		stmt.close();
		return searchResult;
	}
	
	/**UNUSED
	 * This method will search for a book in the database by seller's account number
	  *@param num the seller's account number
	  * @return formatted string of the result
	  * @throws ClassNotFoundException
	  * @throws SQLException
	 */
	public String getBookByAccNum(int num)throws ClassNotFoundException, SQLException
	{
		conn=DBConnection.getConnection();
		String searchResult="";
		PreparedStatement stmt= conn.prepareStatement("SELECT  * FROM product WHERE SellerAccountNum=?");	
		stmt.setInt(1,num);
		ResultSet rs= stmt.executeQuery();	
		if(!rs.next()){
			searchResult="No Book found, please try again";
		}	
		else
			while(rs.next()){
				int entrynum=rs.getInt("Entry_number");
				String title	= rs.getString("Booktitle");
				String ISBN		= rs.getString("ISBN");
				String condition= rs.getString("Condition");
				String authorFirst=rs.getString("Author_Firstname");
				String authorLast=rs.getString("Author_Lastname");
				String seller	=rs.getString("Seller_Name");
				double price	=rs.getDouble("Price");		
				searchResult = "";
				searchResult += 
						"\nItem ID #### \t" + entrynum +
						"\nTitle: \t" + title +
						"\nISBN: \t" + ISBN +
						"\nAuthor: \t"+ authorFirst + " " + authorLast +
						"\nSeller: \t" + seller +
						"\nCondition: \t" + condition +
						"\nPrice:\t $" + price +"\n\n";
			}	
		stmt.close();
		return searchResult;
	}
	
	/**UNUSED
	 * This method will search for a book by the seller's name 
	 * @param name the name of the seller of a book
	 * @return a formatted String of the result
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public String getBookBySellerName(String name)throws ClassNotFoundException, SQLException
	{
		conn=DBConnection.getConnection();
		String searchResult="";
		PreparedStatement stmt= conn.prepareStatement("SELECT * FROM product WHERE Seller_Name=?");
		stmt.setString(1,name);
		ResultSet rs= stmt.executeQuery();
		if(!rs.next()){
			searchResult="No Book found, please try again";
		}
		else
			while(rs.next()){
				int entrynum	=	rs.getInt("Entry_number");
				String title	=	rs.getString("Booktitle");
				String ISBN		=	rs.getString("ISBN");
				String condition	= rs.getString("Condition");
				String authorFirst	= rs.getString("Author_Firstname");
				String authorLast	= rs.getString("Author_Lastname");
				String seller	= rs.getString("Seller_Name");
				double price	= rs.getDouble("Price");
				
				searchResult = "";
				searchResult += 
						"\nItem ID #### \t" + entrynum +
						"\nTitle: \t" + title +
						"\nISBN: \t" + ISBN +
						"\nAuthor: \t"+ authorFirst + " " + authorLast +
						"\nSeller: \t" + seller +
						"\nCondition: \t" + condition +
						"\nPrice:\t $" + price +"\n\n";			}
		
		stmt.close();
		return searchResult;
	}

	/**
	 * This method will search for a book by its unique entry number in the product table 
	 * @param entrynum2 the entry number
	 * @return a book object with the information of the book selected
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public Book getBookByEntryNum (int id) throws ClassNotFoundException, SQLException
	{	
		conn=DBConnection.getConnection();
		Book book = null;
		PreparedStatement stmt= conn.prepareStatement("SELECT * FROM product WHERE Entry_number=?");
		stmt.setInt(1,id);
		ResultSet rs = stmt.executeQuery();
		if(!rs.next()){
			book = null;
		}
		else{
				int entrynum		= rs.getInt("Entry_number");
				int sellAccnum		= rs.getInt("SellerAccountNum");
				String title		= rs.getString("Booktitle");
				String ISBN			= rs.getString("ISBN");
				String condition	= rs.getString("Condition");
				String authorFirst	= rs.getString("Author_Firstname");
				String authorLast	= rs.getString("Author_Lastname");
				String seller		= rs.getString("Seller_Name");
				double price=rs.getDouble("Price");
				book = new Book ( entrynum, sellAccnum, seller, title,
						authorFirst, authorLast, ISBN, price, condition);
			} 		
		stmt.close();
		return book;
	}
	
	/**
	 * This method will be called when a user is adding a book to the database. 
	 * @param EntryNumber the auto incremented number that notes the entry of the book in the table
	 * @param SellerAccNum the seller's account number that may or may not be randomly generated by us
	 * @param SellerName the name of the Seller
	 * @param BookTitle the title of the book
	 * @param AuthorFirstname the first name of the Author of the book
	 * @param AuthorLastname the last name of the Author of the book
	 * @param ISBN the book's ISBN
	 * @param Price the price the seller is asking for
	 * @param Condition the condition of the book
	 * @throws SQLException 
	 */
	public void addBook( int SellerAccNum, String SellerName, String BookTitle, String AuthorFirstname, String AuthorLastname, String ISBN, double Price, String Condition) throws SQLException
	{
		conn=DBConnection.getConnection();
		PreparedStatement stmt= conn.prepareStatement("INSERT into Product values(?, ?, ?, ?, ?, ?, ?, ?, ?)");
		//stmt.setInt(1, EntryNumber); this field of the table is auto incremented
		stmt.setInt(2, SellerAccNum);
		stmt.setString(3, SellerName);
		stmt.setString(4, BookTitle);
		stmt.setString(5, AuthorFirstname);
		stmt.setString(6, AuthorLastname);
		stmt.setString(7, ISBN);
		stmt.setDouble(8, Price);
		stmt.setString(9, Condition);
		stmt.close();
	}
	
	/**
	 * This method will let users remove a book from the database they no long wish to sell.
	 * it will remove a record that matches the title and seller account number. 
	 * @param entrynum
	 * @param accNum
	 * @throws SQLException 
	 */
	public void removeBook(String title, int accNum) throws SQLException{
		conn=DBConnection.getConnection();
		PreparedStatement stmt= conn.prepareStatement("DELETE from Product where Booktitle=? AND SellerAccountNum=?");
		stmt.setString(1, title);
		stmt.setInt(2, accNum);
	}
	
}
