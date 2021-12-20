package fr.lernejo.navy_battle.server;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import fr.lernejo.navy_battle.check.Check;
import fr.lernejo.navy_battle.game.Game;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public record FireHandler(Game game) implements HttpHandler {

    public void updateMyBoard(String cell, String consequence){
        if(consequence.equals("hit") || consequence.equals("sunk")) game.sea.fillCells(new ArrayList<>(List.of(cell)), game.my_sea, 'H');
        else{game.sea.fillCells(new ArrayList<>(List.of(cell)), game.my_sea, 'X');}
        game.sea.displaySea(game.my_sea, game.enemy_sea);
    }

    public void handle(HttpExchange exchange) throws IOException {
        if ("GET".equals(exchange.getRequestMethod())) {
            final String cell = new Check().getCell(exchange.getRequestURI().toString());
            final String consequence = game.sea.whatInCell(cell,game.my_sea, game.boat_list);
            final boolean shipLeft = game.statusGame();
            updateMyBoard(cell, consequence);
            System.out.println("Cell " + cell + " attacked. Enemy " + consequence + " you.");
            new Response().json_response_get(exchange, 202, consequence, shipLeft);
            if(!shipLeft){
                System.out.println("DEFEAT ! Enemy has destroyed all your units.");
                System.exit(0);}
            try {game.shoot();}
            catch (InterruptedException e) {e.printStackTrace();}
        } else {new Response().basic_response(exchange, 404, "Not Found");}
    }
}
