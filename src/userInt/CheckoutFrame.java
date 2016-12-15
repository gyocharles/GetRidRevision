package userInt;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import DBAccessClasses.TransactionDBAccess;
import ObjectClasses.Book;

/**
 * This class handles user check out. Accessed from the CartFrame and uses the Transaction class
 */

public class CheckoutFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int FRAME_HEIGHT = 450;
	private static final int FRAME_WIDTH = 450;
	private JTextArea cartField;
	
	private JPanel cartPanel;
	private JButton checkoutButton;
	private JButton menuButton;
	
	TransactionDBAccess t1= new TransactionDBAccess();//added
	
	
	/**
	 * This method calls the methods that creates the text area, buttons, and panel, which all make up the frame. 
	 */
	public CheckoutFrame() 
	{
		createTextArea();
		createButtons();
		createPanel();
		
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
	}

	private void createTextArea() 
	{
		cartField = new JTextArea(20, 30);
		cartField.setEditable(false);
		cartField.setText("");
	
	        for (Book books : Variables.cart) 
	        {
	            cartField.append(books.BooktoString());
	            cartField.append("\n");
	            }
	}
	
	private void createButtons()
	{
		checkoutButton = new JButton("Pay");
		menuButton = new JButton ("Back to Main Menu");
 	    
 	   ActionListener CheckoutAndPayListener = new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		// check if IsLoggedIn = true, then add up all prices of books in cart and move to paymentFrame
	    		//check if IsLoggedIn = false, then go to LogInFrame
	    		
	    		t1.addTransaction();//error caused here-must fix
	    		/*try {                                       
					t1.retrieveTransaction();          //The code that's commented out serves as a "receipt" for when a user checks out.
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}*/
	    		dispose();
	    		JOptionPane.showMessageDialog(null, "Thank you for your purchase. A confirmation email of the transaction will be sent out. Please give 7-10 days for processing and shipment.");
	    		JFrame frame = new MenuFrame();
 	 	   		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 	 	   		frame.setTitle("GetRid - Menu");
 	 	   		frame.setResizable(false);
 	 	   		frame.setVisible(true);
	    	}
	    };
	    //Return to main menu
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
	    	menuButton.addActionListener(MenuListener);
	   	    checkoutButton.addActionListener(CheckoutAndPayListener);
	}
	
	private void createPanel()
	{
		cartPanel = new JPanel();
		cartPanel.setLayout(new FlowLayout());
        
		JScrollPane scrollPane = new JScrollPane(cartField);
		cartPanel.add(scrollPane);
		
		cartPanel.add(checkoutButton);
		cartPanel.add(menuButton);
		
		add(cartPanel);
	}
}
