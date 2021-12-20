/*package fr.lernejo.navy_battle.game;

import java.util.ArrayList;
import java.util.List;

public class Torpilleur implements Boat {

    private final ArrayList<String> positions = new ArrayList<>();
    private final ArrayList<String> hitPositions = new ArrayList<>();
    private final ArrayList<Boolean> isSunk = new ArrayList<>(List.of(false));

    @Override
    public void updateStatus(){
        this.isSunk.remove(0);
        this.isSunk.add(true);
    }

    @Override
    public boolean getStatus() {
        return this.isSunk.get(0);
    }

    @Override
    public void setBoatPos(ArrayList<String> pos) {
        this.positions.addAll(pos);
    }

    @Override
    public ArrayList<String> getBoatPos() {
        return this.positions;
    }

    @Override
    public void setHitBoatPos(String  pos) {
        this.hitPositions.add(pos);
    }

    @Override
    public ArrayList<String> getHitBoatPos() {
        return this.hitPositions;
    }

    @Override
    public String name() {
        return "Torpedo boat";
    }

    @Override
    public int size() {
        return 2;
    }
}*/
