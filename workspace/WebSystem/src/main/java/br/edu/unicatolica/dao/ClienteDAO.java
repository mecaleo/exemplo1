package br.edu.unicatolica.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.edu.unicatolica.entity.Cliente;
import br.edu.unicatolica.jpa.util.JPAUtil;

public class ClienteDAO {

	private static ClienteDAO instance;

	public static ClienteDAO getInstance() {
		if (instance == null) {
			instance = new ClienteDAO();
		}
		return instance;
	}

	public void salvar(Cliente c) {
		EntityManager em = JPAUtil.createEntityManager();
		try {
			em.getTransaction().begin();
			em.merge(c);
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
	}

	public void atualizar(Cliente c) {
		EntityManager em = JPAUtil.createEntityManager();
		try {
			em.getTransaction().begin();
			em.merge(c);
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
	}

	public void excluir(Cliente c) {
		EntityManager em = JPAUtil.createEntityManager();
		try {
			c = em.find(Cliente.class, c.getCodigo());
			em.getTransaction().begin();
			em.remove(c);
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
	}

	public List<Cliente> listar(String consulta) {
		EntityManager em = JPAUtil.createEntityManager();
		try {
			Session s = em.unwrap(Session.class);
			Criteria c = s.createCriteria(Cliente.class);
			c.addOrder(Order.asc("nome"));
			if (consulta != null) {
				if (!consulta.equals("")) {
					c.add(Restrictions.like("nome", consulta + "%"));
				}
			}
			return c.list();
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
		return null;
	}

	public Cliente clienteSelecionado(Long id) {
		EntityManager em = JPAUtil.createEntityManager();
		try {
			return em.find(Cliente.class, id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
		return null;
	}
}
