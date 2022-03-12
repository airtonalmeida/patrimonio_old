package br.org.ibmi.patrimonio.business;

import java.util.List;

import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.template.DelegateCrud;
import br.org.ibmi.patrimonio.domain.SubTipoBem;
import br.org.ibmi.patrimonio.domain.TipoBem;
import br.org.ibmi.patrimonio.persistence.SubTipoBemDAO;

@BusinessController
public class SubTipoBemBC extends DelegateCrud<SubTipoBem, Long, SubTipoBemDAO> {
	
	private static final long serialVersionUID = 1L;
	
	public List<SubTipoBem> consultarSubTipoBemPorTipoBem (TipoBem tipoBem){
		
		List <SubTipoBem> listSubTipoBemPorTipoBem = getDelegate()
				.consultarSubTipoBemPorTipoBem(tipoBem);
		return  listSubTipoBemPorTipoBem;
	}


	public SubTipoBem verificaSubTipoBemExiste(SubTipoBem subTipoBem){
		
		SubTipoBem subTipoBemBD = getDelegate().
				verificaSubTipoBemExiste(subTipoBem);
	
		return subTipoBemBD;
	}
	
	
}
