package br.org.ibmi.patrimonio.business;

import br.gov.frameworkdemoiselle.lifecycle.Startup;
import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.template.DelegateCrud;
import br.gov.frameworkdemoiselle.transaction.Transactional;
import br.org.ibmi.patrimonio.domain.TipoBem;
import br.org.ibmi.patrimonio.persistence.TipoBemDAO;

@BusinessController
public class TipoBemBC extends DelegateCrud<TipoBem, Long, TipoBemDAO> {
	
	private static final long serialVersionUID = 1L;
	
	@Startup
	@Transactional
	public void load() {
		if (findAll().isEmpty()) {
			insert(new TipoBem ("COMPUTADOR"));
			insert(new TipoBem ("MÓVEL"));
			insert(new TipoBem ("VEÍCULO"));
			insert(new TipoBem ("IMÓVEL"));
					
		}
	}
	
	
	public TipoBem verificaTipoBemExiste(TipoBem tipoBem){
		
		TipoBem tipoBemBD = getDelegate().
				verificaTipoBemExiste(tipoBem);
	
		return tipoBemBD;
	}
	
}
