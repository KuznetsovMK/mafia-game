package state;

import game.Game;
import org.junit.jupiter.api.Test;
import player.Player;
import role.impl.Settler;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DefiningRoleStateTest {

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


        var game = new Game();
        addPlayers(game, List.of(player1, player2));
        game.nextGameLevel();

        new Settler(player2);
        game.nextGameLevel();

        new Settler(player1);
        game.nextGameLevel();
    }

    private void addPlayers(Game game, List<Player> players) {
        players.forEach(player -> game.getPlayerByName().put(player.getName(), player));
    }
}