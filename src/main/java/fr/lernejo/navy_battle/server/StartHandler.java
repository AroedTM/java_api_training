package fr.lernejo.navy_battle.server;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import fr.lernejo.navy_battle.check.Check;

import java.io.IOException;

public class StartHandler implements HttpHandler {

    public final String jsonMaster = "{\n" +"    \"$schema\": \"http://json-schema.org/schema#\",\n" +
        "    \"type\": \"object\",\n" +"    \"properties\": {\n" +"        \"id\": {\n" +"            \"type\": \"string\"\n" +
        "        },\n" +"        \"url\": {\n" +"            \"type\": \"string\"\n" +"        },\n" +
        "        \"message\": {\n" +"            \"type\": \"string\"\n" +"        }\n" +"    },\n" +"    \"required\": [\n" +"        \"id\",\n" +
        "        \"url\",\n" +"        \"message\"\n" +"    ]\n" +"}";

    public void handle(HttpExchange exchange) throws IOException {
        if("POST".equals(exchange.getRequestMethod())){
            if(exchange.getRequestHeaders().get("Content-Type").toString().equals("[application/json]")) {
                if(new Check().validateJson(new String(exchange.getRequestBody().readAllBytes()), jsonMaster)){
                    new Response().json_response_post(exchange, 202, "2aca7611-0ae4-49f3-bf63-75bef4769028", "Requete receptionnee");
                }else {new Response().basic_response(exchange, 400, "Bad Request");}
            }else {new Response().basic_response(exchange, 400, "Bad Request");}
        }
        else {new Response().basic_response(exchange, 404, "Not Found");}
    }
}
