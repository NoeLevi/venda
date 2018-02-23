package mz.com.venda.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.envers.Audited;

@Entity
@Table(name="usuario")
@Audited
public class Usuario implements Serializable{
	
	
	private static final long serialVersionUID = 1L;

	@Id
	private Long codigo;
	
	private String nomeUsuario=null;
	
	private String senhaUsuario=null;
	
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date userUltimoacesso;

	
	private boolean inactivo=false;
	
	

	public void setUserUltimoacesso(Date userUltimoacesso) {
		this.userUltimoacesso = userUltimoacesso;
	}
	
	public Date getUserUltimoacesso() {
		return userUltimoacesso;
	}
	public void setInactivo(boolean inactivo) {
		this.inactivo = inactivo;
	}
	
	public boolean getInativo(){
		return inactivo;
	}


	public String getNomeUsuario() {
		return nomeUsuario;
	}
	

	public Long getCodigo() {
		return codigo;
	}


	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}


	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public String getSenhaUsuario() {
		return senhaUsuario;
	}

	public void setSenhaUsuario(String senhaUsuario) {
		this.senhaUsuario = senhaUsuario;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}
	
	
}
