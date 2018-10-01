package br.edu.ifpb.resteasyapp.dao;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import br.edu.ifpb.resteasyapp.entidade.Alternativa;
import br.edu.ifpb.resteasyapp.entidade.Assunto;
import br.edu.ifpb.resteasyapp.entidade.Disciplina;
import br.edu.ifpb.resteasyapp.hibernate.HibernateUtil;

public class AlternativaDAO extends GenericDao<Integer, Alternativa> {

	private static AlternativaDAO instance;

	public static AlternativaDAO getInstance() {
		instance = new AlternativaDAO();
		return instance;
	}

	@Override
	public List<Alternativa> getAll() throws SQLException {
		// TODO Auto-generated method stub
		return super.getAll("Alternativa.getAll");
	}

	@Override
	public Class<?> getEntityClass() {
		// TODO Auto-generated method stub
		return Alternativa.class;
	}

	@Override
	public Alternativa find(Alternativa entity) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	public Alternativa findAlternativaByEnunciado(String enunciado) throws SQLException{
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		Alternativa alternativa = null;
		
		try{
		
			String hql = "from Alternativa a "
				+ "where a.enunciado like :enunciado";
			
			Query query = session.createQuery(hql);
			
			query.setParameter("email", "%" + enunciado + "%");
			
			alternativa = (Alternativa) query.uniqueResult();
			
		} catch(HibernateException hibernateException){
			
			session.getTransaction().rollback();
			
			throw new SQLException(hibernateException);
		
		} finally {
			session.close();
		}
		return alternativa;
	}
	
	public List<Alternativa> getAllByQuestao(int cod_q) throws SQLException{
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		List<Alternativa> alternativas = null;
		
		try{
		
			String hql = "select a from Alternativa a "
				+ "where a.questao.cod_q = :cod_q";
			
			Query query = session.createQuery(hql);
			
			query.setParameter("cod_q", cod_q);
			
			alternativas = (List<Alternativa>) query.list();
			
		} catch(HibernateException hibernateException){
			
			session.getTransaction().rollback();
			
			throw new SQLException(hibernateException);
		
		} finally {
			session.close();
		}
		return alternativas;
	}
	
}
