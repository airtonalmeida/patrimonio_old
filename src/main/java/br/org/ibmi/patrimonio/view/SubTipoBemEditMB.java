package br.org.ibmi.patrimonio.view;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.inject.Inject;

import org.primefaces.context.RequestContext;

import br.gov.frameworkdemoiselle.annotation.PreviousView;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.AbstractEditPageBean;
import br.gov.frameworkdemoiselle.transaction.Transactional;
import br.org.ibmi.patrimonio.business.ClassificacaoBC;
import br.org.ibmi.patrimonio.business.SubTipoBemBC;
import br.org.ibmi.patrimonio.business.TipoBemBC;
import br.org.ibmi.patrimonio.domain.Classificacao;
import br.org.ibmi.patrimonio.domain.SubTipoBem;
import br.org.ibmi.patrimonio.domain.TipoBem;

@ViewController
@PreviousView("./subTipoBem_list.jsf")
public class SubTipoBemEditMB extends AbstractEditPageBean<SubTipoBem, Long> {

	private static final long serialVersionUID = 1L;

	@Inject
	private SubTipoBemBC subTipoBemBC;
	
	@Inject
	private TipoBemBC tipoBemBC;
	
	@Inject
	private ClassificacaoBC classificacaoBC;
	
	@Override
	@Transactional
	public String delete() {
		this.subTipoBemBC.delete(getId());
		return getPreviousView();
	}
	
	@Override
	@Transactional
	public String insert() {
		
		if((this.getBean().getDescricao()=="")||(this.getBean().getTipoBem()==null)){
			
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "AVISO", "Infome a Descrição e o Tipo de Bem!");
			
			RequestContext.getCurrentInstance().showMessageInDialog(message);
			
			return null;
			
		}else{
		
			boolean subTipoBemExiste;
			
			subTipoBemExiste = this.verificaSubTipoBemExiste();
			
			if(subTipoBemExiste == false){
			
				this.subTipoBemBC.insert(getBean());
				
				return getPreviousView();
					
			}else{
				
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "AVISO", "Não foi possível inserir! Sub Tipo de Bem já cadastrado.");
				
				RequestContext.getCurrentInstance().showMessageInDialog(message);
				
				return null;
			}
		
		}
	}
	
	public Boolean verificaSubTipoBemExiste(){
		boolean resultado;
		
		SubTipoBem subTipoBemBD = this.subTipoBemBC.verificaSubTipoBemExiste(getBean());
		
		if(subTipoBemBD==null){
			
			resultado = false;
			
		}else{
			
			resultado = true;
		}
		
		return resultado;
	}
	
	@Override
	@Transactional
	public String update() {
		
		if((this.getBean().getDescricao()=="")||(this.getBean().getTipoBem()==null)){
			
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "AVISO", "Infome a Descrição e o Tipo de Bem!");
			
			RequestContext.getCurrentInstance().showMessageInDialog(message);
			
			return null;
			
		}else{
		
			boolean subTipoBemExiste;
			
			subTipoBemExiste = this.verificaSubTipoBemExiste();
			
			if(subTipoBemExiste == false){
			
				this.subTipoBemBC.update(getBean());
				return getPreviousView();
			
			}else{
				
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "AVISO", "Não foi possível inserir! Sub Tipo de Bem já cadastrado.");
				
				RequestContext.getCurrentInstance().showMessageInDialog(message);
				
				return null;
			}
			
		}	
	}
	
	public String cancelar() {
		return getPreviousView();
	}
	
	@Override
	protected SubTipoBem handleLoad(Long id) {
		return this.subTipoBemBC.load(id);
	}

/*	public TipoBemBC getTipoBemBC() {
		return tipoBemBC;
	}

	public void setTipoBemBC(TipoBemBC tipoBemBC) {
		this.tipoBemBC = tipoBemBC;
	}*/
	
	public List<TipoBem> getListTipoBem() {
		return tipoBemBC.findAll();
	}
	
	public List<Classificacao> getListClassificacao() {
		return classificacaoBC.findAll();
	}

}