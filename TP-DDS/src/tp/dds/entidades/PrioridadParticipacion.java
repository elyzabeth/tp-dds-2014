package tp.dds.entidades;
public enum PrioridadParticipacion{
	ESTANDAR(3), SOLIDARIO(2), CONDICIONAL(1);
	
	private int value;
	
	private PrioridadParticipacion(int i){
		this.value = i;
	}
	
	public int getValue(){
		return value;
	}
}