package tp.dds.entidades;

import java.util.Date;

public class Infraccion {

	private Date fecha;
	private String motivo;

	public Infraccion (Date fecha, String motivo) {
		this.fecha = fecha;
		this.motivo = motivo;
	}

	public Date fecha() {
		return fecha;
	}

	public String motivo() {
		return motivo;
	}

}
