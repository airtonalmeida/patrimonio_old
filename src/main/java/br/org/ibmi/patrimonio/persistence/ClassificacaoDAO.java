package br.org.ibmi.patrimonio.persistence;

import br.gov.frameworkdemoiselle.stereotype.PersistenceController;
import br.gov.frameworkdemoiselle.template.JPACrud;
import br.org.ibmi.patrimonio.domain.Classificacao;


@PersistenceController
public class ClassificacaoDAO extends JPACrud<Classificacao, Long> {

	private static final long serialVersionUID = 1L;
	
	
}
