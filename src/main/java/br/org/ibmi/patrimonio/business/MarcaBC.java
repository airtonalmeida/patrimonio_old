package br.org.ibmi.patrimonio.business;

import br.gov.frameworkdemoiselle.lifecycle.Startup;
import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.template.DelegateCrud;
import br.gov.frameworkdemoiselle.transaction.Transactional;
import br.org.ibmi.patrimonio.domain.Marca;
import br.org.ibmi.patrimonio.persistence.MarcaDAO;

@BusinessController
public class MarcaBC extends DelegateCrud<Marca, Long, MarcaDAO> {
	
	private static final long serialVersionUID = 1L;
	
	@Startup
	@Transactional
	public void load() {
		if (findAll().isEmpty()) {
			insert(new Marca ("LOGIN"));
			insert(new Marca ("LG"));
			insert(new Marca ("SAMSUNG"));
			insert(new Marca ("DELL"));
			insert(new Marca ("TRAMONTINA"));
		}
	}
	
	public Marca verificaMarcaExiste(Marca marca){
		
		Marca marcaBD = getDelegate().
				verificaMarcaExiste(marca);
	
		return marcaBD;
	}
	
	
}
