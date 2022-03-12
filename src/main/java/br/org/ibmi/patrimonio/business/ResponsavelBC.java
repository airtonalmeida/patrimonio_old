package br.org.ibmi.patrimonio.business;

import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.template.DelegateCrud;
import br.org.ibmi.patrimonio.domain.Ministerio;
import br.org.ibmi.patrimonio.domain.Responsavel;
import br.org.ibmi.patrimonio.persistence.ResponsavelDAO;

@BusinessController
public class ResponsavelBC extends DelegateCrud<Responsavel, Long, ResponsavelDAO> {
	
	private static final long serialVersionUID = 1L;
		
	/*public List<Responsavel> obterResponsavelMinisterio(Ministerio ministerio){
		
		List<Responsavel> listaResponsaveis = getDelegate()
						.obterResponsavelMinisterio(ministerio);
		
		return listaResponsaveis;
		
	}*/
	
	public Responsavel obterResponsavelMinisterio(Ministerio ministerio){
		
		Responsavel responsavel = getDelegate()
						.obterResponsavelMinisterio(ministerio);
		
		return responsavel;
		
	}
	
	public Responsavel verificaMinResponsavelExiste(Responsavel responsavel){
		
		Responsavel minResponsavelBD = getDelegate().
				verificaMinResponsavelExiste(responsavel);
	
		return minResponsavelBD;
	}
	
	public Responsavel verificaNomeResponsavelExiste(Responsavel responsavel){
		
		Responsavel nomeResponsavelBD = getDelegate().
				verificaNomeResponsavelExiste(responsavel);
	
		return nomeResponsavelBD;
	}
	
	public Responsavel verificaResponsavelUpdate(Responsavel responsavel){
		
		Responsavel updateResponsavelBD = getDelegate().
				verificaResponsavelUpdate(responsavel);
	
		return updateResponsavelBD;
	}
	
	
}
