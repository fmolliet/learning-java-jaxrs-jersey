package br.com.alura.loja.jersey;

import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.thoughtworks.xstream.XStream;

import br.com.alura.loja.jersey.modelo.Carrinho;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;

public class ClientTest {
	
	
	private HttpServer server;
	private WebTarget target;

	@Before
	public void setUp() throws Exception {
	        // start the server
		server = Main.startServer();
	        // create the client
	    Client c = ClientBuilder.newClient();

	        // uncomment the following line if you want to enable
	        // support for JSON in the client (you also have to uncomment
	        // dependency on jersey-media-json module in pom.xml and Main.startServer())
	        // --
	        // c.configuration().enable(new org.glassfish.jersey.media.json.JsonJaxbFeature());

	    target = c.target(Main.BASE_URI);
	}

	@After
	public void tearDown() throws Exception {
	    server.stop();
	}
    
    @Test
	public void testaQueAConexaoComServidorFunciona() {
		
    	Client client = ClientBuilder.newClient();
		
		WebTarget target = client.target("http://localhost:8080");
		String conteudo = target.path("/carrinhos").request().get(String.class);
		
		Carrinho carrinho = (Carrinho) new XStream().fromXML(conteudo);
		Assert.assertEquals("Rua Vergueiro 3185, 8 andar", carrinho.getRua());
	}
}
