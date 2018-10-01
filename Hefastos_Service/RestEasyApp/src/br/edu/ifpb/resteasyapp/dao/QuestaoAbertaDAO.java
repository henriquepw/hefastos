package br.edu.ifpb.resteasyapp.dao;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import br.edu.ifpb.resteasyapp.entidade.Assunto;
import br.edu.ifpb.resteasyapp.entidade.Professor;
import br.edu.ifpb.resteasyapp.entidade.QuestaoAberta;
import br.edu.ifpb.resteasyapp.hibernate.HibernateUtil;

public class QuestaoAbertaDAO extends GenericDao<Integer, QuestaoAberta> {

	private static QuestaoAbertaDAO instance;

	public static QuestaoAbertaDAO getInstance() {
		instance = new QuestaoAbertaDAO();
		return instance;
	}

	@Override
	public List<QuestaoAberta> getAll() throws SQLException {
		// TODO Auto-generated method stub
		return super.getAll("QuestaoAberta.getAll");
	}

	@Override
	public Class<?> getEntityClass() {
		// TODO Auto-generated method stub
		return QuestaoAberta.class;
	}

	@Override
	public QuestaoAberta find(QuestaoAberta entity) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	public QuestaoAberta findQuestaoAbertaByEnunciado(String enunciado) throws SQLException {

		Session session = HibernateUtil.getSessionFactory().openSession();

		QuestaoAberta questaoAberta = null;

		try {

			String hql = "from QuestaoAberta q " + "where q.enunciado like :enunciado";

			Query query = session.createQuery(hql);

			query.setParameter("enunciado", "%" + enunciado + "%");

			questaoAberta = (QuestaoAberta) query.uniqueResult();

		} catch (HibernateException hibernateException) {

			session.getTransaction().rollback();

			throw new SQLException(hibernateException);

		} finally {
			session.close();
		}
		return questaoAberta;
	}

	public List<QuestaoAberta> getAllQuestoesAbertasByAssunto(int id) throws SQLException {

		Session session = HibernateUtil.getSessionFactory().openSession();

		List<QuestaoAberta> questoesAbertas = null;

		try {

			String hql = "from QuestaoAberta q "
					+ "where q.assunto.id = :id";

			Query query = session.createQuery(hql);

			query.setParameter("id", id);

			questoesAbertas = (List<QuestaoAberta>) query.list();

		} catch (HibernateException hibernateException) {

			session.getTransaction().rollback();

			throw new SQLException(hibernateException);

		} finally {
			session.close();
		}
		return questoesAbertas;
	}

}
