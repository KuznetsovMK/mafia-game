package state.impl;

import game.Game;
import org.junit.jupiter.api.Test;
import player.Player;
import role.ActionType;
import role.impl.Detective;
import role.impl.Mafia;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MafiaShootingStateTest {
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

        var player3 = Player.builder()
                .name("Player_3")
                .ready(true)
                .build();

        var player4 = Player.builder()
                .name("Player_4")
                .ready(true)
                .build();


        var game = new Game();
        addPlayers(game, List.of(player1, player2, player3, player4));
        game.nextGameLevel();

        game.setDetective(new Detective(game, player2));
        game.setMafia(new Mafia(List.of(player1, player3)));

        game.setState(game.getMafiaShootingState());
        game.nextGameLevel();

        game.vote("Player_2", "Player_1", ActionType.SHOOT);
        game.vote("Player_2", "Player_3", ActionType.SHOOT);


        game.gameInfo();

    }

    private void addPlayers(Game game, List<Player> players) {
        players.forEach(player -> game.getPlayerByName().put(player.getName(), player));
    }
}