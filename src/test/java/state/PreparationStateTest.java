package state;

import game.Game;
import org.junit.jupiter.api.Test;
import player.Player;

import java.util.List;

class PreparationStateTest {

    @Test
    void test1() {
        var player1 = Player.builder()
                .name("Player_1")
                .ready(false)
                .build();

        var player2 = Player.builder()
                .name("Player_2")
                .ready(false)
                .build();

        var game = new Game();
        addPlayers(game, List.of(player1, player2));

        game.nextGameLevel();

        player1.setReady(true);
        game.nextGameLevel();

        player2.setReady(true);
        game.nextGameLevel();
    }

    private void addPlayers(Game game, List<Player> players) {
        players.forEach(e -> game.getPlayerByName().put(e.getName(), e));
    }
}