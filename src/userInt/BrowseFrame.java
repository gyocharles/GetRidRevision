package userInt;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.*;

import DBAccessClasses.BookDBAccess;
import ObjectClasses.Book;

/**
 * This Class handles the browsing part of the program. From here users can search for a book in the database by title, author or ISBN
 * They can also view all the books in the database if they just want to browse our database. If the user isn't logged in when they try to
 * add a book to their cart, they will be prompted to do so or sign up after which they can return here or go to the sub menu
 */
@SuppressWarnings("serial")
public class BrowseFrame extends JFrame {
	private static final int FRAME_HEIGHT = 650;
	private static final int FRAME_WIDTH = 900;

	private JTextArea searchResultField;	

	private JLabel headLabel;
	private JLabel lineLabel;
	private JLabel lineLabel1;
	private JLabel isbnLabel;
	private JLabel titleLabel;
	private JLabel authorLabel;	
	private JLabel firstNameLabel;
	//	private JLabel lastNameLabel;
	private JLabel showAllLabel;
	private JLabel addToCartLabel;

	private JTextField isbnField;
	private JTextField titleField;
	private JTextField authorLastField;	
	private JTextField authorFirstField;	
	private JTextField addToCartField;

	private JButton ISBNButton;
	private JButton TitleButton;
	private JButton AuthorButton;	
	private JButton ShowAllButton;
	private JButton CartButton;
	private JButton MenuButton;

	private  JPanel mainPanel;
	private	 JPanel headPanel;		 
	private  JPanel isbnPanel; 		 
	private  JPanel titlePanel; 	 
	private  JPanel authorPanel; 	 
	//	private  JPanel lastNamePanel;
	private  JPanel firstNamePanel;
	private  JPanel showAllPanel; 	 
	private  JPanel cartPanel;
	private  JPanel menuPanel;
	private  JPanel linePanel1;
	private  JPanel linePanel;		 
	private  JScrollPane scrollPane;

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
		headLabel 		= new JLabel("Please search for any book either by ISBN, title, or author.  ");
		lineLabel	    = new JLabel("_______________________________________________________________________________");
		lineLabel1	    = new JLabel("_______________________________________________________________________________");
		//	lineLabel2	    = new JLabel("_______________________________________________________________________________");

		isbnLabel 		= new JLabel("      ISBN:    ");
		titleLabel 		= new JLabel("      Title:   ");
		authorLabel 	= new JLabel("      Author, Last name:     ");
		firstNameLabel	= new JLabel("      First name (optional): ");       
		addToCartLabel  = new JLabel("      Type in ONE book's ISBN, then add to cart:  ");
		showAllLabel 	= new JLabel("      Show All Books in the Catalog:  ");

		final int ISBN_FIELD_WIDTH 		= 13;
		final int TITLE_FIELD_WIDTH 	= 35;
		final int AUTHOR_FIELD_WIDTH 	= 35;

