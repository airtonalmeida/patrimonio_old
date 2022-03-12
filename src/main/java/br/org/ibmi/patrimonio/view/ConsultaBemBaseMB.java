package br.org.ibmi.patrimonio.view;



import java.io.IOException;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.primefaces.context.RequestContext;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import br.gov.frameworkdemoiselle.annotation.NextView;
import br.gov.frameworkdemoiselle.annotation.PreviousView;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.AbstractEditPageBean;
import br.gov.frameworkdemoiselle.transaction.Transactional;

import br.org.ibmi.patrimonio.business.BaseBC;
import br.org.ibmi.patrimonio.business.BemBC;
import br.org.ibmi.patrimonio.domain.Base;
import br.org.ibmi.patrimonio.domain.Bem;
import br.org.ibmi.patrimonio.util.relatorio.Relatorio;

@ViewController
@NextView("./bem_edit.jsf")
@PreviousView("./consultaPorBase.jsf")
public class ConsultaBemBaseMB extends AbstractEditPageBean <Bem, Long> {

	private static final long serialVersionUID = 1L;
		
	private List <Bem> listBem;
	
	@Inject
	private BemBC bemBC;
	
	@Inject
	private BaseBC baseBC;
	
	private List<Bem> resultado = new ArrayList<Bem>();
	
	@Inject
	Relatorio relatorio;
	
	private JasperPrint impressao;
    private HashMap<String, Serializable> parametroMap;
    private FacesContext context;
    private ServletContext servletContext;
    private String mensagem, caminhoRelatorio;
	
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
	
	public List<Base> getListBase() {
		return baseBC.findAll();
	}
	
	public BaseBC getBaseBC() {
		return baseBC;
	}

	public void setBaseBC(BaseBC baseBC) {
		this.baseBC = baseBC;
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
	
	private String totalValorAtual = "";
	
	public String getTotalValorAtual() {
		return totalValorAtual;
	}

	public void setTotalValorAtual(String totalValorAtual) {
		this.totalValorAtual = totalValorAtual;
	}
	
	@Transactional
	public void consultarBemPorBase() {
		
		if(getBean().getBase()==null){
			
			setListBem(null);
			
			this.setTotalBens(null);
			
			this.setTotalValorAtual("");
			
			FacesMessage messageNull = new FacesMessage(FacesMessage.SEVERITY_INFO, "AVISO", "Selecione a Base!");
			
			RequestContext.getCurrentInstance().showMessageInDialog(messageNull);
			
		}else{
		
			resultado = bemBC.consultarListBemBaseBC(getBean());
	
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
				
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "AVISO", "Não há Bem cadastrado para a Base " + getBean().getBase().getDescricao());
				
				RequestContext.getCurrentInstance().showMessageInDialog(message);
				
				
								
			}
		}
	}
	
	@Transactional
	public String showReport() throws JRException, IOException {
		
		if(getBean().getBase()==null){
			
			setListBem(null);
			
			this.setTotalBens(null);
			
			this.setTotalValorAtual("");
			
			FacesMessage messageNull = new FacesMessage(FacesMessage.SEVERITY_INFO, "AVISO", "Selecione a Base!");
			
			RequestContext.getCurrentInstance().showMessageInDialog(messageNull);
			
		}else{		
		
			resultado = bemBC.consultarListBemBaseBC(getBean());
	
			if(!resultado.isEmpty()){
				
				relatorio.showReportBase(resultado);
				
			}else{
				
				setListBem(null);
				
				this.setTotalBens(null);
				
				this.setTotalValorAtual("");
								
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "AVISO", "Não há Bem cadastrado para a Base " + getBean().getBase().getDescricao());
				
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
	
	public void geraRelatorio() {
		
		Long base = this.getBean().getBase().getCodigo();
		
        context = FacesContext.getCurrentInstance();
        servletContext = (ServletContext) context.getExternalContext().getContext();
 
        caminhoRelatorio = servletContext.getRealPath("WEB-INF/classes/reports/BensBase.jasper");
       
        parametroMap = new HashMap<String, Serializable>();
               
        parametroMap.put("base", base);
       
        enviarPdf();
    }
	
	public void enviarPdf() {
        // Carrega o xml de definição do relatório
        try {
        	
        	Connection conexao = this.getConexao();
        	      	
            HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
            // Configura o response para suportar o relatório
            response.setContentType("application/pdf");
            //response.addHeader("Content-disposition", "inline; filename=\"arquivo.pdf\"");
            response.addHeader("Content-disposition", "attachment; filename=\"BensPorBase.pdf\"");
 
            // Preenche o relatório com os parametros e o data source
            impressao = JasperFillManager.fillReport(caminhoRelatorio, parametroMap, conexao);
           
            // Exporta o relatório
            JasperExportManager.exportReportToPdfStream(impressao, response.getOutputStream());
            // Salva o estado da aplicação no contexto do JSF
            context.getApplication().getStateManager().saveView(context);
            // Fecha o stream do response
            context.responseComplete();
        } catch (Exception e) {
           
        }
    }
	
	
	public  Connection getConexao() {
		Connection conn = null;
		try {
			// Carrega o driver JDBC especfico para o banco de dados MySQL
			String driver = "com.mysql.jdbc.Driver";
			Class.forName(driver);
			// Estabelece a conexao com o banco de dados
			String url = "jdbc:mysql://127.0.0.1?user=root&password=123456";
			conn = DriverManager.getConnection(url);
			// Se chegou porque a conexo foi feita com successo
			System.out.println("Conexao estabelecida com sucesso");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	

}