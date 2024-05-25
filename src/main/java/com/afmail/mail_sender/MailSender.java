package com.afmail.mail_sender;

import java.util.Objects;

public class MailSender {
	private String email;
	private String password;
	private String receiversList;
	private String sender;
	private String emailSubject;
	private String emailText;
	
	public MailSender() {}

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
	
	
	
}