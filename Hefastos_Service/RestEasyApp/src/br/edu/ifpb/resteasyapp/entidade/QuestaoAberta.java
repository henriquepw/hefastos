package br.edu.ifpb.resteasyapp.entidade;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "tb_questao_aberta")
@PrimaryKeyJoinColumn(name = "cod_q")
@NamedQuery(name = "QuestaoAberta.getAll", query = "from QuestaoAberta")
@XmlRootElement
public class QuestaoAberta extends Questao {
	
	@Column(name = "resposta")
	private String resposta;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Assunto assunto;
	
	public QuestaoAberta(){
		
	}
	
	public QuestaoAberta(String fonte, String descricao, String enunciado, String resposta) {
        super(fonte, descricao, enunciado);
        this.resposta = resposta;
    }
	
	@XmlElement
    public String getResposta() {
        return resposta;
    }

    public void setResposta(String resposta) {
        this.resposta = resposta;
    }

	public Assunto getAssunto() {
		return assunto;
	}

	public void setAssunto(Assunto assunto) {
		this.assunto = assunto;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((assunto == null) ? 0 : assunto.hashCode());
		result = prime * result + ((resposta == null) ? 0 : resposta.hashCode());
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
		QuestaoAberta other = (QuestaoAberta) obj;
		if (assunto == null) {
			if (other.assunto != null)
				return false;
		} else if (!assunto.equals(other.assunto))
			return false;
		if (resposta == null) {
			if (other.resposta != null)
				return false;
		} else if (!resposta.equals(other.resposta))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "QuestaoAberta [resposta=" + resposta + ", assunto=" + assunto + "]";
	}

}
