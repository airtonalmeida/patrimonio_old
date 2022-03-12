package br.org.ibmi.patrimonio.view;

import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import org.primefaces.context.RequestContext;
import br.gov.frameworkdemoiselle.annotation.PreviousView;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.AbstractEditPageBean;
import br.gov.frameworkdemoiselle.transaction.Transactional;
import br.org.ibmi.patrimonio.business.TipoAquisicaoBC;
import br.org.ibmi.patrimonio.domain.TipoAquisicao;

@ViewController
@PreviousView("./tipoAquisicao_list.jsf")
public class TipoAquisicaoEditMB extends AbstractEditPageBean<TipoAquisicao, Long> {

	private static final long serialVersionUID = 1L;

	@Inject
	private TipoAquisicaoBC tipoAquisicaoBC;
	
	@Override
	@Transactional
	public String delete() {
		this.tipoAquisicaoBC.delete(getId());
		return getPreviousView();
	}
	
	@Override
	@Transactional
	public String insert() {
		
		if(this.getBean().getDescricao()==""){
			
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "AVISO", "Infome o Tipo de Aquisição!");
			
			RequestContext.getCurrentInstance().showMessageInDialog(message);
			
			return null;
			
		}else{
				
			boolean tipoAquisicaoExiste;
			
			tipoAquisicaoExiste = this.verificaTipoAquisicaoExiste();
			
			if(tipoAquisicaoExiste == false){
			
				this.tipoAquisicaoBC.insert(getBean());
				return getPreviousView();
			
			}else{
				
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "AVISO", "Não foi possível inserir! Tipo de Aquisicão já cadastrada.");
				
				RequestContext.getCurrentInstance().showMessageInDialog(message);
				
				return null;
			}
			
		}	
	}
	
	@Override
	@Transactional
	public String update() {
		
		if(this.getBean().getDescricao()==""){
			
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "AVISO", "Infome o Tipo de Aquisição!");
			
			RequestContext.getCurrentInstance().showMessageInDialog(message);
			
			return null;
			
		}else{
		
			boolean tipoAquisicaoExiste;
			
			tipoAquisicaoExiste = this.verificaTipoAquisicaoExiste();
			
			if(tipoAquisicaoExiste == false){
			
				this.tipoAquisicaoBC.update(getBean());
				return getPreviousView();
				
			}else{
				
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "AVISO", "Não foi possível inserir! Tipo de Aquisicão já cadastrado.");
				
				RequestContext.getCurrentInstance().showMessageInDialog(message);
				
				return null;
			}
			
		}	
		
	}
	
	public Boolean verificaTipoAquisicaoExiste(){
		boolean resultado;
		
		TipoAquisicao tipoAquisicaoBD = this.tipoAquisicaoBC.verificaTipoAquisicaoExiste(getBean());
		
		if(tipoAquisicaoBD==null){
			
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
	protected TipoAquisicao handleLoad(Long id) {
		return this.tipoAquisicaoBC.load(id);
	}

}