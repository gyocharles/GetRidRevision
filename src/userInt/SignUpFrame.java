package userInt;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import DBAccessClasses.UserDBAccess;

public class SignUpFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private static final int FRAME_HEIGHT = 800;
	private static final int FRAME_WIDTH = 500;

	private JPanel signupPanel;
	
	private JLabel firstNameLabel;
	private JTextField firstNameField;

	private JLabel lastNameLabel;
	private JTextField lastNameField;
	
	private JLabel NameOnCardLabel;
	private JTextField NameOnCardField;
	
	private JLabel addressLabel;
	private JTextField addressField;
	
	private JLabel emailLabel;
	private JTextField emailField;
	
	private JLabel billingAddressLabel;
	private JTextField billingAddressField;
	
	private JLabel cardNumberLabel;
	private JTextField cardNumberField;
	
	private JLabel securityQuestLabel;
	private JTextField securityQuestField;

	private JLabel securityQuestAnsLabel;
	private JTextField securityQuestAnsField;

	private JLabel expirationDateLabel;
	private JTextField expirationDateField;
	
	private JLabel cvcCodeLabel;
	private JTextField cvcCodeField;
	
	private JButton browseButton;
	private JButton createAccountButton;
	
	private JLabel usernameLabel;
	private JTextField usernameField;
	
	private JLabel passwordLabel;
	private JTextField passwordField;
	
	UserDBAccess userdba= new UserDBAccess();
	
	public SignUpFrame() 
	{
		createTextField();
		createButtons();
		createPanel();
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
	}
	
	private void createTextField()
	      {
			final int FIELD_WIDTH = 20;
			
	         emailLabel = new JLabel("   Email: ");	   
	         emailField = new JTextField(FIELD_WIDTH);
	         
	         firstNameLabel = new JLabel("   First Name: ");	   
	         firstNameField = new JTextField(FIELD_WIDTH);
	         
	         lastNameLabel = new JLabel("   Last Name: ");	   
	         lastNameField = new JTextField(FIELD_WIDTH);
	         
	         cardNumberLabel = new JLabel("   Card Number: ");	   
	         cardNumberField = new JTextField(FIELD_WIDTH);
	         
	         expirationDateLabel = new JLabel("   Expiration Date: ");	   
	         expirationDateField = new JTextField(FIELD_WIDTH);
	         
	         cvcCodeLabel = new JLabel("   CVC Security Code: ");	   
	         cvcCodeField = new JTextField(FIELD_WIDTH);
	       
	         addressLabel = new JLabel("   Address: ");	   
	         addressField = new JTextField(FIELD_WIDTH);
	         
	         billingAddressLabel = new JLabel("  Billing Address: ");	   
	         billingAddressField = new JTextField(FIELD_WIDTH);
	         
	         NameOnCardLabel = new JLabel ("	Name on Card: ");
	         NameOnCardField = new JTextField (FIELD_WIDTH);
	         
	         usernameLabel	 = new JLabel (" 	Username: ");
	         usernameField	 = new JTextField (FIELD_WIDTH);
	     	
	         passwordLabel = new  JLabel ("		 Password: ");
	         passwordField = new  JTextField (FIELD_WIDTH);
	         
	         securityQuestLabel = new  JLabel (" Security question:");
	         securityQuestField = new  JTextField (FIELD_WIDTH);

	         securityQuestAnsLabel =  new  JLabel (" Security question answer");
	         securityQuestAnsField =  new JTextField (FIELD_WIDTH);

	    
	        }
	
	private void createButtons()
	{
		browseButton = new JButton("Back to Browsing");
		createAccountButton = new JButton("Create Account");
		
		 ActionListener BrowseListener = new ActionListener()
		 {
			public void actionPerformed(ActionEvent ae) 
			{
				dispose();
 	 	    	JFrame frame = new BrowseFrame();
 	 	   		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 	 	   		frame.setTitle("GetRid - Search");
 	 	   		frame.setResizable(false);
 	 	   		frame.setVisible(true);
			}
 	    };
		 
 	   ActionListener CreateAccountListener = new ActionListener() 
 	   {
	    	public void actionPerformed(ActionEvent ae) 
	    	{
	    		//TODO send to backend User table the info in the textfields
	    		
	    		try {
					userdba.addInfo 
					
					( 		firstNameField.getText(),  
							lastNameField.getText(), 
							emailField.getText(), 
							addressField.getText(), 
							usernameField.getText(),
							passwordField.getText(),
							cardNumberField.getText(),
							expirationDateField.getText(),
							cvcCodeField.getText(),
							NameOnCardField.getText(),	
							billingAddressField.getText(), 	
							securityQuestField.getText(),
							securityQuestAnsField.getText()		) ;
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    		
	    		dispose();
	    	//	JOptionPane.showMessageDialog(null, "Your Account has been created. Please log in to continue.");
	    		JFrame frame = new LogInFrame();
 	 	   		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 	 	   		frame.setTitle("GetRid - Log In");
 	 	   		frame.setResizable(false);
 	 	   		frame.setVisible(true);
	    	}
	    	 
 	    };
		 
 	    browseButton.addActionListener(BrowseListener);
	 	createAccountButton.addActionListener(CreateAccountListener);
		
	}
	
 	   
	private void createPanel()
	{
	
		signupPanel = new JPanel();
		signupPanel.setLayout(new FlowLayout());
        
		
		JPanel middlePanel = new JPanel();
		middlePanel.setLayout(new BoxLayout(middlePanel, BoxLayout.Y_AXIS));
	//	middlePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		
        middlePanel.add(firstNameLabel);
        middlePanel.add(firstNameField);
  
        middlePanel.add(lastNameLabel);
        middlePanel.add(lastNameField);  
        
        middlePanel.add(emailLabel);
        middlePanel.add(emailField);
        
        middlePanel.add(addressLabel);
        middlePanel.add(addressField);
        
        middlePanel.add(usernameLabel);
        middlePanel.add(usernameField);
        
        middlePanel.add(passwordLabel);
        middlePanel.add(passwordField);
       
        middlePanel.add(cardNumberLabel);
        middlePanel.add(cardNumberField);
        
        middlePanel.add(expirationDateLabel);
        middlePanel.add(expirationDateField);
        
        middlePanel.add(cvcCodeLabel);
        middlePanel.add(cvcCodeField);
        
        middlePanel.add(NameOnCardLabel);
        middlePanel.add(NameOnCardField);
        
        middlePanel.add( billingAddressLabel);
        middlePanel.add( billingAddressField);
 
        
        middlePanel.add(securityQuestLabel);
        middlePanel.add(securityQuestField);
               
        middlePanel.add(securityQuestAnsLabel);
        middlePanel.add(securityQuestAnsField);
   
        
		JPanel bottomPanel = new JPanel();
		bottomPanel.add(browseButton);
		bottomPanel.add(createAccountButton);		
	
		signupPanel.add(middlePanel);
		signupPanel.add(bottomPanel);
		add(signupPanel);
	}
}
