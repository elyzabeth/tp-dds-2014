package tp.dds.dominio;

import java.util.List;

import tp.dds.interfaces.MailSender;
import tp.dds.interfaces.Partido;
import tp.dds.interfaces.Persona;


public abstract class PartidoDecorator implements Partido {

	protected Partido partido;
	protected MailSender mailSender;

	public PartidoDecorator(Partido partido, MailSender mailSender) {
		this.partido = partido;
		this.mailSender = mailSender;
	}

	public abstract void inscribir(Inscripcion inscripcion);

	public abstract void bajaJugador(Jugador jugadorBaja, Jugador jugadorNuevo);



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
