package br.com.alura.loja.jersey;

import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.thoughtworks.xstream.XStream;

import br.com.alura.loja.jersey.modelo.Carrinho;
import br.com.alura.loja.jersey.modelo.Produto;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

public class CompraTest {
	
	
	private HttpServer server;
	private WebTarget target;

	@Before
	public void setUp() throws Exception {
	    // start the server
		server = Main.startServer();
		// create the client
	    Client client = ClientBuilder.newClient();

	    target = client.target(Main.BASE_URI);
	}

	@After
	public void tearDown() throws Exception {
	    server.stop();
	}
    
    @Test
	public void testaQueBuscarUmCarrinhoTrazOCarrinhoEsperado() {
		
    	Client client = ClientBuilder.newClient();
		
		WebTarget target = client.target("http://localhost:8080");
		String conteudo = target.path("/carrinhos/1").request().get(String.class);
		
		Carrinho carrinho = (Carrinho) new XStream().fromXML(conteudo);
		Assert.assertEquals("Rua Vergueiro 3185, 8 andar", carrinho.getRua());
	}
    
    @Test
    public void testaQueSuportaNovosCarrinhos() {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:8080");
        
        Carrinho carrinho = new Carrinho();
        carrinho.adiciona(new Produto(314L, "Tablet", 999, 1));
        carrinho.setRua("Rua Vergueiro");
        carrinho.setCidade("Sao Paulo");
        
        String xml = carrinho.toXML();
        Entity<String> entity = Entity.entity(xml, MediaType.APPLICATION_XML);
        Response response = target.path("/carrinhos").request().post(entity);
        
        Assert.assertEquals("<status>sucesso</status>", response.readEntity(String.class));
    }
}
