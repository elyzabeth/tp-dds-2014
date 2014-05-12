package tp.dds.observer;

import tp.dds.entidades.Partido;

public class InscripcionJugador extends InscripcionObserver {

	public InscripcionJugador(Partido partido) {
		super(partido);
	}

	public void notificarNuevaInscripcion(Partido partido) {

		// TODO notificar a los amigos del jugador que se anoto al partido.
	}

}
