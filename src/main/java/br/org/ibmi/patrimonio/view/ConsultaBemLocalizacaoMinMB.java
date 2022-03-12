package br.org.ibmi.patrimonio.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.inject.Inject;

import org.primefaces.context.RequestContext;

import net.sf.jasperreports.engine.JRException;
import br.gov.frameworkdemoiselle.annotation.NextView;
import br.gov.frameworkdemoiselle.annotation.PreviousView;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.AbstractEditPageBean;
import br.gov.frameworkdemoiselle.transaction.Transactional;
import br.org.ibmi.patrimonio.business.BemBC;
import br.org.ibmi.patrimonio.business.LocalizacaoBC;
import br.org.ibmi.patrimonio.business.MinisterioBC;
import br.org.ibmi.patrimonio.domain.Bem;
import br.org.ibmi.patrimonio.domain.Localizacao;
import br.org.ibmi.patrimonio.domain.Ministerio;
import br.org.ibmi.patrimonio.util.relatorio.Relatorio;

@ViewController
@NextView("./bem_edit.jsf")
@PreviousView("./consultaPorLocalizacaoMinisterio.jsf")
public class ConsultaBemLocalizacaoMinMB extends AbstractEditPageBean<Bem, Long> {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Localizacao localizacaoAlt;
		
	public Localizacao getLocalizacaoAlt() {
		return localizacaoAlt;
	}

	public void setLocalizacaoAlt(Localizacao localizacaoAlt) {
		this.localizacaoAlt = localizacaoAlt;
	}

	private List <Bem> listBem;
	
	@Inject
	private BemBC bemBC;
	
	@Inject
	private LocalizacaoBC localizacaoBC;
	
	@Inject
	private MinisterioBC ministerioBC;
	
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
	
	public List<Localizacao> getListLocalizacao() {
		return localizacaoBC.findAll();
	}
		
	public List<Ministerio> getListMinisterio() {
		return ministerioBC.findAll();
	}
	
	public List<Bem> getListBem() {
		return listBem;
	}

	public void setListBem(List<Bem> listBem) {
		this.listBem = listBem;
	}
	
	@Transactional
	public void conBemLocalizacaoMin() {
		
			if((getBean().getLocalizacao()==null)||(getBean().getMinisterio()==null)){
				
				setListBem(null);
				
				this.setTotalBens(null);
				
				this.setTotalValorAtual("");
				
				FacesMessage messageNull = new FacesMessage(FacesMessage.SEVERITY_INFO, "AVISO", "Selecione a Localização e o Ministerio!");
				
				RequestContext.getCurrentInstance().showMessageInDialog(messageNull);
				
			}else{
			
					resultado = bemBC.conListBemLocalizacaoMinBC(getBean());
			
					if(!resultado.isEmpty()){
						
						this.setListBem(resultado);
						
						this.totalBens = resultado.size();
						
						Double totalValorAtual = (double) 0;
												
						Iterator<Bem> iterCalculo = resultado.iterator();
						
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
						
						FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "AVISO", "Não há Bem cadastrado com a Localização " + getBean().getLocalizacao().getDescricao() + " e ao Ministério " + getBean().getMinisterio().getDescricao());
						
						RequestContext.getCurrentInstance().showMessageInDialog(message);
						
						
										
					}
			}
		}
	
	@Transactional
	public String showReport() throws JRException {
		
		if((getBean().getLocalizacao()==null)||(getBean().getMinisterio()==null)){
			
			setListBem(null);
			
			this.setTotalBens(null);
			
			this.setTotalValorAtual("");
			
			FacesMessage messageNull = new FacesMessage(FacesMessage.SEVERITY_INFO, "AVISO", "Selecione a Localização e o Ministerio!");
			
			RequestContext.getCurrentInstance().showMessageInDialog(messageNull);
			
		}else{	
		
					resultado = bemBC.conListBemLocalizacaoMinBC(getBean());
			
					if(!resultado.isEmpty()){
						
						relatorio.showReportLocalizacaoMinisterio(resultado);
						
					}else{
						
						setListBem(null);
						
						this.setTotalBens(null);
						
						this.setTotalValorAtual("");
						
						FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "AVISO", "Não há Bem cadastrado com a Localização " + getBean().getLocalizacao().getDescricao() + " e ao Ministério " + getBean().getMinisterio().getDescricao());
						
						RequestContext.getCurrentInstance().showMessageInDialog(message);
						
						
										
					}
							
		}		
					return null;
	
	}
	
	@Transactional
	public void alterarLocalizacao(){
		
		int contador = 0;
		
		Localizacao selLocalizacao = this.getLocalizacaoAlt();
		
		Bem bemResult;
		
		boolean selecionado;
		
			for (Iterator<Long> iterLoc = getSelection().keySet().iterator(); iterLoc.hasNext();) {
				Long id = iterLoc.next();
				
			selecionado = getSelection().get(id);	
			
				if (selecionado) {
					bemResult = bemBC.consultarBemCodigoBC(id);
					
					bemResult.setLocalizacao(selLocalizacao);
					
					this.bemBC.update(bemResult);
					
					iterLoc.remove();
					
					contador ++;
				}
			}
						
			setListBem(null);
			this.totalBens = 0;
			this.setTotalValorAtual("");
			
			
			this.getListLocalizacao().clear();
			this.getListMinisterio().clear();
			
			FacesMessage messageNull = new FacesMessage(FacesMessage.SEVERITY_INFO, "AVISO", contador + " Bem(s) alterado(s) para a localização " + selLocalizacao.getDescricao());
			
			RequestContext.getCurrentInstance().showMessageInDialog(messageNull);
	
			
			
			
	}
	
	
	public void clear() {
		this.resultado = null;
	
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