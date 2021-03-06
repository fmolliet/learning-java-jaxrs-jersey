package br.com.alura.loja.jersey;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import java.io.IOException;
import java.net.URI;

/**
 * Main class.
 *
 */
public class Main {

    public static final String BASE_URI = "http://localhost:8080/";

    /**
     * Starts Grizzly HTTP server exposing JAX-RS resources defined in this application.
     * @return Grizzly HTTP server.
     */
    public static HttpServer startServer() {
        // create a resource config that scans for JAX-RS resources and providers
        final ResourceConfig config = new ResourceConfig().packages("br.com.alura.loja.jersey");
        
        return GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), config);
    }

    /**
     * Main method.
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        final HttpServer server = startServer();
		
		System.out.println(String.format("Jersey app iniciado e disponivel em"
                + "%s%nHit Ctrl-C to stop it...", BASE_URI));
		
		System.in.read();
		server.stop();

    }
}

