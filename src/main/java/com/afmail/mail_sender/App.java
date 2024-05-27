package com.afmail.mail_sender;

public class App {
			
		public static void main(String[] args) {
		
		StringBuilder stb = new StringBuilder();
		stb.append("<h2>Sou eu, Mario!</h2>");
		stb.append("<br/>");
		stb.append("<a target=\"_blank\" href=\"https://www.myinstants.com/pt/instant/its-a-me-mario/\" style =\"color:red; text-align:center; text-decoration: none\">Clique aqui</a>");
		
		FileClass metas = new FileClass("C:\\Users\\User\\Desktop\\Metas.docx","Metas");
		FileClass proposito = new FileClass("C:\\Users\\User\\Desktop\\Propósito.docx", "propósitos");
		
		MailSender mail = new MailSender("ale.fenandes23@gmail.com", 
				"ratc elzt ddll ljwk", 
				"alex.silva250@hotmail.com", 
				"Alexandre", 
				"Olá mundo"
				,stb.toString());
		
		mail.getFileInfo().add(metas);
		mail.getFileInfo().add(proposito);
		
		mail.sendEmail();
		
		}
}
