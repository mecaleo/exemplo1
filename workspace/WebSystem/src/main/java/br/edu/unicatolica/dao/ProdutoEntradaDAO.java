package br.edu.unicatolica.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.edu.unicatolica.entity.Entrada;
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
	
	public List<ProdutoEntrada> listaItens(Entrada entrada) {
		em = JPAUtil.createEntityManager();
		try {
			Session s = em.unwrap(Session.class);
			Criteria crit = s.createCriteria(ProdutoEntrada.class);
			crit.add(Restrictions.eq("entrada", entrada));
			return crit.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
}
