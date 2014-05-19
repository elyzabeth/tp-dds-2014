package tp.dds.entidades;

import java.util.Iterator;
import java.util.List;

import tp.dds.interfaces.MailSender;
import tp.dds.interfaces.Partido;
import tp.dds.interfaces.Persona;


public class PartidoDecorator implements Partido {

	private Partido partido;
	private MailSender mailSender;

	public PartidoDecorator(PartidoPosta partido, MailSender mailSender) {
		this.partido = partido;
		this.mailSender = mailSender;
	}

	public void inscribir(Inscripcion inscripcion) {
		Integer cantInscriptosAnterior = this.cantInscriptos();

		this.partido.inscribir(inscripcion);
		notificarInscripcion(inscripcion.jugador(), cantInscriptosAnterior );

	}

	public void bajaJugador(Jugador jugadorBaja, Jugador jugadorNuevo) {
		Integer cantInscriptosAnterior = this.cantInscriptos();

		// DAR de BAJA jugador
		this.partido.bajaJugador(jugadorBaja, jugadorNuevo);

		if (null == jugadorNuevo){
			notificarBajaJugador(cantInscriptosAnterior);
		} else {
			notificarInscripcion(jugadorNuevo, cantInscriptosAnterior);
		}
	}


	private void notificarBajaJugador(Integer cantInscriptosAnterior) {
		// Notificar al admin Baja de jugador cuando el partido ya estaba confirmado.
		if ( this.partido.maxJugadoresxPartido().equals(cantInscriptosAnterior)
				&& this.partido.maxJugadoresxPartido().compareTo(this.partido.cantInscriptos()) > 0 ) {

			Mail mail = MailAdapter.crearMail("sistema@ddsutn.com", this.partido.administrador().mail(), "Partido con menos de 10 jugadores", "El partido "+this.partido.fecha()+" dejo de tenre 10 jugadores");
			mailSender.sendMail(mail);
		}
	}

	private void notificarInscripcion(Jugador jugador, Integer cantInscriptosAnterior) {
		// notificar partido confirmado
		if (this.partido.cantInscriptos().equals(this.partido.maxJugadoresxPartido())
			&& !this.partido.cantInscriptos().equals(cantInscriptosAnterior))
			notificarPartidoConfirmado();

		if (null != jugador) {
			notificarAmigosJugador(jugador);
		}
	}

	private void notificarAmigosJugador(Jugador jugador) {
		// notificar a los amigos del jugador que se anoto al partido.
		Iterator<Persona> it = jugador.amigos().iterator();
		Persona amigo;
		while(it.hasNext()){
			amigo = (Persona) it.next();
			Mail mail = MailAdapter.crearMail("sistema@ddsutn.com", amigo.mail(), "Tu amigo se anoto al partido", "Tu amigo "+ jugador.nombre()+" se anoto al partido del ");
			mailSender.sendMail(mail);
		}
	}

	private void notificarPartidoConfirmado() {

		// notificar Partido Confirmado al admin.
		Mail mail = MailAdapter.crearMail("sistema@ddsutn.com", this.partido.administrador().mail(), "Partido Confirmado", "El partido de la fecha "+this.partido.fecha() +" tiene 10 jugadores");
		mailSender.sendMail(mail);

	}

	public MailSender mailSender() {
		return mailSender;
	}


	/// Metodos del partido posta 
	public void generarEquipos(){
		this.partido.generarEquipos();
	}

	public boolean contieneJugador(Inscripcion inscripcion) {
		return this.partido.contieneJugador(inscripcion);
	}
	
	public Integer cantJugadoresEstandar() {
		return this.partido.cantJugadoresEstandar();
	}
	
	public Integer cantInscriptos() {
		return this.partido.cantInscriptos();
	}

	public Integer maxJugadoresxPartido() {
		return this.partido.maxJugadoresxPartido();
	}

	public Persona administrador() {
		return this.partido.administrador();
	}

	public String fecha() {
		return this.partido.fecha();
	}

	public String lugar(){
		return this.lugar();
	}

	public List<Inscripcion> inscripciones() {
		return this.partido.inscripciones();
	}
}
