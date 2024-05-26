package com.afmail.mail_sender;

public class App {
			
		public static void main(String[] args) {
		
		StringBuilder stb = new StringBuilder();
		stb.append("<h2>Sou eu, Mario!</h2>");
		stb.append("<br/>");
		stb.append("<a target=\"_blank\" href=\"https://www.myinstants.com/pt/instant/its-a-me-mario/\" style =\"color:red; text-align:center; text-decoration: none\">Clique aqui</a>");
		
		
		MailSender mail = new MailSender("ale.fenandes23@gmail.com", 
				"ratc elzt ddll ljwk", 
				"alex.silva250@hotmail.com", 
				"Alexandre", 
				"Olá mundo"
				,stb.toString(),
				"C:\\Users\\User\\Documents\\PDFS\\Certificações\\Certificado_HTML.pdf",
				"This_pdf");
		
		mail.sendEmail();
		}
}
