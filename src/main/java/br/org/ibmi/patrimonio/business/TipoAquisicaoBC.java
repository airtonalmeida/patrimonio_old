package br.org.ibmi.patrimonio.business;

import br.gov.frameworkdemoiselle.lifecycle.Startup;
import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.template.DelegateCrud;
import br.gov.frameworkdemoiselle.transaction.Transactional;
import br.org.ibmi.patrimonio.domain.TipoAquisicao;
import br.org.ibmi.patrimonio.persistence.TipoAquisicaoDAO;

@BusinessController
public class TipoAquisicaoBC extends DelegateCrud<TipoAquisicao, Long, TipoAquisicaoDAO> {
	
	private static final long serialVersionUID = 1L;
	
	@Startup
	@Transactional
	public void load() {
		if (findAll().isEmpty()) {
			insert(new TipoAquisicao ("COMPRA"));
			insert(new TipoAquisicao ("DOACAO"));
			
			
		}
	}
	
	
	public TipoAquisicao verificaTipoAquisicaoExiste(TipoAquisicao tipoAquisicao){
		
		TipoAquisicao tipoAquisicaoBD = getDelegate().
				verificaTipoAquisicaoExiste(tipoAquisicao);
	
		return tipoAquisicaoBD;
	}
	
}
