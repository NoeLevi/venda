package mz.com.venda.utils.call;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public abstract class Messagens extends FacesContext implements Serializable {

	/**
		 * 
		 */
	private static final long serialVersionUID = 1L;

	public Messagens() {
		// TODO Auto-generated constructor stub
	}

	public static void responseOperation(StatusPersistencia statusPersistencia) {

		if (statusPersistencia != null && statusPersistencia.equals(statusPersistencia.SUCESSO)) {

			sucesso();
		} else if (statusPersistencia != null && statusPersistencia.equals(statusPersistencia.OBJETO_REFERENCIADO)) {
			msgSeverityFatal(StatusPersistencia.OBJETO_REFERENCIADO.toString());
		} else {
erroNaOperacao();
		}

	}

	public static void msg(String msg){
		
		if(facesContextValido()){
			
			getFacesContext().addMessage("msg", new FacesMessage(msg));
		}
		
	}
	public static void sucesso() {

		msgSeverityFatal(Constante.SUCESSO);

	}

	public static void erroNaOperacao() {
		if (facesContextValido()) {

			msgSeverityFatal(Constante.ERRO_NA_OPERACAO);
		}
	}

	public static FacesContext getFacesContext() {
		return FacesContext.getCurrentInstance();
	}

	private static boolean facesContextValido() {
		return getFacesContext() != null;
	}

	public static void msgSeverityInfo(String msg) {
		if (facesContextValido()) {
			getFacesContext().addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg));
		}
	}

	public static void msgSeverityError(String msg) {
		if (facesContextValido()) {
			getFacesContext().addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg));
		}
	}

	public static void msgSeverityWarm(String msg) {
		if (facesContextValido()) {
			getFacesContext().addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_WARN, msg, msg));
		}
	}

	public static void msgSeverityFatal(String msg) {
		if (facesContextValido()) {
			getFacesContext().addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_FATAL, msg, msg));
		}
	}

}
