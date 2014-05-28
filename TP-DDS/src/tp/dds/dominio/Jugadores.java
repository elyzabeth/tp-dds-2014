package tp.dds.dominio;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


import tp.dds.interfaces.CheckCandidato;

public class Jugadores {

	private List<Jugador> jugadoresPendientes;
	private List<Jugador> jugadoresAprobados;
	private List<Denegacion> denegaciones;

	public Jugadores(){
		inicializar();
	}

	private void inicializar(){
		this.jugadoresAprobados = new ArrayList<Jugador>();
		this.denegaciones = new ArrayList<Denegacion>();
		this.jugadoresPendientes = new ArrayList<Jugador>();
	}

	public List<Jugador> jugadoresAprobados() {
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
		this.jugadoresAprobados.add(jugador);
	}

	public void desaprobarJugador(Jugador jugador) {
		this.jugadoresPendientes.remove(jugador);
		this.denegaciones.add(new Denegacion(LocalDateTime.now(), "No cumple condicion", jugador));
	}

	public void evaluarJugadoresPendientes (CheckCandidato evaluador) {

		for (Jugador candidato : this.jugadoresPendientes) {
			if(evaluador.cumpleCondicion(candidato)){
				aprobarJugador(candidato);
			} else {
				desaprobarJugador(candidato);
			}
		}

		this.jugadoresPendientes.clear();
	}

}
