package fr.lernejo.navy_battle;

import com.sun.net.httpserver.HttpServer;
import fr.lernejo.navy_battle.check.Check;
import fr.lernejo.navy_battle.server.Server;

import java.io.IOException;

public class Launcher{

    public static void main(String[] args) throws IOException {
        if(args.length == 1)
        {
            final boolean is_Int = new Check().isStringInt(args[0]);
            if(is_Int){
                final HttpServer server = new Server().launchServer(Integer.parseInt(args[0]));
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
