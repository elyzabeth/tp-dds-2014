package tp.dds.entidades;

public abstract class InsCondicional extends Inscripcion {

	private PrioridadParticipacion prioridad = PrioridadParticipacion.CONDICIONAL;

	public InsCondicional(Jugador jugador) {
		super(jugador);
	}

	@Override
	public int prioridad() {
		return this.prioridad.ordinal();
	}

	@Override
	public boolean cederPlaza(Inscripcion inscripcion) {
		return (this.prioridad() <= inscripcion.prioridad());
	}

	protected abstract boolean cumpleCondicion(Partido partido);

}
