package br.org.ibmi.patrimonio.domain;



import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name="responsaveis")
public class Responsavel implements Serializable {
	
private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="cod_responsavel")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;
	
	@Column
	private String nome;
	
	@OneToOne(optional = true)
	@JoinColumn(name = "cod_ministerio", nullable=false)
	private Ministerio ministerio;
	
	
	public Responsavel() {
		super();
	}
	
	public Responsavel(String nome) {
		super();
		this.nome = nome;
	}
	
	public Responsavel (String nome, Ministerio ministerio) {
		super();
		this.nome = nome;
		this.ministerio = ministerio;
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public Ministerio getMinisterio() {
		return ministerio;
	}

	public void setMinisterio(Ministerio ministerio) {
		this.ministerio = ministerio;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		result = prime * result
				+ ((ministerio == null) ? 0 : ministerio.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
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
		Responsavel other = (Responsavel) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		if (ministerio == null) {
			if (other.ministerio != null)
				return false;
		} else if (!ministerio.equals(other.ministerio))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}


	

}
