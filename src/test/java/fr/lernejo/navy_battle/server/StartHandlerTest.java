package fr.lernejo.navy_battle.server;

import com.sun.net.httpserver.HttpServer;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

class StartHandlerTest {

    private final int port = 9870;
    private final HttpServer server = new Server().launchServer(port);
    private final HttpClient client = HttpClient.newHttpClient();

    StartHandlerTest() throws IOException {
    }

    @Test
    public void test_404_get() throws Exception {
        server.start();
        final HttpRequest request = new Request().getRequest("http://localhost:9870/api/game/start");
        final HttpResponse<?> response = client.send(request,
            HttpResponse.BodyHandlers.ofString());
        Assertions.assertThat(response.statusCode()).isEqualTo(404);
        server.stop(1);
    }

    @Test
    public void test_400_post_without_json() throws Exception {
        server.start();
        final HttpRequest request = new Request().postRequest("http://localhost:9870/api/game/start",
            "text/html",
            "Test");
        final HttpResponse<?> response = client.send(request,
            HttpResponse.BodyHandlers.ofString());
        Assertions.assertThat(response.statusCode()).isEqualTo(400);
        server.stop(1);
    }

    @Test
    public void test_400_post_with_bad_json() throws Exception {
        server.start();
        final HttpRequest request = new Request().postRequest("http://localhost:9870/api/game/start",
            "application/json",
            "Test");
        final HttpResponse<?> response = client.send(request,
            HttpResponse.BodyHandlers.ofString());
        Assertions.assertThat(response.statusCode()).isEqualTo(400);
        server.stop(1);
    }

    @Test
    public void test_400_post_with_good_json() throws Exception {
        server.start();
        final HttpRequest request = new Request().postRequest("http://localhost:9870/api/game/start",
            "application/json",
            "{\"id\":\"45145151\", \"url\":\"http://localhost:" + port + "\", \"message\":\"Test\"}");
        final HttpResponse<?> response = client.send(request,
            HttpResponse.BodyHandlers.ofString());
        Assertions.assertThat(response.statusCode()).isEqualTo(202);
        server.stop(1);
    }

}
