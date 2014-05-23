package tp.dds.observer;

import tp.dds.dominio.Inscripcion;
import tp.dds.dominio.MailAdapter;
import tp.dds.dominio.Partido;
import tp.dds.interfaces.MailSender;

public abstract class InscripcionObserver {

	protected Partido partido;
	protected MailSender mailSender;
	
	public InscripcionObserver (Partido partido, MailSender mailSender){
		this.partido = partido;
		this.mailSender = mailSender;
	}

	public abstract void notificarNuevaInscripcion(Inscripcion inscripcion);
	
	protected void enviarMail(String remitente, String receptor, String asunto, String mensaje) {
		mailSender.sendMail(MailAdapter.crearMail(remitente, receptor, asunto, mensaje));
	}

}
