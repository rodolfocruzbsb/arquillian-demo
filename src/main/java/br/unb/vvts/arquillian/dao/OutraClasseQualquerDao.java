package br.unb.vvts.arquillian.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.unb.vvts.arquillian.model.OutraClasseQualquer;
import br.unb.vvts.arquillian.model.Seminario;

@Stateless
public class OutraClasseQualquerDao {

	@PersistenceContext(unitName = "teste")
	EntityManager em;

	public void salvar(OutraClasseQualquer o) {
		em.persist(o);
	}

	public void atualizar(OutraClasseQualquer o) {
		em.merge(o);
	}

	public OutraClasseQualquer buscar(int id) {
		return em.find(OutraClasseQualquer.class, id);
	}

	public List<Seminario> buscarTodos() {
		return em.createQuery("SELECT o FROM OutraClasseQualquer o ORDER BY o.id", Seminario.class).getResultList();
	}
}
