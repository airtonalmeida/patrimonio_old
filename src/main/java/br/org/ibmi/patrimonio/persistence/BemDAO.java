package br.org.ibmi.patrimonio.persistence;

import java.util.List;
import javax.persistence.Query;
import br.gov.frameworkdemoiselle.stereotype.PersistenceController;
import br.gov.frameworkdemoiselle.template.JPACrud;
import br.org.ibmi.patrimonio.domain.Base;
import br.org.ibmi.patrimonio.domain.Bem;
import br.org.ibmi.patrimonio.domain.Ministerio;




@PersistenceController
public class BemDAO extends JPACrud<Bem, Long> {

	private static final long serialVersionUID = 1L;
	
		
	
	@SuppressWarnings("unchecked")
	public List<Bem> consultarListBemPorTombo(Bem bem){
		
		Query query = getEntityManager().createQuery("select b from Bem b where b.tombo = :tombo");
		query.setParameter("tombo", bem.getTombo());
		List<Bem> result = query.getResultList();
		return result;
	}
	
	public Bem consultarBemCodigo(Long id){
		
		Query query = getEntityManager().createQuery("select b from Bem b where b.codigo = :id");
		query.setParameter("id", id);
		Bem result = (Bem) query.getSingleResult();
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public List <Bem> consultarListBemPorMinisterio(Bem bem){
		
		Query query = getEntityManager().createQuery("select b from Bem b where b.ministerio = :ministerio");
		query.setParameter("ministerio", bem.getMinisterio());
		List<Bem> result = query.getResultList();
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public List <Bem> consultarListBemPorSubTipoBem(Bem bem){
		
		Query query = getEntityManager().createQuery("select b from Bem b where b.subTipoBem = :subTipoBem");
		query.setParameter("subTipoBem", bem.getSubTipoBem());
		List<Bem> result = query.getResultList();
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public List <Bem> consultarListBemPorResponsavel(Bem bem){
		
		Query query = getEntityManager().createQuery("select b from Bem b where b.responsavel = :responsavel");
		query.setParameter("responsavel", bem.getResponsavel());
		List<Bem> result = query.getResultList();
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public List <Bem> consultarListBemPorBase(Bem bem){
		
		Query query = getEntityManager().createQuery("select b from Bem b where b.base = :base");
		query.setParameter("base", bem.getBase());
		List<Bem> result = query.getResultList();
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public List <Bem> conListBemSubTpBemBase(Bem bem){
				
		Query query = getEntityManager().createQuery("select b from Bem b where b.subTipoBem = :subTipoBem and b.base = :base");
		query.setParameter("subTipoBem", bem.getSubTipoBem());
		query.setParameter("base", bem.getBase());
		List<Bem> result = query.getResultList();
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public List <Bem> conListBemSubTpBemMin(Bem bem){
				
		Query query = getEntityManager().createQuery("select b from Bem b where b.subTipoBem = :subTipoBem and b.ministerio = :ministerio");
		query.setParameter("subTipoBem", bem.getSubTipoBem());
		query.setParameter("ministerio", bem.getMinisterio());
		List<Bem> result = query.getResultList();
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public List <Bem> conListBemLocalizacaoMin(Bem bem){
				
		Query query = getEntityManager().createQuery("select b from Bem b where b.localizacao = :localizacao and b.ministerio = :ministerio");
		query.setParameter("localizacao", bem.getLocalizacao());
		query.setParameter("ministerio", bem.getMinisterio());
		List<Bem> result = query.getResultList();
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public List <Bem> conListBemSubTpBemRes(Bem bem){
				
		Query query = getEntityManager().createQuery("select b from Bem b where b.subTipoBem = :subTipoBem and b.responsavel = :responsavel");
		query.setParameter("subTipoBem", bem.getSubTipoBem());
		query.setParameter("responsavel", bem.getResponsavel());
		List<Bem> result = query.getResultList();
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public List <Bem> relBensComReservaValor(){
				
		Query query = getEntityManager().createQuery("select b from Bem b where b.subTipoBem.classificacao.descricao = 'COM RESERVA DE VALOR' ");
		
		List<Bem> result = query.getResultList();
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public List <Bem> relBensSemReservaValor(){
				
		Query query = getEntityManager().createQuery("select b from Bem b where b.subTipoBem.classificacao.descricao = 'SEM RESERVA DE VALOR' ");
		
		List<Bem> result = query.getResultList();
		return result;
	}
	
	
	
	public Base obterBaseBD(Bem bem){
		Query query = getEntityManager().createQuery("select b.base from Bem b where b.codigo = :codBem");
		query.setParameter("codBem", bem.getCodigo());
		Base result = (Base)query.getSingleResult();
		return result;
	}
	
	public Ministerio obterMinisterioBD(Bem bem){
		
		Query query = getEntityManager().createQuery("select b.ministerio from Bem b where b.codigo = :codBem");
		query.setParameter("codBem", bem.getCodigo());
		Ministerio result = (Ministerio)query.getSingleResult();
		return result;
	
	}
	
	
	public Integer obterTomboMax() {
		
		Query query = getEntityManager().createQuery("select max(b.tomboInteger) from Bem b");
		Integer result = (Integer)query.getSingleResult();
		return result;
	}
	

}
