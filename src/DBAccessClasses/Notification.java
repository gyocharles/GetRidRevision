package DBAccessClasses;
/**
 * this class will handle notifying the seller and buy via email after a transaction has been made
 * there will be one method that sends an email to the seller and one that sends an email to the buyer
 * the methods should take the email of their intended recipient and the book being bought if to the seller
 * and a list of the order if to the buyer. This is a draft with no functionality
 * @author gchar158
 *
 */
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import ObjectClasses.Book;

public class Notification {
final String from="GetRidofTextbooks@gmail.com"; 
final String pswrd="getridemailpassword";

//String host="localhost";//may have to fully declare this variable
Properties prop= new Properties();
//prop.put("mail.smtp.auth", "true");
//prop.put()



	public void sendToSeller(String seller, Book book){//pass the email/name of the seller and the book being sold
		

	      // Get the default Session object.
	      Session session = Session.getDefaultInstance(properties);

	      try {
	         // Create a default MimeMessage object.
	         MimeMessage message = new MimeMessage(session);

	         // Set From: header field of the header.
	         message.setFrom(new InternetAddress(from));

	         // Set To: header field of the header.
	         message.addRecipient(Message.RecipientType.TO, new InternetAddress(seller));

	         // Set Subject: header field
	         message.setSubject("Your Book Has Been Purchased!");

	         // Now set the actual message
	         message.setText("Your book has been purchased. Please mail the following book to GetRid by the end "
	         		+ "of the week to recieve payment\n"+book.toString()+" \nDO NOT REPLY TO THIS MESSAGE");
	         //may add an arbitrary address and a formatted shipping label-stored in variables

	         // Send message
	         Transport.send(message);
	      }catch (MessagingException mex) {
	         mex.printStackTrace();
	      }
	}
	
	public void sendToBuyer(String buyer){//in addition to the email/name of the buyer, may pass a formatted string detailing their purchase from transaction
		Properties properties= new Properties();
		properties.setProperty("mail.smtp.host", host);

	      // Get the default Session object.
	      Session session = Session.getDefaultInstance(properties);

	      try {
	         // Create a default MimeMessage object.
	         MimeMessage message = new MimeMessage(session);

	         // Set From: header field of the header.
	         message.setFrom(new InternetAddress(from));

	         // Set To: header field of the header.
	         message.addRecipient(Message.RecipientType.TO, new InternetAddress(buyer));

	         // Set Subject: header field
	         message.setSubject("Thank You for Your Purchase!");

	         // Now set the actual message
	         message.setText("Thank you using GetRid.\nThe seller(s) of your books have been notified."
	         		+ "\nPLease allow for two weeks for delivery \nDO NOT REPLY TO THIS MESSAGE");
	         //may add an arbitrary address and a formatted shipping label-stored in variables

	         // Send message
	         Transport.send(message);
	      }catch (MessagingException mex) {
	         mex.printStackTrace();
	      }
	}
}
