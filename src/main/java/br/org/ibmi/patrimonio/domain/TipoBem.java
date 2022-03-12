package br.org.ibmi.patrimonio.domain;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="tipos_bens")
public class TipoBem implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="cod_tipo_bem")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;
	
	@Column
	private String descricao;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "cod_tipo_bem")
	private List<SubTipoBem> subTiposBens = new ArrayList<SubTipoBem>();
	
	public TipoBem() {
		super();
	}
	
	public TipoBem (String descricao) {
		this.descricao = descricao;
	}
	
	public TipoBem(String descricao, List<SubTipoBem> subTiposBens) {
		super();
		this.descricao = descricao;
		this.subTiposBens = subTiposBens;
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

	public List<SubTipoBem> getSubTiposBens() {
		return subTiposBens;
	}

	public void setSubTiposBens(List<SubTipoBem> subTiposBens) {
		this.subTiposBens = subTiposBens;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		result = prime * result
				+ ((descricao == null) ? 0 : descricao.hashCode());
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
		TipoBem other = (TipoBem) obj;
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
		return true;
	}
	
	


}
