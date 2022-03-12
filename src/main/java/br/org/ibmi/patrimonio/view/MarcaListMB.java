package br.org.ibmi.patrimonio.view;

import java.util.Iterator;
import java.util.List;
import javax.inject.Inject;
import br.gov.frameworkdemoiselle.annotation.NextView;
import br.gov.frameworkdemoiselle.annotation.PreviousView;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.AbstractListPageBean;
import br.gov.frameworkdemoiselle.transaction.Transactional;
import br.org.ibmi.patrimonio.business.MarcaBC;
import br.org.ibmi.patrimonio.domain.Marca;

@ViewController
@NextView("./marca_edit.jsf")
@PreviousView("./marca_list.jsf")
public class MarcaListMB extends AbstractListPageBean<Marca, Long> {

	private static final long serialVersionUID = 1L;

	@Inject
	private MarcaBC marcaBC;
	
	@Override
	protected List<Marca> handleResultList() {
		return this.marcaBC.findAll();
	}
	
	@Transactional
	public String deleteSelection() {
		boolean delete;
		for (Iterator<Long> iter = getSelection().keySet().iterator(); iter.hasNext();) {
			Long id = iter.next();
			delete = getSelection().get(id);
			if (delete) {
				marcaBC.delete(id);
				iter.remove();
			}
		}
		return getPreviousView();
	}

}