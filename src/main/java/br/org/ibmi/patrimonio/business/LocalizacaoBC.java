package br.org.ibmi.patrimonio.business;

import br.gov.frameworkdemoiselle.lifecycle.Startup;
import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.template.DelegateCrud;
import br.gov.frameworkdemoiselle.transaction.Transactional;
import br.org.ibmi.patrimonio.domain.Localizacao;
import br.org.ibmi.patrimonio.persistence.LocalizacaoDAO;


@BusinessController
public class LocalizacaoBC extends DelegateCrud<Localizacao, Long, LocalizacaoDAO> {
	
	private static final long serialVersionUID = 1L;
	
	@Startup
	@Transactional
	public void load() {
		if (findAll().isEmpty()) {
			insert(new Localizacao ("2º ANDAR/TEMPLO IBMI"));
			insert(new Localizacao ("SANTUÁRIO"));
			
			
		}
	}
	
	
	public Localizacao verificaLocalizacaoExiste(Localizacao localizacao){
		
		Localizacao localizacaoBD = getDelegate().
				verificaLocalizacaoExiste(localizacao);
	
		return localizacaoBD;
	}
	
}
