package userInt;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.mail.MessagingException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import DBAccessClasses.Notification;
import DBAccessClasses.TransactionDBAccess;
import ObjectClasses.Book;

/**
 * this class contains the user interface which will give the user the option of checking out
 * more specifically this class shows what will happen when a user clicks on the checkout button
 * @author 
 *
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
		//searchResultField.setText();
		cartField.setEditable(false);

		cartField.setText("");

		//cartField.setText(cart.get(0)); 
		double sum=0.0;
        //cartField.setText(cart.get(0)); //for Alexanders part 
        for (Book books : Variables.cart) 
        {
             sum+= books.getPrice();
        }
        String numberAsStriing= String.valueOf(sum);
		for (Book books : Variables.cart) 
		{//goes through arraylist to prints it to the textarea
			cartField.append(books.BooktoString());
			cartField.append("\n");
	        cartField.append("TOTAL:\t"+numberAsStriing);

		}
	}

	private void createButtons()
	{
		checkoutButton = new JButton("Pay");
		menuButton = new JButton ("Back to Main Menu");

		ActionListener CheckoutAndPayListener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// check if IsLoggedIn = true, then add up all prices of books in cart and move to paymentFrame
			if(Variables.isLoggedIn)
			{

				double sum=0.0;
				String BookTitle;
				String AuthorLastname;
				String AuthorFirstname;
				String ISBN;
				double Price;
				String Condition;
				int EntryNumber;
				//int SellerAccNum;
				//String SellerName;

				//this loop will get all the info of each book and pass it to createTransaction
				for (Book books : Variables.cart) 
				{
					sum+=books.getPrice();//can go in seperate loop to just get the total
					BookTitle=books.getBookTitle();
					AuthorLastname=books.getAuthorLastname();
					AuthorFirstname=books.getAuthorFirstname();
					ISBN=books.getISBN();
					Price=books.getPrice();
					Condition=books.getCondition();
					EntryNumber=books.getEntryNumber();
				}




				Notification jv = new Notification();
				String userEmail = JOptionPane.showInputDialog(null, "Thank you for your purchase. Please input your email address for the comfirmation email to be sent out:");
				try {
					jv.generateAndSendEmail(userEmail, Variables.cartInfo);
				} catch (MessagingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}//ask for user email
				//t1.addTransaction();
				//some of the paramters must be removed or a new method must be made to just get the book info
				try {
					t1.createTransaction(Variables.transactionNum,  Variables.Seller_Name,  
							Variables.Seller_Acc_Num,  Variables.Buyer_Name,  Variables.Buyer_Acc_Num,
							Variables.Buyer_Address, 
							Variables.Transaction_Date,  Variables.Book_Title,  Variables.Author_Firstname, 
							Variables.Author_Lastname,  Variables.ISBN,  Variables.Price,  
							Variables.Credit_Card_Number,  Variables.Percent_Received, 
							Variables.total,  Variables.received,  Variables.shipped);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				//t1.retrieveTransaction();          //The code that's commented out serves as a "receipt" for when a user checks out.

				dispose();				
				JFrame frame = new MenuFrame();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setTitle("GetRid - Menu");
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


			ActionListener MenuListener = new ActionListener() 
			{
				public void actionPerformed(ActionEvent e) 
				{
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
