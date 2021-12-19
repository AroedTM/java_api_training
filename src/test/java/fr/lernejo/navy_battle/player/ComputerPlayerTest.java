package fr.lernejo.navy_battle.player;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ComputerPlayerTest {

    private final ComputerPlayer computerPlayer = new ComputerPlayer();

    @Test
    void cell_to_target_correspond_regex(){
        final String cell = computerPlayer.cellToTarget();
        Assertions.assertTrue(cell.matches("[A-J]([1-9]|10)"));
    }

    @Test
    void cell_to_place_correspond_regex(){
        final String cell = computerPlayer.placeBoat();
        Assertions.assertTrue(cell.matches("[A-J]([1-9]|10)"));
    }

}
