package fr.lernejo.navy_battle.server;

import com.sun.net.httpserver.HttpServer;
import fr.lernejo.navy_battle.game.Game;
import fr.lernejo.navy_battle.player.ComputerPlayer;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

class CallHandlerTest {

    private final HttpServer server = new Server().launchServer(9870, new Game());
    private final HttpClient client = HttpClient.newHttpClient();

    CallHandlerTest() throws IOException {
    }

    @Test
    public void test_200() throws Exception {
        server.start();
        final String response = new Request().simpleRequest("http://localhost:9870/ping");
        Assertions.assertThat(response).contains("OK");
        server.stop(0);
    }
}
