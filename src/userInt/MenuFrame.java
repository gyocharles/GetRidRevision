package userInt;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JFrame;

public class MenuFrame extends JFrame {
	private static final int FRAME_HEIGHT = 450;
	private static final int FRAME_WIDTH = 450;
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
		ridButton = new JButton("Rid");
		cartButton = new JButton("Cart");
		accountManagementButton = new JButton("Edit Account Info");

		
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
		menuPanel.setLayout(new GridLayout(4, 1));
		
		menuPanel.add(browseButton);
		menuPanel.add(ridButton);
		menuPanel.add(cartButton);
		menuPanel.add(accountManagementButton);

		add(menuPanel);
	}
}
