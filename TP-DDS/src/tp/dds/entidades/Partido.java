package tp.dds.entidades;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import tp.dds.excepciones.NoHayLugarException;

public class Partido {

	private List<Inscripcion> inscripciones;
	private	Integer plaza_asegurada;
	private Date fecha;

	public Partido(Date fecha) {
		inicializar();
		this.fecha = fecha;
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
			desplazar(inscripcion);
			this.plaza_asegurada += inscripcion.incrementarPlazaAsegurada();
		}
		else {
			throw new NoHayLugarException(); 
		}
	}

	private boolean permitirInscripcion(){
		return (this.plaza_asegurada < 10);
	}

	private void desplazar(Inscripcion inscripcion) {
		Iterator<Inscripcion> it = this.inscripciones.iterator();
		Inscripcion aux;

		if(this.inscripciones.isEmpty()|| this.inscripciones.size()<10)
			this.inscripciones.add(inscripcion);
		else
			while(it.hasNext()){
				aux = it.next();
				if (aux.cederPlaza(inscripcion)){
					this.inscripciones.remove(aux);
					this.inscripciones.add(inscripcion);
				}
			}
	}

	public void generarEquipos(){
		// TODO
	}

	public Date fecha() {
		return fecha;
	}

	public boolean contieneJugador(Inscripcion inscripcion) {
		Iterator<Inscripcion> it = this.inscripciones.iterator();
		Inscripcion aux;
		while(it.hasNext()){
			aux = it.next();
			if (aux.jugador().nombre().equals(inscripcion.jugador().nombre()) ) {
				return true;
			}
		}
		return false;
	}
	
	public Integer cantJugadoresEstandar() {
		return plaza_asegurada;
	}

}
