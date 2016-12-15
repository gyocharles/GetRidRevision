package userInt;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

import DBAccessClasses.BookDBAccess;
import ObjectClasses.Book;

public class CartFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int FRAME_HEIGHT = 450;
	private static final int FRAME_WIDTH = 450;
	private JTextArea cartField;
	private JPanel cartPanel;
	private JButton addAnotherBookButton;
	private JButton checkoutButton;
	private JButton menu;
	
	//copy arraylist in a method then display the copy
	//add arraylist here and have method heree to take book param and add to it
	
	BookDBAccess bookdba = new BookDBAccess();//create object to get access 
	
	// Variables.cart = bookdba.getListofBooks();//create and fill arraylist
	
	public CartFrame() 
	{
		createTextArea();
		createButtons();
		createPanel();
		
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
	}
    
	/*private void createTextArea() 
	{
		cartField = new JTextArea(20, 30);
		//searchResultField.setText();
		cartField.setEditable(false);
		//cartField.setText(cart.get(0)); //for Alexanders part 
		
		for (Book books : cart) {//goes through arraylist to prints it to the textarea
		    cartField.append(books.toString());
		    cartField.append("\n");
		}  
	}
	*/

private void createTextArea() 
    {
        cartField = new JTextArea(20, 30);
        //searchResultField.setText();
        cartField.setEditable(false);
        cartField.setText("");
        double sum=0.0;
        //cartField.setText(cart.get(0)); //for Alexanders part 
        for (Book books : Variables.cart) 
        {
             sum+= books.getPrice();
        }
        
          String numberAsStriing= String.valueOf(sum);//sum2 Double.toString(double);
        for (Book books : Variables.cart) {//goes through arraylist to prints it to the textarea
            cartField.append(books.BooktoString());
            cartField.append("\n");
           cartField.append("TOTAL:\t"+numberAsStriing);
        }  
    }

//	BookDBAccess bookdba = new BookDBAccess();//create object to get access 
//    ArrayList<Book> cart = bookdba.getListofBooks();//create and fill arraylist
	
	
	
	
	private void createButtons()
	{
		addAnotherBookButton = new JButton("Add Another Book");
		checkoutButton = new JButton("Checkout and Pay");
		menu = new JButton ("Back to Main Menu");
		
		 ActionListener AddAnotherBookListener = new ActionListener() {
 	    	public void actionPerformed(ActionEvent e) {
 	 	    	dispose();
 	 	    	JFrame frame = new BrowseFrame();
 	 	   		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 	 	   		frame.setTitle("GetRid - Search");
 	 	   		frame.setResizable(false);
 	 	   		frame.setVisible(true);
 	    	}

			
 	    };
 	    
 	   ActionListener CheckoutAndPayListener = new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		// check if IsLoggedIn = true, then add up all prices of books in cart and move to CheckoutFrame
	    		if(Variables.isLoggedIn) 
	    		{
	    			 dispose();
	    			JFrame frame = new CheckoutFrame();
 	 	   			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 	 	   			frame.setTitle("GetRid - Checkout");
 	 	   			frame.setResizable(false);
 	 	   			frame.setVisible(true);
	    		}
	    		else
	    		{
 	 	   		//check if IsLoggedIn = false, then go to LogInFrame
	     	    dispose();
	 	    	JFrame frame = new LogInFrame();
	 	   		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	 	   		frame.setTitle("GetRid - Log In");
	 	   		frame.setResizable(false);
	 	   		frame.setVisible(true);
	    		}
	    	}
	    };
	    
	    ActionListener MenuListener = new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	 	    	dispose();
	 	    	JFrame frame = new MenuFrame();
	 	   		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	 	   		frame.setTitle("GetRid - Menu");
	 	   		frame.setResizable(false);
	 	   		frame.setVisible(true);
	    	}
	    };
	    
	    menu.addActionListener(MenuListener);
	    addAnotherBookButton.addActionListener(AddAnotherBookListener);
	    checkoutButton.addActionListener(CheckoutAndPayListener);
	}
	
	private void createPanel()
	{
		cartPanel = new JPanel();
		cartPanel.setLayout(new FlowLayout());
        
		JScrollPane scrollPane = new JScrollPane(cartField);
		cartPanel.add(scrollPane);
		
		cartPanel.add(addAnotherBookButton);
		cartPanel.add(checkoutButton);
		cartPanel.add(menu, new FlowLayout().LEFT);
		
		add(cartPanel);
	}
}