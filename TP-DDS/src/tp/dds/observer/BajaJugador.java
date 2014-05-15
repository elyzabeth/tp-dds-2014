package tp.dds.observer;

import tp.dds.entidades.Inscripcion;
import tp.dds.entidades.Mail;
import tp.dds.entidades.MailAdapter;
import tp.dds.entidades.MailSender;
import tp.dds.entidades.Partido;

public class BajaJugador extends InscripcionObserver {

	private Integer cantInscriptosAnterior;

	public BajaJugador(Partido partido, MailSender mailSender) {
		super(partido, mailSender);
		this.cantInscriptosAnterior = partido.cantInscriptos();
	}

	public void notificarNuevaInscripcion(Inscripcion inscripcion) {
		if (cantInscriptosAnterior == partido.maxJugadoresxPartido() && partido.cantInscriptos() < partido.maxJugadoresxPartido()){
			// TODO notificar Partido dejo de tener 10 jugadores al admin.
			Mail mail = MailAdapter.crearMail("sistema@ddsutn.com", partido.administrador().mail(), "Partido con menos de 10 jugadores", "El partido "+partido.fecha()+" dejo de tenre 10 jugadores");
			mailSender.sendMail(mail);
		}
		cantInscriptosAnterior = partido.cantInscriptos();
	}

}