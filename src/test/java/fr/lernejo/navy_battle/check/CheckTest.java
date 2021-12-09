package fr.lernejo.navy_battle.check;

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
    void convert_port(){
        final String string = check.convertArgPort("http://localhost:9876");
        Assertions.assertThat(string).isEqualTo("9876");
    }

}
