package com.afmail.mail_sender;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;

public class App {
	private static String email = "ale.fenandes23@gmail.com";
	private static String password = "46422278As";

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
			System.out.println(password);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
