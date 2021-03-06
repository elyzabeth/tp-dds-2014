package tp.dds.dominio;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import tp.dds.excepciones.NoCumpleCondicionException;
import tp.dds.excepciones.NoExisteJugadorEnPartidoException;
import tp.dds.excepciones.NoHayLugarException;
import tp.dds.interfaces.Partido;
import tp.dds.interfaces.Persona;

public class PartidoPosta implements Partido {

	private final Integer MAX_JUGADORES_XPARTIDO = 10;
	private Date fecha;
	private String lugar;
	private Administrador administrador;
	
	private List<Inscripcion> inscripciones;
	private Integer plaza_asegurada;


	public PartidoPosta() {
		this(new Date());
	}
	
	public PartidoPosta(Date fecha) {
		this(fecha, new Administrador("Administrador", "admin@ddsutn.com"));
	}

	public PartidoPosta(Date fecha, Administrador admin) {
		inicializar();
		this.fecha = fecha;
		this.administrador = admin;
		this.lugar = "";
	}

	private void inicializar(){
		this.plaza_asegurada = 0;
		this.inscripciones = new ArrayList<Inscripcion>();
	}


	public void inscribir(Inscripcion inscripcion) {
		if (permitirInscripcion()) {
			desplazar(inscripcion);
		}
		else {
			throw new NoHayLugarException(); 
		}
		limpiarCondicionales();
	}


	public void bajaJugador(Jugador jugadorBaja, Jugador jugadorNuevo) {

		// DAR de BAJA jugador
		quitarJugador(jugadorBaja);

		if (null == jugadorNuevo){
			jugadorBaja.agregarInfraccion(new Infraccion(new Date(), "BAJA"));
		} else {
			inscribir(new InsEstandar(jugadorNuevo));
		}
	}


	protected void quitarJugador(Jugador jugadorBaja) {
		List<Inscripcion> ins = new ArrayList<Inscripcion>();
		Iterator<Inscripcion> it;
		Inscripcion aux = null;

		ins.addAll(this.inscripciones);
		it = ins.iterator();

		while(it.hasNext()){
			aux = it.next();
			if (aux.jugador().nombre().equals(jugadorBaja.nombre()) ) {
				this.inscripciones.remove(aux);
				this.plaza_asegurada -= aux.incrementarPlazaAsegurada();
				return;
			}
		}
		throw new NoExisteJugadorEnPartidoException();
	}

	protected boolean permitirInscripcion(){
		return (this.plaza_asegurada < MAX_JUGADORES_XPARTIDO);
	}

	private void desplazar(Inscripcion inscripcion) {
		List<Inscripcion> ins = new ArrayList<Inscripcion>();
		Iterator<Inscripcion> it;
		Inscripcion aux;

		if( inscripcion.confirmarPresencia(this)) {
			if( this.inscripciones.isEmpty() || cantInscriptos() < MAX_JUGADORES_XPARTIDO) {
				this.inscripciones.add(inscripcion);
				this.plaza_asegurada += inscripcion.incrementarPlazaAsegurada();
				return;
			} else {
				ins.addAll(this.inscripciones);
				it = ins.iterator();
				while(it.hasNext()){
					aux = it.next();
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
		Iterator<Inscripcion> it;
		Inscripcion aux;
		ins.addAll(this.inscripciones);
		it = ins.iterator();

		while(it.hasNext()){
			aux = it.next();
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
		return fecha.toString();
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

	public List<Inscripcion> inscripciones() {
		return inscripciones;
	}

	public String lugar() {
		return lugar;
	}


}
