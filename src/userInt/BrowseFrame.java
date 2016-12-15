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
		isbnLabel 		= new JLabel("      ISBN:    ");
		titleLabel 		= new JLabel("      Title:   ");
		authorLabel 	= new JLabel("      Author, Last name:     ");
		firstNameLabel	= new JLabel("      First name (optional): ");       
		addToCartLabel  = new JLabel("      Type in ONE book's Entry number, then add to cart:  ");
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
			public void actionPerformed(ActionEvent e) 
			{

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



		ActionListener Title_Search_Listener = new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{

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

	
		ActionListener Author_Search_Listener = new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{

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

		
		ActionListener SearchAllListener = new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{

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
			 * This button when pressed, Adds a book matching the entry number to an ArrayList that functions as
			 * the cart
			 */
			ActionListener AddToCartListener = new ActionListener() 
			{
				public void actionPerformed(ActionEvent e) 
				{
				
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
			 * This button when pressed takes the user back to the menu
			 */

			ActionListener MenuListener = new ActionListener() 
			{
				public void actionPerformed(ActionEvent e) 
				{
					if(Variables.isLoggedIn) 
					{
						dispose();
						JFrame frame = new MenuFrame();
						frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
						frame.setTitle("GetRid - Menu");
						frame.setVisible(true);
					}
					else
					{
						dispose();
						JFrame frame = new LogInFrame();
						frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
						frame.setTitle("GetRid - Log In");
						frame.setVisible(true);
					}
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

			mainPanel = new JPanel();
			mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

			headPanel 		= 	new JPanel();
			isbnPanel 		= 	new JPanel();
			titlePanel 		= 	new JPanel();
			authorPanel 		= 	new JPanel();
			firstNamePanel 	=	new JPanel();
			showAllPanel 		= 	new JPanel();
			cartPanel 		= 	new JPanel();
			menuPanel 		= 	new JPanel();
			linePanel1 		= 	new JPanel();
			linePanel 		= 	new JPanel();

			scrollPane = new JScrollPane(searchResultField); 	
			scrollPane.add(searchResultField);
			scrollPane.setSize(20, 20);

			headPanel.add(headLabel);
			headPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

			isbnPanel.add(isbnLabel);
			isbnPanel.add(isbnField);
			isbnPanel.add(ISBNButton);
			isbnPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

			titlePanel.add(titleLabel);
			titlePanel.add(titleField);
			titlePanel.add(TitleButton);
			titlePanel.setLayout(new FlowLayout(FlowLayout.LEFT));

			authorPanel.add(authorLabel);
			authorPanel.add(authorLastField);
			authorPanel.add(AuthorButton);
			authorPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

			firstNamePanel.add(firstNameLabel);
			firstNamePanel.add(authorFirstField);
			firstNamePanel.setLayout(new FlowLayout(FlowLayout.LEFT));

			showAllPanel.add(showAllLabel);
			showAllPanel.add(ShowAllButton);
			showAllPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

			cartPanel.add(addToCartLabel);
			cartPanel.add(addToCartField);
			cartPanel.add(CartButton);
			cartPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

		
			menuPanel.add(MenuButton);
			linePanel.add(lineLabel);
			linePanel1.add(lineLabel1);
		

			mainPanel.add(headPanel); 		
			mainPanel.add(linePanel1); 		
			mainPanel.add(isbnPanel); 	
			mainPanel.add(titlePanel); 		
			mainPanel.add(authorPanel);		
			mainPanel.add(firstNamePanel);
			mainPanel.add(showAllPanel);		

			JScrollPane aaa = new JScrollPane(searchResultField);
			aaa.setMaximumSize(new Dimension (550,80));

			mainPanel.add(aaa);
			mainPanel.add(linePanel); 						 
			mainPanel.add(cartPanel);		
			mainPanel.add(menuPanel);		


			add(mainPanel);
		}
	}