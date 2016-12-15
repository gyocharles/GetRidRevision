package userInt;
/**
 * This class handles account management for the user.
 */
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import DBAccessClasses.UserDBAccess;

public class AccountManagementFrame extends JFrame {
	private static final int FRAME_HEIGHT = 800;
	private static final int FRAME_WIDTH = 500;

	private JPanel accountManagementPanel;

	private JLabel emailLabel;
	private JTextField emailField;

	private JLabel usernameLabel;
	private JTextField usernameField;

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

	private JButton menuButton;
	private JButton updateAccountButton;

	private JLabel addressLabel;
	private JTextField addressField;

	
	UserDBAccess userdba= new UserDBAccess();

	public AccountManagementFrame() 
	{
		createTextField1();
		createButtons();
		createPanel();
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
	}

	private void createTextField1()
	{
		final int FIELD_WIDTH = 20;
		emailLabel = new JLabel("   Email: ");       
		emailField = new JTextField(FIELD_WIDTH);
		usernameLabel = new JLabel("   Username: ");       
		usernameField = new JTextField(FIELD_WIDTH);
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
		addressLabel=new JLabel("   Address: ");
		addressField=new JTextField(FIELD_WIDTH);
	}

	private void createButtons()
	{
		menuButton = new JButton("Main Menu");
		updateAccountButton = new JButton("Update Account");

		ActionListener MenuListener = new ActionListener()
		{
			public void actionPerformed(ActionEvent ae) 
			{
				dispose();
				JFrame frame = new MenuFrame();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setTitle("GetRid - Main Menu");
				frame.setResizable(false);
				frame.setVisible(true);
			}
		};

		ActionListener UpdateAccountListener = new ActionListener() 
		{
			public void actionPerformed(ActionEvent ae) 
			{
				String user=usernameField.getText();    
				String email= emailField.getText();
				String first=firstNameField.getText();
				String last=lastNameField.getText();
				String card=cardNumberField.getText();
				String cvc=cvcCodeField.getText();
				String expire= expirationDateField.getText();
				String address=addressField.getText();

				try {
					userdba.updateInfo(first, last, address, address, card, email, cvc);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				dispose();
				JOptionPane.showMessageDialog(null, "Your Account has been updated.");
				JFrame frame = new MenuFrame();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setTitle("GetRid - Main Menu");
				frame.setResizable(false);
				frame.setVisible(true);
			}

		};

		menuButton.addActionListener(MenuListener);
		updateAccountButton.addActionListener(UpdateAccountListener);

	}

	private void createPanel()
	{
		accountManagementPanel = new JPanel();
		accountManagementPanel.setLayout(new FlowLayout());

		JPanel middlePanel = new JPanel();
		middlePanel.setLayout(new GridLayout(7, 2));
		middlePanel.add(emailLabel);
		middlePanel.add(emailField);

		middlePanel.add(usernameLabel);
		middlePanel.add(usernameField);

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
		bottomPanel.add(menuButton);
		bottomPanel.add(updateAccountButton); 

		accountManagementPanel.add(middlePanel);
		accountManagementPanel.add(bottomPanel);
		add(accountManagementPanel);
	}
}



