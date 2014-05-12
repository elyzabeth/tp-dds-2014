package tp.dds.entidades;

public class InsSolidaria extends Inscripcion {

	private PrioridadParticipacion prioridad = PrioridadParticipacion.SOLIDARIO;

	public InsSolidaria(Jugador jugador) {
		super(jugador);
	}


	@Override
	public int prioridad() {
		return this.prioridad.getValue();
	}
	
	@Override
	public boolean cederPlaza(Inscripcion inscripcion) {
		return (this.prioridad() <= inscripcion.prioridad());
	}

}
