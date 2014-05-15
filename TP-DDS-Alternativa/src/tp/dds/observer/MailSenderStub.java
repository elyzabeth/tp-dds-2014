package tp.dds.observer;

import java.util.ArrayList;
import java.util.List;

import tp.dds.entidades.Mail;
import tp.dds.entidades.MailSender;

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
