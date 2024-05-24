package com.afmail.mail_sender;

import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class App {
	private static String email = "ale.fenandes23@gmail.com";
	private static String password = "ratc elzt ddll ljwk";

	public static void main(String[] args) {
		Properties properties = new Properties();

		try {
			properties.put("mail.smtp.auth", true);
			properties.put("mail.smtp.starttls", "true");
			properties.put("mail.smtp.host", "smtp.gmail.com");
			properties.put("mail.smtp.port", "465");
			properties.put("mail.smtp.socketFactory.port", "465");
			properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

			Session session = Session.getInstance(properties, new Authenticator() {
				@Override
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(email, password);
				}
			});
			
			Address[] toUsers = InternetAddress.parse("alex.silva250@hotmail.com");
			
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(email));
			message.setRecipients(Message.RecipientType.TO, toUsers);
			message.setSubject("Olá mundo!");
			message.setText("Olá mundo, é nóis na fita zirkonio");
			
			Transport.send(message);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
