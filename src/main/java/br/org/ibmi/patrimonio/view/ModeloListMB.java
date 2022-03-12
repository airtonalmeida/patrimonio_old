package br.org.ibmi.patrimonio.view;

import java.util.Iterator;
import java.util.List;
import javax.inject.Inject;
import br.gov.frameworkdemoiselle.annotation.NextView;
import br.gov.frameworkdemoiselle.annotation.PreviousView;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.AbstractListPageBean;
import br.gov.frameworkdemoiselle.transaction.Transactional;
import br.org.ibmi.patrimonio.business.ModeloBC;
import br.org.ibmi.patrimonio.domain.Modelo;

@ViewController
@NextView("./modelo_edit.jsf")
@PreviousView("./modelo_list.jsf")
public class ModeloListMB extends AbstractListPageBean<Modelo, Long> {

	private static final long serialVersionUID = 1L;

	@Inject
	private ModeloBC modeloBC;
	
	@Override
	protected List<Modelo> handleResultList() {
		return this.modeloBC.findAll();
	}
	
	@Transactional
	public String deleteSelection() {
		boolean delete;
		for (Iterator<Long> iter = getSelection().keySet().iterator(); iter.hasNext();) {
			Long id = iter.next();
			delete = getSelection().get(id);
			if (delete) {
				modeloBC.delete(id);
				iter.remove();
			}
		}
		return getPreviousView();
	}

}