package fr.lernejo.navy_battle.player;

import fr.lernejo.navy_battle.game.Boat;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ComputerPlayer implements Player {

    private final Random random = new Random();
    private final ArrayList<String> letterList = new ArrayList<>(List.of("A","B","C","D","E","F","G","H","I","J"));
    private final ArrayList<String> alreadyTarget = new ArrayList<>();
    private final ArrayList<String> alreadyPlaced = new ArrayList<>();
    private final ArrayList<Boolean> again = new ArrayList<>();
    private final ArrayList<String> positions = new ArrayList<>();

    @Override
    public String cellToTarget() {
        final ArrayList<String> cell = new ArrayList<>();
        do{
            final String line = letterList.get(random.nextInt(10));
            final String col = Integer.toString(random.nextInt(10) + 1);
            final String target = line + col;
            if(!cell.isEmpty())
                cell.remove(0);
            cell.add(target);
        }while(alreadyTarget.contains(cell.get(0)));
        alreadyTarget.add(cell.get(0));
        return cell.get(0);
    }

    @Override
    public ArrayList<String> placeBoat(Boat boat) {
        final int dir = random.nextInt(2);
        do{
            positions.clear();
            again.clear();
            again.add(false);
            if(dir == 0) testPlacementColumn(boat);
            else{testPlacementLine(boat);}
            for(String pos : positions){
                if(alreadyPlaced.contains(pos)){
                    again.remove(0);
                    again.add(true);}}
        }while(again.get(0));
        alreadyPlaced.addAll(positions);
        return positions;
    }

    @Override
    public void testPlacementColumn(Boat boat) {
        final int intCol = random.nextInt(10);
        final String col = letterList.get(intCol);
        final int intLine = random.nextInt(6) + 1;
        final String line = Integer.toString(intLine);
        final String cell = col + line;
        positions.add(cell);
        for(int i = 1; i < boat.size(); i++) {
            final String nextLine = Integer.toString(intLine + i);
            final String nextCell = col + nextLine;
            positions.add(nextCell);
        }
    }

    @Override
    public void testPlacementLine(Boat boat){
        final int intCol = random.nextInt(6);
        final String col = letterList.get(intCol);
        final int intLine = random.nextInt(10) + 1;
        final String line = Integer.toString(intLine);
        final String cell = col + line;
        positions.add(cell);
        for(int i = 1; i < boat.size(); i++) {
            final String nextCol = letterList.get(intCol + i);
            final String nextCell = nextCol + line;
            positions.add(nextCell);
        }
    }
}
