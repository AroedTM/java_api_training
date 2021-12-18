package fr.lernejo.navy_battle.game;

import java.util.ArrayList;

public interface Boat {
    String name();
    int size();
    void setBoatPos(ArrayList<String> pos);
    ArrayList<String> getBoatPos();
}
