package br.org.ibmi.patrimonio.util.relatorio;


import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import javax.inject.Inject;
import javax.servlet.http.HttpServlet;
import net.sf.jasperreports.engine.JRException;
import br.gov.frameworkdemoiselle.report.Report;
import br.gov.frameworkdemoiselle.report.Type;
import br.gov.frameworkdemoiselle.report.annotation.Path;
import br.gov.frameworkdemoiselle.util.FileRenderer;
import br.org.ibmi.patrimonio.domain.Bem;



public class Relatorio extends HttpServlet implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	@Path ("reports/TodosBens.jrxml")
	Report relatorio1;
	
	@Inject
	private FileRenderer renderer;
	
		
	public void showReportTodosBens(Collection<Bem> list) throws JRException {
		Map<String, Object> param = new HashMap<String, Object>();
		byte[] buffer = this.relatorio1.export(list,param, Type.PDF);
	    this.renderer.render(buffer, FileRenderer.ContentType.PDF,
		 "TodosBens.pdf");

	}
	
	@Inject
	@Path ("reports/BensBase.jrxml")
	Report relatorio2;
	
	public void showReportBase(Collection<Bem> list) throws JRException {
		Map<String, Object> param = new HashMap<String, Object>();
		byte[] buffer = this.relatorio2.export(list,param, Type.PDF);
	    this.renderer.render(buffer, FileRenderer.ContentType.PDF,
		 "ListaBensBase.pdf");
	    
	}
	
	@Inject
	@Path ("reports/BensMinisterio.jrxml")
	Report relatorio3;
	
	public void showReportMinisterio(Collection<Bem> list) throws JRException {
		Map<String, Object> param = new HashMap<String, Object>();
		byte[] buffer = this.relatorio3.export(list,param, Type.PDF);
	    this.renderer.render(buffer, FileRenderer.ContentType.PDF,
		 "ListaBensMinisterio.pdf");

	}
	
	@Inject
	@Path ("reports/BensSubTipoBem.jrxml")
	Report relatorio4;
	
	public void showReportSubTipoBem(Collection<Bem> list) throws JRException {
		Map<String, Object> param = new HashMap<String, Object>();
		byte[] buffer = this.relatorio4.export(list,param, Type.PDF);
	    this.renderer.render(buffer, FileRenderer.ContentType.PDF,
		 "ListaBensSubTipoBem.pdf");

	}
	
	@Inject
	@Path ("reports/BensResponsavel.jrxml")
	Report relatorio5;
	
	public void showReportResponsavel(Collection<Bem> list) throws JRException {
		Map<String, Object> param = new HashMap<String, Object>();
		byte[] buffer = this.relatorio5.export(list,param, Type.PDF);
	    this.renderer.render(buffer, FileRenderer.ContentType.PDF,
		 "ListaBensResponsavel.pdf");

	}
	
	
	@Inject
	@Path ("reports/BensSubTipoBemMinisterio.jrxml")
	Report relatorio6;
	
	public void showReportSTBMinisterio(Collection<Bem> list) throws JRException {
		Map<String, Object> param = new HashMap<String, Object>();
		byte[] buffer = this.relatorio6.export(list,param, Type.PDF);
	    this.renderer.render(buffer, FileRenderer.ContentType.PDF,
		 "ListaBensSubTipoBemMinisterio.pdf");

	}
	
	@Inject
	@Path ("reports/BensSubTipoBemResponsavel.jrxml")
	Report relatorio7;
	
	public void showReportSTBResponsavel(Collection<Bem> list) throws JRException {
		Map<String, Object> param = new HashMap<String, Object>();
		byte[] buffer = this.relatorio7.export(list,param, Type.PDF);
	    this.renderer.render(buffer, FileRenderer.ContentType.PDF,
		 "ListaBensSubTipoBemResponsavel.pdf");

	}
	
	@Inject
	@Path ("reports/BensSubTipoBemBase.jrxml")
	Report relatorio8;
	
	public void showReportSTBBase(Collection<Bem> list) throws JRException {
		Map<String, Object> param = new HashMap<String, Object>();
		byte[] buffer = this.relatorio8.export(list,param, Type.PDF);
	    this.renderer.render(buffer, FileRenderer.ContentType.PDF,
		 "ListaBensSubTipoBemBase.pdf");

	}
	
	@Inject
	@Path ("reports/BensComReservaValor.jrxml")
	Report relatorio9;
	
	public void reportComReservaValor(Collection<Bem> list) throws JRException {
		Map<String, Object> param = new HashMap<String, Object>();
		byte[] buffer = this.relatorio9.export(list,param, Type.PDF);
	    this.renderer.render(buffer, FileRenderer.ContentType.PDF,
		 "RelBensComReservaValor.pdf");

	}
	
	@Inject
	@Path ("reports/BensSemReservaValor.jrxml")
	Report relatorio10;
	
	public void reportSemReservaValor(Collection<Bem> list) throws JRException {
		Map<String, Object> param = new HashMap<String, Object>();
		byte[] buffer = this.relatorio10.export(list,param, Type.PDF);
	    this.renderer.render(buffer, FileRenderer.ContentType.PDF,
		 "RelBensSemReservaValor.pdf");

	}
	
	@Inject
	@Path ("reports/BensLocalizacaoMinisterio.jrxml")
	Report relatorio11;
	
	public void showReportLocalizacaoMinisterio(Collection<Bem> list) throws JRException {
		Map<String, Object> param = new HashMap<String, Object>();
		byte[] buffer = this.relatorio11.export(list,param, Type.PDF);
	    this.renderer.render(buffer, FileRenderer.ContentType.PDF,
		 "ListaBensLocalizacaoMinisterio.pdf");

	}

}
