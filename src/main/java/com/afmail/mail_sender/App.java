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
	
	
	
	
		public static void main(String[] args) {
		Properties properties = new Properties();
		
		final MailSender mailSender = new MailSender("ale.fenandes23@gmail.com", "ratc elzt ddll ljwk", "alex.silva250@hotmail.com", "Alexandre", "Olá mundo"
				, "Sou eu, Mario!");

		try {
			properties.put("mail.smtp.ssl.trust", "*");
			properties.put("mail.smtp.auth", true);
			properties.put("mail.smtp.starttls", "true");
			properties.put("mail.smtp.host", "smtp.gmail.com");
			properties.put("mail.smtp.port", "465");
			properties.put("mail.smtp.socketFactory.port", "465");
			properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

			Session session = Session.getInstance(properties, new Authenticator() {
				@Override
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(mailSender.getEmail(), mailSender.getPassword());
				}
			});
			
			Address[] toUsers = InternetAddress.parse(mailSender.getReceiversList());
			
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(mailSender.getEmail(), mailSender.getSender()));
			message.setRecipients(Message.RecipientType.TO, toUsers);
			message.setSubject(mailSender.getEmailSubject());
			message.setText(mailSender.getEmailText());
			
			Transport.send(message);
			System.out.println("E-mail enviado com sucesso!");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
}
