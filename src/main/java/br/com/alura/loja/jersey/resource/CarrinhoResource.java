package br.com.alura.loja.jersey.resource;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import br.com.alura.loja.jersey.dao.CarrinhoDAO;
import br.com.alura.loja.jersey.modelo.Carrinho;

@Path("carrinhos")
public class CarrinhoResource {
	
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public String busca() {
		Carrinho carrinho = new CarrinhoDAO().busca(1l);
		return carrinho.toXML();
	}
}
