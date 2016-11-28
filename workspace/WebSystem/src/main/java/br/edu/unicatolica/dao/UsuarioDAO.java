package br.edu.unicatolica.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import br.edu.unicatolica.entity.Usuario;
import br.edu.unicatolica.jpa.util.JPAUtil;
import br.edu.unicatolica.jsf.util.MessagesView;

public class UsuarioDAO {

	private static UsuarioDAO instance;
	private MessagesView m = new MessagesView();

	public static UsuarioDAO getInstance() {
		if (instance == null) {
			instance = new UsuarioDAO();
		}
		return instance;
	}

	public Usuario autenticar(Usuario us) {
		EntityManager em = JPAUtil.createEntityManager();
		try {
			Session s = em.unwrap(Session.class);
			Criteria c = s.createCriteria(Usuario.class);
			c.add(Restrictions.eq("nomeUsuario", us.getNomeUsuario())).add(Restrictions.eq("senha", us.getSenha()));
			if (c.uniqueResult() != null) {
				Usuario usuarioAutenticado = (Usuario) c.uniqueResult();
				return usuarioAutenticado;
			} else {
				return null;
			}
		} catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			em.close();
		}
		return null;
	}

	public void salvar(Usuario us) {
		EntityManager em = JPAUtil.createEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(us);
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			em.close();
		}
	}

	public void atualizar(Usuario us) {
		EntityManager em = JPAUtil.createEntityManager();
		try {
			em.getTransaction().begin();
			em.merge(us);
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			em.close();
		}
	}

	public void excluir(Usuario us) {
		EntityManager em = JPAUtil.createEntityManager();
		try {
			us = em.find(Usuario.class, us.getCodigo());
			em.getTransaction().begin();
			em.remove(us);
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			em.close();
		}
	}

	public List<Usuario> listar(String nomeUsuario) {
		EntityManager em = JPAUtil.createEntityManager();
		try {
			Session s = em.unwrap(Session.class);
			Criteria c = s.createCriteria(Usuario.class);
			if (!nomeUsuario.equals("")) {
				c.add(Restrictions.like("nomeUsuario", nomeUsuario + "%"));
			}
			return c.addOrder(Order.asc("nomeUsuario")).list();
		} finally {
			em.close();
		}
	}

}
