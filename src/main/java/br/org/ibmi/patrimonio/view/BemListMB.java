package br.org.ibmi.patrimonio.view;

import java.util.Iterator;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.jasperreports.engine.JRException;
import br.gov.frameworkdemoiselle.annotation.NextView;
import br.gov.frameworkdemoiselle.annotation.PreviousView;
import br.gov.frameworkdemoiselle.message.MessageContext;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.AbstractListPageBean;
import br.gov.frameworkdemoiselle.transaction.Transactional;
import br.org.ibmi.patrimonio.business.BemBC;
import br.org.ibmi.patrimonio.domain.Bem;
import br.org.ibmi.patrimonio.util.relatorio.Relatorio;





@ViewController
@NextView("./bem_edit.jsf")
@PreviousView("./bem_list.jsf")
public class BemListMB extends AbstractListPageBean<Bem, Long> {

	private static final long serialVersionUID = 1L;

	@Inject
	private BemBC bemBC;
		
	private List<Bem> listBem;
	
	private List<Bem> resultado = null;
	
	@Inject
	private Bem bem;
	
	private String tombo;
	
	@Inject
	private MessageContext messageContext;
	
	@Inject
	Relatorio relatorio;	
	
			
	public Integer obterTotalBens(){
		Integer total = null;
		
		total = this.bemBC.findAll().size();
		
		return total;
	}

	@Override
	protected List<Bem> handleResultList() {
		return this.bemBC.findAll();
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
	
	
	public void consultarBemPorTombo() {
		
		bem.setTombo(this.tombo);
			
		resultado = bemBC.consultarListBemTomboBC(bem);
		
		if(!resultado.isEmpty()){
			
			setListBem(resultado);
			
		}else{
			
			setListBem(null);
			
			messageContext.add("Não existe Bem com o tombo informado!");
							
		}
		
	}

	public Bem getBem() {
		return bem;
	}

	public void setBem(Bem bem) {
		this.bem = bem;
	}

	public String getTombo() {
		return tombo;
	}

	public void setTombo(String tombo) {
		this.tombo = tombo;
	}

	public List<Bem> getListBem() {
		return listBem;
	}

	public void setListBem(List<Bem> listBem) {
		this.listBem = listBem;
	}

	public String showReport() throws JRException {
		relatorio.showReportTodosBens(bemBC.findAll());
		//return getNextView();
		return null;
	}
	
	public void telaOrigem() {
		
		super.clear();
		
		this.resultado = null;
		
		HttpServletRequest request = (HttpServletRequest) FacesContext
	            .getCurrentInstance().getExternalContext().getRequest();
		
		HttpSession session=request.getSession();	
		
		session.setAttribute("viewOrigem", getPreviousView()); 
	    
	}

	
	
}