package mz.com.venda.srv;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import mz.com.venda.repositorio.interfaces.RepositoryCidade;
import mz.com.venda.srv.interfaces.SrvCidade;

@Service
public class SrvCidadeImpl implements SrvCidade {

	private static final long serialVersionUID = 1L;

	@Resource
	private RepositoryCidade repositoryCidade;
}
