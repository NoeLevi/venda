package mz.com.venda.srv;

import org.springframework.beans.factory.annotation.Autowired;

import mz.com.venda.repositorio.interfaces.RepositoryLogin;
import mz.com.venda.srv.interfaces.SrvLogin;

public class SrvLoginImpl implements SrvLogin {

	
	private static final long serialVersionUID = 1L;

	@Autowired
	private RepositoryLogin repositoryLogin;
	
	@Override
	public boolean autentico(String login, String senha) throws Exception {
		return repositoryLogin.autentico(login, senha);
	}

}
