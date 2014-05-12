package tp.dds.entidades;

public class Administrador implements Persona {

	private String nombre;
	private String mail;

	public Administrador(String nombre, String mail) {
		this.nombre = nombre;
		this.mail = mail;
	}

	@Override
	public String nombre() {
		return this.nombre;
	}

	@Override
	public String mail() {
		return this.mail;
	}

}
