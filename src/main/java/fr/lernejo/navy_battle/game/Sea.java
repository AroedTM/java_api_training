package fr.lernejo.navy_battle.game;

import fr.lernejo.navy_battle.check.Check;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Sea {

    private final Check check = new Check();

    public void initializeSea(char[][] sea) {
        for (final char[] chars : sea)
            Arrays.fill(chars, '-');
    }

    public char[][] fillCells(ArrayList<String> positions, char[][] sea, char caracter){
        for(final String pos : positions){
            final String[] split = pos.split("");
            final int col = check.getIntFromString(split[0]);
            if(pos.length() == 2)
                sea[Integer.parseInt(split[1])-1][col] = caracter;
            else{
                sea[Integer.parseInt(split[1] + split[2])-1][col] = caracter;}
        }return sea;
    }

    public void displaySea(char[][] leftSea, char[][] rightSea){
        System.out.println("     A B C D E F G H I J | A B C D E F G H I J \n   ---------------------------------------------");
        final ArrayList<Integer> ligne = new ArrayList<>(List.of(1));
        for (final char[] chars : leftSea) {
            if(ligne.get(0) != 10) System.out.print(" ");
            System.out.print(ligne + " ");
            for (final char aChar : chars) System.out.print(aChar + " ");
            System.out.print("|");
            for (final char aChar : rightSea[ligne.get(0)-1]) System.out.print(" " + aChar);
            System.out.println(" " + ligne);
            final int tmp = ligne.get(0);
            ligne.remove(0);
            ligne.add(tmp+1);}
        System.out.println("   ---------------------------------------------");
    }

    public void updateMyBoard(String cell, String consequence, char[][] my_sea, char[][] enemy_sea){
        if(consequence.equals("hit") || consequence.equals("sunk")) fillCells(new ArrayList<>(List.of(cell)), my_sea, 'H');
        else{fillCells(new ArrayList<>(List.of(cell)), my_sea, 'X');}
        displaySea(my_sea, enemy_sea);
    }

    public String whatInCell(String cell, char[][] my_sea, List<Boat> boat_list) {
        final String[] split = cell.split("");
        final int col = new Check().getIntFromString(split[0]);
        if (cell.length() == 2) {
            if ((my_sea[Integer.parseInt(split[1]) - 1][col]) == '-') return "miss";
            if ((my_sea[Integer.parseInt(split[1]) - 1][col]) == 'O') return updateHitBoat(cell, boat_list);}
        else {
            if ((my_sea[Integer.parseInt(split[1] + split[2]) - 1][col]) == '-') return "miss";
            if ((my_sea[Integer.parseInt(split[1] + split[2]) - 1][col]) == 'O') return updateHitBoat(cell, boat_list);}
        return "miss";
    }

    public String updateHitBoat(String cell, List<Boat> boat_list){
        for (final Boat b : boat_list) {
            if(b.getBoatPos().contains(cell)){
                if(!b.getHitBoatPos().contains(cell))
                    b.setHitBoatPos(cell);
                if(b.getHitBoatPos().containsAll(b.getBoatPos())){
                    b.updateStatus();
                    return "sunk";}}}
        return "hit";
    }
}
