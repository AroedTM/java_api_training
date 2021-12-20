package fr.lernejo.navy_battle.server;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import fr.lernejo.navy_battle.check.Check;
import fr.lernejo.navy_battle.game.Game;
import netscape.javascript.JSObject;
import org.json.JSONObject;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StartHandler implements HttpHandler {

    public final String jsonMaster = "{\n" +"    \"$schema\": \"http://json-schema.org/schema#\",\n" +
        "    \"type\": \"object\",\n" +"    \"properties\": {\n" +"        \"id\": {\n" +"            \"type\": \"string\"\n" +
        "        },\n" +"        \"url\": {\n" +"            \"type\": \"string\"\n" +"        },\n" +
        "        \"message\": {\n" +"            \"type\": \"string\"\n" +"        }\n" +"    },\n" +"    \"required\": [\n" +"        \"id\",\n" +
        "        \"url\",\n" +"        \"message\"\n" +"    ]\n" +"}";
    private final Game game;

    public StartHandler(Game game) {
        this.game = game;
    }

    public void dest(String message){
        JSONObject jsonObject = new JSONObject(message);
        game.destination.add(jsonObject.getString("url"));
    }

    public void handle(HttpExchange exchange) throws IOException {
        if("POST".equals(exchange.getRequestMethod())){
            if(exchange.getRequestHeaders().get("Content-Type").toString().equals("[application/json]")) {
                final String message = new String(exchange.getRequestBody().readAllBytes());
                if(new Check().validateJson(message, jsonMaster)){
                    new Response().json_response_post(exchange, 202, "2", "Hi, ready too. Game on !");
                    dest(message);
                    try {game.shoot();}
                    catch (IOException | InterruptedException e) {e.printStackTrace();}
                }else {new Response().basic_response(exchange, 400, "Bad Request");}
            }
            else {new Response().basic_response(exchange, 400, "Bad Request");}
        }
        else {new Response().basic_response(exchange, 404, "Not Found");}
    }
}
