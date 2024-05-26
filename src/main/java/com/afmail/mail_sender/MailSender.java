package com.afmail.mail_sender;

import java.util.Objects;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;

public class MailSender {
	private String email;
	private String password;
	private String receiversList;
	private String sender;
	private String emailSubject;
	private String emailText;
	private String filePath;
	private String attchmentName;

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

	public MailSender(String email, String password, String receiversList, String sender, String emailSubject,
			String emailText, String filePath, String attchmentName) {
		super();
		this.email = email;
		this.password = password;
		this.receiversList = receiversList;
		this.sender = sender;
		this.emailSubject = emailSubject;
		this.emailText = emailText;
		this.filePath = filePath;
		this.attchmentName = attchmentName;
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

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	

	public String getAttchmentName() {
		return attchmentName;
	}

	public void setAttchmentName(String attchmentName) {
		this.attchmentName = attchmentName;
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
			
			if(filePath!=null) {
				MimeBodyPart attachment = new MimeBodyPart();
				AttachmentClass att = new AttachmentClass(filePath, attchmentName);
				attachment.setDataHandler(new DataHandler(new ByteArrayDataSource(att.fileSender(), "application/"+att.getFileExtension())));
				attachment.setFileName(attchmentName);
				multipart.addBodyPart(attachment);
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
