package fr.lernejo.navy_battle;

import com.sun.net.httpserver.HttpServer;
import fr.lernejo.navy_battle.check.Check;
import fr.lernejo.navy_battle.game.Game;
import fr.lernejo.navy_battle.server.Request;
import fr.lernejo.navy_battle.server.Server;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Launcher{

    public static void main(String[] args) throws IOException, InterruptedException {
        Game game = new Game();
        if(args.length == 1 || args.length == 2) {
            final boolean is_Int = new Check().isStringInt(args[0]);
            if(is_Int){
                final HttpServer server = new Server().launchServer(Integer.parseInt(args[0]), game);
                server.start();
                game.placeBoats();
                if(args.length == 2){
                    final HttpClient client = HttpClient.newHttpClient();
                    final HttpRequest request = new Request().postRequest(args[1] + "/api/game/start",
                        "application/json",
                        "{\"id\":\"1\", \"url\":\"http://localhost:" + args[0] + "\", \"message\":\"Hello, my boats are ready ! And you ?\"}");
                    final HttpResponse<?> response =  client.send(request, HttpResponse.BodyHandlers.ofString());
                    game.initData(response.body().toString());
                }
            }else{new Check().displayHelp();}}
        else{new Check().displayHelp();}
    }
}
