package tp.dds.interfaces;

import java.util.List;

import tp.dds.entidades.Inscripcion;
import tp.dds.entidades.Jugador;

public interface Partido {

	public void inscribir(Inscripcion inscripcion);

	public void bajaJugador(Jugador jugadorBaja, Jugador jugadorNuevo);

	public void generarEquipos();

	public String fecha();

	public boolean contieneJugador(Inscripcion inscripcion);
	
	public Integer cantJugadoresEstandar();
	
	public Integer cantInscriptos();

	public Integer maxJugadoresxPartido();

	public Persona administrador();

	public List<Inscripcion> inscripciones();

	public String lugar();


}
