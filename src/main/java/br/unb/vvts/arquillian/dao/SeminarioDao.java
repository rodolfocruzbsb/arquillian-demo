package br.unb.vvts.arquillian.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.unb.vvts.arquillian.model.Seminario;

@Stateless
public class SeminarioDao {

	@PersistenceContext(unitName = "teste")
	EntityManager em;

	public void salvar(Seminario p) {
		em.persist(p);
	}

	public void atualizar(Seminario p) {
		em.merge(p);
	}

	public Seminario buscar(int id) {
		return em.find(Seminario.class, id);
	}

	public List<Seminario> buscarTodos() {
		return em.createQuery("SELECT s FROM Seminario s ORDER BY s.id", Seminario.class).getResultList();
	}
}
