package fr.lernejo.navy_battle.player;

import fr.lernejo.navy_battle.game.Boat;

import java.util.ArrayList;

public interface Player {
    String cellToTarget();
    ArrayList<String> placeBoat(Boat boat);
    ArrayList<String> testPlacementColumn(Boat boat);
    ArrayList<String> testPlacementLine(Boat boat);
}
