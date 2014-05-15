package tp.dds.observer;

import java.security.acl.Permission;
import java.util.Iterator;

import tp.dds.entidades.Inscripcion;
import tp.dds.entidades.Mail;
import tp.dds.entidades.MailAdapter;
import tp.dds.entidades.MailSender;
import tp.dds.entidades.Partido;
import tp.dds.entidades.Persona;

public class InscripcionJugador extends InscripcionObserver {

	public InscripcionJugador(Partido partido, MailSender mailSender) {
		super(partido,mailSender);
	}

	public void notificarNuevaInscripcion(Inscripcion inscripcion) {
		// TODO notificar a los amigos del jugador que se anoto al partido.
		if (null != inscripcion) {
			Iterator<Persona> it = inscripcion.jugador().amigos().iterator();
			Persona amigo;
			while(it.hasNext()){
				amigo = (Persona) it.next();
				Mail mail = MailAdapter.crearMail("sistema@ddsutn.com", amigo.mail(), "Tu amigo se anoto al partido", "Tu amigo "+inscripcion.jugador().nombre()+" se anoto al partido del ");
				mailSender.sendMail(mail);
			}
		}
	}

}
