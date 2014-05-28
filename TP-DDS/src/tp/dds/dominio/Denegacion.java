package tp.dds.dominio;

import java.time.LocalDateTime;

public class Denegacion {

	private Jugador jugador;
	private LocalDateTime fecha;
	private String motivo;

	public Denegacion(LocalDateTime fecha, String motivo, Jugador jugador) {
		this.fecha = fecha;
		this.motivo = motivo;
		this.jugador = jugador;
	}

	public LocalDateTime fecha() {
		return fecha;
	}

	public String motivo() {
		return motivo;
	}

	public Jugador jugador() {
		return jugador;
	}
}
