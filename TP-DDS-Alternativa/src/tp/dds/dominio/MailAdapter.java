package tp.dds.dominio;

public class MailAdapter {

	public static Mail crearMail(String remitente, String receptor, String asunto, String mensaje  ){
		return new Mail(remitente, receptor, asunto, mensaje);
	}
}
