package br.org.ibmi.patrimonio.persistence;

import javax.persistence.Query;

import br.gov.frameworkdemoiselle.stereotype.PersistenceController;
import br.gov.frameworkdemoiselle.template.JPACrud;

import br.org.ibmi.patrimonio.domain.Base;

@PersistenceController
public class BaseDAO extends JPACrud<Base, Long> {

	private static final long serialVersionUID = 1L;
	
	public Base verificaBaseExiste(Base base){
		Query query = getEntityManager().createQuery("select b from Base b where b.descricao = :base");
		query.setParameter("base", base.getDescricao());
		try {
			Base result = (Base)query.getSingleResult();
			return result;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			return null;
		}
	}

}
