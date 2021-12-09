package fr.lernejo.navy_battle.server;

import com.sun.net.httpserver.HttpServer;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.*;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

class StartHandlerTest {

    private final HttpServer server = new Server().launchServer(9870);
    private final HttpClient client = HttpClient.newHttpClient();

    StartHandlerTest() throws IOException {
    }

    @Test
    public void test_404_get() throws Exception {
        server.start();
        final HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create("http://localhost:9870/api/game/start"))
            .GET()
            .build();
        final HttpResponse<?> response = client.send(request,
            HttpResponse.BodyHandlers.ofString());
        Assertions.assertThat(response.statusCode()).isEqualTo(404);
        server.stop(1);
    }

    @Test
    public void test_400_post() throws Exception {
        server.start();
        final HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create("http://localhost:9870/api/game/start"))
            .header("Content-Type", "text/html")
            .POST(HttpRequest.BodyPublishers.ofString("Test"))
            .build();
        final HttpResponse<?> response = client.send(request,
            HttpResponse.BodyHandlers.ofString());
        Assertions.assertThat(response.statusCode()).isEqualTo(400);
        server.stop(1);
    }

    @Test
    public void test_202_post() throws Exception {
        server.start();
        final HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create("http://localhost:9870/api/game/start"))
            .header("Content-Type", "application/json")
            .POST(HttpRequest.BodyPublishers.ofString("Test"))
            .build();
        final HttpResponse<?> response = client.send(request,
            HttpResponse.BodyHandlers.ofString());
        Assertions.assertThat(response.statusCode()).isEqualTo(202);
        server.stop(1);
    }

}

