package mz.com.venda.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ForeignKey;
import org.hibernate.envers.DefaultRevisionEntity;
import org.hibernate.envers.RevisionEntity;

import mz.com.venda.listener.CustomListener;

@Entity
@Table(name = "REVINFO")
@RevisionEntity(CustomListener.class)
public class InformacaoRevisao extends DefaultRevisionEntity implements Serializable {

	@ManyToOne
	@ForeignKey(name = "usuario_fk")
	@JoinColumn(nullable = false, name = "usuario")
	private Usuario usuario;

	private static final long serialVersionUID = 1L;

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
