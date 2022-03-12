package br.org.ibmi.patrimonio.view;

import java.util.List;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import org.primefaces.context.RequestContext;
import br.gov.frameworkdemoiselle.annotation.PreviousView;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.AbstractEditPageBean;
import br.gov.frameworkdemoiselle.transaction.Transactional;
import br.org.ibmi.patrimonio.business.MarcaBC;
import br.org.ibmi.patrimonio.business.ModeloBC;
import br.org.ibmi.patrimonio.domain.Marca;
import br.org.ibmi.patrimonio.domain.Modelo;


@ViewController
@PreviousView("./modelo_list.jsf")
public class ModeloEditMB extends AbstractEditPageBean<Modelo, Long> {

	private static final long serialVersionUID = 1L;

	@Inject
	private ModeloBC modeloBC;
	
	@Inject
	private MarcaBC marcaBC;
	
	@Override
	@Transactional
	public String delete() {
		this.modeloBC.delete(getId());
		return getPreviousView();
	}
	
	@Override
	@Transactional
	public String insert() {
				
		if((this.getBean().getDescricao()=="")||(this.getBean().getMarca()==null)){
			
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "AVISO", "Infome a Descrição e a Marca!");
			
			RequestContext.getCurrentInstance().showMessageInDialog(message);
			
			return null;
			
		}else{
			
			boolean modeloExiste;
		
			modeloExiste = this.verificaModeloExiste();
			
			if(modeloExiste == false){
			
				this.modeloBC.insert(getBean());
				return getPreviousView();
				
			}else{
				
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "AVISO", "Não foi possível inserir! Modelo já cadastrado.");
				
				RequestContext.getCurrentInstance().showMessageInDialog(message);
				
				return null;
			}
		}
	}
	
	@Override
	@Transactional
	public String update() {
		
		boolean modeloExiste;
		
		if((this.getBean().getDescricao()=="")||(this.getBean().getMarca()==null)){
			
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "AVISO", "Infome a Descrição e a Marca!");
			
			RequestContext.getCurrentInstance().showMessageInDialog(message);
			
			return null;
			
		}else{
		
			modeloExiste = this.verificaModeloExiste();
			
			if(modeloExiste == false){
			
				this.modeloBC.update(getBean());
				return getPreviousView();
			
			}else{
				
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "AVISO", "Não foi possível inserir! Modelo já cadastrado.");
				
				RequestContext.getCurrentInstance().showMessageInDialog(message);
				
				return null;
			}
		}
	}
	
	public Boolean verificaModeloExiste(){
		boolean resultado;
		
		Modelo modeloBD = this.modeloBC.verificaModeloExiste(getBean());
		
		if(modeloBD==null){
			
			resultado = false;
			
		}else{
			
			resultado = true;
		}
		
		return resultado;
	}
	
	public String cancelar() {
		return getPreviousView();
	}

	
	@Override
	protected Modelo handleLoad(Long id) {
		return this.modeloBC.load(id);
	}

	
	
	public List<Marca> getListMarca() {
		return marcaBC.findAll();
	}

}