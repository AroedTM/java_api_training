package fr.lernejo.navy_battle.check;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

class CheckTest {

    private final Check check = new Check();
    private final String jsonMaster = "{\n" +"    \"$schema\": \"http://json-schema.org/schema#\",\n" +
        "    \"type\": \"object\",\n" +"    \"properties\": {\n" +"        \"id\": {\n" +
        "            \"type\": \"string\"\n" +"        }\n" +"    },\n" +"    \"required\": [\n" +"        \"id\"\n" +"    ]\n" +"}";

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
    void json_is_similar() throws IOException {
        final boolean bool = check.validateJson("{\"id\":\"45145151\"}", jsonMaster);
        Assertions.assertThat(bool).isTrue();
    }

    @Test
    void json_is_different() throws IOException {
        final boolean bool = check.validateJson("{\"test\":\"45145151\"}", jsonMaster);
        Assertions.assertThat(bool).isFalse();
    }

}
