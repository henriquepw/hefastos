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
import br.edu.ifpb.resteasyapp.dao.DisciplinaDAO;
import br.edu.ifpb.resteasyapp.dao.ProfessorDAO;
import br.edu.ifpb.resteasyapp.entidade.Assunto;
import br.edu.ifpb.resteasyapp.entidade.Disciplina;
import br.edu.ifpb.resteasyapp.entidade.Professor;
import br.edu.ifpb.resteasyapp.entidade.Questao;
import br.edu.ifpb.resteasyapp.entidade.QuestaoAberta;

@Path("assunto")
public class AssuntoController {

	@PermitAll
	@POST
	@Path("/inserir")
	@Consumes("application/json")
	@Produces("application/json")
	public Response insert(Assunto assunto) {

		// Preparando a resposta. Provisoriamente o sistema preparará a resposta
		// como requisição incorreta.
		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		// TODO: Regra de negócio e manipulação de dados nesse ponto. As
		// informaçãos devem ser associadas
		// nesse ponto ao build (response).

		try {

			int id = AssuntoDAO.getInstance().insert(assunto);

			assunto.setId(id);

			builder.status(Response.Status.OK).entity(assunto);

		} catch (SQLException e) {

			builder.status(Response.Status.INTERNAL_SERVER_ERROR);
		}
		// Resposta.
		return builder.build();
	}

	@PermitAll
	@GET
	@Path("/listar/id_disciplina/{id_disciplina}")
	@Produces("application/json")
	public List<Assunto> getAllByDisciplina(@PathParam("id_disciplina") int id) {

		// Retorno em formato de lista.
		// Desse modo o response sempre conterá o código de resposta OK.
		List<Assunto> assuntos = new ArrayList<Assunto>();

		try {

			if (id == 0) {

				return null;

			} else {

				// TODO: Regra de negócio e manipulação de dados nesse ponto.
				assuntos = AssuntoDAO.getInstance().getAllByDisciplina(id);

			}

		} catch (SQLException e) {

			// TODO: Tratar a exceção.
		}

		// Será retornado ao cliente um conjunto de alunos no formato de Json.
		return assuntos;
	}
	
	@PermitAll
	@GET
	@Path("/listar")
	@Produces("application/json")
	public List<Assunto> getAll() {

		// Retorno em formato de lista.
		// Desse modo o response sempre conterá o código de resposta OK.
		List<Assunto> assuntos = new ArrayList<Assunto>();

		try {
				// TODO: Regra de negócio e manipulação de dados nesse ponto.
				assuntos = AssuntoDAO.getInstance().getAll();

		} catch (SQLException e) {

			// TODO: Tratar a exceção.
		}

		// Será retornado ao cliente um conjunto de alunos no formato de Json.
		return assuntos;
	}

	@PermitAll
	@GET
	@Path("/id/{id}")
	@Produces("application/json")
	public Response getAssuntoById(@PathParam("id") int id) {

		// Preparando a resposta. Provisoriamente o sistema preparará a resposta
		// como requisição incorreta.
		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		try {

			// Regra de negócio e manipulação de dados nesse ponto.
			Assunto assunto = AssuntoDAO.getInstance().getById(id);

			if (assunto != null) {

				// As informaçãos associadas ao build para o response.
				builder.status(Response.Status.OK);
				builder.entity(assunto);

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
	public Response delete(Assunto assunto) {
		// Preparando a resposta. Provisoriamente o sistema preparará a resposta
		// como requisição incorreta.
		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		try {

			if (assunto != null) {
				// Regra de negócio e manipulação de dados nesse ponto.
				
				AssuntoDAO.getInstance().delete(assunto);
				Assunto assuntoTeste = AssuntoDAO.getInstance().findAssuntoByNome(assunto.getNome());

				if (assuntoTeste == null) {

					// As informaçãos associadas ao build para o response.
					builder.status(Response.Status.NO_CONTENT);

				} else {

					// Conteúdo não deletado
					builder.status(418);
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
	public Response update(Assunto assunto) {

		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		try {

			AssuntoDAO.getInstance().updateByEntity(assunto);
			builder.status(Response.Status.OK).entity(assunto);

		} catch (SQLException exception) {
			builder.status(Response.Status.INTERNAL_SERVER_ERROR);
		}
		return builder.build();

	}

}
