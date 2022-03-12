package br.org.ibmi.patrimonio.view;

import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import org.primefaces.context.RequestContext;
import br.gov.frameworkdemoiselle.annotation.PreviousView;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.AbstractEditPageBean;
import br.gov.frameworkdemoiselle.transaction.Transactional;
import br.org.ibmi.patrimonio.business.BaseBC;
import br.org.ibmi.patrimonio.domain.Base;


@ViewController
@PreviousView("./base_list.jsf")
public class BaseEditMB extends AbstractEditPageBean<Base, Long> {

	private static final long serialVersionUID = 1L;

	@Inject
	private BaseBC baseBC;
	

	
	@Override
	@Transactional
	public String delete() {
		this.baseBC.delete(getId());
		return getPreviousView();
	}
	
	@Override
	@Transactional
	public String insert() {
		
		if(this.getBean().getDescricao()==""){
			
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "AVISO", "Infome a Base!");
			
			RequestContext.getCurrentInstance().showMessageInDialog(message);
			
			return null;
			
		}else{
		
			boolean baseExiste;
			
			baseExiste = this.verificaBaseExiste();
			
			if(baseExiste == false){
				
				this.baseBC.insert(this.getBean());
				
				return getPreviousView();
					
			}else{
				
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "AVISO", "Não foi possível inserir! Base já cadastrada.");
				
				RequestContext.getCurrentInstance().showMessageInDialog(message);
				
				return null;
			}
		
		}
		
	}
	
	
	@Override
	@Transactional
	public String update() {
		
		if(this.getBean().getDescricao()==""){
			
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "AVISO", "Infome a Base!");
			
			RequestContext.getCurrentInstance().showMessageInDialog(message);
			
			return null;
			
		}else{
		
			boolean baseExiste;
			
			baseExiste = this.verificaBaseExiste();
			
			if(baseExiste == false){
				
				this.baseBC.update(getBean());
				
				return getPreviousView();
				
			}else{
				
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "AVISO", "Não foi possível inserir! Base já cadastrada.");
				
				RequestContext.getCurrentInstance().showMessageInDialog(message);
				
				return null;
			}
			
		}
		
	}
	
	public Boolean verificaBaseExiste(){
		boolean resultado;
		
		Base baseBD = this.baseBC.verificaBaseExiste(getBean());
		
		if(baseBD==null){
			
			resultado = false;
			
		}else{
			
			resultado = true;
		}
		
		return resultado;
	}
	
	@Override
	protected Base handleLoad(Long id) {
		return this.baseBC.load(id);
	}
		
	public String cancelar() {
		return getPreviousView();
	}
	
	

}