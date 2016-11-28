package br.edu.unicatolica.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.edu.unicatolica.entity.ProdutoVenda;
import br.edu.unicatolica.entity.Venda;
import br.edu.unicatolica.jpa.util.JPAUtil;

public class ProdutoVendaDAO {

	private static ProdutoVendaDAO instance;
	private EntityManager em;
	
	public static ProdutoVendaDAO getInstance() {
		if(instance == null) {
			instance = new ProdutoVendaDAO();
		}
		return instance;
	}

	public void salvar(ProdutoVenda pv) {
		em = JPAUtil.createEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(pv);
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
	}
	
	public List<ProdutoVenda> listaItens(Venda venda) {
		em = JPAUtil.createEntityManager();
		try {
			Session session = em.unwrap(Session.class);
			Criteria c = session.createCriteria(ProdutoVenda.class);
			c.add(Restrictions.eq("venda", venda));
			return c.list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
		return null;
	}
}
