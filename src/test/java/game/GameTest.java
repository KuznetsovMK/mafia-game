package game;

import org.junit.jupiter.api.Test;
import player.Player;
import role.ActionType;
import role.RoleNameConst;

import java.util.ArrayList;
import java.util.List;

class GameTest {
    @Test
    void test1() {
        var game = new Game();

        addPlayers(game, initPlayers());
        game.nextGameLevel();

        List<Player> players = game.getPlayerByName().values().stream().toList();

        allReady(game, players);


        mafiaMeeting(game, players);

        while (game.getWinner() == null) {
            judge(game, players);
            mafiaShooting(game, players);
            leaderShooting(game, players);
        }

        game.gameInfo();

    }

    private List<Player> initPlayers() {
        var result = new ArrayList<Player>();
        var name = "Player_";

        for (int i = 1; i < 30; i++) {
            result.add(Player.builder()
                    .name(name + i)
                    .build());
        }

        return result;
    }

    private void addPlayers(Game game, List<Player> players) {
        players.forEach(player -> game.getPlayerByName().put(player.getName(), player));
    }

    private void allReady(Game game, List<Player> players) {
        players.forEach(player -> game.vote(null, player.getName(), ActionType.READY));
    }

    private void mafiaMeeting(Game game, List<Player> players) {
        var target = players.stream()
                .filter(player -> !RoleNameConst.MAFIA.equals(player.getRole()))
                .findFirst()
                .orElse(null);

        players.stream()
                .filter(player -> RoleNameConst.MAFIA.equals(player.getRole()))
                .forEach(player -> game.vote(target.getName(), player.getName(), ActionType.SHOOT));
    }

    private void judge(Game game, List<Player> players) {
        var target = players.stream()
                .filter(player -> player.isAlive())
                .findFirst()
                .orElse(null);

        players.stream()
                .filter(Player::isAlive)
                .forEach(player -> game.vote(target.getName(), player.getName(), ActionType.JUDGE));
    }

    private void mafiaShooting(Game game, List<Player> players) {
        var target = players.stream()
                .filter(Player::isAlive)
                .filter(player -> !RoleNameConst.MAFIA.equals(player.getRole()))
                .findFirst()
                .orElse(null);

        players.stream()
                .filter(player -> RoleNameConst.MAFIA.equals(player.getRole()))
                .forEach(player -> game.vote(target.getName(), player.getName(), ActionType.SHOOT));
    }

    private void leaderShooting(Game game, List<Player> players) {
        var target = players.stream()
                .filter(Player::isAlive)
                .filter(player -> !RoleNameConst.DETECTIVE.equals(player.getRole()))
                .findFirst()
                .orElse(null);

        players.stream()
                .filter(player -> RoleNameConst.DETECTIVE.equals(player.getRole()))
                .forEach(player -> game.vote(target.getName(), player.getName(), ActionType.SHOOT));
    }
}