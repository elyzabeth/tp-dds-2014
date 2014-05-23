package tp.dds.dominio;

public class Mail {

	private String mensaje;
	private String remitente;
	private String receptor;
	private String asunto;

	public Mail (String remitente, String receptor, String asunto, String mensaje ){
		this.remitente = remitente;
		this.receptor = receptor;
		this.asunto = asunto;
		this.mensaje = mensaje;
	}

	public String getMensaje() {
		return mensaje;
	}
	public String getRemitente() {
		return remitente;
	}
	public String getReceptor() {
		return receptor;
	}
	public String getAsunto() {
		return asunto;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	public void setRemitente(String remitente) {
		this.remitente = remitente;
	}
	public void setReceptor(String receptor) {
		this.receptor = receptor;
	}
	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}
}
