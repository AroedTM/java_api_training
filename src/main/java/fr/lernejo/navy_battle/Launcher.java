package fr.lernejo.navy_battle;

import com.sun.net.httpserver.HttpServer;
import fr.lernejo.navy_battle.game.Game;
import fr.lernejo.navy_battle.server.Request;
import fr.lernejo.navy_battle.server.Server;

import java.io.IOException;

public class Launcher{

    public static void main(String[] args) throws IOException, InterruptedException {
        final Game game = new Game();
        final int port = Integer.parseInt(args[0]);
        HttpServer server = new Server().launchServer(port, game);
        server.start();
        game.placeBoats();
        if(args.length == 2){
            game.destination.add(args[1]);
            new Request().postRequest(args[1] + "/api/game/start",
                "application/json",
                "{\"id\":\"1\", \"url\":\"http://localhost:" + args[0] + "\", \"message\":\"Hello, my boats are ready ! And you ?\"}");
        }
    }
}
