package br.edu.ifpb.resteasyapp.entidade;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
@Table(name = "tb_alternativa")
@NamedQuery(name = "Alternativa.getAll", query = "from Alternativa")
public class Alternativa {
	
	@Id
	@Column(name = "cod_a")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int cod_a;
	
	@Column(name = "enunciado")
	private String enunciado;
	
	@Column(name = "resposta")
	private boolean resposta;
	
	public Alternativa(){
		
	}
	
	public Alternativa(int cod_a, String enunciado, boolean resposta) {
		super();
		this.cod_a = cod_a;
		this.enunciado = enunciado;
		this.resposta = resposta;
	}

	@XmlElement
	public int getCod_a() {
		return cod_a;
	}

	public void setCod_a(int cod_a) {
		this.cod_a = cod_a;
	}

	@XmlElement
	public String getEnunciado() {
		return enunciado;
	}

	public void setEnunciado(String enunciado) {
		this.enunciado = enunciado;
	}

	@XmlElement
	public boolean isResposta() {
		return resposta;
	}

	public void setResposta(boolean resposta) {
		this.resposta = resposta;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + cod_a;
		result = prime * result + ((enunciado == null) ? 0 : enunciado.hashCode());
		result = prime * result + (resposta ? 1231 : 1237);
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
		Alternativa other = (Alternativa) obj;
		if (cod_a != other.cod_a)
			return false;
		if (enunciado == null) {
			if (other.enunciado != null)
				return false;
		} else if (!enunciado.equals(other.enunciado))
			return false;
		if (resposta != other.resposta)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Alternativa [enunciado=" + enunciado + ", resposta=" + resposta + "]";
	}
}
