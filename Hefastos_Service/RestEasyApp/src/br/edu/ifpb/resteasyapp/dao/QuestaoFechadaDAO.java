package br.edu.ifpb.resteasyapp.dao;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import br.edu.ifpb.resteasyapp.entidade.Alternativa;
import br.edu.ifpb.resteasyapp.entidade.Assunto;
import br.edu.ifpb.resteasyapp.entidade.QuestaoAberta;
import br.edu.ifpb.resteasyapp.entidade.QuestaoFechada;
import br.edu.ifpb.resteasyapp.hibernate.HibernateUtil;

public class QuestaoFechadaDAO extends GenericDao<Integer, QuestaoFechada> {
	private static QuestaoFechadaDAO instance;

	public static QuestaoFechadaDAO getInstance() {
		instance = new QuestaoFechadaDAO();
		return instance;
	}

	@Override
	public List<QuestaoFechada> getAll() throws SQLException {
		// TODO Auto-generated method stub
		return super.getAll("QuestaoFechada.getAll");
	}

	@Override
	public Class<?> getEntityClass() {
		// TODO Auto-generated method stub
		return QuestaoFechada.class;
	}

	@Override
	public QuestaoFechada find(QuestaoFechada entity) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	public QuestaoFechada findQuestaoFechadaByEnunciado(String enunciado) throws SQLException {

		Session session = HibernateUtil.getSessionFactory().openSession();

		QuestaoFechada questaoFechada = null;

		try {

			String hql = "from QuestaoFechada q " + "where q.enunciado	 like :enunciado";

			Query query = session.createQuery(hql);

			query.setParameter("enunciado", "%" + enunciado + "%");

			questaoFechada = (QuestaoFechada) query.uniqueResult();

		} catch (HibernateException hibernateException) {

			session.getTransaction().rollback();

			throw new SQLException(hibernateException);

		} finally {
			session.close();
		}
		return questaoFechada;
	}

	public List<QuestaoFechada> getAllQuestoesFechadasByAssunto(int cod_assunto) throws SQLException {

		Session session = HibernateUtil.getSessionFactory().openSession();

		List<QuestaoFechada> questoesFechadas = null;

		try {

			String hql1 = "from QuestaoFechada qf where qf.assunto.cod_assunto = :cod_assunto";

			Query query1 = session.createQuery(hql1);

			query1.setParameter("cod_assunto", cod_assunto);

			questoesFechadas = (List<QuestaoFechada>) query1.list();

			//String hql2 = "SELECT Alternativa a FROM QuestaoFechada qf WHERE a.cod_q = :cod_q";

			/**
			for (int i = 0; i < questoesFechadas.size(); i++) {

				List<Alternativa> alternativas = null;
				Query query2 = session.createQuery(hql2);
				query2.setParameter("cod_q", "%" + questoesFechadas.get(i).getId_Questao() + "%");
				alternativas = (List<Alternativa>) query2.list();
				questoesFechadas.get(i).setAlternativas(alternativas);

			} **/

		} catch (HibernateException hibernateException) {

			session.getTransaction().rollback();

			throw new SQLException(hibernateException);

		} finally {
			session.close();
		}
		return questoesFechadas;
	}
	
	public List<Alternativa> getAllAlternativas(int cod_q) throws SQLException {

		Session session = HibernateUtil.getSessionFactory().openSession();

		List<Alternativa> alternativas = null;

		try {

			String hql1 = "select qf.alternativas from QuestaoFechada qf join Questao q where q.id_Questao = :cod_q";

			Query query1 = session.createQuery(hql1);

			query1.setParameter("id_Questao", cod_q);
			
			alternativas = (List<Alternativa>) query1.list();

		} catch (HibernateException hibernateException) {

			session.getTransaction().rollback();

			throw new SQLException(hibernateException);

		} finally {
			session.close();
		}
		return alternativas;
	}
}
