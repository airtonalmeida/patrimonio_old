package br.org.ibmi.patrimonio.view;

import java.util.Iterator;
import java.util.List;
import javax.inject.Inject;
import br.gov.frameworkdemoiselle.annotation.NextView;
import br.gov.frameworkdemoiselle.annotation.PreviousView;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.AbstractListPageBean;
import br.gov.frameworkdemoiselle.transaction.Transactional;
import br.org.ibmi.patrimonio.business.SubTipoBemBC;
import br.org.ibmi.patrimonio.domain.SubTipoBem;

@ViewController
@NextView("./subTipoBem_edit.jsf")
@PreviousView("./subTipoBem_list.jsf")
public class SubTipoBemListMB extends AbstractListPageBean<SubTipoBem, Long> {

	private static final long serialVersionUID = 1L;

	@Inject
	private SubTipoBemBC subTipoBemBC;
	
	@Override
	protected List<SubTipoBem> handleResultList() {
		return this.subTipoBemBC.findAll();
	}
	
	@Transactional
	public String deleteSelection() {
		boolean delete;
		for (Iterator<Long> iter = getSelection().keySet().iterator(); iter.hasNext();) {
			Long id = iter.next();
			delete = getSelection().get(id);
			if (delete) {
				subTipoBemBC.delete(id);
				iter.remove();
			}
		}
		return getPreviousView();
	}

}