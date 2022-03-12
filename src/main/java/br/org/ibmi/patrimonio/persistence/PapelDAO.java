package br.org.ibmi.patrimonio.persistence;

import br.gov.frameworkdemoiselle.stereotype.PersistenceController;
import br.gov.frameworkdemoiselle.template.JPACrud;

import br.org.ibmi.patrimonio.domain.Papel;

@PersistenceController
public class PapelDAO extends JPACrud<Papel, Long> {

	private static final long serialVersionUID = 1L;
	

}
