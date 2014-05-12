package tp.dds.observer;

import tp.dds.entidades.Partido;

public class PartidoConfirmado extends InscripcionObserver {

	public PartidoConfirmado(Partido partido) {
		super(partido);
	}

	public void notificarNuevaInscripcion(Partido partido) {
		if (partido.cantInscriptos() == 10){
			// TODO notificar Partido Confirmado al admin.
		}
			
	}
}
