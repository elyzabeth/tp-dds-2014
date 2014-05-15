package tp.dds.entidades;

import java.util.Iterator;
import java.util.List;

public class CondMaxCantJugxEdad extends InsCondicional {

	//TODO agregar condicion!!!
	private Integer edadMaxJugadores;
	private Integer cantJugxEdad;

	public CondMaxCantJugxEdad(Jugador jugador) {
		this(jugador, 10, 20);
	}

	public CondMaxCantJugxEdad(Jugador jugador, Integer cantidad, Integer edad) {
		super(jugador);
		this.cantJugxEdad = cantidad;
		this.edadMaxJugadores = edad;
	}
	
	protected boolean cumpleCondicion(Partido partido){
		// TODO valor hardcodeado, modificar por metodo que controle la condicion.
		List<Inscripcion> inscripciones = partido.inscripciones();
		Iterator<Inscripcion> it = inscripciones.iterator();
		Integer cant = 0;
		Inscripcion aux;

		while(it.hasNext()){
			aux = it.next();
			/// FIX this
			cant += (2014 - aux.jugador().categoria() <= edadMaxJugadores) ? 1 : 0;
		}

		return cantJugxEdad >= cant;
	}

	@Override
	public boolean confirmarPresencia(Partido partido) {
		return cumpleCondicion(partido);
	}

}