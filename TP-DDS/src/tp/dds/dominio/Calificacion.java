package tp.dds.dominio;

public class Calificacion {

	private Integer calificacion;
	private String critica;

	public Calificacion (Integer calificacion, String critica) {
		this.calificacion = calificacion;
		this.critica = critica;
	}

	public Integer calificacion() {
		return calificacion;
	}

	public void setCalificacion(Integer calificacion) {
		this.calificacion = calificacion;
	}

	public String critica() {
		return critica;
	}

	public void setCritica(String critica) {
		this.critica = critica;
	}

	
}
