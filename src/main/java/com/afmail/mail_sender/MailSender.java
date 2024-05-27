package com.afmail.mail_sender;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class MailSender {
	private String email;
	private String password;
	private String receiversList;
	private String sender;
	private String emailSubject;
	private String emailText;
	
	private List<FileClass> fileInfo = new ArrayList<>();

	public MailSender() {
	}

	public MailSender(String email, String password, String receiversList, String sender, String emailSubject,
			String emailText) {
		super();
		this.email = email;
		this.password = password;
		this.receiversList = receiversList;
		this.sender = sender;
		this.emailSubject = emailSubject;
		this.emailText = emailText;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getReceiversList() {
		return receiversList;
	}

	public void setReceiversList(String receiversList) {
		this.receiversList = receiversList;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getEmailSubject() {
		return emailSubject;
	}

	public void setEmailSubject(String emailSubject) {
		this.emailSubject = emailSubject;
	}

	public String getEmailText() {
		return emailText;
	}

	public void setEmailText(String emailText) {
		this.emailText = emailText;
	}
	
	public List<FileClass> getFileInfo() {
		return fileInfo;
	}

	public void setFileInfo(List<FileClass> fileInfo) {
		this.fileInfo = fileInfo;
	}

	@Override
	public int hashCode() {
		return Objects.hash(email, emailSubject, emailText, password, receiversList, sender);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MailSender other = (MailSender) obj;
		return Objects.equals(email, other.email) && Objects.equals(emailSubject, other.emailSubject)
				&& Objects.equals(emailText, other.emailText) && Objects.equals(password, other.password)
				&& Objects.equals(receiversList, other.receiversList) && Objects.equals(sender, other.sender);
	}

	public void sendEmail() {
		try {
			Properties properties = new Properties();

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
					return new PasswordAuthentication(email, password);
				}
			});

			Address[] toUsers = InternetAddress.parse(receiversList);
			
			Multipart multipart = new MimeMultipart();
			
			if(fileInfo.size()>0) {
				fileInfo
				.forEach(fi-> {
					try {
						multipart
								.addBodyPart(new AttachmentClass(fi.getFileName(), fi.getFilePath())
										.createAttachment());
					} catch (MessagingException | IOException e) {
						e.printStackTrace();
					}
				});
			}

			Message message = new MimeMessage(session);
			MimeBodyPart msg = new MimeBodyPart();
			message.setFrom(new InternetAddress(email, sender));
			message.setRecipients(Message.RecipientType.TO, toUsers);
			message.setSubject(emailSubject);
			msg.setContent(emailText, "text/html; charset=utf-8");

			multipart.addBodyPart(msg);
			

			message.setContent(multipart);

			Transport.send(message);
			System.out.println("E-mail enviado com sucesso!");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
