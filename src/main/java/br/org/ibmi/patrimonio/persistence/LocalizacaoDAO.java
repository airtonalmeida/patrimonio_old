package br.org.ibmi.patrimonio.persistence;

import javax.persistence.Query;
import br.gov.frameworkdemoiselle.stereotype.PersistenceController;
import br.gov.frameworkdemoiselle.template.JPACrud;
import br.org.ibmi.patrimonio.domain.Localizacao;

@PersistenceController
public class LocalizacaoDAO extends JPACrud<Localizacao, Long> {

	private static final long serialVersionUID = 1L;
	
	public Localizacao verificaLocalizacaoExiste(Localizacao localizacao){
		Query query = getEntityManager().createQuery("select l from Localizacao l where l.descricao = :localizacao");
		query.setParameter("localizacao", localizacao.getDescricao());
		try {
			Localizacao result = (Localizacao)query.getSingleResult();
			return result;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			return null;
		}
	}
	
	

}
