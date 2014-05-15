package tp.dds.entidades;

import java.util.List;

import tp.dds.observer.InscripcionObserver;


public class PartidoDecorator extends Partido {

	protected Partido partidoDecorado;

	public PartidoDecorator(Partido partido) {
		this.partidoDecorado = partido;
	}

	public void agregarObservador(InscripcionObserver obs) {
		this.partidoDecorado.agregarObservador(obs);
	}

	public void generarEquipos(){
		this.partidoDecorado.generarEquipos();
	}

	public boolean contieneJugador(Inscripcion inscripcion) {
		return this.partidoDecorado.contieneJugador(inscripcion);
	}
	
	public Integer cantJugadoresEstandar() {
		return this.partidoDecorado.plaza_asegurada;
	}
	
	public Integer cantInscriptos() {
		return this.partidoDecorado.cantInscriptos();
	}

	public Integer maxJugadoresxPartido() {
		return this.partidoDecorado.MAX_JUGADORES_XPARTIDO;
	}

	public Persona administrador() {
		return this.partidoDecorado.administrador();
	}

	public String fecha() {
		return this.partidoDecorado.fecha();
	}

	public List<Inscripcion> getInscripciones() {
		return this.partidoDecorado.getInscripciones();
	}
}
