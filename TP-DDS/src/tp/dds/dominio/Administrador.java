package tp.dds.dominio;

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

}
