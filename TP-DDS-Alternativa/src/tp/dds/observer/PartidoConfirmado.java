package tp.dds.observer;

import tp.dds.entidades.Inscripcion;
import tp.dds.entidades.Mail;
import tp.dds.entidades.MailAdapter;
import tp.dds.entidades.MailSender;
import tp.dds.entidades.Partido;

public class PartidoConfirmado extends InscripcionObserver {

	private Integer cantInscriptosAnterior;

	public PartidoConfirmado(Partido partido, MailSender mailSender) {
		super(partido, mailSender);
		this.cantInscriptosAnterior = partido.cantInscriptos();
	}

	public void notificarNuevaInscripcion(Inscripcion inscripcion) {
		if (partido.cantInscriptos() == 10 && cantInscriptosAnterior != partido.cantInscriptos()){
			// TODO notificar Partido Confirmado al admin.
			Mail mail = MailAdapter.crearMail("sistema@ddsutn.com", partido.administrador().mail(), "Partido Confirmado", "El partido tiene 10 jugadores");
			//Mail mail = new Mail("sistema@ddsutn.com", partido.administrador().mail(), "Partido Confirmado", "El partido tiene 10 jugadores");
			mailSender.sendMail(mail);
		}
		this.cantInscriptosAnterior = partido.cantInscriptos();
	}
}
