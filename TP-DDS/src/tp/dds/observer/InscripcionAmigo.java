package tp.dds.observer;

import java.util.Iterator;

import tp.dds.dominio.Inscripcion;
import tp.dds.dominio.Partido;
import tp.dds.interfaces.MailSender;
import tp.dds.interfaces.Persona;

public class InscripcionAmigo extends InscripcionObserver {

	public InscripcionAmigo(Partido partido, MailSender mailSender) {
		super(partido,mailSender);
	}

	public void notificarNuevaInscripcion(Inscripcion inscripcion) {
		// notificar a los amigos del jugador que se anoto al partido.
		if (null != inscripcion) {
			Iterator<Persona> it = inscripcion.jugador().amigos().iterator();
			Persona amigo;
			while(it.hasNext()){
				amigo = (Persona) it.next();
				enviarMail("sistema@ddsutn.com", amigo.mail(), "Tu amigo se anoto al partido", "Tu amigo "+inscripcion.jugador().nombre()+" se anoto al partido de la fecha " + partido.fecha());
			}
		}
	}

}
