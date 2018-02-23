package mz.com.venda.bean.view;

import java.io.Serializable;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import mz.com.venda.controller.SessionController;
import mz.com.venda.geral.controller.UsuarioController;
import mz.com.venda.model.Usuario;

@Scope(value = "session")
@Component(value = "contextoBean")
public class ContextoBean implements Serializable {

	@Autowired
	private UsuarioController usuarioController;

	@Autowired
	private SessionController sessionController;

	private static final long serialVersionUID = 1L;
	private static final String USER_LOGADO_SESSAO = "userLogadoSessao";

	/**
	 * retorna a informacao do usuario logado
	 * 
	 * @return informacao do usuario
	 */
	public Authentication getAuthentication() {

		return SecurityContextHolder.getContext().getAuthentication();
	}

	public Usuario getUserLogado() throws Exception {
		Usuario usuario = (Usuario) getExternalContext().getSessionMap().get(USER_LOGADO_SESSAO);

		if (usuario == null || (usuario != null && !usuario.getNomeUsuario().equals(getUserPricipal()))) {

			if (getAuthentication().isAuthenticated()) {

				usuarioController.updateUltimoAcessoUser(getAuthentication().getName());
				usuario = usuarioController.findUserLogado(getAuthentication().getName());
				getExternalContext().getSessionMap().put(USER_LOGADO_SESSAO, usuario);
				sessionController.addSession(usuario.getNomeUsuario(),
						(HttpSession) getExternalContext().getSession(true));

			}
		}

		return usuario;
	}

	private String getUserPricipal() {
		return getExternalContext().getUserPrincipal().getName();
	}

	public ExternalContext getExternalContext() {

		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext externalContext = context.getExternalContext();
		return externalContext;
	}

	public boolean possuiAcesso(String...acessos) {
		
		for(String acesso:acessos){
			
			for(GrantedAuthority authority: getAuthentication().getAuthorities()){
				
				if(authority.getAuthority().trim().equals(acesso.trim())){
					
					return true;
				}
			}
		}
		return false;
	}

}
