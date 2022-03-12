package br.org.ibmi.patrimonio.view;



import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import org.primefaces.context.RequestContext;
import br.gov.frameworkdemoiselle.annotation.PreviousView;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.transaction.Transactional;
import br.org.ibmi.patrimonio.business.BemBC;
import br.org.ibmi.patrimonio.domain.Bem;


import java.io.Serializable;


@ViewController
@PreviousView("./index.jsf")
public class AtualizarValorAtualBemMB implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private BemBC bemBC;
	
	private List<Bem> bens; 
	
	Date date;
	
	@PostConstruct
    public void postConstruct(){
            init();
    }
	
	public void init(){
		
		bens =  this.bemBC.findAll();
		
		this.setTotalBens(bens.size());
		
		Double totalValorAtual = (double) 0;
		
		Iterator<Bem> iterVT = bens.iterator();
		
		Bem bemVT;
		
		for(int i = 0; i < bens.size(); i++){
			
			bemVT = iterVT.next();
			
			totalValorAtual = totalValorAtual +	bemBC.converteStringDouble(bemVT.getValorAtualString());
										
		}
		
		this.setTotalValorAtual(bemBC.converteDoubleString(totalValorAtual));
		
	}
	
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
	
	private String totalValorAtualRecalculado = "";
	
	public String getTotalValorAtualRecalculado() {
		return totalValorAtualRecalculado;
	}

	public void setTotalValorAtualRecalculado(String totalValorAtualRecalculado) {
		this.totalValorAtualRecalculado = totalValorAtualRecalculado;
	}
	
	/*@Transactional
	public void atualizaValorAtual(){
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setLenient(false);
		String data = null;
						
		Iterator<Bem> iter = bens.iterator();
		
		Bem bem;
		
		try {
			for(int i = 0; i < bens.size(); i++){
				
				bem = iter.next();
				
				if((bem.getValorHistorico()!=null) && (bem.getDataCompra()!=null)){
				
					data = bem.getDataCompra();
												
					try {
						date = sdf.parse(data);
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					// seta no atributo valorAtualString o valor calculado do atributo valorHistorico.
					bem.setValorAtualString(bemBC.calculaValorAtualString(bem.getValorHistorico(),date));
					
					this.bemBC.update(bem);
				}
				
			}
			
			this.getTotalBens();
			
			this.getTotalValorAtual();
			
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "SUCESSO", bens.size() + " Bem(s) atualizado(s)!" );
			
			RequestContext.getCurrentInstance().showMessageInDialog(message);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
	}*/
	
	@Transactional
	public void atualizaValorAtual(){
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setLenient(false);
		String data = null;
						
		Iterator<Bem> iter = bens.iterator();
		
		Bem bem;
		
		Double totalVAtual = (double) 0;
		
		try {
			for(int i = 0; i < bens.size(); i++){
				
				bem = iter.next();
				
				if((bem.getValorHistorico()!=null) && (bem.getDataCompra()!=null)){
				
					data = bem.getDataCompra();
												
					try {
						date = sdf.parse(data);
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					// seta no atributo valorAtualString o valor calculado do atributo valorHistorico.
					bem.setValorAtualString(bemBC.calculaValorAtualString(bem.getValorHistorico(),date));
					
					totalVAtual = totalVAtual +	bemBC.converteStringDouble(bem.getValorAtualString());
										
					this.bemBC.update(bem);
				}
				
			}
			
			this.setTotalValorAtualRecalculado(bemBC.converteDoubleString(totalVAtual));
									
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "SUCESSO", bens.size() + " Bem(s) atualizado(s)!" );
			
			RequestContext.getCurrentInstance().showMessageInDialog(message);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
	}
	
	public String cancelar() {
		return getPreviousView();
	}
	
	private String previousView;
	
	public String getPreviousView() {

		if (previousView == null) {
			PreviousView annotation = this.getClass().getAnnotation(PreviousView.class);

			if (annotation != null) {
				previousView = annotation.value();
			} else {
				// TODO LanÃ§ar exceÃ§Ã£o orientando o usuÃ¡rio a anotar sua classe com essa anotaÃ§Ã£o aÃ­ ou entÃ£o
				// sobre-escrever este mÃ©todo.
			}
		}

		return previousView;
	}

	

	


}	
    

	

