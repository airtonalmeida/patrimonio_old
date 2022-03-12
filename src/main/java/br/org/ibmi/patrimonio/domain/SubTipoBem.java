package br.org.ibmi.patrimonio.domain;



import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;



@Entity
@Table(name="sub_tipos_bens")
public class SubTipoBem implements Serializable {
	
private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="cod_sub_tipo_bem")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;
	
	@Column
	private String descricao;
	
	
	@ManyToOne(optional = true)
	@JoinColumn(name = "cod_tipo_bem", nullable=false)
	private TipoBem tipoBem;
	
	@ManyToOne(optional = true)
	@JoinColumn(name = "cod_classificacao", nullable=false)
	private Classificacao classificacao;
	
	public SubTipoBem() {
		super();
	}
	
	public SubTipoBem (String descricao) {
		this.descricao = descricao;
	}
		
	public SubTipoBem(String descricao, TipoBem tipoBem, Classificacao classificacao) {
		super();
		this.descricao = descricao;
		this.tipoBem = tipoBem;
		this.classificacao = classificacao;
	}

	public Long getCodigo() {
		return codigo;
	}
	
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public TipoBem getTipoBem() {
		return tipoBem;
	}

	public void setTipoBem(TipoBem tipoBem) {
		this.tipoBem = tipoBem;
	}
	
	public Classificacao getClassificacao() {
		return classificacao;
	}

	public void setClassificacao(Classificacao classificacao) {
		this.classificacao = classificacao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((classificacao == null) ? 0 : classificacao.hashCode());
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		result = prime * result
				+ ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((tipoBem == null) ? 0 : tipoBem.hashCode());
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
		SubTipoBem other = (SubTipoBem) obj;
		if (classificacao == null) {
			if (other.classificacao != null)
				return false;
		} else if (!classificacao.equals(other.classificacao))
			return false;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (tipoBem == null) {
			if (other.tipoBem != null)
				return false;
		} else if (!tipoBem.equals(other.tipoBem))
			return false;
		return true;
	}


	
}
