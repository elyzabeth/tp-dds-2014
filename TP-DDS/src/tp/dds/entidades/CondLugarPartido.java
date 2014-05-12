package tp.dds.entidades;

public class CondLugarPartido extends InsCondicional {

	public CondLugarPartido(Jugador jugador) {
		super(jugador);
	}
	
	public boolean cumpleCondicion(){
		// TODO valor hardcodeado, modificar por metodo que controle la condicion.
		return true;
	}

}
