package tp.dds.entidades;

import java.util.Iterator;
import java.util.List;


public class PartidoDecorator extends Partido {

	private Partido partidoDecorado;
	private Integer cantInscriptosAnterior;
	private MailSender mailSender;

	public PartidoDecorator(Partido partido, MailSender mailSender) {
		this.partidoDecorado = partido;
		this.mailSender = mailSender;
	}

	@Override
	public void inscribir(Inscripcion inscripcion) {
		actualizarCantInscriptosAnterior();
		this.partidoDecorado.inscribir(inscripcion);
		notificarInscripcion(inscripcion.jugador());
		actualizarCantInscriptosAnterior();
	}

	@Override
	public void bajaJugador(Jugador jugadorBaja, Jugador jugadorNuevo) {
		actualizarCantInscriptosAnterior();

		// DAR de BAJA jugador
		this.partidoDecorado.bajaJugador(jugadorBaja, jugadorNuevo);

		if (null == jugadorNuevo){
			notificarBajaJugador();
		} else {
			notificarInscripcion(jugadorNuevo);
		}
		actualizarCantInscriptosAnterior();
	}

	private void notificarBajaJugador() {
		// Notificar al admin Baja de jugador cuando el partido ya estaba confirmado.
		if ( this.partidoDecorado.maxJugadoresxPartido().equals(cantInscriptosAnterior)
				&& this.partidoDecorado.maxJugadoresxPartido().compareTo(this.partidoDecorado.cantInscriptos()) > 0 ) {
			Mail mail = MailAdapter.crearMail("sistema@ddsutn.com", this.partidoDecorado.administrador().mail(), "Partido con menos de 10 jugadores", "El partido "+this.partidoDecorado.fecha()+" dejo de tenre 10 jugadores");
			mailSender.sendMail(mail);
		}
	}

	private void notificarInscripcion(Jugador jugador) {
		// notificar partido confirmado
		if (this.partidoDecorado.cantInscriptos().equals(this.partidoDecorado.maxJugadoresxPartido()))
			notificarPartidoConfirmado();

		notificarAmigosJugador(jugador);
	}

	private void notificarAmigosJugador(Jugador jugador) {
		// notificar a los amigos del jugador que se anoto al partido.
		if (null != jugador) {
			Iterator<Persona> it = jugador.amigos().iterator();
			Persona amigo;
			while(it.hasNext()){
				amigo = (Persona) it.next();
				Mail mail = MailAdapter.crearMail("sistema@ddsutn.com", amigo.mail(), "Tu amigo se anoto al partido", "Tu amigo "+ jugador.nombre()+" se anoto al partido del ");
				mailSender.sendMail(mail);
			}
		}
	}

	private void notificarPartidoConfirmado() {

		if ( !this.partidoDecorado.cantInscriptos().equals(cantInscriptosAnterior)) {

			// notificar Partido Confirmado al admin.
			Mail mail = MailAdapter.crearMail("sistema@ddsutn.com", this.partidoDecorado.administrador().mail(), "Partido Confirmado", "El partido de la fecha "+this.partidoDecorado.fecha() +" tiene 10 jugadores");
			mailSender.sendMail(mail);
		}

	}

	private void actualizarCantInscriptosAnterior() {
		this.cantInscriptosAnterior = this.partidoDecorado.cantInscriptos();
	}

	public MailSender mailSender() {
		return mailSender;
	}


	/// Metodos del partido posta 
	public void generarEquipos(){
		this.partidoDecorado.generarEquipos();
	}

	public boolean contieneJugador(Inscripcion inscripcion) {
		return this.partidoDecorado.contieneJugador(inscripcion);
	}
	
	public Integer cantJugadoresEstandar() {
		return this.partidoDecorado.cantJugadoresEstandar();
	}
	
	public Integer cantInscriptos() {
		return this.partidoDecorado.cantInscriptos();
	}

	public Integer maxJugadoresxPartido() {
		return this.partidoDecorado.maxJugadoresxPartido();
	}

	public Persona administrador() {
		return this.partidoDecorado.administrador();
	}

	public String fecha() {
		return this.partidoDecorado.fecha();
	}

	public List<Inscripcion> getInscripciones() {
		return this.partidoDecorado.inscripciones();
	}

	public String lugar(){
		return this.lugar();
	}
}
