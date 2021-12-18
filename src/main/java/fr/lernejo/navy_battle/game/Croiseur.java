package fr.lernejo.navy_battle.game;

import java.util.ArrayList;

public class Croiseur implements Boat {

    private final ArrayList<String> positions = new ArrayList<>();

    @Override
    public void setBoatPos(ArrayList<String> pos){
        this.positions.addAll(pos);
    }

    @Override
    public ArrayList<String> getBoatPos() {
        return this.positions;
    }

    @Override
    public String name() {
        return "Croiseur";
    }

    @Override
    public int size() {
        return 4;
    }
}
