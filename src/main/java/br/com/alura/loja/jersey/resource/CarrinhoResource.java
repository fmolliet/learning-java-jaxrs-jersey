package br.com.alura.loja.jersey.resource;

import java.net.URI;

import com.thoughtworks.xstream.XStream;

import br.com.alura.loja.jersey.dao.CarrinhoDAO;
import br.com.alura.loja.jersey.modelo.Carrinho;
import br.com.alura.loja.jersey.modelo.Produto;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("carrinhos")
public class CarrinhoResource {
	
	@Path("{id}")
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public Carrinho busca(@PathParam("id") Long id ) {
		Carrinho carrinho = new CarrinhoDAO().busca(id);
		return carrinho;
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_XML)
	public Response adiciona( String conteudo ) {
		Carrinho carrinho = (Carrinho ) new XStream().fromXML(conteudo);
		new CarrinhoDAO().adiciona(carrinho);
		URI uri = URI.create("/carrinhos/" + carrinho.getId());
		return Response.created(uri).build();
	}
	
	@Path("{id}/produtos/{produtoId}")
	@DELETE
	public Response removeProduto(@PathParam("id") Long id, @PathParam("produtoId") Long produtoId) {
		Carrinho carrinho = new CarrinhoDAO().busca(id);
		carrinho.remove(produtoId);
		
		return Response.ok().build();
	}
	
	
	@Path("{id}/produtos/{produtoId}/quandidade")
	@PUT
	public Response alteraProduto( String conteudo, @PathParam("id") Long id, @PathParam("produtoId") Long produtoId) {
		Carrinho carrinho = new CarrinhoDAO().busca(id);
		Produto produto = (Produto) new XStream().fromXML(conteudo);
		carrinho.trocaQuantidade(produto);
		
		return Response.ok().build();
	}
	
}
