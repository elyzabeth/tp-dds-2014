package tp.dds.dominio;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import tp.dds.excepciones.NoCumpleCondicionException;
import tp.dds.excepciones.NoExisteJugadorEnPartidoException;
import tp.dds.excepciones.NoHayLugarException;
import tp.dds.interfaces.Persona;
import tp.dds.observer.InscripcionObserver;

public class Partido {

	private final Integer MAX_JUGADORES_XPARTIDO = 10;
	private LocalDateTime fecha;
	private String lugar;
	private Administrador administrador;

	private	Integer plaza_asegurada;
	private List<Inscripcion> inscripciones;
	private List<InscripcionObserver> observadores;


	public Partido(LocalDateTime fecha) {
		this(fecha, new Administrador("Administrador", "admin@ddsutn.com"));
	}

	public Partido(LocalDateTime fecha, Administrador admin) {
		inicializar();
		this.fecha = fecha;
		this.administrador = admin;
		this.lugar = "";
	}

	private void inicializar(){
		this.plaza_asegurada = 0;
		this.inscripciones = new ArrayList<Inscripcion>();
		this.observadores = new ArrayList<InscripcionObserver>();
	}


	public void inscribir(Inscripcion inscripcion) {
		if (permitirInscripcion()) {
			desplazar(inscripcion);
		}
		else {
			throw new NoHayLugarException();
		}
		limpiarCondicionales();

		notificarInscripcion(inscripcion);
	}


	private void notificarInscripcion(Inscripcion inscripcion) {
		for (InscripcionObserver aux : this.observadores) {
			aux.notificarNuevaInscripcion(inscripcion);
		}
	}

	public void bajaJugador(Jugador jugadorBaja, Jugador jugadorNuevo) {

		// DAR de BAJA jugador
		quitarJugador(jugadorBaja);

		if (null == jugadorNuevo){
			jugadorBaja.agregarInfraccion(new Infraccion(LocalDateTime.now(), "BAJA sin reemplazo"));
			notificarInscripcion(null);
		} else {
			inscribir(new InsEstandar(jugadorNuevo));
		}

	}


	private void quitarJugador(Jugador jugadorBaja) {
		List<Inscripcion> ins = new ArrayList<Inscripcion>();
		ins.addAll(this.inscripciones);

		for (Inscripcion inscripcion : ins) {
			if (inscripcion.jugador().nombre().equals(jugadorBaja.nombre()) ) {
				this.inscripciones.remove(inscripcion);
				this.plaza_asegurada -= inscripcion.incrementarPlazaAsegurada();
				return;
			}
		}

		throw new NoExisteJugadorEnPartidoException();
	}


	private boolean permitirInscripcion(){
		return (this.plaza_asegurada < MAX_JUGADORES_XPARTIDO);
	}

	private void desplazar(Inscripcion inscripcion) {
		List<Inscripcion> ins = new ArrayList<Inscripcion>();

		if( inscripcion.confirmarPresencia(this)) {
			if( this.inscripciones.isEmpty() || cantInscriptos() < MAX_JUGADORES_XPARTIDO) {
				this.inscripciones.add(inscripcion);
				this.plaza_asegurada += inscripcion.incrementarPlazaAsegurada();
				return;
			} else {
				ins.addAll(this.inscripciones);

				for (Inscripcion aux : ins) {
					if (aux.cederPlaza(inscripcion)){
						this.inscripciones.remove(aux);
						this.inscripciones.add(inscripcion);
						this.plaza_asegurada += inscripcion.incrementarPlazaAsegurada();
						return;
					}
				}
			}

			// TODO aca deberiamos distinguir cuando no hay lugar porque nadie le cede su lugar?? 
			// de cuando no hay lugar porque son todas inscripciones estandar??
			throw new NoHayLugarException();

		} else {
			throw new NoCumpleCondicionException();
		}

	}

	private void limpiarCondicionales() {
		List<Inscripcion> ins = new ArrayList<Inscripcion>();
		ins.addAll(this.inscripciones);

		for (Inscripcion aux : ins) {
			if (!aux.confirmarPresencia(this)) {
				this.inscripciones.remove(aux);
				this.plaza_asegurada -= aux.incrementarPlazaAsegurada();
			}
		}
	}

	public void generarEquipos(){
		// TODO Devolver listado de jugadores verificando si se cumplen 
		// las condiciones de las inscripciones condicionales
	}

	public String fecha() {
		// TODO formatear fecha!!
		return fecha.format(DateTimeFormatter.ofPattern("dd-MM-uuuu"));
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

	public void agregarObservador(InscripcionObserver obs) {
		this.observadores.add(obs);
	}

	public List<Inscripcion> inscripciones() {
		return inscripciones;
	}

	public String lugar() {
		return lugar;
	}

}
