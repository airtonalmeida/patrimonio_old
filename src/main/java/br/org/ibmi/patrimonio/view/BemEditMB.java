package br.org.ibmi.patrimonio.view;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.primefaces.context.RequestContext;
import br.gov.frameworkdemoiselle.annotation.PreviousView;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.AbstractEditPageBean;
import br.gov.frameworkdemoiselle.transaction.Transactional;
import br.org.ibmi.patrimonio.business.BaseBC;
import br.org.ibmi.patrimonio.business.BemBC;
import br.org.ibmi.patrimonio.business.LocalizacaoBC;
import br.org.ibmi.patrimonio.business.MarcaBC;
import br.org.ibmi.patrimonio.business.MinisterioBC;
import br.org.ibmi.patrimonio.business.ModeloBC;
import br.org.ibmi.patrimonio.business.ResponsavelBC;
import br.org.ibmi.patrimonio.business.SituacaoBC;
import br.org.ibmi.patrimonio.business.SubTipoBemBC;
import br.org.ibmi.patrimonio.business.TipoAquisicaoBC;
import br.org.ibmi.patrimonio.business.TipoBemBC;
import br.org.ibmi.patrimonio.domain.Base;
import br.org.ibmi.patrimonio.domain.Bem;
import br.org.ibmi.patrimonio.domain.Localizacao;
import br.org.ibmi.patrimonio.domain.Marca;
import br.org.ibmi.patrimonio.domain.Ministerio;
import br.org.ibmi.patrimonio.domain.Modelo;
import br.org.ibmi.patrimonio.domain.Responsavel;
import br.org.ibmi.patrimonio.domain.Situacao;
import br.org.ibmi.patrimonio.domain.SubTipoBem;
import br.org.ibmi.patrimonio.domain.TipoAquisicao;
import br.org.ibmi.patrimonio.domain.TipoBem;


@ViewController
@PreviousView("./bem_list.jsf")
public class BemEditMB extends AbstractEditPageBean<Bem, Long> {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private BemBC bemBC;
	
	@Inject
	private TipoBemBC tipoBemBC;
	
	@Inject
	private SubTipoBemBC subTipoBemBC;
		
	@Inject
	private BaseBC baseBC;
	
	@Inject
	private MinisterioBC ministerioBC;
	
	@Inject
	private MarcaBC marcaBC;
	
	@Inject
	private ModeloBC modeloBC;
	
	@Inject
	private ResponsavelBC responsavelBC;
		
	@Inject
	private TipoAquisicaoBC tipoAquisicaoBC;
	
	@Inject
	private LocalizacaoBC localizacaoBC;
	
	@Inject
	private SituacaoBC situacaoBC;
	
	private List<Ministerio> ministerios = new ArrayList<Ministerio>();
	
	private List<SubTipoBem> subTiposBens = new ArrayList<SubTipoBem>();
	
	private List<Modelo> modelos = new ArrayList<Modelo>();
	
	Double valorConvertido;
	
	private Date date;
	
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	private boolean responsavelAlterado = false;
		
	private String msgValorAtual = null;

	public String getMsgValorAtual() {
		return msgValorAtual;
	}

	public void setMsgValorAtual(String msgValorAtual) {
		this.msgValorAtual = msgValorAtual;
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
				
		try {
			this.geraTombo();
												
			this.bemBC.insert(getBean());
			
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "CONFIRMAÇÃO", "Cadastro realizado com sucesso!");
			
			RequestContext.getCurrentInstance().showMessageInDialog(message);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return getPreviousView();
		
	}
		

	@Override
	@Transactional
	public String update() {
						
		try {
			this.bemBC.update(getBean());
			
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "CONFIRMAÇÃO", "Alteração realizada com sucesso!");
			
			RequestContext.getCurrentInstance().showMessageInDialog(message);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		HttpServletRequest request = (HttpServletRequest) FacesContext
	            .getCurrentInstance().getExternalContext().getRequest();
		
		String viewAnterior = (String) request.getSession().getAttribute("viewOrigem");
		
		return viewAnterior;
		
	}
	
	public String cancelar() {
		
		HttpServletRequest request = (HttpServletRequest) FacesContext
	            .getCurrentInstance().getExternalContext().getRequest();
		
		String viewAnterior = (String) request.getSession().getAttribute("viewOrigem");
		
		return viewAnterior;
		
	}


