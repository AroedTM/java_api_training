package fr.lernejo.navy_battle.player;

import fr.lernejo.navy_battle.game.PorteAvion;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class ComputerPlayerTest {

    private final ComputerPlayer computerPlayer = new ComputerPlayer();

    @Test
    void cell_to_target_correspond_regex(){
        final String cell = computerPlayer.cellToTarget();
        Assertions.assertTrue(cell.matches("[A-J]([1-9]|10)"));
    }

    @Test
    void cell_to_place_correspond_regex(){
        final ArrayList<String> cell = computerPlayer.placeBoat(new PorteAvion());
        org.assertj.core.api.Assertions.assertThat(cell.size()).isEqualTo(5);
    }

}
