package mz.com.venda.excepcoes;

import java.util.Iterator;
import java.util.Map;

import javax.faces.FacesException;
import javax.faces.application.FacesMessage;
import javax.faces.application.NavigationHandler;
import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerWrapper;
import javax.faces.context.FacesContext;
import javax.faces.event.ExceptionQueuedEvent;
import org.primefaces.context.RequestContext;
import javax.faces.event.ExceptionQueuedEventContext;

import org.hibernate.SessionFactory;

import mz.com.venda.hibernate.session.HibernateUtil;

public class CustomExceptionHandler extends ExceptionHandlerWrapper {

	private ExceptionHandler wrapper;

	final FacesContext facesContext = FacesContext.getCurrentInstance();

	final Map<String, Object> requestMap = facesContext.getExternalContext().getRequestMap();

	final NavigationHandler navigableHandler = facesContext.getApplication().getNavigationHandler();

	public CustomExceptionHandler(ExceptionHandler exceptionHandler) {
		this.wrapper = exceptionHandler;
	}

	// sobrescreve o metodo exceptionHandler que retorna a pilha de excecoes
	@Override
	public ExceptionHandler getWrapped() {
		return wrapper;
	}

	// sobrescreve o metodo handle que e responsavel por maniplar as excecoes
	// do Jsf
	@Override
	public void handle() throws FacesException {

		final Iterator<ExceptionQueuedEvent> iterator = getUnhandledExceptionQueuedEvents().iterator();

		while (iterator.hasNext()) {
			ExceptionQueuedEvent event = iterator.next();
			ExceptionQueuedEventContext context = (ExceptionQueuedEventContext) event.getSource();

			// recuperar a excecao doc contexto

			Throwable exception = context.getException();
			// aqui trabalhamos a exceccao
			try {
				requestMap.put("exceptionMessage", exception.getMessage());
				if (exception != null && exception.getMessage() != null
						&& exception.getMessage().indexOf("ConstraintViolationException") != -1) {
					FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_WARN,
							"Registro nao pode ser removido por " + "estar associado ", ""));
				} else if (exception != null && exception.getMessage() != null
						&& exception.getMessage().indexOf("org.hibernate.StaleObjectStateException") != -1) {
					FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Registro foi atualizado ou excluido por outro usuario" + "consulte novamente ", ""));

				} else {
					// Avisa o usuario do erro
					FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_FATAL,
							"O sistema se recuperou de um erro inesperado", ""));

					// Tranquiliza o usuario para que ele continue usando sistea
					FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_INFO,
							"Voce pode continuar usando o sistema normalmente", ""));

					FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_FATAL,
							"O erro foi causado por: \n" + exception.getMessage(), ""));

					
					//primesface
					
					// esse alert apenas e exivido se a paginao nao redirecionar
					RequestContext.getCurrentInstance()
							.execute("alert('O sistema se recuperou de um erro inesperado')");
					
					RequestContext.getCurrentInstance()
					.showMessageInDialog(new FacesMessage(FacesMessage.SEVERITY_INFO,"Erro",
							"O sistema se recuperou de um erro inesperado"));
					
					//Redirecciona para a pagina de erro
					navigableHandler.handleNavigation(facesContext, null, 
							"/error/error.jsf?faces-redirect=true&expired=true");
					
					

				}
				//Renderiza a pagina de erro se exibe as mensagens
				facesContext.renderResponse();
			} finally {
				SessionFactory sf = HibernateUtil.getSessionFactory();
				if (sf.getCurrentSession().getTransaction().isActive()) {
					sf.getCurrentSession().getTransaction().rollback();
				}
				// imprime o erro no console
				exception.printStackTrace();
				iterator.remove();

			}
			getWrapped().handle();

		}

	}

}