	@Override
	protected Bem handleLoad(Long id) {
		return this.bemBC.load(id);
	}

	public TipoBemBC getTipoBemBC() {
		return tipoBemBC;
	}

	public void setTipoBemBC(TipoBemBC tipoBemBC) {
		this.tipoBemBC = tipoBemBC;
	}

	public SubTipoBemBC getSubTipoBemBC() {
		return subTipoBemBC;
	}

	public void setSubTipoBemBC(SubTipoBemBC subTipoBemBC) {
		this.subTipoBemBC = subTipoBemBC;
	}

	public BaseBC getBaseBC() {
		return baseBC;
	}

	public void setBaseBC(BaseBC baseBC) {
		this.baseBC = baseBC;
	}

	public MinisterioBC getMinisterioBC() {
		return ministerioBC;
	}

	public void setMinisterioBC(MinisterioBC ministerioBC) {
		this.ministerioBC = ministerioBC;
	}
	
	public MarcaBC getMarcaBC() {
		return marcaBC;
	}

	public void setMarcaBC(MarcaBC marcaBC) {
		this.marcaBC = marcaBC;
	}
	
	public ModeloBC getModeloBC() {
		return modeloBC;
	}

	public void setModeloBC(ModeloBC modeloBC) {
		this.modeloBC = modeloBC;
	}

	public ResponsavelBC getResponsavelBC() {
		return responsavelBC;
	}

	public void setResponsavelBC(ResponsavelBC responsavelBC) {
		this.responsavelBC = responsavelBC;
	}

	public TipoAquisicaoBC getTipoAquisicaoBC() {
		return tipoAquisicaoBC;
	}

	public void setTipoAquisicaoBC(TipoAquisicaoBC tipoAquisicaoBC) {
		this.tipoAquisicaoBC = tipoAquisicaoBC;
	}

	public SituacaoBC getSituacaoBC() {
		return situacaoBC;
	}

	public void setSituacaoBC(SituacaoBC situacaoBC) {
		this.situacaoBC = situacaoBC;
	}
	
	public List<TipoBem> getListTipoBem() {
		return tipoBemBC.findAll();
	}
		
	public List<Base> getListBase() {
		return baseBC.findAll();
	}
			
	public List<Marca> getListMarca() {
		return marcaBC.findAll();
	}
	
	public List<TipoAquisicao> getListTipoAquisicao() {
		return tipoAquisicaoBC.findAll();
	}
	
	public List<Localizacao> getListLocalizacao() {
		return localizacaoBC.findAll();
	}
	
	public List<Situacao> getListSituacao() {
		return situacaoBC.findAll();
	}
		
	public void atualizaSubTipoBem(){
				
		setSubTiposBensList(this.getBean().getTipoBem().getSubTiposBens());		
		
	}
	
	public void atualizaModelos(){
				
		setModelosList(this.getBean().getMarca().getModelos());
		
		
	}
	
	@Transactional
	public void atualizaMinisterio(){
		
		setMinisteriosList(this.getBean().getBase().getMinisterios());
				
		//Ao atualizar o Ministério, ou seja, ao escolher uma Base, o combo do Responsável fica em branco.
		//Checa se o responsavel é diferente de null e se a tela é de inserção.
		if((getBean().getResponsavel()!=null)&&(this.isUpdateMode()==false)){
		
		this.getBean().getResponsavel().setNome("");
		
		}
		
		//Checa se a tela é de alteração.
		if(this.isUpdateMode()==true){
			
			//Obtém a Base selecionada na tela.
			Base baseSelecionada = this.getBean().getBase();
			
			//Obtém a Base do banco do Bean que está sendo alterado.
			Base baseBanco = this.bemBC.obterBaseBD(this.getBean());
			
			//Se a Base selecionada for igual a Base do banco, seta no combo do Responsável, o Responsável do Bean que está sendo alterado.
			if((baseSelecionada.equals(baseBanco))&&(this.responsavelAlterado==false)){
				
				atualizaResponsavel();
				
				this.responsavelAlterado = true;
			
			//Caso contrário, limpa o combo do Responsável.	
			}else if(((baseSelecionada.equals(baseBanco))&&(this.responsavelAlterado==true))||(!baseSelecionada.equals(baseBanco))){
				
				this.getBean().getResponsavel().setNome("");
			
			}
		}
							
	}
	
