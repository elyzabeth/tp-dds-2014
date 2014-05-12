package tp.dds.entidades;

public class InsEstandar extends Inscripcion {

	private PrioridadParticipacion prioridad = PrioridadParticipacion.ESTANDAR;

	public InsEstandar(Jugador jugador) {
		super(jugador);
	}


	@Override
	public int prioridad() {
		return prioridad.getValue();
	}

	@Override
	public Integer incrementarPlazaAsegurada(){
		return 1;
	}

}
