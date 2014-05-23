package tp.dds.observer;

import tp.dds.dominio.Inscripcion;
import tp.dds.dominio.Partido;
import tp.dds.interfaces.MailSender;

public class PartidoConfirmado extends InscripcionObserver {

	private Integer cantInscriptosAnterior;

	public PartidoConfirmado(Partido partido, MailSender mailSender) {
		super(partido, mailSender);
		this.cantInscriptosAnterior = partido.cantInscriptos();
	}

	public void notificarNuevaInscripcion(Inscripcion inscripcion) {
		if (partido.cantInscriptos() == 10 && cantInscriptosAnterior != partido.cantInscriptos()){
			// notificar Partido Confirmado al admin.
			enviarMail("sistema@ddsutn.com", partido.administrador().mail(), "Partido Confirmado", "El partido de la fecha "+partido.fecha()+" tiene 10 jugadores");
		}
		this.cantInscriptosAnterior = partido.cantInscriptos();
	}
}
