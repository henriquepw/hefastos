package br.edu.ifpb.resteasyapp.dao;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import br.edu.ifpb.resteasyapp.entidade.Assunto;
import br.edu.ifpb.resteasyapp.entidade.Disciplina;
import br.edu.ifpb.resteasyapp.entidade.Professor;
import br.edu.ifpb.resteasyapp.hibernate.HibernateUtil;

public class AssuntoDAO extends GenericDao<Integer, Assunto> {

	private static AssuntoDAO instance;

	public static AssuntoDAO getInstance() {
		instance = new AssuntoDAO();
		return instance;
	}

	@Override
	public List<Assunto> getAll() throws SQLException {
		// TODO Auto-generated method stub
		return super.getAll("Assunto.getAll");
	}

	@Override
	public Class<?> getEntityClass() {
		// TODO Auto-generated method stub
		return Assunto.class;
	}

	@Override
	public Assunto find(Assunto entity) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	public Assunto findAssuntoByNome(String nome) throws SQLException{
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		Assunto assunto  = null;
		
		try{
		
			String hql = "from Assunto a "
				+ "where a.nome like :nome";
			
			Query query = session.createQuery(hql);
			
			query.setParameter("nome", "%" + nome + "%");
			
			assunto = (Assunto) query.uniqueResult();
			
		} catch(HibernateException hibernateException){
			
			session.getTransaction().rollback();
			
			throw new SQLException(hibernateException);
		
		} finally {
			session.close();
		}
		return assunto;
	}
	
	public List<Assunto> getAllByDisciplina(int id) throws SQLException{
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		List<Assunto> assuntos = null;
		
		try{
		
			String hql = "select a from Assunto a "
				+ "where a.disciplina.id = :id";
			
			Query query = session.createQuery(hql);
			
			query.setParameter("id", id);
			
			assuntos = (List<Assunto>) query.list();
			
		} catch(HibernateException hibernateException){
			
			session.getTransaction().rollback();
			
			throw new SQLException(hibernateException);
		
		} finally {
			session.close();
		}
		return assuntos;
	}
	
}