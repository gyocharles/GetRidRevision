package userInt;

import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

import javax.swing.*;

import DBAccessClasses.DBConnection;


public class LogInFrame extends JFrame 
{
	private static final int FRAME_HEIGHT = 450;
	private static final int FRAME_WIDTH = 600;

	private JPanel loginPanel;
	private JLabel usernameLabel;
	private JTextField usernameField;
	
	private JLabel passwordLabel;
	private JPasswordField passwordField;
	
	private JButton loginButton;
	private JButton signupButton;
	
	public LogInFrame() 
	{
		createTextField();
		createButtons();
		createPanel();
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
	}
	
	private void createTextField()
	      {
			final int FIELD_WIDTH = 10;
	         usernameLabel = new JLabel("   Username: ");	   
	         usernameField = new JTextField(FIELD_WIDTH);
	         passwordLabel = new JLabel("   Password: ");	   
	         passwordField = new JPasswordField(FIELD_WIDTH);
	      }
	
	private void createButtons()
	{
		loginButton = new JButton("Log In");
		signupButton = new JButton("Sign Up");
		
		 ActionListener LoginListener = new ActionListener()
		 
		 {
			public void actionPerformed(ActionEvent ae) 
			{
				//checks if the button clicked
				
				if(ae.getSource()==loginButton)
				{
					char[] temp_pwd=passwordField.getPassword();
					String pwd=null;
					pwd=String.copyValueOf(temp_pwd);
					// System.out.println("Username,Pwd:"+usernameField.getText()+","+pwd);

					//The entered user name and password are sent via "checkLogin()" which return boolean
					try {
						if(DBConnection.checkLogin (usernameField.getText(), pwd))
						{
							//a pop-up box
						JOptionPane.showMessageDialog(null, "You have logged in successfully","Success",
						JOptionPane.INFORMATION_MESSAGE);
						//if no problems with login
						Variables.isLoggedIn = true;
						Variables.userName = usernameField.getText();
						dispose();
						
						JFrame frame = new MenuFrame();
						frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
						frame.setTitle("GetRid - Menu");
						frame.setResizable(false);
						frame.setVisible(true);
						}
						else
						{
							//a pop-up box
							JOptionPane.showMessageDialog(null, "Login failed!","Failed!!",
									JOptionPane.ERROR_MESSAGE);
						}
					} catch (HeadlessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}	
			}
		 
 	    };
 	    
		 
 	   ActionListener SignupListener = new ActionListener() 
 	   {
	    	public void actionPerformed(ActionEvent ae) 
	    	{
	    		dispose();
 	 	    	JFrame frame = new SignUpFrame();
 	 	   		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 	 	   		frame.setTitle("GetRid - Sign Up");
 	 	   		frame.setResizable(false);
 	 	   		frame.setVisible(true);
	    	
	    		//checks if the button clicked
				if(ae.getSource() == signupButton)
				{
					char[] temp_pwd=passwordField.getPassword();
					String pwd=null;
					pwd=String.copyValueOf(temp_pwd);
					System.out.println("Username,Pwd:"+usernameField.getText()+","+pwd);

					//The entered username and password are sent via "checkLogin()" which return boolean
					try {
						if(DBConnection.checkLogin(usernameField.getText(), pwd))
						{
							//a pop-up box
						JOptionPane.showMessageDialog(null, "You have signed up successfully","Success",
						JOptionPane.INFORMATION_MESSAGE);
						}
						else
						{
							JOptionPane.showMessageDialog(null, "Signup failed!","Failed!!",
									JOptionPane.ERROR_MESSAGE);
						}
					} catch (HeadlessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}	
	    	}
	    	 
 	    };
		 
 	    loginButton.addActionListener(LoginListener);
	 	signupButton.addActionListener(SignupListener);
		
		 }
	
 	   
	private void createPanel()
	{
	
		loginPanel = new JPanel();
		loginPanel.setLayout(new FlowLayout());
        
		ImageIcon image = new ImageIcon("loginLogo.png");
        JLabel imageLabel = new JLabel(image); 
		imageLabel.setBounds(5, 5, 5, 5);
		imageLabel.setVisible(true);
        loginPanel.add(imageLabel);
		
		JPanel middlePanel = new JPanel();
		middlePanel.setLayout(new GridLayout(2, 2));
        middlePanel.add(usernameLabel);
        middlePanel.add(usernameField);
        middlePanel.add(passwordLabel);
        middlePanel.add(passwordField);
		
		JPanel bottomPanel = new JPanel();
		bottomPanel.add(loginButton);
		bottomPanel.add(signupButton);		
	
		loginPanel.add(middlePanel);
		loginPanel.add(bottomPanel);
		add(loginPanel);
	}
}

