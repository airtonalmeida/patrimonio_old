package br.org.ibmi.patrimonio.view;

import javax.faces.application.FacesMessage;
import javax.inject.Inject;

import org.primefaces.context.RequestContext;

import br.gov.frameworkdemoiselle.annotation.PreviousView;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.AbstractEditPageBean;
import br.gov.frameworkdemoiselle.transaction.Transactional;
import br.org.ibmi.patrimonio.business.TipoBemBC;
import br.org.ibmi.patrimonio.domain.TipoBem;

@ViewController
@PreviousView("./tipoBem_list.jsf")
public class TipoBemEditMB extends AbstractEditPageBean<TipoBem, Long> {

	private static final long serialVersionUID = 1L;

	@Inject
	private TipoBemBC tipoBemBC;
	
	@Override
	@Transactional
	public String delete() {
		this.tipoBemBC.delete(getId());
		return getPreviousView();
	}
	
	@Override
	@Transactional
	public String insert() {
		
		if(this.getBean().getDescricao()==""){
			
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "AVISO", "Infome o Tipo do Bem!");
			
			RequestContext.getCurrentInstance().showMessageInDialog(message);
			
			return null;
			
		}else{
		
			boolean tipoBemExiste;
			
			tipoBemExiste = this.verificaTipoBemExiste();
			
			if(tipoBemExiste == false){
			
				this.tipoBemBC.insert(getBean());
				
				return getPreviousView();
					
			}else{
				
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "AVISO", "Não foi possível inserir! Tipo de Bem já cadastrado.");
				
				RequestContext.getCurrentInstance().showMessageInDialog(message);
				
				return null;
			}
		
		}
		
	}
	
	public Boolean verificaTipoBemExiste(){
		boolean resultado;
		
		TipoBem tipoBemBD = this.tipoBemBC.verificaTipoBemExiste(getBean());
		
		if(tipoBemBD==null){
			
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
			
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "AVISO", "Infome o Tipo do Bem!");
			
			RequestContext.getCurrentInstance().showMessageInDialog(message);
			
			return null;
			
		}else{
		
			boolean tipoBemExiste;
			
			tipoBemExiste = this.verificaTipoBemExiste();
			
			if(tipoBemExiste == false){
			
				this.tipoBemBC.update(getBean());
				return getPreviousView();
			
			}else{
				
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "AVISO", "Não foi possível inserir! Tipo de Bem já cadastrado.");
				
				RequestContext.getCurrentInstance().showMessageInDialog(message);
				
				return null;
			}
			
		}
			
	}
	
	public String cancelar() {
		return getPreviousView();
	}
	
	@Override
	protected TipoBem handleLoad(Long id) {
		return this.tipoBemBC.load(id);
	}

}