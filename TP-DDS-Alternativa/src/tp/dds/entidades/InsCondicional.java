package tp.dds.entidades;

import tp.dds.interfaces.Partido;

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

	@Override
	public boolean confirmarPresencia(Partido partido) {
		return cumpleCondicion(partido);
	}

	protected abstract boolean cumpleCondicion(Partido partido);
}
