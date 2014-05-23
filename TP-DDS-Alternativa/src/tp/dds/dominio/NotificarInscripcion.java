package tp.dds.dominio;

import java.util.Iterator;

import tp.dds.interfaces.MailSender;
import tp.dds.interfaces.Partido;
import tp.dds.interfaces.Persona;


public class NotificarInscripcion extends PartidoDecorator {


	public NotificarInscripcion(Partido partido, MailSender mailSender) {
		super(partido, mailSender);
	}

	public void inscribir(Inscripcion inscripcion) {
		Integer cantInscriptosAnterior = this.cantInscriptos();

		partido.inscribir(inscripcion);
		notificarInscripcion(inscripcion.jugador(), cantInscriptosAnterior );

	}

	public void bajaJugador(Jugador jugadorBaja, Jugador jugadorNuevo) {
		Integer cantInscriptosAnterior = this.cantInscriptos();

		// DAR de BAJA jugador
		partido.bajaJugador(jugadorBaja, jugadorNuevo);

		if (null == jugadorNuevo){
			notificarBajaJugador(cantInscriptosAnterior);
		} else {
			notificarInscripcion(jugadorNuevo, cantInscriptosAnterior);
		}
	}


	private void notificarBajaJugador(Integer cantInscriptosAnterior) {
		// Valido si hay que Notificar al admin Baja de jugador si el partido ya estaba confirmado.
		if ( this.partido.maxJugadoresxPartido().equals(cantInscriptosAnterior)
				&& this.partido.maxJugadoresxPartido().compareTo(this.partido.cantInscriptos()) > 0 ) {

			enviarMail("sistema@ddsutn.com", this.partido.administrador().mail(), "Partido con menos de 10 jugadores", "El partido "+this.partido.fecha()+" dejo de tenre 10 jugadores");
		}
	}



	private void notificarInscripcion(Jugador jugador, Integer cantInscriptosAnterior) {
		// Valido si hay que notificar partido confirmado
		if (this.partido.cantInscriptos().equals(this.partido.maxJugadoresxPartido())
			&& !this.partido.cantInscriptos().equals(cantInscriptosAnterior))
			notificarPartidoConfirmado();

		if (null != jugador) {
			notificarAmigosJugador(jugador);
		}
	}

	/**
	 * Notificar a los amigos del jugador que se inscribe, 
	 * que se anoto a un partido. 
	 * @param jugador 
	 */
	private void notificarAmigosJugador(Jugador jugador) {
		Iterator<Persona> it = jugador.amigos().iterator();
		Persona amigo;
		while(it.hasNext()){
			amigo = (Persona) it.next();
			enviarMail("sistema@ddsutn.com", amigo.mail(), "Tu amigo se anoto al partido", "Tu amigo "+ jugador.nombre()+" se anoto al partido del ");
		}
	}

	/**
	 * Notificar por mail al administrador
	 * que el partido tiene los 10 jugadores necesarios.
	 */
	private void notificarPartidoConfirmado() {
		enviarMail("sistema@ddsutn.com", this.partido.administrador().mail(), "Partido Confirmado", "El partido de la fecha "+this.partido.fecha() +" tiene 10 jugadores");
	}

	private void enviarMail(String remitente, String receptor, String asunto, String mensaje) {
		mailSender.sendMail(MailAdapter.crearMail(remitente, receptor, asunto, mensaje));
	}
}
