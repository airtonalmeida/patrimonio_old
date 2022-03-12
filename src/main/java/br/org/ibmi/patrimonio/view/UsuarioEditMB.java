package br.org.ibmi.patrimonio.view;

import java.util.List;

import javax.inject.Inject;
import br.gov.frameworkdemoiselle.annotation.PreviousView;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.AbstractEditPageBean;
import br.gov.frameworkdemoiselle.transaction.Transactional;
import br.org.ibmi.patrimonio.business.PapelBC;
import br.org.ibmi.patrimonio.business.UsuarioBC;
import br.org.ibmi.patrimonio.domain.Papel;
import br.org.ibmi.patrimonio.domain.Usuario;

@ViewController
@PreviousView("./usuario_list.jsf")
public class UsuarioEditMB extends AbstractEditPageBean<Usuario, Long> {

	private static final long serialVersionUID = 1L;

	@Inject
	private UsuarioBC usuarioBC;
	
	@Inject
	private PapelBC papelBC;
	
	@Override
	@Transactional
	public String delete() {
		this.usuarioBC.delete(getId());
		return getPreviousView();
	}
	
	@Override
	@Transactional
	public String insert() {
		this.usuarioBC.insert(getBean());
		return getPreviousView();
	}
	
	@Override
	@Transactional
	public String update() {
		this.usuarioBC.update(getBean());
		return getPreviousView();
	}
	
	@Override
	protected Usuario handleLoad(Long id) {
		return this.usuarioBC.load(id);
	}
	
	public List<Papel> getListPapel() {
		return papelBC.findAll();
	}
	
	public String cancelar() {
		return getPreviousView();
	}


}