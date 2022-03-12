package br.org.ibmi.patrimonio.view;

import javax.faces.application.FacesMessage;
import javax.inject.Inject;

import org.primefaces.context.RequestContext;

import br.gov.frameworkdemoiselle.annotation.PreviousView;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.AbstractEditPageBean;
import br.gov.frameworkdemoiselle.transaction.Transactional;
import br.org.ibmi.patrimonio.business.LocalizacaoBC;
import br.org.ibmi.patrimonio.domain.Localizacao;

@ViewController
@PreviousView("./localizacao_list.jsf")
public class LocalizacaoEditMB extends AbstractEditPageBean<Localizacao, Long> {

	private static final long serialVersionUID = 1L;

	@Inject
	private LocalizacaoBC localizacaoBC;
	
	@Override
	@Transactional
	public String delete() {
		this.localizacaoBC.delete(getId());
		return getPreviousView();
	}
	
	@Override
	@Transactional
	public String insert() {
		
		if(this.getBean().getDescricao()==""){
			
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "AVISO", "Infome a Localização!");
			
			RequestContext.getCurrentInstance().showMessageInDialog(message);
			
			return null;
			
		}else{
		
			boolean localizacaoExiste;
			
			localizacaoExiste = this.verificaLocalizacaoExiste();
			
			if(localizacaoExiste == false){
			
				this.localizacaoBC.insert(getBean());
				return getPreviousView();
			
			}else{
				
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "AVISO", "Não foi possível inserir! Localização já cadastrada.");
				
				RequestContext.getCurrentInstance().showMessageInDialog(message);
				
				return null;
			}
			
		}	
	}
	
	@Override
	@Transactional
	public String update() {
		
		if(this.getBean().getDescricao()==""){
			
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "AVISO", "Infome a Localização!");
			
			RequestContext.getCurrentInstance().showMessageInDialog(message);
			
			return null;
			
		}else{
		
			boolean localizacaoExiste;
			
			localizacaoExiste = this.verificaLocalizacaoExiste();
			
			if(localizacaoExiste == false){
			
				this.localizacaoBC.update(getBean());
				return getPreviousView();
				
			}else{
				
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "AVISO", "Não foi possível inserir! Localização já cadastrado.");
				
				RequestContext.getCurrentInstance().showMessageInDialog(message);
				
				return null;
			}
		
		}
	}
	
	public Boolean verificaLocalizacaoExiste(){
		boolean resultado;
		
		Localizacao localizacaoBD = this.localizacaoBC.verificaLocalizacaoExiste(getBean());
		
		if(localizacaoBD==null){
			
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
	protected Localizacao handleLoad(Long id) {
		return this.localizacaoBC.load(id);
	}

}