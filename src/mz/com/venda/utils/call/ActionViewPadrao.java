package mz.com.venda.utils.call;

import java.io.Serializable;

import javax.annotation.PostConstruct;

public interface ActionViewPadrao extends Serializable {

	abstract void limparLista() throws Exception;
	
	abstract String save() throws Exception;
	
	abstract void saveNotReturn() throws Exception;
	
	abstract void saveEdit() throws Exception;

	abstract void excluir() throws Exception;
	
	abstract String ativar() throws Exception;
	
	/*
	 * @PostContruct realiza inicializacao de metodos, vaores ou variaveis
	 *throws Exception
	 * */
	@PostConstruct
	abstract String novo() throws Exception;
	
	abstract String editar()throws Exception;

	abstract void setarVariaaveisNulas() throws Exception;
	
	abstract void consultarEntidade() throws Exception;
	
	abstract void statusOperation(StatusPersistencia	a)throws Exception;
	
	abstract String redirecionarNewEntidate()	throws Exception;
	
	abstract String redirecionarFindEntidate()	throws Exception;
	
	abstract void addMsg(String msg)	throws Exception;



}