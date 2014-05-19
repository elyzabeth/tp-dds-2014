package tp.dds.entidades;

public abstract class InsCondicional extends Inscripcion {

	private PrioridadParticipacion prioridad = PrioridadParticipacion.CONDICIONAL;

	public InsCondicional(Jugador jugador) {
		super(jugador);
	}

	public int prioridad() {
		return this.prioridad.ordinal();
	}

	public boolean cederPlaza(Inscripcion inscripcion) {
		return (this.prioridad() <= inscripcion.prioridad());
	}

	public boolean confirmarPresencia(Partido partido) {
		return cumpleCondicion(partido);
	}

	protected abstract boolean cumpleCondicion(Partido partido);

}
