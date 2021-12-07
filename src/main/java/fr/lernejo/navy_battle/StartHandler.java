package fr.lernejo.navy_battle;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;

public class StartHandler implements HttpHandler {

    public void handle(HttpExchange exchange) throws IOException {
        if("POST".equals(exchange.getRequestMethod())){
            final String body = "Visiblement c'est ok";
            exchange.sendResponseHeaders(202, body.length());
            try(final OutputStream os = exchange.getResponseBody()){
                os.write(body.getBytes());
            }
        }else {
            final String body = "Not Found";
            exchange.sendResponseHeaders(404, body.length());
            try (final OutputStream os = exchange.getResponseBody()) {
                os.write(body.getBytes());
            }
        }
    }
}
