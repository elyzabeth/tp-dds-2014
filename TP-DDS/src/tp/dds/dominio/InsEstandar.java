package tp.dds.dominio;

public class InsEstandar extends Inscripcion {

	private PrioridadParticipacion prioridad = PrioridadParticipacion.ESTANDAR;

	public InsEstandar(Jugador jugador) {
		super(jugador);
	}


	@Override
	public int prioridad() {
		return prioridad.ordinal();
	}

	@Override
	public Integer incrementarPlazaAsegurada(){
		return 1;
	}


	@Override
	public boolean cederPlaza(Inscripcion inscripcion) {
		return false;
	}


	@Override
	public boolean confirmarPresencia(Partido partido) {
		return true;
	}

}
