package br.com.alura.loja.jersey.resource;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import br.com.alura.loja.jersey.dao.ProjetoDAO;
import br.com.alura.loja.jersey.modelo.Projeto;

@Path("projetos")
public class ProjetoResource {
	
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public String busca() {
		 Projeto projetos = new ProjetoDAO().busca(1l);
		return projetos.toXML();
	}
}
