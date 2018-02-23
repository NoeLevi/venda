package mz.com.venda.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.ForeignKey;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

import com.sun.istack.internal.NotNull;

import mz.com.venda.annotation.IdentificaCampo;

@Audited
@Entity
@Table(name="provincia")
@SequenceGenerator(name="provincia_seq",sequenceName="provincia_seq",allocationSize=1,initialValue=1)
public class Provincia  implements Serializable{

	
	private static final long serialVersionUID = 1L;

	@IdentificaCampo(descricaoCampo="Codigo",campoConsulta="prov_id")
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="provincia_seq")
	private Long prov_id;
	
	@IdentificaCampo(descricaoCampo="Provincia",campoConsulta="prov_nome")
	@Column(length=100,nullable=false)
	private String prov_nome;
	
	@NotAudited
	@OneToMany(mappedBy="provincia", orphanRemoval=false)
	@Cascade(value={org.hibernate.annotations.CascadeType.SAVE_UPDATE,CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REFRESH})
	private List<Cidade> cidade = new ArrayList<Cidade>();
	
	
	@Basic
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="pais")
	@NotNull
	@ForeignKey(name="pais_fk")
	private Pais pais = new Pais();
	
	@Version
	@Column(name="versionNum")
	private int versionNum;

	public Long getProv_id() {
		return prov_id;
	}

	public void setProv_id(Long prov_id) {
		this.prov_id = prov_id;
	}

	public String getProv_nome() {
		return prov_nome;
	}

	public void setProv_nome(String prov_nome) {
		this.prov_nome = prov_nome;
	}

	public List<Cidade> getCidade() {
		return cidade;
	}

	public void setCidade(List<Cidade> cidade) {
		this.cidade = cidade;
	}

	public Pais getPais() {
		return pais;
	}

	public void setPais(Pais pais) {
		this.pais = pais;
	}

	public int getVersionNum() {
		return versionNum;
	}

	public void setVersionNum(int versionNum) {
		this.versionNum = versionNum;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((prov_id == null) ? 0 : prov_id.hashCode());
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
		Provincia other = (Provincia) obj;
		if (prov_id == null) {
			if (other.prov_id != null)
				return false;
		} else if (!prov_id.equals(other.prov_id))
			return false;
		return true;
	}
	
	


}
