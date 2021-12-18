package fr.lernejo.navy_battle.game;

import fr.lernejo.navy_battle.check.Check;

import java.util.ArrayList;
import java.util.Arrays;

public class Sea {

    final public Check check = new Check();

    public void initializeSea(char[][] sea) {
        for (final char[] chars : sea) {
            Arrays.fill(chars, '-');
        }
    }

    public char[][] fillCells(ArrayList<String> positions, char[][] sea, char caracter){
        for(final String pos : positions){
            final String[] split = pos.split("");
            final int col = check.getIntFromString(split[0]);
            if(pos.length() == 2){
                sea[Integer.parseInt(split[1])-1][col] = caracter;
            }else{
                sea[Integer.parseInt(split[1] + split[2])-1][col] = caracter;
            }
        }
        return sea;
    }

    public void displaySea(char[][] leftSea, char[][] rightSea){
        System.out.println("     A B C D E F G H I J | A B C D E F G H I J \n   ---------------------------------------------");
        int ligne = 1;
        for (final char[] chars : leftSea) {
            if(ligne != 10)
                System.out.print(" ");
            System.out.print(ligne + " | ");
            for (final char aChar : chars) System.out.print(aChar + " ");
            System.out.print("|");
            for (final char aChar : rightSea[ligne-1]) System.out.print(" " + aChar);
            System.out.println(" | " + ligne);
            ligne++;
        }
        System.out.println("   ---------------------------------------------");
    }
}
