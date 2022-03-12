package br.org.ibmi.patrimonio.persistence;

import javax.persistence.Query;

import br.gov.frameworkdemoiselle.stereotype.PersistenceController;
import br.gov.frameworkdemoiselle.template.JPACrud;
import br.org.ibmi.patrimonio.domain.TipoAquisicao;

@PersistenceController
public class TipoAquisicaoDAO extends JPACrud<TipoAquisicao, Long> {

	private static final long serialVersionUID = 1L;
	
	public TipoAquisicao verificaTipoAquisicaoExiste(TipoAquisicao tipoAquisicao){
		Query query = getEntityManager().createQuery("select ta from TipoAquisicao ta where ta.descricao = :tipoAquisicao");
		query.setParameter("tipoAquisicao", tipoAquisicao.getDescricao());
		try {
			TipoAquisicao result = (TipoAquisicao)query.getSingleResult();
			return result;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			return null;
		}
	}
	
	

}
