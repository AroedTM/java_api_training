package fr.lernejo.navy_battle;

import com.sun.net.httpserver.HttpServer;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.net.*;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.Executors;

class CallHandlerTest {

    @Test
    public void test_200() throws Exception {
        final HttpServer server = HttpServer.create(new InetSocketAddress(9870), 0);
        server.createContext("/ping", new CallHandler());
        server.setExecutor(Executors.newFixedThreadPool(1));
        server.start();
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create("http://localhost:9870/ping"))
            .build();
        HttpResponse<?> response = client.send(request,
            HttpResponse.BodyHandlers.ofString());
        Assertions.assertThat(response.statusCode()).isEqualTo(200);
        server.stop(1);
    }
}
