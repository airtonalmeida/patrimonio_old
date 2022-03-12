package br.org.ibmi.patrimonio.persistence;

import javax.persistence.Query;

import br.gov.frameworkdemoiselle.stereotype.PersistenceController;
import br.gov.frameworkdemoiselle.template.JPACrud;
import br.org.ibmi.patrimonio.domain.Situacao;


@PersistenceController
public class SituacaoDAO extends JPACrud<Situacao, Long> {

	private static final long serialVersionUID = 1L;
	
	public Situacao verificaSituacaoExiste(Situacao situacao){
		Query query = getEntityManager().createQuery("select s from Situacao s where s.descricao = :situacao");
		query.setParameter("situacao", situacao.getDescricao());
		try {
			Situacao result = (Situacao)query.getSingleResult();
			return result;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			return null;
		}
	}
	

}
