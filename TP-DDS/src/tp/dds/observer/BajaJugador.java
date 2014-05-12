package tp.dds.observer;

import tp.dds.entidades.Partido;

public class BajaJugador extends InscripcionObserver {

	private Integer cantInscriptosAnterior;

	public BajaJugador(Partido partido) {
		super(partido);
		this.cantInscriptosAnterior = partido.cantInscriptos();
	}

	public void notificarNuevaInscripcion(Partido partido) {
		if (cantInscriptosAnterior == partido.maxJugadoresxPartido() && partido.cantInscriptos() < partido.maxJugadoresxPartido()){
			cantInscriptosAnterior = partido.cantInscriptos();
			// TODO notificar Partido dejo de tener 10 jugadores al admin.
		}
	}

}
