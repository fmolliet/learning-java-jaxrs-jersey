package br.com.alura.loja.jersey.resource;

import com.thoughtworks.xstream.XStream;

import br.com.alura.loja.jersey.dao.CarrinhoDAO;
import br.com.alura.loja.jersey.modelo.Carrinho;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("carrinhos")
public class CarrinhoResource {
	
	@Path("{id}")
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public String busca(@PathParam("id") Long id ) {
		Carrinho carrinho = new CarrinhoDAO().busca(id);
		return carrinho.toXML();
	}
	
	@POST
	@Produces(MediaType.APPLICATION_XML)
	public String adiciona( String conteudo ) {
		Carrinho carrinho = (Carrinho ) new XStream().fromXML(conteudo);
		new CarrinhoDAO().adiciona(carrinho);
		
		return "<status>sucesso</status>";
	}
}
