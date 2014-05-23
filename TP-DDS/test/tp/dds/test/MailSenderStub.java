package tp.dds.test;

import java.util.ArrayList;
import java.util.List;

import tp.dds.dominio.Mail;
import tp.dds.interfaces.MailSender;

public class MailSenderStub implements MailSender {
	private List<Mail> sentMails;
	
	public MailSenderStub(){
		this.sentMails = new ArrayList<Mail>();
	}

	public void sendMail(Mail mail) {
		this.sentMails.add(mail);
	}
	
	public List<Mail> listaMails(){
		return this.sentMails;
	}

}
