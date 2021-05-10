package br.com.alura.loja.jersey.resource;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.net.URI;

import com.thoughtworks.xstream.XStream;

import br.com.alura.loja.jersey.dao.CarrinhoDAO;
import br.com.alura.loja.jersey.dao.ProjetoDAO;
import br.com.alura.loja.jersey.modelo.Carrinho;
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
	
	@POST
	@Consumes(MediaType.APPLICATION_XML)
	public Response adiciona( String conteudo ) {
		Projeto projeto = (Projeto ) new XStream().fromXML(conteudo);
		new ProjetoDAO().adiciona(projeto);
		URI uri = URI.create("/projetos/" + projeto.getId());
		return Response.created(uri).build();
	}
	
	@Path("{id}")
	@DELETE
	public Response removeProjeto(@PathParam("id") Long id) {
		new ProjetoDAO().remove(id);

		return Response.ok().build();
	}

}
