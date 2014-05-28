package tp.dds.dominio;

import java.util.ArrayList;
import java.util.List;


public abstract class Inscripcion {

	private Jugador jugador;
	private Boolean asistencia;
	private List<Calificacion> calificaciones;

	public Inscripcion(Jugador jugador) {
		this.jugador = jugador;
		this.asistencia = false;
		this.calificaciones = new ArrayList<Calificacion>();
	}


	public Integer incrementarPlazaAsegurada() {
		return 0;
	}

	public abstract int prioridad();

	public abstract boolean cederPlaza(Inscripcion inscripcion);

	public abstract boolean confirmarPresencia(Partido partido);

	public Jugador jugador() {
		return jugador;
	}

	public Boolean asistencia() {
		return asistencia;
	}

	public void confirmarAsistencia() {
		this.asistencia = true;
	}

	public void confirmarAusencia() {
		this.asistencia = false;
	}

	public List<Calificacion> calificaciones() {
		return calificaciones;
	}

	public void setCalificaciones(List<Calificacion> calificaciones) {
		this.calificaciones = calificaciones;
	}

	public void calificarJugador(Calificacion calificacion) {
		this.calificaciones.add(calificacion);
	}

}
