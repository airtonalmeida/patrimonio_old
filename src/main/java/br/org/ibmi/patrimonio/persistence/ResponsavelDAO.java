package br.org.ibmi.patrimonio.persistence;


import javax.persistence.Query;
import br.gov.frameworkdemoiselle.stereotype.PersistenceController;
import br.gov.frameworkdemoiselle.template.JPACrud;
import br.org.ibmi.patrimonio.domain.Ministerio;
import br.org.ibmi.patrimonio.domain.Responsavel;

@PersistenceController
public class ResponsavelDAO extends JPACrud<Responsavel, Long> {

	private static final long serialVersionUID = 1L;
	
	
	/*@SuppressWarnings("unchecked")
	public List<Responsavel> obterResponsavelMinisterio(Ministerio ministerio){
		
		Query query = getEntityManager().createQuery("from "+ getBeanClass().getName() + " where ministerio = :ministerio");
		query.setParameter("ministerio", ministerio);
		List<Responsavel> result = query.getResultList();
		return result;
		
	}*/
	
	public Responsavel obterResponsavelMinisterio(Ministerio ministerio){
		
		Query query = getEntityManager().createQuery("select r from Responsavel r where r.ministerio = :ministerio");
		query.setParameter("ministerio", ministerio);
		try {
			Responsavel result = (Responsavel) query.getSingleResult();
			return result;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			return null;
		}
		
	}
	
	public Responsavel verificaMinResponsavelExiste(Responsavel responsavel){
		Query query = getEntityManager().createQuery("select r from Responsavel r where r.ministerio = :ministerio");
		query.setParameter("ministerio", responsavel.getMinisterio());
		try {
			Responsavel resultMin = (Responsavel)query.getSingleResult();
			return resultMin;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			return null;
		}
	}
	
	public Responsavel verificaNomeResponsavelExiste(Responsavel responsavel){
		Query query = getEntityManager().createQuery("select r from Responsavel r where r.nome = :nome");
		query.setParameter("nome", responsavel.getNome());
		try {
			Responsavel resultNome = (Responsavel)query.getSingleResult();
			return resultNome;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			return null;
		}
	}
	
	public Responsavel verificaResponsavelUpdate(Responsavel responsavel){
		Query query = getEntityManager().createQuery("select r from Responsavel r where r.nome = :nome and r.ministerio = :ministerio");
		query.setParameter("nome", responsavel.getNome());
		query.setParameter("ministerio", responsavel.getMinisterio());
		try {
			Responsavel resultRes = (Responsavel)query.getSingleResult();
			return resultRes;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			return null;
		}
	}
	
	
	

}
