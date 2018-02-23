package mz.com.venda.dao.implementacao;

import org.springframework.stereotype.Repository;

import mz.com.venda.implementacao.crud.ImplementacaoCrud;
import mz.com.venda.model.Cidade;
import mz.com.venda.repositorio.interfaces.RepositoryCidade;


@Repository
public class DaoCidade extends ImplementacaoCrud<Cidade> implements RepositoryCidade {


	private static final long serialVersionUID = 1L;

}
