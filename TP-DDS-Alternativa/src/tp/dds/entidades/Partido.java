package tp.dds.entidades;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import tp.dds.excepciones.NoHayLugarException;
import tp.dds.observer.InscripcionObserver;

public class Partido {

	protected final Integer MAX_JUGADORES_XPARTIDO = 10;
	private List<Inscripcion> inscripciones;
	protected	Integer plaza_asegurada;
	private Date fecha;
	private Administrador administrador;
	private List<InscripcionObserver> observadores;

	public Partido() {
		this(new Date());
	}
	
	public Partido(Date fecha) {
		this(fecha, new Administrador("Elizabeth", "elyzabeth@ddsutn.com"));
	}

	public Partido(Date fecha, Administrador admin) {
		inicializar();
		this.fecha = fecha;
		this.administrador = admin;
	}

	private void inicializar(){
		this.plaza_asegurada = 0;
		this.inscripciones = new ArrayList<Inscripcion>();
		this.observadores = new ArrayList<InscripcionObserver>();
	}

	public void inscribir(Inscripcion inscripcion) throws NoHayLugarException {
		if (permitirInscripcion()) {
			desplazar(inscripcion);
		}
		else {
			throw new NoHayLugarException(); 
		}

		notificarInscripcion(inscripcion);
	}


	private void notificarInscripcion(Inscripcion inscripcion) {
		Iterator<InscripcionObserver> it = this.observadores.iterator();
		InscripcionObserver aux;
		while(it.hasNext()){
			aux = (InscripcionObserver) it.next();
			aux.notificarNuevaInscripcion(inscripcion);
		}
	}

	public void bajaJugador(Jugador jugadorBaja, Jugador nuevoJugador) throws NoHayLugarException {

		// DAR de BAJA jugador
		quitarJugador(jugadorBaja);

		if (null == nuevoJugador){
			jugadorBaja.agregarInfraccion(new Infraccion(new Date(), "BAJA"));
			notificarInscripcion(null);
		} else {
			inscribir(new InsEstandar(nuevoJugador));
		}
	}


	private void quitarJugador(Jugador jugadorBaja) {
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
				break;
			}
		}
	}

	private boolean permitirInscripcion(){
		return (this.plaza_asegurada < MAX_JUGADORES_XPARTIDO);
	}

	private boolean desplazar(Inscripcion inscripcion) {
		List<Inscripcion> ins = new ArrayList<Inscripcion>();
		Iterator<Inscripcion> it;
		Inscripcion aux;

		if(this.inscripciones.isEmpty()|| cantInscriptos() < MAX_JUGADORES_XPARTIDO){
			this.inscripciones.add(inscripcion);
			this.plaza_asegurada += inscripcion.incrementarPlazaAsegurada();
			return true;
		} else {
			ins.addAll(this.inscripciones);
			it = ins.iterator();
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

	public void agregarObservador(InscripcionObserver obs) {
		this.observadores.add(obs);
	}

	public void generarEquipos(){
		// TODO
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

	public String fecha() {
		return fecha.toString();
	}

	public List<Inscripcion> getInscripciones() {
		return inscripciones;
	}


}
