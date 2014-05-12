package tp.dds.entidades;

public class Jugador {
	
	private String jnombre;
	private Integer jcategoria;
	private Integer jinfracciones;
	
	public Jugador(String nombre, Integer categoria){
		this.jnombre = nombre;
		this.jcategoria = categoria;
	}

	public String nombre() {
		return jnombre;
	}

	public Integer categoria() {
		return jcategoria;
	}

	public Integer infracciones() {
		return jinfracciones;
	}

	public void agregarInfraccion() {
		this.jinfracciones+=1;
	}

}
