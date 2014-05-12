package tp.dds.observer;

import tp.dds.entidades.Partido;

public abstract class InscripcionObserver {

	private Partido partido;
	
	public InscripcionObserver (Partido partido){
		this.partido = partido;
	}

	public void notificarNuevaInscripcion(){
		
	}

}