		isbnField 			= new JTextField(ISBN_FIELD_WIDTH);
		titleField 			= new JTextField(TITLE_FIELD_WIDTH);
		authorLastField 	= new JTextField(AUTHOR_FIELD_WIDTH);
		authorFirstField 	= new JTextField(AUTHOR_FIELD_WIDTH);	         
		addToCartField 		= new JTextField(ISBN_FIELD_WIDTH);

	}

	private void createTextArea() 
	{
		searchResultField = new JTextArea(20, 30);
		searchResultField.setText("");
		searchResultField.setEditable(false);
	}

	private void createButtons()
	{
		ISBNButton 	= new JButton("Search By ISBN");
		TitleButton 	= new JButton("Search By Title");
		AuthorButton = new JButton("Search By Author");
		ShowAllButton 	= new JButton("Show All Books");
		CartButton 	= new JButton("Add to Cart");
		MenuButton = new JButton("Back to Main Menu");

		ActionListener ISBN_Search_Listener = new ActionListener() 
		{
			public void actionPerformed(ActionEvent e){ 
				String search;
				try {
					search = isbnField.getText();
					String searchResult = bookdba.getBookByISBN(search);
					searchResultField.setText(searchResult);
				} 
				catch (ClassNotFoundException e1) 
				{ e1.printStackTrace(); } 
				catch (SQLException e1) 
				{ e1.printStackTrace();	}
			}
		};

/**Title Search
 *This button when pressed will take the contents of the titleField, store it in a variable and use it
 * to find all the books that match this title and then return this information in a formatted string 
 */

		ActionListener Title_Search_Listener = new ActionListener(){ 
			public void actionPerformed(ActionEvent e) {
				String search;
				try {
					search = titleField.getText();
					String searchResult = bookdba.getBookByTitle(search);
					searchResultField.setText(searchResult);
				} 
				catch (ClassNotFoundException e1) 
				{ e1.printStackTrace(); } 
				catch (SQLException e1) 
				{ e1.printStackTrace();	}
			}
		};

/**
 * Author Search
 * This button when pressed will take the contents of the horautField, store it in a variable and use it
 * to find all the books that match this author and then return this information in a formatted string
**/

		ActionListener Author_Search_Listener = new ActionListener(){ 
			public void actionPerformed(ActionEvent e){ 
				String search, search1;
				try {	    			
					search  = authorLastField.getText();
					search1 = authorFirstField.getText();
					String searchResult = bookdba.getBookByAuthor(search,search1);
					searchResultField.setText(searchResult);
					scrollPane.repaint();
				} 
				catch (ClassNotFoundException e1) 
				{ e1.printStackTrace(); } 
				catch (SQLException e1) 
				{ e1.printStackTrace();	}
			}
		};
 
		/**
		 * General Search
		 * This button, when pressed should return a list of every book in the database in a formatted string
		  that includes its entry number, title, author etc.
		 */
		ActionListener SearchAllListener = new ActionListener(){ 
			public void actionPerformed(ActionEvent e){ 
				ArrayList<Book> bookResults = null;
				String results;
				String searchResult="";
				try {
					searchResult = bookdba.searchAllBooks();
				} 
				catch (ClassNotFoundException e1) 
				{ e1.printStackTrace(); } 
				catch (SQLException e1) 
				{ e1.printStackTrace();	}
				searchResultField.setText(searchResult);
			}
		};

		/**
		 * Add to Cart
		 * This button when pressed, Adds a book matching the entry number entered by the user
		 * to an ArrayList that functions as the cart
		 */ 
		ActionListener AddToCartListener = new ActionListener(){ 
			public void actionPerformed(ActionEvent e){ 
				String book_id =  (addToCartField.getText());			
				int id;
				if ( !(book_id.trim()).isEmpty())
				{
					try {
						id = Integer.parseInt(book_id);
					}
					catch(NumberFormatException e2)
					{
						return;
					}
					try {
						Book book = bookdba.getBookByEntryNum(id);	                
						if (book!=null)		
						{
							Variables.cart.add(book);							 							
							dispose();
							JFrame frame = new CartFrame();
							frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
							frame.setTitle("GetRid - Cart");
							frame.setVisible(true);
						}	
					} 
					catch (ClassNotFoundException e1) 
					{ e1.printStackTrace(); } 
					catch (SQLException e1) 
					{ e1.printStackTrace();	}	
				}
			}
		};

		/**
		 * Return to Menu
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

		MenuButton.addActionListener(MenuListener);
		ISBNButton.addActionListener(ISBN_Search_Listener);
		TitleButton.addActionListener(Title_Search_Listener);
		AuthorButton.addActionListener(Author_Search_Listener);
		ShowAllButton.addActionListener(SearchAllListener);
		CartButton.addActionListener(AddToCartListener);


	}

	private void createPanel(){
		mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		// inside panels
		headPanel 		= 	new JPanel();
		isbnPanel 		= 	new JPanel();
		titlePanel 		= 	new JPanel();
		authorPanel 	= 	new JPanel();
		//	lastNamePanel 	=	new JPanel();
		firstNamePanel 	=	new JPanel();
		showAllPanel 	= 	new JPanel();
		cartPanel 		= 	new JPanel();
		menuPanel 		= 	new JPanel();
		linePanel1 		= 	new JPanel();
		linePanel 		= 	new JPanel();

		scrollPane = new JScrollPane(searchResultField); 	
		//	scrollPane.setLayout(new ScrollPaneLayout());
		//	scrollPane.setPreferredSize(new Dimension(20,40));
		scrollPane.add(searchResultField);
		scrollPane.setSize(20, 20);

		// head panel  
		headPanel.add(headLabel);
		headPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

		// ISBN panel    
		isbnPanel.add(isbnLabel);
		isbnPanel.add(isbnField);
		isbnPanel.add(ISBNButton);
		isbnPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

		//title panel
		titlePanel.add(titleLabel);
		titlePanel.add(titleField);
		titlePanel.add(TitleButton);
		titlePanel.setLayout(new FlowLayout(FlowLayout.LEFT));

		//author panel
		authorPanel.add(authorLabel);
		//authorPanel.add(lastNameLabel);
		authorPanel.add(authorLastField);
		authorPanel.add(AuthorButton);
		authorPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

		//first name panel
		firstNamePanel.add(firstNameLabel);
		firstNamePanel.add(authorFirstField);
		firstNamePanel.setLayout(new FlowLayout(FlowLayout.LEFT));

		// show all panel
		showAllPanel.add(showAllLabel);
		showAllPanel.add(ShowAllButton);
		showAllPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

		// cart panel
		cartPanel.add(addToCartLabel);
		cartPanel.add(addToCartField);
		cartPanel.add(CartButton);
		cartPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

		// back to menu panel
		//menuPanel.add(menuLabel);
		menuPanel.add(MenuButton);

		// line panels  
		linePanel.add(lineLabel);
		linePanel1.add(lineLabel1);
		//linePanel2.add(lineLabel2);

		mainPanel.add(headPanel); 		// head
		mainPanel.add(linePanel1); 		// break
		mainPanel.add(isbnPanel); 		// ISBN 
		mainPanel.add(titlePanel); 		// title
		mainPanel.add(authorPanel);		//author	
		//mainPanel.add(lastNamePanel);	//last name
		mainPanel.add(firstNamePanel);	//first name

		mainPanel.add(showAllPanel);		//showing all books in the DB.

		//	mainPanel.add(linePanel2); 		// break

		JScrollPane aaa = new JScrollPane(searchResultField);
		aaa.setMaximumSize(new Dimension (550,80));

		mainPanel.add(aaa);

		//mainPanel.add(scrollPane);		// search results

		mainPanel.add(linePanel); 		// break				 
		mainPanel.add(cartPanel);		//cart
		//	mainPanel.add(linePanel); 		// break
		mainPanel.add(menuPanel);		// goes back to menu.
		//	mainPanel.add(linePanel); 		// break

		add(mainPanel);
	}
}