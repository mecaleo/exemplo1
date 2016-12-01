package br.edu.unicatolica.dao;

import javax.persistence.EntityManager;

import br.edu.unicatolica.entity.ProdutoEntrada;
import br.edu.unicatolica.jpa.util.JPAUtil;

public class ProdutoEntradaDAO {

	private static ProdutoEntradaDAO instance;
	private EntityManager em;
	
	public static ProdutoEntradaDAO getInstance() {
		if(instance == null) {
			instance = new ProdutoEntradaDAO();
		}
		return instance;
	}
	
	public void salvar(ProdutoEntrada item) {
		em = JPAUtil.createEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(item);
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
	}
	
	
}
