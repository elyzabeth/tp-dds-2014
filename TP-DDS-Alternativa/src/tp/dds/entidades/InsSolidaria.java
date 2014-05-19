package tp.dds.entidades;

import tp.dds.interfaces.Partido;

public class InsSolidaria extends Inscripcion {

	private PrioridadParticipacion prioridad = PrioridadParticipacion.SOLIDARIO;

	public InsSolidaria(Jugador jugador) {
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
		return true;
	}

}
