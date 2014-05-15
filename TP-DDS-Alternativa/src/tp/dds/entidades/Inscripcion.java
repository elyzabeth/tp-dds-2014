package tp.dds.entidades;

public abstract class Inscripcion {

	//private PrioridadParticipacion prioridad;

	private Jugador jugador;

	public Inscripcion(Jugador jugador) {
		this.jugador = jugador;
	}


	public Integer incrementarPlazaAsegurada(){
		return 0;
	}

	public abstract int prioridad();

	public Jugador jugador() {
		return jugador;
	}

	public boolean cederPlaza(Inscripcion inscripcion) {
		return false;
	}
}
