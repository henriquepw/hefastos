package br.edu.ifpb.resteasyapp.dao;
import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import br.edu.ifpb.resteasyapp.hibernate.HibernateUtil;

public abstract class GenericDao<PK, T> {
	
	private static Logger logger = LogManager.getLogger(GenericDao.class);
	
	public abstract List<T> getAll() throws SQLException;
	
	public abstract Class<?> getEntityClass();
	
	public int insert(T entity) throws SQLException {
		
		Session session = HibernateUtil.getSessionFactory().openSession();

		Integer id;
		
		try {
			
			session.beginTransaction();
			id = (Integer) session.save(entity);
			session.getTransaction().commit();

		} catch (HibernateException hibernateException) {
			
			session.getTransaction().rollback();
			
			throw new SQLException(hibernateException);
			
		} finally {
			
			session.close();
		}
		
		return id;
	}
	
	public boolean insertOrUpdate(T entity) throws SQLException {
		
		logger.info("Init abstract Insert to: " + entity.getClass());
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		boolean success = false;
		
		try {
			
			session.beginTransaction();
			session.saveOrUpdate(entity);
			session.getTransaction().commit();
			
			success = true;

		} catch (HibernateException hibernateException) {
			
			session.getTransaction().rollback();
			
			throw new SQLException(hibernateException);
			
		} finally {
			
			session.close();
		}
		
		return success;
	}

	public T update(T entity) throws SQLException{
		
		logger.info("Init abstract Update to: " + entity.getClass());
		
		Session session = HibernateUtil.getSessionFactory().openSession();

		try {
			
			session.beginTransaction();
			entity = (T) session.merge(entity);
			session.getTransaction().commit();

		} catch (HibernateException hibernateException) {
			
			session.getTransaction().rollback();
			
			throw new SQLException(hibernateException);
			
		} finally {
			
			session.close();
		}
		
		return entity;
	}
	
	public void updateByEntity(T entity) throws SQLException{
		
		logger.info("Init abstract Update to: " + entity.getClass());
		
		Session session = HibernateUtil.getSessionFactory().openSession();

		try {
			
			session.beginTransaction();
			session.update(entity);
			session.getTransaction().commit();

		} catch (HibernateException hibernateException) {
			
			session.getTransaction().rollback();
			
			throw new SQLException(hibernateException);
			
		} finally {
			
			session.close();
		}
	}

	public void delete(T entity) throws SQLException {
		
		Session session = HibernateUtil.getSessionFactory().openSession();

		try {
			
			session.beginTransaction();
			session.delete(entity);
			session.getTransaction().commit();

		} catch (HibernateException hibernateException) {
			
			session.getTransaction().rollback();
			
			throw new SQLException(hibernateException);
			
		} finally {
			
			session.close();
		}
	}
	
	public void delete(Integer id) throws SQLException {

		Session session = HibernateUtil.getSessionFactory().openSession();

		try {
			
			Object entity = session.load(getEntityClass(), id);
			session.beginTransaction();
			session.delete(entity);
			session.getTransaction().commit();

			// This makes the pending delete to be done
			session.flush();

		} catch (HibernateException hibernateException) {

			session.getTransaction().rollback();

			throw new SQLException(hibernateException);

		} finally {

			session.close();
		}
	}
	
	public List<T> getAll(String namedQuery) throws SQLException {
		
		logger.info("Init abstract GetAll to: " + namedQuery);
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<T> list = null;

		try {
			
			session.beginTransaction();
			Query query = session.getNamedQuery(namedQuery);
			list = (List<T>) query.list();
			session.getTransaction().commit();
			
		} catch (HibernateException hibernateException) {
			
			session.getTransaction().rollback();
			
			throw new SQLException(hibernateException);
			
		} finally {
			
			session.close();
		}

		return list;
	}

	public T getById(Integer pk) throws SQLException {		
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		T entity = null;
		
		try {
		
			session.beginTransaction();
			entity = (T) session.get(getEntityClass(), pk);
	        Hibernate.initialize(entity);
	        session.getTransaction().commit();
	        
		} catch (HibernateException hibernateException) {
			
			session.getTransaction().rollback();
			
			throw new SQLException(hibernateException);
			
		} finally {
		
			session.close();
		}
		
		return entity;
	}
	
	public abstract T find(T entity) throws SQLException;
}