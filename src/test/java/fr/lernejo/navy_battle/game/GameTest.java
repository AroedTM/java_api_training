package fr.lernejo.navy_battle.game;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class GameTest {

    private final Game game = new Game();

    @Test
    void amount_of_boat_is_17(){
        game.placeBoats();
        final ArrayList<Boolean> list = new ArrayList<>();
        for(char[] c : game.my_sea){
            for(char ch : c){
                if(ch == 'O')
                    list.add(true);}}
        Assertions.assertThat(list.size()).isEqualTo(17);
    }
}
