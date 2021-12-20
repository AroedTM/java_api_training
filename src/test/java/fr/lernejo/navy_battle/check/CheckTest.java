package fr.lernejo.navy_battle.check;

import fr.lernejo.navy_battle.game.Game;
import fr.lernejo.navy_battle.server.StartHandler;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class CheckTest {

    private final Check check = new Check();

    @Test
    void four_is_Int() {
        final boolean bool = check.isStringInt("4");
        Assertions.assertThat(bool).isTrue();
    }

    @Test
    void server_is_not_Int() {
        final boolean bool = check.isStringInt("server");
        Assertions.assertThat(bool).isFalse();
    }

    @Test
    void cell_is_A1(){
        final String cell = check.getCell("http://localhost:9870/api/game/start?cell=A1");
        Assertions.assertThat(cell).isEqualTo("A1");
    }

    @Test
    void B_is_equal_to_1(){
        final int number = check.getIntFromString("B2");
        Assertions.assertThat(number).isEqualTo(1);
    }
}
