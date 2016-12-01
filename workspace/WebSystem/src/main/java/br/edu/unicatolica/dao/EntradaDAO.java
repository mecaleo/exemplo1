package br.edu.unicatolica.dao;

import javax.persistence.EntityManager;

import br.edu.unicatolica.entity.Entrada;
import br.edu.unicatolica.jpa.util.JPAUtil;

public class EntradaDAO {

	private EntityManager em;
	private static EntradaDAO instance;

	public static EntradaDAO getInstance() {
		if(instance == null) {
			instance = new EntradaDAO();
		}
		return instance;
	}

	public void salvar(Entrada ent) {
		em = JPAUtil.createEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(ent);
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
	}
}
