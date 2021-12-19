package fr.lernejo.navy_battle.game;

import fr.lernejo.navy_battle.check.Check;
import fr.lernejo.navy_battle.player.ComputerPlayer;
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
    final public ComputerPlayer computerPlayer = new ComputerPlayer();

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

    public void initData(String message){
        this.address.add(message.split("\"")[7]);
        this.id_received.add(Integer.parseInt(message.split("\"")[3]));
        this.gameOn.remove(0);
        this.gameOn.add(true);
        System.out.println("Game on !");
        if(this.id_received.get(0) == 1) {
            try {shoot();}
            catch (IOException | InterruptedException e) {e.printStackTrace();}
        }
    }

    public String whatInCell(String cell){
        final String[] split = cell.split("");
        final int col = new Check().getIntFromString(split[0]);
        if(cell.length() == 2){
            if((my_sea[Integer.parseInt(split[1])-1][col]) == '-') return "miss";
            if((my_sea[Integer.parseInt(split[1])-1][col]) == 'O') return updateHitBoat(cell);
        }else{
            if((my_sea[Integer.parseInt(split[1] + split[2])-1][col]) == '-') return "miss";
            if((my_sea[Integer.parseInt(split[1] + split[2])-1][col]) == 'O') return updateHitBoat(cell);
        }
        return "miss";
    }

    public void shoot() throws IOException, InterruptedException {
        if(gameOn.get(0)){
            System.out.println("Which enemy cell to attack ?");
            final ArrayList<String> target_list = new ArrayList<>();
            target_list.add(computerPlayer.cellToTarget());
            System.out.println(target_list.get(0));
            final HttpRequest request = new Request().getRequest(this.address.get(0) + "/api/game/fire?cell=" + target_list.get(0));
            final HttpClient client = HttpClient.newHttpClient();
            final HttpResponse<?> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            consequence(response.body().toString(), target_list);
        }
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

    public String updateHitBoat(String cell){
        for (final Boat b : boat_list) {
            if(b.getBoatPos().contains(cell)){
                if(!b.getHitBoatPos().contains(cell))
                    b.setHitBoatPos(cell);
                if(b.getHitBoatPos().containsAll(b.getBoatPos())){
                    b.updateStatus();
                    return "sunk";
                }
            }
        }
        return "hit";
    }

    public boolean statusGame(){
        for (final Boat b : boat_list) {
            if(!b.getStatus())
                return true;
        }
        return false;
    }
}
