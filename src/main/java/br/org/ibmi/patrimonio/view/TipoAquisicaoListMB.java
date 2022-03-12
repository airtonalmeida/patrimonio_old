package br.org.ibmi.patrimonio.view;

import java.util.Iterator;
import java.util.List;
import javax.inject.Inject;
import br.gov.frameworkdemoiselle.annotation.NextView;
import br.gov.frameworkdemoiselle.annotation.PreviousView;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.AbstractListPageBean;
import br.gov.frameworkdemoiselle.transaction.Transactional;
import br.org.ibmi.patrimonio.business.TipoAquisicaoBC;
import br.org.ibmi.patrimonio.domain.TipoAquisicao;

@ViewController
@NextView("./tipoAquisicao_edit.jsf")
@PreviousView("./tipoAquisicao_list.jsf")
public class TipoAquisicaoListMB extends AbstractListPageBean<TipoAquisicao, Long> {

	private static final long serialVersionUID = 1L;

	@Inject
	private TipoAquisicaoBC tipoAquisicaoBC;
	
	@Override
	protected List<TipoAquisicao> handleResultList() {
		return this.tipoAquisicaoBC.findAll();
	}
	
	@Transactional
	public String deleteSelection() {
		boolean delete;
		for (Iterator<Long> iter = getSelection().keySet().iterator(); iter.hasNext();) {
			Long id = iter.next();
			delete = getSelection().get(id);
			if (delete) {
				tipoAquisicaoBC.delete(id);
				iter.remove();
			}
		}
		return getPreviousView();
	}

}