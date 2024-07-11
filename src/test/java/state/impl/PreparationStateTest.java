package state.impl;

import game.Game;
import org.junit.jupiter.api.Test;
import player.Player;
import role.ActionType;

import java.util.List;

class PreparationStateTest {
    @Test
    void test1() {
        var player1 = Player.builder()
                .name("Player_1")
                .build();

        var player2 = Player.builder()
                .name("Player_2")
                .build();

        var player3 = Player.builder()
                .name("Player_3")
                .build();

        var player4 = Player.builder()
                .name("Player_4")
                .build();

        var player5 = Player.builder()
                .name("Player_5")
                .build();


        var game = new Game();
        addPlayers(game, List.of(player1, player2, player3, player4, player5));

        game.nextGameLevel();

        game.vote(null, "Player_3", ActionType.READY);
        game.vote(null, "Player_4", ActionType.READY);
        game.vote(null, "Player_5", ActionType.READY);
        game.vote(null, "Player_1", ActionType.READY);
        game.vote(null, "Player_2", ActionType.READY);

        game.gameInfo();
    }

    private void addPlayers(Game game, List<Player> players) {
        players.forEach(player -> game.getPlayerByName().put(player.getName(), player));
    }
}