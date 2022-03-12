package br.org.ibmi.patrimonio.view;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.primefaces.context.RequestContext;

import net.sf.jasperreports.engine.JRException;
import br.gov.frameworkdemoiselle.annotation.PreviousView;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.AbstractEditPageBean;
import br.gov.frameworkdemoiselle.transaction.Transactional;
import br.org.ibmi.patrimonio.business.BemBC;
import br.org.ibmi.patrimonio.business.ClassificacaoBC;
import br.org.ibmi.patrimonio.domain.Bem;
import br.org.ibmi.patrimonio.domain.Classificacao;
import br.org.ibmi.patrimonio.util.relatorio.Relatorio;

@ViewController
@PreviousView( "./relatorioBensReservaValor.jsf")
public class RelBensReservaValorMB extends AbstractEditPageBean<Bem, Long> {

	private static final long serialVersionUID = 1L;
		
	private List <Bem> listBem;
	
	@Inject
	private BemBC bemBC;
	
	@Inject
	private ClassificacaoBC classificacaoBC;
	
	
	private List<Bem> resultado = new ArrayList<Bem>();
	
	@Inject
	Relatorio relatorio;
		
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
		
	public List<Bem> getListBem() {
		return listBem;
	}

	public void setListBem(List<Bem> listBem) {
		this.listBem = listBem;
	}
	
	private Integer totalBens;
	
	public Integer getTotalBens() {
		return totalBens;
	}

	public void setTotalBens(Integer totalBens) {
		this.totalBens = totalBens;
	}
	
	public List<Classificacao> getListClassificacao() {
		return classificacaoBC.findAll();
	}
		
	public void clear() {
		this.resultado = null;
		
		HttpServletRequest request = (HttpServletRequest) FacesContext
	            .getCurrentInstance().getExternalContext().getRequest();
		
		HttpSession session=request.getSession();	
		
		session.setAttribute("viewOrigem", getPreviousView()); 
	    
	}
		
	public String reportComReservaValor() throws JRException {
		
		resultado = bemBC.relBensComReservaValorBC();

		if(!resultado.isEmpty()){
			
			relatorio.reportComReservaValor(resultado);
			
		}else{
			
			setListBem(null);
			
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "AVISO", "Não há Bens cadastrados com Reserva de Valor!");
			
			RequestContext.getCurrentInstance().showMessageInDialog(message);
													
		}
				
		//return getNextView();
		return null;
	}
	
	public String reportSemReservaValor() throws JRException {
		
		resultado = bemBC.relBensSemReservaValorBC();

		if(!resultado.isEmpty()){
			
			relatorio.reportSemReservaValor(resultado);
			
		}else{
			
			setListBem(null);
			
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "AVISO", "Não há Bens cadastrados sem Reserva de Valor!");
			
			RequestContext.getCurrentInstance().showMessageInDialog(message);
													
		}
				
		//return getNextView();
		return null;
	}
	
	

	

}