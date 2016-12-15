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
 * This Class handles the browsing part of the user interface. adding a book to a cart and searching for a particular book
 * or the entire database to see what's in it. There should be options to search by author, title or ISBN
 * 
 *
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

	//ArrayList<Book> cart= new ArrayList(); 

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

				String search;

				try {
					search = isbnField.getText();
					String searchResult = bookdba.getBookByISBN(search);

					// showing search results as a list of book records
					searchResultField.setText(searchResult);

				} 
				catch (ClassNotFoundException e1) 
				{ e1.printStackTrace(); } 
				catch (SQLException e1) 
				{ e1.printStackTrace();	}
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

				String search;

				try {

					search = titleField.getText();

					String searchResult = bookdba.getBookByTitle(search);

					// showing search results as a list of book records

					searchResultField.setText(searchResult);

				} 
				catch (ClassNotFoundException e1) 
				{ e1.printStackTrace(); } 
				catch (SQLException e1) 
				{ e1.printStackTrace();	}
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

				String search, search1;

				try {	    			

					search  = authorLastField.getText();
					search1 = authorFirstField.getText();

					String searchResult = bookdba.getBookByAuthor(search,search1);

					// showing search results as a list of book records

					searchResultField.setText(searchResult);
					scrollPane.repaint();

				} 
				catch (ClassNotFoundException e1) 
				{ e1.printStackTrace(); } 
				catch (SQLException e1) 
				{ e1.printStackTrace();	}
			}
		};

		/*
		 * This button, when pressed should return a list of every book in the database in a formatted string
		 * that includes its entry number, title, author etc.
		 */
		ActionListener SearchAllListener = new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{


				//back-end query results displayed for general search
				//call to method that will display the contents of ArrayList in formated string
				ArrayList<Book> bookResults = null;
				String results;
				String searchResult="";
				
				try {
					//bookResults = bookdba.searchAllBooks();
					searchResult = bookdba.searchAllBooks();
				} 

				catch (ClassNotFoundException e1) 
				{ e1.printStackTrace(); } 
				catch (SQLException e1) 
				{ e1.printStackTrace();	}

				//			for(int i=0; i<bookResults.size(); i++){//prints out the list of results in formatted string
				//				results=bookResults.get(i).BooktoString();//may add "\n"

				//	searchResultField.append(results);
				searchResultField.setText(searchResult);
				

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
					//sends book ISBN number to back end,  
					//it finds a book by the entry number in the database and adds to the cart

					String book_id =  (addToCartField.getText());			// added book ISBN
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
							
							Book book = bookdba.getBookByEntryNum(id);	// book's unique ID                    
							// searchResultField.setText(book_id);
							if (book!=null)		
							{
							Variables.cart.add(book);							// add to shopping cart 							
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

		

		private void createPanel()
		{

			//word1.setPreferredSize(new Dimension(#,#);

			// main panel

			mainPanel = new JPanel();
			mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

			// inside panels

			headPanel 		= 	new JPanel();
			isbnPanel 		= 	new JPanel();
			titlePanel 		= 	new JPanel();
			authorPanel 		= 	new JPanel();
			//	lastNamePanel 	=	new JPanel();
			firstNamePanel 	=	new JPanel();
			showAllPanel 		= 	new JPanel();
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

			// composing main panel


			/*

		browsePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		JScrollPane scrollPane = new JScrollPane(searchResultField);
		browsePanel.add(scrollPane);

			 */

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