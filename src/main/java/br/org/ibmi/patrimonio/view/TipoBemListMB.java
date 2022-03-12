package br.org.ibmi.patrimonio.view;

import java.util.Iterator;
import java.util.List;
import javax.inject.Inject;
import br.gov.frameworkdemoiselle.annotation.NextView;
import br.gov.frameworkdemoiselle.annotation.PreviousView;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.AbstractListPageBean;
import br.gov.frameworkdemoiselle.transaction.Transactional;
import br.org.ibmi.patrimonio.business.TipoBemBC;
import br.org.ibmi.patrimonio.domain.TipoBem;

@ViewController
@NextView("./tipoBem_edit.jsf")
@PreviousView("./tipoBem_list.jsf")
public class TipoBemListMB extends AbstractListPageBean<TipoBem, Long> {

	private static final long serialVersionUID = 1L;

	@Inject
	private TipoBemBC tipoBemBC;
	
	@Override
	protected List<TipoBem> handleResultList() {
		return this.tipoBemBC.findAll();
	}
	
	@Transactional
	public String deleteSelection() {
		boolean delete;
		for (Iterator<Long> iter = getSelection().keySet().iterator(); iter.hasNext();) {
			Long id = iter.next();
			delete = getSelection().get(id);
			if (delete) {
				tipoBemBC.delete(id);
				iter.remove();
			}
		}
		return getPreviousView();
	}

}