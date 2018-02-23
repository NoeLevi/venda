package mz.com.venda.utils.call;

public enum StatusPersistencia {
	
	ERRO("Erro"),SUCESSO("Sucesso"),
	OBJETO_REFERENCIADO("Esse Objecto nao pode ser apagado por possuir referencias ao mesmo");
	
	private String name;
	
	private StatusPersistencia(String s) {
		// TODO Auto-generated constructor stub
		this.name=s;
	}
@Override
public String toString() {
	// TODO Auto-generated method stub
	return name;
}
}
