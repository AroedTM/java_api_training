package fr.lernejo.navy_battle.server;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;

public class StartHandler implements HttpHandler {

    public void handle(HttpExchange exchange) throws IOException {
        if("POST".equals(exchange.getRequestMethod())){
            if(exchange.getRequestHeaders().get("Content-Type").toString().equals("[application/json]")) {
                final String msg = new String(exchange.getRequestBody().readAllBytes());
                if(msg.contains("id") && msg.contains("url") && msg.contains("message")){
                    new Response().json_response(exchange, 202, "2aca7611-0ae4-49f3-bf63-75bef4769028", "Requete receptionnee");
                }else{new Response().basic_response(exchange, 400, "Bad Request");}
            }else{new Response().basic_response(exchange, 400, "Bad Request");}
        }else {new Response().basic_response(exchange, 404, "Not Found");}
    }
}
