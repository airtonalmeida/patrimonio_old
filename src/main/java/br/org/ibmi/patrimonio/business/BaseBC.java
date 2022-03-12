package br.org.ibmi.patrimonio.business;

import br.gov.frameworkdemoiselle.lifecycle.Startup;
import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.template.DelegateCrud;
import br.gov.frameworkdemoiselle.transaction.Transactional;
import br.org.ibmi.patrimonio.domain.Base;
import br.org.ibmi.patrimonio.persistence.BaseDAO;

@BusinessController
public class BaseBC extends DelegateCrud<Base, Long, BaseDAO> {
	
	private static final long serialVersionUID = 1L;
	
	@Startup
	@Transactional
	public void load() {
		if (findAll().isEmpty()) {
			insert(new Base ("ADORAÇÃO"));
			insert(new Base ("MISSÕES"));
			insert(new Base ("COMUNHÃO"));
			insert(new Base ("DISCIPULADO"));
			insert(new Base ("SERVIÇO"));
		}
	}
	
	public Base verificaBaseExiste(Base base){
		
		Base baseBD = getDelegate().
				verificaBaseExiste(base);
	
		return baseBD;
	}
	
}
