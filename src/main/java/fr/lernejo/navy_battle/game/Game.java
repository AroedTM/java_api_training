package fr.lernejo.navy_battle.game;

import fr.lernejo.navy_battle.player.ComputerPlayer;
import fr.lernejo.navy_battle.server.Request;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class Game {
    final public Sea sea = new Sea();
    final public char[][] my_sea = new char[10][10];
    final public char[][] enemy_sea = new char[10][10];
    final public List<Boat> boat_list = List.of(
        new PorteAvion(),
        new Croiseur(),
        new ContreTorpilleur(),
        new ContreTorpilleur(),
        new Torpilleur()
    );
    final public ComputerPlayer computerPlayer;
    final public ArrayList<String> destination = new ArrayList<>();

    public Game(ComputerPlayer computerPlayer){
        this.computerPlayer = computerPlayer;
    }

    public void placeBoats(){
        sea.initializeSea(my_sea);
        sea.initializeSea(enemy_sea);
        sea.displaySea(my_sea, enemy_sea);
        for (final Boat b : boat_list) {
            final ArrayList<String> positions = new ArrayList<>();
            for (int i = 0; i < b.size(); i++) {
                System.out.println("Where would you like to place the cell " + (i + 1) + " of your : " + b.name() + " (" + (b.size() - i) + " remaining cell) : ");
                positions.add(computerPlayer.placeBoat());
                System.out.println(positions.get(i));}
            b.setBoatPos(positions);
            sea.displaySea(sea.fillCells(b.getBoatPos(), my_sea, 'O'), enemy_sea);
        }
        System.out.println("Waiting for the opponent..");
    }

    public void shoot() throws IOException, InterruptedException {
        System.out.println("Which enemy cell to attack ?");
        final ArrayList<String> target_list = new ArrayList<>();
        target_list.add(computerPlayer.cellToTarget());
        System.out.println(target_list.get(0));
        final HttpRequest request = new Request().getRequest(destination.get(0) + "/api/game/fire?cell=" + target_list.get(0), "application/json");
        final HttpClient client = HttpClient.newHttpClient();
        final HttpResponse<?> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        consequence(response.body().toString(), target_list);
    }

    public void consequence(String response, ArrayList<String> target_list){
        if(response.contains("hit")){
            sea.displaySea(my_sea, sea.fillCells(target_list, enemy_sea, 'H'));
            System.out.println("Target hit !");}
        else if(response.contains("sunk")){
            sea.displaySea(my_sea, sea.fillCells(target_list, enemy_sea, 'H'));
            System.out.println("Target sunk !");
            if(response.contains("false")){
                System.out.println("VICTORY ! All enemies boats are destroyed.");
                System.exit(0);
            }
        }
        else{
            sea.displaySea(my_sea, sea.fillCells(target_list, enemy_sea, 'X'));
            System.out.println("Target missed !");}
    }
}
