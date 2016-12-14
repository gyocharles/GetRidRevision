package userInt;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class WelcomeFrame extends JFrame {
	
	private static final int FRAME_HEIGHT = 450;
	private static final int FRAME_WIDTH = 450;
	private JTextArea welcomeField;
	private JPanel welcomePanel;
	private String welcomeText;
	private JButton browseButton;
	private JButton logInButton;
	
	public WelcomeFrame() 
	{
		createTextArea();
		createButtons();
		createPanel();
		
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
	}
	
	private void createPanel()
	{
		welcomePanel = new JPanel();
		welcomePanel.setLayout(new FlowLayout());
		//ImageIcon image = new ImageIcon("getRidLogo.png");
        //JLabel imageLabel = new JLabel(image); 
		//imageLabel.setBounds(5, 5, 5, 5);
		//imageLabel.setVisible(true);
        //welcomePanel.add(imageLabel);
        
		welcomePanel.add(welcomeField);
		welcomePanel.add(browseButton);
		welcomePanel.add(logInButton);
		
		add(welcomePanel);
	}
	
	private void createTextArea() 
	{
		welcomeText = "Welcome to GetRid, a simple program that helps college students "
				+ "\nto buy and sell textbooks! Start by browsing as a guest or logging in as a user.";
		welcomeField = new JTextArea(5, 10);
		welcomeField.setText(welcomeText);
		welcomeField.setEditable(false);
	}
	
	
	private void createButtons()
	{
		browseButton = new JButton("Browse");
		logInButton = new JButton("Log In");
		
		
		 ActionListener BrowseListener = new ActionListener() {
 	    	public void actionPerformed(ActionEvent e) {
 	    	dispose();
 	    	JFrame frame = new BrowseFrame();
 	   		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 	   		frame.setTitle("GetRid - Search");
 	   		frame.setResizable(false);
 	   		frame.setVisible(true);
 	   		
 	    	}
 	    };
 	    
 	   ActionListener LogInListener = new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	 	    	dispose();
	 	    	JFrame frame = new LogInFrame();
	 	   		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	 	   		frame.setTitle("GetRid - Log In");
	 	   		frame.setResizable(false);
	 	   		frame.setVisible(true);
	    	}
	    };

		browseButton.addActionListener(BrowseListener);
		logInButton.addActionListener(LogInListener);	
	}
}
