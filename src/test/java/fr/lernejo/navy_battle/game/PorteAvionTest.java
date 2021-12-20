/*package fr.lernejo.navy_battle.game;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class PorteAvionTest {

    private final PorteAvion porteAvion = new PorteAvion();

    @Test
    void set_and_get_ok() {
        final ArrayList<String> positions = new ArrayList<>();
        positions.add("A2");
        positions.add("A3");
        porteAvion.setBoatPos(positions);
        Assertions.assertThat(porteAvion.getBoatPos()).isEqualTo(positions);
    }

    @Test
    void set_and_get_hit_ok() {
        final ArrayList<String> positions = new ArrayList<>();
        positions.add("A2");
        porteAvion.setHitBoatPos(positions.get(0));
        Assertions.assertThat(porteAvion.getHitBoatPos()).isEqualTo(positions);
    }

    @Test
    void name_is_ContreTorpilleur() {
        Assertions.assertThat(porteAvion.name()).isEqualTo("Aircraft carrier");
    }

    @Test
    void size_is_3() {
        Assertions.assertThat(porteAvion.size()).isEqualTo(5);
    }

}*/
