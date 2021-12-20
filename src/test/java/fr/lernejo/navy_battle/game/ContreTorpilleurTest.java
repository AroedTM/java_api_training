package fr.lernejo.navy_battle.game;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class ContreTorpilleurTest {

    private final ContreTorpilleur contreTorpilleur = new ContreTorpilleur();

    @Test
    void set_and_get_ok() {
        final ArrayList<String> positions = new ArrayList<>();
        positions.add("A2");
        positions.add("A3");
        contreTorpilleur.setBoatPos(positions);
        Assertions.assertThat(contreTorpilleur.getBoatPos()).isEqualTo(positions);
    }

    @Test
    void set_and_get_hit_ok() {
        final ArrayList<String> positions = new ArrayList<>();
        positions.add("A2");
        contreTorpilleur.setHitBoatPos(positions.get(0));
        Assertions.assertThat(contreTorpilleur.getHitBoatPos()).isEqualTo(positions);
    }

    @Test
    void _update_and_see_status(){
        contreTorpilleur.updateStatus();
        Assertions.assertThat(contreTorpilleur.getStatus()).isTrue();
    }

    @Test
    void name_is_ContreTorpilleur() {
        Assertions.assertThat(contreTorpilleur.name()).isEqualTo("Destroyer");
    }

    @Test
    void size_is_3() {
        Assertions.assertThat(contreTorpilleur.size()).isEqualTo(3);
    }
}
