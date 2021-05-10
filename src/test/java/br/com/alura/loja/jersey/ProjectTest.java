package br.com.alura.loja.jersey;

import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.alura.loja.jersey.modelo.Carrinho;
import br.com.alura.loja.jersey.modelo.Produto;
import br.com.alura.loja.jersey.modelo.Projeto;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

public class ProjectTest {
	
	private HttpServer server;
	private WebTarget target;

	@Before
	public void setUp() throws Exception {
		// start the server
		server = Main.startServer();

	    Client client = ClientBuilder.newClient();

	    target = client.target(Main.BASE_URI);
	}

	@After
	public void tearDown() throws Exception {
	    server.stop();
	}
	
	@Test
    public void testaQueAConexaoComOServidorFuncionaNoPathDeProjetos() {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:8080");
        String conteudo = target.path("/projetos/1").request().get(String.class);
        Assert.assertTrue(conteudo.contains("<nome>Minha loja"));
    }
	
	@Test
    public void testaCriacaoDeUmNovoProjeto() {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:8080");
        
        Projeto projeto = new Projeto();
        projeto.setNome("Projeto 2");
        projeto.setAnoDeInicio(2019);
        
        String xml = projeto.toXML();
        Entity<String> entity = Entity.entity(xml, MediaType.APPLICATION_XML);
        Response response = target.path("/projetos").request().post(entity);
        
        Assert.assertEquals(201, response.getStatus());
        
        String location = response.getHeaderString("Location");
        String conteudo = client.target(location).request().get(String.class);
        Assert.assertTrue(conteudo.contains("Projeto"));
    }
}
