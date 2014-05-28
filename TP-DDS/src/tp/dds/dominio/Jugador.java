package tp.dds.dominio;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import tp.dds.interfaces.Persona;


public class Jugador implements Persona {

	private String nombre;
	private String mail;
	private Integer categoria;
	private List<Infraccion> infracciones;
	private List<Persona> amigos;


	public Jugador(String nombre, Integer categoria){
		this(nombre, "", categoria);
	}

	public Jugador(String nombre, String mail, Integer categoria){
		this.nombre = nombre;
		this.mail = mail;
		this.categoria = categoria;
		inicializar();
	}

	private void inicializar() {
		this.infracciones = new ArrayList<Infraccion>();
		this.amigos = new ArrayList<Persona>();
	}


	public Integer categoria() {
		return categoria;
	}
	
	public Integer edad() {
		return LocalDate.now().getYear() - categoria;
	}

	public List<Infraccion> infracciones() {
		return infracciones;
	}

	public Integer cantInfracciones() {
		return infracciones.size();
	}

	public void agregarInfraccion(Infraccion infraccion) {
		this.infracciones.add(infraccion);
	}

	public void agregarAmigo(Persona amigo) {
		if (!this.amigos.contains(amigo))
			this.amigos.add(amigo);
	}

	public String nombre() {
		return nombre;
	}

	public String mail() {
		return this.mail;
	}

	public List<Persona> amigos() {
		return this.amigos;
	}

}
