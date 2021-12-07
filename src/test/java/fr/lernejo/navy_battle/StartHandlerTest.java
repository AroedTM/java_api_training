package fr.lernejo.navy_battle;

import com.sun.net.httpserver.HttpServer;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.net.*;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.Executors;

class StartHandlerTest {

    @Test
    public void test_404_get() throws Exception {
        final HttpServer server = HttpServer.create(new InetSocketAddress(9870), 0);
        server.createContext("/api/game/start", new StartHandler());
        server.setExecutor(Executors.newFixedThreadPool(1));
        server.start();
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create("http://localhost:9870/api/game/start"))
            .GET()
            .build();
        HttpResponse<?> response = client.send(request,
            HttpResponse.BodyHandlers.ofString());
        Assertions.assertThat(response.statusCode()).isEqualTo(404);
        server.stop(1);
    }

    @Test
    public void test_200_post() throws Exception {
        final HttpServer server = HttpServer.create(new InetSocketAddress(9870), 0);
        server.createContext("/api/game/start", new StartHandler());
        server.setExecutor(Executors.newFixedThreadPool(1));
        server.start();
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create("http://localhost:9870/api/game/start"))
            .POST(HttpRequest.BodyPublishers.ofString(""))
            .build();
        HttpResponse<?> response = client.send(request,
            HttpResponse.BodyHandlers.ofString());
        Assertions.assertThat(response.statusCode()).isEqualTo(202);
        server.stop(1);
    }

}

