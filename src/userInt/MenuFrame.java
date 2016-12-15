package userInt;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
/**
 * This is the main menu of the class that lets the user access any function of the program available to them
 * searching, viewing their cart, creating a post about a book or managing their account
 *
 */
public class MenuFrame extends JFrame {
	private static final int FRAME_HEIGHT = 350;
	private static final int FRAME_WIDTH = 7000;
	private JButton browseButton;
	private JButton ridButton;
	private JButton cartButton;
	private JButton accountManagementButton;

	private JPanel menuPanel;

	public MenuFrame() 
	{
		createButtons();
		createPanel();

		setSize(FRAME_WIDTH, FRAME_HEIGHT);
	}

	private void createButtons()
	{
		browseButton = new JButton("Browse");
		ridButton = new JButton("Sell a Book");
		cartButton = new JButton("Cart");
		accountManagementButton = new JButton("Edit Account Info");

		//Browse
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
		//Create Post
		ActionListener RidListener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				JFrame frame = new RidFrame();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setTitle("GetRid - Sell Book");
				frame.setResizable(false);
				frame.setVisible(true);
			}
		}; 
		//View cart
		ActionListener CartListener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				JFrame frame = new CartFrame();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setTitle("GetRid - Cart");
				frame.setResizable(false);
				frame.setVisible(true);
			}
		};
		//Manage Account
		ActionListener AccountManagementListener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				JFrame frame = new AccountManagementFrame();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setTitle("GetRid - Update Account");
				frame.setResizable(false);
				frame.setVisible(true);
			}
		};

		browseButton.addActionListener(BrowseListener);
		ridButton.addActionListener(RidListener);
		cartButton.addActionListener(CartListener);
		accountManagementButton.addActionListener(AccountManagementListener);
	}

	private void createPanel()
	{
		menuPanel = new JPanel();
		menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));
		menuPanel.add(browseButton);
		menuPanel.add(ridButton);
		menuPanel.add(cartButton);
		menuPanel.add(accountManagementButton);
		add(menuPanel);
	}
}
