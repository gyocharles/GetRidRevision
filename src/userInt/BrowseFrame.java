package userInt;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import DBAccessClasses.BookDBAccess;
import ObjectClasses.Book;
/**
 * This Class handles the browsing part of the user interface. adding a book to a cart and searching for a particular book
 * or the entire database to see what's in it. There should be options to search by author, title or ISBN
 * 
 *
 */
@SuppressWarnings("serial")
public class BrowseFrame extends JFrame {
	private static final int FRAME_HEIGHT = 600;
	private static final int FRAME_WIDTH = 950;
	
	private JTextArea searchResultField;	
	private JPanel browsePanel;	
	private JPanel browseHeadPanel;
	private JPanel browseISBNPanel;
	private JPanel browseTitlePanel;
	private JPanel browseAutherPanel;
	
	private JLabel browseHeadLabel;
	private JLabel browseLineLabel;
	private JLabel browseIsbnLabel;
	private JLabel browseTitleLabel;
	private JLabel browseAuthorFirstLabel;
	private JLabel browseAuthorLastLabel;
	private JLabel browseAllLabel;
	private JLabel addToCartLabel;
	
	private JTextField isbnField;
	private JTextField titleField;
	private JTextField authorLastField;	
	private JTextField authorFirstField;	
	private JTextField addToCartField;
	
	private JButton searchISBNButton;
	private JButton searchTitleButton;
	private JButton searchAuthorButton;	
	private JButton searchAllButton;
	private JButton addToCartButton;
	
	private JButton menu;
	
	
	
	BookDBAccess bookdba= new BookDBAccess();
	
	public BrowseFrame() 
	{
		createTextField();
		createTextArea();
		createButtons();
		createPanel();
		
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
	}
	
	private void createTextField()
	      {
        	 browseHeadLabel 	= new JLabel("<html><br> Please search for any book either by ISBN, title, or author.</br></html>");
	         browseLineLabel 	= new JLabel("<html><br>________________________________________________________________\n\n");	 
	         browseIsbnLabel 	= new JLabel("<html><br> ISBN:   <html><br>");
	         browseTitleLabel 	= new JLabel("<html><br>Title:  </br></html>");
	         browseAuthorLastLabel 	= new JLabel("<html><br>Author: Last name: </br></html>");
	         browseAuthorFirstLabel	= new JLabel("<html><br>First name (optional): </br></html>");      
	         browseAllLabel 	= new JLabel("<html><br>Show All: </br></html>");
	         
	         addToCartLabel  = new JLabel("<html><br>Type in ONE book's ISBN, then add to cart: <html><br>");

	         final int ISBN_FIELD_WIDTH = 13;
	         final int TITLE_FIELD_WIDTH = 45;
	         final int AUTHOR_FIELD_WIDTH = 45;
	         
	         isbnField = new JTextField(ISBN_FIELD_WIDTH);
	         titleField = new JTextField(TITLE_FIELD_WIDTH);
	         authorLastField = new JTextField(AUTHOR_FIELD_WIDTH);
	         authorFirstField = new JTextField(AUTHOR_FIELD_WIDTH);
	         
	         addToCartField = new JTextField(ISBN_FIELD_WIDTH);

	         //isbnField.setText("");
	      }

	private void createTextArea() 
	{
		searchResultField = new JTextArea(20, 30);
		//searchResultField.setText();
		searchResultField.setEditable(false);
	}
	
