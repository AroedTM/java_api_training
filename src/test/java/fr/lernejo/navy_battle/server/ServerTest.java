package fr.lernejo.navy_battle.server;

import com.sun.net.httpserver.HttpServer;
import fr.lernejo.navy_battle.game.Game;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

class ServerTest {

    @Test
    public void test_server_listening_on_port() throws IOException {
        final HttpServer server = new Server().launchServer(9870, new Game());
        server.start();
        final Socket socket = new Socket(InetAddress.getLocalHost(), 9870);
        Assertions.assertThat(socket).isNotNull();
        server.stop(0);
    }
}
