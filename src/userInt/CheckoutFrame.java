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

/**
 * this class contains the user interface which will give the user the option of checking out
 * more specifically this class shows what will happen when a user clicks on the checkout button
 * @author 
 *
 */

public class CheckoutFrame extends JFrame {
	private static final int FRAME_HEIGHT = 450;
	private static final int FRAME_WIDTH = 450;
	private JTextArea cartField;
	private JPanel cartPanel;
	private JButton checkoutButton;
	
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
		//searchResultField.setText();
		cartField.setEditable(false);
	}
	
	private void createButtons()
	{
		checkoutButton = new JButton("Pay");
 	    
 	   ActionListener CheckoutAndPayListener = new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		// check if IsLoggedIn = true, then add up all prices of books in cart and move to paymentFrame
	    		//check if IsLoggedIn = false, then go to LogInFrame
	    		
	    	
	    		
	    		t1.addTransaction();
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
	   	    checkoutButton.addActionListener(CheckoutAndPayListener);
	}
	
	private void createPanel()
	{
		cartPanel = new JPanel();
		cartPanel.setLayout(new FlowLayout());
        
		JScrollPane scrollPane = new JScrollPane(cartField);
		cartPanel.add(scrollPane);
		
		cartPanel.add(checkoutButton);
		
		add(cartPanel);
	}
}
