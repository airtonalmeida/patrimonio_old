package br.org.ibmi.patrimonio.persistence;

import java.util.List;

import javax.persistence.Query;

import br.gov.frameworkdemoiselle.stereotype.PersistenceController;
import br.gov.frameworkdemoiselle.template.JPACrud;
import br.org.ibmi.patrimonio.domain.Base;
import br.org.ibmi.patrimonio.domain.Ministerio;

@PersistenceController
public class MinisterioDAO extends JPACrud<Ministerio, Long> {

	private static final long serialVersionUID = 1L;
	
	@SuppressWarnings("unchecked")
	public List<Ministerio> consultarMinisterioPorBase(Base base){
		
		Query query = getEntityManager().createQuery("select m from Ministerio m where m.base = :base");
		query.setParameter("base", base);
		List<Ministerio> result = query.getResultList();
		return result;
	}
	
	public Ministerio verificaMinisterioExiste(Ministerio ministerio){
		Query query = getEntityManager().createQuery("select m from Ministerio m where m.descricao = :ministerio");
		query.setParameter("ministerio", ministerio.getDescricao());
		try {
			Ministerio result = (Ministerio)query.getSingleResult();
			return result;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			return null;
		}
	}

	

}
