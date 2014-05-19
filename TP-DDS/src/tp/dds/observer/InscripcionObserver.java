package tp.dds.observer;

import tp.dds.entidades.Inscripcion;
import tp.dds.entidades.Partido;
import tp.dds.interfaces.MailSender;

public abstract class InscripcionObserver {

	protected Partido partido;
	protected MailSender mailSender;
	
	public InscripcionObserver (Partido partido, MailSender mailSender){
		this.partido = partido;
		this.mailSender = mailSender;
	}

	public abstract void notificarNuevaInscripcion(Inscripcion inscripcion);

}
