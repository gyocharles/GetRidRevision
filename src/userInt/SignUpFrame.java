package userInt;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SignUpFrame extends JFrame {
	private static final int FRAME_HEIGHT = 450;
	private static final int FRAME_WIDTH = 800;

	private JPanel signupPanel;
	
	private JLabel emailLabel;
	private JTextField emailField;
	
	private JLabel user_idLabel;
	private JTextField user_idField;
	
	private JLabel firstNameLabel;
	private JTextField firstNameField;

	private JLabel lastNameLabel;
	private JTextField lastNameField;

	private JLabel cardNumberLabel;
	private JTextField cardNumberField;
	
	private JLabel expirationDateLabel;
	private JTextField expirationDateField;
	
	private JLabel cvcCodeLabel;
	private JTextField cvcCodeField;
	
	private JButton browseButton;
	private JButton createAccountButton;
	
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
	         user_idLabel = new JLabel("   Username: ");	   
	         user_idField = new JTextField(FIELD_WIDTH);
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
		    		
	    		dispose();
	    		JOptionPane.showMessageDialog(null, "Your Account has been created. Please log in to continue.");
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
		middlePanel.setLayout(new GridLayout(7, 2));
		
        middlePanel.add(emailLabel);
        middlePanel.add(emailField);
        
        middlePanel.add(user_idLabel);
        middlePanel.add(user_idField);
        
        middlePanel.add(firstNameLabel);
        middlePanel.add(firstNameField);
        
        middlePanel.add(lastNameLabel);
        middlePanel.add(lastNameField);
        
        middlePanel.add(cardNumberLabel);
        middlePanel.add(cardNumberField);
        
        middlePanel.add(expirationDateLabel);
        middlePanel.add(expirationDateField);
        
        middlePanel.add(cvcCodeLabel);
        middlePanel.add(cvcCodeField);
       
        
        
		JPanel bottomPanel = new JPanel();
		bottomPanel.add(browseButton);
		bottomPanel.add(createAccountButton);		
	
		signupPanel.add(middlePanel);
		signupPanel.add(bottomPanel);
		add(signupPanel);
	}
}
