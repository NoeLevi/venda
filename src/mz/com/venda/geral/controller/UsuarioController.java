package mz.com.venda.geral.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import mz.com.venda.implementacao.crud.ImplementacaoCrud;
import mz.com.venda.interfac.crud.InterfaceCrud;
import mz.com.venda.model.Usuario;
import mz.com.venda.srv.interfaces.SrvEntidade;

@Controller
public class UsuarioController extends ImplementacaoCrud<Usuario> implements InterfaceCrud<Usuario> {

	@Autowired
	private SrvEntidade srvEntidade;

	
	private static final long serialVersionUID = 1L;

	public Usuario findUserLogado(String userLogado)throws Exception{
		return super.findInuqueByProperty(Usuario.class,userLogado, "nomeUsuario", "and inactivo is false");
		
	}
	
	public Date getUltimoAcessoEntidadeLogada(String login){
		
		return srvEntidade.getUltimoAcessoEntidadeLogada(login);
	}
	
	public void updateUltimoAcessoUser(String name) {
		srvEntidade.updateUltimoAcessoUser(name);
	}
}
