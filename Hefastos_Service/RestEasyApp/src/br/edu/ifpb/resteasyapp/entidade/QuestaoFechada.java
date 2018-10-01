package br.edu.ifpb.resteasyapp.entidade;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;


@Entity
@Table(name = "tb_questao_fechada")
@NamedQuery(name = "QuestaoFechada.getAll", query = "from QuestaoFechada")
@PrimaryKeyJoinColumn(name = "cod_q")
@XmlRootElement
public class QuestaoFechada extends Questao {
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Assunto assunto;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Alternativa> alternativas;
	
	public QuestaoFechada(){
		
	}
	
	public QuestaoFechada(String fonte, String descricao, String enunciado) {
	        super(fonte, descricao, enunciado);
	}

	public Assunto getAssunto() {
		return assunto;
	}

	public void setAssunto(Assunto assunto) {
		this.assunto = assunto;
	}

	public List<Alternativa> getAlternativas() {
		return alternativas;
	}

	public void setAlternativas(List<Alternativa> alternativas) {
		this.alternativas = alternativas;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((alternativas == null) ? 0 : alternativas.hashCode());
		result = prime * result + ((assunto == null) ? 0 : assunto.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		QuestaoFechada other = (QuestaoFechada) obj;
		if (alternativas == null) {
			if (other.alternativas != null)
				return false;
		} else if (!alternativas.equals(other.alternativas))
			return false;
		if (assunto == null) {
			if (other.assunto != null)
				return false;
		} else if (!assunto.equals(other.assunto))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "QuestaoFechada [assunto=" + assunto + ", alternativas=" + alternativas + "]";
	}

}
