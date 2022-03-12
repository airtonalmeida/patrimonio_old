package br.org.ibmi.patrimonio.view;

import java.util.Iterator;
import java.util.List;
import javax.inject.Inject;
import br.gov.frameworkdemoiselle.annotation.NextView;
import br.gov.frameworkdemoiselle.annotation.PreviousView;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.AbstractListPageBean;
import br.gov.frameworkdemoiselle.transaction.Transactional;
import br.org.ibmi.patrimonio.business.BaseBC;
import br.org.ibmi.patrimonio.domain.Base;

@ViewController
@NextView("./base_edit.jsf")
@PreviousView("./base_list.jsf")
public class BaseListMB extends AbstractListPageBean<Base, Long> {

	private static final long serialVersionUID = 1L;

	@Inject
	private BaseBC baseBC;
	
	@Override
	protected List<Base> handleResultList() {
		return this.baseBC.findAll();
	}
	
	@Transactional
	public String deleteSelection() {
		boolean delete;
		for (Iterator<Long> iter = getSelection().keySet().iterator(); iter.hasNext();) {
			Long id = iter.next();
			delete = getSelection().get(id);
			if (delete) {
				baseBC.delete(id);
				iter.remove();
			}
		}
		return getPreviousView();
	}

}