package br.org.ibmi.patrimonio.view;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.primefaces.context.RequestContext;

import net.sf.jasperreports.engine.JRException;
import br.gov.frameworkdemoiselle.annotation.NextView;
import br.gov.frameworkdemoiselle.annotation.PreviousView;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.AbstractEditPageBean;
import br.gov.frameworkdemoiselle.transaction.Transactional;
import br.org.ibmi.patrimonio.business.BemBC;
import br.org.ibmi.patrimonio.business.SubTipoBemBC;
import br.org.ibmi.patrimonio.domain.Bem;
import br.org.ibmi.patrimonio.domain.SubTipoBem;
import br.org.ibmi.patrimonio.util.relatorio.Relatorio;

@ViewController
//@NextView("./consultaPorSubTipoBemEditar.jsf")
@NextView("./bem_edit.jsf")
@PreviousView( "./consultaPorSubTipoBem.jsf")
public class ConsultaBemSubTipoBemMB extends AbstractEditPageBean<Bem, Long> {

	private static final long serialVersionUID = 1L;
		
	private List <Bem> listBem;
	
	@Inject
	private BemBC bemBC;
	
	@Inject
	private SubTipoBemBC subTipoBemBC;
	
	private List<Bem> resultado = new ArrayList<Bem>();
	
	@Inject
	Relatorio relatorio;
	
	private Integer totalBens;
	
	public Integer getTotalBens() {
		return totalBens;
	}

	public void setTotalBens(Integer totalBens) {
		this.totalBens = totalBens;
	}
	
	private String totalValorAtual = "";
	
	public String getTotalValorAtual() {
		return totalValorAtual;
	}

	public void setTotalValorAtual(String totalValorAtual) {
		this.totalValorAtual = totalValorAtual;
	}
			
	@Override
	@Transactional
	public String delete() {
		this.bemBC.delete(getId());
		return getPreviousView();
	}
	
	@Override
	@Transactional
	public String insert() {
		this.bemBC.insert(getBean());
		return getPreviousView();
	}
	
	@Override
	@Transactional
	public String update() {
		this.bemBC.update(getBean());
		return getPreviousView();
	}
		
	@Override
	protected Bem handleLoad(Long id) {
		return this.bemBC.load(id);
	}
	
	public List<SubTipoBem> getListSubTipoBem() {
		return subTipoBemBC.findAll();
	}
	
	public SubTipoBemBC getSubTipoBemBC() {
		return subTipoBemBC;
	}

	public void setSubTipoBemBC(SubTipoBemBC subTipoBemBC) {
		this.subTipoBemBC = subTipoBemBC;
	}

	public List<Bem> getListBem() {
		return listBem;
	}

	public void setListBem(List<Bem> listBem) {
		this.listBem = listBem;
	}
	
	@Transactional
	public void consultarBemPorSubTipoBem() {
		
		if(getBean().getSubTipoBem()==null){
			
			setListBem(null);
			
			this.setTotalBens(null);
			
			this.setTotalValorAtual("");
			
			FacesMessage messageNull = new FacesMessage(FacesMessage.SEVERITY_INFO, "AVISO", "Selecione o Sub Tipo de Bem!");
			
			RequestContext.getCurrentInstance().showMessageInDialog(messageNull);
			
		}else{
		
			resultado = bemBC.consultarListBemSubTipoBemBC(getBean());
	
			if(!resultado.isEmpty()){
				
				setListBem(resultado);
				
				this.totalBens = resultado.size();
				
				Double totalValorAtual = (double) 0;
				
				Iterator<Bem> iterCalculo = resultado.iterator() ;
				
				Bem bemCalculo;
				
				for(int i = 0; i < resultado.size(); i++){
					
					bemCalculo = iterCalculo.next();
					
					totalValorAtual = totalValorAtual +	bemBC.converteStringDouble(bemCalculo.getValorAtualString());
												
				}
				
				this.setTotalValorAtual(bemBC.converteDoubleString(totalValorAtual));
				
			}else{
				
				setListBem(null);
				
				this.setTotalBens(null);
				
				this.setTotalValorAtual("");
				
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "AVISO", "Não há Bem cadastrado com o Sub Tipo de Bem " + getBean().getSubTipoBem().getDescricao());
				
				RequestContext.getCurrentInstance().showMessageInDialog(message);
								
								
			}
		}
	}
	
	@Transactional
	public String showReport() throws JRException {
		
		if(getBean().getSubTipoBem()==null){
			
			setListBem(null);
			
			this.setTotalBens(null);
			
			this.setTotalValorAtual("");
			
			FacesMessage messageNull = new FacesMessage(FacesMessage.SEVERITY_INFO, "AVISO", "Selecione o Sub Tipo de Bem!");
			
			RequestContext.getCurrentInstance().showMessageInDialog(messageNull);
			
		}else{
				
				resultado = bemBC.consultarListBemSubTipoBemBC(getBean());
		
				if(!resultado.isEmpty()){
					
					relatorio.showReportSubTipoBem(resultado);
					
				}else{
					
					setListBem(null);
					
					this.setTotalBens(null);
					
					this.setTotalValorAtual("");
					
					FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "AVISO", "Não há Bem cadastrado com o Sub Tipo de Bem " + getBean().getSubTipoBem().getDescricao());
					
					RequestContext.getCurrentInstance().showMessageInDialog(message);
					
														
				}
				
		}
		
		return null;
	}
	
	public void clear() {
		this.resultado = null;
		
		HttpServletRequest request = (HttpServletRequest) FacesContext
	            .getCurrentInstance().getExternalContext().getRequest();
		
		HttpSession session=request.getSession();	
		
		session.setAttribute("viewOrigem", getPreviousView()); 
	    
	}
	
	private Map<Long, Boolean> selection = new HashMap<Long, Boolean>();
		
	public Map<Long, Boolean> getSelection() {
		return selection;
	}

	public void setSelection(Map<Long, Boolean> selection) {
		this.selection = selection;
	}

	@Transactional
	public String deleteSelection() {
		boolean delete;
		for (Iterator<Long> iter = getSelection().keySet().iterator(); iter.hasNext();) {
			Long id = iter.next();
			delete = getSelection().get(id);
			if (delete) {
				bemBC.delete(id);
				iter.remove();
			}
		}
		return getPreviousView();
	}
	


	

}