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
    private final HttpClient client = HttpClient.newHttpClient();

    FireHandlerTest() throws IOException {}

    @Test
    public void test_202_get() throws Exception {
        server.start();
        final String response = new Request().getRequest("http://localhost:9870/api/game/fire?cell=A1", "application/json");
        Assertions.assertThat(response).contains("shipLeft");
        server.stop(0);
    }

    @Test
    public void test_404_post() throws Exception {
        server.start();
        final String response = new Request().postRequest("http://localhost:9870/api/game/fire",
            "text/html",
            "Test");
        Assertions.assertThat(response).contains("Not Found");
        server.stop(0);
    }
}
