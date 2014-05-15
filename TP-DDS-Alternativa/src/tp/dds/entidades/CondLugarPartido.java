package tp.dds.entidades;

public class CondLugarPartido extends InsCondicional {

	private String lugar;

	public CondLugarPartido(Jugador jugador) {
		this(jugador, "");
	}

	public CondLugarPartido(Jugador jugador, String lugar) {
		super(jugador);
		this.lugar = lugar; 
	}

	protected boolean cumpleCondicion(Partido partido){
		return lugar.equalsIgnoreCase(partido.lugar());
	}

	@Override
	public boolean confirmarPresencia(Partido partido) {
		return cumpleCondicion(partido);
	}

}
