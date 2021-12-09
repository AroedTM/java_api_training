package fr.lernejo.navy_battle.server;

import com.sun.net.httpserver.HttpServer;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.*;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

class CallHandlerTest {

    private final HttpServer server = new Server().launchServer(9870);
    private final HttpClient client = HttpClient.newHttpClient();

    CallHandlerTest() throws IOException {
    }

    @Test
    public void test_200() throws Exception {
        server.start();
        final HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create("http://localhost:9870/ping"))
            .build();
        final HttpResponse<?> response = client.send(request,
            HttpResponse.BodyHandlers.ofString());
        Assertions.assertThat(response.statusCode()).isEqualTo(200);
        server.stop(1);
    }
}
