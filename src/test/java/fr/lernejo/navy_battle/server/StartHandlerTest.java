package fr.lernejo.navy_battle.server;

import com.sun.net.httpserver.HttpServer;
import fr.lernejo.navy_battle.game.Game;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

class StartHandlerTest {

    private final int port = 9870;
    private final HttpServer server = new Server().launchServer(port, new Game());

    StartHandlerTest() throws IOException {}

    @Test
    public void test_404_get() throws Exception {
        server.start();
        final String response = new Request().getRequest("http://localhost:9870/api/game/start", "application/json");
        Assertions.assertThat(response).contains("Not Found");
        server.stop(0);
    }

    @Test
    public void test_400_post_with_bad_json() throws Exception {
        server.start();
        final String response = new Request().postRequest("http://localhost:9870/api/game/start",
            "application/json",
            "{\"test\":\"45145151\"}");
        Assertions.assertThat(response).contains("Bad Request");
        server.stop(0);
    }


    @Test
    public void test_202_post_with_good_json() throws Exception {
        server.start();
        final String response = new Request().postRequest("http://localhost:9870/api/game/start",
            "application/json",
            "{\"id\":\"45145151\", \"url\":\"http://localhost:" + port + "\", \"message\":\"Test\"}");
        Assertions.assertThat(response).contains("id");
        server.stop(0);
    }
}
