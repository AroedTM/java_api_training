package fr.lernejo.navy_battle.game;

import fr.lernejo.navy_battle.player.ComputerPlayer;
import fr.lernejo.navy_battle.server.Request;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Game {
    public final Sea sea = new Sea();
    public final char[][] my_sea = new char[10][10];
    public final char[][] enemy_sea = new char[10][10];
    public final List<Boat> boat_list = List.of(
        new PorteAvion(),
        new Croiseur(),
        new ContreTorpilleur(),
        new ContreTorpilleur(),
        new Torpilleur()
    );
    public final ArrayList<String> destination = new ArrayList<>();
    private final ComputerPlayer computerPlayer = new ComputerPlayer();

    public void placeBoats(){
        sea.initializeSea(my_sea);
        sea.initializeSea(enemy_sea);
        sea.displaySea(my_sea, enemy_sea);
        for (final Boat b : boat_list) {
            System.out.println("Where would you like to place your " + b.name() + " (" + (b.size()) + " cells) : ");
            b.setBoatPos(computerPlayer.placeBoat(b));
            sea.displaySea(sea.fillCells(b.getBoatPos(), my_sea, 'O'), enemy_sea);}
        System.out.println("Waiting for the opponent..");
    }

    public void shoot() throws IOException, InterruptedException {
        System.out.println("Which enemy cell to attack ?");
        final ArrayList<String> target_list = new ArrayList<>();
        target_list.add(computerPlayer.cellToTarget());
        System.out.println(target_list.get(0));
        final String response = new Request().getRequest(destination.get(0) + "/api/game/fire?cell=" + target_list.get(0), "application/json");
        consequence(response, target_list);
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
                System.exit(0);}}
        else{
            sea.displaySea(my_sea, sea.fillCells(target_list, enemy_sea, 'X'));
            System.out.println("Target missed !");}
    }

    public boolean statusGame(){
        for (final Boat b : boat_list)
            if(!b.getStatus())
                return true;
        return false;
    }
}
