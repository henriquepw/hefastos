package br.edu.ifpb.resteasyapp.dao;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import br.edu.ifpb.resteasyapp.entidade.Professor;
import br.edu.ifpb.resteasyapp.hibernate.HibernateUtil;

public class ProfessorDAO extends GenericDao<Integer, Professor> {

	private static ProfessorDAO instance;

	public static ProfessorDAO getInstance() {
		instance = new ProfessorDAO();
		return instance;
	}

	@Override
	public List<Professor> getAll() throws SQLException {
		// TODO Auto-generated method stub
		return super.getAll("Professor.getAll");
	}

	@Override
	public Class<?> getEntityClass() {
		// TODO Auto-generated method stub
		return Professor.class;
	}

	@Override
	public Professor find(Professor entity) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	public Professor findProfessorByEmail(String email) throws SQLException{
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		Professor professor = null;
		
		try{
		
			String hql = "from Professor p "
				+ "where p.email like :email";
			
			Query query = session.createQuery(hql);
			
			query.setParameter("email", "%" + email + "%");
			
			professor = (Professor) query.uniqueResult();
			
		} catch(HibernateException hibernateException){
			
			session.getTransaction().rollback();
			
			throw new SQLException(hibernateException);
		
		} finally {
			session.close();
		}
		return professor;
	}
	
}
