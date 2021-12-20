/*package fr.lernejo.navy_battle.player;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ComputerPlayer implements Player {

    final Random random = new Random();
    final ArrayList<String> letterList = new ArrayList<>(List.of("A","B","C","D","E","F","G","H","I","J"));
    final ArrayList<String> alreadyTarget = new ArrayList<>();
    final ArrayList<String> alreadyPlaced = new ArrayList<>();

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
    public String placeBoat() {
        final ArrayList<String> cell = new ArrayList<>();
        do{
            final String line = letterList.get(random.nextInt(10));
            final String col = Integer.toString(random.nextInt(10) + 1);
            final String target = line + col;
            if(!cell.isEmpty())
                cell.remove(0);
            cell.add(target);
        }while(alreadyPlaced.contains(cell.get(0)));
        alreadyPlaced.add(cell.get(0));
        return cell.get(0);
    }
}*/
