package state;

import game.Game;
import org.junit.jupiter.api.Test;
import player.Player;
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

        var suspect = Player.builder()
                .name("Suspect")
                .ready(true)
                .build();


        new Settler(player1);
        new Settler(player2);
        new Settler(suspect);

        var game = new Game();
        addPlayers(game, List.of(player1, player2, suspect));

        game.nextGameLevel();
        game.nextGameLevel();
        game.nextGameLevel();
        game.nextGameLevel();
        game.nextGameLevel();

        game.getSuspect().put("Suspect", List.of("Player_1", "Player_2"));
        game.getSuspect().put("Player_1", List.of("Suspect"));
        game.nextGameLevel();


        game.nextGameLevel();
        game.nextGameLevel();
        game.nextGameLevel();
        game.nextGameLevel();
        game.nextGameLevel();
        game.nextGameLevel();

        playerList(game);

    }

    private void addPlayers(Game game, List<Player> players) {
        players.forEach(e -> game.getPlayerByName().put(e.getName(), e));
    }

    private void playerList(Game game) {
        game.getPlayerByName().values()
                .forEach(System.out::println);
    }
}