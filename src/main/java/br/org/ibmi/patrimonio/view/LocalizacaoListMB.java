package br.org.ibmi.patrimonio.view;

import java.util.Iterator;
import java.util.List;
import javax.inject.Inject;
import br.gov.frameworkdemoiselle.annotation.NextView;
import br.gov.frameworkdemoiselle.annotation.PreviousView;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.AbstractListPageBean;
import br.gov.frameworkdemoiselle.transaction.Transactional;
import br.org.ibmi.patrimonio.business.LocalizacaoBC;
import br.org.ibmi.patrimonio.domain.Localizacao;

@ViewController
@NextView("./localizacao_edit.jsf")
@PreviousView("./localizacao_list.jsf")
public class LocalizacaoListMB extends AbstractListPageBean<Localizacao, Long> {

	private static final long serialVersionUID = 1L;

	@Inject
	private LocalizacaoBC localizacaoBC;
	
	@Override
	protected List<Localizacao> handleResultList() {
		return this.localizacaoBC.findAll();
	}
	
	@Transactional
	public String deleteSelection() {
		boolean delete;
		for (Iterator<Long> iter = getSelection().keySet().iterator(); iter.hasNext();) {
			Long id = iter.next();
			delete = getSelection().get(id);
			if (delete) {
				localizacaoBC.delete(id);
				iter.remove();
			}
		}
		return getPreviousView();
	}

}