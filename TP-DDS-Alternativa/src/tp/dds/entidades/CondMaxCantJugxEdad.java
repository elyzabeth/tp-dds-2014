package tp.dds.entidades;

public class CondMaxCantJugxEdad extends InsCondicional {

	public CondMaxCantJugxEdad(Jugador jugador) {
		super(jugador);
	}
	
	public boolean cumpleCondicion(){
		// TODO valor hardcodeado, modificar por metodo que controle la condicion.
		return true;
	}

}
