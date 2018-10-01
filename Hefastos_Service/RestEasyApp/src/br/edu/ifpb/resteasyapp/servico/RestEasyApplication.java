package br.edu.ifpb.resteasyapp.servico;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

import org.jboss.resteasy.plugins.interceptors.CorsFilter;

import br.edu.ifpb.resteasyapp.controller.AlternativaController;
import br.edu.ifpb.resteasyapp.controller.AssuntoController;
import br.edu.ifpb.resteasyapp.controller.DisciplinaController;
import br.edu.ifpb.resteasyapp.controller.ProfessorController;
import br.edu.ifpb.resteasyapp.controller.QuestaoAbertaController;
import br.edu.ifpb.resteasyapp.controller.QuestaoFechadaController;

public class RestEasyApplication extends Application {

	private Set<Object> singletons = new HashSet<Object>();
	private Set<Class<?>> empty = new HashSet<Class<?>>();

	public RestEasyApplication() {
		
		// Multiple client-request: Cross-Filter
		CorsFilter filter = new CorsFilter();
		filter.getAllowedOrigins().add("*");
		filter.setAllowedMethods("POST, GET, DELETE, PUT, OPTIONS");
		filter.setAllowedHeaders("Content-Type, Authorization");
		
		this.singletons.add(filter);
		
		// Controllers disponíveis no serviço.
		this.singletons.add(new ProfessorController());
		this.singletons.add(new DisciplinaController());
		this.singletons.add(new AssuntoController());
		this.singletons.add(new AlternativaController());
		this.singletons.add(new QuestaoAbertaController());
		this.singletons.add(new QuestaoFechadaController());
	}

	public Set<Class<?>> getClasses() {
		return this.empty;
	}

	public Set<Object> getSingletons() {
		return this.singletons;
	}
}