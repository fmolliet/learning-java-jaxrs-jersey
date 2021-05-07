package br.com.alura.loja.jersey.resource;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import br.com.alura.loja.jersey.dao.ProjetoDAO;
import br.com.alura.loja.jersey.modelo.Projeto;

@Path("projetos")
public class ProjetoResource {
	
	@Path("{id}")
	@GET
	// Precisamos adicionar o media type para o tipo que vamos retornar  MediaType.APPLICATION_JSON -> para JSON
	@Produces(MediaType.APPLICATION_XML)
	public String busca( @PathParam("id") Long id) {
		 Projeto projetos = new ProjetoDAO().busca(id);
		return projetos.toXML();
	}
}
