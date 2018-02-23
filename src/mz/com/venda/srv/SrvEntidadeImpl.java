package mz.com.venda.srv;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mz.com.venda.repositorio.interfaces.RepositoryEntidade;
import mz.com.venda.srv.interfaces.SrvEntidade;

@Service
public class SrvEntidadeImpl implements SrvEntidade{

	

	private static final long serialVersionUID = 1L;

	@Autowired
	private RepositoryEntidade repositoryEntidade;
	
	@Override
	public Date getUltimoAcessoEntidadeLogada(String name) {
		return repositoryEntidade.getUltimoAcessoEntidadeLogada(name);
	}

	@Override
	public void updateUltimoAcessoUser(String login) {
		repositoryEntidade.updateUltimoAcessoUser(login);		
	}

	@Override
	public boolean existeUsuario(String user_login) {
		return repositoryEntidade.existeUsuario(user_login);
	}
	}
