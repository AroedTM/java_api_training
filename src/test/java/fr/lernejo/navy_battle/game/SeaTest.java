package fr.lernejo.navy_battle.game;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class SeaTest {

    private final Sea sea = new Sea();

    @Test
    void fill_is_correct() {
        final char[][] tab1 = new char[1][1];
        final char[][] tab2 = new char[1][1];
        ArrayList<String> positions = new ArrayList<>(List.of("A1"));
        tab1[0][0] = 'O';
        Assertions.assertThat(sea.fillCells(positions, tab2, 'O')).isEqualTo(tab1);
    }
}
