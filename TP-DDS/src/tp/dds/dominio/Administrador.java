package tp.dds.dominio;

import java.time.LocalDateTime;

import tp.dds.interfaces.Persona;

public class Administrador implements Persona {

	private String nombre;
	private String mail;

	public Administrador(String nombre, String mail) {
		this.nombre = nombre;
		this.mail = mail;
	}

	public String nombre() {
		return this.nombre;
	}

	public String mail() {
		return this.mail;
	}
	
	public void evaluarJugadoresPendientes(Jugadores baseDeDatos) { 
		
		while(baseDeDatos.jugadoresPendientes().size() > 0) {
			
			Jugador jugador = baseDeDatos.jugadoresPendientes().remove(0);		
			if (this.esAprobable(jugador)) {
				
				baseDeDatos.jugadoresAprobados().add(jugador);
				System.out.println(jugador.nombre()+ " fue aceptado.");
				
			} else {
				
				String motivo = "No me cae bien";
				Denegacion denegacion = new Denegacion(LocalDateTime.now(), motivo, jugador);
				baseDeDatos.denegaciones().add(denegacion);
				System.out.println(jugador.nombre()+ " fue rechazado.");
			} 
		}
	}

	public boolean esAprobable(Jugador jugador) {
		if (jugador.nombre().length() > 6) return true;
		else return false;
	}
}
