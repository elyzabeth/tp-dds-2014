package tp.dds.dominio;

public abstract class Inscripcion {

	private Jugador jugador;
	private Boolean asistencia;

	public Inscripcion(Jugador jugador) {
		this.jugador = jugador;
		this.asistencia = false;
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

	public Boolean asistencia() {
		return asistencia;
	}

	public void confirmarAsistencia() {
		this.asistencia = true;
	}

	public void confirmarAusencia() {
		this.asistencia = false;
	}
}
