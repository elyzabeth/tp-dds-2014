package tp.dds.entidades;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import tp.dds.excepciones.NoHayLugarException;

public class Partido {

	private final Integer MAX_JUGADORES_XPARTIDO = 10;
	private List<Inscripcion> inscripciones;
	private	Integer plaza_asegurada;
	private Date fecha;
	private Administrador administrador;

	
	public Partido(Date fecha) {
		this(fecha, null);
	}

	public Partido(Date fecha, Administrador admin) {
		inicializar();
		this.fecha = fecha;
		this.administrador = admin;
	}

	private void inicializar(){
		this.plaza_asegurada = 0;
		this.inscripciones = new ArrayList<Inscripcion>();
	}

	public List<Inscripcion> getInscripciones() {
		return inscripciones;
	}

	public void inscribir(Inscripcion inscripcion) throws NoHayLugarException {
		if (permitirInscripcion()) {

			if (desplazar(inscripcion))
				notificarAmigosJugador(inscripcion.jugador());

			if (this.inscripciones.size() == MAX_JUGADORES_XPARTIDO)
				notificarPartidoConfirmado();
		}
		else {
			throw new NoHayLugarException(); 
		}
	}


	public void bajaJugador(Jugador jugadorBaja, Jugador nuevoJugador) throws NoHayLugarException {

		Integer cantInscripciones = this.cantInscriptos();

		// DAR de BAJA jugador
		quitarJugador(jugadorBaja);

		if (null == nuevoJugador)
			jugadorBaja.agregarInfraccion(new Infraccion(new Date(), "BAJA"));
		else
			inscribir(new InsEstandar(nuevoJugador));

		// TODO notificar si quedan menos inscriptos
		if (cantInscripciones == MAX_JUGADORES_XPARTIDO && cantInscripciones > cantInscriptos())
			notificarMenosJugConfirmados();
	}


	private void quitarJugador(Jugador jugadorBaja) {
		Iterator<Inscripcion> it = this.inscripciones.iterator();
		Inscripcion aux;

		while(it.hasNext()){		
			aux = it.next();
			if (aux.jugador().nombre().equals(jugadorBaja.nombre()) ) {
				this.inscripciones.remove(aux);
			}
		}
	}

	private void notificarPartidoConfirmado() {

	}

	private void notificarMenosJugConfirmados() {
		// TODO notificar al admin que el partido dejo de tener 10 jugadores.
	}

	private void notificarAmigosJugador(Jugador jugador) {
		// TODO notificar que un jugador se inscribio a sus amigos.
	}

	private boolean permitirInscripcion(){
		return (this.plaza_asegurada < MAX_JUGADORES_XPARTIDO);
	}

	private boolean desplazar(Inscripcion inscripcion) {
		Iterator<Inscripcion> it = this.inscripciones.iterator();
		Inscripcion aux;

		if(this.inscripciones.isEmpty()|| cantInscriptos() < MAX_JUGADORES_XPARTIDO){
			this.inscripciones.add(inscripcion);
			this.plaza_asegurada += inscripcion.incrementarPlazaAsegurada();
			return true;
		} else {
			while(it.hasNext()){
				aux = it.next();
				if (aux.cederPlaza(inscripcion)){
					this.inscripciones.remove(aux);
					this.inscripciones.add(inscripcion);
					this.plaza_asegurada += inscripcion.incrementarPlazaAsegurada();
					return true;
				}
			}
		}

		return false;
	}

	public void generarEquipos(){
		// TODO
	}

	public Date fecha() {
		return fecha;
	}

	public boolean contieneJugador(Inscripcion inscripcion) {
		return this.inscripciones.contains(inscripcion);
	}
	
	public Integer cantJugadoresEstandar() {
		return plaza_asegurada;
	}
	
	public Integer cantInscriptos() {
		return this.inscripciones.size();
	}

	public Integer maxJugadoresxPartido() {
		return this.MAX_JUGADORES_XPARTIDO;
	}

	public Persona administrador() {
		return administrador;
	}

}
