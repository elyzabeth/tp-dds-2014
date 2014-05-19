package tp.dds.entidades;

public abstract class Inscripcion {

	private Jugador jugador;

	public Inscripcion(Jugador jugador) {
		this.jugador = jugador;
	}


	public Integer incrementarPlazaAsegurada() {
		return 0;
	}

	public abstract int prioridad();

	public abstract boolean cederPlaza(Inscripcion inscripcion);

	public abstract boolean confirmarPresencia(Partido partido);

	public Jugador jugador() {
		return jugador;
	}

}
