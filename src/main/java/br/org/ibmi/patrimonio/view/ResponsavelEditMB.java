package br.org.ibmi.patrimonio.view;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import org.primefaces.context.RequestContext;
import br.gov.frameworkdemoiselle.annotation.PreviousView;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.AbstractEditPageBean;
import br.gov.frameworkdemoiselle.transaction.Transactional;
import br.org.ibmi.patrimonio.business.MinisterioBC;
import br.org.ibmi.patrimonio.business.ResponsavelBC;
import br.org.ibmi.patrimonio.domain.Ministerio;
import br.org.ibmi.patrimonio.domain.Responsavel;

@ViewController
@PreviousView("./responsavel_list.jsf")
public class ResponsavelEditMB extends AbstractEditPageBean<Responsavel, Long> {

	private static final long serialVersionUID = 1L;

	@Inject
	private ResponsavelBC responsavelBC;
	
	@Inject
	private MinisterioBC ministerioBC;
	
	boolean acessoUpdate = true;
	
	Responsavel responsavelUpdate = new Responsavel();
	
	@Override
	@Transactional
	public String delete() {
		this.responsavelBC.delete(getId());
		return getPreviousView();
	}
	
	@Override
	@Transactional
	public String insert() {
		
		if((this.getBean().getNome()=="")||(this.getBean().getMinisterio()==null)){
			
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "AVISO", "Infome o Nome e o Ministério!");
			
			RequestContext.getCurrentInstance().showMessageInDialog(message);
			
			return null;
			
		}else{
		
			boolean responsavelExiste;
			
			responsavelExiste = this.verificaResponsavelExiste();
			
			if(responsavelExiste == false){
			
				this.responsavelBC.insert(getBean());
				return getPreviousView();
				
			}else{
				
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "AVISO", "Não foi possível inserir! Nome e/ou Ministério já cadastrado(s) para Responsável.");
				
				RequestContext.getCurrentInstance().showMessageInDialog(message);
				
				return null;
			}
			
		}
			
	}
	
	@Override
	@Transactional
	public String update() {
		
		if((this.getBean().getNome()=="")||(this.getBean().getMinisterio()==null)){
			
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "AVISO", "Infome o Nome e o Ministério!");
			
			RequestContext.getCurrentInstance().showMessageInDialog(message);
			
			return null;
			
		}else{
		
			boolean responsavelExiste;
			
			responsavelExiste = this.verificaResponsavelExiste();
			
			if(responsavelExiste == false){
			
				this.responsavelBC.update(getBean());
				return getPreviousView();
				
			}else{
				
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "AVISO", "Não foi possível inserir! Nome e/ou Ministério já cadastrado(s) para Responsável.");
				
				RequestContext.getCurrentInstance().showMessageInDialog(message);
				
				return null;
			}
			
		}	
		
	}
	
	public Boolean verificaResponsavelExiste(){
		boolean resultado = false;
				
		
		//verifica se a função é para inserir um responsável novo ou para alterar existente.
		if(this.isUpdateMode()==false){
			
			//Verifica se há Responsável cadastrado com o Ministério selecionado
			Responsavel minResponsavelBD = this.responsavelBC.verificaMinResponsavelExiste(getBean());
			
			//Verifica se há Responsável cadastrado com o nome informado
			Responsavel nomeResponsavelBD = this.responsavelBC.verificaNomeResponsavelExiste(getBean());
											
			//Se o nome e o ministério do Responsável não tiverem sido cadastrados retorna falso, ou seja, permite-se inserir.
			if((minResponsavelBD==null && nomeResponsavelBD==null)){
				
				resultado = false;
			
			//Se o nome ou ministério do Responsável tiverem sido cadastrado retorna verdadeiro, ou seja, não será permitido inserir.
			}else if(minResponsavelBD!=null || nomeResponsavelBD!=null){
				
				resultado = true;
			}
			
		}else{
			
			resultado = true;
			
			Responsavel responsavelBanco = this.responsavelBC.verificaResponsavelUpdate(init());
						
			//Verifica se o Responsável retornado do banco é diferente de null, ou seja, o Responsável que o usuário
			//está tentando salvar já existe no banco. Não será possível realizar a alteração.
			if(responsavelBanco!=null){
				
				resultado = false;
				
				resultado = true;
							
			}else{
				
				
				//Verifica se há Responsável cadastrado com o Ministério selecionado
				Responsavel minResponsavelBD2 = this.responsavelBC.verificaMinResponsavelExiste(init());
				
				//Verifica se há Responsável cadastrado com o nome informado
				Responsavel nomeResponsavelBD2 = this.responsavelBC.verificaNomeResponsavelExiste(init());
				
				
				//Se o nome do Responsável não existir no banco e o ministério não tiver sido modificado, será possível realizar a alteração.
				if((nomeResponsavelBD2==null) && (minResponsavelBD2.getMinisterio().equals(getBean().getMinisterio()))){
				
					resultado = false;
				
				//Se o ministério do Responsável não existir no banco e o nome não tiver sido modificado, será possível realizar a alteração.
				}else if((minResponsavelBD2==null) && (nomeResponsavelBD2.getNome().equals(getBean().getNome()))){
				
					resultado = false;
				
				}
							
			}
			
		}
				
		return resultado;
				
	}
	
	@PostConstruct
    public void postConstruct(){
            init();
    }
		
	public Responsavel init(){
		
		if((this.acessoUpdate == true)&&(this.isUpdateMode()==true)){
			
			this.responsavelUpdate = this.getBean();
						
			this.acessoUpdate = false;
		}
		
		return this.responsavelUpdate;
		
	}
	
	
		
	
	public String cancelar() {
		return getPreviousView();
	}
	
	@Override
	protected Responsavel handleLoad(Long id) {
		return this.responsavelBC.load(id);
	}
	
	public List<Ministerio> getListMinisterio() {
		return ministerioBC.findAll();
	}
	
	
	 
	
	

	
}