package fr.lernejo.navy_battle;

import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

public class Launcher{

    public static void main(String[] args) throws IOException {
        if(args.length == 1)
        {
            final boolean is_Int = new Check().isStringInt(args[0]);
            if(is_Int){
                final HttpServer server = HttpServer.create(new InetSocketAddress(Integer.parseInt(args[0])), 0);
                server.createContext("/ping", new CallHandler());
                server.createContext("/api/game/start", new StartHandler());
                server.setExecutor(Executors.newFixedThreadPool(1));
                server.start();
            }else{
                System.out.println("Pour lancer le programme :");
                System.out.println(" -<port>");
            }
        }
        else{
            System.out.println("Pour lancer le programme :");
            System.out.println(" -<port>");
        }
    }
}
