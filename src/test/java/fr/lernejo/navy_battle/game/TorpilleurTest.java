package fr.lernejo.navy_battle.game;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class TorpilleurTest {

    private final Torpilleur torpilleur = new Torpilleur();

    @Test
    void set_and_get_ok() {
        final ArrayList<String> positions = new ArrayList<>();
        positions.add("A2");
        positions.add("A3");
        torpilleur.setBoatPos(positions);
        Assertions.assertThat(torpilleur.getBoatPos()).isEqualTo(positions);
    }

    @Test
    void set_and_get_hit_ok() {
        final ArrayList<String> positions = new ArrayList<>();
        positions.add("A2");
        torpilleur.setHitBoatPos(positions.get(0));
        Assertions.assertThat(torpilleur.getHitBoatPos()).isEqualTo(positions);
    }

    @Test
    void _update_and_see_status(){
        torpilleur.updateStatus();
        Assertions.assertThat(torpilleur.getStatus()).isTrue();
    }

    @Test
    void name_is_ContreTorpilleur() {
        Assertions.assertThat(torpilleur.name()).isEqualTo("Torpedo boat");
    }

    @Test
    void size_is_3() {
        Assertions.assertThat(torpilleur.size()).isEqualTo(2);
    }
}
