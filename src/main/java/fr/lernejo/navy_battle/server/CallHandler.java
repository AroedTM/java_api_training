package fr.lernejo.navy_battle.server;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;

public class CallHandler implements HttpHandler {

    public void handle(HttpExchange exchange) throws IOException {
        new Response().basic_response(exchange, 200, "OK");
    }
}
