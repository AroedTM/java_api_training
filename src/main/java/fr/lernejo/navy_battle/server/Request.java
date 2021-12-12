package fr.lernejo.navy_battle.server;

import java.net.URI;
import java.net.http.HttpRequest;

public class Request {

    public HttpRequest simpleRequest(String message){
        return HttpRequest.newBuilder()
            .uri(URI.create(message))
            .build();
    }

    public HttpRequest postRequest(String url, String type, String message){
        return HttpRequest.newBuilder()
            .uri(URI.create(url))
            .setHeader("Accept", type)
            .setHeader("Content-Type", type)
            .POST(HttpRequest.BodyPublishers.ofString(message))
            .build();
    }

    public HttpRequest getRequest(String url, String cell){
        return HttpRequest.newBuilder()
            .uri(URI.create(url))
            .setHeader("cell", cell)
            .GET()
            .build();
    }
}
