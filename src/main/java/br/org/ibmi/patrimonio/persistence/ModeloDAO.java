package br.org.ibmi.patrimonio.persistence;

import java.util.List;
import javax.persistence.Query;
import br.gov.frameworkdemoiselle.stereotype.PersistenceController;
import br.gov.frameworkdemoiselle.template.JPACrud;
import br.org.ibmi.patrimonio.domain.Marca;
import br.org.ibmi.patrimonio.domain.Modelo;


@PersistenceController
public class ModeloDAO extends JPACrud<Modelo, Long> {

	private static final long serialVersionUID = 1L;
	
	@SuppressWarnings("unchecked")
	public List<Modelo> consultarModeloPorMarca(Marca marca){
		
		Query query = getEntityManager().createQuery("select m from Modelo m where m.marca = :marca");
		query.setParameter("marca", marca);
		List<Modelo> result = query.getResultList();
		return result;
	}
	
	public Modelo obterModelo (Long codModelo){
		
		Query query = getEntityManager().createQuery("select m from Modelo m where m.codigo = :codigo");
		query.setParameter("codigo", codModelo);
		Modelo result = (Modelo) query.getSingleResult();
		return result;
	}
	
	public Modelo verificaModeloExiste(Modelo modelo){
		Query query = getEntityManager().createQuery("select m from Modelo m where m.descricao = :modelo and m.marca = :marca");
		query.setParameter("modelo", modelo.getDescricao());
		query.setParameter("marca", modelo.getMarca());
		try {
			Modelo result = (Modelo)query.getSingleResult();
			return result;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			return null;
		}
	}
	

}
