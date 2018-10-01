package br.edu.ifpb.resteasyapp.entidade;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "tb_questao")
@Inheritance(strategy = InheritanceType.JOINED)
@NamedQuery(name = "Questao.getAll", query = "from Questao")
@XmlRootElement
public class Questao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_questao")
	protected int id;

	@Column(name = "fonte")
	protected String fonte;

	@Column(name = "descricao")
	protected String descricao;

	@Column(name = "enunciado")
	protected String enunciado;

	public Questao() {

	}

	public Questao(String fonte, String descricao, String enunciado) {
		super();
		this.fonte = fonte;
		this.descricao = descricao;
		this.enunciado = enunciado;
	}

	@XmlElement
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@XmlElement
	public String getFonte() {
		return fonte;
	}

	public void setFonte(String fonte) {
		this.fonte = fonte;
	}

	@XmlElement
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@XmlElement
	public String getEnunciado() {
		return enunciado;
	}

	public void setEnunciado(String enunciado) {
		this.enunciado = enunciado;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((enunciado == null) ? 0 : enunciado.hashCode());
		result = prime * result + ((fonte == null) ? 0 : fonte.hashCode());
		result = prime * result + id;
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
		Questao other = (Questao) obj;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (enunciado == null) {
			if (other.enunciado != null)
				return false;
		} else if (!enunciado.equals(other.enunciado))
			return false;
		if (fonte == null) {
			if (other.fonte != null)
				return false;
		} else if (!fonte.equals(other.fonte))
			return false;
		if (id != other.id)
			return false;
		return true;
	}

}
