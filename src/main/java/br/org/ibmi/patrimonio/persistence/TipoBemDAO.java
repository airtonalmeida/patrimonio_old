package br.org.ibmi.patrimonio.persistence;

import javax.persistence.Query;

import br.gov.frameworkdemoiselle.stereotype.PersistenceController;
import br.gov.frameworkdemoiselle.template.JPACrud;
import br.org.ibmi.patrimonio.domain.TipoBem;

@PersistenceController
public class TipoBemDAO extends JPACrud<TipoBem, Long> {

	private static final long serialVersionUID = 1L;
	
	public TipoBem verificaTipoBemExiste(TipoBem tipoBem){
		Query query = getEntityManager().createQuery("select tb from TipoBem tb where tb.descricao = :tipoBem");
		query.setParameter("tipoBem", tipoBem.getDescricao());
		try {
			TipoBem result = (TipoBem)query.getSingleResult();
			return result;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			return null;
		}
	}
	

}
