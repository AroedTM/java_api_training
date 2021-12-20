package fr.lernejo.navy_battle.game;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class SeaTest {

    private final Sea sea = new Sea();
    private final Boat boat = new PorteAvion();
    private final ArrayList<String> positions = new ArrayList<>(List.of("A1", "A2", "A3", "A4", "A5"));

    @Test
    void fill_is_correct() {
        final char[][] tab1 = new char[1][1];
        final char[][] tab2 = new char[1][1];
        ArrayList<String> positions = new ArrayList<>(List.of("A1"));
        tab1[0][0] = 'O';
        Assertions.assertThat(sea.fillCells(positions, tab2, 'O')).isEqualTo(tab1);
    }

    @Test
    void test_hit(){
        boat.setBoatPos(positions);
        final ArrayList<Boat> boatList = new ArrayList<>(List.of(boat));
        Assertions.assertThat(sea.updateHitBoat("A1", boatList)).isEqualTo("hit");
    }

    @Test
    void test_sunk(){
        boat.setBoatPos(positions);
        boat.setHitBoatPos("A2");
        boat.setHitBoatPos("A3");
        boat.setHitBoatPos("A4");
        boat.setHitBoatPos("A5");
        final ArrayList<Boat> boatList = new ArrayList<>(List.of(boat));
        Assertions.assertThat(sea.updateHitBoat("A1", boatList)).isEqualTo("sunk");
    }
}
