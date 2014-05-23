package tp.dds.observer;

import tp.dds.dominio.Inscripcion;
import tp.dds.dominio.Partido;
import tp.dds.interfaces.MailSender;

public class BajaJugador extends InscripcionObserver {

	private Integer cantInscriptosAnterior;

	public BajaJugador(Partido partido, MailSender mailSender) {
		super(partido, mailSender);
		this.cantInscriptosAnterior = partido.cantInscriptos();
	}

	public void notificarNuevaInscripcion(Inscripcion inscripcion) {
		if (cantInscriptosAnterior == partido.maxJugadoresxPartido() && partido.cantInscriptos() < partido.maxJugadoresxPartido()){
			// notificar Partido dejo de tener 10 jugadores al admin.
			enviarMail("sistema@ddsutn.com", partido.administrador().mail(), "Partido con menos de 10 jugadores", "El partido de la fecha "+partido.fecha()+" dejo de tenre 10 jugadores");
		}
		cantInscriptosAnterior = partido.cantInscriptos();
	}

}
