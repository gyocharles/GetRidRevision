package DBAccessClasses;
/**
 * this class will handle notifying the seller and buyer via email after a transaction has been made.
 * there will be one method that sends an email to the seller and one that sends an email to the buyer
 * the methods should take the email of their intended recipient and the book being bought if to the seller
 * and a list of the order if to the buyer. 
 * @MiYoen
 */
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
 
 
public class Notification {
 
	static Properties mailServerProperties;
	static Session getMailSession;
	static MimeMessage generateMailMessage;
 
	public static void generateAndSendEmail(String userEmail) throws AddressException, MessagingException { //pass through the user's email that he/she types in
 
		// Step1
		System.out.println("\n 1st ===> setup Mail Server Properties..");
		mailServerProperties = System.getProperties();
		mailServerProperties.put("mail.smtp.port", "587");
		mailServerProperties.put("mail.smtp.auth", "true");
		mailServerProperties.put("mail.smtp.starttls.enable", "true");
		System.out.println("Mail Server Properties have been setup successfully..");
 
		// Step2
		String userE = userEmail;
		
		System.out.println("\n\n 2nd ===> get Mail Session..");
		getMailSession = Session.getDefaultInstance(mailServerProperties, null);
		generateMailMessage = new MimeMessage(getMailSession);
		generateMailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(userE));
		generateMailMessage.setSubject("Payment Confirmation");
		String emailBody = "This email is to confirm your transaction for the book(s)...Thank you for using GetRid; we will update you once the book(s) has been shipped to you!" + "<br><br> Regards, <br>GetRid Admin";
		generateMailMessage.setContent(emailBody, "text/html");
		System.out.println("Mail Session has been created successfully..");
 
		// Step3
		System.out.println("\n\n 3rd ===> Get Session and Send mail");
		Transport transport = getMailSession.getTransport("smtp");
 
		// Enter your correct gmail UserID and Password
		// if you have 2FA enabled then provide App Specific Password
		transport.connect("smtp.gmail.com", "getridinc@gmail.com", "GetRid16");
		transport.sendMessage(generateMailMessage, generateMailMessage.getAllRecipients());
		transport.close();
	}
}