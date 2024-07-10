package state;

import game.Game;
import org.junit.jupiter.api.Test;
import player.Player;
import role.impl.Settler;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DailyResultStateTest {
    @Test
    void test1() {
        var player1 = Player.builder()
                .name("Player_1")
                .ready(true)
                .build();

        var player2 = Player.builder()
                .name("Player_2")
                .ready(true)
                .build();


//        new Settler(player1);
//        new Settler(player2);

        var game = new Game();
        addPlayers(game, List.of(player1, player2));
        game.nextGameLevel();
        game.nextGameLevel();
        game.nextGameLevel();

        game.getNightResults().add("Событие первое");
        game.getNightResults().add("Событие второе");
        game.getNightResults().add("Событие третье");

        game.nextGameLevel();
    }

    private void addPlayers(Game game, List<Player> players) {
        players.forEach(e -> game.getPlayerByName().put(e.getName(), e));
    }
}