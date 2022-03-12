package br.org.ibmi.patrimonio.persistence;

import javax.persistence.Query;

import br.gov.frameworkdemoiselle.stereotype.PersistenceController;
import br.gov.frameworkdemoiselle.template.JPACrud;
import br.org.ibmi.patrimonio.domain.Marca;

@PersistenceController
public class MarcaDAO extends JPACrud<Marca, Long> {

	private static final long serialVersionUID = 1L;
	
	public Marca verificaMarcaExiste(Marca marca){
		Query query = getEntityManager().createQuery("select m from Marca m where m.descricao = :marca");
		query.setParameter("marca", marca.getDescricao());
		try {
			Marca result = (Marca)query.getSingleResult();
			return result;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			return null;
		}
	}
	

}
