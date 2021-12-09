package fr.lernejo.navy_battle.server;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

public class Server{

    public HttpServer launchServer(int port) throws IOException {
        final HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
        server.createContext("/ping", new CallHandler());
        server.createContext("/api/game/start", new StartHandler());
        server.setExecutor(Executors.newFixedThreadPool(1));
        return server;
    }
}
