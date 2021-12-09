package fr.lernejo.navy_battle.server;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;

public class CallHandler implements HttpHandler {

    public void handle(HttpExchange exchange) throws IOException {
        final String body = "OK";
        exchange.sendResponseHeaders(200, body.length());
        try(final OutputStream os = exchange.getResponseBody()){
            os.write(body.getBytes());
        }
    }
}
