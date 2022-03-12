package br.org.ibmi.patrimonio.view;

import java.util.Iterator;
import java.util.List;
import javax.inject.Inject;
import br.gov.frameworkdemoiselle.annotation.NextView;
import br.gov.frameworkdemoiselle.annotation.PreviousView;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.AbstractListPageBean;
import br.gov.frameworkdemoiselle.transaction.Transactional;
import br.org.ibmi.patrimonio.business.MinisterioBC;
import br.org.ibmi.patrimonio.domain.Ministerio;

@ViewController
@NextView("./ministerio_edit.jsf")
@PreviousView("./ministerio_list.jsf")
public class MinisterioListMB extends AbstractListPageBean<Ministerio, Long> {

	private static final long serialVersionUID = 1L;

	@Inject
	private MinisterioBC ministerioBC;
	
	@Override
	protected List<Ministerio> handleResultList() {
		return this.ministerioBC.findAll();
	}
	
	@Transactional
	public String deleteSelection() {
		boolean delete;
		for (Iterator<Long> iter = getSelection().keySet().iterator(); iter.hasNext();) {
			Long id = iter.next();
			delete = getSelection().get(id);
			if (delete) {
				ministerioBC.delete(id);
				iter.remove();
			}
		}
		return getPreviousView();
	}

}