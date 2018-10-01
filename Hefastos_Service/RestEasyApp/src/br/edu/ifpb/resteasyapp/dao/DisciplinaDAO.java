package br.edu.ifpb.resteasyapp.dao;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import br.edu.ifpb.resteasyapp.entidade.Disciplina;
import br.edu.ifpb.resteasyapp.entidade.Professor;
import br.edu.ifpb.resteasyapp.hibernate.HibernateUtil;

public class DisciplinaDAO extends GenericDao<Integer, Disciplina> {

	private static DisciplinaDAO instance;

	public static DisciplinaDAO getInstance() {
		instance = new DisciplinaDAO();
		return instance;
	}

	@Override
	public List<Disciplina> getAll() throws SQLException {
		// TODO Auto-generated method stub
		return super.getAll("Disciplina.getAll");
	}

	@Override
	public Class<?> getEntityClass() {
		// TODO Auto-generated method stub
		return Disciplina.class;
	}

	@Override
	public Disciplina find(Disciplina entity) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	public Disciplina findDisciplinaByNome(String nome) throws SQLException{
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		Disciplina disciplina = null;
		
		try{
		
			String hql = "from Disciplina d "
				+ "where d.nome like :nome";
			
			Query query = session.createQuery(hql);
			
			query.setParameter("nome", "%" + nome + "%");
			
			disciplina = (Disciplina) query.uniqueResult();
			
		} catch(HibernateException hibernateException){
			
			session.getTransaction().rollback();
			
			throw new SQLException(hibernateException);
		
		} finally {
			session.close();
		}
		return disciplina;
	}
	
public Disciplina findDisciplinaById(int id) throws SQLException{
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		Disciplina disciplina = null;
		
		try{
		
			String hql = "from Disciplina d "
				+ "where d.id like :id";
			
			Query query = session.createQuery(hql);
			
			query.setParameter("id", id);
			
			disciplina = (Disciplina) query.uniqueResult();
			
		} catch(HibernateException hibernateException){
			
			session.getTransaction().rollback();
			
			throw new SQLException(hibernateException);
		
		} finally {
			session.close();
		}
		return disciplina;
	}
	
}
