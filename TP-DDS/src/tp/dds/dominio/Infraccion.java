package tp.dds.dominio;

import java.time.LocalDateTime;

public class Infraccion {

	private LocalDateTime fecha;
	private String motivo;

	public Infraccion (LocalDateTime fecha, String motivo) {
		this.fecha = fecha;
		this.motivo = motivo;
	}

	public LocalDateTime fecha() {
		return fecha;
	}

	public String motivo() {
		return motivo;
	}

}
