package state.impl;

import game.Game;
import player.Player;
import role.RoleNameConst;
import state.State;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static java.util.function.UnaryOperator.identity;
import static java.util.stream.Collectors.toMap;


public class DefineRoleState implements State {
    private static final String INFO = "Назначение ролей игрокам.";
    private static final String ALL_READY = "Роли назначены.";
    private final Game game;
    private List<Player> players;

    @Override
    public void nextGameLevel() {
        System.out.println(INFO);

        defineRoles();
        System.out.println(ALL_READY);

        goNextGameLevel();
    }


    private void defineRoles() {
        players = new ArrayList<>(game.getPlayerByName().values().stream().toList());
        Collections.shuffle(players);

        createMafia();
        createDetective();
        createSettler();

        game.setPlayerByName(getPlayersMap());
    }

    private void createMafia() {
        int mafiaPlayersCount = players.size() / 3;

        for (int i = 0; i < mafiaPlayersCount; i++) {
            var player = players.get(i);
            if (player.getRole() == null) {
                player.setRole(RoleNameConst.MAFIA);
                player.setAlive(true);

                game.getMafia().addPlayer(player);
            }
        }

    }

    private void createDetective() {
        int i = 0;

        while (true) {
            var player = players.get(i);
            if (player.getRole() == null) {
                player.setRole(RoleNameConst.DETECTIVE);
                player.setAlive(true);

                game.getDetective().addPlayer(player);
                break;
            }

            i++;
        }
    }

    private void createSettler() {
        players.stream()
                .filter(player -> player.getRole() == null)
                .forEach(player -> {
                    player.setRole(RoleNameConst.SETTLER);
                    player.setAlive(true);

                    game.getSettler().addPlayer(player);
                });
    }

    private Map<String, Player> getPlayersMap() {
        return players.stream()
                .collect(toMap(
                        Player::getName,
                        identity()));
    }

    private void goNextGameLevel() {
        game.setState(game.getMafiaMeetingState());
        game.nextGameLevel();
    }

    public DefineRoleState(Game game) {
        this.game = game;
    }
}
