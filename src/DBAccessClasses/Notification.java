package DBAccessClasses;

import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Notification {
 
	static Properties mailServerProperties;
	static Session getMailSession;
	static MimeMessage generateMailMessage;
	private static String userE;
	private static String cartI;
	
	
	public static void generateAndSendEmail(String userEmail, String cartInfo) throws AddressException, MessagingException { //pass through the user's email that he/she types in
 

		System.out.println("\n 1st ===> setup Mail Server Properties..");
		mailServerProperties = System.getProperties();
		mailServerProperties.put("mail.smtp.port", "587");
		mailServerProperties.put("mail.smtp.auth", "true");
		mailServerProperties.put("mail.smtp.starttls.enable", "true");
		System.out.println("Mail Server Properties have been setup successfully..");
 
		 userE = userEmail;
		 cartI = cartInfo;
				
		System.out.println("\n\n 2nd ===> get Mail Session..");
		getMailSession = Session.getDefaultInstance(mailServerProperties, null);
		generateMailMessage = new MimeMessage(getMailSession);
		generateMailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(userE));
		generateMailMessage.setSubject("Payment Confirmation");
		String emailBody = "This email is to confirm your transaction for the book(s)..." + "\n" + cartI + "\n" + "Thank you for using GetRid; we will update you once the book(s) has been shipped to you!" + "<br><br> Regards, <br>GetRid Admin";
		generateMailMessage.setContent(emailBody, "text/html");
		System.out.println("Mail Session has been created successfully..");
 
		System.out.println("\n\n 3rd ===> Get Session and Send mail");
		Transport transport = getMailSession.getTransport("smtp");
 
	
		transport.connect("smtp.gmail.com", "getridinc@gmail.com", "GetRid16");
		transport.sendMessage(generateMailMessage, generateMailMessage.getAllRecipients());
		transport.close();
	}
}