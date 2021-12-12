package fr.lernejo.navy_battle.server;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;

public class FireHandler implements HttpHandler{

    public void handle(HttpExchange exchange) throws IOException {
        if("GET".equals(exchange.getRequestMethod())){
            new Response().json_response_get(exchange, 202, "sunk", true);
        }else {new Response().basic_response(exchange, 404, "Not Found");}
    }
}
