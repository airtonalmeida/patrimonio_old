package br.org.ibmi.patrimonio.view;

import javax.faces.application.FacesMessage;
import javax.inject.Inject;

import org.primefaces.context.RequestContext;

import br.gov.frameworkdemoiselle.annotation.PreviousView;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.AbstractEditPageBean;
import br.gov.frameworkdemoiselle.transaction.Transactional;
import br.org.ibmi.patrimonio.business.MarcaBC;
import br.org.ibmi.patrimonio.domain.Marca;

@ViewController
@PreviousView("./marca_list.jsf")
public class MarcaEditMB extends AbstractEditPageBean<Marca, Long> {

	private static final long serialVersionUID = 1L;

	@Inject
	private MarcaBC marcaBC;
	
	@Override
	@Transactional
	public String delete() {
		this.marcaBC.delete(getId());
		return getPreviousView();
	}
	
	@Override
	@Transactional
	public String insert() {
		
		if(this.getBean().getDescricao()==""){
			
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "AVISO", "Infome a Marca!");
			
			RequestContext.getCurrentInstance().showMessageInDialog(message);
			
			return null;
			
		}else{
		
			boolean marcaExiste;
			
			marcaExiste = this.verificaMarcaExiste();
			
			if(marcaExiste == false){
			
				this.marcaBC.insert(getBean());
				
				return getPreviousView();
					
			}else{
				
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "AVISO", "Não foi possível inserir! Marca já cadastrada.");
				
				RequestContext.getCurrentInstance().showMessageInDialog(message);
				
				return null;
			}
		
		}
	}
	
	public Boolean verificaMarcaExiste(){
		boolean resultado;
		
		Marca marcaBD = this.marcaBC.verificaMarcaExiste(getBean());
		
		if(marcaBD==null){
			
			resultado = false;
			
		}else{
			
			resultado = true;
		}
		
		return resultado;
	}
	
	@Override
	@Transactional
	public String update() {
		
		if(this.getBean().getDescricao()==""){
			
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "AVISO", "Infome a Marca!");
			
			RequestContext.getCurrentInstance().showMessageInDialog(message);
			
			return null;
			
		}else{
		
			boolean marcaExiste;
			
			marcaExiste = this.verificaMarcaExiste();
			
			if(marcaExiste == false){
			
				this.marcaBC.update(getBean());
				return getPreviousView();
				
			}else{
				
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "AVISO", "Não foi possível inserir! Marca já cadastrada.");
				
				RequestContext.getCurrentInstance().showMessageInDialog(message);
				
				return null;
			}
			
		}	
	}
	
	@Override
	protected Marca handleLoad(Long id) {
		return this.marcaBC.load(id);
	}
	
	public String cancelar() {
		return getPreviousView();
	}


}