package fr.lernejo.navy_battle.player;

import fr.lernejo.navy_battle.game.Boat;

import java.util.ArrayList;

public interface Player {
    String cellToTarget();
    ArrayList<String> placeBoat(Boat boat);
    void testPlacementColumn(Boat boat);
    void testPlacementLine(Boat boat);
}
