package br.org.ibmi.patrimonio.business;

import br.gov.frameworkdemoiselle.lifecycle.Startup;
import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.template.DelegateCrud;
import br.gov.frameworkdemoiselle.transaction.Transactional;
import br.org.ibmi.patrimonio.domain.Classificacao;
import br.org.ibmi.patrimonio.persistence.ClassificacaoDAO;


@BusinessController
public class ClassificacaoBC extends DelegateCrud<Classificacao, Long, ClassificacaoDAO> {
	
	private static final long serialVersionUID = 1L;
	
	@Startup
	@Transactional
	public void load() {
		if (findAll().isEmpty()) {
			insert(new Classificacao ("COM RESERVA DE VALOR"));
			insert(new Classificacao ("SEM RESERVA DE VALOR"));
			
			
		}
	}
		
	
	
}
