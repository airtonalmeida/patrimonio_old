package br.org.ibmi.patrimonio.view;


import java.util.List;

import javax.faces.application.FacesMessage;
import javax.inject.Inject;

import org.primefaces.context.RequestContext;

import br.gov.frameworkdemoiselle.annotation.PreviousView;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.AbstractEditPageBean;
import br.gov.frameworkdemoiselle.transaction.Transactional;
import br.org.ibmi.patrimonio.business.BaseBC;
import br.org.ibmi.patrimonio.business.MinisterioBC;
import br.org.ibmi.patrimonio.domain.Base;
import br.org.ibmi.patrimonio.domain.Ministerio;

@ViewController
@PreviousView("./ministerio_list.jsf")
public class MinisterioEditMB extends AbstractEditPageBean<Ministerio, Long> {

	private static final long serialVersionUID = 1L;

	@Inject
	private MinisterioBC ministerioBC;
	
	@Inject
	private BaseBC baseBC;
	
	@Override
	@Transactional
	public String delete() {
		this.ministerioBC.delete(getId());
		return getPreviousView();
	}
	
	@Override
	@Transactional
	public String insert() {
		
		if((this.getBean().getDescricao()=="")||(this.getBean().getBase()==null)){
			
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "AVISO", "Infome a Descrição e a Base!");
			
			RequestContext.getCurrentInstance().showMessageInDialog(message);
			
			return null;
			
		}else{
		
				boolean ministerioExiste;
				
				ministerioExiste = this.verificaMinisterioExiste();
				
				if(ministerioExiste == false){
				
					this.ministerioBC.insert(getBean());
					
					return getPreviousView();
						
				}else{
					
					FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "AVISO", "Não foi possível inserir! Ministério já cadastrado.");
					
					RequestContext.getCurrentInstance().showMessageInDialog(message);
					
					return null;
				}
		
		}
		
	}
		
	@Override
	@Transactional
	public String update() {
		
		if((this.getBean().getDescricao()=="")||(this.getBean().getBase()==null)){
			
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "AVISO", "Infome a Descrição e a Base!");
			
			RequestContext.getCurrentInstance().showMessageInDialog(message);
			
			return null;
			
		}else{
		
			boolean ministerioExiste;
			
			ministerioExiste = this.verificaMinisterioExiste();
			
			if(ministerioExiste == false){
			
				this.ministerioBC.update(getBean());
				return getPreviousView();
				
			}else{
				
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "AVISO", "Não foi possível inserir! Ministério já cadastrado.");
				
				RequestContext.getCurrentInstance().showMessageInDialog(message);
				
				return null;
			}
		}
		
	}
	
	public Boolean verificaMinisterioExiste(){
		boolean resultado;
		
		Ministerio ministerioBD = this.ministerioBC.verificaMinisterioExiste(getBean());
		
		if(ministerioBD==null){
			
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
	protected Ministerio handleLoad(Long id) {
		return this.ministerioBC.load(id);
	}

	public BaseBC getBaseBC() {
		return baseBC;
	}

	public void setBaseBC(BaseBC baseBC) {
		this.baseBC = baseBC;
	}
	
	public List<Base> getListBase() {
		return baseBC.findAll();
	}

}