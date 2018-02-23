package mz.com.venda.controller;


import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import mz.com.venda.implementacao.crud.ImplementacaoCrud;
import mz.com.venda.interfac.crud.InterfaceCrud;
import mz.com.venda.model.Cidade;
import mz.com.venda.repositorio.interfaces.RepositoryCidade;
import mz.com.venda.srv.interfaces.SrvCidade;

@Controller
public class CidadeController extends ImplementacaoCrud<Cidade> implements InterfaceCrud<Cidade> {


	private static final long serialVersionUID = 1L;
	
	@Resource
	private SrvCidade srvCidade;
	@Resource
	private RepositoryCidade repositoryCidade;
	
	
}
