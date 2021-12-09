package fr.lernejo.navy_battle.check;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class CheckTest {

    private final Check check = new Check();

    @Test
    void four_is_Int() {
        boolean bool = check.isStringInt("4");
        Assertions.assertThat(bool).isTrue();
    }

    @Test
    void server_is_not_Int() {
        boolean bool = check.isStringInt("server");
        Assertions.assertThat(bool).isFalse();
    }
}
