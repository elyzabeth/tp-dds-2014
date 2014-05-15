package tp.dds.test.e1;

import java.util.Date;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import tp.dds.entidades.CondMaxCantJugxEdad;
import tp.dds.entidades.InsEstandar;
import tp.dds.entidades.InsSolidaria;
import tp.dds.entidades.Inscripcion;
import tp.dds.entidades.Jugador;
import tp.dds.entidades.Partido;
import tp.dds.excepciones.NoHayLugarException;

/**
 * Tengo 1 partido con 9 jugadores estandar y 1 condicional.
 * Debe poder agregarse cualquier tipo de inscripcion desplazando al condicional.
 */
public class TPE1Prioridad4 {

	Jugador jugador1, jugador2, jugador3 , jugador4, jugador5;
	Jugador jugador6, jugador7, jugador8, jugador9, jugador10, jugador11, jugador12;
	Partido partido;
	
	@Before
	public void initObjects(){
		Date fechaPartido = new Date();
		partido = new Partido(fechaPartido);

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

		partido.inscribir(new InsEstandar(jugador1));
		partido.inscribir(new InsEstandar(jugador2));
		partido.inscribir(new InsEstandar(jugador3));
		partido.inscribir(new InsEstandar(jugador4));
		partido.inscribir(new InsEstandar(jugador5));
		partido.inscribir(new InsEstandar(jugador6));
		partido.inscribir(new InsEstandar(jugador7));
		partido.inscribir(new InsEstandar(jugador8));
		partido.inscribir(new InsEstandar(jugador9));
		partido.inscribir(new CondMaxCantJugxEdad(jugador10, 5, 20));
	
		System.out.println("Cant Jugadores estandar: "+ partido.cantJugadoresEstandar());
		
	}


	@Test
	public void agregarJugadorEstandar(){
		System.out.println("Agrego jugador Estandar: debe desplazar al condicional");
		Inscripcion ins = new InsEstandar(jugador11);
		partido.inscribir(ins);
		Assert.assertTrue(partido.contieneJugador(ins));
	}

	@Test
	public void agregarJugadorSolidario(){
		System.out.println("Agrego jugador Solidario: debe desplazar al condicional");
		Inscripcion ins = new InsSolidaria(jugador11);
		partido.inscribir(ins);
		Assert.assertTrue(partido.contieneJugador(ins));
	}

	@Test
	public void agregarJugadorCondicional(){
		System.out.println("Agrego jugador Condicional: debe desplazar al condicional");
		Inscripcion ins = new CondMaxCantJugxEdad(jugador11, 5, 20);
		partido.inscribir(ins);
		Assert.assertTrue(partido.contieneJugador(ins));
	}

}
