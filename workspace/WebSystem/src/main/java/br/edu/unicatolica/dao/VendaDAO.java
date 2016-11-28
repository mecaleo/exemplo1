package br.edu.unicatolica.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.edu.unicatolica.entity.Venda;
import br.edu.unicatolica.filter.VendaFilter;
import br.edu.unicatolica.jpa.util.JPAUtil;

public class VendaDAO {

	private static VendaDAO instance;
	private EntityManager em;

	public static VendaDAO getInstance() {
		if (instance == null) {
			instance = new VendaDAO();
		}
		return instance;
	}

	public void salvar(Venda venda) {
		em = JPAUtil.createEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(venda);
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
	}

	public List<Venda> listarVendas(VendaFilter filter) {
		em = JPAUtil.createEntityManager();
		try {
			Session session = em.unwrap(Session.class);
			Criteria c = session.createCriteria(Venda.class);
			if (filter.getCliente() != null) {
				c.add(Restrictions.like("cliente", filter.getCliente()));
			} else if (filter.getDataInicial() != null && filter.getDataFinal() != null) {
				System.out.println(filter.getDataInicial());
				c.add(Restrictions.between("dataVenda", filter.getDataInicial(), filter.getDataFinal()));
			}
			c.addOrder(Order.desc("dataVenda"));
			return c.list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
		return null;
	}

}
