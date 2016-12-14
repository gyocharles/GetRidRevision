package userInt;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JFrame;
import javax.swing.JTextArea;

public class CartFrame extends JFrame {
	private static final int FRAME_HEIGHT = 450;
	private static final int FRAME_WIDTH = 450;
	private JTextArea cartField;
	private JPanel cartPanel;
	private JButton addAnotherBookButton;
	private JButton checkoutButton;
	private JButton menu;
	public CartFrame() 
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
		//cartField.setText(cart.get(0)); //for Alexanders part 
	       
	}
	
	private void createButtons()
	{
		addAnotherBookButton = new JButton("Add Another Book");
		checkoutButton = new JButton("Checkout and Pay");
		
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