package fr.lernejo.navy_battle.server;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import fr.lernejo.navy_battle.check.Check;
import fr.lernejo.navy_battle.game.Game;

import java.io.IOException;

public class FireHandler implements HttpHandler{

    final public Game game;

    public FireHandler(Game game) {
        this.game = game;
    }

    public void handle(HttpExchange exchange) throws IOException {
        if("GET".equals(exchange.getRequestMethod())){
            final String cell = new Check().getCell(exchange.getRequestURI().toString());
            System.out.println("cell : " + cell + ". Enemy " + game.whatInCell(cell) + " your boat.");
            new Response().json_response_get(exchange, 202, game.whatInCell(cell), true);
            try {game.shoot();}
            catch (InterruptedException e) {e.printStackTrace();}
        }else {new Response().basic_response(exchange, 404, "Not Found");}
    }
}
