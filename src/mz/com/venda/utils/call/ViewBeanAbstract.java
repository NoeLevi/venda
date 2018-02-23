package mz.com.venda.utils.call;

import org.springframework.stereotype.Component;

@Component
public abstract class ViewBeanAbstract implements ActionViewPadrao {

	
	private static final long serialVersionUID = 1L;

	@Override
	public void limparLista() throws Exception {

	}

	@Override
	public String save() throws Exception {
		return null;
	}

	@Override
	public void saveNotReturn() throws Exception {

	}

	@Override
	public void saveEdit() throws Exception {

	}

	@Override
	public void excluir() throws Exception {

	}

	@Override
	public String ativar() throws Exception {
		return null;
	}

	@Override
	public String novo() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String editar() throws Exception {
		return null;
	}

	@Override
	public void setarVariaaveisNulas() throws Exception {

	}

	@Override
	public void consultarEntidade() throws Exception {

	}

	protected void error() throws Exception{
		  statusOperation(StatusPersistencia.ERRO);
		  }
	
	protected void sucesso() throws Exception{
		  statusOperation(StatusPersistencia.SUCESSO);
	}
	@Override
	public void statusOperation(StatusPersistencia statusPersistencia		) throws Exception {
Messagens.responseOperation(statusPersistencia);
	}

	@Override
	public String redirecionarNewEntidate() throws Exception {
		return null;
	}

	@Override
	public String redirecionarFindEntidate() throws Exception {
		return null;
	}

	@Override
	public void addMsg(String msg) throws Exception {
Messagens.msg(msg);
}

}
