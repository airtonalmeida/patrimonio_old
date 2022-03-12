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
@Table(name="bens")
public class Bem implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="cod_bem")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;
	
	@Column
	private String tombo;
	
	@Column
	private Integer tomboInteger;
		
	@Column
	private String numeroNotaFiscal;
	
	@Column
	private String descricao;
	
	@Column
	private String dataCompra;
	
	@Column
	private Double valorHistorico;
	
	@Column
	private String valorHistoricoString;
		
	@Column
	private String valorAtualString;
	
	@ManyToOne
	@JoinColumn(name = "cod_tipo_bem")
	private TipoBem tipoBem;
	
	@ManyToOne
	@JoinColumn(name = "cod_sub_tipo_bem")
	private SubTipoBem subTipoBem;
	
	@ManyToOne
	@JoinColumn(name = "cod_base")
	private Base base;
	
	@ManyToOne
	@JoinColumn(name = "cod_ministerio")
	private Ministerio ministerio;
	
	@ManyToOne
	@JoinColumn(name = "cod_marca")
	private Marca marca;
	
	@ManyToOne
	@JoinColumn(name = "cod_modelo")
	private Modelo modelo;
		
	@ManyToOne
	@JoinColumn(name = "cod_tipo_aquisicao")
	private TipoAquisicao tipoAquisicao;
		
	@ManyToOne
	@JoinColumn(name = "cod_responsavel")
	private Responsavel responsavel;
		
	@ManyToOne
	@JoinColumn(name = "cod_situacao")
	private Situacao situacao;
	
	@ManyToOne
	@JoinColumn(name = "cod_localizacao")
	private Localizacao localizacao;
	
	@Column
	private String nota;
	
	@Column
	private Boolean valorEstavel;
	
	public Bem() {
		super();
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getTombo() {
		return tombo;
	}

	public void setTombo(String tombo) {
		this.tombo = tombo;
	}
	
	public Integer getTomboInteger() {
		return tomboInteger;
	}

	public void setTomboInteger(Integer tomboInteger) {
		this.tomboInteger = tomboInteger;
	}
	
	public String getNumeroNotaFiscal() {
		return numeroNotaFiscal;
	}

	public void setNumeroNotaFiscal(String numeroNotaFiscal) {
		this.numeroNotaFiscal = numeroNotaFiscal;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getDataCompra() {
		return dataCompra;
	}

	public void setDataCompra(String dataCompra) {
		this.dataCompra = dataCompra;
	}
	
	public Double getValorHistorico() {
		return valorHistorico;
	}

	public void setValorHistorico(Double valorHistorico) {
		this.valorHistorico = valorHistorico;
	}
	
	public String getValorAtualString() {
		return valorAtualString;
	}

	public void setValorAtualString(String valorAtualString) {
		this.valorAtualString = valorAtualString;
	}

	public TipoBem getTipoBem() {
		return tipoBem;
	}

	public void setTipoBem(TipoBem tipoBem) {
		this.tipoBem = tipoBem;
	}

	public SubTipoBem getSubTipoBem() {
		return subTipoBem;
	}

	public void setSubTipoBem(SubTipoBem subTipoBem) {
		this.subTipoBem = subTipoBem;
	}

	public Base getBase() {
		return base;
	}

	public void setBase(Base base) {
		this.base = base;
	}

	public Ministerio getMinisterio() {
		return ministerio;
	}

	public void setMinisterio(Ministerio ministerio) {
		this.ministerio = ministerio;
	}

	public TipoAquisicao getTipoAquisicao() {
		return tipoAquisicao;
	}

	public void setTipoAquisicao(TipoAquisicao tipoAquisicao) {
		this.tipoAquisicao = tipoAquisicao;
	}

	public Responsavel getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(Responsavel responsavel) {
		this.responsavel = responsavel;
	}

	public Situacao getSituacao() {
		return situacao;
	}

	public void setSituacao(Situacao situacao) {
		this.situacao = situacao;
	}

	public Marca getMarca() {
		return marca;
	}

	public void setMarca(Marca marca) {
		this.marca = marca;
	}

	public Modelo getModelo() {
		return modelo;
	}

	public void setModelo(Modelo modelo) {
		this.modelo = modelo;
	}

	public String getValorHistoricoString() {
		return valorHistoricoString;
	}

	public void setValorHistoricoString(String valorHistoricoString) {
		this.valorHistoricoString = valorHistoricoString;
	}
	
	public Localizacao getLocalizacao() {
		return localizacao;
	}

	public void setLocalizacao(Localizacao localizacao) {
		this.localizacao = localizacao;
	}
	
	public String getNota() {
		return nota;
	}

	public void setNota(String nota) {
		this.nota = nota;
	}
	
	public Boolean getValorEstavel() {
		return valorEstavel;
	}

	public void setValorEstavel(Boolean valorEstavel) {
		this.valorEstavel = valorEstavel;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((base == null) ? 0 : base.hashCode());
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		result = prime * result
				+ ((dataCompra == null) ? 0 : dataCompra.hashCode());
		result = prime * result
				+ ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result
				+ ((localizacao == null) ? 0 : localizacao.hashCode());
		result = prime * result + ((marca == null) ? 0 : marca.hashCode());
		result = prime * result
				+ ((ministerio == null) ? 0 : ministerio.hashCode());
		result = prime * result + ((modelo == null) ? 0 : modelo.hashCode());
		result = prime * result + ((nota == null) ? 0 : nota.hashCode());
		result = prime
				* result
				+ ((numeroNotaFiscal == null) ? 0 : numeroNotaFiscal.hashCode());
		result = prime * result
				+ ((responsavel == null) ? 0 : responsavel.hashCode());
		result = prime * result
				+ ((situacao == null) ? 0 : situacao.hashCode());
		result = prime * result
				+ ((subTipoBem == null) ? 0 : subTipoBem.hashCode());
		result = prime * result
				+ ((tipoAquisicao == null) ? 0 : tipoAquisicao.hashCode());
		result = prime * result + ((tipoBem == null) ? 0 : tipoBem.hashCode());
		result = prime * result + ((tombo == null) ? 0 : tombo.hashCode());
		result = prime * result
				+ ((tomboInteger == null) ? 0 : tomboInteger.hashCode());
		result = prime
				* result
				+ ((valorAtualString == null) ? 0 : valorAtualString.hashCode());
		result = prime * result
				+ ((valorEstavel == null) ? 0 : valorEstavel.hashCode());
		result = prime * result
				+ ((valorHistorico == null) ? 0 : valorHistorico.hashCode());
		result = prime
				* result
				+ ((valorHistoricoString == null) ? 0 : valorHistoricoString
						.hashCode());
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
		Bem other = (Bem) obj;
		if (base == null) {
			if (other.base != null)
				return false;
		} else if (!base.equals(other.base))
			return false;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		if (dataCompra == null) {
			if (other.dataCompra != null)
				return false;
		} else if (!dataCompra.equals(other.dataCompra))
			return false;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (localizacao == null) {
			if (other.localizacao != null)
				return false;
		} else if (!localizacao.equals(other.localizacao))
			return false;
		if (marca == null) {
			if (other.marca != null)
				return false;
		} else if (!marca.equals(other.marca))
			return false;
		if (ministerio == null) {
			if (other.ministerio != null)
				return false;
		} else if (!ministerio.equals(other.ministerio))
			return false;
		if (modelo == null) {
			if (other.modelo != null)
				return false;
		} else if (!modelo.equals(other.modelo))
			return false;
		if (nota == null) {
			if (other.nota != null)
				return false;
		} else if (!nota.equals(other.nota))
			return false;
		if (numeroNotaFiscal == null) {
			if (other.numeroNotaFiscal != null)
				return false;
		} else if (!numeroNotaFiscal.equals(other.numeroNotaFiscal))
			return false;
		if (responsavel == null) {
			if (other.responsavel != null)
				return false;
		} else if (!responsavel.equals(other.responsavel))
			return false;
		if (situacao == null) {
			if (other.situacao != null)
				return false;
		} else if (!situacao.equals(other.situacao))
			return false;
		if (subTipoBem == null) {
			if (other.subTipoBem != null)
				return false;
		} else if (!subTipoBem.equals(other.subTipoBem))
			return false;
		if (tipoAquisicao == null) {
			if (other.tipoAquisicao != null)
				return false;
		} else if (!tipoAquisicao.equals(other.tipoAquisicao))
			return false;
		if (tipoBem == null) {
			if (other.tipoBem != null)
				return false;
		} else if (!tipoBem.equals(other.tipoBem))
			return false;
		if (tombo == null) {
			if (other.tombo != null)
				return false;
		} else if (!tombo.equals(other.tombo))
			return false;
		if (tomboInteger == null) {
			if (other.tomboInteger != null)
				return false;
		} else if (!tomboInteger.equals(other.tomboInteger))
			return false;
		if (valorAtualString == null) {
			if (other.valorAtualString != null)
				return false;
		} else if (!valorAtualString.equals(other.valorAtualString))
			return false;
		if (valorEstavel == null) {
			if (other.valorEstavel != null)
				return false;
		} else if (!valorEstavel.equals(other.valorEstavel))
			return false;
		if (valorHistorico == null) {
			if (other.valorHistorico != null)
				return false;
		} else if (!valorHistorico.equals(other.valorHistorico))
			return false;
		if (valorHistoricoString == null) {
			if (other.valorHistoricoString != null)
				return false;
		} else if (!valorHistoricoString.equals(other.valorHistoricoString))
			return false;
		return true;
	}

	


	

	
	
	

	

}
