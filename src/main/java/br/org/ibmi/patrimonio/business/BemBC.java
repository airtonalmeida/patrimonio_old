package br.org.ibmi.patrimonio.business;



import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.template.DelegateCrud;
import br.org.ibmi.patrimonio.domain.Base;
import br.org.ibmi.patrimonio.domain.Bem;
import br.org.ibmi.patrimonio.domain.Ministerio;
import br.org.ibmi.patrimonio.persistence.BemDAO;
 


@BusinessController
public class BemBC extends DelegateCrud<Bem, Long, BemDAO> {
	
	private static final long serialVersionUID = 1L;
		
	public List<Bem> consultarListBemTomboBC(Bem bem) {
		List<Bem> consultarListBemPorTombo = getDelegate()
				.consultarListBemPorTombo(bem);
		return consultarListBemPorTombo;
	}
	
	public Bem consultarBemCodigoBC(Long id) {
		Bem bemCodigo = getDelegate()
				.consultarBemCodigo(id);
		return bemCodigo;
	}
	
	public List <Bem> consultarListBemMinisterioBC(Bem bem) {
		List <Bem> consultarListBemPorMinisterio = getDelegate()
				.consultarListBemPorMinisterio(bem);
		return consultarListBemPorMinisterio;
	}
	
	public List <Bem> consultarListBemSubTipoBemBC(Bem bem) {
		List <Bem> consultarListBemPorSubTipoBem = getDelegate()
				.consultarListBemPorSubTipoBem(bem);
		return consultarListBemPorSubTipoBem;
	}
	
	public List <Bem> consultarListBemResponsavelBC(Bem bem) {
		List <Bem> consultarListBemPorResponsavel = getDelegate()
				.consultarListBemPorResponsavel(bem);
		return consultarListBemPorResponsavel;
	}
	
	public List <Bem> consultarListBemBaseBC(Bem bem) {
		List <Bem> consultarListBemPorBase = getDelegate()
				.consultarListBemPorBase(bem);
		return consultarListBemPorBase;
	}
	
	public List <Bem> conListBemSubTpBemBaseBC(Bem bem) {
		List <Bem> conListBemSubTpBemBase = getDelegate()
				.conListBemSubTpBemBase(bem);
		return conListBemSubTpBemBase;
	}
	
	public List <Bem> conListBemSubTpBemMinBC(Bem bem) {
		List <Bem> conListBemSubTpBemMin = getDelegate()
				.conListBemSubTpBemMin(bem);
		return conListBemSubTpBemMin;
	}
	
	public List <Bem> conListBemLocalizacaoMinBC(Bem bem) {
		List <Bem> conListBemLocalizacaoMin = getDelegate()
				.conListBemLocalizacaoMin(bem);
		return conListBemLocalizacaoMin;
	}
	
	public List <Bem> conListBemSubTpBemResBC(Bem bem) {
		List <Bem> conListBemSubTpBemRes = getDelegate()
				.conListBemSubTpBemRes(bem);
		return conListBemSubTpBemRes;
	}
	
	public List <Bem> relBensComReservaValorBC() {
		List <Bem> relBensComReservaValor = getDelegate()
				.relBensComReservaValor();
		return relBensComReservaValor;
	}
	
	public List <Bem> relBensSemReservaValorBC() {
		List <Bem> relBensSemReservaValor = getDelegate()
				.relBensSemReservaValor();
		return relBensSemReservaValor;
	}	
	
	
	public Integer obterTomboMax() {
		Integer tomboMaximo = getDelegate()
				.obterTomboMax();
		return tomboMaximo;
	}
	
	public Base obterBaseBD(Bem bem){
		
		Base baseBD = getDelegate()
				.obterBaseBD(bem);
			
		return baseBD;
	}
	
	public Ministerio obterMinisterioBD(Bem bem){
		
		Ministerio minBanco = getDelegate()
				.obterMinisterioBD(bem);
		
		return minBanco;
	}
	
	/**
	 * converte o valor histórico do bem de string para double
	 * 
	 * @param valorHistoricoString
	 * @return
	 */
	public Double converteStringDouble (String valorHistoricoString){
		DecimalFormat formato = new DecimalFormat("");
		
		double valor = 0;
		try {
			formato.applyLocalizedPattern("#.#00,0#");
			valor = formato.parse(valorHistoricoString).doubleValue();
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return valor;
		
	}
	
	public String converteDoubleString (Double valor){
		
		NumberFormat df = NumberFormat.getCurrencyInstance();
		
		String dx = df.format(valor);
							
		return dx.substring(3);	
		
	}
	

	/**
	 * calcula o valor atual do bem baseado no valorHistorico. Por cada ano o valor histórico sofre um abatimento de 20%. 
	 * 
	 * @param valorAtualFormatado
	 * @param dataCompra
	 * @return
	 */
	public String calculaValorAtualString (Double valorHistorico, Date dataCompra){
		Double resultado = valorHistorico;
		
		Calendar dataAtual = Calendar.getInstance();
		
		Calendar dc = new GregorianCalendar();
		
		dc.setTime(dataCompra);
				
		int diferenca = this.diferencaEmDias(dc, dataAtual);
		
		if((diferenca >= 365)&& (diferenca < 730)){
			
			resultado = resultado * 0.8;
			
		}else if((diferenca >= 730)&& (diferenca < 1095)){
			
			resultado = resultado * 0.6;
		}else if((diferenca >= 1095)&& (diferenca < 1460)){
			
			resultado = resultado * 0.4;
		}else if(diferenca >= 1460){
			
			resultado = resultado * 0.2;
		}
				
		NumberFormat df = NumberFormat.getCurrencyInstance();
		
		String dx = df.format(resultado);
							
		return dx.substring(3);	
		
	}
	
	public int diferencaEmDias(Calendar c1, Calendar c2){
       long m1 = c1.getTimeInMillis();
       
       long m2 = c2.getTimeInMillis();
       
       return (int) ((m2 - m1) / (24*60*60*1000));
       
    }
	
	
	
	
}
