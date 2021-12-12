package fr.lernejo.navy_battle.server;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import fr.lernejo.navy_battle.check.Check;

import java.io.IOException;

public class FireHandler implements HttpHandler{

    public void handle(HttpExchange exchange) throws IOException {
        if("GET".equals(exchange.getRequestMethod())){
            final String cell = new Check().getCell(exchange.getRequestURI().toString());
            new Response().json_response_get(exchange, 202, "sunk", true);
        }else {new Response().basic_response(exchange, 404, "Not Found");}
    }
}
