package tp.dds.test.e3;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import tp.dds.dominio.Administrador;
import tp.dds.dominio.Jugadores;
import tp.dds.dominio.Jugador;


public class Entrega3Test2 {
	
	Administrador admin;
	Jugadores jugadores;

	Jugador jugador1, jugador2, jugador3 , jugador4, jugador5;
	Jugador jugador6, jugador7, jugador8, jugador9, jugador10, jugador11, jugador12;


	@Before
	public void initObjects(){

		jugador1 = new Jugador("Martin", 1970);
		jugador2 = new Jugador("Marcelo", 1974);
		jugador3 = new Jugador("Matias", 1992); 
		jugador4 = new Jugador("Federico", 1984);
		jugador5 = new Jugador("Patricio", 1981);
		jugador6 = new Jugador("Fabrizio", 1979);
		jugador7 = new Jugador("Esteban", 1978);
		jugador8 = new Jugador("Mariano", 1982);
		jugador9 = new Jugador("Walter", 1980);
		jugador10 = new Jugador("Cornelio", 1975);
		jugador11 = new Jugador("Demian", 1978);
		jugador12 = new Jugador("Gregorio", 1977);

		admin = new Administrador("Julian", "admin@ddsutn.com");
		jugadores = new Jugadores();

		jugadores.proponerJugador(jugador1);
		jugadores.proponerJugador(jugador2);
		jugadores.proponerJugador(jugador3);
		jugadores.proponerJugador(jugador4);
		jugadores.proponerJugador(jugador5);
		jugadores.proponerJugador(jugador6);
		jugadores.proponerJugador(jugador7);
		jugadores.proponerJugador(jugador8);
		jugadores.proponerJugador(jugador9);
		jugadores.proponerJugador(jugador10);
		jugadores.proponerJugador(jugador11);
		jugadores.proponerJugador(jugador12);
	}


	@Test
	public void evaluarJugadores() {
		System.out.println("Tiene que aprobar a todos");
		jugadores.evaluarJugadoresPendientes( (Jugador j) -> j.edad() >= 18 );
		Assert.assertEquals(12, jugadores.jugadoresAprobados().size());
	}


	@Test
	public void evaluarJugadores2() {
		System.out.println("Tiene que desaprobar 1");
		Assert.assertEquals(1, jugadores.denegaciones().size());
	}

}
