package br.edu.unicatolica.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.edu.unicatolica.entity.Entrada;
import br.edu.unicatolica.filter.EntradaFilter;
import br.edu.unicatolica.jpa.util.JPAUtil;

public class EntradaDAO {

	private EntityManager em;
	private static EntradaDAO instance;

	public static EntradaDAO getInstance() {
		if (instance == null) {
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

	public List<Entrada> listar(EntradaFilter filtro) {
		em = JPAUtil.createEntityManager();
		try {
			Session s = em.unwrap(Session.class);
			Criteria crit = s.createCriteria(Entrada.class);
			if (filtro.getFornecedor() != null) {
				crit.add(Restrictions.eq("fornecedor", filtro.getFornecedor()));
			} else if (filtro.getDataInicial() != null && filtro.getDataFinal() != null) {
				crit.add(Restrictions.between("dataEntrada", filtro.getDataInicial(), filtro.getDataFinal()));
			}
			crit.addOrder(Order.desc("dataEntrada"));
			return crit.list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
		return null;
	}
}
