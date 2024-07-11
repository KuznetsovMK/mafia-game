package state.impl;

import game.Game;
import org.junit.jupiter.api.Test;
import player.Player;
import role.ActionType;
import role.impl.Detective;
import role.impl.Mafia;
import role.impl.Settler;

import java.util.List;

class LawsuitStateTest {
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

        var player5 = Player.builder()
                .name("Player_5")
                .ready(true)
                .build();


        var game = new Game();
        addPlayers(game, List.of(player1, player2, player3, player4, player5));
        game.nextGameLevel();

        game.setSettler(new Settler(game, List.of(player1, player2)));
        game.setDetective(new Detective(game, player3));
        game.setMafia(new Mafia(List.of(player4, player5)));

        game.setState(game.getLawsuitState());
        game.nextGameLevel();

        game.vote("Player_2", "Player_1", ActionType.JUDGE);
        game.vote("Player_1", "Player_3", ActionType.JUDGE);
        game.vote("Player_1", "Player_4", ActionType.JUDGE);
        game.vote("Player_1", "Player_2", ActionType.JUDGE);
        game.vote("Player_4", "Player_5", ActionType.JUDGE);


        game.gameInfo();

    }

    private void addPlayers(Game game, List<Player> players) {
        players.forEach(player -> game.getPlayerByName().put(player.getName(), player));
    }
}