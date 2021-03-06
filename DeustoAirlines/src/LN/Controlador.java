package LN;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import LN.Correo;

/**
 * Metodo creado para hacer posible el envio de los correos (no funciona)
 */
public class Controlador
{
	
	//Devolver� false si el correo no se ha enviado
	public boolean enviarCorreo(Correo c)
	{
		try
		{
			Properties p = new Properties();
			p.put("mail.smtp.host", "smtp.gmail.com");
			p.setProperty("mail.smtp.starttls.enable", "true");
			p.setProperty("mail.smtp.port", "587");
			p.setProperty("mail.smtp.user", c.getUsuarioCorreo());
			p.setProperty("mail.smtp.auth", "true");
			
			
			//Session s = Session.getDefaultInstance(p, null); --> lo que puso ianire
			
			Session s = Session.getDefaultInstance(p, new javax.mail.Authenticator() // lo que creo que hay que poner yo (maitane)
			{
			protected PasswordAuthentication getPasswordAuthentication() 
				{
			      return new PasswordAuthentication("usuarioCorreo", "contrasenya");}
			    });
			
			
			BodyPart texto = new MimeBodyPart();
			texto.setText(c.getMensaje());
			BodyPart adjunto = new MimeBodyPart();
			
			
			if(!c.getRutaArchivo().equals(""))
			{
				adjunto.setDataHandler(new DataHandler(new FileDataSource(c.getRutaArchivo())));
				adjunto.setFileName(c.getNombreArchivo());
			}
			MimeMultipart m = new MimeMultipart();
			m.addBodyPart(texto);
			
			if(!c.getRutaArchivo().equals(""))
			{
				m.addBodyPart(adjunto);
			}
			MimeMessage mensaje = new MimeMessage(s);
			mensaje.setFrom(new InternetAddress(c.getUsuarioCorreo()));
			mensaje.addRecipient(Message.RecipientType.TO, new InternetAddress(c.getDestino()));
			mensaje.setSubject(c.getAsunto());
			mensaje.setContent(m);
			
			Transport t = s.getTransport("smtp");
			t.connect(c.getUsuarioCorreo(), c.getContrasenya());
			t.sendMessage(mensaje, mensaje.getAllRecipients());
			t.close();
			
			return true;
		}catch(Exception e)
		{
			System.out.println("Error en el env�o del correo" + e);
			return false;
		}
		
	}
}
