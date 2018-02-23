package mz.com.venda.acessos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public enum Permissao {

	ADMIN("ADMIN","Administrador"),
	USER("USER","Usuario Padrao");
	
	private String valor="";
	private String descricao="";
	
	
	private Permissao(String valor, String descricao) {
		// TODO Auto-generated constructor stub
	this.valor=valor;
	this.descricao=descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public String getValor() {
		return valor;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public void setValor(String valor) {
		this.valor = valor;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return getValor();
	}
	
	public List<Permissao> getListPermissao(){
		
		List<Permissao> lista = new ArrayList<Permissao>();
		
		for (Permissao permissao: Permissao.values()) {
			lista.add(permissao);
		}
		
		Collections.sort(lista,new Comparator<Permissao>(){

			@Override
			public int compare(Permissao o1, Permissao o2) {
				return new Integer(o1.ordinal()).compareTo(new Integer(o2.ordinal()));
			}
			
			
		});
		return lista;
	}
}