	private void createButtons()
	{
		 searchISBNButton 	= new JButton("Search By ISBN");
		 searchTitleButton 	= new JButton("Search By Title");
		 searchAuthorButton = new JButton("Search By Author");
		 searchAllButton 	= new JButton("Show All Books");
		 addToCartButton 	= new JButton("Add to Cart");
		 
		 menu = new JButton("Menu");
		
		/*
		 * This button when pressed will take the contents of the isbnField, store it in a variable and use it
		 * to find all the books that match this ISBN and then return this information in a formatted string
		 */
		 
		 // ISBN Search
		 
		 ActionListener ISBN_Search_Listener = new ActionListener() 
		 {
 	    	public void actionPerformed(ActionEvent e) 
 	    	{
 	    		//  back-end query results displayed for ISBN search
 	    		//  takes the text in the search field
 	    		
 	    		String search;
 	    		
 	    		try {
 	    			
 	    			// search=Integer.parseInt(isbnField.getText());	    			
 	    			
 	    			search = isbnField.getText();
 	    			
					String searchResult = bookdba.getBookByISBN(search);
					
					// showing search results as a list of book records
					
					searchResultField.setText(searchResult);
													
				} catch (ClassNotFoundException e1) {
					// 
						e1.printStackTrace();
						
				} catch (SQLException e1) {
					// 
						e1.printStackTrace();
				}
 	    	}
 	    };
 	    
 	    
 	    /*	
		 * This button when pressed will take the contents of the titleField, store it in a variable and use it
		 * to find all the books that match this title and then return this information in a formatted string
		 */
 	    
 	// Title Search
		 
 			 ActionListener Title_Search_Listener = new ActionListener() 
 			 {
 	 	    	public void actionPerformed(ActionEvent e) 
 	 	    	{
 	 	    		//  back-end query results displayed for ISBN search
 	 	    		//  takes the text in the search field
 	 	    		
 	 	    		String search;
 	 	    		
 	 	    		try {
 	    				 	    			
 	 	    			search = titleField.getText();
 	 	    			
 						String searchResult = bookdba.getBookByTitle(search);
 						
 						// showing search results as a list of book records
 						
 						searchResultField.setText(searchResult);
 														
 					} catch (ClassNotFoundException e1) {
 						// 
 							e1.printStackTrace();
 							
 					} catch (SQLException e1) {
 						// 
 							e1.printStackTrace();
 					}
 	 	    	}
 	 	    };
 	 	    
 	 	  /*
 		   * This button when pressed will take the contents of the horautField, store it in a variable and use it
 		   * to find all the books that match this author and then return this information in a formatted string
 		   */
 	 	    
 	 	// Author Search
 			 
 			 ActionListener Author_Search_Listener = new ActionListener() 
 			 {
 	 	    	public void actionPerformed(ActionEvent e) 
 	 	    	{
 	 	    		//  back-end query results displayed for ISBN search
 	 	    		//  takes the text in the search field
 	 	    		
 	 	    		String search, search1;
 	 	    		
 	 	    		try {	    			
 	 	    			
 	 	    			search = authorLastField.getText();
	 	    			search1 = authorFirstField.getText();
 	 	    			
 						String searchResult = bookdba.getBookByAuthor(search,search1);
 						
 						// showing search results as a list of book records
 						
 						searchResultField.setText(searchResult);
 														
 					} catch (ClassNotFoundException e1) {
 						// 
 							e1.printStackTrace();
 							
 					} catch (SQLException e1) {
 						// 
 							e1.printStackTrace();
 					}
 	 	    	}
 	 	    };
 	 	    
 	    /*
 	     * This button, when pressed should return a list of every book in the database in a formatted string
 	     * that includes its entry number, title, author etc.
 	     */
 	   ActionListener SearchAllListener = new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		
	    			
	    	//back-end query results displayed for general search
	    	//call to method that will display the contents of ArrayList in formated string
	    		ArrayList<Book> bookResults = null;
	    		String results;
				try {
					bookResults = bookdba.searchAllBooks();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	    		for(int i=0; i<bookResults.size(); i++){//prints out the list of results in formatted string
	    			results=bookResults.get(i).BooktoString();//may add "\n"
	    			searchResultField.append(results);
	    		}
	    		
	    	}
	    };
	    /**
	     * This button when pressed, Adds a book matching the entry number to an ArrayList that functions as
	     * the cart
	     */
	    ActionListener AddToCartListener = new ActionListener() 
	    {
	    	public void actionPerformed(ActionEvent e) 
	    	{
	    	//	bookdba.getBookByEntryNum();
	    		//sends book id number to back end, back end sends back book's info,
	    		//it finds a book by the entry number in the datbase which is the primary key
	    		//so when they are looking at books and want to add one to the cart, they type in the entry number
	    		//which is displayed in the cart
	    		String entrynum=  addToCartField.getText();//added
	    		int Entry= Integer.parseInt(entrynum);//added
	    		
	    		try {//make void-just do it
					Book book=bookdba.getBookByEntryNum(Entry);//added
					
					//cart.add(book);//added
					
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	    		
	 	    	dispose();
	 	    	JFrame frame = new CartFrame();
	 	   		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	 	   		frame.setTitle("GetRid - Cart");
	 	   		frame.setVisible(true);
	    	}
	    };
	    
	    /**
	     * This button when pressed takes the user back to the menu
	     */
	    
	    ActionListener MenuListener = new ActionListener() 
	    {
	    	public void actionPerformed(ActionEvent e) 
	    	{
	 	    	dispose();
	 	    	JFrame frame = new MenuFrame();
	 	   		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	 	   		frame.setTitle("GetRid - Menu");
	 	   		frame.setVisible(true);
	    	}
	    };
	    
	   
	    
	    menu.addActionListener(MenuListener);
	    
	    searchISBNButton.addActionListener(ISBN_Search_Listener);
	    searchTitleButton.addActionListener(Title_Search_Listener);
	    searchAuthorButton.addActionListener(Author_Search_Listener);
		searchAllButton.addActionListener(SearchAllListener);
		addToCartButton.addActionListener(AddToCartListener);
	}
	
	private void createPanel()
	{
		// ====
		
		browseHeadPanel = new JPanel();
		browseHeadPanel.setLayout(new FlowLayout());
        
		browseHeadPanel.add(browseHeadLabel);
		
		add (browseHeadPanel);
		
		// ====
		
		browsePanel = new JPanel();
		browsePanel.setLayout(new FlowLayout());
        
		browsePanel.add(browseHeadLabel);
		
		browsePanel.add(browseIsbnLabel);
		browsePanel.add(isbnField);
		browsePanel.add(searchISBNButton);
		       
		browsePanel.add(browseTitleLabel);
		browsePanel.add(titleField);
		browsePanel.add(searchTitleButton);	
		
        
		browsePanel.add(browseAuthorLastLabel);
		browsePanel.add(authorLastField);	
		browsePanel.add(browseAuthorFirstLabel);
		browsePanel.add(authorFirstField);

		browsePanel.add(searchAuthorButton);
		browsePanel.add(browseLineLabel);
		
		
		JScrollPane scrollPane = new JScrollPane(searchResultField);
		browsePanel.add(scrollPane);
		
		browsePanel.add(searchAllButton);
		
		browsePanel.add(browseLineLabel);
		
		browsePanel.add(addToCartLabel);
		browsePanel.add(addToCartField);
		browsePanel.add(addToCartButton);
		browsePanel.add(menu);
		add(browsePanel);
	}
}