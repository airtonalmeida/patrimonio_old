package br.org.ibmi.patrimonio.business;

import java.util.List;

import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.template.DelegateCrud;
import br.org.ibmi.patrimonio.domain.Base;
import br.org.ibmi.patrimonio.domain.Ministerio;
import br.org.ibmi.patrimonio.persistence.MinisterioDAO;

@BusinessController
public class MinisterioBC extends DelegateCrud<Ministerio, Long, MinisterioDAO> {
	
	private static final long serialVersionUID = 1L;
		
	public List<Ministerio> consultarMinisterioPorBase (Base base){
		
		List <Ministerio> listMinisterioPorBase = getDelegate()
				.consultarMinisterioPorBase(base);
		return  listMinisterioPorBase;
	}
	
	public Ministerio verificaMinisterioExiste(Ministerio ministerio){
		
		Ministerio ministerioBD = getDelegate().
				verificaMinisterioExiste(ministerio);
	
		return ministerioBD;
	}
		
	
	
}
