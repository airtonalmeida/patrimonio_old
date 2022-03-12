package br.org.ibmi.patrimonio.view;

import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import org.primefaces.context.RequestContext;
import br.gov.frameworkdemoiselle.annotation.PreviousView;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.AbstractEditPageBean;
import br.gov.frameworkdemoiselle.transaction.Transactional;
import br.org.ibmi.patrimonio.business.SituacaoBC;
import br.org.ibmi.patrimonio.domain.Situacao;


@ViewController
@PreviousView("./situacao_list.jsf")
public class SituacaoEditMB extends AbstractEditPageBean<Situacao, Long> {

	private static final long serialVersionUID = 1L;

	@Inject
	private SituacaoBC situacaoBC;
	
	@Override
	@Transactional
	public String delete() {
		this.situacaoBC.delete(getId());
		return getPreviousView();
	}
	
	@Override
	@Transactional
	public String insert() {
		
		if(this.getBean().getDescricao()==""){
			
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "AVISO", "Infome a Situação!");
			
			RequestContext.getCurrentInstance().showMessageInDialog(message);
			
			return null;
			
		}else{
		
			boolean situacaoExiste;
			
			situacaoExiste = this.verificaSituacaoExiste();
			
			if(situacaoExiste == false){
			
				this.situacaoBC.insert(getBean());
				return getPreviousView();
				
			}else{
				
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "AVISO", "Não foi possível inserir! Situação já cadastrada.");
				
				RequestContext.getCurrentInstance().showMessageInDialog(message);
				
				return null;
			}
			
		}	
		
	}
	
	@Override
	@Transactional
	public String update() {
		
		if(this.getBean().getDescricao()==""){
			
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "AVISO", "Infome a Situação!");
			
			RequestContext.getCurrentInstance().showMessageInDialog(message);
			
			return null;
			
		}else{
		
			boolean situacaoExiste;
			
			situacaoExiste = this.verificaSituacaoExiste();
			
			if(situacaoExiste == false){
			
				this.situacaoBC.update(getBean());
				return getPreviousView();
				
			}else{
				
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "AVISO", "Não foi possível inserir! Situação já cadastrada.");
				
				RequestContext.getCurrentInstance().showMessageInDialog(message);
				
				return null;
			}
		
		}
		
	}
	
	public Boolean verificaSituacaoExiste(){
		boolean resultado;
		
		Situacao situacaoBD = this.situacaoBC.verificaSituacaoExiste(getBean());
		
		if(situacaoBD==null){
			
			resultado = false;
			
		}else{
			
			resultado = true;
		}
		
		return resultado;
	}
	
	@Override
	protected Situacao handleLoad(Long id) {
		return this.situacaoBC.load(id);
	}
	
	public String cancelar() {
		return getPreviousView();
	}


}