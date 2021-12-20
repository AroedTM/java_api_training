package fr.lernejo.navy_battle.server;

import com.sun.net.httpserver.HttpServer;
import fr.lernejo.navy_battle.game.Game;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

class FireHandlerTest {
    private final int port = 9870;
    private final HttpServer server = new Server().launchServer(port, new Game());
    //private final HttpServer server = new Server().launchServer(port);
    private final HttpClient client = HttpClient.newHttpClient();

    FireHandlerTest() throws IOException {
    }

    @Test
    public void test_202_get() throws Exception {
        server.start();
        final HttpRequest request = new Request().getRequest("http://localhost:9870/api/game/fire?cell=A1");
        final HttpResponse<?> response = client.send(request,
            HttpResponse.BodyHandlers.ofString());
        Assertions.assertThat(response.statusCode()).isEqualTo(202);
        server.stop(0);
    }

    @Test
    public void test_404_post() throws Exception {
        server.start();
        final HttpRequest request = new Request().postRequest("http://localhost:9870/api/game/fire",
            "text/html",
            "Test");
        final HttpResponse<?> response = client.send(request,
            HttpResponse.BodyHandlers.ofString());
        Assertions.assertThat(response.statusCode()).isEqualTo(404);
        server.stop(0);
    }
}
