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

import br.edu.ifpb.resteasyapp.dao.AlternativaDAO;
import br.edu.ifpb.resteasyapp.dao.ProfessorDAO;
import br.edu.ifpb.resteasyapp.entidade.Alternativa;
import br.edu.ifpb.resteasyapp.entidade.Professor;
import br.edu.ifpb.resteasyapp.entidade.QuestaoFechada;

@Path("alternativa")
public class AlternativaController {

	@PermitAll
	@POST
	@Path("/inserir")
	@Consumes("application/json")
	@Produces("application/json")
	public Response insert(Alternativa alternativa, QuestaoFechada questao) {

		// Preparando a resposta. Provisoriamente o sistema preparará a resposta
		// como requisição incorreta.
		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		// TODO: Regra de negócio e manipulação de dados nesse ponto. As
		// informaçãos devem ser associadas
		// nesse ponto ao biuld (response).

		try {

			int id = AlternativaDAO.getInstance().insert(alternativa);

			alternativa.setCod_a(id);

			builder.status(Response.Status.OK).entity(alternativa);

		} catch (SQLException e) {

			builder.status(Response.Status.INTERNAL_SERVER_ERROR);
		}

		// Resposta.
		return builder.build();
	}

	@PermitAll
	@GET
	@Path("/listar/cod_q/{cod_q}")
	@Produces("application/json")
	public List<Alternativa> getAll(@PathParam("cod_q") int cod_q) {

		// Retorno em formato de lista.
		// Desse modo o response sempre conterá o código de resposta OK.
		List<Alternativa> alternativas = new ArrayList<Alternativa>();

		try {

			if (cod_q == 0) {

				return null;

			} else {

				alternativas = AlternativaDAO.getInstance().getAllByQuestao(cod_q);

			}

		} catch (SQLException e) {

			// TODO: Tratar a exceção.
		}

		// Será retornado ao cliente um conjunto de alunos no formato de Json.
		return alternativas;
	}

	@PermitAll
	@GET
	@Path("/id/{id}")
	@Produces("application/json")
	public Response getAlternativaById(@PathParam("id") int id) {

		// Preparando a resposta. Provisoriamente o sistema preparará a resposta
		// como requisição incorreta.
		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		try {

			// Regra de negócio e manipulação de dados nesse ponto.
			Alternativa alternativa = AlternativaDAO.getInstance().getById(id);

			if (alternativa != null) {

				// As informaçãos associadas ao build para o response.
				builder.status(Response.Status.OK);
				builder.entity(alternativa);

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
	public Response delete(Alternativa alternativa) {
		// Preparando a resposta. Provisoriamente o sistema preparará a resposta
		// como requisição incorreta.
		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		try {

			if (alternativa != null) {
				// Regra de negócio e manipulação de dados nesse ponto.
				AlternativaDAO.getInstance().delete(alternativa);
				Alternativa alternativaTeste = AlternativaDAO.getInstance()
						.findAlternativaByEnunciado(alternativa.getEnunciado());

				if (alternativaTeste == null) {

					// As informaçãos associadas ao build para o response.
					builder.status(Response.Status.OK);

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
	public Response update(Alternativa alternativaEnviada) {

		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		try {

			Alternativa alternativaCompleta = AlternativaDAO.getInstance()
					.findAlternativaByEnunciado(alternativaEnviada.getEnunciado());
			AlternativaDAO.getInstance().update(alternativaCompleta);
			builder.status(Response.Status.OK).entity(alternativaCompleta);

		} catch (SQLException exception) {
			builder.status(Response.Status.INTERNAL_SERVER_ERROR);
		}
		return builder.build();

	}

}
