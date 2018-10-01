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
import br.edu.ifpb.resteasyapp.entidade.Assunto;
import br.edu.ifpb.resteasyapp.entidade.Disciplina;

@Path("disciplina")
public class DisciplinaController {

	@PermitAll
	@POST
	@Path("/inserir")
	@Consumes("application/json")
	@Produces("application/json")
	public Response insert(Disciplina disciplina) {

		// Preparando a resposta. Provisoriamente o sistema preparará a resposta
		// como requisição incorreta.
		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		// TODO: Regra de negócio e manipulação de dados nesse ponto. As
		// informaçãos devem ser associadas
		// nesse ponto ao build (response).

		try {

			int id = DisciplinaDAO.getInstance().insert(disciplina);

			disciplina.setId(id);
			;

			builder.status(Response.Status.CREATED).entity(disciplina);

		} catch (SQLException e) {

			builder.status(Response.Status.INTERNAL_SERVER_ERROR);
		}

		// Resposta.
		return builder.build();
	}

	/**
	 * Retorna todos os professores cadastrados.
	 * 
	 * @return Response
	 */
	@PermitAll
	@GET
	@Path("/listar")
	@Produces("application/json")
	public List<Disciplina> getAll() {

		// Retorno em formato de lista.
		// Desse modo o response sempre conterá o código de resposta OK.
		List<Disciplina> disciplinas = new ArrayList<Disciplina>();

		try {

			// TODO: Regra de negócio e manipulação de dados nesse ponto.
			disciplinas = DisciplinaDAO.getInstance().getAll();

		} catch (SQLException e) {

			// TODO: Tratar a exceção.
		}

		// Será retornado ao cliente um conjunto de alunos no formato de Json.
		return disciplinas;
	}

	/**
	 * Recupera o professor cadastrado no sistema através do seu id.
	 * 
	 * @param id
	 * @return Response
	 */
	@PermitAll
	@GET
	@Path("/nome/{nome}")
	@Produces("application/json")
	public Response getDisciplinaByNome(@PathParam("nome") String nome) {

		// Preparando a resposta. Provisoriamente o sistema preparará a resposta
		// como requisição incorreta.
		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		try {

			// Regra de negócio e manipulação de dados nesse ponto.
			Disciplina disciplina = DisciplinaDAO.getInstance().findDisciplinaByNome(nome);

			if (disciplina != null) {

				// As informaçãos associadas ao build para o response.
				builder.status(Response.Status.OK);
				builder.entity(disciplina);

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
	public Response delete(Disciplina disciplina) {
		// Preparando a resposta. Provisoriamente o sistema preparará a resposta
		// como requisição incorreta.
		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		try {

			if (disciplina != null) {
				// Regra de negócio e manipulação de dados nesse ponto.
				List<Assunto> assuntos = AssuntoDAO.getInstance().getAllByDisciplina(disciplina.getId());
				
				if (assuntos != null){
					for (int i = 0; i < assuntos.size(); i++) {
						AssuntoDAO.getInstance().delete(assuntos.get(i));
					}
				}
				
				DisciplinaDAO.getInstance().delete(disciplina);
				Disciplina disciplinaTeste = DisciplinaDAO.getInstance().findDisciplinaByNome(disciplina.getNome());

				if (disciplinaTeste == null) {

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
	public Response update(Disciplina disciplina) {

		ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		builder.expires(new Date());

		try {
			DisciplinaDAO.getInstance().updateByEntity(disciplina);
			builder.status(Response.Status.OK).entity(disciplina);

		} catch (SQLException exception) {
			builder.status(Response.Status.INTERNAL_SERVER_ERROR);
		}
		return builder.build();

	}
}