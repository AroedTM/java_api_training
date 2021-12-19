package fr.lernejo.navy_battle.game;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class CroiseurTest {

    private final Croiseur croiseur = new Croiseur();

    @Test
    void set_and_get_ok() {
        final ArrayList<String> positions = new ArrayList<>();
        positions.add("A2");
        positions.add("A3");
        croiseur.setBoatPos(positions);
        Assertions.assertThat(croiseur.getBoatPos()).isEqualTo(positions);
    }

    @Test
    void set_and_get_hit_ok() {
        final ArrayList<String> positions = new ArrayList<>();
        positions.add("A2");
        croiseur.setHitBoatPos(positions.get(0));
        Assertions.assertThat(croiseur.getHitBoatPos()).isEqualTo(positions);
    }

    @Test
    void name_is_ContreTorpilleur() {
        Assertions.assertThat(croiseur.name()).isEqualTo("Cruiser");
    }

    @Test
    void size_is_3() {
        Assertions.assertThat(croiseur.size()).isEqualTo(4);
    }

}
