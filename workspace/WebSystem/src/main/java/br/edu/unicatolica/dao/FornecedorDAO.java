package br.edu.unicatolica.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.edu.unicatolica.entity.Fornecedor;
import br.edu.unicatolica.jpa.util.JPAUtil;

public class FornecedorDAO {

	private EntityManager em;
	private static FornecedorDAO instance;
	
	public static FornecedorDAO getInstance() {
		if(instance == null) {
			instance = new FornecedorDAO();
		}
		return instance;
	}
	
	public void salvar(Fornecedor fornecedor) {
		em = JPAUtil.createEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(fornecedor);
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
	}
	
	public void editar(Fornecedor fornecedor) {
		em = JPAUtil.createEntityManager();
		try {
			em.getTransaction().begin();
			em.merge(fornecedor);
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
	}
	
	public void excluir(Fornecedor fornecedor) {
		em = JPAUtil.createEntityManager();
		try {
			fornecedor = em.find(Fornecedor.class, fornecedor.getCodigo());
			em.getTransaction().begin();
			em.remove(fornecedor);
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
	}
	
	public List<Fornecedor> listar(String consulta) {
		em = JPAUtil.createEntityManager();
		try {
			Session s = em.unwrap(Session.class);
			Criteria c = s.createCriteria(Fornecedor.class);
			if(consulta != null) {
				c.add(Restrictions.like("nomeFantasia", consulta+"%"));
			}
			c.addOrder(Order.asc("nomeFantasia"));
			return c.list();
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
		return null;
	}
}
