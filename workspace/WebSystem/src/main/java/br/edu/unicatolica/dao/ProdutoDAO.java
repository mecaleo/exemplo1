package br.edu.unicatolica.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.edu.unicatolica.entity.Produto;
import br.edu.unicatolica.entity.ProdutoVenda;
import br.edu.unicatolica.jpa.util.JPAUtil;
import br.edu.unicatolica.jsf.util.ProdutoFilter;

public class ProdutoDAO {

	private EntityManager em = null;
	private static ProdutoDAO instance;

	public static ProdutoDAO getInstance() {
		if (instance == null) {
			instance = new ProdutoDAO();
		}
		return instance;
	}

	public void salvar(Produto p) {
		em = JPAUtil.createEntityManager();
		try {
			em.getTransaction().begin();
			em.merge(p);
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
	}

	public void excluir(Produto p) {
		em = JPAUtil.createEntityManager();
		try {
			p = em.find(Produto.class, p.getCodigo());
			em.getTransaction().begin();
			em.remove(p);
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
	}

	public List<Produto> listar(String consulta) {
		em = JPAUtil.createEntityManager();
		try {
			Session s = em.unwrap(Session.class);
			Criteria c = s.createCriteria(Produto.class);
			if (consulta != null) {
				c.add(Restrictions.like("descricao", consulta + "%"));
			}
			c.addOrder(Order.asc("descricao"));
			return (List<Produto>) c.list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
		return null;
	}

	public Produto getProduto(ProdutoFilter pf) {
		em = JPAUtil.createEntityManager();
		try {
			return em.find(Produto.class, pf.getCodigoProduto());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
		return null;
	}

	public void baixarEstoque(ProdutoVenda p) {
		em = JPAUtil.createEntityManager();
		try {
			Produto produto = em.find(Produto.class, p.getProduto().getCodigo());
			produto.setEstoque(produto.getEstoque() - p.getQtde());
			em.getTransaction().begin();
			em.merge(produto);
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
	}

}
