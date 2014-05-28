package tp.dds.dominio;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class Jugadores {

	private List<Jugador> jugadoresPendientes;
	private List<Inscripcion> jugadoresAprobados;
	private List<Denegacion> denegaciones;

	public Jugadores(){
		inicializar();
	}

	private void inicializar(){
		this.jugadoresAprobados = new ArrayList<Inscripcion>();
		this.denegaciones = new ArrayList<Denegacion>();
		this.jugadoresPendientes = new ArrayList<Jugador>();
	}

	public List<Inscripcion> jugadoresAprobados() {
		return this.jugadoresAprobados;
	}

	public List<Denegacion> denegaciones() {
		return this.denegaciones;
	}

	public List<Jugador> jugadoresPendientes() {
		return this.jugadoresPendientes;
	}

	public boolean proponerJugador(Jugador jugador) {
		return this.jugadoresPendientes.add(jugador);
	}

	public void aprobarJugador(Jugador jugador) {
		this.jugadoresPendientes.remove(jugador);
		this.jugadoresAprobados.add(new InsEstandar(jugador));
	}

	public void desaprobarJugador(Jugador jugador) {
		this.jugadoresPendientes.remove(jugador);
		this.denegaciones.add(new Denegacion(LocalDateTime.now(), "No cumple condicion", jugador));
	}

}
