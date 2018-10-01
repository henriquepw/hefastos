package br.edu.ifpb.resteasyapp.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.security.PermitAll;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import br.edu.ifpb.resteasyapp.dao.AlternativaDAO;
import br.edu.ifpb.resteasyapp.dao.AssuntoDAO;
import br.edu.ifpb.resteasyapp.dao.ProfessorDAO;
import br.edu.ifpb.resteasyapp.dao.QuestaoAbertaDAO;
import br.edu.ifpb.resteasyapp.dao.QuestaoFechadaDAO;
import br.edu.ifpb.resteasyapp.entidade.Alternativa;
import br.edu.ifpb.resteasyapp.entidade.Assunto;
import br.edu.ifpb.resteasyapp.entidade.Professor;
import br.edu.ifpb.resteasyapp.entidade.Questao;
import br.edu.ifpb.resteasyapp.entidade.QuestaoAberta;
import br.edu.ifpb.resteasyapp.entidade.QuestaoFechada;
import br.edu.ifpb.resteasyapp.hibernate.HibernateUtil;

@Path("questao/fechada")
public class QuestaoFechadaController {

	@PermitAll
	@POST
	@Path("/inserir")
	@Consumes("application/json")
	@Produces("application/json")
	public Response insert(QuestaoFechada questaoFechada) {

		// Preparando a resposta. Provisoriamente o sistema preparará a resposta
		// como requisição incorreta.
		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		// TODO: Regra de negócio e manipulação de dados nesse ponto. As
		// informaçãos devem ser associadas
		// nesse ponto ao biuld (response).

		try {

			int cod_q = QuestaoFechadaDAO.getInstance().insert(questaoFechada);
			questaoFechada.setId(cod_q);
			builder.status(Response.Status.CREATED).entity(questaoFechada);

		} catch (SQLException e) {

			builder.status(Response.Status.INTERNAL_SERVER_ERROR);
		}

		// Resposta.
		return builder.build();
	}

	@PermitAll
	@GET
	@Path("/listar/cod_assunto/{cod_assunto}")
	@Produces("application/json")
	public List<QuestaoFechada> getAll(@PathParam("cod_assunto") int cod_assunto) {

		// Retorno em formato de lista.
		// Desse modo o response sempre conterá o código de resposta OK.
		List<QuestaoFechada> questoesFechadas = new ArrayList<QuestaoFechada>();

		try {
			if (cod_assunto == 0) {

				return null;

			} else {

				questoesFechadas = QuestaoFechadaDAO.getInstance().getAllQuestoesFechadasByAssunto(cod_assunto);

			}

		} catch (SQLException e) {

			// TODO: Tratar a exceção.
		}

		// Será retornado ao cliente um conjunto de alunos no formato de Json.
		return questoesFechadas;
	}

	@PermitAll
	@GET
	@Path("/id/{id}")
	@Produces("application/json")
	public Response getQuestaoFechadaById(@PathParam("id") int id) {

		// Preparando a resposta. Provisoriamente o sistema preparará a resposta
		// como requisição incorreta.
		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		try {

			// Regra de negócio e manipulação de dados nesse ponto.
			QuestaoFechada questaoFechada = QuestaoFechadaDAO.getInstance().getById(id);

			if (questaoFechada != null) {

				// As informaçãos associadas ao build para o response.
				builder.status(Response.Status.OK);
				builder.entity(questaoFechada);

			} else {

				// Conteúdo não encontrado.
				builder.status(Response.Status.NOT_FOUND);
			}

		} catch (SQLException exception) {

			builder.status(Response.Status.INTERNAL_SERVER_ERROR);
		}

		// Resposta
		return builder.build();
	}

	@PermitAll
	@POST
	@Path("/deletar")
	@Consumes("application/json")
	public Response delete(QuestaoFechada questaoFechada) {
		// Preparando a resposta. Provisoriamente o sistema preparará a resposta
		// como requisição incorreta.
		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		try {

			if (questaoFechada != null) {
				// Regra de negócio e manipulação de dados nesse ponto.
				List<Alternativa> alternativas = QuestaoFechadaDAO.getInstance().getAllAlternativas(questaoFechada.getId());
				
				for (int i = 0; i < alternativas.size(); i++) {
					AlternativaDAO.getInstance().delete(alternativas.get(i));
				}
				
				QuestaoFechadaDAO.getInstance().delete(questaoFechada);
				QuestaoFechada questaoFechadaTeste = QuestaoFechadaDAO.getInstance()
						.findQuestaoFechadaByEnunciado(questaoFechada.getEnunciado());

				if (questaoFechadaTeste == null) {

					// As informaçãos associadas ao build para o response.
					builder.status(Response.Status.NO_CONTENT);

				} else {

					// Conteúdo não deletado
					builder.status(Response.Status.INTERNAL_SERVER_ERROR);
				}
			}

		} catch (SQLException exception) {

			builder.status(Response.Status.INTERNAL_SERVER_ERROR);
		}

		// Resposta
		return builder.build();
	}

	@PermitAll
	@POST
	@Path("/alterar")
	@Produces("application/json")
	@Consumes("application/json")
	public Response update(QuestaoFechada questaoFechada) {

		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		try {

			QuestaoFechadaDAO.getInstance().update(questaoFechada);
			questaoFechada = QuestaoFechadaDAO.getInstance().find(questaoFechada);
			builder.status(Response.Status.OK).entity(questaoFechada);

		} catch (SQLException exception) {
			builder.status(Response.Status.INTERNAL_SERVER_ERROR);
		}
		return builder.build();

	}

}