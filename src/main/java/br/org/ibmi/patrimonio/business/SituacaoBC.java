package br.org.ibmi.patrimonio.business;

import br.gov.frameworkdemoiselle.lifecycle.Startup;
import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.template.DelegateCrud;
import br.gov.frameworkdemoiselle.transaction.Transactional;
import br.org.ibmi.patrimonio.domain.Situacao;
import br.org.ibmi.patrimonio.persistence.SituacaoDAO;

@BusinessController
public class SituacaoBC extends DelegateCrud<Situacao, Long, SituacaoDAO> {
	
	private static final long serialVersionUID = 1L;
	
	@Startup
	@Transactional
	public void load() {
		if (findAll().isEmpty()) {
			insert(new Situacao ("EM USO"));
			insert(new Situacao ("COM DEFEITO"));
			insert(new Situacao ("INSERVÍVEL"));
			
		}
	}
	
	public Situacao verificaSituacaoExiste(Situacao situacao){
		
		Situacao situacaoBD = getDelegate().
				verificaSituacaoExiste(situacao);
	
		return situacaoBD;
	}
	
	
}
