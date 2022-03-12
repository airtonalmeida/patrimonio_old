package br.org.ibmi.patrimonio.persistence;

import java.util.List;
import javax.persistence.Query;
import br.gov.frameworkdemoiselle.stereotype.PersistenceController;
import br.gov.frameworkdemoiselle.template.JPACrud;
import br.org.ibmi.patrimonio.domain.SubTipoBem;
import br.org.ibmi.patrimonio.domain.TipoBem;

@PersistenceController
public class SubTipoBemDAO extends JPACrud<SubTipoBem, Long> {

	private static final long serialVersionUID = 1L;
	
	@SuppressWarnings("unchecked")
	public List<SubTipoBem> consultarSubTipoBemPorTipoBem(TipoBem tipoBem){
		
		Query query = getEntityManager().createQuery("select stb from SubTipoBem stb where stb.tipoBem = :tipoBem");
		query.setParameter("tipoBem", tipoBem);
		List<SubTipoBem> result = query.getResultList();
		return result;
	}
	
	public SubTipoBem verificaSubTipoBemExiste(SubTipoBem subTipoBem){
		Query query = getEntityManager().createQuery("select stb from SubTipoBem stb where stb.descricao = :subTipoBem and stb.tipoBem = :tipoBem and stb.classificacao = :classificacao");
		query.setParameter("subTipoBem", subTipoBem.getDescricao());
		query.setParameter("tipoBem", subTipoBem.getTipoBem());
		query.setParameter("classificacao", subTipoBem.getClassificacao());
		try {
			SubTipoBem result = (SubTipoBem)query.getSingleResult();
			return result;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			return null;
		}
	}
	

}
