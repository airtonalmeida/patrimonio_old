package br.org.ibmi.patrimonio.business;

import br.gov.frameworkdemoiselle.annotation.Priority;
import br.gov.frameworkdemoiselle.lifecycle.Startup;
import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.template.DelegateCrud;
import br.gov.frameworkdemoiselle.transaction.Transactional;
import br.org.ibmi.patrimonio.domain.Papel;
import br.org.ibmi.patrimonio.persistence.PapelDAO;

@BusinessController
public class PapelBC extends DelegateCrud<Papel, Long, PapelDAO> {
	
	private static final long serialVersionUID = 1L;
	
	@Startup 
	@Priority(1)
	@Transactional
	public void load() {
		if (findAll().isEmpty()) {
			insert(new Papel ("ADMINISTRADOR"));
			insert(new Papel ("OPERADOR"));
			insert(new Papel ("CONSULTOR"));
			
		}
	}
	
}
