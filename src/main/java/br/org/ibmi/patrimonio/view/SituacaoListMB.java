package br.org.ibmi.patrimonio.view;

import java.util.Iterator;
import java.util.List;
import javax.inject.Inject;
import br.gov.frameworkdemoiselle.annotation.NextView;
import br.gov.frameworkdemoiselle.annotation.PreviousView;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.AbstractListPageBean;
import br.gov.frameworkdemoiselle.transaction.Transactional;
import br.org.ibmi.patrimonio.business.SituacaoBC;
import br.org.ibmi.patrimonio.domain.Situacao;

@ViewController
@NextView("./situacao_edit.jsf")
@PreviousView("./situacao_list.jsf")
public class SituacaoListMB extends AbstractListPageBean<Situacao, Long> {

	private static final long serialVersionUID = 1L;

	@Inject
	private SituacaoBC situacaoBC;
	
	@Override
	protected List<Situacao> handleResultList() {
		return this.situacaoBC.findAll();
	}
	
	@Transactional
	public String deleteSelection() {
		boolean delete;
		for (Iterator<Long> iter = getSelection().keySet().iterator(); iter.hasNext();) {
			Long id = iter.next();
			delete = getSelection().get(id);
			if (delete) {
				situacaoBC.delete(id);
				iter.remove();
			}
		}
		return getPreviousView();
	}

}