	@Transactional
	public void atualizaResponsavel(){
		
		 	Responsavel responsavel =  this.responsavelBC.obterResponsavelMinisterio(this.getBean().getMinisterio());
	
			this.getBean().setResponsavel(responsavel);
	
				
	}
	
	
	
		
	public void setModelosList(List<Modelo> modelos) {
		this.modelos = modelos;
		
	}
	
	public List<Modelo> getModelosList() {
		
		if (this.getBean().getModelo() != null) {
			
			atualizaModelos();
		}
		
		return modelos;
		
	}
		
	public void setSubTiposBensList(List<SubTipoBem> subTiposBens) {
		this.subTiposBens = subTiposBens;
	}
	
	public List<SubTipoBem> getSubTiposBensList() {
		
		if (this.getBean().getSubTipoBem() != null) {
			
			 atualizaSubTipoBem();
			
		}
		return subTiposBens;
	}
	
	public void setMinisteriosList(List<Ministerio> ministerios) {
		this.ministerios = ministerios;
	}
	
	public List<Ministerio> getMinisteriosList() {
		
		if (this.getBean().getMinisterio() != null) {
			
			atualizaMinisterio();
					
		}
		return ministerios;
	}
		
	private String resultData = null;
	
	public String getResultData() {
		return resultData;
	}

	public void setResultData(String resultData) {
		this.resultData = resultData;
	}

	@Transactional
	public void verificaData(){
		
		boolean resultado;
		
		resultado = formatarData();
		
		if(resultado == false){
			
			setResultData("Data inválida!");
			
			getBean().setDataCompra(null);
			
			getBean().setValorAtualString(null);
								
		}else{
			
			setResultData("");
			
			setMsgValorAtual("");
			
			if(getBean().getValorHistoricoString()!=null){
				
				this.calculaValorAtual();
				
			}
			
		}
		
	}
	
	@Transactional
	public boolean formatarData(){
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setLenient(false);
		String data = null;
					
		try {
			data = getBean().getDataCompra();
						
			date = sdf.parse(data);
			
			if(date.after(new Date())){
				
				return false;
				
			}else{
				
				return true;
				
			}
									
		} catch (ParseException e) {
			
			return false;
			
			// TODO Auto-generated catch block
			//e.printStackTrace();
		} 
		
		
	}


	@Transactional
	public void calculaValorAtual(){
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setLenient(false);
		String data = null;
		
		// seta no atributo valorHistorico o valor convertido para double
		getBean().setValorHistorico(bemBC.converteStringDouble(getBean().getValorHistoricoString()));
		
		if(getBean().getDataCompra().equals(null)){
			
			setMsgValorAtual("Valor Atual não será calculado!");
			
		}else{
			
			data = getBean().getDataCompra();
			
			try {
				date = sdf.parse(data);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			// seta no atributo valorAtualString o valor calculado do atributo valorHistorico.
			getBean().setValorAtualString(bemBC.calculaValorAtualString(getBean().getValorHistorico(),date));
						
			setMsgValorAtual("");
			
		}
			
	}
	    
	/**
	 * Método responsável por gerar o valor do atributo tombo.
	 * 
	 */
	@Transactional
	public void geraTombo(){
		
		String maxTombo = null;
		
		// obtém o maior valor do atributo tombo.
		Integer tomboMaximo = this.bemBC.obterTomboMax();
		
		 
		if(tomboMaximo == null){
		
			// se não houver nenhum registro no banco, atribui o valor 1 aos atributos tombo e tomboInteger
			tomboMaximo = 1;
			
			getBean().setTomboInteger(tomboMaximo);
			
			maxTombo = Integer.toString(tomboMaximo);
			
			// preenche com zeros à esquerda até formar uma string com 6 caracteres.
			maxTombo = String.format("%06d", Integer.parseInt(maxTombo));
			
			getBean().setTombo(maxTombo);
			
		}else{
						
			tomboMaximo++;
			
			getBean().setTomboInteger(tomboMaximo);
					
			maxTombo = Integer.toString(tomboMaximo);
			
			maxTombo = String.format("%06d", Integer.parseInt(maxTombo));
			
			getBean().setTombo(maxTombo);
			
		}
		
				
	}
	

    
   
	

}