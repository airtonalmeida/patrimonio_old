package br.org.ibmi.patrimonio.business;

import java.util.List;
import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.template.DelegateCrud;
import br.org.ibmi.patrimonio.domain.Marca;
import br.org.ibmi.patrimonio.domain.Modelo;
import br.org.ibmi.patrimonio.persistence.ModeloDAO;

@BusinessController
public class ModeloBC extends DelegateCrud<Modelo, Long, ModeloDAO> {
	
	private static final long serialVersionUID = 1L;
	
	public List<Modelo> consultarModeloPorMarca (Marca marca){
		
		List <Modelo> listModeloPorMarca = getDelegate()
				.consultarModeloPorMarca(marca);
		return  listModeloPorMarca;
	}
	
	
	public Modelo obterModelo (Long codModelo){
		
		Modelo modelo = getDelegate()
				.obterModelo(codModelo);
		return modelo;		
		
	}
	
	public Modelo verificaModeloExiste(Modelo modelo){
		
		Modelo modeloBD = getDelegate().
				verificaModeloExiste(modelo);
	
		return modeloBD;
	}
	
}
