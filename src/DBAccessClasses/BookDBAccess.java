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

public class BookDBAccess {//may remove public access specifier

	private static Connection conn;
	
	/**
	 * this method will search for books that match the ISBN passed and return them to be displayed. if no book is found a message is returned. 
	 * the method may need to be modified for the different search options if the separate one isn't created i.e. author, subject, edition etc.
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
				//while there is data to be taken in, enter it in the correct variable and put it into a formatted string.
				
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
		
	//return the formated string
		return searchResult;
	}
	
	
	
	/**
	 * This method retrieves all the info of ALL the books in the product table. It calls createResultList to create 
	 * an ArrayList of book objects then returns it. It will be called when the "Browse All" button is hit in the BrowseFrame class.
	 * will either be modified for finding the books of a specific seller or used as a base for a separate similar method
	 * @return an ArrayList of book objects each containing info about a book. may require a toString method to format it
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
//	public ArrayList<Book> searchAllBooks() throws ClassNotFoundException, SQLException
	public String searchAllBooks() throws ClassNotFoundException, SQLException
	{
		ArrayList<Book>books;
		conn = DBConnection.getConnection();
		String searchResult;
		
		PreparedStatement stmt= conn.prepareStatement("SELECT * FROM product");
		ResultSet rs= stmt.executeQuery();
		searchResult = "";
		//books = createResultList(rs);
		
		if(!rs.next()){
			searchResult = "No Book found, please try again.";
		}
		else
			do {
				//while there is data to be taken in, enter it in the correct variable and put it into a formatted string.
				
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
		
		/*for(int i=0; i<books.size(); i++){//prints out the list of results in formatted string
			books.get(i).BooktoString();
		}	*/
		
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
	
	/*
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
	 * This method does the same at getBookByIBSN except it searches by title
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
				//while there is data to be taken in, enter it in the correct variable and put it into a formatted string.
				
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
		return searchResult;//return the formated string
	}
	
	/**
	 * This method searches for books by author's name
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
			//while there is data to be taken in, enter it in the correct variable and put it into a formatted string.
			
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
		return searchResult;//return the formated string
	}
	
	
	 /*
	  * This method will search for a book in the database by seller's account number 
	  * when someone is trying to find their book, they can use this method
	  * 
	  * @param num the seller's account number
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
			while(rs.next()){//while there is data to be taken in, enter it in the correct variable and put it into a formatted string.
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
		return searchResult;//return the formated string
	}
	
	/**
	 * This method will search for a book by the seller's name so if a user what's to find their book they can search by their name
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
			while(rs.next()){//while there is data to be taken in, enter it in the correct variable and put it into a formatted string.
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
		return searchResult;//return the formated string
	}

	/**
	 * This method will search for a book by its unique entry number in the product table 
	 * 
	 * @param entrynum2 the entry number
	 * @return a book object with the information of the book selected
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	
	
	public Book getBookByEntryNum (int id) throws ClassNotFoundException, SQLException{//added to find book by entry number so it can be added to the cart  
		
		conn=DBConnection.getConnection();
		//String searchResult=null;
		
		Book book = null;
		PreparedStatement stmt= conn.prepareStatement("SELECT * FROM product WHERE Entry_number=?");
		
		stmt.setInt(1,id);
		ResultSet rs = stmt.executeQuery();
		
		if(!rs.next())
		{			
			//searchResult="No Book found, please try again";
			//return searchResult;//return a message saying the book wasn't found
			//book=new Book ( 1, 2, "seller", "title",
			//		"authorFirst", "authorLast", "ISBN", 1.7, "bad");
			book = null;
		}
		else

			{//while there is data to be taken in, enter it in the correct variable and put it into a formatted string.
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
		
		  //must add ____ cart.add(book);//add the found 
	}
	/**
	 * This method will be called when a user is adding a book to the database. All the parameters can be passed 
	 * at the moment the method is called or 
	 * the object can be created then past making the method take Book type parameters. 
	 * Changes may have to be made for auto incrementing primary key
	 * and whatever field parameters we decide on keeping or omitting. 
	 * Maybe have a boolean variable to return when the method finishes?
	 * 
	 * @param EntryNumber the auto incremented number that notes the entry of the book in the table
	 * 
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
	 * This method will let users remove a book from the database they no long wish to sell
	 * it will remove a record that matches the title and seller account number. 
	 * There should be a check variable but that depends on how this 
	 * method will be called and where the results will be displayed. 
	 * the check variable will let the user know the record
	 * has been deleted successfully. the primary key of the table or user account(whatever it may be) 
	 * must be passed as a check to make sure a user is 
	 * deleting their own book
	 * 
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


/*
	public ArrayList<Book> getListofBooks() {
		return null;
        //return cart;
	}
*/
	
}
