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

import br.edu.ifpb.resteasyapp.dao.AssuntoDAO;
import br.edu.ifpb.resteasyapp.dao.QuestaoAbertaDAO;
import br.edu.ifpb.resteasyapp.dao.QuestaoFechadaDAO;
import br.edu.ifpb.resteasyapp.entidade.Assunto;
import br.edu.ifpb.resteasyapp.entidade.Questao;
import br.edu.ifpb.resteasyapp.entidade.QuestaoAberta;

@Path("questao/aberta")
public class QuestaoAbertaController {

	@PermitAll
	@POST
	@Path("/inserir")
	@Consumes("application/json")
	@Produces("application/json")
	public Response insert(QuestaoAberta questaoAberta) {

		// Preparando a resposta. Provisoriamente o sistema preparará a resposta
		// como requisição incorreta.
		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		// TODO: Regra de negócio e manipulação de dados nesse ponto. As
		// informaçãos devem ser associadas
		// nesse ponto ao biuld (response).

		try {

			int cod_q = QuestaoAbertaDAO.getInstance().insert(questaoAberta);
			questaoAberta.setId(cod_q);
			builder.status(Response.Status.CREATED).entity(questaoAberta);

		} catch (SQLException e) {

			builder.status(Response.Status.INTERNAL_SERVER_ERROR);
		}

		// Resposta.
		return builder.build();
	}

	@PermitAll
	@GET
	@Path("/listar/id_assunto/{id_assunto}")
	@Produces("application/json")
	public List<QuestaoAberta> getAll(@PathParam("id_assunto") int id) {

		// Retorno em formato de lista.
		// Desse modo o response sempre conterá o código de resposta OK.
		List<QuestaoAberta> questoesAbertas = new ArrayList<QuestaoAberta>();

		try {
			if (id == 0) {

				return null;

			} else {

				questoesAbertas = QuestaoAbertaDAO.getInstance().getAllQuestoesAbertasByAssunto(id);

			}

		} catch (SQLException e) {

			// TODO: Tratar a exceção.
		}

		// Será retornado ao cliente um conjunto de alunos no formato de Json.
		return questoesAbertas;
	}

	@PermitAll
	@GET
	@Path("/id/{id}")
	@Produces("application/json")
	public Response getQuestaoAbertaById(@PathParam("id") int id) {

		// Preparando a resposta. Provisoriamente o sistema preparará a resposta
		// como requisição incorreta.
		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		try {

			// Regra de negócio e manipulação de dados nesse ponto.
			QuestaoAberta questaoAberta = QuestaoAbertaDAO.getInstance().getById(id);

			if (questaoAberta != null) {

				// As informaçãos associadas ao build para o response.
				builder.status(Response.Status.OK);
				builder.entity(questaoAberta);

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
	public Response delete(QuestaoAberta questaoAberta) {
		// Preparando a resposta. Provisoriamente o sistema preparará a resposta
		// como requisição incorreta.
		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		try {

			if (questaoAberta != null) {
				// Regra de negócio e manipulação de dados nesse ponto.
				QuestaoAbertaDAO.getInstance().delete(questaoAberta);
				QuestaoAberta questaoAbertaTeste = QuestaoAbertaDAO.getInstance()
						.getById(questaoAberta.getId());

				if (questaoAberta == null) {

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
	public Response update(QuestaoAberta questaoAberta) {

		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		try {

			QuestaoAbertaDAO.getInstance().updateByEntity(questaoAberta);
			questaoAberta = QuestaoAbertaDAO.getInstance().find(questaoAberta);
			builder.status(Response.Status.OK).entity(questaoAberta);

		} catch (SQLException exception) {
			builder.status(Response.Status.INTERNAL_SERVER_ERROR);
		}
		return builder.build();
	}
}
