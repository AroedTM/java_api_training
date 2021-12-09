package fr.lernejo.navy_battle;

import com.sun.net.httpserver.HttpServer;
import fr.lernejo.navy_battle.check.Check;
import fr.lernejo.navy_battle.server.Request;
import fr.lernejo.navy_battle.server.Server;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Launcher{

    public static void main(String[] args) throws IOException, InterruptedException {
        if(args.length == 1 || args.length == 2) {
            final boolean is_Int = new Check().isStringInt(args[0]);
            if(is_Int){
                final HttpServer server = new Server().launchServer(Integer.parseInt(args[0]));
                server.start();
                if(args.length == 2){
                    final String myPort = new Check().convertArgPort(args[1]);
                    final boolean isPort = new Check().isStringInt(myPort);
                    if(isPort){
                        final HttpClient client = HttpClient.newHttpClient();
                        final HttpRequest request = new Request().postRequest(args[1] + "/api/game/start",
                            "application/json",
                            "{\"id\":\"45145151\", \"url\":\"http://localhost:" + myPort + "\", \"message\":\"Test\"}");
                        client.send(request, HttpResponse.BodyHandlers.ofString());
                    }else{new Check().displayHelp();}}
            }else{new Check().displayHelp();}}
        else{new Check().displayHelp();}
    }
}
