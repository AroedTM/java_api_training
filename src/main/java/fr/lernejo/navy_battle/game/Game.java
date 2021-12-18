package fr.lernejo.navy_battle.game;

import fr.lernejo.navy_battle.check.Check;
import fr.lernejo.navy_battle.server.Request;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game {

    final public Sea sea = new Sea();
    final public Check check = new Check();
    final public char[][] my_sea = new char[10][10];
    final public char[][] enemy_sea = new char[10][10];
    final public List<Boat> boat_list = List.of(
        new PorteAvion(),
        new Croiseur(),
        new ContreTorpilleur(),
        new ContreTorpilleur(),
        new Torpilleur()
    );
    final public Scanner scanner = new Scanner(System.in);
    final public ArrayList<String> address = new ArrayList<>();
    final public ArrayList<Integer> id_received = new ArrayList<>();
    final public ArrayList<Boolean> gameOn = new ArrayList<>(List.of(false));

    public void placeBoats(){
        sea.initializeSea(my_sea);
        sea.initializeSea(enemy_sea);
        sea.displaySea(my_sea, enemy_sea);
        for (final Boat b : boat_list) {
            final ArrayList<String> positions = new ArrayList<>();
            for (int i = 0; i < b.size(); i++) {
                System.out.println("Où souhaitez-vous placer la case " + (i + 1) + " de votre : " + b.name() + " (" + (b.size() - i) + " cases restantes) : ");
                positions.add(scanner.nextLine());}
            b.setBoatPos(positions);
            sea.displaySea(sea.fillCells(b.getBoatPos(), my_sea, 'O'), enemy_sea);
        }
        System.out.println("En attente de l'adversaire..");
    }

    public void initData(String message){
        this.address.add(message.split("\"")[7]);
        this.id_received.add(Integer.parseInt(message.split("\"")[3]));
        this.gameOn.remove(0);
        this.gameOn.add(true);
        System.out.println("La partie commence !");
        if(this.id_received.get(0) == 1) {
            try {shoot();}
            catch (IOException | InterruptedException e) {e.printStackTrace();}
        }
    }

    public String whatInCell(String cell){
        final String[] split = cell.split("");
        final int col = check.getIntFromString(split[0]);
        if(cell.length() == 2){
            if((my_sea[Integer.parseInt(split[1])-1][col]) == '-') return "miss";
            if((my_sea[Integer.parseInt(split[1])-1][col]) == 'O') return "hit";
        }else{
            if((my_sea[Integer.parseInt(split[1] + split[2])-1][col]) == '-') return "miss";
            if((my_sea[Integer.parseInt(split[1] + split[2])-1][col]) == 'O') return "hit";
        }
        return "miss";
    }

    public void shoot() throws IOException, InterruptedException {
        if(gameOn.get(0)){
            System.out.println("Quelle case souhaitez-vous attaquer ?");
            final ArrayList<String> target_list = new ArrayList<>();
            target_list.add(scanner.nextLine());
            final HttpRequest request = new Request().getRequest(this.address.get(0) + "/api/game/fire?cell=" + target_list.get(0));
            final HttpClient client = HttpClient.newHttpClient();
            final HttpResponse<?> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            consequence(response.body().toString(), target_list);
        }
    }

    public void consequence(String response, ArrayList<String> target_list){
        if(response.contains("hit")){
            sea.displaySea(my_sea, sea.fillCells(target_list, enemy_sea, 'H'));
            System.out.println("Cible touchée !");}
        else if(response.contains("sunk")){
            sea.displaySea(my_sea, sea.fillCells(target_list, enemy_sea, 'H'));
            System.out.println("Cible coulée !");}
        else{
            sea.displaySea(my_sea, sea.fillCells(target_list, enemy_sea, 'X'));
            System.out.println("Cible manquée !");}
        sea.displaySea(my_sea, enemy_sea);
    }
}
