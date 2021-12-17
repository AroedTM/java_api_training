package fr.lernejo.navy_battle.server;

import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;

public class Response{

    public void basic_response(HttpExchange exchange, int code, String message) throws IOException {
        exchange.sendResponseHeaders(code, message.length());
        try(final OutputStream os = exchange.getResponseBody()){
            os.write(message.getBytes());
        }
    }

    public void json_response_post(HttpExchange exchange, int code, String id, String message) throws IOException {
        final String body = "{\n\t\"id\": \""
            + id
            + "\",\n\t\"url\": \"http://localhost:"
            + exchange.getLocalAddress().getPort()
            + "\",\n\t\"message\": \""
            + message
            + "\"\n}";
        exchange.sendResponseHeaders(code, body.length());
        try(final OutputStream os = exchange.getResponseBody()){
            os.write(body.getBytes());
        }
    }

    public void json_response_get(HttpExchange exchange, int code, String consequence, boolean shipLeft) throws IOException {
        final String body = "{\n\t\"consequence\": \""
            + consequence
            + "\",\n\t\"shipLeft\": "
            + shipLeft
            + "\n}";
        exchange.sendResponseHeaders(code, body.length());
        try(final OutputStream os = exchange.getResponseBody()){
            os.write(body.getBytes());
        }
    }
}
