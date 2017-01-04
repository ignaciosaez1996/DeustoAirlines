package LN;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;



public class MailTest {
	
	
	public static void send(String to,String sub, String msg, String user, String pass )
	{
		Properties prop = new Properties();
		
		prop.put("mail.smtp.host", "smtp.gmail.com");
		prop.put("mail.smtp.port", "587");
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.starttls.enable", "true");
		
		
		Session session = Session.getDefaultInstance(prop, new Authenticator()
		{
			protected PasswordAuthentication getPasswordAuthentication()
			{
				return new PasswordAuthentication(user,pass);
			}
		});
		
		try
		
		{
			Message message = new MimeMessage(session);
			
			message.setFrom(new InternetAddress(user));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
			message.setSubject(sub);
			message.setText(msg);
			
			Transport.send(message);
			
		}
		
		catch(MessagingException e)
		{
			throw new RuntimeException(e);
			
			
		}
		
		
		
	}
}