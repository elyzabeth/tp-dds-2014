package tp.dds.entidades;

public abstract class Inscripcion {

	private PrioridadParticipacion prioridad;

	private Jugador iJugador;

	public Inscripcion(Jugador jugador) {
		this.iJugador = jugador;
	}


	public Integer incrementarPlazaAsegurada(){
		return 0;
	}

	public int prioridad() {
		return prioridad.getValue();
	}

	public Jugador jugador() {
		return iJugador;
	}

	public boolean cederPlaza(Inscripcion inscripcion) {
		return false;
	}
}
